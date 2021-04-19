package cn.itcast.web;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        User loginUser = new User();
        //2.获取参数,以map形式获取
        Map<String, String[]> map = request.getParameterMap();
        if(map!=null && map.size()!=0){
            Set<String> keySet = map.keySet();
            for(String key : keySet){
                //2.1检测验证码
                if("checkCode".equals(key)){
                    HttpSession session = request.getSession();
//                    char[] c = (char[])session.getAttribute("checkCode");
//                      String checkCode = String.valueOf(c);
                    String checkCode = (String) session.getAttribute("checkCode");
                    //保证验证码只能用一次，用完就删
                    session.removeAttribute("checkCode");


//                    System.out.println("验证码："+checkCode);
                    //2.1.1如果用户输入验证码与session（服务器生成验证码不同），则转发到登录页面
                    String[] values = map.get(key);
                    for(String value : values){
                        //2.2如果用户输入验证码与服务器生成验证码不同,或者验证码为null，则转到登录页面，并提示验证码错误
                        if(checkCode == null || !(checkCode.toUpperCase()).equals(value.toUpperCase())){
//                            System.out.println("验证码错误");
                            //2.2.1利用cookie设置状态为checkFailed
                            Cookie cookie = new Cookie("checkMsg","checkFailed");
                            response.addCookie(cookie);
//                            request.getSession().setAttribute("checkMsg","验证码错误");
                            request.getRequestDispatcher("/login.jsp").forward(request,response);
                            return;
                        }
//                        System.out.println("验证码正确");
                    }
                }

                //3.1获取用户输入的用户名和密码，并包装进User
               if("username".equals(key)){
                   loginUser.setUsername(map.get(key)[0]);
                   System.out.println("username="+map.get(key)[0]);
               }
               if("password".equals(key)){
                   loginUser.setPassword(map.get(key)[0]);
                   System.out.println("password="+map.get(key)[0]);
               }

            }
            //4.校验用户名和密码
            UserDao dao = new UserDao();
            User user = dao.login(loginUser);

            if(user==null){
                //4.1登录失败
                System.out.println("密码或用户名错误");
                ////4.1.1用cookie传递用户登录成功与否
                Cookie c = new Cookie("userMsg","loginFailed");
                response.addCookie(c);
                //4.2转发到登录页
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else{
                //4.3登录成功
//                System.out.println("账户信息正确");
                //4.3.1将用户信息保存到session
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                //4.4重定向到successServlet
                response.sendRedirect(request.getContextPath()+"/successServlet");
            }
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
