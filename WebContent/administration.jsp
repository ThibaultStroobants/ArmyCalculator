<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Administration</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<div id="container">
			<jsp:include page="header.jsp">
				<jsp:param value="Administration" name="title"/>
			</jsp:include>
			<main>
				<h2>Save Database</h2>
				<form method="post" action="Controller?action=saveCountries" novalidate="novalidate">
					<p>
						<label for="path">Path</label>
						<input type="text" id="path" name="path">
					</p>
					<p>
						<label for="overwrite">Overwrite existing files</label>
						<input type="checkbox" id="overwrite" name="overwrite">
					</p>
					<p>
						<label for="saveDb">&nbsp;</label>
						<input type="submit" id="saveDb" value="Save Database">
					</p>
				</form>
				<h2>Load Database</h2>
				<form method="post" action="Controller?action=loadCountries" novalidate="novalidate">
					<p>
						<label for="path">Path</label>
						<input type="text" id="path" name="path">
					</p>
					<p>
						<label for="overwrite">Overwrite existing countries</label>
						<input type="checkbox" id="overwrite" name="overwrite">
					</p>
					<p>
						<label for="loadDb">&nbsp;</label>
						<input type="submit" id="loadDb" value="Load Database">
					</p>
				</form>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</body>
</html>