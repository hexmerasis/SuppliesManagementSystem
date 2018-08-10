package com.sms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sms.service.StockSuppliesService;

public class StockSuppliesServlet extends HttpServlet {

	private static final long serialVersionUID = 2944485028734390991L;

	@SuppressWarnings("resource")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "";
		String action = request.getParameter("action");
		action = action == null ? "" : request.getParameter("action");
		
		try {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					"/com/sms/resource/applicationContext.xml");
			StockSuppliesService stocksSupplies = (StockSuppliesService) applicationContext
					.getBean("StockSuppliesService");
			if ("pageOut".equals(action)) {
				page = "/pages/editIssue.jsp";
			} else {
				stocksSupplies.getSupplies(request);
				page = "/pages/stockSupplies.jsp";
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/com/sms/resource/applicationContext.xml");
		StockSuppliesService stocksSupplies = (StockSuppliesService) applicationContext.getBean("StockSuppliesService");
		String page = "/pages/stockSupplies.jsp";
		String action = request.getParameter("action") == null ? "" : request.getParameter("action");
		boolean bool = true;
		try {
			if ("insertRecord".equals(action)) {
				bool = stocksSupplies.insertSupplies(request);
				stocksSupplies.getSupplies(request);
			} else if ("updateRecord".equals(action)) {
				stocksSupplies.updateSupplies(request);
			}else if("searchRecord".equals(action)){
				stocksSupplies.searchFor(request);
			}
		} catch (SQLException e) {

		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

}
