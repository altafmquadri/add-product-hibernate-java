<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Form</title>
</head>
<body>
	<div style="text-align: center">
	
	<div style="margin-left:40%">
		<nav>
			<a href="index.jsp">Search Product</a>
		</nav>
	</div>
	
		<h1>Product Form</h1>
		<p>Please enter the following details to add a product</p>
		<br> <br>

		<form action="addproduct" method="post">

			Product Name: <input style="margin: 5px 10px" type="text" name="name" required="required"> <br>
			Product Category: <input style="margin: 5px 5px" type="text" name="category" required="required"> <br>
			Product Price: <input style="margin: 5px 10px" type="text" name="price" pattern=^[0-9]{1,10}(\.[0-9]{1,2})?$ title="Max 10 digits, Max 2 decimal" required="required"><br> 
			<input type="submit" value="Add Product">

		</form>
	</div>


</body>
</html>