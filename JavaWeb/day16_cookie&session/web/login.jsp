<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2020/12/1
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>

    <script>
        window.onload = function () {
            document.getElementById("img").onclick = function () {

                this.src = "/day16/checkCodeServlet?time="+ new Date().getTime();

            }
        }
    </script>
</head>
<body>
    <form action="/day16/loginServlet" method="post">
        <table>
            <tr>
                <td>用户名:</td>
                <td><input type="text" name="username">
                    <%
                        String header = response.getHeader("Set-Cookie");
                        if(header!=null && header.length()!=0 && header.contains("loginFailed")){
                            out.print("用户名或密码错误！");
                        }
                    %>
                </td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>验证码:</td>
                <td><input type="text" name="checkCode">
                    <%
                        //获取响应头Set-Cookie，如果包含checkFailed则提示验证码输入错误
                        header = response.getHeader("Set-Cookie");
                        if(header!=null && header.length()!=0 && header.contains("checkFailed")){
                            out.print("验证码输入错误！");
                        }
                    %>
<%--                    ${sessionScope.checkMsg}--%>
                </td>
            </tr>
            <tr>

                <td colspan="2"><img id="img" src="/day16/checkCodeServlet"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="登录"> </td>
            </tr>
        </table>
    </form>
</body>
</html>
