package com.sms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sms.service.UserService;

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String page = "";
		try{
			ApplicationContext applicationContext = 
					new ClassPathXmlApplicationContext("/com/sms/resource/applicationContext.xml");
			HttpSession session = req.getSession();
			UserService userService = 
					(UserService) applicationContext.getBean("userService");
			
			userService.getUser(req);
			if (session.getAttribute("currentAccessLevel").equals("A")) {
				page = "pages/AdminUserMaintenance.jsp";
			} else {
				page = "pages/UserUserMaintenance.jsp";
			}
			
		
		} catch (Exception e){
			System.out.println(e.getMessage());
		} finally {
			RequestDispatcher rd = req.getRequestDispatcher(page);
			rd.forward(req, res);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("/com/sms/resource/applicationContext.xml");
		
		UserService userService = 
				(UserService) applicationContext.getBean("userService");
		
		String page = "";
		String action = req.getParameter("action") == null ? "" : req.getParameter("action");
		
		try{
			if("insertRecord".equals(action)){
				userService.insertUser(req);
			}else if("updateRecord".equals(action)){
				userService.updateUser(req);
			}else if("updatePasswrd".equals(action)){
				userService.updatePassword(req);
			}else if("searchUser".equals(action)){
				userService.searchUser(req);
			}else if("updateThisUser".equals(action)){
				userService.updateThisUser(req);
			}
			page = "pages/AdminUserMaintenance.jsp";
		} catch(SQLException e){
			System.out.println(e.getMessage());
		} finally{
			RequestDispatcher rd = req.getRequestDispatcher(page);
			rd.forward(req, res);
		}
	}
	
}
