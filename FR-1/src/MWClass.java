/**
 * MW Test Utility Class.
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

public class MWClass 
{

	public static void utilityMW(String airline) throws IOException, ClassNotFoundException, SQLException
	{
		
		Properties properties = new Properties();
		InputStream input = null;
		input = new FileInputStream("E:\\Cmd\\Sanjay\\Test\\FR\\config\\configMW.properties");
		properties.load(input);
		
		
		String queryMW = "UPDATE cfd_schedule_change_mode SET schedule_change_mode = 1";
		String checkTSM = "select * from schedule_state";
		String checkSCRecords = "select* from T1MWRTG.genstatus";
		
		String Url = properties.getProperty("urlTest");
		String usernameMW = properties.getProperty("usernameMW");
		String passwordMW = properties.getProperty("passwordMW");
//		String commitStmt = "commit";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		//if(airline.equalsIgnoreCase("MW"))
		{
			Connection conMW = DriverManager.getConnection(Url, usernameMW, passwordMW);
			System.out.println("Connection Established Successfully to MW DB and the DATABASE NAME IS:"+ conMW.getMetaData().getDatabaseProductName());
			Statement stmtMW = conMW.createStatement();  
			ResultSet rsTSM = stmtMW.executeQuery(checkTSM);
			boolean emptySetTSM = true;
	
			while(rsTSM.next())
			{
				emptySetTSM = false;
			}
			
			ResultSet rsSCRecords = stmtMW.executeQuery(checkSCRecords);
			boolean emptySetSCRecords = true;
			
			while(rsSCRecords.next())
			{
				emptySetSCRecords = false;
			}
			
			if(emptySetTSM == true && emptySetSCRecords == true)
			{			
	//			Statement commitSt = conMW.createStatement();
	//			String queryMW = "UPDATE cfd_schedule_change_mode SET schedule_change_mode = 1";//QueriesUtil.updateModeTest;
				System.out.println("No TSM and SC records Exist.");
				int count = stmtMW.executeUpdate(queryMW); 
				
	//			int checkCommit = stmtMW.executeUpdate(commitStmt);
	
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
	
			conMW.close();  
		}
	}
}
