
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el1</title>
</head>
<body>
    <!-- el表达式 -->
    ${5>3}
    \${5>3}
    <h2>算数运算符</h2>
${3 / 4}<br/>
${3 div 4}<br/>
${3 * 4}<br/>
${3 % 4}<br/>

    <h2>比较运算符</h2>
${3 > 4}<br/>
${3 == 4}<br/>
${3 != 4}<br/>

    <h2>逻辑运算符</h2>
${3 > 4 && 3 < 4}<br/>


</body>
</html>
