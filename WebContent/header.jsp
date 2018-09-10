<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
	<h1><span>Army Calculator</span></h1>
	<nav>
		<ul>
			<c:choose>
				<c:when test="${param.title == 'Home'}">
					<li id="actual"><a href="Controller">Home</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="Controller">Home</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.title == 'Country overview'}">
					<li id="actual"><a href="Controller?action=countryOverview">Countries</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="Controller?action=countryOverview">Countries</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.title == 'Connections overview'}">
					<li id="actual"><a href="Controller?action=connectionOverview">Connections</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="Controller?action=connectionOverview">Connections</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.title == 'Add country'}">
					<li id="actual"><a href="Controller?action=newCountry">Add country</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="Controller?action=newCountry">Add country</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.title == 'Add connection'}">
					<li id="actual"><a href="Controller?action=newConnection">Add connection</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="Controller?action=newConnection">Add connection</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	<h2>${param.title}</h2>
</header>