package com.sms.entity;

import java.util.Date;

public class SuppliesStock {

	private long stockId;
	private long supplyId;
	private String itemName;
	private Date dateAdded;
	private Date purchaseDate;
	private String referenceNo;
	private int quantity;
	private String lastUser;
	private Date lastUpdate;
	
	public long getStockId() {
		return stockId;
	}
	
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	
	public long getSupplyId() {
		return supplyId;
	}
	
	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Date getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	public String getReferenceNo() {
		return referenceNo;
	}
	
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
