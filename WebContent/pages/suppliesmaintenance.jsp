<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Supplies Maintenance</title>
<link rel = "stylesheet" href = "css/bootstrap.min.css">
<script src = "js/prototype.js"></script>

<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>
<style>
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 60%;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}
</style>
<body>
<br>
<div id = "mainContents">
<div class = "col-md-2"></div>

<div class = "col-md-8">
<div class="panel panel-success" style = "box-shadow: 5px 5px rgb(163,211,141)">
  <div class="panel-heading"><h3><strong>Supply Maintenance</strong></h3></div>
  <div class="panel-body">
	<br>
	<div class = "col-md-6">
		<div class = "form-group row col-xs-12">
			<label>Supply Type</label>
			<select id = "selSupplyType" class = "form-control" disabled = "disabled">
			<c:forEach var = "supplytype" items = "${supplyTypesList}">
				<option value = "${supplytype.typeName}"><c:out value = "${supplytype.typeName}"/></option>
			</c:forEach>
			</select>
		</div>
		<div class = "col-md-12"></div>
		<div class = "form-group row col-xs-12">
			<label>Item Name</label><span class = "glyphicon glyphicon-asterisk"></span>
			<input type = "text" id = "txtItemName" maxlength = "100"
				class = "form-control" disabled = "disabled">
		</div>
		<div class = "col-md-12"></div>
		<div class = "form-group row col-xs-12">
			<label>Item Unit</label><span class = "glyphicon glyphicon-asterisk"></span>
			<input type = "text" id = "txtItemUnit" maxlength = "10"
				class = "form-control" disabled = "disabled">
		</div>
		<div class = "col-md-12"></div>
		<div class = "form-group row col-md-12">
			<div class = "row col-md-12">
				<label>Obsolete Tag</label>
			</div>
			<div class = "col-xs-1">
				<input type = "radio" id = "rdoYes" name = "rdoObsolete" value = "Y" disabled = "disabled">
			</div>
			<div class = "col-xs-1">Yes</div>
			<div class = "col-xs-1"></div>
			<div class = "col-xs-1">
				<input type = "radio" id = "rdoNo" name = "rdoObsolete" value = "N" checked = "checked" disabled = "disabled">
			</div>
			<div class = "col-xs-1">No</div>
			<div class = "col-xs-7"></div>
		</div>
		<div class = "col-md-12"></div>
		<div class = "form-group row col-xs-12">
			<label>Location</label>
			<input type = "text" id = "txtLocation" maxlength = 100
				class = "form-control">
		</div>
	</div>
	<div class = "col-md-6">
		<div class = "form-group row col-xs-12">
			<label>Entered Date</label>
			<jsp:useBean id = "now" class = "java.util.Date"/>
			<fmt:formatDate var = "dateToday" value = "${now}" pattern = "yyyy-MM-dd"/>
			<input type = "text" id = "txtEnteredDate" disabled = "disabled" value = "${dateToday}" 
				class = "form-control" style = "cursor: default;" disabled = "disabled"> 
		</div>
		<div class = "col-md-12"></div>
		<div class = "form-group row col-xs-5">
			<label>Reorder Level</label><span class = "glyphicon glyphicon-asterisk"></span>
			<input type = "text" id = "txtReorderLevel" maxlength = "4"
				class = "form-control" disabled = "disabled">
		</div>
		<div class = "col-md-1"></div>
		<div class = "form-group row col-xs-6">
			<label>Actual Count</label>
			<input type = "text" id = "txtActualCount" maxlength = "10" 
				class = "form-control" style = "cursor: default;" disabled = "disabled">
		</div>
		<div class = "col-md-12"></div>
		<div class = "form-group row col-xs-12">
			<label>Remarks</label>
			<textarea id = "txtRemarks" class = "form-control" rows = "8" maxlength = "1000"
				class = "form-control rounded-0" disabled = "disabled"></textarea>
		</div>
	</div>
</div>
<div class = "panel-footer" style = "text-align: right;">
		<input type = "button" id = "btnAdd" value = "Add New Item" class = "btn btn-primary">
		<input type = "button" id = "btnSave" value = "Save" class = "btn btn-success" style = "display: none;">
		<input type = "button" id = "btnCancel" value = "Cancel" class = "btn btn-warning" style = "display: none;">
