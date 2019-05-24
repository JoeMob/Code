package controller;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daomain.User;
import service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User loginUser = null;
	UserService userService = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return super.getServletConfig();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setCharacterEncoding("text/html;UTF-8"); 
        request.setCharacterEncoding("UTF-8");
        
		//从页面得到参数
		String userSno = request.getParameter("userSno");
		//转为整型
		int password = Integer.parseInt(request.getParameter("password"));
        //接受jsp页面的session内容
		System.out.println("userSno:"+userSno);
		System.out.println("password:"+password);
	
		
		
//		//响应数据
//		
//		PrintWriter out = response.getWriter();

//		out.flush();
//		out.close();
		
		
		
		//在数据库中查看是否找到了
		loginUser = new User();
		loginUser.setUserSno(userSno);
		loginUser.setPassword(password);
		
		userService = new UserService();
		User user= userService.login(loginUser);
		
		if(user!=null) {	
			//服务器端跳转
			System.out.println("已注册");
			//servlet传值给JSP 
			HttpSession session = request.getSession();     //设置服务器端的session
			session.setAttribute("currentUserSno", userSno);
			request.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(request, response);
		}else{
			//客户端跳转
			System.out.println("未注册");
			//request.getRequestDispatcher("./login.jsp").forward(request, response);
			response.sendRedirect("login.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
