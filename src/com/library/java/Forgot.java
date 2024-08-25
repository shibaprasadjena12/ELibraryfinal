package com.library.java;


import java.io.IOException;
import com.call.jsp.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Forgot")
public class Forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		String toEmail=request.getParameter("email");
		
		try {
			Connection cn = DBconnect.getConn();
			PreparedStatement ps = cn.prepareStatement("select email from student where email=?");
			ps.setString(1, toEmail);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				int num =(int)(Math.random()*1000000);
				System.out.println(num);
				
				PreparedStatement ps1 = cn.prepareStatement("update student set otp=? where email=?");
				ps1.setInt(1, num);
				ps1.setString(2, toEmail);
				
				int i=ps1.executeUpdate();
				if(i<=0){
					out.println("Something Went Wrong...Plz try after some time...");
				}
				else {
				
					String subject="OTP Verification";
					String message="Your One Time Password is "+num+". Please Don't share with anyone.";
									
					String fromEmail = "elibrary.demo2019@gmail.com";
					String username = "elibrary.demo2019@gmail.com";
					String password = "elibrary@2019";
					
					SendMail.send(fromEmail,username,password,toEmail,subject,message);
					
					response.sendRedirect("resetpassword.jsp");
				}
				
			}
			else
			{
				out.println("Please input a valid Email ID");
				RequestDispatcher rd=request.getRequestDispatcher("forgot.html");
				rd.include(request, response);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
