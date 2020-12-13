<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>
<%@ include file="footer.jsp"%>

<! DOCTYPE html>
<html>
<head>
	<title>Go Crafty</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<body>
	<div class = content>
		<div class="button">
			<input class="button-student" type = "button" value = "For Students" onclick="location.href = 'home/userLogin?role=student';"/> 
			<input class="button-instructor" type = "button" value = "For Instructors" onclick="location.href = 'home/userLogin?role=instructor';"/>
		</div>
		<div id="about">
			<h1> About</h1>
			<h4>We Provide a world where the learning is beyond any age and time restrictions. Explore our courses for free now!</h4>
			<ul>
				<li>Free Course Content</li>
				<li>Easy Registration</li>
				<li>Courses for every Age group</li>
				<li>Secure</li>
				<li>100% Online</li>
				<li>Earn a certificate in 4-6 weeks</li>
			</ul>
		</div><br><br>
		<div id="services">
			<h2>Services Offered</h2>
			<h4>At GoCrafty, you have access to:</h4>
			<ul>
				<li>Mobile learning through our web application. Access the videos on demand, anywhere.</li>
				<li>Academic and technical support where you can chat with the Instructors in real-time or communicate with us in case of any technical issues.</li>  
				<li>Easily access your courses in one click</li>
				<li>Register as an Instructor and show your skills by teaching online.</li>
			</ul>
		</div><br><br>
		<div id="contactus">
			<h2>Contact Us</h2>
				<p>GoCrafty is an initiative for students who wants to learn Arts and Crafts 100% online. The project is a part of course curriculum for ENPM613: Software Design and Architecture.
					GoCrafty is currently in development stage and will be launched soon. The members of the team are:
				</p>
				<div id="memberCards">
					<div id="membercard">
					HARSH SHAH
					<br><a href="mailto:harshshah0110@gmail.com"><img src = "${pageContext.request.contextPath}/resources/images/gmail.png" class="social"></a>
					<a href="https://www.linkedin.com/in/harshshah4/">
						<img src = "${pageContext.request.contextPath}/resources/images/linkedin.png" class="social">
						</a>
					</div>
					<div id="membercard">
					MOHIT PARYANI
					<br><a href="mailto:mohitparyani23@gmail.com"><img src = "${pageContext.request.contextPath}/resources/images/gmail.png" class="social"></a>
					<a href="https://www.linkedin.com/in/mohit-paryani-8672a5141/">
						<img src = "${pageContext.request.contextPath}/resources/images/linkedin.png" class="social">
					</a>
					</div>
					<div id="membercard">
					NIKITA WADHWANI
					<br>
					<a href="mailto:nikitawadhwani1009654@gmail.com"><img src = "${pageContext.request.contextPath}/resources/images/gmail.png" class="social"></a>
					</div>
					<div id="membercard">
					PRAKHAR SHARMA
					<br><a href="mailto:prakhar2811@gmail.com"><img src = "${pageContext.request.contextPath}/resources/images/gmail.png" class="social"></a>
										<a href="https://www.linkedin.com/in/prakhar-sharma-41093b141/">
						<img src = "${pageContext.request.contextPath}/resources/images/linkedin.png" class="social">
						</a>
					</div>
				</div>
				<p>For any technical assistance, reach us at supportgocrafty@gmail.com ('@gocrafty.com' coming soon)</p>
		</div>
	</div><br><br><br><br><br><br>
</body>
</html>