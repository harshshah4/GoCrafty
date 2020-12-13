<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ include file="header.jsp" %> --%>
<%@ include file="footer.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href = "${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<title>Change Password</title>
</head>
<body>
	<div id="loginhead"><h2>Change Password</h2></div>
	<form action="${pageContext.request.contextPath}/home/admin/changePassword">
	Previous Password: <input type="password" name="previousPassword"/>
	<br>New Password: <input type="password" name="newPassword"/>
	<br>Confirm Password: <input type="password" name="confirmPassword"/> 
	<br><input type="submit" value="Submit"/>
	</form>
</body>
</html>