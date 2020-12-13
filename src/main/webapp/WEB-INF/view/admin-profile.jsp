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
<title>Profile</title>
</head>
<body>
<c:if test="${tempSession.id != 'temp'}">
</c:if>
<div id="loginhead"><h2>Hello ${adminlist.name}</h2></div>
<%@ include file="admin-header.jsp" %>
<c:catch var="exception">
			<h2>${message}</h2>
 			</c:catch>
			<c:if test = "${exception != null}">
			<h2>${message}</h2>
			</c:if>
Kindly click on any of the buttons above to perform ADD/DELETE/VIEW - Student/Instructor/Course <br>
You can also change password using Change Password button. We recommend you to do it for the first time.
</body>
</html>