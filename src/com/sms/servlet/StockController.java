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

import com.sms.entity.SuppliesStock;
import com.sms.service.SuppliesStocksService;

public class StockController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3254083445269926470L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String page = "pages/supplies_stocks.jsp";
		try{
			ApplicationContext applicationContext = 
					new ClassPathXmlApplicationContext("/com/sms/resource/applicationContext.xml");
			
			SuppliesStocksService suppliesStocksService = 
					(SuppliesStocksService) applicationContext.getBean("suppliesStocksService");
			
			suppliesStocksService.getSuppliesItemList(request);
			suppliesStocksService.getSuppliesStocks(request);
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("/com/sms/resource/applicationContext.xml");
		
		SuppliesStocksService suppliesStocksService = 
				(SuppliesStocksService) applicationContext.getBean("suppliesStocksService");
		
		String page = "pages/supplies_stocks.jsp";
		String action = request.getParameter("action") == null ? "" : request.getParameter("action");

		try{
			if("insertRecord".equals(action)){
				page = "pages/supplies_stocks_add.jsp";
				suppliesStocksService.insertSuppliesStocks(request);
			}else if("updateRecord".equals(action)){
				suppliesStocksService.updateSuppliesStocks(request);
			}else if("searchRecord".equals(action)) {
				suppliesStocksService.getSuppliesItemList(request);
				suppliesStocksService.searchSuppliesStocks(request);
			}else if("displayNextPage".equals(action)) {
				page = "pages/supplies_stocks_add.jsp";
				suppliesStocksService.getSuppliesItemList(request);
				suppliesStocksService.getSuppliesStocks(request);
				suppliesStocksService.getSuppliesStockItem(request);
			}
		} catch(SQLException e){
			System.out.println(e.getMessage());
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
}
