package src;

import javax.swing.plaf.nimbus.State;
import java.util.*;
import java.sql.*;

public class NewPatient  {

	 Scanner in = new Scanner(System.in);
	 
	 public void createAccount() {
		 
		 try {
			 Connection con = mySqlConnection.getConnection();
			 int patient_id = 0;
			 String name = "";
			 String date_of_birth = "";
			 int age = 0;
			 String disease = "";
			 String gender = "";
			 String username = "";
			 String password = "";


			 System.out.println("Enter the patient id : ");
			 patient_id = in.nextInt();

			 System.out.println("Enter the patient name : ");
			 in.nextLine();
			 name = in.nextLine();

			 System.out.println("Enter the patient Date of Birth : ");
			 date_of_birth = in.nextLine();

			 System.out.println("Enter the patient age : ");
			 age = in.nextInt();

			 System.out.println("Enter the patient gender : ");
			 in.nextLine();
			 gender = in.nextLine();

			 System.out.println("Enter the username : ");
			 username = in.nextLine();

			 System.out.println("Enter the password : ");
			 password = in.nextLine();

				 String patientQuery = "Insert into patient(patient_id,patient_name,gender,dob,age,patient_username,password) values (" + patient_id + ",'" + name + "','" +gender+"','"+ date_of_birth + "'," + age + ",'"  + username + "','" + password + "');";
				 PreparedStatement ps = con.prepareStatement(patientQuery);
				 ps.execute();



		 }
		 catch (Exception e) {
	            e.printStackTrace();
	        }
}
	 }
