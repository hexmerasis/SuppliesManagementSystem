package com.sms.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
	void getUser(HttpServletRequest request) throws SQLException;
	void searchUser(HttpServletRequest request) throws SQLException;
	void getPassword(HttpServletRequest request) throws SQLException;
	void insertUser(HttpServletRequest request) throws SQLException;
	void updateUser(HttpServletRequest request) throws SQLException;
	void updatePassword(HttpServletRequest request) throws SQLException;
	void updateThisUser(HttpServletRequest request) throws SQLException;
}
