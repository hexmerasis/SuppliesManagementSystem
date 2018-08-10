package com.sms.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;


public interface SuppliesService {
	public void getSupplies(HttpServletRequest request) throws SQLException;
	public void insertSupplies(HttpServletRequest request) throws SQLException;
	public void updateSupplies(HttpServletRequest request) throws SQLException;
	public void searchSupplies(HttpServletRequest request) throws SQLException;
}
