package cn.itcast.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
       //1.获取cookie
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        if(cookies!=null && cookies.length!=0){
            for (Cookie cookie : cookies) {
                //2.获取cookie的name
                String name = cookie.getName();

                //3.判断name是否等于lastTime
                if("lastTime".equals(name)){
                    //等于lastTime则表示不是第一次登录
                    flag = true;

                    //4.响应信息，“欢迎回来，你上次登录时间为 value（ yy-MM-dd hh-mm-ss”）
                    String value = cookie.getValue();
                    System.out.println("解析前value："+value);
                    //4.1解析cookie的值
                    String strValue = URLDecoder.decode(value,"utf-8");
                    System.out.println("解析后value："+strValue);

                    response.getWriter().write("欢迎回来，你上次登录时间为:"+strValue);

                    //6.将本次登录时间更新到cookie中
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String strDate = sdf.format(date);
                    System.out.println("编码前："+strDate);
                    //6.1url编码
                    strDate = URLEncoder.encode(strDate,"utf-8");
                    System.out.println("编码后："+strDate);
                    cookie.setValue(strDate);
                    //7.设置cooike的存活时间
                    cookie.setMaxAge(60*60*24*30);
                    response.addCookie(cookie);
                    break;
                }

            }
        }
        if(cookies==null || cookies.length==0 || !flag){

            //表示第一次登录
            response.getWriter().write("欢迎你，首次登录");

            //将本次登录时间更新到cookie中
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(date);
            System.out.println("编码前："+strDate);
            //url编码
            strDate = URLEncoder.encode(strDate, "utf-8");
            System.out.println("编码后："+strDate);

            Cookie cookie = new Cookie("lastTime",strDate);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
