<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOMAS</title>
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
	<div class="jumbotron">
		<h1 style="text-align: center">Welcome to HOMAS!</h1>
		<p style="text-align: center">
			<a class="btn btn-primary btn-lg" href="#signUp" role="button">Sign
				Up</a>
		</p>
		<p style="text-align: center">
			Already a member?&nbsp &nbsp<a class="btn btn-default" href="#signIn"
				role="button">Sign In</a>
		</p>
	</div>
	<div class="panel panel-primary panels"
		style="margin-left: 20px; width: 30%; display: inline-block"">
		<div class="input-group" style="width: 80%; margin-left: 10%">
			<!-- <span class="input-group-addon" id="basic-addon1" ></span> -->
			<h3 style="text-align: center;">Customer login</h3>
			<form method="post" action="login">
				<p>Username</p>
				<input type="text" class="form-control"
					placeholder="Enter your Username" aria-describedby="basic-addon1"
					name="username">
				<p>Password</p>
				<input type="text" class="form-control"
					placeholder="Enter your Password" aria-describedby="basic-addon1"
					name="password"> <input type="submit"
					class="btn btn-default" style="margin-top: 8px; margin-bottom: 8px"
					name="submit" value="Sign In" /> <span class="error"
					style="color: red">${error}</span>
			</form>
		</div>
	</div>
	<div class="panel panel-primary panels"
		style="margin-left: 20px; display: inline-block; width: 30%">
		<div class="input-group" style="width: 80%; margin-left: 10%">
			<!-- <span class="input-group-addon" id="basic-addon1" ></span> -->
			<h3 style="text-align: center;">Employee login</h3>
			<form method="post" action="login">
				<p>Username</p>
				<input type="text" class="form-control"
					placeholder="Enter your Username" aria-describedby="basic-addon1"
					name="username">
				<p>Password</p>
				<input type="text" class="form-control"
					placeholder="Enter your Password" aria-describedby="basic-addon1"
					name="password"> <input type="submit"
					class="btn btn-default" style="margin-top: 8px; margin-bottom: 8px"
					name="submit" value="Sign In" /> <span class="error"
					style="color: red">${error}</span>
			</form>
		</div>
	</div>


</body>
</html>