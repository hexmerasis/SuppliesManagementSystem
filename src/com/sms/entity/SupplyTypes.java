package com.sms.entity;

import java.sql.Date;

public class SupplyTypes {
	private Integer supplyTypeID;
	private String typeName;
	private String entryDate;
	private String lastUser;
	private Date lastUpdate;
	
	public int getSupplyTypeID() {
		return supplyTypeID;
	}
	public void setSupplyTypeID(int supplyTypeID) {
		this.supplyTypeID = supplyTypeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getLastUser() {
		return lastUser;
	}
	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
}
