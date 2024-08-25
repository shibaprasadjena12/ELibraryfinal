package com.library.java;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
public class SendMail {

	public static void send(String fromEmail, final String username, final String password, String toEmail, String subject, String message)
	{
		try {
			
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");
			
			Session mailSession = Session.getInstance(props,
				      new javax.mail.Authenticator() {
		         protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(username, password);
		         }
		      });
			
			Message mailMessage = new MimeMessage(mailSession);
		
		
			mailMessage.setFrom(new InternetAddress(fromEmail));
			mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			mailMessage.setSubject(subject);
			mailMessage.setText(message);
			
			Transport.send(mailMessage);
			
		}
		catch (MessagingException e)
		{
            throw new RuntimeException(e);
		}


	}

}
