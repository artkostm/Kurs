<%-- 
    Document   : nonote
    Created on : 06.06.2014, 14:53:17
    Author     : Development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <link rel="stylesheet" href="css/style_index.css" media="screen" type="text/css" />
        <script src="js/xmlhttp.js"></script>
        <title>No note</title>
        <script>
            setInterval(checkNewMessage, 5000);
        </script>
    </head>
    <body>
        <div id="user-email" >
                ${email}
        </div>
        <div id="wrap">
            <div id="imp">
                Ваша записка с меткой <i>#</i><b>${tag}</b> отправилась на поиски подходящего получателя.
            </div>
            <a href="/main/help.jsp">Подробнее о метках...</a><br/><br/>
            <a class="index_button" href="/main/some.do?command=index">Запустить новую записку</a>
        </div>
        <div id="footer">
            <div id="hid"></div>
            <div id="footer-nav">
                <a class="snav" href="/find">ищу тебя</a>
                <a class="snav" href="/wall">стена</a>
                <a class="snav" href="http://vk.com/flymer" target="_blank" title="Вконтакте">вк</a>
                <a class="snav" href="/blog">блог</a>
                <a class="snav" href="/tos">правила</a>
                <a class="snav" href="/about">о flymer</a>
                <a class="snav" href="/contact">связь</a>
                <a class="snav" href="/main/help.jsp">помощь</a>
            </div>
        </div>
    </body>
</html>
