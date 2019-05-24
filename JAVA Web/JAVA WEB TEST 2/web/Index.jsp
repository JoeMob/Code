<%--
  Created by IntelliJ IDEA.
  User: A2zWyx
  Date: 2018-05-21
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
            <title>Login Page</title>
        </head>
        <%session.invalidate(); %>

            <body background="1158774818e9c9685bo.jpg">
                <center>
                    <font style="color: #FFFFFF;-webkit-text-stroke: 1px #000000; font-size: 64px">Login</font>
                    <form id="password" name="username" action="Submit.jsp" method="post">
                        <table border="0">
                            <tr>
                                <td>
                                    <font style="color: #ffffff; -webkit-text-stroke: 1px #000000; font-size: 32px">username:</font>
                                    <input type="text" name="username">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font style="color: #ffffff; -webkit-text-stroke: 1px #000000; font-size: 32px">password:</font>
                                    <input type="password" name="password">
                                </td>
                            </tr>
                        </table>
                        <input type="submit" value="Login" style="color: #000000">
                    </form>
                </center>
            </body>

        </html>