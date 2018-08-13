<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issuance</title>
<script src="js/prototype.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}"
</script>
</head> 
<link rel="stylesheet" href="css/styleMark.css" type="text/css">
<body>

	<div id="main">
		<h1>CPI</h1>
		<hr>
		<br> <br> <br>
		<div id="issueSupplies">
			<fieldset style="width: 98%;">
				<legend>Issue Supplies</legend>
				<table align="center">
					<tr>
						<td>Item Name:</td>
						<td><select id="iName">
								<c:forEach var="items" items="${itemList}">
									<option>${items.itemName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>Quantity:</td>
						<td><input type="text" name="quant" id="iQuantity"></td>
					</tr>

					<tr>
						<td>Requested By:</td>
						<td><input type="text" name="request" id="iRequestor"></td>
					</tr>

					<tr>
						<td>Department Name:</td>
						<td><select id="departments">
								<c:forEach var="dept" items="${departmentList}">
									<option>${dept.departmentName}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td>Issue Date:</td>
						<td><input type="date" data-date=""
							data-date-format="DD MMMM YYYY" value="" id="iDate" /></td>
					</tr>
					
					<tr>
					<td><div id="theSearch" align = "center">
						<input type="text" id="iSearch"><input type = "button" id = "searchFor" value = "Search">
					</div></td>
					</tr>
				</table>
				<div align="center" id = "buttons">
					<div id="btnR">
						<input type="button" name="issue" id="iRequest"value="Issue Request" class = "btns">
					</div>
					
					<br>
					<input type="button" name="save" id="saveRow" value="Save" class = "btns">
					<br>
					<br>
					<input type="button" name="cancel" id="cancelRow" value="Cancel" class = "btns">
					<br>
					
				</div>
				
				<!-- <div id="theSearch" align = "center">
						<input type="text" id="iSearch"><input type = "button" id = "searchFor" value = "Search">
					</div> -->
			</fieldset>
		</div>
		<br> <br>
		<div id="issuedSuppliesTable">
			<div id="tableDiv"
				style="width: 986px; margin: 3px 1px 1px 3px; border: 1px solid black"
				align="center">
				<table id="issuedTable" class="dbTable" border="1">
					<tr>
						<td class="td" align="center">Issued Id</td>
						<td class="td" align="center">Item Name</td>
						<td class="td" align="center">Issue Date</td>
						<td class="td" align="center">Requestor</td>
						<td class="td" align="center">Quantity</td>
						<td class="td" align="center">Department Name</td>
						<td class="td" align="center">Last User</td>
						<td class="td" align="center">Last Update</td>
					</tr>
					<c:forEach var="supply" items="${issueList}">
						<tr class="tableRow">
							<td class="td" align="center"><a class="toLink">${supply.issueId}</a></td>
							<td class="td" align="center">${supply.itemName}</td>
							<td class="td" align="center">${supply.issueDate}</td>
							<td class="td" align="center">${supply.requestor}</td>
							<td class="td" align="center">${supply.quantity}</td>
							<td class="td" align="center">${supply.departmentName}</td>
							<td class="td" align="center">${supply.lastUser}</td>
							<td class="td" align="center">${supply.lastUpdate}</td>
						</tr>
					</c:forEach>
					
				</table>


			</div>
		</div>
	</div>
</body>
<script>
	if ('${sytemMessage}' != "") {
		alert('${sytemMessage}');
	}
	var disables = 1;
	var handler = 0;
	var message = "insert";
	var method = "";
	var issueId = 0;
	var cancelCheck = 0;
	var saveContent = 0;
	
	$('searchFor').observe("click", function(){
		new Ajax.Request(contextPath + "/searchRecord", {
			method : "POST",
			parameters : {
				action : "searchRecord",
				searchFor : $F("iSearch"),				
			},
			onComplete : function(response) {
				$("main").update(response.responseText);
			}
		})
		
	});
	

	if (disables == 1) {
		$("iName").disabled = true;
		$("iQuantity").disabled = true;
		$("iRequestor").disabled = true;
		$("departments").disabled = true;
		$("iDate").disabled = true;
	}
	$("saveRow").observe("click", function() {

		if(saveContent == 1){
		
			emptyHandler();
			
			if (handler == 1){
				
				addRecord();
				}
		}else{
			
			
			new Ajax.Request(contextPath + "/insertRecord", {
				method : "POST",
				parameters : {
					action : "commitRecord"
				},
				onComplete : function(response) {
					window.location.reload();
				}
			}) 
			
		}
	});

	$("cancelRow").observe("click", function() {

		if(cancelCheck == 1){
			clearAll();
			


		new Ajax.Request(contextPath + "/issueSupplies", {
			method : "GET",
			parameters : {
				action : "pageOut"
			},
			onComplete : function(response) {
				cancelCheck = 0;
				window.location.reload();

			}
		})
		}else{
			
			new Ajax.Request(contextPath + "/loginfromIssue", {
				method : "POST",	parameters: {

			},
				onComplete : function(response) {
					window.location.reload();

				}
			})
		}
	});

	function emptyHandler() {
		
		 if($("iQuantity").value==null || $("iQuantity").value== "" || 
			$("iRequestor").value==null || $("iRequestor").value== ""||
		    $("iDate").value==null || $("iDate").value== "" || $("departments").value == null ||
		    $("departments").value ==""){
			 
			alert("Check fields or proper values");
			handler = 0;
		}else if(($("iQuantity").value<0)||$("iQuantity").value>9999){
			alert("Check fields or proper values");
			handler = 0;
		}else {

			handler = 1;
			}
 
	}
	$("iRequest").observe("click", function() {		
		cancelCheck = 1;
		saveContent = 1;
		$("iQuantity").value="";
		$("iRequestor").value="";
		$("iDate").value="";
		$("iName").value="";
		$("departments").value ="";
		

		$("iName").disabled = false;
		$("iQuantity").disabled = false;
		$("iRequestor").disabled = false;
		$("departments").disabled = false;
		$("iDate").disabled = false;

		new Ajax.Request(contextPath + "/issueSupplies", {
			method : "GET",
			parameters : {
				action : "pageOut"
			},
			onComplete : function(response) {
				$("issuedSuppliesTable").update(response.responseText);
				$("btnR").update(response.responseText);
				$("theSearch").update(response.responseText);

			}
		})
	});

	function addRecord() {
		
		if (message == "insert") {
			new Ajax.Request(contextPath + "/insertRecord", {
				method : "POST",
				parameters : {
					action : "insertRecord",
					itemName : $F("iName"),
					quantity : $F("iQuantity"),
					requestor : $F("iRequestor"),
					departmentName : $F("departments"),
					issueDate : $F("iDate"),
				},
				onComplete : function(response) {
					disables = 1;
					$("iName").value = "";
					$("iQuantity").value = "";
					$("iRequestor").value = "";
					$("departments").value = "";
					$("iDate").value = "";
					$("main").update(response.responseText);
					saveContent = 0;
				}
			})
		} else if (message == "update") {
			new Ajax.Request(contextPath + "/insertRecord", {
				method : "POST",
				parameters : {
					action : "updateRecord",
					issueId : issueId,
					itemName : $F("iName"),
					quantity : $F("iQuantity"),
					requestor : $F("iRequestor"),
					departmentName : $F("departments"),
					issueDate : $F("iDate"),

				},
				onComplete : function(response) {
					saveContent = 0;
					window.location.reload();
				}
			})
		}
	};
	function clearAll() {
		$("iQuantity").value = "";
		$("iRequestor").value = "";

	}

	$$(".tableRow").each(function(row) {
		
		row.observe("click", function() {
			$("iName").value = row.down("td", 1).innerHTML;
			$("iQuantity").value = row.down("td", 4).innerHTML;
			$("iRequestor").value = row.down("td", 3).innerHTML;
			$("departments").value = row.down("td", 5).innerHTML;
			$("iDate").value = row.down("td", 2).innerHTML;
		})

		row.down('a', 0).observe("click", function() {
			cancelCheck = 1;
			saveContent = 1;
			$("iName").disabled = false;
			$("iQuantity").disabled = false;
			$("iRequestor").disabled = false;
			$("departments").disabled = false;
			$("iDate").disabled = false;

			$("iName").value = row.down("td", 1).innerHTML;
			$("iQuantity").value = row.down("td", 4).innerHTML;
			$("iRequestor").value = row.down("td", 3).innerHTML;
			$("departments").value = row.down("td", 5).innerHTML;
			$("iDate").value = row.down("td", 2).innerHTML;
			issueId = row.down("a", 0).innerHTML;
			new Ajax.Request(contextPath + "/issueSupplies", {
				method : "GET",
				parameters : {
					action : "pageOut"
				},
				onComplete : function(response) {
					$("issuedSuppliesTable").update(response.responseText);
					$("btnR").update(response.responseText);
					$("theSearch").update(response.responseText);
					message = "update";
				}
			})
		})

	});
</script>

</html>