<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
			String bid = rs.getString(4);
			String semester = rs.getString(5);
			System.out.println(sroll+"  "+bid);
			
			PreparedStatement ps1=cn.prepareStatement("delete from pendingrequest where sroll=? and bid=?");
			ps1.setString(1, roll);
			ps1.setString(2, bid);
			
			Timestamp currentTime= new java.sql.Timestamp(System.currentTimeMillis());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, 18);
			String output = sdf.format(c.getTime());
			
			PreparedStatement ps2 = cn.prepareStatement("insert into issuetable(sname,sroll,bname,bid,semester,issuedate,tentative,fine) values(?,?,?,?,?,?,?,?)");
			ps2.setString(1,sname);
			ps2.setString(2,sroll);
			ps2.setString(3,bname);
			ps2.setString(4,bid);
			ps2.setString(5,semester);
			ps2.setTimestamp(6, currentTime);
			
			ps2.setString(7, output);
			ps2.setDouble(8, 0.0);
			
			PreparedStatement ps3 = cn.prepareStatement("insert into report(sname,sroll,bname,bid,semester,issuedate,tentative,fine) values(?,?,?,?,?,?,?,?)");
			ps3.setString(1,sname);
			ps3.setString(2,sroll);
			ps3.setString(3,bname);
			ps3.setString(4,bid);
			ps3.setString(5,semester);
			ps3.setTimestamp(6, currentTime);
			
			ps3.setString(7, output);
			ps3.setDouble(8, 0.0);
			
			int i = ps1.executeUpdate();
			int j = ps2.executeUpdate();
			int k = ps3.executeUpdate();
			if(i>0 && j>0 && k>0)
			{
				out.println("<centre><font color='green' size='5'>Successfully Issued</font></center>");
				pageContext.include("adminrequestpending.jsp");
			}
		}
		
	}catch(Exception ee)
		{
	ee.printStackTrace();
		}
%>

</body>
</html>