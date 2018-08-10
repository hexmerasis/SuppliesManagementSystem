package com.sms.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public interface StockSuppliesService {

	void getSupplies(HttpServletRequest request) throws SQLException;

	boolean insertSupplies(HttpServletRequest request) throws SQLException;

	void updateSupplies(HttpServletRequest request) throws SQLException;
	
	void searchFor(HttpServletRequest request) throws SQLException;
}
