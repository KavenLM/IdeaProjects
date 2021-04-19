package cn.itcast.web.servlet;

import cn.itcast.domian.PageBean;
import cn.itcast.domian.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.接收参数
        String currentPage = request.getParameter("currentPage");
        System.out.println("currentPage1:"+currentPage);
        String rows = request.getParameter("rows");
        if(currentPage==null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }
        System.out.println("currentPage2:"+currentPage);

        //获取条件查询参数
        Map<String, String[]> condition = request.getParameterMap();


        //2.调用service查询pageBean
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage,rows,condition);
        System.out.println(pb);
        //3.将pageBean存入request
        request.setAttribute("pb",pb);
        //将condition存入request
        request.setAttribute("cd",condition);

        //4.将页面转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
