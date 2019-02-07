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
					<th>Product</th>
					<th>Employee</th>
					<th>Customer</th>
					<th>Date</th>
					<th>Status</th>
				</tr>
				
				<c:forEach var="appointment" items="${appointments}">
																		
					<tr>
						<td> ${appointment.product.name} </td>
						<td> ${appointment.employee.lastName} </td>
						<td> ${appointment.customer.lastName} </td>
						<td> ${appointment.date}</td>
						<td> ${appointment.status}</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	<br>
	<a href="admin.jsp"> << Menu</a>
	</div>
</body>


</html>








