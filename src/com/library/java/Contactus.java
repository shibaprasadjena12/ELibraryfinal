package com.library.java;


import java.io.IOException;
import com.call.jsp.*;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Contactus")
public class Contactus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String name=request.getParameter("t1");
		String email=request.getParameter("t2");
		String message=request.getParameter("t3");
		
		try {
			Connection cn = DBconnect.getConn();
			PreparedStatement ps = cn.prepareStatement("insert into contactus values(?,?,?)");
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, message);
			
			int i = ps.executeUpdate();
			
			if( i > 0 )
			{
				response.sendRedirect("index.html#contactus");
				pw.println("<center><font color='white' size='5'>Thank You for Your Feedback</font></center>");
			}
			else
			{
				response.sendRedirect("index.html");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
