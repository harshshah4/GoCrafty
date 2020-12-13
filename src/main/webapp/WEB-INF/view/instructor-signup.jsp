<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>
<%@ include file="footer.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<meta charset="UTF-8">
<title>Create an account</title>
</head>
<body>
	<br><br>
	<div id="signup_form">
	<form:form action="${pageContext.request.contextPath}/home/instructor/createAccount" modelAttribute="instructor" method="post">
		<div id="leftSgn"><label>First Name: </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="firstName" required="required"/></div>
		<br>
		<div id="leftSgn"><label>Last Name: </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="lastName" required="required"/></div>
		<br>
		<div id="leftSgn"><label>E-mail: </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="email" required="required"/></div>
		<br>
		<div id="leftSgn"><label>Password: </label></div>
		<div id="rightSgn"><form:password id="forminputbox" path="password" required="required"/></div>
		<br>
		<div id="leftSgn"><label>Recruiter </label></div>
		<div id="rightSgn"><form:checkbox path="recruiter" value="1" /></div>
		
		<div id="leftSgn"><input class="viewcourse" type="submit" value="Create Account"></div>
	</form:form> 
	</div><br><br><br><br><br><br><br>
</body>
</html>