package cn.lm.web.servlet;

import cn.lm.domain.PageBean;
import cn.lm.domain.User;
import cn.lm.service.UserService;
import cn.lm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        String rows = request.getParameter("rows");
        String currentPage = request.getParameter("currentPage");
        if("".equals(rows) || rows == null){
            rows = "5";
        }
        if("".equals(currentPage) || currentPage == null){
            currentPage = "1";
        }
        Map<String, String[]> condition = request.getParameterMap();
        //3.调用service查询
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage,rows,condition);
//        System.out.println("PageBean~~~~:"+pb);
        //4.转发pb到list.jsp
        request.setAttribute("pb",pb);
        //将condition存入request
        request.setAttribute("cd",condition);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