</div>
</div>
</div>
<div class = "col-md-2"></div>
<div class = "col-md-12"><hr></div>
<div class = "col-md-1"></div>

<div class = "col-md-10">
	<div id = "searchDiv">
		<div class = "col-xs-4">
		<label>Search Item Name:</label>
		<div class = "input-group">
			<input type = "text" id = "txtSearch" maxlength = "100"
				class = "form-control">
			<span class = "input-group-btn">
				<button type = "button" id = "btnSearch" class = "btn btn-default glyphicon glyphicon-search">
				</button>
			</span>
			</div>
		</div>
		<div class = "col-xs-8"></div>
	</div>
	<br><br><br><br>
	<div class = "table-responsive">
	<table class= "table table-hover table-bordered" id = "suppliesTable" 
		style = "background-color: lightblue; box-shadow: 3px 3px rgb(80,80,80)">
	<tr style = "background-color: white;">
		<th>Supply ID</th>
		<th>Supply Type</th>
		<th>Item Name</th>
		<th>Item Unit</th>
		<th>Obsolete Tag</th>
		<th>Location</th>
		<th>Reorder Level</th>
		<th>Actual Count</th>
		<th>Remarks</th>
		<th>Date Added</th>
		<th>Last User</th>
		<th>Last Update</th>
	</tr>
	<c:forEach var = "supplies" items = "${suppliesList}">
		<tr id = "${supplies.supplyID}" class = "tablerow">
		<td><a href = "#"><c:out value="${supplies.supplyID}"/></a></td>
		<td><c:out value="${supplies.typeName}"/></td>
		<td><c:out value="${supplies.itemName}"/></td>
		<td><c:out value="${supplies.itemUnit}"/></td>
		<td><c:out value="${supplies.obsoleteTag}"/></td>
		<td><c:out value="${supplies.location}"/></td>
		<td><c:out value="${supplies.reorderLevel}"/></td>
		<td><c:out value="${supplies.actualCount}"/></td>
		<td><c:out value="${supplies.remarks}"/></td>
		<td><c:out value="${supplies.dateAdded}"/></td>
		<td><c:out value="${supplies.lastUser}"/></td>
		<td><c:out value="${supplies.lastUpdate}"/></td>
		</tr>
	</c:forEach>
	<c:if test = "${empty suppliesList}">
		<tr>
		<td colspan = "12" align="center">
		No results
		</td>
		</tr>
	</c:if>
	</table>
	</div>
</div>
<div class = "col-md-1"></div>
</div>
<div id = "incompleteRequirements" class = "modal">
	<div class = "modal-content">
		<span class = "close">&times;</span>
		<h4 style = "text-align: center;">Required details are not filled(*)</h4>
	</div>
</div>

<div id = "invalidInput" class = "modal">
	<div class = "modal-content">
		<span class = "close">&times;</span>
		<h4 style = "text-align: center;">Invalid Input</h4>
	</div>
</div>

</body>
<script>
$("btnAdd").observe("click", function(){addSupplies()});
$("btnSave").observe("click", function(){createOrReplaceSupplies()})
$("btnCancel").observe("click", function(){cancelSupplies()});
$("btnSearch").observe("click", function(){search()});
$("txtActualCount").writeAttribute("disabled", "disabled");
$("txtActualCount").value = 0;
disableFields();

var action = "insertSupplies";
var addObj = {};
var rec = [];
var updateID = -1;
var selectedRow = -1;

function search(){
	new Ajax.Request(contextPath + "/suppliesmaintenance", {
		method: "POST",
		parameters: {
			action: "searchSupplies",
			search: $F("txtSearch")
		},
		onComplete: function(response){
			$("mainContents").update(response.responseText);
		}
	});
}

