<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<meta charset="ISO-8859-1">
<title>${instructor.firstName} - Profile</title>
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
			<c:if test="${img == 'failed'}">
    			<a href="#ppedit" onclick="showForm();"><img class="profilePicProps" src = "${pageContext.request.contextPath}/resources/images/profile-picture.jpg"></a>
			</c:if>
			<c:if test="${img != 'failed'}">
   				<a href="#ppedit" onclick="showForm();"><img class="profilePicProps"src="data:image/jpg;base64,${img}"/>	</a>
			</c:if>
			Hello ${instructor.firstName}
		</div><br>
		<div id="headerLink"><a href="/GoCrafty/home/authentication/logOut">Log Out</a></div>
		<div id="headerLink"><a href="showEditProfile">Edit Profile</a></div>	
		<div id="headerLink"><a href="deleteProfile">Delete Profile</a></div>
		<div id="headerLink"><a href="showAddCourse">Add Course</a></div>
	</div>
	</div>
</header>
<div class ="content">
	<div id = "ppedit" style="visibility: hidden;">
        <form method="post" action="${pageContext.request.contextPath}/home/instructor/doUpload" enctype="multipart/form-data" >
         <table id="upload">
            <tr>
    	        <td><input type="file" name="fileUpload" accept='image/*'  /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Upload" /></td>
            </tr>
         </table>
       	</form>
    </div>				
				
<div id="profileContainer" onclick="hideForm();"> 
		<div id="namelgo">Hello ${instructor.firstName} ${instructor.lastName}, Welcome back!</div>
 		<div id="emaillgo">Your registered email address is: <b>${instructor.email}</b></div>
 		<c:if test="${instructor.recruiter =='1'}">
 			<div id="jobslgo">Your Active Services: 
 				<ol>
 					<li>Your are Recruiting: <b>Active</b> </li>
 				</ol>
 			</div>
 		</c:if>
 		<div id="logslgo">Last Login: ${instructor.logs}</div>		
</div>
	
<div id="courses">
		<div id="courselgo"><h3 align = "center">Your Courses</h3></div>
		<c:forEach var="course" items="${course}">
			<div id="coursedet">
			<a href="${pageContext.request.contextPath}/home/course/showCourseHomeToInstructor?id=${course.getId()}">
						Course Name: ${course.getName()}<br>
						Course Duration:  ${course.getEstimatedTimeToComplete()}<br>
						Category: ${course.getCategory()}
			</a>
			</div><hr>
		</c:forEach> 
</div>
</div>
</c:if><br><br><br>
</body>
</html>