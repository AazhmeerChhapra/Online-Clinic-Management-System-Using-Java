package src;

import java.sql.*;
import java.util.*;

public class Rider {
int delivery_boyid;
int flag=0;
Scanner in = new Scanner(System.in);
public void login() throws Exception{
	Connection con=mySqlConnection.getConnection();
	String username;
	String password;
	System.out.println("Enter your username : ");
	username = in.nextLine();

	System.out.println("Enter your password : ");
	password = in.nextLine();

	String validateRider = "select * from delivery where username = '"+username+"' AND password ='"+password+"';";
	Statement st= con.createStatement();
	ResultSet rs=st.executeQuery(validateRider);
	if(rs.next()) {
		System.out.println("You have successfully logged in !");
		delivery_boyid=rs.getInt("delivery_boyid");
		flag=1;

	}
	else {
		System.out.println("Invalid username or password");
	}
}

public void updateDeliveryStatus() throws Exception{
	 
	 try {
		 String status;
		 Connection con = mySqlConnection.getConnection();
		String deliveryStatus ="";
           System.out.println("Have you delivered the medicine :");
           System.out.println("If yes then press Y otherwise press N");
          status = in.nextLine();
           if(status.equalsIgnoreCase("Y")) {
			   deliveryStatus = "delivered";
			   System.out.println("Medicines have been delivered");
		   }
		   else if (status.equalsIgnoreCase("N")){
			   deliveryStatus = "Not Delivered";
			   System.out.println("Medicines have NOT been delivered");
		   }
        String updateDeliveryStatusQuery = "Update Delivery set delivery_status='"+deliveryStatus+"' where delivery_boyid="+delivery_boyid+";";
        PreparedStatement ps=con.prepareStatement(updateDeliveryStatusQuery);
        ps.execute();
           }
	 catch (Exception e) {
         e.printStackTrace();
     }

}


}