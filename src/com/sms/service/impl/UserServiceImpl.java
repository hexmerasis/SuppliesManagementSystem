package com.sms.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sms.dao.UserDAO;
import com.sms.entity.User;
import com.sms.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void getUser(HttpServletRequest request) throws SQLException{
		List<User> user = userDAO.getUser();
		String userId = request.getParameter("userId");
		
		if(userId != null){
			for(User u: user){
				if(request.getParameter("userId").equals(u.getUserId())){
					request.setAttribute("passwordList", u.getPassword());
					break;
				}
			}
		}
		request.setAttribute("userList", user);
	}
	
	public void getPassword(HttpServletRequest request) throws SQLException{
		Map<String, Object> params = new HashMap<>();
		params.put("userId", request.getParameter("userId"));
		request.setAttribute("passwordList", userDAO.getPassword(params));
	}
	
	public void searchUser(HttpServletRequest request) throws SQLException{
		Map<String, Object> params = new HashMap<>();
		params.put("userId", request.getParameter("userId"));
		request.setAttribute("userList", userDAO.searchUser(params));
	}
	
	
	public void insertUser(HttpServletRequest request) throws SQLException{
		Map<String, Object> params = new HashMap<>();
		params.put("userId", request.getParameter("userId"));
		params.put("password", request.getParameter("userId"));
		params.put("firstName", request.getParameter("firstName"));
		params.put("lastName", request.getParameter("lastName"));
		params.put("middleInitial", request.getParameter("middleInitial"));
		params.put("email", request.getParameter("email"));
		params.put("activeTag", request.getParameter("activeTag"));
		params.put("accessLevel", request.getParameter("accessLevel"));
		params.put("lastUser", request.getParameter("lastUser"));
		params.put("lastUpdate", request.getParameter("lastUpdate"));
		
		this.userDAO.insertUser(params);
	}
	
	public void updateUser(HttpServletRequest request) throws SQLException{
		Map<String, Object> params = new HashMap<>();
		params.put("userId", request.getParameter("userId"));
		params.put("firstName", request.getParameter("firstName"));
		params.put("lastName", request.getParameter("lastName"));
		params.put("middleInitial", request.getParameter("middleInitial"));
		params.put("email", request.getParameter("email"));
		params.put("activeTag", request.getParameter("activeTag"));
		params.put("accessLevel", request.getParameter("accessLevel"));
		params.put("entryDate", request.getParameter("entryDate"));
		params.put("lastUser", request.getParameter("lastUser"));
		params.put("lastUpdate", request.getParameter("lastUpdate"));
		this.userDAO.updateUser(params);
	}
	
	public void updateThisUser(HttpServletRequest request) throws SQLException{
		Map<String, Object> params = new HashMap<>();
		params.put("userId", request.getParameter("userId"));
		params.put("firstName", request.getParameter("firstName"));
		params.put("lastName", request.getParameter("lastName"));
		params.put("middleInitial", request.getParameter("middleInitial"));
		params.put("email", request.getParameter("email"));
		params.put("entryDate", request.getParameter("entryDate"));
		params.put("lastUser", request.getParameter("lastUser"));
		params.put("lastUpdate", request.getParameter("lastUpdate"));
		this.userDAO.updateThisUser(params);
	}
	
	
	public void updatePassword(HttpServletRequest request) throws SQLException{
		Map<String, Object> params = new HashMap<>();
		params.put("userId", request.getParameter("userId"));
		params.put("password", request.getParameter("newPass"));
		params.put("lastUser", request.getParameter("lastUser"));
		this.userDAO.updatePassword(params);
	}
}
