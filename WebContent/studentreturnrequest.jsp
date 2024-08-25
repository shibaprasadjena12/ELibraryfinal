<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
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
System.out.println(name);

String id=request.getParameter("id");
try
{
	/* Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","bithalb2"); */
	Connection cn = DBconnect.getConn();

	PreparedStatement ps=cn.prepareStatement("select * from issuetable where sname=? and bid=?");
	ps.setString(1, name);
	ps.setString(2, id);
	
	ResultSet rs=ps.executeQuery();
	if(rs.next())
	{
		System.out.println("hello");
		String sname = rs.getString(1);
		String sroll = rs.getString(2);
		String bname = rs.getString(3);
		String bid=	rs.getString(4);
		String semester = rs.getString(5);
				
		PreparedStatement ps1=cn.prepareStatement("delete from issuetable where sroll=? and bid=?");
		ps1.setString(1, sroll);
		ps1.setString(2, bid);
		
		PreparedStatement ps2 = cn.prepareStatement("insert into pendingreturn(sname,sroll,bname,bid,semester) values(?,?,?,?,?)");
		ps2.setString(1, sname);
		ps2.setString(2, sroll);
		ps2.setString(3, bname);
		ps2.setString(4, bid);
		ps2.setString(5, semester);
			
		int i=ps1.executeUpdate();
		int j=ps2.executeUpdate();
		if(i > 0 && j > 0)
		{
			out.println("<centre><font color='red' size='5'>Return Pending Admin\'s Approval Required</font></center>");
			pageContext.include("studentreturnbook.jsp");
		}
		else
		{
			out.println("something went wrong return");
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