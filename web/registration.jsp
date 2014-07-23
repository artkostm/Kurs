<%-- 
    Document   : registration
    Created on : 04.06.2014, 13:07:15
    Author     : Development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <title>Registration</title>
        <script src="js/xmlhttp.js">
        </script>
    </head>
    <body>
        <div id="login">
        <form action="some.do" method="post">
            <input type="hidden" name="command" value="registration"/>
            <fieldset class="clearfix">
		<p><span class="fontawesome-user"></span>
                    <input type="text" id="email" name="email" value="Email" 
                           onBlur="if(this.value === '') this.value = 'Email'" 
                           onFocus="if(this.value === 'Email') this.value = ''" 
                           onchange="validateEmail(this.value)" required></p> 
                <p><span class="fontawesome-lock"></span>
                    <input type="password"  value="Пароль" name="password" onBlur="if(this.value === '') 
                        this.value = 'Пароль'" onFocus="if(this.value === 'Пароль') 
                        this.value = ''" onchange="validatePassword()" required></p> 
                <p><span class="fontawesome-lock"></span>
                    <input type="password"  value="Пароль" name="password1" onBlur="if(this.value === '') 
                        this.value = 'Пароль'" onFocus="if(this.value === 'Пароль') 
                        this.value = ''" onchange="validatePassword()" required></p> 
                <p><input type="submit" value="Зарегистрироваться"></p>
            </fieldset>
        </form>
    </div>
        <div class="error-context-hid" id="error" >
        </div>
    </body>
</html>
