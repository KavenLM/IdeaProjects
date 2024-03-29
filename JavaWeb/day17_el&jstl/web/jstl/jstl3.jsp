<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ForEach标签</title>
</head>
<body>
    <c:forEach begin="1" end="10" var="i" step="2" varStatus="s">

        ${i}  ${s.index}  ${s.count}  <br/>
    </c:forEach>

    <hr>

    <%
        List list = new ArrayList();
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        request.setAttribute("list",list);
    %>

    <c:forEach items="${list}" var="str" varStatus="s">
       ${s.index}  ${s.count}  ${str} <br/>
    </c:forEach>
</body>
</html>
