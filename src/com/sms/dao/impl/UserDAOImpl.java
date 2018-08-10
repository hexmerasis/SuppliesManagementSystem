package com.sms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.sms.dao.UserDAO;
import com.sms.entity.User;

public class UserDAOImpl implements UserDAO{
	private SqlMapClient sqlMapClient;
	
	public SqlMapClient getSqlMapClient(){
		return sqlMapClient;
	}
	
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUser() throws SQLException {
		List<User> listUsr = new ArrayList<>();
		try{
			listUsr = this.getSqlMapClient().queryForList("getUser");
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		
		return listUsr;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> searchUser(Map<String, Object> search) throws SQLException {
		List<User> listUsr = new ArrayList<>();
		try{
			listUsr = this.getSqlMapClient().queryForList("getSearch",search);
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		
		return listUsr;
	}
	
	@Override
	public void insertUser(Map<String, Object> params) throws SQLException {
		try{
		this.sqlMapClient.startTransaction();
		this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
		this.sqlMapClient.startBatch();
		
		
		this.getSqlMapClient().update("insertUser", params);
		
		this.sqlMapClient.executeBatch();
		this.sqlMapClient.getCurrentConnection().commit();
		
		} catch (SQLException e){
			System.out.println(e.getLocalizedMessage());
			this.sqlMapClient.getCurrentConnection().rollback();
		}finally{
			this.sqlMapClient.endTransaction();
		}
	}
	
	
	@Override
	public void updateUser(Map<String, Object> params) throws SQLException {
		try{
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();
	
			this.getSqlMapClient().update("updateUser", params);
			
			this.sqlMapClient.executeBatch();
			this.sqlMapClient.getCurrentConnection().commit();
		} catch(SQLException e){
			System.out.println(e.getMessage());
			this.getSqlMapClient().getCurrentConnection().rollback();
		} finally{
			this.sqlMapClient.endTransaction();
		}
	}
	
	@Override
	public void updateThisUser(Map<String, Object> params) throws SQLException {
		try{
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();
	
			this.getSqlMapClient().update("updateThisUser", params);
			
			this.sqlMapClient.executeBatch();
			this.sqlMapClient.getCurrentConnection().commit();
		} catch(SQLException e){
			System.out.println(e.getMessage());
			this.getSqlMapClient().getCurrentConnection().rollback();
		} finally{
			this.sqlMapClient.endTransaction();
		}
	}
	
	@Override
	public void updatePassword(Map<String, Object> params) throws SQLException {
		try{
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();
			this.getSqlMapClient().update("updatePassword", params);
			this.sqlMapClient.executeBatch();
			this.sqlMapClient.getCurrentConnection().commit();
		} catch(SQLException e){
			System.out.println(e.getMessage());
			this.getSqlMapClient().getCurrentConnection().rollback();
		} finally{
			this.sqlMapClient.endTransaction();
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getPassword(Map<String, Object> params) throws SQLException {
		List<User> listUsr = new ArrayList<>();
		try{
			listUsr = this.getSqlMapClient().queryForList("getPassword");
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		
		return listUsr;
	}

}
