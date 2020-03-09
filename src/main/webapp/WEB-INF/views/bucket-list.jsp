<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
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
<!-- Your custom styles -->
<link rel="stylesheet" href="../css/style.css">
<script src="https://kit.fontawesome.com/5ce8ef763e.js"
	crossorigin="anonymous"></script>

<title>Bucket List</title>
</head>
<body class="cards">
	<%@ include file="partials/navbar.jsp"%>

	<div class="container">
		<c:forEach items="${ bucketList }" var="event"
			varStatus="eventCounter">
			<c:if test="${eventCounter.count % 3 == 1}">
				<div class="row v-center-parent">
			</c:if>
			<div class="col-md-4">

				<!-- Card -->
				<div class="card v-center-child">

					<!-- Card image -->
					<img class="card-img-top" src="${ event.image }"
						alt="${ event.name } image">

					<!-- Card content -->
					<div class="card-body">

						<!-- Title -->

						<div class="split">
							<div>
								<h2 class="card-title">${ event.name }</h2>
								<h3>${ event.date }</h3>
								<br>
								<h4>${ event.segment }(${ event.genre })</h4>
								<p class="card-text">${ event.attractions[0].name }</p>

							</div>
							<div>
								<figure>
									<div class="box">
										<canvas id="icon2" width="128" height="128"></canvas>
									</div>
								</figure>
								<script>
								var skycons = new Skycons({
									"monochrome" : false,
									"color" : {
										"main" : "black",
										"moon" : "gold",
										"fog" : "gray",
										"fogbank" : "light gray",
										"light_cloud" : "gray",
										"cloud" : "gold",
										"dark_cloud" : "dark gray",
										"thunder" : "dark gray",
										"snow" : "light blue",
										"hail" : "gray",
										"sleet" : "dark gray",
										"wind" : "lime green",
										"leaf" : "green",
										"rain" : "blue",
										"sun" : "yellow",
										"thunderbolts" : "yellow"
									}
								});
								skycons.add("icon2", Skycons.${event.weather.icon});
								skycons.play();
								</script>
								<p class="mb-0">${ event.weather.summary }</p>
								<p>${ event.weather.temperature }&#176F</p>
							</div>
						</div>

						<!-- Button -->
						<div class="split">
							<div></div>
							<div>
								<form method="post" action="/bucket-list">
									<input type="hidden" name="id" value="event.eventId">
									<button type="submit" name="fave" value="true"
										class="btn btn-outline-danger">
										<i class="fas fa-heart-broken"></i> Unfavorite
									</button>
								</form>
							</div>
						</div>
						<div class="split">
							<div>
								<form method="post" action="/search">
									<button class="btn btn-primary">Back to Search</button>
								</form>
							</div>
							<div>
								<a href="${ event.link }" class="btn btn-primary" target="blank">
									View on TicketMaster</a>
							</div>
						</div>
					</div>
				</div>

				<!-- /Card -->

			</div>
			<c:if
				test="${eventCounter.count % 3 == 0||eventCounter.count == fn:length(values)}">
	</div>
	<br>
	</c:if>
	</c:forEach>
	</div>


	<script>
		var icon = "${ icon }";
		var iconString = "<c:out value="${icon}"/>";
		var skycons = new Skycons({
			"monochrome" : false,
			"color" : {
				"main" : "black",
				"moon" : "gold",
				"fog" : "gray",
				"fogbank" : "light gray",
				"light_cloud" : "gray",
				"cloud" : "gold",
				"dark_cloud" : "dark gray",
				"thunder" : "dark gray",
				"snow" : "light blue",
				"hail" : "gray",
				"sleet" : "dark gray",
				"wind" : "lime green",
				"leaf" : "green",
				"rain" : "blue",
				"sun" : "yellow",
				"thunderbolts" : "yellow"
			}
		});

		// you can add a canvas by it's ID...
		skycons.add("icon1", Skycons.PARTLY_CLOUDY);
		skycons.add("icon2", Skycons.${event.weather.icon});


		// ...or by the canvas DOM element itself.
		skycons.add(document.getElementById("icon2"), Skycons.RAIN);

		// if you're using the Forecast API, you can also supply
		// strings: "partly-cloudy-day" or "rain".

		// start animation!
		skycons.play();

		// you can also halt animation with skycons.pause()
	</script>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<script
		src=" https://rawgithub.com/darkskyapp/skycons/master/skycons.js"></script>

</body>
</html>