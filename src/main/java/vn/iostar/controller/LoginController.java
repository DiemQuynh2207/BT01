package vn.iostar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.dao.UserServiceImpl;
import vn.iostar.models.UserModel;
import vn.iostar.service.IUserService;

import java.io.IOException;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;



@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("usermodel") != null) {
			UserModel user = (UserModel) session.getAttribute("usermodel");
			if (user.getRole().equals("MEMBER"))
				resp.sendRedirect(req.getContextPath() + "/user");
			else if (user.getRole().equals("MANAGER"))
				resp.sendRedirect(req.getContextPath() + "/manager");
			return;
		}
		
		req.removeAttribute("error");
		req.setAttribute("type", "Login"); 
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		System.out.println(12);
		String username = req.getParameter("username");
		String password = req.getParameter("pword");
		boolean isRememberMe = false;
		String remember = req.getParameter("remember");
		if ("on".equals(remember)) {
			isRememberMe = true;
		}
		String errorMsg = "";
		if (username == null || password == null) {
			errorMsg = "Tài khoản hoặc mật khẩu không được để trống";
			req.setAttribute("color", "red");
			req.setAttribute("error", errorMsg);
			req.setAttribute("type", "Login"); 
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		IUserService service = new UserServiceImpl();

		System.out.println(1);
		UserModel user = service.login(username, password);

		System.out.println(2);
		if (user != null) {
			
			saveRemeber(req, user);
			System.out.println("User "+username+" login to web, role: "+user.getRole());
			
			if (user.getRole().equals("MEMBER"))
				resp.sendRedirect(req.getContextPath() + "/home");
			else if (user.getRole().equals("MANAGER"))
				resp.sendRedirect(req.getContextPath() + "/home");
		} else {
			System.out.println("login fail for user: "+username);
			errorMsg = "Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("color", "red");
			req.setAttribute("error", errorMsg); 
			req.setAttribute("type", "Login"); 
			System.out.println("khong dang nhap duoc");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			
		}
	}
	private void saveRemeber(HttpServletRequest req, UserModel usermodel) {
		HttpSession session = req.getSession();
		session.setAttribute("usermodel", usermodel);
	}
}
