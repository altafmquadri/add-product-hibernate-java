<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.service.Record" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find Product</title>
</head>
<body>
	<div style="text-align:center">
		<div>
			<h1>Product Search</h1>
		</div>
		<% 
			Record r = new Record();
			int total= r.getRecords();
			
		%>
		<div>
			<p>Please use the form below to search our Catalog <br> Our current product lines offer Models 1 to <%= total %></p>
		</div>

		
		<form action="Retrieval" method="post">
			Enter a product Id<input style="margin-left:5px; margin-bottom:10px" type="number" name="product_id"> <br> 
				<input type="hidden" name="total" value="<%=total %>">
			<input type="submit" value="Search">
		</form>
	</div>
</body>
</html>