package cn.itcast.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * cookie存活时间
 */
@WebServlet("/cookieDemo4")
public class CookieDemo4 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1创建cookie
        Cookie c1 = new Cookie("msg","setMaxAge");

        //2.设置存活时间
        //c1.setMaxAge(30);//持久化cookie，30s后自动删除
        //c1.setMaxAge(-1);//默认，关闭浏览器自动删除
        //c1.setMaxAge(300);
        c1.setMaxAge(0); //删除cookie信息

        //3.发送cookie
        response.addCookie(c1);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
