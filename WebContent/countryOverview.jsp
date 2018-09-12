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
				<table>
					<tr>
						<th>ID <a href="Controller?action=sortCountries&orderby=id">Sorteer</a></th>
						<th>Name <a href="Controller?action=sortCountries&orderby=name">Sorteer</a></th>
						<th>Income <a href="Controller?action=sortCountries&orderby=income">Sorteer</a></th>
						<th>Territories <a href="Controller?action=sortCountries&orderby=territories">Sorteer</a></th>
						<th>UPDATE?</th>
						<th>DELETE?</th>
					</tr>
					<c:forEach var="country" items="${countries}">
						<tr>
							<td>${country.getId()}</td>
							<td>${country.getName()}</td>
							<td>${country.getIncome()}</td>
							<td>${country.getTerritories()}</td>
							<td><a href="Controller?action=fetchCountryForUpdate&countryid=${country.getId()}">UPDATE</a></td>
							<td><a href="Controller?action=fetchCountryForDelete&countryid=${country.getId()}">DELETE</a></td>
						</tr>
					</c:forEach>
					<caption>Countries Overview</caption>
				</table>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</body>
</html>