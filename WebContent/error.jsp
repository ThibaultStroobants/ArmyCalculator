<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Error</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<div id="container">
			<main>
				<h1>Error</h1>
				<p>You caused a ${pageContext.exception} on the server!</p>
				<p>${pageContext.exception.message}</p>
				<p><a href="Controller">Home</a></p>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</body>
</html>