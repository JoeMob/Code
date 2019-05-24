package controller;

import dao.Userdao;
import domain.User;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        if (user.getUsername() == null || "".equals(user.getUsername().trim()) || user.getPassword() == null || "".equals(user.getPassword().trim())) {
            System.out.println("NULL is unavailable!");
            response.sendRedirect("Index.jsp");
            return;
        }
        Userdao userDao = new Userdao();
        userDao.createConnection();
        String i = userDao.Verify(user.getUsername(),user.getPassword());
        if (i.equals("User")) {
            out.println("Welcome!");
            response.sendRedirect("User.jsp");
        } else if (i.equals("Admin")) {
            out.println("Long may the sun shine.");
            response.sendRedirect("Admin.jsp");
        } else if(i.equals("")) {
            if(userDao.Test()){
                out.println("Connection Success!<br>");
            }else{
                out.println("Connection Failed.<br>");
            }
            out.println("[Data Deleted] or [Never Registered]<br>");
            out.println("Username:" + user.getUsername() + "<br>Password:" + user.getPassword());
            out.println("<br>" + i);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
