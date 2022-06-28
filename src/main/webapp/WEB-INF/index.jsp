<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body class="m-4">
	<header>
		<h1>Book Club</h1>
		<p>A place for friends to share thoughts on books.</p>
	</header>
	<div class="container d-flex justify-content-between">
		<div class="left">
			<h2>Register:</h2>
			<form:form action="/register" method="POST" modelAttribute="newUser">
				<div class="form-group">
					<form:label path="name">Name:</form:label>
					<form:input path="name" />
					<br />
					<form:errors path="name" class="text-danger" />
				</div>
				<div class="form-group">
					<form:label path="email">Email:</form:label>
					<form:input path="email" />
					<br />
					<form:errors path="email" class="text-danger" />
				</div>
				<div class="form-group">
					<form:label path="password">Password:</form:label>
					<form:input path="password" />
					<br />
					<form:errors path="password" class="text-danger" />
				</div>
				<div class="form-group">
					<form:label path="confirm">Confirm Password:</form:label>
					<form:input path="confirm" />
					<br />
					<form:errors path="confirm" class="text-danger" />
				</div>
				<input type="submit" value="Register" class="btn bg-dark text-light" />
			</form:form>
		</div>
		<div class="right">
			<h2>Log in:</h2>
			<form:form action="/login" method="POST" modelAttribute="newLogin">
				<div class="form-group">
					<form:label path="email">Email:</form:label>
					<form:input path="email" />
					<br />
					<form:errors path="email" class="text-danger" />
				</div>
				<div class="form-group">
					<form:label path="password">Password:</form:label>
					<form:input path="password" />
					<br />
					<form:errors path="password" class="text-danger" />
				</div>
				<input type="submit" value="Log in" class="btn bg-dark text-light" />
			</form:form>
		</div>
	</div>
</body>
</html>