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
<title>Admin Login</title>
</head>
<body>
		<div id="loginhead"><h2>Admin Login</h2></div>
			<div id="loginerror"><c:catch var="exception">
			${message}
 			</c:catch>
 			
			<c:if test = "${exception != null}">
			${message}
			</c:if>
		</div>
			
	<form:form action="admin/adminLogin" modelAttribute="admin" method="POST">
		<table id="lgtbl">
			<tbody>
				<tr>
					<td class="email"><label>ID</label></td>
					<td><form:input  path="id" name="id" placeholder="Enter your ID" class="emailbox" required="required"/></td>
				</tr>
				<tr>
					<td class="password"><label>Password</label></td>
					<td><form:password maxlength="20" path="password" name="password" placeholder="Enter your password" class="passwordbox" required="required"/></td>
				</tr>
					<tr>
					<td><input class="loginbutton" type="Submit" value= "Log In"/></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>