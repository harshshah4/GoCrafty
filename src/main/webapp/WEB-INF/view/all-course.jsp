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
<title>All Instructor</title>
</head>
<body>
<%@ include file="admin-header.jsp"%>
<%-- <c:if test="${tempSession.id != 'temp'}"> --%>
<input type="button" onclick="location.href ='${pageContext.request.contextPath}/home/admin/showCourseForm'; buttonAnimation();" value="Add Course"/>
<%-- </c:if> --%>	

<c:forEach var="course" items="${courseList}">
<%-- 	${user.profilePic()} --%>
	<br>Name: ${course.getName()}
	<br>Description: ${course.getDescription()}
	<br>Estimated time to complete: ${course.getEstimatedTimeToComplete()}
	<br>Category: ${course.getCategory()}
	<br>Video Link: ${course.getVideoLink()}
	<br>Quiz Link: ${course.getQuizLink()}
	<br>Response Link: ${course.getResponseLink()}
	<br><input type="button" value="Delete Course" onclick="location.href = '${pageContext.request.contextPath}/home/admin/deleteCourse?courseId=${course.getId()}'; buttonAnimation();">
	<hr>
</c:forEach>
<%@ include file="footer.jsp" %>
</body>
</html>