<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Eventation</title>
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
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body>
	<%@ include file="partials/navbar.jsp"%>
	<container class="container-fluid bg-relief">
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<!-- Card -->
			<div class="card v-center-child">

				<!-- Card image -->

				<h1>Welcome to Eventation</h1>

				<img class="card-img-top" src="">

				<!-- Card content -->
				<div class="card-body">

					<!-- Title -->
					<h1 class="card-title">Welcome to Eventation</h1>
					<h4 class="error">${message}</h4>
					<!--  sends a message when zip code failed to return events -->

					<h3>Search events:</h3>
					<form class="container" action="/search">

						<p>
							Area Code: <input type="text" name="zipCode" required
								pattern="[.\d]{5}">
						</p>

							<button type="submit" class="btn btn-primary">Submit</button>

						</form>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-3"></div>
	</container>
</body>
</html>