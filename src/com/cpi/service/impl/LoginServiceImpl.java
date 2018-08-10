package com.cpi.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cpi.dao.LoginDAO;
import com.cpi.entity.Login;
import com.cpi.service.LoginService;

public class LoginServiceImpl implements LoginService{
	
	private LoginDAO loginDAO;
	
	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	@Override
	public void getUserInfo(HttpServletRequest request){
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("userId", request.getParameter("userId"));
		userInfo.put("password", request.getParameter("password"));
		
		try {
			String inputId = loginDAO.checkUserId(request.getParameter("userId"));
			
			if(inputId == null){
				request.setAttribute("loginStatus", 0);
			}
			
			else{
				Login user = loginDAO.getUserInfo(userInfo);
				if(user.getActiveTag().equals("N")){
					request.setAttribute("loginStatus", 2);
				}
				
				else if(!user.getPassword().equals(request.getParameter("password"))){						
					Integer attempts = loginDAO.checkAttempts(request.getParameter("userId"));
					if(attempts == null){
						loginDAO.insertAttempt(request.getParameter("userId"));
					}
					else{
						loginDAO.updateAttempt(request.getParameter("userId"));
						if(attempts == 2){
							loginDAO.lockAccount(request.getParameter("userId"));
						}
					}
					request.setAttribute("loginStatus", 0);
				}
				
				else if(user.getPassword().equals(request.getParameter("password"))){
					HttpSession session = request.getSession();
					loginDAO.updateLastLogin(request.getParameter("userId"));						
					session.setAttribute("currentUserId", loginDAO.getUserInfo(userInfo).getUserId());
					session.setAttribute("currentPassword", loginDAO.getUserInfo(userInfo).getPassword());
					session.setAttribute("currentUserFN", loginDAO.getUserInfo(userInfo).getFirstName());
					session.setAttribute("currentAccessLevel", loginDAO.getUserInfo(userInfo).getAccessLevel());
					request.setAttribute("loginStatus", 1);
				}	
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 	
	}
	
	public void logout(){
		try {
			loginDAO.truncateAttempts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
