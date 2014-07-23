<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Log in</title>
	<link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <link rel="stylesheet" href="css/login.css" media="screen" type="text/css" />
        <script src="js/xmlhttp.js">
        </script>
</head>
<body>
    <div id="login">
        <form action="some.do" method="post">
            <input type="hidden" name="command" value="login"/>
            <fieldset class="clearfix">
                <p><span class="fontawesome-user"></span>
                    <input type="text" id="email" name="email" value="Email" 
                           onBlur="if(this.value === '') this.value = 'Email'" 
                           onFocus="if(this.value === 'Email') this.value = ''" 
                           onchange="validateEmail(this.value)" required></p> 
                <p><span class="fontawesome-lock"></span>
                    <input type="password" id="password" name="password" value="Пароль" 
                           onBlur="if(this.value === '') this.value = 'Пароль'" 
                           onFocus="if(this.value === 'Пароль'){ this.value = '';}" 
                           onchange="validatePassword()" required></p> 
                <p><input type="submit" value="Войти" ></p>
            </fieldset>
        </form>
        <p>Нет аккаунта? &nbsp;&nbsp;<a href="/main/registration.jsp">Регистрация</a><span class="fontawesome-arrow-right"></span></p>
    </div>
    <div class="error-context-hid" id="error" >
            
    </div>
    <div class="arrow_box">
        <h1 class="logo">Почта анонимных записок</h1>
        Напишите записку и запустите её в Интернет. Кто-то, далеко или близко, поймает вашу записку и откликнется. Ловите записки других и общайтесь с теми, кто вам интересен. Ведь это... 
    </div>
</body>
</html>