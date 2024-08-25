package com.call.jsp;
import com.library.java.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Check {

	public static float checkFine(String tatDate)
	{
		float fine = 0;
		try {
			java.util.Date dd = new java.util.Date();
			String currentDate = dd.getDate()+" "+(dd.getMonth()+1)+" "+((dd.getYear() < 200)?(dd.getYear()+1900):(dd.getYear()));
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");

			Date dateCur = sdf.parse(currentDate);
			Date dateTat = sdf.parse(tatDate);
			
			long diff = dateCur.getTime() - dateTat.getTime();
			float days = (diff / (1000*60*60*24));

			if(days <= 0)
			{
				fine = 0;
			}
			else if(days < 5)
			{
				fine = days*1;
			}
			else if(days < 30)
			{
				fine = (days-5)*2 + 5*1;
			}
			else
			{
				fine = 200;
			}
			
			} catch (Exception e)
				{
					e.printStackTrace();
				}
		return fine;
		
	}
	
	public static void checkReturn(ResultSet rs)
	{
		try {
			java.util.Date dd = new java.util.Date();
			String currentDate = dd.getDate()+" "+(dd.getMonth()+1)+" "+((dd.getYear() < 200)?(dd.getYear()+1900):(dd.getYear()));
			
			while(rs.next())
			{
				String roll = rs.getString(1);
				String tatDate = rs.getString(3);
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");

				Date dateCur = sdf.parse(currentDate);
				Date dateTat = sdf.parse(tatDate);
				
				long diff = dateCur.getTime() - dateTat.getTime();
				float days = (diff / (1000*60*60*24));
				
				
				if(days == -1)
				{
					Connection cn = DBconnect.getConn();
					PreparedStatement ps1 = cn.prepareStatement("select email from student where roll=?");
					ps1.setString(1, roll);
					
					ResultSet rs1 = ps1.executeQuery();
					
					if(rs1.next())
					{
						String toEmail = rs1.getString(1);
						String subject = "Book Return Message";
						String message = "Dear Student\n\n\tPlease Return your Issued book (ID - "+rs.getString(2)+") as it is DUE on TOMORROW without any fine.\n\n\n\t\t\tDays :\t\t\t\tFine\n\t\t\tFirst 5 Days :\t\t\tRs.1/day\n\t\t\tNext 30 Days :\t\t\tRs.2/day\n\t\t\tOnwards\t\t\t\tRs.200";
						
						String fromEmail = "elibrary.demo2019@gmail.com";
						String username = "elibrary.demo2019@gmail.com";
						String password = "elibrary@2019";
						
						SendMail.send(fromEmail,username,password,toEmail,subject,message);
					}
				}
				if(days == 0)
				{
					Connection cn = DBconnect.getConn();
					PreparedStatement ps1 = cn.prepareStatement("select email from student where roll=?");
					ps1.setString(1, rs.getString(1));
					
					ResultSet rs1 = ps1.executeQuery();
					
					if(rs1.next())
					{
						String toEmail = rs1.getString(1);
						String subject = "Book Return Message";
						String message = "Dear Student\n\n\tPlease Return your Issued book (ID - "+rs.getString(2)+") as it is DUE on TODAY without any fine.\n\n\n\t\t\tDays :\t\t\t\tFine\n\t\t\tFirst 5 Days :\t\t\tRs.1/day\n\t\t\tNext 30 Days :\t\t\tRs.2/day\n\t\t\tOnwards\t\t\t\tRs.200";
						
						String fromEmail = "elibrary.demo2019@gmail.com";
						String username = "elibrary.demo2019@gmail.com";
						String password = "elibrary@2019";
						
						SendMail.send(fromEmail,username,password,toEmail,subject,message);
					}
				}
				else if(days > -1)
				{
					Connection cn = DBconnect.getConn();
					PreparedStatement ps1 = cn.prepareStatement("select email from student where roll=?");
					ps1.setString(1, rs.getString(1));
					
					ResultSet rs1 = ps1.executeQuery();
					
					if(rs1.next())
					{
						String toEmail = rs1.getString(1);
						String subject = "Book Return Message";
						String message = "Dear Student\n\n\tPlease Return your Issued book (ID - "+rs.getString(2)+") as it is already cross DUE date.\n\n\n\t\t\tDays :\t\t\t\tFine\n\t\t\tFirst 5 Days :\t\t\tRs.1/day\n\t\t\tNext 30 Days :\t\t\tRs.2/day\n\t\t\tOnwards\t\t\t\tRs.200";
						
						String fromEmail = "elibrary.demo2019@gmail.com";
						String username = "elibrary.demo2019@gmail.com";
						String password = "elibrary@2019";
						
						SendMail.send(fromEmail,username,password,toEmail,subject,message);
					}
				}
			}	
		} catch (Exception e) {
			
		}
		
	}


}
