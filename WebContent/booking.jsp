<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Concierge</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<div style="margin-left: 10%; margin-right: 10%">
		<div class="page-header">
			<h1>Almost Done!!</h1>
			<div class="panel panel-success">
				<div class="panel-heading">
					<b>Your current selection</b>
				</div>
				<div class="panel-body">
					<b>Room Selected- </b>${roomType}<br> <b>Number of Rooms
						booked- </b>${numberOfRooms}<br>
				</div>
			</div>
			<div class="panel panel-warning">
				<div class="panel-heading">
					<b>Contact & Payment Information</b>
				</div>
				<div class="panel-body">
					<div class="input-group" style="width: 60%; margin-left: 2%">
						<form method="post" action="BookingConfirmation">
							<p>
								<b> First Name</b>
							</p>
							<input type="text" class="form-control" placeholder="First name"
								aria-describedby="basic-addon1" name="firstName">
							<p>
								<b> Last Name</b>
							</p>
							<input type="text" class="form-control" placeholder="Last name"
								aria-describedby="basic-addon1" name="lastName">
							<p>
								<b> Country</b>
							</p>
							<input type="text" class="form-control"
								placeholder="Enter your Country" aria-describedby="basic-addon1"
								name="country">
							<p>
								<b>Address</b>
							</p>
							<input type="text" class="form-control"
								placeholder="Enter your billing address"
								aria-describedby="basic-addon1" name="address">
							<p>
								<b>City/Town</b>
							</p>
							<input type="text" class="form-control"
								placeholder="Enter your City" aria-describedby="basic-addon1"
								name="city">

							<p>
								<b>Enter phone Number</b>
							</p>
							<input type="text" class="form-control"
								placeholder="Enter your contact number"
								aria-describedby="basic-addon1" name="phoneNo">
							<p>
								<b>Enter your email address</b>
							</p>
							<input type="text" class="form-control"
								placeholder="Enter your email id"
								aria-describedby="basic-addon1" name="email">
							<p>
								<b> Payment Card Type</b>
							</p>
							<input type="text" class="form-control"
								placeholder="Enter card type" aria-describedby="basic-addon1"
								name="cardType" id="cardType">
							<p>
								<b>Payment Card Number</b>
							</p>
							<input type="text" class="form-control"
								placeholder="Enter card number" aria-describedby="basic-addon1"
								name="cardNumber">
							<p>
								<b> Expiration month/year</b>
							</p>
							<input type="text" class="form-control" placeholder="mm/yy"
								aria-describedby="basic-addon1" name="expirationYear">
							<p>
								<b>Security Code</b>
							</p>
							<input type="text" class="form-control"
								placeholder="Enter CVV number" aria-describedby="basic-addon1"
								name="cvv">
							<div>
								<input type="submit" class="btn btn-default"
									style="margin-top: 8px; margin-bottom: 8px" name="submit"
									value="Book your Room!" /> <span class="error" style="color: red">${signUpError}</span>
							</div>
						</form>
					</div>
				</div>
			</div>
</body>
</html>