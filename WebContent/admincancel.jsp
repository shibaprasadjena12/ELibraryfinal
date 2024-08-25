<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>
<%@ page import="com.call.jsp.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<%

	String name=(String)session.getAttribute("na");
	session.setAttribute("na", name);
	String roll=request.getParameter("roll");
	try
	{
		/* Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","bithalb2"); */
		Connection cn = DBconnect.getConn();

		PreparedStatement ps=cn.prepareStatement("select * from pendingrequest where sroll=?");
		ps.setString(1,roll);
		
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			String sname = rs.getString(1);
			String sroll = rs.getString(2);
			String bname = rs.getString(3);
			String bid=	rs.getString(4);
			String semester = rs.getString(5);
			
			PreparedStatement ps1=cn.prepareStatement("delete from pendingrequest where sroll=? and bid=?");
			ps1.setString(1, roll);
			ps1.setString(2, bid);
			
			PreparedStatement ps2 = cn.prepareStatement("select availability from book where id=?");
			ps2.setString(1, bid);
			
			ResultSet rs2=ps2.executeQuery();
			if(rs2.next())
			{
					int avail=Integer.parseInt(rs2.getString(1));
					avail++;
					
					PreparedStatement ps3 = cn.prepareStatement("update book set availability=? where id=?");
					ps3.setInt(1, avail);
					ps3.setString(2, bid);
					
					int i=ps1.executeUpdate();
					int j=ps3.executeUpdate();
					if(i > 0 && j > 0)
					{
						out.println("<centre><font color='red' size='5'>Successfully Cancelled</font></center>");
						pageContext.include("adminrequestpending.jsp");
					}
			}
			else{
				out.println("something went wrong availability");
				}
		}
		else{
			out.println("something went wrong in delete");
			}		
	}catch(Exception ee)
		{
	ee.printStackTrace();
		}
%>

</body>
</html>