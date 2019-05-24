<%@ page import="controller.Register" %><%--
  Created by IntelliJ IDEA.
  User: A2zWyx
  Date: 2018-06-15
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="Register/do" method="post">
Username:<input type="text" name="username">
<br>
Password:<input type="password" name="password">
<br>
<input type ='Submit' Value="register">
</body>
</html>
