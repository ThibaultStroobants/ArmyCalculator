<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<div id="container">
			<jsp:include page="header.jsp">
				<jsp:param value="Home" name="title"/>
			</jsp:include>
			<main>
				<p>
					This is the army calculator I designed to determine how many armies certain borders should have.
					Below you will find the calculation of the armies for the borders of country A with the adjacent borders of country B:
				</p>
				<p>
					Country A's income in apt
					Times 		the # of adjacent territories country B has next to country A
					Divided by 	the # of adjacent territories country A has next to country B
					Times		the # of territories Country B has
					Divided by	the # of territories Country A has
				</p>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</body>
</html>