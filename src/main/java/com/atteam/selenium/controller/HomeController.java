package com.atteam.selenium.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.atteam.selenium.model.User;
import com.atteam.selenium.model.UserDAO;

@WebServlet(urlPatterns={"/index", "/login", "/logout"})
public class HomeController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	String id;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(); // Khai báo vì logout cần nhận session
		// nhân url để xử lý. getServletPath() : dùng để rẻ nhánh khi nào login, logout...
		String path = req.getServletPath(); // localhost:8080/selenium/login => path "/login"
		switch(path) {
			case "/index":
				doGetIndex(req, resp);
				break;
			case "/login":
				doGetLogin(req, resp);
				break;
			case "/logout":
				doGetLogout(session, req, resp);
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// khi user đăng nhập thành công. Thì phải lưu thông tin user vào 1 nơi nào đó. => Session.
		// Nếu không đăng nhập thành công. refresh trang củng sẽ biến mất
		HttpSession session = req.getSession();
		// nhân url để xử lý. getServletPath() : dùng để rẻ nhánh khi nào login, logout...
		String path = req.getServletPath();
		switch(path) {
			case "/login":
				doPostLogin(session, req, resp); // doPost sẽ nhận thêm 1 biến session
				break;
		}
	}
	
	private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
	}
	
	private void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/sign-in.jsp").forward(req, resp);
	}
	
	private void doGetLogout(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session.removeAttribute("currentUser"); // xóa khỏi session
		resp.sendRedirect("index"); // chuyển về lại trang index
	}
	
	// xử lý đăng nhập
	private void doPostLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserDAO dao = new UserDAO();
		User user = dao.findById(username);
		
		if(user != null) {
			if(user.getPassword().equals(password)) {
				req.setAttribute("message", "Login successfully!");
				session.setAttribute("currentUser", user);
				resp.sendRedirect("index"); // đăng nhập xong chuyển về lại trang index
			}else {
				req.setAttribute("message", "Login failed!");
				resp.sendRedirect("login");
			}
		}else {
			// ngược lại đăng nhập thất bại. tiếp tục ở trạng thái trang đăng nhập để tiến hành đăng nhập lại.
			resp.sendRedirect("login");
		}
	}

}
