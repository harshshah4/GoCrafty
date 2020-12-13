<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

<meta charset="UTF-8">
<title>Add Barber Shop</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br><br><br>
<form:form action = "addBarberShop" modelAttribute ="theBarberShop" method= "post">
		<label>Shop Name</label>
		<form:input path="name"/><br>
		<label>Address</label>
		<form:input path="address"/><br>
		<label>City</label>
		<form:input path="city"/><br>
		<label>State</label>
		<form:input path="state"/><br>
		<label>Country</label>
		<form:input path="country"/><br>
		<label>Days Open: </label>
		<input type="checkbox"  name="day" value="Monday"/>
		<label >Monday</label><br>
		<input type="checkbox"  name="day" value="Tuesday"/>
		<label >Tuesday</label><br>
		<input type="checkbox"  name="day" value="Wednesday"/>
		<label >Wednesday</label><br>
		<input type="checkbox"  name="day" value="Thursday"/>
		<label >Thursday</label><br>
		<input type="checkbox"  name="day" value="Friday"/>
		<label >Friday</label><br>
		<input type="checkbox"  name="day" value="Saturday"/>
		<label >Saturday</label><br>
		<input type="checkbox"  name="day" value="Sunday"/>
		<label >Sunday</label><br>
		<label>Timings: From</label>
		<input type="text" name="from">
		  <select  name="AM_PM_from">
			    <option value="AM">AM</option>
			    <option value="PM">PM</option>
		  </select>
					 &nbsp;to
		<input type="text" name="to">
		<select  name="AM_PM_to">
			    <option value="AM">AM</option>
			    <option value="PM">PM</option>
		  </select><br>
		<input type="Submit" value="Add Shop"/>
	</form:form>
	<%@ include file="footer.jsp" %>
</body>
</html>