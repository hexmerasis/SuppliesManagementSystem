package com.sms.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sms.dao.SuppliesStocksDAO;
import com.sms.entity.SuppliesStock;
import com.sms.service.SuppliesStocksService;

public class SuppliesStocksServiceImpl implements SuppliesStocksService {
	
	private SuppliesStocksDAO suppliesStocksDAO;
	
	public SuppliesStocksDAO getSuppliesStocksDAO() {
		return suppliesStocksDAO;
	}
	
	public void setSuppliesStocksDAO(SuppliesStocksDAO suppliesStocksDAO) {
		this.suppliesStocksDAO = suppliesStocksDAO;
	}
	
	@Override
	public void getSuppliesStocks(HttpServletRequest request) throws SQLException {
		request.setAttribute("stocksList", suppliesStocksDAO.getSuppliesStocks());
	}
	
	@Override
	public void getSuppliesItemList(HttpServletRequest request) throws SQLException {
		request.setAttribute("suppliesItemList", suppliesStocksDAO.getSuppliesItemList());
	}
	
	@Override
	public void insertSuppliesStocks(HttpServletRequest request) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		
		params.put("supplyId", request.getParameter("supplyId"));
		params.put("dateAdded", formatDate(request.getParameter("dateAdded")));
		params.put("purchaseDate", formatDate(request.getParameter("purchaseDate")));
		params.put("referenceNo", request.getParameter("referenceNo"));
		params.put("quantity", request.getParameter("quantity"));
		params.put("lastUser", "user");
		
		this.suppliesStocksDAO.insertSuppliesStocks(params);
		this.suppliesStocksDAO.updateSupplies(params);
	}
	
	@Override
	public void updateSuppliesStocks(HttpServletRequest request) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		
		params.put("stockId", request.getParameter("stockId"));
		params.put("supplyId", request.getParameter("supplyId"));
		params.put("purchaseDate", formatDate(request.getParameter("purchaseDate")));
		params.put("referenceNo", request.getParameter("referenceNo"));
		params.put("lastUser", "user");
	
		this.suppliesStocksDAO.updateSuppliesStocks(params);
	}
	
	@Override
	public void searchSuppliesStocks(HttpServletRequest request) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		
		params.put("stockId", request.getParameter("searchTxt"));
		params.put("itemName", request.getParameter("searchTxt"));
		
		request.setAttribute("stocksList", this.suppliesStocksDAO.searchSuppliesStocks(params));
	}
	
	public String formatDate(String dateStr) {
		String dt = "";
	    try {
			Date date = new SimpleDateFormat("MM/dd/yyyy").parse(dateStr);
			dt = new SimpleDateFormat("dd-MMM-yyyy").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
	    
	    return dt;
	}
	
	@Override
	public void getSuppliesStockItem(HttpServletRequest request) {
		request.setAttribute("stockId", request.getParameter("stockId"));
		request.setAttribute("selectedSupplyId", request.getParameter("supplyId"));
		request.setAttribute("quantity", 
				request.getParameter("quantity")==null? "" : Integer.parseInt(request.getParameter("quantity")));
		request.setAttribute("referenceNo", request.getParameter("referenceNo"));
		request.setAttribute("dateAdded", request.getParameter("dateAdded"));
		request.setAttribute("purchaseDate", request.getParameter("purchaseDate"));
	}
}
