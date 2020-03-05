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
<link rel="stylesheet" href="css/style.css">

<title>Event Details</title>
</head>
<body>


	<div class="container">
		<div class="row v-center-parent">
			<div class="col-md-1"></div>
			<div class="col-md-10">

				<!-- Card -->
				<div class="card v-center-child">

					<!-- Card image -->
					
					<img class="card-img-top" src="${ image.url }"
						alt="${ event.name } image">
					


					
					<!-- Card content -->
					<div class="card-body">

						<!-- Title -->
						<h2 class="card-title">${ event.name }</h2>
						<h4>${ segment.name }</h4>


						<!-- Text -->
						<p class="card-text">Date: ${ date }</p>
						<p class="card-text">Genre: ${ genre.name }</p>
						<!-- Button -->
						<a href="${ link }" class="btn btn-primary"> View on
							TickeMaster</a>
					</div>
				</div>
				<!-- Card -->

			</div>
		</div>
		<br>


	</div>


</body>
</html>