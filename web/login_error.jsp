<%-- 
    Document   : login_error
    Created on : 25.05.2014, 19:10:33
    Author     : Development
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body{
                background-color: #2C3338;
            }
            h2{
                color: #9999ff;
                text-align: center;
                text-transform: uppercase;
            }
        </style>
        <title>Login error</title>
    </head>
    <body>
        <br/>
        <div align="center">
            <h2>Такого пользователя не существует.</h2>
            <a href="/main/login.jsp">Повторить вход</a>
        </div>
    </body>
</html>
