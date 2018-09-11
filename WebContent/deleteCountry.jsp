<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta charset="UTF-8">
		<title>Delete country</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<div id="container">
			<jsp:include page="header.jsp">
				<jsp:param value="Are you sure you want to delete the following country?" name="title"/>
			</jsp:include>
			<main>
			    <form method="post" action="Controller?action=deleteCountry" novalidate="novalidate">
			        <input type="hidden" id="id" name="id" value="${country.getId()}">
			        <p>ID: ${country.getId()}</p>
			        <p>Name: ${country.getName()}</p>
			        <p>Income: ${country.getIncome()}</p>
			        <p>Territories: ${country.getTerritories}</p>
			        <p><input type="submit" id="deleteCountry" value="Delete"> <a href="Controller?action=countryOverview">Cancel</a></p>
			    </form>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</body>
</html>
