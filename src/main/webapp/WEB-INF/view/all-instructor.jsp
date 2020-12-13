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
<input type="button" onclick="location.href = '${pageContext.request.contextPath}/home/admin/showInstructorForm';" value="Add Instructor"/>
<%-- </c:if> --%>
		<c:forEach var="instructor" items="${instructorList}" ><
			<br><hr>
			Id: ${instructor.getId()}<br>
			First Name: ${instructor.getFirstName()}<br>
			Last name: ${instructor.getLastName()}<br>
			E-Mail: ${instructor.getEmail()}<br>
			<c:if test="${instructor.getRecruiter() == 1}">
			Recruiter: True<br>
			</c:if>
			<c:if test="${instructor.getRecruiter() != 1}">
			Recruiter: False<br>
			</c:if>
			<input type="button" onclick="location.href ='${pageContext.request.contextPath}/home/admin/deleteInstructor?instructorId=${instructor.getId()}'; buttonAnimation();" value="Delete Instructor"/>
		</c:forEach>
</body>
</html>