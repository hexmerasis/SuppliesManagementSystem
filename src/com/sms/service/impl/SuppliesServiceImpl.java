package com.sms.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sms.dao.SuppliesDAO;
import com.sms.service.SuppliesService;

public class SuppliesServiceImpl implements SuppliesService{
	
	private SuppliesDAO suppliesDAO;
	
	public SuppliesDAO getSuppliesDAO(){
		return suppliesDAO;
	}
	
	public void setSuppliesDAO(SuppliesDAO suppliesDAO){
		this.suppliesDAO = suppliesDAO;
	}
	
	public void getSupplies(HttpServletRequest request) throws SQLException {
		request.setAttribute("suppliesList", suppliesDAO.getSupplies());
		request.setAttribute("supplyTypesList", suppliesDAO.getSupplyTypes());
	}
	
	public void insertSupplies(HttpServletRequest request) throws SQLException {
		Map<String, Object> supplies = new HashMap<>();
		HttpSession session = request.getSession();
		
		supplies.put("typeName", request.getParameter("typeName"));
		supplies.put("itemName", request.getParameter("itemName"));
		supplies.put("itemUnit", request.getParameter("itemUnit"));
		supplies.put("obsoleteTag", request.getParameter("obsoleteTag"));
		supplies.put("location", request.getParameter("location"));
		supplies.put("reorderLevel", Integer.parseInt(request.getParameter("reorderLevel")));
		supplies.put("actualCount", Integer.parseInt(request.getParameter("actualCount")));
		supplies.put("remarks", request.getParameter("remarks"));
		supplies.put("dateAdded", request.getParameter("dateAdded"));
		supplies.put("lastUser", session.getAttribute("username"));
		supplies.put("lastUpdate", request.getParameter("lastUpdate"));
		
		this.suppliesDAO.insertSupplies(supplies);
	}
	
	public void updateSupplies(HttpServletRequest request) throws SQLException {
		Map<String, Object> supplies = new HashMap<>();
		HttpSession session = request.getSession();
		
		supplies.put("supplyID", Integer.parseInt(request.getParameter("supplyID")));
		supplies.put("typeName", request.getParameter("typeName"));
		supplies.put("itemName", request.getParameter("itemName"));
		supplies.put("itemUnit", request.getParameter("itemUnit"));
		supplies.put("obsoleteTag", request.getParameter("obsoleteTag"));
		supplies.put("location", request.getParameter("location"));
		supplies.put("reorderLevel", Integer.parseInt(request.getParameter("reorderLevel")));
		supplies.put("remarks", request.getParameter("remarks"));
		supplies.put("lastUser", session.getAttribute("username"));
		
		this.suppliesDAO.updateSupplies(supplies);
	}
	
	public void searchSupplies(HttpServletRequest request) throws SQLException{
		String itemName = request.getParameter("search") == null ? "" : request.getParameter("search");
		request.setAttribute("suppliesList", suppliesDAO.searchSupplies(itemName));
		request.setAttribute("supplyTypesList", suppliesDAO.getSupplyTypes());
	}
}
