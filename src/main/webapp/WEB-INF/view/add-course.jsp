<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="instructor-header.jsp" %>
<%-- <%@ include file="footer.jsp" %> --%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<meta charset="UTF-8">
<title>Add New Course</title>
</head>
<body><br><br>
	<div id="signup_form">

	<form:form action="${pageContext.request.contextPath}/home/course/addCourse" modelAttribute="course" method="post">
		<div id="leftSgn"><label>Course Name </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="name" required="required"/></div>
		<br>
		<div id="leftSgn"><label>Description </label></div>
		<div id="rightSgn"><form:textarea id="forminputbox" path="description" required="required"/></div>
		<br>
		<div id="leftSgn"><label>Course Duration <i>(Enter in Weeks. For example, "4 Weeks")</i> </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="estimatedTimeToComplete" required="required"/></div>
		<br>
		<div id="leftSgn"><label>Category </label></div>
		<div id="rightSgn">
			<form:select id="category" path="category" required="required">
				<form:option id="category" value="Drawing" label="Drawing"/>
				<form:option id="category" value="Painting" label="Painting"/>
				<form:option id="category" value="Calligraphy" label="Calligraphy"/>
				<form:option id="category" value="Sketching" label="Sketching"/>
				<form:option id="category" value="Portraiture" label="Portraiture"/>
				<form:option id="category" value="Water Color" label="Water Color"/>
				<form:option id="category" value="Pencil Drawing" label="Pencil Drawing"/>
				<form:option id="category" value="Oil Painting" label="Oil Painting"/>
				<form:option id="category" value="Illustrations" label="Illustrations"/>
				<form:option id="category" value="Art for Kids" label="Art for Kids"/>
				<form:option id="category" value="Crafting" label="Crafting"/>
				<c:if test="${tempSession.recruitement == 1}">
				<form:option id="category" value="Recruitment" label="Recruitment"/>
				</c:if>
		   	</form:select>
		</div>
		<br>
		<div id="leftSgn"><label>Instructor ID </label></div>
		<c:if test="${tempSession.status != 'admin'}">
			<div id="rightSgn"><form:input id="forminputbox" value="${userId}" path="instructor_id" readonly="true" required="required"/></div>
		</c:if>
		<c:if test="${tempSession.status == 'admin'}">
			<div id="rightSgn"><form:input id="forminputbox" value="${userId}" path="instructor_id" readonly="false" required="required"/></div>
		</c:if>
		<div id="leftSgn"><label>Video Link </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="videoLink" required="required"/></div>
		<div id="leftSgn"><label>Quiz Link </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="quizLink" required="required"/></div>
		<div id="leftSgn"><label>Response Link </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="responseLink" required="required"/></div>
		<div id="leftSgn"><input class="viewcourse" type="submit" value="Create Course"></div>
	</form:form> 
		</div><br><br><br><br>
</body>
</html>