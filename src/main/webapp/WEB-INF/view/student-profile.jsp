<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ include file="footer.jsp" %>  --%>
   
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<meta charset="ISO-8859-1">
<title>${student.firstName} - Profile</title>
</head>
<body>
<c:if test="${tempSession.id != 'temp'}">	
<header>
<div class="overlay">
	<div id="leftHeader">
		<a href="${pageContext.request.contextPath}/home/student/viewProfile">
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
			Hello ${student.firstName}
		</div><br>
		<div id="headerLink"><a href="/GoCrafty/home/authentication/logOut">Log Out</a></div>
		<div id="headerLink"><a href="showEditProfile">Edit Profile</a></div>
		<div id="headerLink"><a href="deleteProfile?role=student" >Delete Profile</a></div>
		<div id="headerLink"><a href = "${pageContext.request.contextPath}/home/course/showCategories?recruitment=${tempSession.recruitment}">Browse Course Catalog</a></div>
	</div>
</div>
</header>
<div class = "content" onclick="hideForm();">
<div id = "ppedit" style="visibility: hidden;">
        		<form method="post" action="${pageContext.request.contextPath}/home/student/doUpload" enctype="multipart/form-data" >
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
				<div id="namelgo">Hello ${student.firstName} ${student.lastName}, Welcome back!</div>
 				<div id="emaillgo">Registered Email: <b>${student.email}</b> </div>
 				<c:if test="${student.applyForJob =='1'}">
 					<div id="jobslgo">Your Active Services: 
 						<ol>
 							<li>Apply For Jobs: <b>Active</b> </li>
 						</ol>
 					</div>
 				</c:if>
 				<div id="birthlgo">Birthday: <b>${student.birthDate}.</b><br> 
 				<i>Get exciting offers from team GoCrafty on your Birthday! </i></div>
 				<div id="logslgo">Last Login: ${student.logs}</div>
 		</div>
		<div id="courseContainer-student">Your Enrolled Courses
			<c:if test="${enrolledCourses.size()!=0 }">
		<c:forEach var="i" begin="0" end="${enrolledCourses.size()-1}" >
				<a href="${pageContext.request.contextPath}/home/course/course-home-student?courseId=${enrolledCourses.get(i).getId()}&vId=1&certificate='no'">

					<br>Course Name: ${enrolledCourses.get(i).getName()}<br>
						Estimated time to complete:  ${enrolledCourses.get(i).getEstimatedTimeToComplete()}<br>
						Instructor name: ${instructorName.get(String.valueOf(enrolledCourses.get(i).getInstructor_id()))}<br>
						Category: ${enrolledCourses.get(i).getCategory()}<br>
						Grades: ${grades.get(i)}
						
						<hr>
				</a>
			</c:forEach>
			</c:if>
		</div><br>
			 	
			<c:if test="${ enrolledCourses.size() == 0}">
			<div id="courseContainer-student">
			You have't enrolled in any course.<br>
			Visit Browse Course Catalog to enroll in the course
			</div>
			</c:if>
</div>	
</c:if>
</body>
</html>