package src;

import java.util.*;
import java.sql.*;

public class Drug {
	  Scanner in = new Scanner(System.in);
	
	  public void addDrug(){
		  try {
			  Connection con = mySqlConnection.getConnection();
		  
	    int drugID;
	    String drugName = "";
	    String expirationDate = "";
	    String manufacturedDate = "";
	   boolean drugAvailibility = false;
	   int drugPrice = (Integer)null;
	   
	   System.out.println("Enter Drug ID :");
	   in.nextLine();
	   drugID = in.nextInt();
	   
	   System.out.println("Enter Drug Name :");
	   in.nextLine();
	   drugName = in.nextLine();
	   
	   System.out.println("Enter Expiration Date :");
	   in.nextLine();
	   expirationDate = in.nextLine();
	   
	   System.out.println("Enter drug manufacture Date :");
	   in.nextLine();
	   manufacturedDate = in.nextLine();

	   System.out.println("Enter Drug Availabilty Status :");
	   drugAvailibility = in.nextBoolean();
	   
	   System.out.println("Enter Drug Price :");
	   in.nextLine();
	   drugPrice = in.nextInt();
	   
	   String AddDrugQuery="Insert into medicine values ("+drugID+",'"+drugName+"','"+expirationDate+"','"+manufacturedDate+"','"+drugAvailibility+"','"+drugPrice+"');";
	   PreparedStatement ps=con.prepareStatement(AddDrugQuery);
       ps.execute();
	   
		  }
		  catch (Exception e) {
	            e.printStackTrace();
	        }
	  }

	    
	    
}
