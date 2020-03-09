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
<!-- Favicon It's a coffee cup right now. Change it to whatever you want--><link rel="icon" 
      type="image/png" 
      href="https://i2.wp.com/awakedetroit.com/wp-content/uploads/2019/04/cropped-Favicon.png?ssl=1">
<!-- Bootstrap core CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<!-- Bootswatch Theme Flatly. Grab a different one from https://www.bootstrapcdn.com/bootswatch/ if you want-->
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/flatly/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-yrfSO0DBjS56u5M+SjWTyAHujrkiYVtRYh2dtB3yLQtUz3bodOeialO59u5lUCFF"
	crossorigin="anonymous">
<!-- Your custom styles -->
<link rel="stylesheet" href="../css/style.css">

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
					<img class="card-img-top"
						src="${ event.image }"
						alt="${ event.name } image">

					<!-- Card content -->
					<div class="card-body">

						<!-- Title -->
						<h4 class="card-title">
							<a>${event.name}</a>
						</h4>
						<!-- Text -->
						
						<p class="card-text">Date: ${ event.date }</p>
						<p class="card-text">Genre: ${ event.genre }</p>
						<p class="card-text">Weather: ${ event.weather.summary }</p>
						<p class="card-text">Temperature: ${ event.weather.temperature } f</p>
						<p class="card-text">Featuring: ${ event.attractions[0].name }</p> 

						<div>
							<a href="${ event.link }" class="btn btn-primary"> View on
								TicketMaster</a>
						</div>
						<form method="post" action="/search">

							<div>
								<button class="btn btn-primary">Back to Search</button>
							</div>

						</form>
					</div>
				</div>
				<!-- Card -->

			</div>
			<c:if
				test="${eventCounter.count % 3 == 0||eventCounter.count == fn:length(values)}">
	</div>
	<br>
	</c:if>
	</c:forEach>
	</div>




</body>
</html>