<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.itcast.domain.User" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>test</title>
</head>
<body>
    <%
        List list = new ArrayList();
        list.add(new User("张三",22,"女",new Date()));
        list.add(new User("李四",24,"男",new Date()));
        list.add(new User("王五",28,"男",new Date()));
        list.add(new User("赵六",28,"男",new Date()));
        request.setAttribute("list",list);
    %>

    <table border="1" cellspacing="0" align="center">
        <tr>
            <td>序号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>性别</td>
            <td>出生日期</td>
        </tr>
        <c:forEach items="${list}" var="user" varStatus="s">
            <c:if test="${s.count % 2 == 0}">
                <tr bgcolor="red">
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.gender}</td>
                    <td>${user.brtday}</td>
                </tr>
            </c:if>
            <c:if test="${s.count % 2 != 0}">
                <tr bgcolor="green">
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.gender}</td>
                    <td>${user.brtday}</td>
                </tr>
            </c:if>

        </c:forEach>

    </table>
</body>
</html>
