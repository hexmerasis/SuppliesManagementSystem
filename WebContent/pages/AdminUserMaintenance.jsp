<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Maintenance - Admin</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
<script src="./js/prototype.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}"
</script>
</head>
<body>

	<div id="mainContents">
		<table>
			<tr>
				<td><label>User ID: </label></td>
				<td><input type="text" id="txtUserId"></td>
				<td id="changePw"></td>
			</tr>
			<tr>
				<td><label>First Name: </label></td>
				<td><input type="text" id="txtFirstName"></td>
				<td></td>
				<td id="replWBtn"><input type="button" id="btnAdd"
					value="Add Record"></td>

			</tr>
			<tr>
				<td><label>Last Name: </label></td>
				<td><input type="text" id="txtLastName"></td>
				<td></td>
				<td id="replWCncl"><input type="button" id="btnSave"
					value="Save"></td>
			</tr>

			<tr>
				<td><label>Middle Initial: </label></td>
				<td><input type="text" id="txtMI"></td>
			</tr>
			<tr>
				<td><label>Email: </label></td>
				<td><input type="text" id="txtEmail"></td>
			</tr>
		</table>
			<table>
				<tr>
					<td><label>Active Tag: </label></td>
					<td><input name="radioActiveTag" id="radioActiveTagY"
						type="radio" value="Y" checked="checked">Yes &nbsp; &nbsp;
						<input name="radioActiveTag" id="radioActiveTagN" type="radio"
						value="N">No</td>
				</tr>
				<tr>
					<td><label>Access Level: </label></td>
					<td><select id="optAccessLevel">
							<option value=""></option>
							<option value="User">User</option>
							<option value="Administrator">Administrator</option>
					</select></td>
				</tr>

				<tr>
					<td><input id=lastUserTxt type="hidden"
						value="${currentUserId}"></td>
				</tr>

				<tr id=searchField>
					<td>Search:</td>
					<td><input id=searchTxt type="text"><input type=button
						value=Search id=searchBtn></td>
				</tr>
				<tr id=searchField>
					<td colspan=2 id=searchResult><label id="noResults"></label></td>
				</tr>
			</table>

			<!-- Results Table -->
			<table id="tableDiv">
				<tr>
					<th><b>User ID</b></th>
					<th><b>First Name</b></th>
					<th><b>Last Name</b></th>
					<th><b>M.I.</b></th>
					<th><b>Email Address</b></th>
					<th><b>Active Tag</b></th>
					<th><b>Access Level</b></th>
					<th><b>Entry Date</b></th>
					<th><b>Last Login</b></th>
					<th><b>Last User</b></th>
					<th><b>Last Update</b></th>
				</tr>
				<c:forEach var="usr" items="${userList}">
					<tr class="tableRow" id="row${usr.userId}">
						<td><a class="updateUser" id="${usr.userId}"
							title="${usr.userId}" href="#"> <c:out value="${usr.userId}"></c:out>
						</a></td>
						<td><c:out value="${usr.firstName}"></c:out></td>
						<td><c:out value="${usr.lastName}"></c:out></td>
						<td><c:out value="${usr.middleInitial}"></c:out></td>
						<td><c:out value="${usr.email}"></c:out></td>
						<td><c:out value="${usr.activeTag}"></c:out></td>
						<td><c:out value="${usr.accessLevel}"></c:out></td>
						<td><c:out value="${usr.entryDate}"></c:out></td>
						<td><c:out value="${usr.lastLogin}"></c:out></td>
						<td><c:out value="${usr.lastUser}"></c:out></td>
						<td><c:out value="${usr.lastUpdate}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
			<!-- End of Results Table -->
	</div>
</body>

<script>
	$("lastUserTxt").value = "Admin"
	var id = 0;
	var currentRow = -1;
	var rec = [];
	var addObj = {};
	var userIds = [];
	var hasKeyword = false;
	var keywordIdx = 0;

