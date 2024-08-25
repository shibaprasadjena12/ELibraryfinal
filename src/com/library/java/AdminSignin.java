package com.library.java;


import java.io.IOException;
import com.call.jsp.*;
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
import javax.servlet.http.HttpSession;

import com.call.jsp.Check;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/AdminSignin")
public class AdminSignin extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String id=request.getParameter("t1");
		String pass=request.getParameter("t2");
		
		try
		{
			Connection	cn=DBconnect.getConn();
			PreparedStatement ps=cn.prepareStatement("select * from Admin where username=(?) and password=(?)");
			ps.setString(1, id);
			ps.setString(2,pass);
			ResultSet res=ps.executeQuery();
			if(res.next())
			{
				HttpSession session=request.getSession();
				session.setAttribute("na",id);
				RequestDispatcher rd=request.getRequestDispatcher("adminhome.jsp");
				rd.forward(request,response);
			
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("adminsignin.html");
				rd.include(request,response);
				out.println("<center><font color='red' size='5'>!!! Invalid Username And Password !!!</font></center>");
			}
			
			PreparedStatement ps1 = cn.prepareStatement("select sroll,bid,to_char(tentative,'dd MM yyyy') from issuetable");
			ResultSet rs1 = ps1.executeQuery();
			
			Check.checkReturn(rs1);
			
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}

}
