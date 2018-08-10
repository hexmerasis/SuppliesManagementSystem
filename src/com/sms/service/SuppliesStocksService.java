package com.sms.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.sms.entity.SuppliesStock;

public interface SuppliesStocksService {
	void getSuppliesStocks(HttpServletRequest request) throws SQLException;
	void insertSuppliesStocks(HttpServletRequest request) throws SQLException;
	void updateSuppliesStocks(HttpServletRequest request) throws SQLException;
	void getSuppliesItemList(HttpServletRequest request) throws SQLException;
	void searchSuppliesStocks(HttpServletRequest request) throws SQLException;
	void getSuppliesStockItem(HttpServletRequest request) throws SQLException;
}
