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
        
		//��ҳ��õ�����
		String userSno = request.getParameter("userSno");
		//תΪ����
		int password = Integer.parseInt(request.getParameter("password"));
        //����jspҳ���session����
		System.out.println("userSno:"+userSno);
		System.out.println("password:"+password);
	
		
		
//		//��Ӧ����
//		
//		PrintWriter out = response.getWriter();

//		out.flush();
//		out.close();
		
		
		
		//�����ݿ��в鿴�Ƿ��ҵ���
		loginUser = new User();
		loginUser.setUserSno(userSno);
		loginUser.setPassword(password);
		
		userService = new UserService();
		User user= userService.login(loginUser);
		
		if(user!=null) {	
			//����������ת
			System.out.println("��ע��");
			//servlet��ֵ��JSP 
			HttpSession session = request.getSession();     //���÷������˵�session
			session.setAttribute("currentUserSno", userSno);
			request.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(request, response);
		}else{
			//�ͻ�����ת
			System.out.println("δע��");
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
