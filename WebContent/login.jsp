<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<script type="text/javascript" src="js/prototype.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>


<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

</head>

<c:if test="${not empty currentUserId}">
	<script>window.location.assign(contextPath + "/pages/home.jsp");</script>
</c:if>

<body>
	<%-- <div id="mainContents">
		User Name: <input type="text" id="userId" /><br />
		Password: <input type="password" id="password" /><br />
		<input type="button" id="loginBtn" value="Login" /><br />
		${error}
	</div> --%>
	
	
	<div id="mainContents" class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-50 p-b-90">
				<span class="login100-form-title p-b-51">
					Login
				</span>

					
				<div class="wrap-input100 validate-input m-b-16" data-validate = "Username is required">
					<input id="userId" class="input100" type="text" name="username" placeholder="User ID">
					<span class="focus-input100"></span>
				</div>
					
				
				<div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
					<input id="password" class="input100" type="password" name="pass" placeholder="Password">
					<span class="focus-input100"></span>
				</div>
				
				<div class="flex-sb-m w-full p-t-3 p-b-24">
					${error}
				</div>

				<div class="container-login100-form-btn m-t-17">
					<button id="loginBtn" class="login100-form-btn">
						Login
					</button>
				</div>
			</div>
		</div>
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