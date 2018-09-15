<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta charset="UTF-8">
		<title>Delete connection</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<div id="container">
			<jsp:include page="header.jsp">
				<jsp:param value="Are you sure you want to delete the following connection?" name="title"/>
			</jsp:include>
			<main>
			    <form method="post" action="Controller?action=deleteConnection" novalidate="novalidate">
			        <input type="hidden" id="countryid" name="countryid" value="${country.getId()}">
			        <input type="hidden" id="connectioncountry" name="connectioncountry" value="${connection.getAdjacentCountry().getName()}">;
			        <p>Name of country: ${country.getName()}</p>
			        <p>Name of adjacent country: ${connection.getAdjacentCountry().getName()}</p>
			        <p># Adjacent territories of ${country.getName()} to ${connection.getAdjacentCountry().getName()}: ${connection.getAdjacentTerritories()}</p>
			        <p><input type="submit" id="deleteConnection" value="Delete"> <a href="Controller?action=connectionOverview">Cancel</a></p>
			    </form>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</body>
</html>
