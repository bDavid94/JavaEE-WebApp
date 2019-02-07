<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Cool Hair App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript">
		function validate() {
			var product = document.AddForm.product.value;
			var employee = document.AddForm.employee.value;
			var appointmentDate = document.AddForm.appointmentDate.value;
			if (product.length < 1 || product === '' 
				|| employee.length < 1 || employee === ''
				|| date.length < 1 || date === '') {
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
	
		<div id="content">
			<c:if test="${ appointment != null }">
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
					
						<c:url var="declineLink" value="AppointmentController">
							<c:param name="command" value="DELETE" />
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
								<a href="${declineLink}"
								   onclick="if (!(confirm('Are you sure you want to cancel this appointment?'))) return false">
										Cancel
								</a>	
							</td>
						</tr>	
					</table>
				</c:if>
			<br>
			<c:if test="${ appointment == null }">
				<h4>Make an appointment</h4>
				<form action="AppointmentController" method="POST" name="AddForm">
					<input type="hidden" name="command" value="ADD" />			
					<table>
						<tbody>
							<tr>
								<td><label>Product name:</label></td>
								<td>
									<select name="product" >
										<c:forEach var="product" items="${ products }">
											<option value=${ product.id } >${ product.name }: ${ product.price }$</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td><label>Employee:</label></td>
								<td>
									<select name="employee" >
										<c:forEach var="employee" items="${ employees }">
											<option value=${ employee.email }>${ employee.lastName }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td><label>Date:</label></td>
								<td><input type="datetime-local" name="appointmentDate" /></td>
							</tr>	
							<tr>
								<td><label></label></td>
								<td><input type="submit" value="Save" class="save" onClick="return validate()"/></td>
							</tr>
						</tbody>
					</table>
				</form>
			</c:if>
		</div>
	</div>
</body>


</html>








