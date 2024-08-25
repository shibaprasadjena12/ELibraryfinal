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
 * Servlet implementation class Resister
 */
@WebServlet("/Resister")
public class Resister extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		String email=request.getParameter("t4");
		String roll=request.getParameter("t5");
		String firstname=request.getParameter("t6");
		String lastname=request.getParameter("t7");
		String dob=request.getParameter("t8");
		String gender=request.getParameter("t9");
		String dept=request.getParameter("t10");
		String semester=request.getParameter("t11");
		String phone=request.getParameter("t12");
		String address=request.getParameter("t13");
		email=email.toLowerCase();
		
		
		try
		{
			Connection	cn=DBconnect.getConn();		
			PreparedStatement ps=cn.prepareStatement("select roll from student where  roll=(?)");
			ps.setString(1, roll);
			
			ResultSet res=ps.executeQuery();
			if(res.next())
			{
				out.println("<center><font color='red' size='5'>Roll Number Already Exist</font></center>");
			}
			else
			{
				PreparedStatement ps1=cn.prepareStatement("select email from student where  email=(?)");
				ps1.setString(1,email);
			
				ResultSet res1=ps1.executeQuery();
				if(res1.next())
				{
					out.println("<center><font color='red' size='5'>Email ID Already Exist</font></center>");
				}
				else
				{
					PreparedStatement ps3=cn.prepareStatement("select name from student where name=?");
					ps3.setString(1, name);
					
					ResultSet res3=ps3.executeQuery();
					if(res3.next())
					{
						out.println("<center><font color='red' size='5'>User Name Already Exist</font></center>");
					}
					else
					{
						PreparedStatement ps2=cn.prepareStatement("insert into student(name,pass,email,roll,firstname,lastname,dob,gender,dept,semester,phone,address) values (?,?,?,?,?,?,?,?,?,?,?,?)");
						ps2.setString(1, name);
						ps2.setString(2, pass);
						ps2.setString(3, email);
						ps2.setString(4, roll);
						ps2.setString(5, firstname);
						ps2.setString(6, lastname);
						ps2.setString(7, dob);
						ps2.setString(8, gender);
						ps2.setString(9, dept);
						ps2.setString(10, semester);
						ps2.setString(11, phone);
						ps2.setString(12, address);
	
						int i = ps2.executeUpdate();
						if(i>0)
						{
							HttpSession session=request.getSession();
							session.setAttribute("na", name);
							out.println("<center><font color='green' size='5'><h1>Registeration successful</h1></font></center>");
							RequestDispatcher rd=request.getRequestDispatcher("studentlogin.html");
							rd.include(request, response);
						}
					}
				}
			}
			
		}catch(Exception ee)
			{
			ee.printStackTrace();
			}	
	}

}
