package cn.itcast.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/requestdemo3")
public class requestdemo3 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         /*(*)String getHeader(String name):通过请求头的名称获取请求头的值
         * Enumeration<String> getHeaderNames():获取所有的请求头名称*/
        String agent = request.getHeader("user-agent");
        if(agent.contains("Chrome")){
            System.out.println("谷歌来了。。。");
        }else if(agent.contains("Firefox")){
            System.out.println("火狐来了。。。");
        }
    }
}
