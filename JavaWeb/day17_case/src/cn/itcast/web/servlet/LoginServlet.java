package cn.itcast.web.servlet;

import cn.itcast.domian.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;
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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取参数
        Map<String, String[]> map = request.getParameterMap();
        //2.1.获取用户填写的验证码
        String verifycode = request.getParameter("verifycode");



        //3.验证码校验
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("CHECKCODE_SERVER");
        //确保验证码只使用一次
        session.removeAttribute("CHECKCODE_SERVER");
        //如果程序生成的验证码为null，或者与用户输入的不同，则验证码有误
        if(checkCode==null || !checkCode.equalsIgnoreCase(verifycode)){
            request.setAttribute("login_msg","验证码错误!");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        //4.封装User对象
        User user = new User();

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //5.调用Service查询
        UserService service = new UserServiceImpl();
        User loginUser = service.login(user);

        //6.判断是否登录成功
        if(loginUser!=null){
            //登录成功
            //将用户信息存入session
            session.setAttribute("user",loginUser);
            //将页面重定向到index.jsp
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else{
            //登录失败
            //错误提示信息
            request.setAttribute("login_msg","用户名或密码错误!");
            //页面跳转回到登录页面login.jsp
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
