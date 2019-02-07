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
		  <c:if test="${ appointments != null }">
			<table>
			
				<tr>
					<th>Customer</th>
					<th>Employee</th>
					<th>Product</th>
					<th>Date</th>
					<th>Price</th>
					<th>Duration</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="appointment" items="${appointments}">
					
					<c:url var="acceptLink" value="AppointmentController">
						<c:param name="command" value="ACCEPT" />
						<c:param name="appointmentId" value="${appointment.id}" />
					</c:url>

					<c:url var="declineLink" value="AppointmentController">
						<c:param name="command" value="DECLINE" />
						<c:param name="appointmentId" value="${appointment.id}" />
					</c:url>
																		
					<tr>
						<td> ${appointment.customer.lastName} </td>
						<td> ${appointment.employee.lastName} </td>
						<td> ${appointment.product.name} </td>
						<td> ${appointment.date} </td>
						<td> ${appointment.product.price} </td>
						<td> ${appointment.product.duration} </td>
						<td> ${appointment.status} </td>
						<td> 
							<c:if test="${appointment.status == 'Pending'}">
								<a href="${acceptLink}">Accept</a> 
								 | 
								<a href="${declineLink}"
								onclick="if (!(confirm('Are you sure you want to decline this appointment?'))) return false">
								Decline</a>	
							</c:if>
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		  </c:if>
		  <c:if test="${ appointments == null }">
		  	<p>You have no appointments</p>
		  </c:if>
		
		</div>
	
	</div>
</body>


</html>








