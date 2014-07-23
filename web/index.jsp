<%-- 
    Document   : index
    Created on : 03.06.2014, 17:21:30
    Author     : Development
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <link rel="stylesheet" href="css/style_index.css" media="screen" type="text/css" />
        <link rel="stylesheet" href="css/hex.css" media="screen" type="text/css" />
        <script src="js/xmlhttp.js"></script>
        <script>
            function getText(mValue){
                document.forma.test.value = document.forma.test.value + ' ' + mValue;
            }
            setInterval(checkNewMessage, 5000);
        </script>
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <div id="user-email" >
                ${email}
            </div>
            <div>
                
            </div>
        </div>
        <div class="hexagon">
            <div class="hexTop"></div>
            <div class="hexBottom"></div>
        </div>
        <div id="wrap">
            <div align="center" id="form">
                <form action="some.do" name="forma">
                    <input type="hidden" name="command" value="send_note"/>
                    <textarea id="test" name="test" required  autocomplete="off" maxlength="400" cols="70" rows="10" wrap="hard"></textarea>
                    <br/>
                    <input type="submit" class="send-button" value="Отправить">
                </form>
            </div> 
            <div align="center" id="tags">
                <span>Популярные метки</span><br/>
                <c:forEach var="tag" items="${tags}">
                    <a onclick="getText(this.innerHTML);return false;" class="trnd" href="#">#${tag.value}</a>
                </c:forEach>
            </div>
        </div>
        <div id="footer">
            <div id="hid"></div>
            <!--<div id="footer-nav">
                <a class="snav" href="/find">ищу тебя</a>
                <a class="snav" href="/wall">стена</a>
                <a class="snav" href="http://vk.com/flymer" target="_blank" title="Вконтакте">вк</a>
                <a class="snav" href="/blog">блог</a>
                <a class="snav" href="/tos">правила</a>
                <a class="snav" href="/about">о flymer</a>
                <a class="snav" href="/contact">связь</a>
                <a class="snav" href="/help">помощь</a>
            </div> -->
        </div>
    </body>
</html>
