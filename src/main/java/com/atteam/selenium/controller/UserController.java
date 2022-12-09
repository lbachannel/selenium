package com.atteam.selenium.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.atteam.selenium.model.User;
import com.atteam.selenium.model.UserDAO;



/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns={"/user","/edit/*","/create","/update","/delete", "/reset"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String id;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		User user = new User();
		String uri = request.getRequestURI();
		System.out.println(uri);
		if(uri.contains("edit")) {
			id = uri.substring(uri.lastIndexOf("/") + 1);
			user = dao.findById(id);
		}else if(uri.contains("/create")) {
			try {
				BeanUtils.populate(user, request.getParameterMap());
				if (user.getFullname().isEmpty()) {
					request.setAttribute("message", "A new user profile is added failed!");
				} else if (user.getEmail().isEmpty()) {
					request.setAttribute("message", "A new user profile is added failed!");
				} else if (user.getId().isEmpty()) {
					request.setAttribute("message", "A new user profile is added failed!");
				} else if (user.getPassword().isEmpty()) {
					request.setAttribute("message", "A new user profile is added failed!");
				} else {
					dao.create(user);
					request.setAttribute("message", "A new user profile is added successfully!");
				}					
			} catch (Exception e) {
				request.setAttribute("message", "A new user profile is added failed!");
			}
		}else if(uri.contains("/update")) {
			try {
				BeanUtils.populate(user, request.getParameterMap());
				if (user.getFullname().isEmpty()) {
					request.setAttribute("message", "A new user profile is added failed!");
				} else if (user.getEmail().isEmpty()) {
					request.setAttribute("message", "A new user profile is added failed!");
				} else if (user.getId().isEmpty()) {
					request.setAttribute("message", "A new user profile is added failed!");
				} else if (user.getPassword().isEmpty()) {
					request.setAttribute("message", "A new user profile is added failed!");
				} else {
					dao.update(user);
					request.setAttribute("message", "Update successfully!");
				}
			} catch (Exception e) {
				request.setAttribute("message", "Update failed!");
			}
		}else if(uri.contains("/delete")) {
			try {
				user = dao.findById(id);
				if (user.getAdmin() == false) {
					id = request.getParameter("id");
					dao.remove(dao.findById(id));
					request.setAttribute("message", "Delete user id " + id + " successfully!");
				}else {
					request.setAttribute("message", "Delete user id " + id + " failed!");
				}
			} catch (Exception e) {
				request.setAttribute("message", "Delete user id " + id + " failed!");
			}
		}
		request.setAttribute("form", user); // show up form layout
		request.setAttribute("items", dao.findAll()); // show up table layout
		request.getRequestDispatcher("/views/user.jsp").forward(request, response);
	}
	

}
