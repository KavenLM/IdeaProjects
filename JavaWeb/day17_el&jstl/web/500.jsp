<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<body>
    <h1>服务器正在维护~~</h1>
    <%
        String message = exception.getMessage();
        out.print(message);
    %>
</body>
</html>
