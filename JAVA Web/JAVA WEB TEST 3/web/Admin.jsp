<%--
  Created by IntelliJ IDEA.
  User: A2zWyx
  Date: 2018-06-14
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<form action="Delete" method="post">
    Username:<input type="text" name = "usernameDel">
    <input type="submit" value="Delete" style="color: #000000">
</form>
<!--<form action="Seek" method="post">
    Username:<input type="text" name = "usernameSeek">
    <input type="submit" value="Seek" style="color: #000000">
</form>-->
<form action="SendSentences" method="post">
    Sentences:<input type="text" name="sentences">
    Username:<input type="text" name="usernameSen">
    <input type="submit" value="Insert" style="color: #000000">
</form>
<input align="center" type="button" value="logout" onclick="location.href='index.jsp'">
</body>
</html>