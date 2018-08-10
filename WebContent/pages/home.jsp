<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Home</title>

<script type="text/javascript" src="../js/prototype.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>

<c:if test="${empty currentUserId}">
	<script>
		window.location.assign(contextPath);
	</script>
</c:if>

<!-- code para sa module ni lana, redirect kung first time login -->

<jsp:include page="header.jsp" />
<body>
	<h3><c:out value="Hello ${currentUserFN}!" /></h3>

	<c:if test="${currentAccessLevel == 'U'}">
		<script>
			$('maintenance').hide();
			$('reports').hide();
		</script>
	</c:if>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>