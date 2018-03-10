<%-- 
    Document   : AnalyzeStore
    Created on : 2018-3-10, 18:04:46
    Author     : pdmilk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>店铺分析</h1>
        <form action="analyzeStore/add.do" method="post" >
            店铺链接：<input type="text" name="shopUrl"><br/>
            <input type="submit">
        </form>
        <form action="analyzeStore/receiveResult.do" method="post" >
            <input type="submit" value="接收结果">
        </form>
    </body>
</html>
