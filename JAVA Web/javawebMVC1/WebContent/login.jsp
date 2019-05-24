<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	/* 样式不合格 */
	.noOk{
		color:red;
		font-size:14px;
	}
	/* 样式合格 */
	.ok{
		color:green;
		font-size:14px;
	}
</style>
<!-- 引入外部的JS文件 -->
<script type="text/javascript" language="javascript"  src="js/userSnoPwdValid.js" charset="UTF-8"></script>

</head>


<body>
	<form action="login" method="post">
		请输入学号：<input type="text" name="userSno" id="userSno" onblur="checkUserSno()">&nbsp;<span id="useSnoHit"></span><br>
	       请输入密码：<input type="password" name="password" id="password" onblur="checkPassword()">&nbsp;<span id="passwordHit"></span> <br>
		<input type="submit" value="提交"  id="submit"  />
	</form>
</body>
</html>