function createOrReplaceSupplies(){
	addObj.supplyType = $F("selSupplyType");
	addObj.itemName = $F("txtItemName");
	addObj.itemUnit = $F("txtItemUnit");
	addObj.obsoleteTag = $("rdoYes").checked ? "Y" : "N";
	addObj.location = $F("txtLocation");
	addObj.reorderLevel = $F("txtReorderLevel");
	addObj.actualCount = $F("txtActualCount");
	addObj.remarks = $F("txtRemarks");
	addObj.dateAdded = $F("txtEnteredDate");
	addObj.lastUpdate = $F("txtEnteredDate");
	
	addObj.itemName.trim();
	addObj.itemUnit.trim();
	addObj.location.trim();
	addObj.reorderLevel.trim();
	addObj.remarks.trim();

	var pattern = /^\d+$/.test(addObj.reorderLevel);
	var modal = "";

	 document.getElementsByClassName("close")[0].onclick = function(){
		 modal.style.display = "none";
	 }
	 
	 document.getElementsByClassName("close")[1].onclick = function(){
		 modal.style.display = "none";
	 }
	 
	 window.onclick = function(event) {
		    if (event.target == modal) {
		        modal.style.display = "none";
		    }
		}
	
	if(addObj.itemName == null || addObj.itemName == '' ||
			addObj.itemUnit == null || addObj.itemUnit == ''){
		 modal = document.getElementById('incompleteRequirements');
		 modal.style.display = "block";
	}
	else if(!pattern){
		modal = $("invalidInput");
		modal.style.display = "block";
	}
	else{
		if(action == "insertSupplies"){
			saveSupplies();
		}
		if(action == "updateSupplies"){
			updateSupplies();
		}
	}
}

function addSupplies(){
	action = "insertSupplies";
	$("searchDiv").remove();
	$("btnAdd").remove();
	$("suppliesTable").remove();
	$("btnSave").removeAttribute("style");
	$("btnCancel").removeAttribute("style");
	$("btnSave").writeAttribute("disabled", false);
	$("btnCancel").writeAttribute("disabled", false);
	enableFields();
	resetFields();
}

function saveSupplies(){
	new Ajax.Request(contextPath + "/suppliesmaintenance", {
		method: "POST",
		parameters: {
			action: action,
			typeName: addObj.supplyType,
			itemName: addObj.itemName,
			itemUnit: addObj.itemUnit,
			obsoleteTag: addObj.obsoleteTag,
			location: addObj.location,
			reorderLevel: addObj.reorderLevel,
			actualCount: addObj.actualCount,
			remarks: addObj.remarks,
			dateAdded: addObj.dateAdded,
			lastUpdate: addObj.lastUpdate
		},
		onComplete: function(response){
			window.location.reload();
			resetFields();
		}
	});
}

function updateSupplies(){
	new Ajax.Request(contextPath + "/suppliesmaintenance", {
		method: "POST",
		parameters: {
			action: action,
			supplyID: updateID,
			typeName: $F("selSupplyType"),
			itemName: $F("txtItemName"),
			itemUnit: $F("txtItemUnit"),
			obsoleteTag: $("rdoYes").checked ? "Y" : "N",
			location: $F("txtLocation"),
			reorderLevel: $F("txtReorderLevel"),
			remarks: $F("txtRemarks"),
		},
		onComplete: function(response){
			window.location.reload();
			resetFields();
		}
	});
}

function cancelSupplies(){
	window.location.reload();
	disableFields();
	resetFields();
}

function resetFields(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1;
	var yyyy = today.getFullYear();
	dd = dd < 10 ? "0" + dd : dd;
	mm = mm < 10 ? "0" + mm : mm;
	
	$("selSupplyType").selectedIndex = 0;
	$("txtItemName").value = null;
	$("txtItemUnit").value = null;
	$("rdoNo").checked = true;
	$("txtLocation").value = null;
	$("txtEnteredDate").value = yyyy + "-" + mm + "-" + dd;
	$("txtReorderLevel").value = null;
	$("txtRemarks").value = null;
	$("txtActualCount").value = 0;
}

