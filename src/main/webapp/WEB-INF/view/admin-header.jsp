<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Header</title>
</head>
<body>
<br><br>
	<input type="button" onclick="location.href ='${pageContext.request.contextPath}/home/admin/logOut'; buttonAnimation();" value="Logout"/>
	<input type="button" onclick="location.href ='${pageContext.request.contextPath}/home/admin/getStudent'; buttonAnimation();" value="View Students"/>
	<input type="button" onclick="location.href ='${pageContext.request.contextPath}/home/admin/getInstructor'; buttonAnimation();" value="View Instructors"/>
	<input type="button" onclick="location.href ='${pageContext.request.contextPath}/home/admin/getCourse'; buttonAnimation();" value="View Courses"/>
	<input type="button" onclick="location.href ='${pageContext.request.contextPath}/home/admin/showChangePassword'; buttonAnimation();" value="Change Password"/>
</body>
</html>