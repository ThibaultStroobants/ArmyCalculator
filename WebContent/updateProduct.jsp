<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta charset="UTF-8">
		<title>Update country</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<div id="container">
			<jsp:include page="header.jsp">
				<jsp:param value="Update country" name="title"/>
			</jsp:include>
			<main>
				<c:if test="${!result.isEmpty() && result != null}">
					<div class="alert-danger">
						<c:forEach var="message" items="${result}">
							<ul>
								<li>${message}</li>
							</ul>
						</c:forEach>
					</div>
				</c:if>
			    <form method="post" action="Controller?action=updateCountry" novalidate="novalidate">
			        <input type="hidden" id="id" name="id" value="<c:choose><c:when test="${countryIDPreviousValue == null}">${country.getId()}</c:when><c:otherwise>${countryIDPreviousValue}</c:otherwise></c:choose>">
			        <p class="form-group ${nameClass}">
				        <label for="name">Name</label>
				        <input type="text" id="name" name="name" value="<c:choose><c:when test="${namePreviousValue == null}">${country.getName()}</c:when><c:otherwise>${namePreviousValue}</c:otherwise></c:choose>">
				    </p>
			        <p class="form-group ${incomeClass}">
				        <label for="income">Income</label>
				        <input type="number" id="income" name="income" value="<c:choose><c:when test="${incomePreviousValue == null}">${country.getIncome()}</c:when><c:otherwise>${incomePreviousValue}</c:otherwise></c:choose>">
			        </p>
			        <p class="form-group ${territoriesClass}">
				        <label for="territories">Territories</label>
				        <input type="number"  id="territories" name="territories" value="<c:choose><c:when test="${territoriesPreviousValue == null}">${country.getTerritories()}</c:when><c:otherwise>${territoriesPreviousValue}</c:otherwise></c:choose>">
			        </p>
			        <p>
			        	<label for="updateCountry">&nbsp;</label>
			        	<input type="submit" id="updateCountry" value="Update country">
			        </p>
			    </form>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</body>
</html>
