<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	登录成功！<br/>当前用户:
	<%
		if((String)session.getAttribute("currentUserSno")==null){
			response.sendRedirect("../login.jsp");
		}else{
			out.println((String)session.getAttribute("currentUserSno"));
		}
	%>
	<a href="logout">退出登录</a>
</body>
</html>