<%--
  Created by IntelliJ IDEA.
  User: A2zWyx
  Date: 2018-06-13
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<%
    String Name = (String)session.getAttribute("username"); //session：会话；在多个页面见共享信息
%>
<p>登陆成功<br>
<p>你好，<%=Name %>同学！</p>
<a href='http://localhost:8080/Index.jsp'> 返回登陆页面</a>
</body>
</html>
