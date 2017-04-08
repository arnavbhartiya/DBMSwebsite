<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<div class="panel"
		style="margin-left: 20px; display: inline-block; width: 30%">
		<div class="input-group" style="width: 80%; margin-left: 10%">
			<!-- <span class="input-group-addon" id="basic-addon1" ></span> -->
			<h3 style="text-align: center;">Sign Up!!</h3>
			<form method="post" action="SignUp">
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
					<b> Choose UserName</b>
				</p>
				<input type="text" class="form-control" placeholder="User name"
					aria-describedby="basic-addon1" name="userName"><span
					class="error" style="color: red">${userNameError}</span>
				<p>
					<b>Choose Password</b>
				</p>
				<input type="text" class="form-control"
					placeholder="Enter your Password" aria-describedby="basic-addon1"
					name="password">
				
				<p>
					<b>Enter phone Number</b>
				</p>
				<input type="text" class="form-control"
					placeholder="Enter your contact number" aria-describedby="basic-addon1"
					name="phoneNo">
				<p>
					<b>Enter your email address</b>
				</p>
				<input type="text" class="form-control"
					placeholder="Enter your email id" aria-describedby="basic-addon1"
					name="email">
				<p><b>Gender</b></p>
				<div class="btn-group" role="group" aria-label="..." style="margin-bottom: 8px">
					<button type="button" class="btn btn-default">Male</button>
					<button type="button" class="btn btn-default">Female</button>
				</div>
				<div>
				 <input type="submit"
					class="btn btn-default" style="margin-top: 8px; margin-bottom: 8px"
					name="submit" value="Sign Up" />
				 <span class="error"
					style="color: red">${error}</span>
				</div>
			</form>
		</div>
	</div> 
</body>
</html>