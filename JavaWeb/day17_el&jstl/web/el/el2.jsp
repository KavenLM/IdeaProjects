<%@ page import="cn.itcast.domain.User" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el2</title>
</head>
<body>
    <%
        String name = "zhangsan";
        request.setAttribute("name",name);

        User user = new User();
        user.setName("李四");
        user.setAge(22);
        user.setGender("男");
        user.setBirthday(new Date());
        request.setAttribute("user",user);

        List list = new ArrayList();
        list.add("王五");
        list.add("赵六");
        list.add(user);
        request.setAttribute("list",list);

        Map map = new HashMap();
        map.put("sname","田七");
        map.put("sage",33);
        map.put("suser",user);
        request.setAttribute("map",map);
    %>
    <h3>el表达式获取对象的值</h3>
    ${requestScope.name}<br/>
    ${name}<br/>
    ${user.name}<br/>
    ${user.age}<br/>
    ${user.gender}<br/>
    ${user.brtday}<br/>

    <h3>el表达式获取集合数据</h3>
    ${list[0]}<br/>
    ${list[1]}<br/>
    ${list[2].name}<br/>

    <h3>el表达式获取map数据</h3>
    ${map.sname}<br/>
    ${map["sage"]}<br/>
    ${map.suser.name}<br/>

    <h3>empty判断字符串，集合，数组是否为空或长度是否为零</h3>
    ${empty name}
    ${!empty name}
    ${not empty name}
</body>
</html>
