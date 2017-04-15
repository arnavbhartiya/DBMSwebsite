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
	<div style="margin-left:10%;margin-right: 10% ">
	<div class="page-header">
		<h1>
			Rooms available
		</h1>
	</div>
	<c:forEach var="entry" items="${fullRoomDetailsMap}">
	<div class="panel panel-info">
  <div class="panel-heading"><b> ${entry.key}</b></div>
  <div class="panel-body">
  <i>${entry.value[2]}</i><br>
  <b>Price- </b>${entry.value[0]}$<br>
  <b>Rooms Available- </b>${entry.value[1]}<br>
  <form action="booking" method="post">
    <input type="submit"
					class="btn btn-default" style="margin-top: 8px; margin-bottom: 8px"
					name="submit" value="Book this" />
  </form>

  </div>
</div>
	</c:forEach>
	</div>
</body>
</html>