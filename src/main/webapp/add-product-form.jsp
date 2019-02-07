<!DOCTYPE html>
<html>

<head>
	<title>Cool Hair App</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-product-style.css">	
	<script type="text/javascript">
		function validate() {
			var name = document.AddForm.name.value;
			var price = document.AddForm.price.value;
			var duration = document.AddForm.duration.value;
			if (name.length < 1 || name === '' 
				|| price.length < 1 || price === ''
				|| duration.length < 1 || duration === '') {
				alert('All fields are required');
				return false;
			} 
			return true;
		}
	</script>
</head>

<body>
<jsp:include page="header.jsp" />
	<div id="wrapper">
		<div id="header">
			<h2>Cool Hair App</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add Product</h3>
		
		<form action="ProductController" method="POST" name="AddForm">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Product name:</label></td>
						<td><input type="text" name="name" /></td>
					</tr>

					<tr>
						<td><label>Product price:</label></td>
						<td><input type="number" name="price" /></td>
					</tr>

					<tr>
						<td><label>Product duration:</label></td>
						<td><input type="number" name="duration" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" onClick="return validate()"/></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="ProductController">Back to List</a>
		</p>
	</div>
</body>

</html>











