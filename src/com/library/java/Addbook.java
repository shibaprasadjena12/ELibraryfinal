package com.library.java;
import com.call.jsp.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Addbook")
public class Addbook extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String id=request.getParameter("t1");
		String bname=request.getParameter("t2");
		String publisher=request.getParameter("t3");
		String semister=request.getParameter("t4");
		double price=Double.parseDouble(request.getParameter("t5"));
		String avail=request.getParameter("t6");
		try
		{
			
		Connection	cn=DBconnect.getConn();

		PreparedStatement ps2=cn.prepareStatement("select * from book where id=(?)");
		ps2.setString(1, id);
		ResultSet res=ps2.executeQuery();
		if(res.next())
		{
			out.println("<center><font style='background-color:green' color='red' size='10' ><h1>!!! Additon failed !!!</h1></font></center>");
			RequestDispatcher rd=request.getRequestDispatcher("addbook.jsp");
			rd.include(request, response);
		}
		else
		{
				PreparedStatement ps1=cn.prepareStatement("insert into book values(?,?,?,?,?,?)");	
				ps1.setString(1,id);
				ps1.setString(2,bname);
				ps1.setString(3, publisher);
				ps1.setString(4,semister);
				ps1.setDouble(5,price);
				
				ps1.setString(6,avail);
				int i=ps1.executeUpdate();
				if(i>0)
				{
				RequestDispatcher rd=request.getRequestDispatcher("addbook.jsp");
				out.println("<center><font  color='green' size='10' ><h1>Added   successfull</h1></font></center>");
				rd.include(request, response);
				}
				
		}
		
		
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}

}
