<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign-in Page</title>
</head>
<body>
	<form action="signin" method="POST">
		Username:	
		<input type="text" placeholder="username" name="username"> <br/>
		Password:	
		<input type="password" placeholder="password" name="password"> <br/>
		<input type="submit" name="signin" value="signin">
		<input type="submit" name="return" value="return"> <br/>
	</form>
</body>
</html>