package com.sms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sms.dao.SuppliesDAO;
import com.sms.entity.Supplies;
import com.sms.entity.SupplyTypes;

public class SuppliesDAOImpl implements SuppliesDAO{
	
	private SqlMapClient sqlMapClient;
	
	public SqlMapClient getSqlMapClient(){
		return sqlMapClient;
		
	}
	
	public void setSqlMapClient(SqlMapClient sqlMapClient){
		this.sqlMapClient = sqlMapClient;
	}
	
	@SuppressWarnings("unchecked")
	public List<SupplyTypes> getSupplyTypes() throws SQLException{
		List<SupplyTypes> supplyTypesList = new ArrayList<>();
		try{
			supplyTypesList = this.getSqlMapClient().queryForList("getSupplyTypes");
		}
		catch(SQLException e){
			
		}
		return supplyTypesList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Supplies> getSupplies() throws SQLException {
		List<Supplies> suppliesList = new ArrayList<>();
		try{
			suppliesList = this.getSqlMapClient().queryForList("getSupplies");
		}
		catch(SQLException e){
			//Change Code
			e.printStackTrace();
		}
		return suppliesList;
	}
	
	public void insertSupplies(Map<String, Object> supplies) throws SQLException {
		try{
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();
			
			this.sqlMapClient.insert("insertSupplies", supplies);
			
			this.sqlMapClient.executeBatch();
			this.sqlMapClient.getCurrentConnection().commit();
		}
		catch(SQLException e){
			e.printStackTrace();
			this.sqlMapClient.getCurrentConnection().rollback();
		}
		finally{
			this.sqlMapClient.endTransaction();
		}
	}
	
	public void updateSupplies(Map<String, Object> supplies) throws SQLException {
		try{
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();
			
			this.sqlMapClient.update("updateSupplies", supplies);
			
			this.sqlMapClient.executeBatch();
			this.sqlMapClient.getCurrentConnection().commit();
		}
		catch(SQLException e){
			e.printStackTrace();
			this.sqlMapClient.getCurrentConnection().rollback();
		}
		finally{
			this.sqlMapClient.endTransaction();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Supplies> searchSupplies(String itemName) throws SQLException{
		List<Supplies> suppliesList = new ArrayList<>();
		try{
			suppliesList = this.getSqlMapClient().queryForList("searchSupplies", itemName);
		}
		catch(SQLException e){
			//Change Code
			e.printStackTrace();
		}
		return suppliesList;
	}
}
