package cn.lm.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.判断请求的资源是否为登录相关资源
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if (uri.contains("/login.jsp")||uri.contains("/loginServlet")||uri.contains("/checkCodeServlet") ||
        uri.contains("/css/")||uri.contains("/fonts/")||uri.contains("/js/")){
            //是，则放行
            request.setAttribute("login_msg","您还未登录，请登录！");
            chain.doFilter(req,resp);
        }else{
            //不是
            //判断是否是登录状态
            Object user = request.getSession().getAttribute("user");
            if(user!=null){
                //如果已登录则放行
                chain.doFilter(req,resp);
            }else{
                //未登录
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
