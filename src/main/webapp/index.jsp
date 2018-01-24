<%-- 
    Document   : index
    Created on : 2018-1-22, 18:09:21
    Author     : SayMing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    out.println("now user-agent:" + request.getHeader("user-agent"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
