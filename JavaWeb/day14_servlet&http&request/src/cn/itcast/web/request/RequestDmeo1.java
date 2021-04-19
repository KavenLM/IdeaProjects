package cn.itcast.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requestdemo1")
public class RequestDmeo1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* 1. 获取请求方式 ：GET
                * String getMethod()
        2. (*)获取虚拟目录：/day14
                * String getContextPath()
        3. 获取Servlet路径: /demo1
                * String getServletPath()
        4. 获取get方式请求参数：name=zhangsan
                * String getQueryString()
        5. (*)获取请求URI：/day14/demo1
                * String getRequestURI():		/day14/demo1
                * StringBuffer getRequestURL()  :http://localhost/day14/demo1
        6. 获取协议及版本：HTTP/1.1
                * String getProtocol()
        7. 获取客户机的IP地址：* String getRemoteAddr()*/


        String method = request.getMethod();
        System.out.println("请求方法："+method);

        String contextPath = request.getContextPath();
        System.out.println("虚拟目录:"+contextPath);

        String servletPath = request.getServletPath();
        System.out.println("servlet路径："+servletPath);

        String queryString = request.getQueryString();
        System.out.println("请求参数："+queryString);

        String requestURI = request.getRequestURI();
        System.out.println("请求uri："+requestURI);

        StringBuffer requestURL = request.getRequestURL();
        System.out.println("请求url："+requestURL);

        String protocol = request.getProtocol();
        System.out.println("请求协议："+protocol);

        String remoteAddr = request.getRemoteAddr();
        System.out.println("客户机ip地址："+remoteAddr);
    }
}
