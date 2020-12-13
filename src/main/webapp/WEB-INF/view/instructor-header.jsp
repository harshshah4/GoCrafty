<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<link href='https://fonts.googleapis.com/css?family=Fredericka the Great' rel='stylesheet'>
<meta charset="ISO-8859-1">
</head>
<body>
<header>	
<div class="overlay">
<div id="leftHeader">
		<a href="${pageContext.request.contextPath}/home/instructor/viewProfile">
		<img class="logoprop"src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
</div>
<div id="header-links">
	<div id="headerLink"><a href="${pageContext.request.contextPath}/home/instructor/viewProfile">Profile</a></div>
	<div id="headerLink"><a href="${pageContext.request.contextPath}/home/authentication/logOut">Log Out</a></div>
<%-- 	<div id="headerLink"><a href="${pageContext.request.contextPath}/home/course/addCourse">Add Course</a></div> --%>
</div>
</div>
</header>
</body>
</html>