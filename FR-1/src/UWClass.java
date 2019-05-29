/**
 * UW Test Utility Class.
 * @author Sanjay
 * @version 1.0
 * @
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

	public static void utilityUW(String airline) throws IOException, ClassNotFoundException, SQLException
	{
		
		Properties properties = new Properties();
		InputStream input = null;
		input = new FileInputStream("E:\\Cmd\\Sanjay\\Test\\FR\\config\\configUW.properties");
		properties.load(input);
		
		
		String queryUW = "UPDATE cfd_schedule_change_mode SET schedule_change_mode = 1";
		String checkTSM = "select * from schedule_state";
		String checkSCRecords = "select* from T1UWRTG.genstatus";
		
		String Url = properties.getProperty("urlTest");
		String usernameUW = properties.getProperty("usernameUW");
		String passwordUW = properties.getProperty("passwordUW");
//		String commitStmt = "commit";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		//if(airline.equalsIgnoreCase("UW"))
		{
			Connection conUW = DriverManager.getConnection(Url, usernameUW, passwordUW);
			System.out.println("Connection Established Successfully to UW DB and the DATABASE NAME IS:"+ conUW.getMetaData().getDatabaseProductName());
			Statement stmtUW = conUW.createStatement();  
			ResultSet rsTSM = stmtUW.executeQuery(checkTSM);
			boolean emptySetTSM = true;
	
			while(rsTSM.next())
			{
				emptySetTSM = false;
			}
			
			ResultSet rsSCRecords = stmtUW.executeQuery(checkSCRecords);
			boolean emptySetSCRecords = true;
			
			while(rsSCRecords.next())
			{
				emptySetSCRecords = false;
			}
			
			if(emptySetTSM == true && emptySetSCRecords ==true)
			{			
	//			Statement commitSt = conUW.createStatement();
	//			String queryUW = "UPDATE cfd_schedule_change_mode SET schedule_change_mode = 1";//QueriesUtil.updateModeTest;
				System.out.println("No TSM and SC records Exist.");
				int count = stmtUW.executeUpdate(queryUW); 
				
	//			int checkCommit = stmtUW.executeUpdate(commitStmt);
	
				if (count > 0)
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
//			ResultSet rsUW = stmtUW.executeQuery(queryUW); 
//			while(rsUW.next())  
//			System.out.println(rsUW.getString(1));  
	
			conUW.close();  
		}
	}
}