<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-product-style.css">
	<script type="text/javascript">
		function validate() {
			var email = document.signinForm.email.value;
			var password = document.signinForm.password.value;
			if (email.length < 1 || email === '' || password.length < 1 || password === '') {
				alert('All fields are required');
				return false;
			} 
			return true;
		}
	</script>
</head>
<body>
<h2>Login</h2>

<div id="wrapper">
		<div id="header">
			<h2>Cool Hair App</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Registration</h3>
		
		<form action="${pageContext.request.contextPath}/hello" method="POST" name="singinForm">

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
						<td><label></label></td>
						<td><input type="submit" value="Login" class="save" onClick="return validate()"/></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		<br>
		<c:if test="${ invalidCredentials == true }">
			<div style="color: red">Invalid Credentials</div>
		</c:if>
		<a href="add-customer-form.jsp">Register now</a>
		
		<div style="clear: both;"></div>
		
	</div>
</body>

</html>
