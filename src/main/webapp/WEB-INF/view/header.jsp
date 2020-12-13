<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header>
<div class="overlay">
<div id="leftHeader">
		<a href="${pageContext.request.contextPath}">
		<img src="${pageContext.request.contextPath}/resources/images/logo.png" class="logoprop"></a> 
</div>
<div id="header-links">
	<div id="headerLink"><a id="about1" href = "${pageContext.request.contextPath}/#about" class="smooth-scroll" onclick="scrollSmooth(this.id)">About</a></div>
	<div id="headerLink"><a id="services1" href = "${pageContext.request.contextPath}/#services" class="smooth-scroll" onclick="scrollSmooth(this.id)">Services</a></div>
	<div id="headerLink"><a id="contact1" href = "${pageContext.request.contextPath}/#contactus" class="smooth-scroll" onclick="scrollSmooth(this.id)">Contact Us</a></div>
	<div id="headerLink">
	<c:if test="${tempSession.id == 'temp'}">
		<a href = "${pageContext.request.contextPath}/home/course/showCategories?recruitment=1">Browse Course Catalog</a>
	</c:if>
	</div>
</div>
</div>
</header>
</body>
</html>