package cn.itcast.web.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletContextDemo5")
public class ServletContextDemo5 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        //获取b.txt真实路劲
        String b = context.getRealPath("/b.txt");
        System.out.println(b);

        //获取c.txt正式路径
        String c = context.getRealPath("/WEB-INF/c.txt");
        System.out.println(c);

        //获取a.txt真实路径
        String a = context.getRealPath("/WEB-INF/classes/a.txt");
        System.out.println(a);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
