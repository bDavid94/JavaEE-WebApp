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
		
			
			<input type="button" value="Add Employee" 
				   onclick="window.location.href='add-employee-form.jsp'; return false;"
				   class="add-product-button"
			/>
			
			<table>
			
				<tr>
					<th>Email</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Phone</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="employee" items="${employees}">
					
					<c:url var="employeeLink" value="EmployeeController">
						<c:param name="command" value="LOAD" />
						<c:param name="employeeEmail" value="${employee.email}" />
					</c:url>

					<c:url var="deleteLink" value="EmployeeController">
						<c:param name="command" value="DELETE" />
						<c:param name="employeeEmail" value="${employee.email}" />
					</c:url>
																		
					<tr>
						<td> ${employee.email} </td>
						<td> ${employee.firstName} </td>
						<td> ${employee.lastName} </td>
						<td> ${employee.phoneNumber} </td>
						<td> 
							<a href="${employeeLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false">
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








