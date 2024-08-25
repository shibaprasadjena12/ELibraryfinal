package com.library.java;


import java.io.IOException;
import com.call.jsp.*;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookIssue
 */
@WebServlet("/BookIssue")
public class BookIssue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookIssue() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String name=request.getParameter("t1");
		int roll=Integer.parseInt(request.getParameter("t2"));
		System.out.println(roll);
		int bookID=Integer.parseInt(request.getParameter("t3"));
		try {
			Connection	cn=DBconnect.getConn();
			PreparedStatement ps=cn.prepareStatement("select * from student where roll=(?) and name=(?)");
			ps.setInt(1,roll);
			ps.setString(2,name);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				PreparedStatement ps2=cn.prepareStatement("select availability from book where id=(?)");
				ps2.setInt(1, bookID);
				ResultSet rs2=ps2.executeQuery();
				if(rs2.next()) {
					int avail=rs2.getInt(1);
					
					if(avail>0) {
						PreparedStatement ps5=cn.prepareStatement("select * from book  ");
						ResultSet res1=ps5.executeQuery();
						PreparedStatement ps6=cn.prepareStatement("select bname from book where id=(?) ");
						ps6.setInt(1,bookID);
						ResultSet res2=ps6.executeQuery();
						if(res1.next()&& res2.next())
						{
						String bname=res2.getString(1);
						int bid=res1.getInt(1);
						String semister=res1.getString(4);
						
						
						
						/*PreparedStatement ps4=cn.prepareStatement("insert into issuetable values('?,?,?,?,?);");
						ps4.setString(1,name);
						ps4.setInt(2,roll);
						ps4.setString(3,bname);
						ps4.setInt(4,bid);
						ps4.setString(5,semister);
						int k=ps4.executeUpdate();*/
						
						
						avail=avail-1;
						PreparedStatement ps3=cn.prepareStatement("update book set availability=(?) where id=(?)");
						ps3.setInt(1, avail);
						ps3.setInt(2, bookID);
						int i=ps3.executeUpdate();
						
						if(i>0) {
							PreparedStatement ps4=cn.prepareStatement("insert into pendingrequest values(?,?,?,?,?)");
							ps4.setString(1,name);
							ps4.setInt(2,roll);
							ps4.setString(3,bname);
							//ps4.setInt(4,bid);
							ps4.setInt(4,bookID);
							ps4.setString(5,semister);
							int k=ps4.executeUpdate();
							pw.println("<center><font color='green' size='5'>Issued book undrprocess...required admin aproval</font></center>");
							RequestDispatcher rd=request.getRequestDispatcher("studentbookissue.jsp");
							rd.include(request, response);
							
						}						
					}
					}
						
						
						else {
						pw.println("Sorry book is not available !! please ckeck the availability");
						RequestDispatcher rd=request.getRequestDispatcher("studentbookissue.jsp");
						rd.include(request, response);
					}
				}
				
			}
			else
			{
				pw.println("<center><h1>please check your roll no</h1><center> ");
				RequestDispatcher rd=request.getRequestDispatcher("studentbookissue.jsp");
				rd.include(request, response);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
	}

}
