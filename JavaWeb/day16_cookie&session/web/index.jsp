<%@ page import="com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput" %><%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2020/11/25
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

    <%
      System.out.println("Hi,Jsp");

      String contextPath = request.getContextPath();
      ServletContext servletContext = request.getServletContext();
      out.print(servletContext);
      out.print(contextPath);

    %>

    <%!
      int i = 3;
    %>

    <%= i %>

    System.out.println("HI,jsp");
    <h1>hi~jsp</h1>
    <%
      response.getWriter().write("ddddd....");

    %>
  </body>
</html>
