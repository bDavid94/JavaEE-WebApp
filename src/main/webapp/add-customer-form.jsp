<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>Cool Hair App</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-product-style.css">	
	<script type="text/javascript">
		function validate() {
			var email = document.AddForm.email.value;
			var firstName = document.AddForm.firstName.value;
			var lastName = document.AddForm.lastName.value;
			var password = document.AddForm.password.value;
			if (email.length < 1 || email === '' 
				|| firstName.length < 1 || firstName === ''
				|| lastName.length < 1 || lastName === ''
				|| password.length < 1 || password === '') {
				alert('All fields are required');
				return false;
			} 
			return true;
		}
	</script>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Cool Hair App</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Registration</h3>
		
		<form action="CustomerController" method="POST" name="AddForm">
		
			<input type="hidden" name="command" value="ADD" />

			<table>
				<tbody>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" /></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="password" /></td>
					</tr>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName"  /></td>
					</tr>

					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" onClick="return validate()"/></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		<c:if test="${ email_exists == true }">
			<p style="color:red">This email is already used</p>
		</c:if>
		
		<div style="clear: both;"></div>
		
	</div>
</body>
</html>











