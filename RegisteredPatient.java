package src;

import java.sql.*;
import java.util.Scanner;


public class RegisteredPatient {
	generalDoctor gd = new generalDoctor();


	 Scanner in = new Scanner(System.in);
	 int qty1=0;
	 int qty2=0;
	 int qty3=0;
	 int qty4=0;
	 int qty5=0;
	 int number;
	 int patient_id;
	 int flag=0;
	int d_id;
	public void login(){
		try{
		  Connection con=mySqlConnection.getConnection();
		  String username="";
		  System.out.println("Enter username: ");
		  username=in.nextLine();
		  String password="";
		  System.out.println("Enter Password: ");
		  password=in.nextLine();
	
		  try {
			  String loginQuery="Select * from patient where patient_username='"+username+"' AND password='"+password+"';";
			  Statement st = con.createStatement();
			  ResultSet rst = st.executeQuery(loginQuery);
			  if(rst.next()){
				  System.out.println("Login Successfull\n");
				  patient_id=rst.getInt("patient_id");
				  System.out.println(patient_id);
	              flag=1;
			  }else{
				  System.out.println("login Failed");
			  }
	
		  } catch (Exception e) {
			  System.out.println("No account found!! CHECK username or password\n");
			  throw new RuntimeException(e);
		  }
	
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	 public void showDoctorsByName()
	 {
		 try {
			 Connection con = mySqlConnection.getConnection();
			 String name;
			 System.out.println("enter the doctors name : ");
			 name = in.nextLine();
			 System.out.println("Enter Doctor type: ");
			 String type=in.nextLine();
			 String showDoctorsQuery = "select * from Doctor where doctor_name ='"+name+"'AND doctor_type='"+type+"';";
			 
		    Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(showDoctorsQuery);
	        if(rs.next()){
				int doctor_id=rs.getInt("doctor_id");
				String doctor_name=rs.getString("doctor_name");
				String specialization=rs.getString("specialization");
				String gender= rs.getString("gender");
				String doc_type=rs.getString("doctor_type");
				System.out.format("Doctor's id: %s  Doctor's Name: %s  Doctor's Specialization: %s  Doctor's Gender: %s  Doctor's Type: %s \n",doctor_id,doctor_name,specialization,gender,doc_type);
			}
		 }
		 catch (Exception e) 
		 {
	            e.printStackTrace();
	      }
	}
	 
	 public void showDoctorByDisease(){
		 
		 try {
			 Connection con = mySqlConnection.getConnection();
			 String disease;
			 System.out.println("enter the disease name : ");
			 disease = in.nextLine();
			 System.out.println("Enter Doctor type: ");
			 String type=in.nextLine();
			 String showDoctorsByDiseaseQuery = "select * from Doctor where specialization ='"+disease+"' AND doctor_type='"+type+"';";
			 Statement st2=con.createStatement();
			 ResultSet rs=st2.executeQuery(showDoctorsByDiseaseQuery);
			 if(rs.next()){
				 int doctor_id=rs.getInt("doctor_id");
				 String doctor_name=rs.getString("doctor_name");
				 String specialization=rs.getString("specialization");
				 String gender= rs.getString("gender");
				 String doc_type=rs.getString("doctor_type");
				 System.out.format("Doctor's id: %s  Doctor's Name: %s  Doctor's Specialization: %s  Doctor's Gender: %s  Doctor's Type: %s \n",doctor_id,doctor_name,specialization,gender,doc_type);
			 }
		 }
		 catch (Exception e) 
		 {
	            e.printStackTrace();
	      }
		 
}
	 PaymentDetails py= new PaymentDetails();
	 String timeSlot;
	 public void confirmAppointment(int p_id)
	 {
		
		 try {
			 Connection con = mySqlConnection.getConnection();
			 System.out.println("Enter the doctor's id ");
			 d_id = in.nextInt();
			 p_id = patient_id;
			 System.out.println(p_id);
			 in.nextLine();
			 System.out.println("Enter the timeslot");
			 timeSlot = in.nextLine();
			String status="booked";
			 
			 String removeSlotQuery = "Insert into Appointment values ("+patient_id+","+d_id+",'"+timeSlot+"','"+status+"');" ;
			 PreparedStatement ps=con.prepareStatement(removeSlotQuery);
	         ps.execute();
			 String addAppoint="Update patient set appointment_date='"+timeSlot+"',doctor_id="+d_id+" where patient_id="+p_id+";";
			 PreparedStatement ps2= con.prepareStatement(addAppoint);
			 ps2.executeUpdate();
			 int amount=500;
			 System.out.println("Please pay Rs500 for the consultancy");
	         py.AddpaymentMethod(p_id,amount);
			 
		 }
		 catch (Exception e) 
		 {
	            e.printStackTrace();
	      }
		 System.out.println("Appointment confirmed!!!!!");
	 }
String med1="";
	 String med2="";
	 String med3="";
	 String med4="";
	 String med5="";
	 int price1;
	 int price2;
	 int price3;
	 int price4;
	 int price5;
	 public void placeOrder() throws Exception{
		 Connection con=mySqlConnection.getConnection();
		 int med1qty = 0;
		 int med2qty = 0;
		 int med3qty = 0;
		 int med4qty = 0;
		 int med5qty = 0;
		 System.out.println("Enter how many medicines do you want? (1-5)");
		 number=in.nextInt();
		 switch (number){
			 case 1:
				 try {
					 in.nextLine();
					 System.out.println("Enter medicine name: ");
					 med1=in.nextLine();
					 System.out.println("Enter 1st medicine quanity: ");
					 qty1=in.nextInt();
					 String query1 = "Select * from Medicine where medicine_name='" + med1 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs1=st.executeQuery(query1);
					if(rs1.next()) {
						price1= rs1.getInt("price");
						med1qty = rs1.getInt("medicine_qty");

						int newqty1 = med1qty - qty1;

						String updateQuery1 = "Update Medicine set medicine_qty=" + newqty1 + " where medicine_name='" + med1 + "';";
						PreparedStatement ps = con.prepareStatement(updateQuery1);
						ps.executeUpdate();
					}
				 } catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 break;
			 case 2:
				 try {
					 in.nextLine();
					 System.out.println("Enter 1st medicine name: ");
					 med1 = in.nextLine();
					 System.out.println("Enter 1st medicine quanity: ");
					 qty1 = in.nextInt();
					 String query1 = "Select * from Medicine where medicine_name='" + med1 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs1 = st.executeQuery(query1);
					 if (rs1.next()) {
						 price1= rs1.getInt("price");
						 med1qty = rs1.getInt("medicine_qty");

						 int newqty1 = med1qty - qty1;

						 String updateQuery1 = "Update Medicine set medicine_qty=" + newqty1 + " where medicine_name='" + med1 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery1);
						 ps.executeUpdate();
					 }
				 }
					 catch (Exception e) {
						 throw new RuntimeException(e);
					 }
					 try{
					 in.nextLine();
					 System.out.println("Enter 2nd medicine name: ");
					 med2=in.nextLine();
					 System.out.println("Enter 2nd medicine Quantity: ");
					 qty2=in.nextInt();
					 String query2 = "Select * from Medicine where medicine_name='" + med2 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs2=st.executeQuery(query2);
					 if(rs2.next()) {
						 price2= rs2.getInt("price");
						 med2qty = rs2.getInt("medicine_qty");
						 int newqty2 = med2qty - qty2;

						 String updateQuery2 = "Update Medicine set medicine_qty=" + newqty2 + " where medicine_name='" + med2 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery2);
						 ps.executeUpdate();
					 }
				 } catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 break;
			 case 3:
				 try {
					 in.nextLine();
					 System.out.println("Enter 1st medicine name: ");
					 med1 = in.nextLine();
					 System.out.println("Enter 1st medicine quanity: ");
					 qty1 = in.nextInt();
					 String query1 = "Select * from Medicine where medicine_name='" + med1 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs1 = st.executeQuery(query1);
					 if (rs1.next()) {
						 price1= rs1.getInt("price");
						 med1qty = rs1.getInt("medicine_qty");

						 int newqty1 = med1qty - qty1;

						 String updateQuery1 = "Update Medicine set medicine_qty=" + newqty1 + " where medicine_name='" + med1 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery1);
						 ps.executeUpdate();
					 }
				 }
				 catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 try{
					 in.nextLine();
					 System.out.println("Enter 2nd medicine name: ");
					 med2=in.nextLine();
					 System.out.println("Enter 2nd medicine Quantity: ");
					 qty2=in.nextInt();
					 String query2 = "Select * from Medicine where medicine_name='" + med2 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs2=st.executeQuery(query2);
					 if(rs2.next()) {
						 price2= rs2.getInt("price");
						 med2qty = rs2.getInt("medicine_qty");

						 int newqty2 = med2qty - qty2;

						 String updateQuery2 = "Update Medicine set medicine_qty=" + newqty2 + " where medicine_name='" + med2 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery2);
						 ps.executeUpdate();
					 }
				 } catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 try{
					 in.nextLine();
					 System.out.println("Enter 3rd medicine name: ");
					 med3=in.nextLine();
					 System.out.println("Enter 3rd medicine Quantity: ");
					 qty3=in.nextInt();
					 String query3 = "Select * from Medicine where medicine_name='" + med3 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs3=st.executeQuery(query3);
					 if(rs3.next()) {
						 price3= rs3.getInt("price");
						 med3qty = rs3.getInt("medicine_qty");

						 int newqty3 = med3qty - qty3;

						 String updateQuery2 = "Update Medicine set medicine_qty=" + newqty3 + " where medicine_name='" + med3 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery2);
						 ps.executeUpdate();
					 }
				 } catch (Exception e) {
					 throw new RuntimeException(e);
				 }
					 break;
			 case 4:
				 try {
					 in.nextLine();
					 System.out.println("Enter 1st medicine name: ");
					 med1 = in.nextLine();
					 System.out.println("Enter 1st medicine quanity: ");
					 qty1 = in.nextInt();
					 String query1 = "Select * from Medicine where medicine_name='" + med1 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs1 = st.executeQuery(query1);
					 if (rs1.next()) {
						 price1= rs1.getInt("price");
						 med1qty = rs1.getInt("medicine_qty");
						 int newqty1 = med1qty - qty1;

						 String updateQuery1 = "Update Medicine set medicine_qty=" + newqty1 + " where medicine_name='" + med1 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery1);
						 ps.executeUpdate();
					 }
				 }
				 catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 try{
					 in.nextLine();
					 System.out.println("Enter 2nd medicine name: ");
					 med2=in.nextLine();
					 System.out.println("Enter 2nd medicine Quantity: ");
					 qty2=in.nextInt();
					 String query2 = "Select * from Medicine where medicine_name='" + med2 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs2=st.executeQuery(query2);
					 if(rs2.next()) {
						 price2= rs2.getInt("price");
						 med2qty = rs2.getInt("medicine_qty");

						 int newqty2 = med2qty - qty2;

						 String updateQuery2 = "Update Medicine set medicine_qty=" + newqty2 + " where medicine_name='" + med2 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery2);
						 ps.executeUpdate();
					 }
				 } catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 try{
					 in.nextLine();
					 System.out.println("Enter 3rd medicine name: ");
					 med3=in.nextLine();
					 System.out.println("Enter 3rd medicine Quantity: ");
					 qty3=in.nextInt();
					 String query3 = "Select * from Medicine where medicine_name='" + med3 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs3=st.executeQuery(query3);
					 if(rs3.next()) {
						 price3= rs3.getInt("price");
						 med3qty = rs3.getInt("medicine_qty");

						 int newqty3 = med3qty - qty3;

						 String updateQuery2 = "Update Medicine set medicine_qty=" + newqty3 + " where medicine_name='" + med3 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery2);
						 ps.executeUpdate();
					 }
				 } catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 try{
					 in.nextLine();
					 System.out.println("Enter 4th medicine name: ");
					 med4=in.nextLine();
					 System.out.println("Enter 4th medicine Quantity: ");
					 qty4=in.nextInt();
					 String query4 = "Select * from Medicine where medicine_name='" + med4 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs4=st.executeQuery(query4);
					 if(rs4.next()) {
						 price4= rs4.getInt("price");
						 med4qty = rs4.getInt("medicine_qty");

						 int newqty4 = med4qty - qty4;

						 String updateQuery2 = "Update Medicine set medicine_qty=" + newqty4 + " where medicine_name='" + med4 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery2);
						 ps.executeUpdate();
					 }
				 } catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 break;
			 case 5:
				 try {
					 in.nextLine();
					 System.out.println("Enter 1st medicine name: ");
					 med1 = in.nextLine();
					 System.out.println("Enter 1st medicine quanity: ");
					 qty1 = in.nextInt();
					 String query1 = "Select * from Medicine where medicine_name='" + med1 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs1 = st.executeQuery(query1);
					 if (rs1.next()) {
						 price1= rs1.getInt("price");
						 med1qty = rs1.getInt("medicine_qty");

						 int newqty1 = med1qty - qty1;

						 String updateQuery1 = "Update Medicine set medicine_qty=" + newqty1 + " where medicine_name='" + med1 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery1);
						 ps.executeUpdate();
					 }
				 }
				 catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 try{
					 in.nextLine();
					 System.out.println("Enter 2nd medicine name: ");
					 med2=in.nextLine();
					 System.out.println("Enter 2nd medicine Quantity: ");
					 qty2=in.nextInt();
					 String query2 = "Select * from Medicine where medicine_name='" + med2 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs2=st.executeQuery(query2);
					 if(rs2.next()) {
						 price2= rs2.getInt("price");
						 med2qty = rs2.getInt("medicine_qty");

						 int newqty2 = med2qty - qty2;

						 String updateQuery2 = "Update Medicine set medicine_qty=" + newqty2 + " where medicine_name='" + med2 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery2);
						 ps.executeUpdate();
					 }
				 } catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 try{
					 in.nextLine();
					 System.out.println("Enter 3rd medicine name: ");
					 med3=in.nextLine();
					 System.out.println("Enter 3rd medicine Quantity: ");
					 qty3=in.nextInt();
					 String query3 = "Select * from Medicine where medicine_name='" + med3 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs3=st.executeQuery(query3);
					 if(rs3.next()) {
						 price3= rs3.getInt("price");
						 med3qty = rs3.getInt("medicine_qty");

						 int newqty3 = med3qty - qty3;

						 String updateQuery2 = "Update Medicine set medicine_qty=" + newqty3 + " where medicine_name='" + med3 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery2);
						 ps.executeUpdate();
					 }
				 } catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 try{
					 in.nextLine();
					 System.out.println("Enter 4th medicine name: ");
					 med4=in.nextLine();
					 System.out.println("Enter 4th medicine Quantity: ");
					 qty4=in.nextInt();
					 String query4 = "Select * from Medicine where medicine_name='" + med4 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs4=st.executeQuery(query4);
					 if(rs4.next()) {
						 price4= rs4.getInt("price");
						 med4qty = rs4.getInt("medicine_qty");

						 int newqty4 = med4qty - qty4;

						 String updateQuery2 = "Update Medicine set medicine_qty=" + newqty4 + " where medicine_name='" + med4 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery2);
						 ps.executeUpdate();
					 }
				 } catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 try{
					 in.nextLine();
					 System.out.println("Enter 5th medicine name: ");
					 med5=in.nextLine();
					 System.out.println("Enter 5th medicine Quantity: ");
					 qty5=in.nextInt();
					 String query5 = "Select * from Medicine where medicine_name='" + med5 + "';";
					 Statement st = con.createStatement();
					 ResultSet rs5=st.executeQuery(query5);
					 if(rs5.next()) {
						 price5= rs5.getInt("price");
						 med5qty = rs5.getInt("medicine_qty");

						 int newqty5 = med5qty - qty5;

						 String updateQuery2 = "Update Medicine set medicine_qty=" + newqty5 + " where medicine_name='" + med5 + "';";
						 PreparedStatement ps = con.prepareStatement(updateQuery2);
						 ps.executeUpdate();
					 }
				 } catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 break;

		 }
	 }
	 public void showAllDoctors() throws Exception{

		 Connection con=mySqlConnection.getConnection();
		 String showDoctorsQuery = "select * from Doctor;";
		 Statement st=con.createStatement();
		 ResultSet rst1=st.executeQuery(showDoctorsQuery);
		 while(rst1.next()){
			 int doctor_id=rst1.getInt("doctor_id");
			 String doctor_name=rst1.getString("doctor_name");
			 String specialization=rst1.getString("specialization");
			 String gender= rst1.getString("gender");
			 String doc_type= rst1.getString("doctor_type");
			 System.out.format("Doctor's id: %s  Doctor's Name: %s  Doctor's Specialization: %s  Doctor's Gender: %s  Doctor's Type: %s \n",doctor_id,doctor_name,specialization,gender,doc_type);

		 }

	 }
	 public void seePrescription(){
		 gd.Prescription();
	 }
	 
}