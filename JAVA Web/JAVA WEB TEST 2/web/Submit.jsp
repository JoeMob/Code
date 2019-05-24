<%--
  Created by IntelliJ IDEA.
  User: A2zWyx
  Date: 2018-06-13
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="bean.Userbean" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Submit</title>
</head>

<body>
<%
    request.setCharacterEncoding("UTF-8");

    String username = request.getParameter("username");//客户端信息
    String password = request.getParameter("password");
    session.setAttribute("username",username);
    if (username == null || "".equals(username.trim()) || password == null || "".equals(password.trim())) {
        System.out.println("NULL is not available!");
        response.sendRedirect("Index.jsp");
        return;
    }
    Userbean userBean = new Userbean();
    userBean.createConn();
    boolean isValid = userBean.valid(username, password);
    if (isValid) {
        System.out.println("Success!");
        response.sendRedirect("Success.jsp");
    } else {
        System.out.println("Fail!");
        response.sendRedirect("Fail.jsp");
    }
%>
</body>
</body>
</html>
