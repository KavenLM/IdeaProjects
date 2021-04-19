<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2020/11/30
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>itcast</title>
</head>
<body>
    <%
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
    %>


                 <h1>欢迎回来，你上次登录时间为:<%= strValue %></h1>
                <input type="text">

    <%
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
    %>
               <h1>欢迎你，首次登录</h1>
    <%
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
    %>
</body>
</html>
