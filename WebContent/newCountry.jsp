<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta charset="UTF-8">
		<title>Add Country</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<div id="container">
			<jsp:include page="header.jsp">
				<jsp:param value="Add country" name="title"/>
			</jsp:include>
			<main>
				<c:if test="${success == true}">
					<div class="alert-success">
						Country was successfully added!
					</div>
				</c:if>
				<c:if test="${!result.isEmpty() && result != null}">
					<div class="alert-danger">
						<c:forEach var="message" items="${result}">
							<ul>
								<li>${message}</li>
							</ul>
						</c:forEach>
					</div>
				</c:if>
			    <form method="post" action="Controller?action=addCountry" novalidate="novalidate">
			        <p class="form-group ${countryIDClass}">
				        <label for="countryID">Country ID</label>
				        <input type="text" id="countryID" name="countryID" value="<c:if test="${countryIDPreviousValue != null}">${countryIDPreviousValue}</c:if>">
				    </p>
			        <p class="form-group ${nameClass}">
				        <label for="name">Name</label>
				        <input type="text" id="name" name="name" value="<c:if test="${namePreviousValue != null}">${namePreviousValue}</c:if>">
				    </p>
			        <p class="form-group ${incomeClass}">
				        <label for="income">Income</label>
				        <input type="number" id="income" name="income" value="<c:if test="${incomePreviousValue != null}">${incomePreviousValue}</c:if>">
			        </p>
			        <p class="form-group ${territoriesClass}">
				        <label for="territories">Territories</label>
				        <input type="number" id="territories" name="territories" value="<c:if test="${territoriesPreviousValue != null}">${territoriesPreviousValue}</c:if>">
			        </p>
			        <p>
			        	<label for="addCountry">&nbsp;</label>
			        	<input type="submit" id="addCountry" value="Add country">
			        </p>
			    </form>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</body>
</html>
