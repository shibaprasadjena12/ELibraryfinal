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

/**
 * Servlet implementation class Login
 */
@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String pass=request.getParameter("pass");
		String name=request.getParameter("name");
		
		try
		{
			Connection	cn=DBconnect.getConn();
			PreparedStatement ps1=cn.prepareStatement("select name,pass from student where name=(?) and pass=(?)");
			ps1.setString(1,name);
			ps1.setString(2, pass);
			ResultSet res=ps1.executeQuery();
			if(res.next())
			{	
				HttpSession session=request.getSession();
				session.setAttribute("na", name);
		
				RequestDispatcher rd=request.getRequestDispatcher("studenthome.jsp");
				rd.forward(request, response);
			}
			else
			{
				out.println("<center><font color='red' size='5'>invalid username or password</font></center>");
				RequestDispatcher rd=request.getRequestDispatcher("studentlogin.html");
				rd.include(request, response);
			}
			
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		
		
	}


	
		
		
	}


