<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/main.css"/>
<head>
<meta charset="ISO-8859-1">
<title>Create Book</title>
</head>
<body>
  	<div class="container">
 		<h1>Add a book to your shelf:</h1>
 		<a href="/dashboard">Back to the shelves:</a>
			<form:form action="/book/create" method="POST" modelAttribute="newBook">
				<div class="form-group">
					<form:label path="title">Title:</form:label>
					<form:input path="title" />
					<br />
					<form:errors path="title" class="text-danger" />
				</div>
				<div class="form-group">
					<form:label path="authorName">Author's Name:</form:label>
					<form:input path="authorName" />
					<br />
					<form:errors path="authorName" class="text-danger" />
				</div>
				<div class="form-group">
					<form:label path="thoughts">Thoughts:</form:label>
					<form:textarea path="thoughts" />
					<br />
					<form:errors path="thoughts" class="text-danger" />
				</div>
				<input type="submit" value="Create" class="btn bg-dark text-light" />
			</form:form>
 	</div>
</body>
</html>