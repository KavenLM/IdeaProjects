package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录过滤器
 * 未登录无法访问服务器其它资源
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        //2.获取访问地址
        String uri = request.getRequestURI();
        //3.判断访问资源是否与登录相关
        if (uri.contains("/loginServlet") || uri.contains("/checkCodeServlet") || uri.contains("/login.jsp") ||
                uri.contains("/css/") || uri.contains("/fonts/") || uri.contains("/js/")){
            //3.1与登录相关，放行
            request.setAttribute("login_msg","您还未登录，请登录！");
            chain.doFilter(req, resp);
        }else{
            //3.2不相关
            Object session = request.getSession().getAttribute("user");
            if (session != null){
                //已登录,放行
                chain.doFilter(req,resp);
            }else{
                //未登录，跳转到登录界面
                request.setAttribute("login_msg","您还未登录，请登录！");
                request.getRequestDispatcher("/login.jsp").forward(req,resp);
            }

        }


    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {
    }

}
