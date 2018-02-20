<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
</head>
<body>
<form:form id="regForm" modelAttribute="user" action="registerUser" method="post">
	<form:label path="userId">User Id</form:label> : <form:input path="userId" id="userId" name="userId"/><br>
	<form:label path="password">Password</form:label> : <form:password path="password" id="password" name="password"/><br>
	<form:label path="firstName">First Name</form:label> : <form:input path="firstName" id="firstName" name="firstName"/><br>
	<form:label path="lastName">Last Name</form:label> : <form:input path="lastName" id="lastName" name="lastName"/><br>
	<form:label path="email">Email</form:label> : <form:input path="email" id="email" name="email"/><br>
	<form:label path="role">Role</form:label> : <form:input path="role" id="role" name="role"/><br>
	<form:label path="phone">Phone</form:label> : <form:input path="phone" id="phone" name="phone"/><br>
	<form:button id="register" name="register">Register</form:button>
	
	<a href="home">Home</a>
</form:form>
</body>
</html>