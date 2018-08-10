package com.sms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sms.dao.StockSuppliesDao;
import com.sms.entity.Departments;
import com.sms.entity.ItemNames;
import com.sms.entity.Supplies;
import com.sms.entity.TableView;

public class StockSuppliesDaoImpl implements StockSuppliesDao {

	private SqlMapClient sqlMapClient;

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TableView> getIssue() throws SQLException {
		List<TableView> listSupply = new ArrayList<>();
		try {

			listSupply = this.getSqlMapClient().queryForList("getIssue");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return listSupply;
	}

	@Override
	public void insertSupplies(Map<String, Object> supplies) throws SQLException {
		try {
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();

			this.getSqlMapClient().update("insertSupplies", supplies);

			this.sqlMapClient.executeBatch();

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getErrorCode());

			this.sqlMapClient.getCurrentConnection().rollback();
		} finally {
			this.sqlMapClient.endTransaction();
		}
	}

	public void updateSupplies(Map<String, Object> supplies) throws SQLException {
		try {
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();
		
			this.getSqlMapClient().update("updatedSupplies", supplies);
			this.sqlMapClient.executeBatch();

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			this.getSqlMapClient().getCurrentConnection().rollback();
		} finally {
			this.sqlMapClient.endTransaction();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Departments> getDepartments() throws SQLException {
		List<Departments> Departments = new ArrayList<>();
		try {

			Departments = this.getSqlMapClient().queryForList("selectDepartments");

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return Departments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemNames> getItemName() throws SQLException {
		List<ItemNames> Items = new ArrayList<>();
		try {

			Items = this.getSqlMapClient().queryForList("selectItems");

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return Items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplies> getSupplies() throws SQLException {
		List<Supplies> supplies = new ArrayList<>();
		try {
			supplies = this.getSqlMapClient().queryForList("selectSupplies");

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return supplies;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TableView> searchFor(HttpServletRequest request)throws SQLException {
		
		List<TableView> searchList = new ArrayList<>();
		String search =request.getParameter("searchFor");
		try {
			searchList = this.getSqlMapClient().queryForList("getSearched", search);
			
		
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return searchList;
	}
}
