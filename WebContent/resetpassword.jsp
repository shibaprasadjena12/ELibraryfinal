<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password Page :</title>
</head>
<body bgcolor="cyan">

	<center>
	<form action="reset.jsp">
		<h3>Reset Password</h3>
		
		<input type="number" name="otp" placeholder="Enter OTP Here"><br><br>
		<input type="password" name="password" placeholder="New Enter Password"><br><br>
		<input type="password" name="repassword" placeholder="Conform New Password"><br><br>
		<input type="submit" value="Change Password">
		
		</form>
	</center>
	
</body>
</html>