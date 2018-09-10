<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta charset="UTF-8">
		<title>Update product</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<div id="container">
			<jsp:include page="header.jsp">
				<jsp:param value="Update product" name="title"/>
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
			    <form method="post" action="Controller?action=updateProduct" novalidate="novalidate">
			        <input type="hidden" id="id" name="id" value="<c:choose><c:when test="${idPreviousValue == null}">${product.getProductId()}</c:when><c:otherwise>${idPreviousValue}</c:otherwise></c:choose>">
			        <p class="form-group ${nameClass}">
				        <label for="name">Name</label>
				        <input type="text" id="name" name="name" value="<c:choose><c:when test="${namePreviousValue == null}">${product.getName()}</c:when><c:otherwise>${namePreviousValue}</c:otherwise></c:choose>">
				    </p>
			        <p class="form-group ${descriptionClass}">
				        <label for="description">Description</label>
				        <input type="text" id="description" name="description" value="<c:choose><c:when test="${descriptionPreviousValue == null}">${product.getDescription()}</c:when><c:otherwise>${descriptionPreviousValue}</c:otherwise></c:choose>">
			        </p>
			        <p class="form-group ${priceClass}">
				        <label for="price">Price</label>
				        <input type="number" step="0.01" id="price" name="price" value="<c:choose><c:when test="${pricePreviousValue == null}">${product.getPrice()}</c:when><c:otherwise>${pricePreviousValue}</c:otherwise></c:choose>">
			        </p>
			        <p class="form-group ${amountClass}">
				        <label for="amount">Amount</label>
				        <input type="number" id="amount" name="amount" value="<c:choose><c:when test="${amountPreviousValue == null}">${product.getAmount()}</c:when><c:otherwise>${amountPreviousValue}</c:otherwise></c:choose>">
			        </p>
			        <p>
			        	<label for="updateProduct">&nbsp;</label>
			        	<input type="submit" id="updateProduct" value="Update product">
			        </p>
			    </form>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</body>
</html>
