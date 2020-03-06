<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Favicon It's a coffee cup right now. Change it to whatever you want-->
<link rel="icon" type="image/png"
	href="https://i2.wp.com/awakedetroit.com/wp-content/uploads/2019/04/cropped-Favicon.png?ssl=1">
<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<!-- Bootswatch Theme Flatly. Grab a different one from https://www.bootstrapcdn.com/bootswatch/ if you want-->
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/flatly/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-yrfSO0DBjS56u5M+SjWTyAHujrkiYVtRYh2dtB3yLQtUz3bodOeialO59u5lUCFF"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css">
</head>
<body>

	<nav class="navbar navbar-expand-lg">

		<form method="post" action="/search" class="form-inline ml-auto">
			<input type="hidden" name="zipCode" value="${zipCode}">
			<input class="form-control mr-sm-2" name="keyword" type="search"
				placeholder="Keyword">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search
				by Keyword</button>
		</form>

		<form method="post" class="form-inline ml-auto" action="/search">
		<input type="hidden" name="zipCode" value="${zipCode}">
			Venue: <select name="venue">
				<c:forEach var="event" items="${events}">
					<c:forEach var="local" items="${event._embedded.venues}">
						<option value="${local.id}">${local.name}</option>
					</c:forEach>
				</c:forEach>
			</select>
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Choose
				Venue</button>
		</form>
	</nav>

	<table class="table table-striped">
		<tr>
			<th>Event</th>
			<th>Date</th>
			<th>Venue</th>
			<th>Details</th>
		</tr>

		<c:forEach var="event" items="${events}">
			<tr>

				<td>${event.name}</td>
				<td>${event.dates.start.localDate}</td>
				<c:forEach var="local" items="${event._embedded.venues}">
					<td>${local.name}</td>
				</c:forEach>
				<td><a href="/event-details" + ${event.id}>Details</a></td>
			</tr>
		</c:forEach>

	</table>


</body>
</html>