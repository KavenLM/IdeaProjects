package cn.itcast.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requestdemo4")
public class requestdemo4 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         /*(*)String getHeader(String name):通过请求头的名称获取请求头的值
         * Enumeration<String> getHeaderNames():获取所有的请求头名称*/
        String  referer = request.getHeader("referer");
        System.out.println(referer);//http://localhost/day14/login.html
        if(referer.contains("day14")){
//            System.out.println("正常播放电影。。");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("正常播放电影。。");
        }else{
//            System.out.println("想看电影？来优酷吧！");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("想看电影？来优酷吧！");
        }
    }
}
