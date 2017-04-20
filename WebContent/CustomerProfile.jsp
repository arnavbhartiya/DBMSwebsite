<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOMAS</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<h1>Hi ${username}!</h1>
	<div class="bs-docs-sidebar"
		style="margin-left: 20px; margin-top: 20px; width: 30%; display: inline-block"">
		<div class="list-group">
		<form method="post" action="BookingHistory">
			<input type="submit"
					class="btn btn-default" style="margin-top: 8px; margin-bottom: 8px"
					name="submit" value="Booking History" />
				</form>
		</div>
	</div>
	<div class="panel panel-primary panels"
		style="margin-left: 20px; display: inline-block"">
		
	</div>
</body>
</html>