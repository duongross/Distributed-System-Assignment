<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<form action="login" method="POST">
		Username:	
		<input type="text" placeholder="username" name="username"> <br/>
		Password:	
		<input type="password" placeholder="password" name="password"> <br/>
		<input type="submit" name="login" value="login">
		<input type="submit" name="return" value="return"> <br/>
	</form>
</body>
</html>