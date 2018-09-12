<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta charset="UTF-8">
		<title>Add Connection</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<div id="container">
			<jsp:include page="header.jsp">
				<jsp:param value="Add connection" name="title"/>
			</jsp:include>
			<main>
				<c:if test="${success == true}">
					<div class="alert-success">
						Connection was successfully added!
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
			    <form method="post" action="Controller?action=addConnection">
			        <p class="form-group ${countryClass}">
				        <label for="countryid">Country</label>
				        <select id="countryid" required="required">
				        	<c:forEach var="country" items="${countries}">
				        		<option value="${country.getId()}"<c:if test="${countryidPreviousValue != null && countryidPreviousValue == country.getId()}"> selected="selected"</c:if>>${country.getId()} | ${country.getName()}</option>
				        	</c:forEach>
				        </select>
				    </p>
			        <p class="form-group ${adjacentCountryClass}">
				        <label for="adjacentcountryid">Adjacent country</label>
				        <select id="adjacentcountryid" required="required">
				        	<c:forEach var="country" items="${countries}">
				        		<option value="${country.getId()}"<c:if test="${adjacentCountryPreviousValue != null && adjacentCountryPreviousValue == country.getId()}"> selected="selected"</c:if>>${country.getId()} | ${country.getName()}</option>
				        	</c:forEach>
				        </select>
				    </p>
			        <p class="form-group ${adjacentTerritoriesClass}">
				        <label for="territories"># Adjacent Territories next to adjacent country</label>
				        <input type="number" id="territories" name="territories" value="<c:if test="${territoriesPreviousValue != null}">${territoriesPreviousValue}</c:if>">
			        </p>
			        <p>
			        	<label for="addConnection">&nbsp;</label>
			        	<input type="submit" id="addConnection" value="Add connection">
			        </p>
			    </form>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</body>
</html>
