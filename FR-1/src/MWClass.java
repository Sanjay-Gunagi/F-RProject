/**
 * Utility Class for MW Test.
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

public class MWClass 
{
	public static void utilityMW(String airline) //throws IOException, ClassNotFoundException, SQLException
	{		
		//To declare property file for DB configurations
		Properties properties = new Properties();
		InputStream input = null;
			
		//Queries Declarations 
		String queryMW;
		String checkTSM;
		String checkSCRecords;
		
		
		//To get DB configurations from property file
		String Url = null;
		String usernameMW = null;
		String passwordMW = null;
		
		
		//SQL connection and other declarations
		Connection conMW;
		Statement stmtMW;
		ResultSet rsTSM;
		ResultSet rsSCRecords;
		
		//Other temp declarations
		boolean emptySetTSM;
		boolean emptySetSCRecords;
		int countTemp;
		
		try
		{
			input = new FileInputStream("E:\\Cmd\\Sanjay\\Test\\FR\\config\\configMW.properties");
			properties.load(input);
			
			Url = properties.getProperty("urlTest");
			usernameMW = properties.getProperty("usernameMW");
			passwordMW = properties.getProperty("passwordMW");			
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
			//loogers to be implemented.
		}
		
//		String commitStmt = "commit";
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");  
		}
		catch(ClassNotFoundException e2)
		{
			e2.printStackTrace();
			//loogers to be implemented.
		}
		//if(airline.equalsIgnoreCase("MW"))
		try
		{
			queryMW = "UPDATE cfd_schedule_change_mode SET schedule_change_mode = 1";
			checkTSM = "select * from schedule_state";
			checkSCRecords = "select* from T1MWRTG.genstatus";
			
			conMW = DriverManager.getConnection(Url, usernameMW, passwordMW);
			System.out.println("Connection Established Successfully to MW DB and the DATABASE NAME IS:"+ conMW.getMetaData().getDatabaseProductName());
			stmtMW = conMW.createStatement();  
			rsTSM = stmtMW.executeQuery(checkTSM);
			emptySetTSM = true;
	
			while(rsTSM.next())
			{
				emptySetTSM = false;
			}
			
			rsSCRecords = stmtMW.executeQuery(checkSCRecords);
			emptySetSCRecords = true;
			
			while(rsSCRecords.next())
			{
				emptySetSCRecords = false;
			}
			
			if(emptySetTSM == true && emptySetSCRecords == true)
			{			
	//			Statement commitSt = conMW.createStatement();
	//			String queryMW = "UPDATE cfd_schedule_change_mode SET schedule_change_mode = 1";//QueriesUtil.updateModeTest;
				System.out.println("No TSM and SC records Exist.");
				countTemp = stmtMW.executeUpdate(queryMW); 
				
	//			int checkCommit = stmtMW.executeUpdate(commitStmt);
	
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
	
			conMW.close();  
		}
		catch(SQLException e3)
		{
			e3.printStackTrace();
			//loogers to be implemented.
		}
	}
}
