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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UWClass 
{
	public static void utilityUW(String airline, Logger log) //throws IOException, ClassNotFoundException, SQLException
	{		
		//To declare property file for DB configurations
		Properties properties = new Properties();
		InputStream input = null;
			
		//Queries Declarations 
		String queryUW;
		String checkTSM;
		String checkSCRecords;
		
		
		//To get DB configurations from property file
		String Url = null;
		String usernameUW = null;
		String passwordUW = null;
		
		
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
			
			Url = properties.getProperty("urlTest");
			usernameUW = properties.getProperty("usernameUW");
			passwordUW = properties.getProperty("passwordUW");			
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
			StringWriter sw = new StringWriter();
            e1.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
			
			log.log(Level.WARNING, exceptionAsString);
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
			StringWriter sw = new StringWriter();
            e2.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
			
			log.log(Level.WARNING, exceptionAsString);
			//loogers to be implemented.
		}
		//if(airline.equalsIgnoreCase("UW"))
		try
		{
			queryUW = "UPDATE cfd_schedule_change_mode SET schedule_change_mode = 1";
			checkTSM = "select * from schedule_state";
			checkSCRecords = "select* from T1UWRTG.genstatus";
			
			conUW = DriverManager.getConnection(Url, usernameUW, passwordUW);
			System.out.println("Connection Established Successfully to UW DB and the DATABASE NAME IS:"+ conUW.getMetaData().getDatabaseProductName());
			log.log(Level.INFO, "Connection Established Successfully to UW DB and the DATABASE NAME IS: "+ conUW.getMetaData().getDatabaseProductName());

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
				System.out.println("No TSM and SC records Exist in UW Test.");
				log.log(Level.INFO, "No TSM and SC records Exist in UW Test.");;
				
				countTemp = stmtUW.executeUpdate(queryUW); 
				
	//			int checkCommit = stmtUW.executeUpdate(commitStmt);
	
				if (countTemp > 0)
				{
					System.out.println("Schedule Change Mode is updated to manual in UW Test.");
					log.log(Level.INFO, "Schedule Change Mode is updated to manual in UW Test.");;
				}
				else
				{
					System.out.println("Not Updated.");
				}
			}
			else
			{
				System.out.println("TSM and SC records exist.");
				log.log(Level.INFO, "TSM and SC records exist in UW Test.");;
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
			StringWriter sw = new StringWriter();
            e3.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
			
			log.log(Level.WARNING, exceptionAsString);
			//loogers to be implemented.
		}
	}
}
