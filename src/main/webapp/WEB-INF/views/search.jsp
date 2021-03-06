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
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<%@ include file="partials/navbar.jsp"%>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

		<form method="post" action="/search" class="form-inline ml-auto">
			<input type="hidden" name="zipCode" value="${zipCode}"> <input
				class="form-control mr-sm-2" name="keyword" type="search"
				placeholder="Keyword">
			<button class="btn btn-light my-2 my-sm-0" type="submit">Search
				by Keyword</button>
		</form>
		<!-- searches by keyword -->


		<form method="post" action="/search" class="form-inline ml-auto">

			<p>
				Start Date<input class="form-inline mr-sm-2" type="date" name="date" />
			</p>
			<p>
				End Date<input class="form-inline mr-sm-2" type="date"
					name="endDate" />
			</p>
			<input type="hidden" name="zipCode" value="${zipCode}">
			<!-- searches by date -->
			<button class="btn btn-light my-2 my-sm-0" type="submit">Search
				By Date</button>

		</form>
		<!-- searches by venue keyword -->
		<form method="post" class="form-inline ml-auto" action="/search">
			<input class="form-control mr-sm-2" name="venuename" type="search"
				placeholder="Venue">
			<button class="btn btn-light my-2 my-sm-0" type="submit">Choose
				Venue</button>
		</form>
		<div></div>

	</nav>
	<h4 class="error">${message}</h4>

	<!-- creates list of events -->
	<table class="table table-striped">





		<tr>
			<th>Event</th>
			<th>Genre</th>
			<th>Date</th>
			<th>Venue</th>
			<th>Details</th>
		</tr>

		<c:forEach var="event" items="${events}">
			<tr>

				<td>${event.name}</td>

				<form method="post" action="/search">
					<td><button class="btn btn-secondary"
							value="${event.classifications[0].genre.id}" name="genre">${event.classifications[0].genre.name}</button></td>
				</form>

				<td>${event.dates.start.localDate}</td>


				<form method="post" action="/search">
					<input type="hidden" name="zipCode" value="${zipCode}">
					<c:forEach var="local" items="${event._embedded.venues}">
						<td><button class="btn btn-secondary" value="${local.id}"
								name="venue">${local.name}</button></td>
					</c:forEach>
				</form>
				<td><a href="/event-details/${event.id}"
					class="btn btn-secondary">Details</a></td>


			</tr>
		</c:forEach>

	</table>
	<form method="post" action="/search">
		<div>
			<ul class="pagination pagination-lg">
				<li class="page-item"><button class="page-link" name="pageNum"
						value="${pageNum-1}">&laquo;</button></li>
				<li class="page-item"><button class="page-link" name="pageNum"
						value="1">1</button></li>
				<li class="page-item"><button class="page-link" name="pageNum"
						value="2">2</button></li>
				<li class="page-item"><button class="page-link" name="pageNum"
						value="3">3</button></li>
				<li class="page-item"><button class="page-link" name="pageNum"
						value="4">4</button></li>
				<li class="page-item"><button class="page-link" name="pageNum"
						value="5">5</button></li>
				<li class="page-item"><button class="page-link" name="pageNum"
						value="6">6</button></li>
				<li class="page-item"><button class="page-link" name="pageNum"
						value="7">7</button></li>
				<li class="page-item"><button class="page-link" name="pageNum"
						value="8">8</button></li>
				<li class="page-item"><button class="page-link" name="pageNum"
						value="9">9</button></li>
				<li class="page-item"><button class="page-link" name="pageNum"
						value="10">10</button></li>
				<li class="page-item"><button class="page-link" name="pageNum"
						value="${pageNum +1}">&raquo;</button></li>
			</ul>
		</div>
	</form>

</body>
</html>