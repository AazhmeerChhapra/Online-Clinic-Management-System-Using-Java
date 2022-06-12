package src;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Lab {

Scanner input = new Scanner(System.in);
generalDoctor gd=new generalDoctor();
RegisteredPatient rp=new RegisteredPatient();
int test_charges;

public void labinsert(int patient_id) {
	String testName="";
	String appt_date="";
	int doctor_id;
	try {
		Connection con = mySqlConnection.getConnection();
		String test_nameQuerey ="Select * from Patient where patient_id="+patient_id+";";
		Statement st= con.createStatement();
		ResultSet rs2=st.executeQuery(test_nameQuerey);
		if (rs2.next()) {
			testName = rs2.getString("lab_test");
			appt_date = rs2.getString("appointment_date");
			doctor_id = rs2.getInt("doctor_id");
			System.out.print("Enter test charges : ");
			int testCharges = input.nextInt();
			input.nextLine();
			System.out.println("Enter patient report\n");
			String patient_report = input.nextLine();
			String labinsert = "Insert into Lab values (" + patient_id + "," + doctor_id + ",'" + testName + "'," + testCharges + ",'" + patient_report + "','" + appt_date + "');";
			PreparedStatement ps = con.prepareStatement(labinsert);
			ps.execute();

		}
	}
	catch (Exception e) {
            e.printStackTrace();
        }
}
public void doctorPerception() {
	try {
		Connection con = mySqlConnection.getConnection();


		String patientID="select from patient where ID who is here for lab test";
		String doctorP = "select from patient where pateintID = '"+patientID+"'";
		
		PreparedStatement ps=con.prepareStatement(doctorP);
		
		
	       ps.execute();
		
			
		
			
	}
	
	  catch (Exception e) {
            e.printStackTrace();
        }
}
public void generateReport(int patient_id) throws Exception{
	Connection con=mySqlConnection.getConnection();
    String appt_date="";
	String test_nameQuerey ="Select * from Patient where patient_id="+patient_id+";";
	Statement st2= con.createStatement();
	ResultSet rs2=st2.executeQuery(test_nameQuerey);
	if (rs2.next()){
		appt_date= rs2.getString("appointment_date");
	}
	String query="select * from Lab where patient_id="+patient_id+" AND appointment_date='"+appt_date+"';";
	Statement st= con.createStatement();
	ResultSet rs=st.executeQuery(query);
	while (rs.next()){
		int patient_idd=rs.getInt("patient_id");
		int doctor_id=rs.getInt("doctor_id");
		String testName=rs.getString("test_name");
		test_charges=rs.getInt("test_charges");
		String patient_report=rs.getString("patient_report");
		System.out.format("patient_id: %s  , test name:  %s , test Charges: %s , patient Report %s  \n",patient_idd,testName,test_charges,patient_report);
	}
}
public void patientLabInvoice() {
	try {
		Connection con = mySqlConnection.getConnection();


		String patientTest="select from patient where patientPerception=test_name";
		String pTestPrice = "Select from lab where testName = '"+patientTest+"'";
		if(pTestPrice != null){
			//System.out.println("Patient name : "+patient_name+"\n Test Price : "+ptestprice+);
		}
		
		PreparedStatement ps=con.prepareStatement(pTestPrice);
		
		
	       ps.execute();
		
			
		
			
	}
	
	  catch (Exception e) {
            e.printStackTrace();
        }
}
}
