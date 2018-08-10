<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<script src="../js/prototype.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}"
</script>
				<c:if test="${currentAccessLevel == 'U'}">
					<c:set var="uid" value="${currentUserId}"/>
					<c:set var="page" value="pages/UserUserMaintenance.jsp"/>
				</c:if>	
					
				<c:if test="${currentAccessLevel == 'A'}">
					<c:set var="uid" value="${changePassUserId}"/>
					<c:set var="page" value="userMaintenance"/>
				</c:if>
</head>
<body>
	<div id="mainContents">
		<table>
			<tr>
				<td><input type="hidden" id="txtLoggedIn"
					value="${currentUserId}"></td>
			</tr>
			<tr>
				<td>

				<input type="text" id="txtUserId" value="${uid}">
				</td>
			</tr>
			<tr>
				<td><label>Current Password: </label></td>
				<td><input type="text" id="txtCurrentPass"></td>
			</tr>

			<tr id=newpassRow>
				<td><label>New Password: </label></td>
				<td><input type="text" id="txtNewPass" maxlength=20></td>
				<td id=validate style="color:red"></td>
			</tr>

			<tr id="retypeRow">
				<td><label>Re-type Password: </label></td>
				<td><input type="text" id="txtRetypePass" maxlength=20></td>
				<td id=warning class="hidden" style="color:red">Password do not match</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="button" id="updatePassBtn" value="Save">
				<a href=${page} }>Cancel</a></td>
			</tr>
		</table>
	</div>
</body>

<script>

	$("txtNewPass").observe(
			"keyup",
			function() {
				if ($F("txtNewPass").length < 8) {
					$("validate").update(
							"Password should have a minimum of 8 characters.")
				} else if ($F("txtNewPass").length > 19) {
					$("validate").update(
							"Password should have a max. of 20 characters.")
				} else {
					if ($F("txtUserId")==$F("txtNewPass")) {
						$("validate").update(
						"Password should not be the same as User ID.")
					} else {
						$("validate").update("");
					}
					
				}
			});

	$("txtRetypePass").observe("keyup", function() {
		if ($F("txtRetypePass") != $F("txtNewPass")) {
			$("warning").removeClassName("hidden");
		} else {
			$("warning").addClassName("hidden");
		}
	});

	$("updatePassBtn").observe("click", function() {
		if ($("newpassRow").down("td", 2).innerHTML != ""){
			alert("Character length is invalid. Re-enter new password.");
		} else if($("warning").hasClassName("hidden")==false ){
			alert("Password do not match.");
		}else if($F("txtUserId")==$F("txtNewPass")){
			alert("Password cannot be same as User ID!");
		}else{
			alert($F("txtUserId"));
			if ($F("txtCurrentPass")!='${passwordList}') {
				alert("Wrong password");
			}else{
				updatePassword();
			}
		
		}
	})
	
	
	function updatePassword() {
		new Ajax.Request(contextPath + "/updatePassword", {
			method : "POST",
			parameters : {
				action : "updatePasswrd",
				userId : $F("txtUserId"),
				currentPass : $F("txtCurrentPass"),
				newPass : $F("txtRetypePass"),
				lastUser: $F("txtLoggedIn")
			},
			onComplete : function(response) {
				$("txtCurrentPass").value = "";
				$("txtNewPass").value = "";
				$("txtRetypePass").value = "";
				alert("Password Changed!");
				window.location.reload();
			}
		});
	}
</script>
</html>