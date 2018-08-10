<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<script type="text/javascript" src="js/prototype.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>

<c:if test="${not empty currentUserId}">
	<script>window.location.assign(contextPath + "/pages/home.jsp");</script>
</c:if>

<body>
	<div id="mainContents">
		User Name: <input type="text" id="userId" /><br />
		Password: <input type="password" id="password" /><br />
		<input type="button" id="loginBtn" value="Login" /><br />
		${error}
	</div>
</body>
<script>
	$('loginBtn').observe('click', function(){
		login();
	});
	
	function login(){
		new Ajax.Request(contextPath + "/login", {
			method: "POST",
			parameters: {
					userId: $F('userId'),
					password: $F('password')
			},
			onComplete: function(response){
				if(response.status == 201){
					window.location.assign(contextPath + "/pages/home.jsp");	
				}
				else{
					$('mainContents').update(response.responseText);
				}
			}
		});
	}
</script>
</html>