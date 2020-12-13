<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ include file="instructor-header.jsp"%> --%>
<%-- <%@ include file="footer.jsp"%> --%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<meta charset="ISO-8859-1">
<title>${course.name} Modify Course</title>
</head>
<body>
<c:if test="${tempSession.id != 'temp'}">	
<header>
<div class="overlay">
	<div id="leftHeader">
		<a href="${pageContext.request.contextPath}/home/instructor/viewProfile">
		<img class="logoprop"src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
	</div>
	<div id="header-links">
		<div id="profilePic">
			<a href="${pageContext.request.contextPath}/home/instructor/viewProfile">Hello ${tempSession.firstName}</a>
		</div>
		<div id="headerLink"><a href="${pageContext.request.contextPath}/home/course/showCourseHomeToInstructor"></a></div>
	</div>
	</div>
</header>
<div class="content" onclick="hideForm();"><br><br>
	<div class="modify-course-left">
		<form:form action="${pageContext.request.contextPath}/home/course/modifyCourse" modelAttribute="course" method="post">
		<div id="leftSgn"><label>Course Name </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="name" required="required" value="${course.name}"/></div>
		<div id="leftSgn"><label>Course Description </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="description" required="required" value="${course.description}"/></div>
		<div id="leftSgn"><label>Course Duration </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="estimatedTimeToComplete" required="required" value="${course.estimatedTimeToComplete}"/></div>
		<div id="leftSgn"><label>Category </label></div>
		<div id="rightSgn">
			<form:select id="category" path="category" required="required" value="${course.category}">
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
		   	</form:select>
		</div>
		<div id="leftSgn"><input class="viewcourse" type="submit" value="Modify Course"></div>
		</form:form>
	</div><br><br>
	
	<div class="modify-course-center">
	<button onClick="showVideoUploadForm();" value="addVideos" class="viewcourse">Add Video</button>
		<button onClick="showQuizUploadForm();" value="addQuiz" class="viewcourse">Add Quiz</button>
	</div>
	
	<c:if test="${videoListSize == '0'}">
	<div class="modify-course-center">
		Get started and upload your first video to unlock more of the GoCrafty!
	</div>
	</c:if>
	
	<c:if test="${videoListSize != '0'}">
	<div class="modify-course-center">
			<form action = "modifyVideos" method = "post">
				<c:forEach items="${videoList}" var="video">
				<%-- <iframe width="560" height="315" src="https://www.youtube.com/embed/${embededLink}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe> --%>
				
				<label>Video Name</label>
				<input type="text" name="videoName" value="${video.key}"/><br>
				<label>Video URL</label>
				<input type="text" name="videoURL" value="${video.value}"/><br>
				</c:forEach>
			</form>
	 </div>
	 </c:if>
	 
	 <c:if test="${quizListSize == '0'}">
	<div class="modify-course-center">
		Get started and upload your first Quiz to unlock more of the GoCrafty!
	</div>
	</c:if>
	
	<c:if test="${quizListSize != '0'}">
	<div class="modify-course-right">
			<form action = "modifyQuiz" method = "post">
				<c:forEach items="${quizList}" var="quiz">
					<c:set var="count" value="0" scope="page" />
					<label>Quiz Name</label>
					<input type="text" name="quizName" value="${quiz.key}"/><br>
					<label>Quiz URL </label>
					<input type="text" name="quizURL" value="${quiz.value}"/><br>
					<label>Response URL </label>
					<input type="text" name="responseURL" value="${responseList[count]}"/><br>
					<c:set var="count" value="${count + 1}" scope="page"/>
				</c:forEach>
			</form>
	</div>
	</c:if>
</div>
</c:if><br><br><br>
</body>
</html>