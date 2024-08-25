<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.call.jsp.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Book page:</title>
</head>
<body bgcolor="cyan">
<center>
	<h1>BOOK LIST</h1>
	<h6><font color="red" size="5">Note*: For book issue please Click <a href="studentlogin.html">here</a></font></h6>
<%
	String bname=request.getParameter("search");
	try
	{
		/* Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","bithalb2"); */
		Connection cn = DBconnect.getConn();
						
		PreparedStatement ps=cn.prepareStatement("select * from book where bname=? or id=? or publisher=?");
		ps.setString(1,bname);
		ps.setString(2,bname);
		ps.setString(3,bname);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
%>
			<table border='1' width='90%'><tr><th>Book ID</th><th>Book Name</th><th>Publisher</th><th>Semister</th><th>Price</th></tr>
			<tr>
				<td><%=rs.getString(1) %></td>
				<td><%=rs.getString(2) %></td>
				<td><%=rs.getString(3) %></td>
				<td><%=rs.getString(4) %></td>
				<td><%=rs.getString(5) %></td>
			</tr>
<%	
			while(rs.next())
			{
%>
			<tr>
				<td><%=rs.getString(1) %></td>
				<td><%=rs.getString(2) %></td>
				<td><%=rs.getString(3) %></td>
				<td><%=rs.getString(4) %></td>
				<td><%=rs.getString(5) %></td>
			</tr>
<%
				}
			}
			else
			{
				out.println("No Search result found...");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
%>
</table>
</center>
</body>
</html>