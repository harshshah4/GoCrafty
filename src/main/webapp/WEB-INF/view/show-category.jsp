<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<link href='https://fonts.googleapis.com/css?family=Fredericka the Great' rel='stylesheet'>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<title>Select Category</title>
</head>
<body >
<c:if test="${tempSession.id == 'temp'}">
<%@ include file="header.jsp" %>
</c:if>
<c:if test="${tempSession.id != 'temp'}">
<%@ include file="student-header.jsp"%>
</c:if>
<c:if test="${tempSession.id == 'temp'}">
<%@ include file="footer.jsp"%>
</c:if>


<div class="content"><br><br>
	<c:forEach var="cat" items="${category}">
		<div id="categories"> 
			<form action = "${pageContext.request.contextPath}/home/course/showCoursesByCategory" method = "post">
			<input type="hidden" value="${cat}" name="category">
			<input type="submit" value="${cat}" class="button-student" ></form>
		</div>
	</c:forEach>
</div>
</body>
</html>