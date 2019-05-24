<%--
  Created by IntelliJ IDEA.
  User: A2zWyx
  Date: 2018-06-14
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body background="210b0893a229993fc65fc80597191e87.jpg" >
<center>
<form action="Login" method="post">
    Username:<input type="text" name="username">
    <br>
    Password:<input type="password" name="password">
    <br>
    <input type="submit" value="Login" style="color: #000000">
    <br>
    <input type="button" value="Register" onclick="location.href='Register.jsp'">
    <br>
    <input type="button" value="test" onclick="location.href='Test'">
</form>
</center>
</body>
</html>