/* 	function keepUserIds() {
		var ctr = 0;
		$$(".updateUser").each(function(r) {
			var userId = r.readAttribute("id")
			userIds[ctr] = userId.toLowerCase();
			ctr = ctr + 1;
		});
	}

	keepUserIds(); */
	
	$("searchBtn").observe("blur", function() {
		window.location.reload();
	});
	
	$("searchBtn").observe("click", function() {
		search();
	});
	
	$("optAccessLevel").observe("change", function() {
		if ($F("optAccessLevel") == "Administrator") {
			$("accessLvlTxt").value = "A";
		} else if ($F("optAccessLevel") == "User") {
			$("accessLvlTxt").value = "U";
		} else {
			$("accessLvlTxt").value = "";
		}
	})

	if ('${userList}' == "[]") {
		var resultString = "<tr> <td colspan=11 style='text-align:center'> No results found. </td></tr>";
		$("tableDiv").insert({
			bottom : resultString
		});
	}

	$("btnAdd").observe("click", function() {
		addRecord();
	});

	$("btnSave").observe("click", function() {
		saveRecord();
	});

	

	$$(".updateUser").each(function(r) {
		var userId = r.readAttribute("id")
		$(userId).observe("click", function() {
			var activeTxtString = "<input type='hidden' id='activeTagTxt'>";
			$("replWCncl").insert({
				bottom : activeTxtString
			});

			var accessLvlString = "<input type='hidden' id='accessLvlTxt'>";
			$("replWCncl").insert({
				bottom : accessLvlString
			});

			displaySelected(userId);
			showHideElements();
		});
	})


	function cancelUpdate() {
		new Ajax.Request(contextPath, {
			method : "POST",
			onComplete : function(response) {
				$("mainContents").update(response.responseText);
			}
		});
	}

	function showHideElements() {
		$("tableDiv").remove();
		$("btnAdd").remove();
		$("btnSave").remove();
		$("searchField").remove();

		var changePwBtnStr = "<a href='#' id='changePwBtn'>Change Password</a>";
		$("changePw").insert({
			bottom : changePwBtnStr
		});

		$("changePwBtn").observe("click", function() {
			changePassword();
		});

		var updateBtnString = "<input type='button' id='btnUpdate' value='Update'>";
		$("replWBtn").insert({
			bottom : updateBtnString
		});
		var cancelBtnString = "<input type='button' id='btnCancel' value='Cancel'>";
		$("replWCncl").insert({
			bottom : cancelBtnString
		});

		$("btnUpdate").observe("click", function() {
			updateRecord();
		});

		$("btnCancel").observe("click", function() {
			cancelUpdate();
		});

		$("radioActiveTagY").observe("click", function() {
			$("activeTagTxt").value = "Y";
		})
		$("radioActiveTagN").observe("click", function() {
			$("activeTagTxt").value = "N";
		})
	}

	function displaySelected(userId) {
		$("txtUserId").value = userId;
		$("txtFirstName").value = $("row" + userId).down("td", 1).innerHTML;
		$("txtLastName").value = $("row" + userId).down("td", 2).innerHTML;
		$("txtMI").value = $("row" + userId).down("td", 3).innerHTML;
		$("txtEmail").value = $("row" + userId).down("td", 4).innerHTML;
		if ($("row" + userId).down("td", 5).innerHTML == "Y") {
			$("radioActiveTagY").checked = true;
		} else {
			$("radioActiveTagN").checked = true;
		}

		if ($("radioActiveTagY").checked) {
			$("activeTagTxt").value = "Y";
		} else {
			$("activeTagTxt").value = "N";
		}

		if ($("row" + userId).down("td", 6).innerHTML == "A") {
			$("optAccessLevel").value = "Administrator";
		} else if ($("row" + userId).down("td", 6).innerHTML == "U") {
			$("optAccessLevel").value = "User";
		} else {
			$("optAccessLevel").value = "";
		}

		if ($F("optAccessLevel") == "Administrator") {
			$("accessLvlTxt").value = "A";
		} else if ($F("optAccessLevel") == "User") {
			$("accessLvlTxt").value = "U";
		} else {
			$("accessLvlTxt").value = "";
		}

	}

	function addRecord() {
		var isNull = false;
		$w("txtUserId txtFirstName txtLastName txtMI txtEmail optAccessLevel")
				.each(function(c) {
					if ($F(c) == null || $F(c) == "") {
						isNull = true;
					}
				})
		if (isNull) {
			alert("Fields cannot be null");
			return false;
		}

		addObj.userId = $F("txtUserId");
		addObj.firstName = $F("txtFirstName");
		addObj.lastName = $F("txtLastName");
		addObj.middleInitial = $F("txtMI");
		addObj.email = $F("txtEmail");

		if ($("radioActiveTagY").checked) {
			addObj.activeTag = "Y";
		} else {
			addObj.activeTag = "N";
		}

		if ($F("optAccessLevel") == "Administrator") {
			addObj.accessLevel = "A";
		} else if ($F("optAccessLevel") == "User") {
			addObj.accessLevel = "U";
		} else {
			addObj.accessLevel = "";
		}

		addObj.lastLogin = "";
		addObj.lastUser = $F("lastUserTxt");
		addObj.lastUpdate = "";

		var addTable = $("tableDiv");
		var newTr = new Element("tr");
		var content = addContent(addObj);

		newTr.setAttribute("name", "rowname");
		newTr.setAttribute("id", "rowid" + id);
		newTr.addClassName("tableRow");

		newTr.update(content);
		addTable.insert({
			bottom : newTr
		});

		rec.push(addObj);
		id = id + 1;
		clearFields();
	}

	function addContent(obj) {
		var content = '<input type="hidden" id="id" value="'+ id +'">' + '<td>'
				+ obj.userId + '</td>' + '<td>' + obj.firstName + '</td>'
				+ '<td>' + obj.lastName + '</td>' + '<td>' + obj.middleInitial
				+ '</td>' + '<td>' + obj.email + '</td>' + '<td>'
				+ obj.activeTag + '</td>' + '<td>' + obj.accessLevel + '</td>'
				+ '<td>' + obj.lastLogin + '</td>' + '<td>' + obj.lastUser
				+ '</td>' + '<td>' + obj.lastUpdate + '</td>';

		return content;
	}

	function clearFields() {
		$("txtUserId").value = "";
		$("txtFirstName").value = "";
		$("txtLastName").value = "";
		$("txtMI").value = "";
		$("txtEmail").value = "";
		$("optAccessLevel").value = "";
	}

	$w("txtUserId").each(function(e) {
		$(e).observe("change", function() {
			validateFields();
		});
	});

	function validateFields() {
		$$(".tableRow").each(function(r) {
			if (r.down("td", 0).innerHTML == $F("txtUserId")) {
				$("txtUserId").clear();
				alert("User ID already exists!");
			}
		});
	}

	function saveRecord() {
		new Ajax.Request(contextPath + "/saveRecord", {
			method : "POST",
			parameters : {
				action : "insertRecord",
				userId : addObj.userId,
				password : addObj.userId,
				firstName : addObj.firstName,
				lastName : addObj.lastName,
				middleInitial : addObj.middleInitial,
				email : addObj.email,
				activeTag : addObj.activeTag,
				accessLevel : addObj.accessLevel,
				lastLogin : addObj.lastLogin,
				lastUser : addObj.lastUser,
				lastUpdate : addObj.lastUpdate
			},
			onComplete : function(response) {
				alert("Record Added!");
				window.location.reload();
			}
		});
	}

	/* UPDATE SCRIPT */
	function updateRecord() {
		var isNull = false;
		$w("txtUserId txtFirstName txtLastName txtMI txtEmail optAccessLevel")
				.each(function(c) {
					if ($F(c) == null || $F(c) == "") {
						isNull = true;
					}
				})
		if (isNull) {
			alert("Fields cannot be null");
			return false;
		}

		new Ajax.Request(contextPath + "/updateRecord", {
			method : "POST",
			parameters : {
				action : "updateRecord",
				userId : $F("txtUserId"),
				firstName : $F("txtFirstName"),
				lastName : $F("txtLastName"),
				middleInitial : $F("txtMI"),
				email : $F("txtEmail"),
				activeTag : $F("activeTagTxt"),
				accessLevel : $F("accessLvlTxt"),
				lastUser : $F("lastUserTxt")
			},
			onComplete : function(response) {
				$("txtUserId").value = "";
				$("txtFirstName").value = "";
				$("txtLastName").value = "";
				$("txtMI").value = "";
				$("txtEmail").value = "";
				$("radioActiveTagY").writeAttribute("checked", "checked");
				$("optAccessLevel").value = "";
				$("lastUserTxt").value = "";
				alert("Record Updated!");
				window.location.reload();
			}
		});
	}

	function changePassword() {
	/* 	window.location.assign(contextPath+"/changepass"); */
		new Ajax.Request(contextPath + "/changepass", {
			method : "GET",
			parameters : {
				userId : $F("txtUserId")
			},
			onComplete : function(response) {
				alert(response.responseText);
				$("mainContents").update(response.responseText);
			}
		}); 
	}

	function search() {
		new Ajax.Request(contextPath + "/searchUser", {
			method : "POST",
			parameters : {
				action : "searchUser",
				userId : $F("searchTxt")
			},
			onComplete : function(response) {
				$("mainContents").update(response.responseText);
			}
		});
	}
</script>
</html>