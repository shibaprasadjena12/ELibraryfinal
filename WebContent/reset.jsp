<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.sql.*" %>
 <%@ page import="com.call.jsp.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String otp=request.getParameter("otp");
	String password = request.getParameter("password");
	String repassword = request.getParameter("repassword");
	
	try{
		/* Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","bithalb2"); */
		Connection cn = DBconnect.getConn();
		
		PreparedStatement ps=cn.prepareStatement("select email from student where otp=?");
		ps.setString(1, otp);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			if(password.equals(repassword))
			{
				PreparedStatement ps1=cn.prepareStatement("update student set pass=? where email=?");
				ps1.setString(1, password);
				ps1.setString(2, rs.getString(1));
				
				int i=ps1.executeUpdate();
				if(i > 0)
				{
					out.println("Your password is successfully updated...");
					response.sendRedirect("studentlogin.html");
				}
				else
				{
					out.println("Something Went Wrong...");
				}
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
%>

</body>
</html>