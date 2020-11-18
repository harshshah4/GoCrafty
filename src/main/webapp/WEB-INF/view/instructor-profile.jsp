<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="footer.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<meta charset="ISO-8859-1">
<title>${instructorlist.firstName} - Profile</title>
</head>
<body>
<c:if test="${tempSession.id != 'temp'}">
<header>
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
			Hello ${instructorlist.firstName}
		</div>
		<div id="headerLink"><a href="/GoCrafty/home/authentication/logOut">Log Out</a></div>
		<div id="headerLink"><a href="showEditProfile">Edit Profile</a></div>	
		<div id="headerLink"><a href="#deleteForm" onclick="showPasswordPrompt();"id="deleteButton">Delete Profile</a></div>
		<div id="headerLink"><a href="addCourse">Add Course</a></div>
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

<div id = "deleteForm" style="visibility: hidden">
	<form action="deleteProfile" method="get">
		<label>Password</label>
		<input id="formd" type="password" name = "password">
		<input class="genericbutton" type="submit" value="Confirm">
	</form>
</div>
				
<div id="container" onclick="hideForm();">
	<div id="Profile">
		<div id="namelgo">User Name:${instructorist.firstName} ${instructorlist.lastName} </div>
 		<div id="emaillgo">Email Id:${instructorlist.email} </div>
	</div>
</div>
</div>
</c:if>
</body>
</html>