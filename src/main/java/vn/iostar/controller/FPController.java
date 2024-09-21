package vn.iostar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.dao.UserServiceImpl;
import vn.iostar.service.IUserService;

import java.io.IOException;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/forgotpassword")
public class FPController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FPController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("forgotpassword.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		String newpassword = req.getParameter("pword");
		IUserService service = new UserServiceImpl();
		
		if(service.checkExistEmail(email) != false){
			if(service.updatePassword(email, newpassword)) {
				req.setAttribute("msg", "Mật khẩu đã được đổi thành công");
			}
			else {
				req.setAttribute("msg", "Đã có lỗi xảy ra");
			}
		}
		else {
			req.setAttribute("msg", "Email không tồn tại");
			
		}
		req.getRequestDispatcher("forgotpassword.jsp").forward(req, resp);
	}

}
