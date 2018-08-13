package com.sms.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sms.dao.StockSuppliesDao;
import com.sms.entity.Departments;
import com.sms.entity.Supplies;
import com.sms.entity.TableView;
import com.sms.service.StockSuppliesService;

public class StockSuppliesServiceImpl implements StockSuppliesService {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private StockSuppliesDao suppliesDao;

	public void setSuppliesDao(StockSuppliesDao suppliesDao) {
		this.suppliesDao = suppliesDao;
	}

	public StockSuppliesDao getSuppliesDao() {
		return suppliesDao;
	}

	@Override
	public void getSupplies(HttpServletRequest request) throws SQLException {
	
		request.setAttribute("issueList", suppliesDao.getIssue());
		request.setAttribute("departmentList", suppliesDao.getDepartments());
		request.setAttribute("itemList", suppliesDao.getItemName());
		request.setAttribute("supplies", suppliesDao.getSupplies());

	}

	@Override
	public boolean insertSupplies(HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();
		Integer go = 0;
		String itemName = request.getParameter("itemName");
		List<Supplies> supply = suppliesDao.getSupplies();
		List<Departments> deptNo = suppliesDao.getDepartments();
		
		for (int i = 0; i < supply.size(); i++) {
			if (itemName.equalsIgnoreCase(supply.get(i).getItemName())) {
				if (Integer.parseInt(request.getParameter("quantity")) <= supply.get(i).getActualCount()) {
					go = 1;
					if ((supply.get(i).getActualCount() - Integer.parseInt(request.getParameter("quantity")) <= supply.get(i).getReorderLevel())) {
						request.setAttribute("sytemMessage", "The actual count of " + itemName + " is below or equal the reorder level!" );
					} else {
						request.setAttribute("sytemMessage", null);
					}

					break;
				}
			}
		}

		if (go == 1) {
			Map<String, Object> supplies = new HashMap<>();

			supplies.put("requestor", request.getParameter("requestor"));
			supplies.put("quantity", request.getParameter("quantity"));
			supplies.put("issueDate", request.getParameter("issueDate"));
			supplies.put("departmentName", request.getParameter("departmentName"));

			
			for (int i = 0; i < deptNo.size(); i++) {
				if (deptNo.get(i).getDepartmentName().equalsIgnoreCase(request.getParameter("departmentName"))) {
					supplies.put("departmentId", deptNo.get(i).getDepartmentId());

					break;
				}
			}
			for (int i = 0; i < supply.size(); i++) {
				if (supply.get(i).getItemName().equalsIgnoreCase(request.getParameter("itemName"))) {
					supplies.put("lastUpdate", supply.get(i).getLastUpdate());
					supplies.put("supplyId", supply.get(i).getSupplyId());
					supplies.put("lastUser", session.getAttribute("currentUserId"));
					break;
				}
			}

			this.suppliesDao.insertSupplies(supplies);
			return true;
		} else {
			

			for (int i = 0; i < supply.size(); i++) {
				if (itemName.equalsIgnoreCase(supply.get(i).getItemName())) {				
					request.setAttribute("sytemMessage", itemName +  " only have " + supply.get(i).getActualCount() );
					break;
				}
			}
			
			return false;
		}
	}

	@Override
	public void updateSupplies(HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();
		Map<String, Object> issueUpdate = new HashMap<>();
		List<Departments> deptNo = suppliesDao.getDepartments();
		List<Supplies> supply = suppliesDao.getSupplies();
		
		
		for (int i = 0; i < deptNo.size(); i++) {
			if (deptNo.get(i).getDepartmentName().equalsIgnoreCase(request.getParameter("departmentName"))) {
				issueUpdate.put("departmentId", deptNo.get(i).getDepartmentId());
				break;
			}
		}
		for (int i = 0; i < supply.size(); i++) {
			if (supply.get(i).getItemName().equalsIgnoreCase(request.getParameter("itemName"))) {
				issueUpdate.put("lastUpdate", supply.get(i).getLastUpdate());
				issueUpdate.put("supplyId", supply.get(i).getSupplyId());
				issueUpdate.put("lastUser",  session.getAttribute("currentUserId"));
				break;
			}
		}
		issueUpdate.put("requestor", request.getParameter("requestor"));
		issueUpdate.put("quantity", request.getParameter("quantity"));
		issueUpdate.put("issueDate", request.getParameter("issueDate"));
		issueUpdate.put("issueId", request.getParameter("issueId"));

		this.suppliesDao.updateSupplies(issueUpdate);
		
	}

	@Override
	public void searchFor(HttpServletRequest request) throws SQLException {			
		request.setAttribute("issueList",this.suppliesDao.searchFor(request));
		request.setAttribute("departmentList", suppliesDao.getDepartments());
		request.setAttribute("itemList", suppliesDao.getItemName());
		request.setAttribute("supplies", suppliesDao.getSupplies());
	}
	
}
