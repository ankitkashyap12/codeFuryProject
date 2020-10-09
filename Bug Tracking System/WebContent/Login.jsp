<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="LoginServlet" method="post">
	<div>
		<label>Email</label>
		<input type="email" name="enteredEmail" id="enteredEmail" required="required">
	</div>
	<div>
		<label>Password</label>
		<input type="password" name="enteredPassword" id="enteredPassword" required="required">
	</div>
	<div>
		<input type="submit" value="Login" id="subButton">
	</div>
</form>
</body>
</html>