<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stocks</title>
<style>
	input[type="text"] {
		width: 200px;
	}
	input[type="button"] {
		width: 100px;
		margin: 3px;
	}
</style>
<script src="js/prototype.js"></script>
<script src="js/script.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}"
</script>
</head>
<body>
<h3>Stocks</h3>
<div style="width: 60%; float: left; padding-bottom: 20px;">
<input type="hidden" id="selectedSupplyId" value="${selectedSupplyId}">
<input type="hidden" id="stockId" value="${stockId}">
	<table>
		<tr>
			<td>Item Name</td>
			<td><select id="itemName">
				<c:forEach var="item" items="${suppliesItemList}">
					<option value="${item.supplyId}"><c:out value="${item.itemName}"/></option>
				</c:forEach>
				</select></td>
		</tr>
		<tr>
			<td>Quantity</td>
			<td><input type="text" name="txtQuantity" id="txtQuantity" value="${quantity}"></td>
		</tr>
		<tr>
			<td>Reference No.</td>
			<td><input type="text" name="txtReferenceNo" id="txtReferenceNo" value="${referenceNo}"></td>
		</tr>
		<tr>
			<td>Date Added</td>
			<td><input type="text" name="txtDateAdded" id="txtDateAdded" value="${dateAdded}" disabled></td>
		</tr>
		<tr>
			<td>Purchase Date</td>
			<td><input type="text" name="txtPurchaseDate" id="txtPurchaseDate" value="${purchaseDate}"></td>
		</tr>
	</table>
	</div>
<div style="width: 30%; float: right">
	<input type="button" id="saveBtn" value="Save"><br>
	<input type="button" id="cancelBtn" value="Cancel"><br>
</div>
</body>
<script>
	$("itemName").value = $F("selectedSupplyId");
	$("txtDateAdded").value = $F("txtDateAdded") == ""? mm + "/" + dd + "/" + yyyy : $F("txtDateAdded");
	
	if(action == "UpdateRecord") {
		$("txtQuantity").disable();
	}
	
	$("cancelBtn").observe("click", function() {
		cancelSuppliesStocksRecord();
	});
	
	$("saveBtn").observe("click", function() {
		if(action == "UpdateRecord") {
			updateSuppliesStocksRecord();
		} else if(action == "AddRecord") {
		 	addSuppliesStocksRecord(); 
		 	saveSuppliesStocksRecord();
		}
	});

</script>
</html>