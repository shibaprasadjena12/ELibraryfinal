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
 * Servlet implementation class Returnbook
 */
@WebServlet("/Returnbook")
public class Returnbook extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("t1");
		String roll=request.getParameter("t2");
		int bookid=Integer.parseInt(request.getParameter("t3"));
		String bookname=request.getParameter("t4");
		String semister=request.getParameter("t5");
		
		try
		{
			Connection	cn=DBconnect.getConn();
			PreparedStatement ps= cn.prepareStatement("select * from student where name=(?) and roll=(?)");
			ps.setString(1,name);
			ps.setString(2,roll);
			ResultSet res=ps.executeQuery();
			
		
		
			if(res.next())
			{	
				PreparedStatement ps1=cn.prepareStatement("select * from book where id=(?) and bname=(?) and semistar=(?)");
				ps1.setString(2,bookname);
				ps1.setInt(1, bookid);
				ps1.setString(3,semister);
				ResultSet res1=ps1.executeQuery();
				
				if(res1.next())
				{
					PreparedStatement ps2=cn.prepareStatement("select AVAILABILITY from book where id=(?)");
					String pa;
					ps2.setInt(1,bookid);
					ResultSet res2=ps2.executeQuery();
					// 	 System.out.println(res2.getString(1));
					
			
					if(res2.next())
					{
						pa=res2.getString(1);
						int aa=Integer.parseInt(pa);
						aa++;
						PreparedStatement ps3=cn.prepareStatement("update book set AVAILABILITY=(?) where id=(?)");
						ps3.setInt(1, aa);
						ps3.setInt(2, bookid);
						int i=ps3.executeUpdate();
						System.out.println(i);
			
			
						if(i>0)
						{
							PreparedStatement ps4=cn.prepareStatement("delete from issuetable where bid=(?)");
							ps4.setInt(1,bookid);
							System.out.println(bookid);
							int j=ps4.executeUpdate();
							System.out.println(j);
							if(j>0)
							{
								out.println("<center><font color='green' size='5'>Return Successfull</font></center>");
								RequestDispatcher rd = request.getRequestDispatcher("studentreturnbook.jsp");
								rd.include(request, response);
							}
							else
							{
								out.println("<center><font color='Red' size='5'>No Such Book Issued</font></center>");
								RequestDispatcher rd = request.getRequestDispatcher("studentreturnbook.jsp");
								rd.include(request, response);
							}
							
						}
					}
				}
				else
				{
					out.println("plz check bookid,semistar,book name");
				}
			}			else
			{
				out.println("plz check ur name and roll");
			}
		
		}
		catch(Exception ee)
		{
		ee.printStackTrace();	
		}
		
	}

}
