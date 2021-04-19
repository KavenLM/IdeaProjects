<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>jstl1</title>
</head>
<body>
    <c:if test="true">
        <h3>我是真的</h3>
    </c:if>
    <%
        List list = new ArrayList();
        list.add("aaaaa");
        request.setAttribute("list",list);

        request.setAttribute("number",4);
    %>

    <c:if test="${not empty list}">
        遍历集合。。。
    </c:if>

    <br/>

    <c:if test="${number % 2 == 0}">
        我是偶数
    </c:if>
</body>
</html>
