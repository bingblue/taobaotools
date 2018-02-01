<%-- 
    Document   : stuck
    Created on : 2018-1-10, 17:20:26
    Author     : SayMing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>卡首屏</title>
    </head>
    <body>
        <h1>此页面是卡首屏功能主页。</h1>
        ↓↓↓↓↓↓↓↓↓↓↓↓↓卡首屏↓↓↓↓↓↓↓↓↓↓↓↓↓
        <form action="stuck/mobile.do" method="post" >
            淘宝宝贝Id：<input type="text" name="taobaoProductId"><br/>
            关键词：<input type="text" name="keywords"><br/>
            淘口令分享标题：<input type="text" name="productTitle"><br/>
            <input type="submit">
        </form>
        <br/><br/>
        ↓↓↓↓↓↓↓↓↓↓↓↓↓卡同行↓↓↓↓↓↓↓↓↓↓↓↓↓
        <form action="stuck/competitor.do" method="post" >
            同行淘宝宝贝Id1：<input type="text" name="competitorProductId1"><br/>
            同行淘宝宝贝Id2：<input type="text" name="competitorProductId2"><br/>
            同行淘宝宝贝Id3：<input type="text" name="competitorProductId3"><br/>
            淘宝宝贝Id：<input type="text" name="taobaoProductId"><br/>
            关键词：<input type="text" name="keywords"><br/>
            淘口令分享标题：<input type="text" name="productTitle"><br/>
            <input type="submit">
        </form>
    </body>
</html>
