package cn.itcast.web.filter;

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
 * 过滤敏感词
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //1.创建代理对象
        ServletRequest req_proxy = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //2.增强getParameter()
                //2.1判断是否是getParameter()方法
                if(method.getName().equals("getParameter")){
                    //3.获取参数
                   String param = (String) method.invoke(req, args);
                   //4.判断参数中是否包含敏感词
                   if(param!=null){
                       for(String str:list){
                           if(param.contains(str)){
                                param = param.replaceAll(str,"***");
                           }
                       }
                   }
                   return param;
                }
                return method.invoke(req, args);
            }
        });

        chain.doFilter(req_proxy,resp);
    }
    //服务器启动后，读取敏感词,并存入list
    private List<String> list = new ArrayList<String>();
    @Override
    public void init(FilterConfig config) throws ServletException {

        try {
            //1.获取文件真实路径
            String realPath = config.getServletContext().getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //2.读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            //3.将文件的每一行存入list中
            String line = null;
            while((line = br.readLine())!=null){
                list.add(line);
            }

            br.close();
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }

}