function enableFields(){
	$("selSupplyType").removeAttribute("disabled");
	$("txtItemName").removeAttribute("disabled");
	$("txtItemUnit").removeAttribute("disabled");
	$("rdoYes").removeAttribute("disabled");
	$("rdoNo").removeAttribute("disabled");
	$("txtLocation").removeAttribute("disabled");
	$("txtReorderLevel").removeAttribute("disabled");
	$("txtRemarks").removeAttribute("disabled");
	
	$("selSupplyType").writeAttribute("style", "cursor: pointer;");
	$("txtItemName").writeAttribute("style", "cursor: text;");
	$("txtItemUnit").writeAttribute("style", "cursor: text;");
	$("rdoYes").writeAttribute("style", "cursor: pointer;");
	$("rdoNo").writeAttribute("style", "cursor: pointer;");
	$("txtLocation").writeAttribute("style", "cursor: text;");
	$("txtReorderLevel").writeAttribute("style", "cursor: text;");
	$("txtRemarks").writeAttribute("style", "cursor: text; resize:none;");
}

function disableFields(){
	$("selSupplyType").writeAttribute("disabled", "disabled");
	$("txtItemName").writeAttribute("disabled", "disabled");
	$("txtItemUnit").writeAttribute("disabled", "disabled");
	$("rdoYes").writeAttribute("disabled", "disabled");
	$("rdoNo").writeAttribute("disabled", "disabled");
	$("txtLocation").writeAttribute("disabled", "disabled");
	$("txtReorderLevel").writeAttribute("disabled", "disabled");
	$("txtRemarks").writeAttribute("disabled", "disabled");

	$("selSupplyType").writeAttribute("style", "cursor: default;");
	$("txtItemName").writeAttribute("style", "cursor: default;");
	$("txtItemUnit").writeAttribute("style", "cursor: default;");
	$("rdoYes").writeAttribute("style", "cursor: default;");
	$("rdoNo").writeAttribute("style", "cursor: default;");
	$("txtLocation").writeAttribute("style", "cursor: default;");
	$("txtReorderLevel").writeAttribute("style", "cursor: default;");
	$("txtRemarks").writeAttribute("style", "cursor: default; resize:none;");
}

$$(".tablerow").each(function(row){
	row.down("a", 0).observe("click", function(){
		updateID = row.down("a", 0).innerHTML;
		$("selSupplyType").value = row.down("td", 1).innerHTML;
		$("txtItemName").value = row.down("td", 2).innerHTML;
		$("txtItemUnit").value = row.down("td", 3).innerHTML;
		switch(row.down("td", 4).innerHTML){
		case "Y": $("rdoYes").checked = true; break;
		case "N": $("rdoNo").checked  = true; break;
		}
		$("txtLocation").value = row.down("td", 5).innerHTML;
		$("txtReorderLevel").value = row.down("td", 6).innerHTML;
		$("txtActualCount").value = row.down("td", 7).innerHTML;
		$("txtRemarks").value = row.down("td", 8).innerHTML;
		$("txtEnteredDate").value = row.down("td", 9).innerHTML;
		
		action = "updateSupplies";
		$("searchDiv").remove();
		$("btnAdd").remove();
		$("suppliesTable").remove();
		$("btnSave").removeAttribute("style");
		$("btnCancel").removeAttribute("style");
		enableFields();
		$("txtActualCount").writeAttribute("disabled","disabled");
	});
	
	row.observe("click", function(){
		$$(".tablerow").each(function(row2){
			row2.removeClassName("success");
		});
		if(selectedRow != row.down("a",0).innerHTML){
			row.addClassName("success");
			selectedRow = row.down("a",0).innerHTML;
			$("selSupplyType").value = row.down("td", 1).innerHTML;
			$("txtItemName").value = row.down("td", 2).innerHTML;
			$("txtItemUnit").value = row.down("td", 3).innerHTML;
			switch(row.down("td", 4).innerHTML){
			case "Y": $("rdoYes").checked = true; break;
			case "N": $("rdoNo").checked  = true; break;
			}
			$("txtLocation").value = row.down("td", 5).innerHTML;
			$("txtReorderLevel").value = row.down("td", 6).innerHTML;
			$("txtActualCount").value = row.down("td", 7).innerHTML;
			$("txtRemarks").value = row.down("td", 8).innerHTML;
			$("txtEnteredDate").value = row.down("td", 9).innerHTML;
		}
		else{
			selectedRow = -1;
		}
	});
});
	
</script>
<script src = "js/bootstrap.js"></script>
</html>