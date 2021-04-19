package cn.lm.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词过滤器
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.创建代理对象
        ServletRequest req_proxy = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //2.增强请求
                if (method.getName().equals("getParameter")) {
                    String value = (String) method.invoke(req, args);
                    //3.判断参数里面是否包含有敏感词汇
                    if (value!=null){
                        for(String str : list){
                            if (value.contains(str)){
                                value = value.replaceAll(str,"***");
                            }
                        }
                    }
                    return value;
                }

                return method.invoke(req,args);

            }
        });
        //放行
        chain.doFilter(req_proxy, resp);

    }
    List<String> list = new ArrayList<String>();
    @Override
    public void init(FilterConfig config) throws ServletException {
        try {
            String realPath = config.getServletContext().getRealPath("/WEB-INF/classes/敏感词汇.txt");
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line = null;
            while((line = br.readLine())!=null){
                list.add(line);
            }
            System.out.println(list);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }

}
