package com.sms.entity;

import java.sql.Date;

public class Supplies {
	private Integer supplyID;
	private String typeName;
	private String itemName;
	private String itemUnit;
	private String obsoleteTag;
	private String location;
	private Integer reorderLevel;
	private Integer actualCount;
	private String remarks;
	private Date dateAdded;
	private String lastUser;
	private Date lastUpdate;
	
	public Integer getSupplyID() {
		return supplyID;
	}
	public void setSupplyID(Integer supplyID) {
		this.supplyID = supplyID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public String getObsoleteTag() {
		return obsoleteTag;
	}
	public void setObsoleteTag(String obsoleteTag) {
		this.obsoleteTag = obsoleteTag;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getReorderLevel() {
		return reorderLevel;
	}
	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}
	public Integer getActualCount() {
		return actualCount;
	}
	public void setActualCount(Integer actualCount) {
		this.actualCount = actualCount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
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
