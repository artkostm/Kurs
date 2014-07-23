<%-- 
    Document   : new_note
    Created on : 06.06.2014, 14:52:59
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
        <title>New note</title>
        <script>
            setInterval(checkNewMessage, 2000);
        </script>
    </head>
    <body>
        <div id="user-email" >
                ${email}
        </div>
        <div id="wrap">
            <input type="hidden" id="noteId" value="${newNote.idNote}" />
            <div class="note" align="center">
                ${newNote.value}
            </div>
            <div id="mes" class="messages" align="center"></div>

            <div align="center" id="form">
                    <textarea id="test" name="test" required  autocomplete="off" maxlength="500" cols="70" rows="10" wrap="hard"></textarea>
                    <br/>
                    <button class="send-button" onclick="goNew();">Отправить</button>
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
