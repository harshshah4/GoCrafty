<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp" %>
<%@ include file="footer.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href = "${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<title>User Login</title>
</head>
<body>
	<div id = "login-container">
		<div id="loginhead">User Login</div>
			<div id="loginerror"><c:catch var="exception">
			${message}
 			</c:catch>
 			
			<c:if test = "${exception != null}">
			${message}
			</c:if>
		</div>
<form:form action="${role}/login" modelAttribute="theUser" method="POST">
		<table id="lgtbl">
			<tbody>
				<tr>
					<td class="email"><label>Email</label></td>
					<td><form:input  path="email" name="email" placeholder="Enter your e-mail" class="emailbox" required="required"/></td>
				</tr>
				<tr>	
					<td class="password"><label>Password</label></td>
					<td><form:password maxlength="20" path="password" name="password" placeholder="Enter your password" class="passwordbox" required="required"/></td>
				</tr>
					<tr>	
					<td><input class="loginbutton" type="Submit" value= "Log In"/></td>
<<<<<<< HEAD:src/main/webapp/WEB-INF/view/user_login_form.jsp
				</tr>
				<tr>
					<td class="linklog"><a href = "student/addStudent">Create an account</a></td>
=======
					<td class="linklog"><a href = "user/addUser">Create an account</a></td>
>>>>>>> fc1f222ce70cf6b4bd0402e41f1dd2aa34dc4946:src/main/webapp/WEB-INF/view/user-login-form.jsp
				</tr>
			</tbody>
		</table>
		</form:form>
	</div>
</body>
</html>