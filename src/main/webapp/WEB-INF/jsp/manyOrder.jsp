<%-- 
    Document   : manyOrder
    Created on : 2018-1-18, 21:40:37
    Author     : SayMing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="manyOrder/addOne.do" method="post" >
            ====================订单信息 START======================<br/>
            商品链接：<input type="text" name="productUrl"><br/>
            备注：<input type="text" name="remark"><br/>
            ====================订单信息 END  ======================<br/>
            ====================订单明细 START======================<br/>
            关键词1：<input type="text" name="manyOrderDetails[0].keywords"><br/>
            点击量1：<input type="text" name="manyOrderDetails[0].limitClickQuantity"><br/>
            关键词2：<input type="text" name="manyOrderDetails[1].keywords"><br/>
            点击量2：<input type="text" name="manyOrderDetails[1].limitClickQuantity"><br/>
            关键词3：<input type="text" name="manyOrderDetails[2].keywords"><br/>
            点击量3：<input type="text" name="manyOrderDetails[2].limitClickQuantity"><br/>
            ====================订单明细 END  ======================<br/>
            <input type="submit">
        </form>
    </body>
</html>
