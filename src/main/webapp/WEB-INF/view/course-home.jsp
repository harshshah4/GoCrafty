<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<meta charset="ISO-8859-1">
<title>${course.name}</title>
</head>
<body>
<c:if test="${tempSession.id != 'temp'}">	
<header>
<div class="overlay">
<div id="leftHeader">
		<a href="${pageContext.request.contextPath}/home/course/showCourseHomeToInstructor">
		<img class="logoprop"src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
</div>
	<div id="header-links">
	<div id="profilePic">
		<a href="${pageContext.request.contextPath}/home/instructor/viewProfile">Hello ${tempSession.firstName}</a>
	</div>
		<div id="headerLink"><a href="${pageContext.request.contextPath}/home/course/deleteCourse">Delete Course</a></div>
		<div id="headerLink"><a href = "${pageContext.request.contextPath}/home/course/showModifyCourse">Modify Course</a></div>
	</div>
	</div>
</header>
<div class = "content">
		<div id = "deleteForm" style="visibility: hidden">
			<form action="deleteCourse" method="get">
				<label>Password</label>
				<input id="formd" type="password" name = "password">
				<input class="mycourse" type="submit" value="Confirm">
			</form>
		</div>
	
		<div id="courseContainer" onclick="hideForm();">
			<h2 align = "center">Welcome to the ${course.name}</h2>
				<div id="course-analytics">
				<h3 align = "center">COURSE ANALYTICS</h3><br>
					Course Description: ${course.description}<br>
					Course Duration: ${course.estimatedTimeToComplete}<br>
					Category: ${course.category}<br><br>
 			</div>
 			<div id="rightContainer">
 				<table>
 				<tbody>
 				<tr><td>Percentage</td><td>Name@Email</td></tr>
 				<c:forEach var="student" items="${students}">

 				<tr><td>${student.key}</td> <td>${student.value}</td></tr>

 				${student.key} <br> 
 				</c:forEach>
 				</tbody>
 				</table>
 			</div>
 		</div>				
 </div>
</c:if><br><br>
</body>
</html>