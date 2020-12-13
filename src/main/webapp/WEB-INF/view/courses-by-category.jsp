<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${tempSession.id == 'temp'}">
<%@ include file="footer.jsp"%>
</c:if>

<c:if test="${tempSession.id == 'temp'}">
<%@ include file="header.jsp" %>
</c:if>
<c:if test="${tempSession.id != 'temp'}">
<%@ include file="student-header.jsp"%>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<title>Courses</title>
</head>
<body>
<br><br>
	<c:forEach var="theCourse" items="${course}">
	<div id="mycourse">
	<br>Course Name: ${theCourse.getName()}<br>
	Estimated time to complete:  ${theCourse.getEstimatedTimeToComplete()}
	<br>
	Instructor name: ${instructor.get(String.valueOf(theCourse.getInstructor_id()))}
	<input type="button" class="viewcourse" value="View Course" onclick="location.href = '${pageContext.request.contextPath}/home/course/viewCourseDescription?id=${theCourse.getId()}';">
	</div>
	</c:forEach>

</body>
</html>