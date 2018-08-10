package com.cpi.dao.impl;

import java.sql.SQLException;
import java.util.Map;

import com.cpi.dao.LoginDAO;
import com.cpi.entity.Login;
import com.ibatis.sqlmap.client.SqlMapClient;

public class LoginDAOImpl implements LoginDAO{
	
	private SqlMapClient sqlMapClient;

	public SqlMapClient getSqlMapClient(){
		return sqlMapClient;
	}
	
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	public Login getUserInfo(Map<String, Object> userInfo) throws SQLException{
		Login user = new Login();
//		String inputId;

		try {
			user = (Login) this.getSqlMapClient().queryForObject("getLoginInfo", userInfo);
			return user;
//			inputId = (String) this.getSqlMapClient().queryForObject("getUserInfo", userInfo.get("userId"));
			
//			if(!inputId.equals("")){
//				if(user.getActiveTag().equals("N")){
//					return user;
//				}
//				
//				else if(!user.getPassword().equals(userInfo.get("password"))){
////					Integer attempts = (Integer) this.getSqlMapClient().queryForObject("getUserAttempt", inputId);
//					if(attempts == null){
//						//code para mag insert sa table 
//						try {
//							this.sqlMapClient.startTransaction();
//							this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
//							this.sqlMapClient.startBatch();
//
//							this.getSqlMapClient().update("insertAttempt", inputId);
//
//							this.sqlMapClient.executeBatch();
//							this.sqlMapClient.getCurrentConnection().commit();
//
//						} catch (SQLException e) {
//							System.out.println(e.getLocalizedMessage());
//							this.sqlMapClient.getCurrentConnection().rollback();
//						} finally {
//							this.sqlMapClient.endTransaction();
//						}
//					}
//					
//					else{
//						try {
//							this.sqlMapClient.startTransaction();
//							this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
//							this.sqlMapClient.startBatch();
//
//							this.getSqlMapClient().update("updateAttempt", inputId);
//							if(attempts == 2){
//								this.getSqlMapClient().update("lockAccount", inputId);
//							}
//							
//							this.sqlMapClient.executeBatch();
//							this.sqlMapClient.getCurrentConnection().commit();
//
//						} catch (SQLException e) {
//							System.out.println(e.getLocalizedMessage());
//							this.sqlMapClient.getCurrentConnection().rollback();
//						} finally {
//							this.sqlMapClient.endTransaction();
//						}
//					}
//				}
//				
//				else if(user != null && user.getPassword().equals(userInfo.get("password")) && !user.getActiveTag().equals("N")) {
//					try {
//						this.sqlMapClient.startTransaction();
//						this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
//						this.sqlMapClient.startBatch();
//
//						this.getSqlMapClient().update("setLastLogin", user.getUserId());
//						this.getSqlMapClient().update("removeAttempt", user.getUserId());
//
//						this.sqlMapClient.executeBatch();
//						this.sqlMapClient.getCurrentConnection().commit();
//						
//						return user;
//					
//					} catch (SQLException e) {
//						System.out.println(e.getLocalizedMessage());
//						this.sqlMapClient.getCurrentConnection().rollback();
//					} finally {
//						this.sqlMapClient.endTransaction();
//					}
//				}
//			}
			
		} catch (SQLException e) {
			// Exception here
		}
		
		return null;
	}
	
	public String checkUserId(String userId) throws SQLException{
		String inputId = (String) this.getSqlMapClient().queryForObject("getUserInfo", userId);
		return inputId;
	}
	
	public void insertAttempt(String userId) throws SQLException{
		try {
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();

			this.getSqlMapClient().update("insertAttempt", userId);

			this.sqlMapClient.executeBatch();
			this.sqlMapClient.getCurrentConnection().commit();

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			this.sqlMapClient.getCurrentConnection().rollback();
		} finally {
			this.sqlMapClient.endTransaction();
		}
	}
	
	public void updateAttempt(String userId) throws SQLException{
		try {
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();

			this.getSqlMapClient().update("updateAttempt", userId);
			
			this.sqlMapClient.executeBatch();
			this.sqlMapClient.getCurrentConnection().commit();

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			this.sqlMapClient.getCurrentConnection().rollback();
		} finally {
			this.sqlMapClient.endTransaction();
		}
	}
	
	public Integer checkAttempts(String userId) throws SQLException{
		Integer attempts = (Integer) this.getSqlMapClient().queryForObject("getUserAttempt", userId);
		
		return attempts;
	}
	
	public void lockAccount(String userId) throws SQLException{
		try {
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();

			this.getSqlMapClient().update("lockAccount", userId);
				
			this.sqlMapClient.executeBatch();
			this.sqlMapClient.getCurrentConnection().commit();

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			this.sqlMapClient.getCurrentConnection().rollback();
		} finally {
			this.sqlMapClient.endTransaction();
		}
	}
	
	public void updateLastLogin(String userId) throws SQLException{
		try {
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.getCurrentConnection().setAutoCommit(false);
			this.sqlMapClient.startBatch();

			this.getSqlMapClient().update("setLastLogin", userId);
			this.getSqlMapClient().update("removeAttempt", userId);

			this.sqlMapClient.executeBatch();
			this.sqlMapClient.getCurrentConnection().commit();
			
//			return user;
		
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			this.sqlMapClient.getCurrentConnection().rollback();
		} finally {
			this.sqlMapClient.endTransaction();
		}
	}
	
	public void truncateAttempts() throws SQLException{
		this.getSqlMapClient().update("truncateAttempts");
	}

}