package com.sms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sms.dao.SuppliesStocksDAO;
import com.sms.entity.SuppliesStock;
import com.sms.entity.Supply;

public class SuppliesStocksDAOImpl implements SuppliesStocksDAO{

	private SqlMapClient sqlMapClient;
	
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}
	
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	@SuppressWarnings("unchecked")
	public List<SuppliesStock> getSuppliesStocks() throws SQLException {
		List<SuppliesStock> stocks = new ArrayList<>();
		
		try {
			stocks = this.getSqlMapClient().queryForList("getSuppliesStocks");
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	
		return stocks;
	}
	
	@SuppressWarnings("unchecked")
	public List<Supply> getSuppliesItemList() throws SQLException {
		List<Supply> itemList = new ArrayList<>();
		
		try {
			itemList = this.getSqlMapClient().queryForList("getSuppliesItemList");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return itemList;
	}
	
	@Override
	public void insertSuppliesStocks(Map<String, Object> params) throws SQLException {
		try {
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();
			
			this.getSqlMapClient().insert("insertSuppliesStocks", params);
			
			this.sqlMapClient.executeBatch();
			this.sqlMapClient.getCurrentConnection().commit();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			this.sqlMapClient.getCurrentConnection().rollback();
		} finally {
			this.sqlMapClient.endTransaction();
		}
	}
	
	@Override
	public void updateSuppliesStocks(Map<String, Object> params) throws SQLException {
		try {
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();
			
			this.getSqlMapClient().update("updateSuppliesStocks", params);
			
			this.sqlMapClient.executeBatch();
			
			this.sqlMapClient.getCurrentConnection().commit();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			this.getSqlMapClient().getCurrentConnection().rollback();
		} finally {
			this.sqlMapClient.endTransaction();
		}
	}
	
	@Override
	public void updateSupplies(Map<String, Object> params) throws SQLException {
		try {
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();
			
			this.getSqlMapClient().update("updateSupplies", params);
			
			this.sqlMapClient.executeBatch();
			this.sqlMapClient.getCurrentConnection().commit();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			this.getSqlMapClient().getCurrentConnection().rollback();
		} finally {
			this.sqlMapClient.endTransaction();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SuppliesStock> searchSuppliesStocks(Map<String, Object> params) throws SQLException {
		List<SuppliesStock> itemList = new ArrayList<>();
		
		try {
			itemList = this.getSqlMapClient().queryForList("searchSuppliesStocks", params);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return itemList;
	}
}
