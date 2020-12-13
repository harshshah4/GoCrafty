<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="instructor-header.jsp"%>
<%-- <%@ include file="footer.jsp"%> --%>

<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
</head>
<body>
<div class="content" onclick="hideForm();"><br><br>
	<div class="edit-profile-form">
				<form action="editProfile" method="post"> 
				<div id="fnamelgo"><label>First Name: </label></div>
				<div id="fnamebox"><input id="formd" type="text" name="firstName" value="${instructor.firstName}"/></div>
				<div id="lnamelgo"><label>Last Name: </label></div>
				<div id="lnamebox"><input id="formd"type="text" name="lastName" value="${instructor.lastName}"/></div>
				<div id="passwordlgo"><label>Password: </label></div>
				<div id="passwordbox"><input id="formd"type="password" name="password" value="${instructor.password}"/></div>
				<div class="updatebutton"><input class="viewcourse" type="submit" value = "Update Profile"></div>
				</form> 
				</div>
</div>
</body>
</html>