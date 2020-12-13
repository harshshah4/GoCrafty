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
<%-- <c:if test="${tempSession.id != 'temp'}"> --%>
<%@ include file="admin-header.jsp"%>
<input type="button" onclick="location.href = '${pageContext.request.contextPath}/home/admin/showStudentForm';" value="Add Student"/>
<hr>

<c:forEach var="student" items="${studentList}">
<%-- 	${student.profilePic()} --%>
	<br>ID: ${student.getId()}
	<br>Name: ${student.getFirstName()} &nbsp; ${student.getLastName()}
	<br>Email: ${student.getEmail()}
	<c:if test="${student.getApplyForJob() == 1}">
	<br>Apply for Job: True
	</c:if>
	<c:if test="${student.getApplyForJob() != 1}">
	<br>Apply for Job: False
	</c:if>
	<br>Birth Date: ${student.getBirthDate()}
	<br><input type="button" value="Delete Student" onclick="location.href = '${pageContext.request.contextPath}/home/admin/deleteStudent?studentId=${student.getId()}'; buttonAnimation();">
	<hr>
</c:forEach>
</body>
</html>