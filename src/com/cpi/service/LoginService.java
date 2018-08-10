package com.cpi.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
	void getUserInfo(HttpServletRequest request) throws SQLException;
	void logout();
}
