package cn.lm.web.servlet;

import cn.lm.domain.User;
import cn.lm.service.UserService;
import cn.lm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数id
        String id = request.getParameter("id");
        //2.调用service查询的id对应的用户记录
        UserService service = new UserServiceImpl();
        User user = service.findUserById(Integer.parseInt(id));
        //3.将user对象放入request中
        request.setAttribute("user",user);
        //4.转发到update.jsp页面
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
