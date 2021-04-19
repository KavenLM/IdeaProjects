package cn.lm.web.servlet;

import cn.lm.domain.User;
import cn.lm.service.UserService;
import cn.lm.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.接收参数
        Map<String, String[]> map = request.getParameterMap();
        String verifycode = request.getParameter("verifycode");
        //3.校验验证码
        HttpSession session = request.getSession();
        String checkcodeServer = (String) session.getAttribute("CHECKCODE_SERVER");

        session.removeAttribute("CHECKCODE_SERVER");

        if(checkcodeServer==null || !checkcodeServer.equalsIgnoreCase(verifycode)){
            //3.1不通过
            request.setAttribute("login_msg","验证码输入错误！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        //3.2通过
        //3.3封装user对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserServiceImpl();
        //4.调用service校验用户名密码
        User loginUser = service.login(user);
        if(loginUser!=null){
            //登录成功
            session.setAttribute("user",loginUser);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            //登录失败
            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
