package com.sanjay.fr;

import java.io.FileInputStream;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

//import com.sanjay.fr.utilqueries.*;

public class PreMain {

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		
		Properties properties = new Properties();
		InputStream input = null;
		input = new FileInputStream("E:\\Cmd\\Sanjay\\Test\\FR\\config\\config.properties");
		properties.load(input);
		
		String airline = args[0];
		
		String Url = properties.getProperty("url");
		String usernameUW = properties.getProperty("usernameUW");
		String passwordUW = properties.getProperty("passwordUW");
		String usernameMW = properties.getProperty("usernameMW");
		String passwordMW = properties.getProperty("passwordMW");
		
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		if(airline.equalsIgnoreCase("UW"))
		{
			Connection conUW = DriverManager.getConnection(Url, usernameUW, passwordUW);
			

			System.out.println("Connection Established Successfully to UW DB and the DATABASE NAME IS:"+ conUW.getMetaData().getDatabaseProductName());
			//step3 create the statement object  
			Statement stmtUW = conUW.createStatement();  
			
			String queryUW = "UPDATE cfd_schedule_change_mode SET schedule_change_mode = 1";//QueriesUtil.updateModeTest;
			
			int count = stmtUW.executeUpdate(queryUW); 

			if (count > 0)
			{
				System.out.println("Updated.");
			}
			else
			{
				System.out.println("Not Updated.");
			}
//			ResultSet rsUW = stmtUW.executeQuery(queryUW); 
			
//			while(rsUW.next())  
//			System.out.println(rsUW.getString(1));  
	
			conUW.close();  
		}
		
		
		
		  
		
	}

}
