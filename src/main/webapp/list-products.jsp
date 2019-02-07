<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Cool Hair App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>
<jsp:include page="header.jsp" />
	<div id="wrapper">
		<div id="header">
			<h2>Cool Hair App</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			
			<input type="button" value="Add Product" 
				   onclick="window.location.href='add-product-form.jsp'; return false;"
				   class="add-product-button"
			/>
			
			<table>
			
				<tr>
					<th>Name</th>
					<th>Price</th>
					<th>Duration</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="product" items="${products}">
					
					<c:url var="productLink" value="ProductController">
						<c:param name="command" value="LOAD" />
						<c:param name="productId" value="${product.id}" />
					</c:url>

					<c:url var="deleteLink" value="ProductController">
						<c:param name="command" value="DELETE" />
						<c:param name="productId" value="${product.id}" />
					</c:url>
																		
					<tr>
						<td> ${product.name} </td>
						<td> ${product.price} </td>
						<td> ${product.duration} </td>
						<td> 
							<a href="${productLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this product?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	<br>
	<a href="admin.jsp"> << Menu</a>
	</div>
</body>


</html>








