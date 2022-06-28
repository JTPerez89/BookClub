<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/main.css"/>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
<div class="container">
	<header>
		<div>
			<h1>Welcome, ${ user.name }!</h1>
			<p>Books from everyones shelves:</p>
		</div>
		<div>
			<a href="/logout">Log out</a>
			<a href="/book/new">Add to my shelf!</a>
		</div>
	</header>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Author Name</th>
				<th>Posted By</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="book" items="${ books }">
			<tr>
				<td><c:out value="${ book.id }" /></td>
				<td><a href="/book/${ book.id }"><c:out value="${ book.title }" /></a></td>
				<td><c:out value="${ book.authorName }" /></td>
				<td><c:out value="${ book.user.name }" /></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>