<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta charset="UTF-8">
		<title>Country Overview</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<div id="container">
			<jsp:include page="header.jsp">
				<jsp:param value="Country overview" name="title"/>
			</jsp:include>
			<main>
				<c:forEach var="country" items="${countries}">
					<h2>${country.getName()} | ${country.getIncome()} apt</h2>
					<table>
						<tr>
							<th>Name of adjacent country</th>
							<th># Adjacent territories of ${country.getName()}</th>
							<th># Adjacent territories of adjacent country</th>
							<th># Armies on all adjacent territories of ${country.getName()}</th>
							<th>UPDATE?</th>
							<th>DELETE?</th>
						</tr>
						<c:forEach var="connection" items="${country.getConnections()}">
							<tr>
								<td>${connection.getAdjacentCountry.getName()}</td>
								<td>${connection.getAdjacentTerritories()}</td>
								<td>${connection.getAdjacentCountry.getConnection(country).getAdjacentTerritories()}</td>
								<td>${country.getCalculatedArmiesPerTerritory(connection)}</td>
								<td><a href="Controller?action=fetchConnectionForUpdate&countryid=${country.getId()}&connectioncountry=${connection.getAdjacentCountry().getName()}">UPDATE</a></td>
								<td><a href="Controller?action=fetchConnectionForDelete&countryid=${country.getId()}&connectioncountry=${connection.getAdjacentCountry().getName()}">DELETE</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:forEach>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</body>
</html>