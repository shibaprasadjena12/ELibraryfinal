package com.call.jsp;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect {
	static Connection cn;
	public static Connection getConn()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		return cn;
		
	}

}
