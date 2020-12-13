<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${tempSession.id == 'temp'}">
<%@ include file="header.jsp" %>
</c:if>
<c:if test="${tempSession.id != 'temp'}">
<%@ include file="student-header.jsp"%>
</c:if>

<c:if test="${tempSession.id == 'temp'}">
<%@ include file="footer.jsp"%>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<title>${theCourse.getName()}</title>
</head>
<body><br><br>
<div id="mycoursedesc">
<br><h2>${theCourse.getName()}</h2>
	Course Duration:  ${theCourse.getEstimatedTimeToComplete()}<br>
	Instructor name: ${instructorName.get(String.valueOf(theCourse.getInstructor_id()))}<br>
	Category: ${theCourse.getCategory()}<br>
	Description: ${theCourse.getDescription() }<br>
	<input type="button" class="viewcourse" value="Enroll" onclick="location.href = '${pageContext.request.contextPath}/home/course/enrollCourse?id=${theCourse.getId()}';">
	<br>${message}
</div><br><br><br>
</body>
</html>	