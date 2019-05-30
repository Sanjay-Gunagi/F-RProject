//package com.sanjay.fr;

/**
 * This program is to implement F&R automation tasks.
 * @author Sanjay
 * @version 1.0
 * @
 * 
 */



//import com.sanjay.fr.utilqueries.*;

public class ModeChangeTest 
{

	public static void main(String[] args) throws Exception 
	{
		// TODO Auto-generated method stub
		
		String airline = args[0];
		
		/*
		 * if( airline.equalsIgnoreCase("UW")) { UWClass.utilityUW(airline); }
		 */
		
		switch (airline) 
		{
			case "UW":
				UWClass.utilityUW(airline);
			break;

			default:
				System.out.println("No airline selected.");
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
