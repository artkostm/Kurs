<%-- 
    Document   : dialog
    Created on : 08.06.2014, 22:15:08
    Author     : Development
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <link rel="stylesheet" href="css/style_index.css" media="screen" type="text/css" />
        <script src="js/xmlhttp.js"></script>
        <script>
            setInterval(checkNewMessage, 5000);
        </script>
        <title>Dialog</title>
    </head>
    <body onload="javascript:scroll(0,9999);">
        <div id="user-email" >
                ${email}
        </div>
        <div id="wrap">
            <input type="hidden" id="noteId" value="${noteid}" />
            <input type="hidden" id="lastMesId" value="${lastMesID}" />
            <div class="note" align="center">
                ${note.value}
            </div>
            <div id="mes" class="messages" align="center">
                <c:forEach var="item" items="${dialog.messages}">
                    <c:if test="${item.idSender == user.idUser}" >
                        <pre class="my">
                            ${item.value}
                        </pre>
                    </c:if>
                    <c:if test="${item.idSender != user.idUser}" >
                        <pre class="other">
                            ${item.value}
                        </pre>
                    </c:if>
                </c:forEach>
            </div>

            <div align="center" id="form">
                    <textarea id="test" name="test" required  autocomplete="off" maxlength="500" cols="70" rows="10" wrap="hard"></textarea>
                    <br/>
                    <button class="send-button" onclick="go();">Отправить</button> 
                    <button class="fontawesome-trash" id="delete-button" onclick="doDelete();return false;" title="Сжечь этот диалог"></button>
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
            </div>-->
        </div> 
    </body>
</html>
