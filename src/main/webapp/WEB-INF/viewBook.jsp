<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/main.css"/>
<head>
<meta charset="ISO-8859-1">
<title><c:out value="${ book.title }" /></title>
</head>
<body>
	<header>
		<h1><c:out value="${ book.title }" /></h1>
		<a href="/dashboard">Back to the shelves:</a>
	</header>
	<div class="container">
		<c:choose>
    		<c:when test="${id == book.user.id}">
        		<h2>You read <c:out value="${ book.title }" /> by <c:out value="${ book.authorName }" />.</h2>
        		<h3>Here are your thoughts:</h3>
        		<p><c:out value="${ book.thoughts }" /></p>
        		<a href="/book/edit/${book.id}">Edit</a>
    		</c:when>    
    		<c:otherwise>
				<h2><c:out value="${ book.user.name }" /> read <c:out value="${ book.title }" /> by <c:out value="${ book.authorName }" />.</h2>
        		<h3>Here are <c:out value="${ book.user.name }" />'s thoughts:</h3>
        		<p><c:out value="${ book.thoughts }" /></p>
    		</c:otherwise>
		</c:choose>
	</div>

</body>
</html>