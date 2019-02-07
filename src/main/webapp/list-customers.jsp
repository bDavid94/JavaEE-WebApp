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
		
			<table>
			
				<tr>
					<th>Email</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="customer" items="${customers}">

					<c:url var="deleteLink" value="CustomerController">
						<c:param name="command" value="DELETE" />
						<c:param name="customerEmail" value="${customer.email}" />
					</c:url>
																		
					<tr>
						<td> ${customer.email} </td>
						<td> ${customer.firstName} </td>
						<td> ${customer.lastName} </td>
						<td> 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
	<br>
	<a href="admin.jsp"> << Menu</a>
</body>


</html>








