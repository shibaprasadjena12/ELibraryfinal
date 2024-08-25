package com.library.java;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FeedbackMail
 */
@WebServlet("/FeedbackMail")
public class FeedbackMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String toEmail = request.getParameter("email");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		String fromEmail = "elibrary.demo2019@gmail.com";
		String username = "elibrary.demo2019@gmail.com";
		String password = "elibrary@2019";
		
		SendMail.send(fromEmail,username,password,toEmail,subject,message);
		RequestDispatcher rd = request.getRequestDispatcher("feedback.jsp");
		rd.forward(request, response);
		
	}

}
