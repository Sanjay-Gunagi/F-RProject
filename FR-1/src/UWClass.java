/**
 * Utility Class for UW Test.
 * @author Sanjay
 * @version 1.0
 * @since 2019-05-27
 * 
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class UWClass 
{
	public static void utilityUW(String airline) //throws IOException, ClassNotFoundException, SQLException
	{		
		//To declare property file for DB configurations
		Properties properties = new Properties();
		InputStream input = null;
			
		//Queries
		String queryUW = "UPDATE cfd_schedule_change_mode SET schedule_change_mode = 1";
		String checkTSM = "select * from schedule_state";
		String checkSCRecords = "select* from T1UWRTG.genstatus";
		
		//To get DB configurations from property file
		String Url = properties.getProperty("urlTest");
		String usernameUW = properties.getProperty("usernameUW");
		String passwordUW = properties.getProperty("passwordUW");
		
		//SQL connection and other declarations
		Connection conUW;
		Statement stmtUW;
		ResultSet rsTSM;
		ResultSet rsSCRecords;
		
		//Other temp declarations
		boolean emptySetTSM;
		boolean emptySetSCRecords;
		int countTemp;
		
		try
		{
			input = new FileInputStream("E:\\Cmd\\Sanjay\\Test\\FR\\config\\configUW.properties");
			properties.load(input);
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		
//		String commitStmt = "commit";
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");  
		}
		catch(ClassNotFoundException e2)
		{
			e2.printStackTrace();
		}
		//if(airline.equalsIgnoreCase("UW"))
		try
		{
			conUW = DriverManager.getConnection(Url, usernameUW, passwordUW);
			System.out.println("Connection Established Successfully to UW DB and the DATABASE NAME IS:"+ conUW.getMetaData().getDatabaseProductName());
			stmtUW = conUW.createStatement();  
			rsTSM = stmtUW.executeQuery(checkTSM);
			emptySetTSM = true;
	
			while(rsTSM.next())
			{
				emptySetTSM = false;
			}
			
			rsSCRecords = stmtUW.executeQuery(checkSCRecords);
			emptySetSCRecords = true;
			
			while(rsSCRecords.next())
			{
				emptySetSCRecords = false;
			}
			
			if(emptySetTSM == true && emptySetSCRecords == true)
			{			
	//			Statement commitSt = conUW.createStatement();
	//			String queryUW = "UPDATE cfd_schedule_change_mode SET schedule_change_mode = 1";//QueriesUtil.updateModeTest;
				System.out.println("No TSM and SC records Exist.");
				countTemp = stmtUW.executeUpdate(queryUW); 
				
	//			int checkCommit = stmtUW.executeUpdate(commitStmt);
	
				if (countTemp > 0)
				{
					System.out.println("Schedule Change Mode is updated to manual.");
				}
				else
				{
					System.out.println("Not Updated.");
				}
			}
			else
			{
				System.out.println("TSM and SC records exist.");
			}
			/*
			 * if(checkCommit >0) { System.out.println("Commited"); } else {
			 * System.out.println("Commit not done."); }
			 */
	
			conUW.close();  
		}
		catch(SQLException e3)
		{
			e3.printStackTrace();
		}
	}
}
