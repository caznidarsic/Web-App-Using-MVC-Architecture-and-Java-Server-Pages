<%@ page import="java.time.LocalDateTime" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Hike Quote Generator</title>
<jsp:useBean id="quoteRequest" class="myServlet.QuoteRequest" scope="session" />
<script src="ValidateHikers.js">  </script>
<%LocalDateTime localDateTime = LocalDateTime.now();%>
</head>

<body>
		



<form method=POST>
<h1>Hike Quote Generator</h1>


Choose a Hike:
<select name="Hike" id=hikeSelector>

<% 
quoteRequest.setHike("some other hike!");
if (quoteRequest.getHike() != null) { 
	for (String name: quoteRequest.getHikeNames()) {
		if (name.equals(request.getParameter("Hike"))) {
			out.write("<option value=\"" + name + "\" selected>" + name + "</option>");
		}
		else {
			out.write("<option value=\"" + name + "\">" + name + "</option>");
		}
	}
} 
else {
	for (String name: quoteRequest.getHikeNames()) {
		out.write("<option value=\"" + name + "\">" + name + "</option>");
	}
} 
%>

</select>
<br />



Select Duration:
<select name="Duration" id=durationSelector>
<%
if (quoteRequest.getDuration() != null && quoteRequest.getDuration().matches("^\\d{1,2}$")) {
	for (int x = 2; x <= 7; x++) {
		if (x == Integer.valueOf(quoteRequest.getDuration())) {
			out.write("<option value=\"" + x + "\" selected>" + x + "</option>");
		}
		else {
			out.write("<option value=\"" + x + "\">" + x + "</option>");
		}
	}
}
else {
	for (int x = 2; x <= 7; x++) {
			out.write("<option value=\"" + x + "\">" + x + "</option>");
	}
}
%>

</select>
<br />



Select Year: 
<select name="Year">
<%
if (quoteRequest.getYear() != null && quoteRequest.getYear().matches("^\\d{4}$")) {
	for (int x = localDateTime.getYear(); x <= quoteRequest.getMaxYear(); x++) {
		if (x == Integer.valueOf(quoteRequest.getYear())) {
			out.write("<option value=\"" + x + "\" selected>" + x + "</option>");
		}
		else {
			out.write("<option value=\"" + x + "\">" + x + "</option>");
		}
	}
}
else {
	for (int x = localDateTime.getYear(); x <= quoteRequest.getMaxYear(); x++) {
			out.write("<option value=\"" + x + "\">" + x + "</option>");
	}
}
%>

</select>
<br />
		
		

Select Month: 
<select name="Month">
<%
if (quoteRequest.getMonth() != null && quoteRequest.getMonth().matches("^\\d{1,2}$")) {
	for (int x = 1; x <= 12; x++) {
		if (x == Integer.valueOf(quoteRequest.getMonth())) {
			out.write("<option value=\"" + x + "\" selected>" + x + "</option>");
		}
		else {
			out.write("<option value=\"" + x + "\">" + x + "</option>");
		}
	}
}
else {
	for (int x = 1; x <= 12; x++) {
			out.write("<option value=\"" + x + "\">" + x + "</option>");
	}
}
%>

</select>
<br />




Select Day: 
<select name="Day">
<%
if (quoteRequest.getDay() != null && quoteRequest.getDay().matches("^\\d{1,2}$")) {
	for (int x = 1; x <= 31; x++) {
		if (x == Integer.valueOf(quoteRequest.getDay())) {
			out.write("<option value=\"" + x + "\" selected>" + x + "</option>");
		}
		else {
			out.write("<option value=\"" + x + "\">" + x + "</option>");
		}
	}
}
else {
	for (int x = 1; x <= 31; x++) {
			out.write("<option value=\"" + x + "\">" + x + "</option>");
	}
}
%>
</select>
<br />
		


		
Select Number of Hikers: 
<%
if (quoteRequest.getNumHikers() != null && quoteRequest.getNumHikers().matches("^\\d+$")) {
	out.write("<input name=\"Hikers\" id=\"hikersInput\" type=\"text\" value=\"" + quoteRequest.getNumHikers() + "\" oninput=validateHikers() size=4 required>");
}
else {
	out.write("<input name=\"Hikers\" id=\"hikersInput\" type=\"text\" placeholder=\"ex: 4\" oninput=validateHikers() size=4 required>");
}
out.write("<p id=errorMessage style=\"color:red\"></p>");



// submit button
if (quoteRequest.getNumHikers() != null) {
	out.write("<input type=\"SUBMIT\" id=\"submitButton\">");
}
else {
	out.write("<input type=\"SUBMIT\" id=\"submitButton\" disabled>");
}

%>
		
</form>


<%
if (!quoteRequest.isFirstAccess()) {
	out.write(quoteRequest.getOutputMessage());
}
%>


		
		
</body>
</html>