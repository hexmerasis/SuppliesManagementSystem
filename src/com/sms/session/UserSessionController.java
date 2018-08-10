package com.sms.session;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sms.entity.User;
import com.sms.service.UserService;

public class UserSessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, IOException {
		String n = req.getParameter("userId");
		
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("/com/sms/resource/applicationContext.xml");
		
		UserService userService = 
				(UserService) applicationContext.getBean("userService");
		try{
			HttpSession session = req.getSession();
			userService.getUser(req);
			session.setAttribute("changePassUserId", n);
		} catch(Exception e){
			
		} finally {
			RequestDispatcher dispatcher = req.getRequestDispatcher("pages/ChangePassword.jsp");
			dispatcher.forward(req, res);
		}
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, IOException {
		String pge = "";
		try {
			pge = "index.jsp";
		
			
		} catch (Exception e) {

		} finally {
			RequestDispatcher rd = req.getRequestDispatcher(pge);
			rd.forward(req, res);
		}

	}
}
