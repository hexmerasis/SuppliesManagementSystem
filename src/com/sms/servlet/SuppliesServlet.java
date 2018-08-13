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

import com.sms.service.SuppliesService;

public class SuppliesServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4694165344043216522L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException{
		String page = "";
		try{
			//Test username
			HttpSession session = request.getSession();
			session.setAttribute("username", "test");
			
			@SuppressWarnings("resource")
			ApplicationContext applicationContext = 
					new ClassPathXmlApplicationContext("/com/sms/resource/applicationContext.xml");
			
			
			SuppliesService suppliesService = 
					(SuppliesService) applicationContext.getBean("suppliesService");
			suppliesService.getSupplies(request);
			page = "/pages/suppliesmaintenance.jsp";
		} 
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
		finally{
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
	
	@SuppressWarnings("resource")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("/com/sms/resource/applicationContext.xml");
		
		SuppliesService suppliesService = (SuppliesService) applicationContext.getBean("suppliesService");
		String action = request.getParameter("action") == null ? "" : request.getParameter("action");
		String page = "";
		try{
			if(action.equals("insertSupplies")){
				suppliesService.insertSupplies(request);
			}
			if(action.equals("updateSupplies")){
				suppliesService.updateSupplies(request);
			}
			if(action.equals("searchSupplies")){
				suppliesService.searchSupplies(request);
			}
			page = "/pages/suppliesmaintenance.jsp";
		}
		catch(SQLException e){
			
		}
		finally{
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
}
