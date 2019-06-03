import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//package com.sanjay.fr;

/**
 * This program is to implement F&R automation tasks.
 * @author Sanjay
 * @version 1.0
 * @since 2019-05-27
 * 
 */

//import com.sanjay.fr.utilqueries.*;

public class ModeChangeTest 
{
	public static void main(String[] args) //throws Exception 
	{
		// TODO Auto-generated method stub		
		String airline = args[0];	
		
		Logger log = Logger.getLogger("log");
		FileHandler fh;
		log.setLevel(Level.INFO);
		try
		{
			fh = new FileHandler("E:\\Cmd\\Sanjay\\Test\\FR\\log.log", true);
			log.addHandler(fh);
			SimpleFormatter sf = new SimpleFormatter();
			fh.setFormatter(sf);
			//log.info("This is a log of deleted files\n");
		}
		catch (IOException e1) 
		{
			// TODO: handle exception
			e1.printStackTrace();
			StringWriter sw = new StringWriter();
            e1.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
			
			log.log(Level.WARNING, exceptionAsString);
		}
		
		/*)
		 * if( airline.equalsIgnoreCase("UW")) { UWClass.utilityUW(airline); }
		 */
		switch (airline) 
		{
			case "UW":
				log.log(Level.INFO, "Airline selected is : UW.");;
				UWClass.utilityUW(airline, log);
			break;
			
			case "MW":
				log.log(Level.INFO, "Airline selected is : UW.");;
				MWClass.utilityMW(airline, log);
			break;

			default:
				System.out.println("No airline selected.");
				log.log(Level.WARNING, "No airline selected.");;
			break;
		}
		/*
		 * String Url = properties.getProperty("urlTest"); String usernameUW =
		 * properties.getProperty("usernameUW"); String passwordUW =
		 * properties.getProperty("passwordUW"); String usernameMW =
		 * properties.getProperty("usernameMW"); String passwordMW =
		 * properties.getProperty("passwordMW");
		 * 
		 * String queryUW =
		 * "UPDATE cfd_schedule_change_mode SET schedule_change_mode = 1"; String
		 * checkTSM = "select * from schedule_state"; String checkSCRecords =
		 * "select* from T1UWRTG.genstatus";
		 * 
		 * // String commitStmt = "commit";
		 * 
		 * Class.forName("oracle.jdbc.driver.OracleDriver");
		 * if(airline.equalsIgnoreCase("UW")) { Connection conUW =
		 * DriverManager.getConnection(Url, usernameUW, passwordUW); System.out.
		 * println("Connection Established Successfully to UW DB and the DATABASE NAME IS:"
		 * + conUW.getMetaData().getDatabaseProductName()); Statement stmtUW =
		 * conUW.createStatement(); ResultSet rsTSM = stmtUW.executeQuery(checkTSM);
		 * boolean emptySetTSM = true;
		 * 
		 * while(rsTSM.next()) { emptySetTSM = false; }
		 * 
		 * ResultSet rsSCRecords = stmtUW.executeQuery(checkSCRecords); boolean
		 * emptySetSCRecords = true;
		 * 
		 * while(rsSCRecords.next()) { emptySetSCRecords = false; }
		 * 
		 * if(emptySetTSM == true && emptySetSCRecords ==true) { // Statement commitSt =
		 * conUW.createStatement(); // String queryUW =
		 * "UPDATE cfd_schedule_change_mode SET schedule_change_mode = 1";//QueriesUtil.
		 * updateModeTest; System.out.println("No TSM and SC records Exist."); int count
		 * = stmtUW.executeUpdate(queryUW);
		 * 
		 * // int checkCommit = stmtUW.executeUpdate(commitStmt);
		 * 
		 * if (count > 0) {
		 * System.out.println("Schedule Change Mode is updated to manual."); } else {
		 * System.out.println("Not Updated."); } } else {
		 * System.out.println("TSM and SC records exist."); }
		 * 
		 * if(checkCommit >0) { System.out.println("Commited"); } else {
		 * System.out.println("Commit not done."); }
		 * 
		 * // ResultSet rsUW = stmtUW.executeQuery(queryUW); // while(rsUW.next()) //
		 * System.out.println(rsUW.getString(1));
		 * 
		 * conUW.close(); }
		 */
	}
}
