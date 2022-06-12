package src;


import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class surgeonDoctor  extends Doctor {
    String medicine1="no medicine";
    String medicine2="no medicine";
    String medicine3="no medicine";
    String medicine4="no medicine";
    String medicine5="no medicine";
    String lab_test = "";
    int doctor_id;
int flag=0;
    Scanner in = new Scanner(System.in);
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
              String loginQuery="Select doctor_id from Doctor where doctor_username='"+username+"' AND Password='"+password+"';";
              Statement st = con.createStatement();
              ResultSet rs = st.executeQuery(loginQuery);
              if(rs.next()){
                  System.out.println("Login Successfull\n");
                  doctor_id=rs.getInt("doctor_id");
                  System.out.println(doctor_id);
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
    public void write_prescription(String time) {
        String disease="";
        try {
            Connection con = mySqlConnection.getConnection();
            int patient_id;
            System.out.println("Enter Patient id: ");
            patient_id=in.nextInt();
            in.nextLine();
            String patient_date= time;

            System.out.println("Enter Disease name: ");
            in.nextLine();
            disease=in.nextLine();
            int count;
            System.out.println("How many medicines would you like to add (1-5)\n");
            count=in.nextInt();
            if (count==1){
                System.out.println("\n Enter medicine name: ");
                in.nextLine();
                medicine1 = in.nextLine();
            }
            else if (count==2){
                System.out.println("\n Enter 1st medicine name: ");
                in.nextLine();
                medicine1 = in.nextLine();
                System.out.println("\n Enter 2nd medicine name: ");
                medicine2 = in.nextLine();
            }
            else if (count==3){
                System.out.println("\n Enter 1st medicine name: ");
                in.nextLine();
                medicine1 = in.nextLine();
                System.out.println("\n Enter 2nd medicine name: ");
                medicine2 = in.nextLine();
                System.out.println("\n Enter 3rd medicine name: ");
                medicine3 = in.nextLine();
            }
            else if (count==4){
                System.out.println("\n Enter 1st medicine name: ");
                in.nextLine();
                medicine1 = in.nextLine();
                System.out.println("\n Enter 2nd medicine name: ");
                medicine2 = in.nextLine();
                System.out.println("\n Enter 3rd medicine name: ");
                medicine3 = in.nextLine();
                System.out.println("\n Enter 4th medicine name: ");
                medicine4 = in.nextLine();
            }
            else if (count==5){
                System.out.println("\n Enter 1st medicine name: ");
                in.nextLine();
                medicine1 = in.nextLine();
                System.out.println("\n Enter 2nd medicine name: ");
                medicine2 = in.nextLine();
                System.out.println("\n Enter 3rd medicine name: ");
                medicine3 = in.nextLine();
                System.out.println("\n Enter 4th medicine name: ");
                medicine4 = in.nextLine();
                System.out.println("\n Enter 5th medicine name: ");
                medicine5 = in.nextLine();
            }
            else{
                System.out.println("Wrong Number Entered");
            }
            System.out.println("if lab test is required \n 1- Required \n 2- Not Required");
            int opt = in.nextInt();

            if (opt == 1) {

                System.out.println("\nEnter Lab test name: ");
                in.nextLine();
                lab_test = in.nextLine();
            } else {
                System.out.println("Not Required\n");
            }
            System.out.println("if Surgery is required \n 1- Required \n 2- Not Required");
            int choice = in.nextInt();
            String surgery_required="";
            String surgery_name="";
            if (choice == 1) {
                in.nextLine();
                surgery_required = "yes";
                System.out.println("\nEnter Surgery Name: ");
                surgery_name = in.nextLine();
            } else {
                surgery_required = "No";
                System.out.println(surgery_required + "\n");
            }

            String query = "update Patient set disease='"+disease+"', medicine1='" + medicine1 + "', medicine2='" + medicine2 +"', medicine3='" + medicine3 +"',medicine4='"+medicine4+"',medicine5='"+medicine5+"',lab_test='" + lab_test + "',surgery_required='"+surgery_required+"',surgery_name='"+surgery_name+"'  where appointment_date='"+patient_date+"';";
            String medicalQuery="Insert into MedicalHistory values ("+patient_id+",'"+medicine1+","+medicine2+","+medicine3+","+medicine4+","+medicine5+"');";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            String status="Treated";
            String appointQuery="Update Appointment set appointment_status='"+status+"' where patient_id="+patient_id+"AND appointment_date='"+patient_date+"';";
            PreparedStatement ps2= con.prepareStatement(appointQuery);
            ps2.executeUpdate();


            // execute the query, and get a java resultset


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Prescription() {
        try {
            int patient_id;
            System.out.println("Enter Patient id: ");
            patient_id=in.nextInt();
            Connection con = mySqlConnection.getConnection();
            String printQuery= "select * from Patient where patient_id="+patient_id+";";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(printQuery);

            while (rs.next()) {
                int p_id = rs.getInt("patient_id");
                String medicine1 = rs.getString("medicine1");
                String medicine2 = rs.getString("medicine2");
                String medicine3 = rs.getString("medicine3");
                String medicine4 = rs.getString("medicine4");
                String medicine5 = rs.getString("medicine5");
                String lab_test = rs.getString("lab_test");
                String surgery_required= rs.getNString("surgery_required");
                String surgery_name=rs.getString("surgery_name");
                System.out.format("%s, %s, %s, %s, %s, %s, %s, %s, %s \n ", p_id, medicine1,medicine2,medicine3,medicine4,medicine5, lab_test,surgery_required,surgery_name);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchPatientHistory(){
        try {
            int patient_id;
            System.out.println("Enter Patient id: ");
            patient_id=in.nextInt();
            Connection con = mySqlConnection.getConnection();
            String printQuery= "select * from Patient where patient_id="+patient_id+";";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(printQuery);

            while (rs.next()) {
                int p_id = rs.getInt("patient_id");
                String patient_name= rs.getString("patient_name");
                String disease= rs.getString("disease");
                String gender=rs.getString("gender");
                int doctor_id=rs.getInt("doctor_id");
                String dob=rs.getString("dob");
                int age= rs.getInt("age");
                String medicine1 = rs.getString("medicine1");
                String medicine2 = rs.getString("medicine2");
                String medicine3 = rs.getString("medicine3");
                String medicine4 = rs.getString("medicine4");
                String medicine5 = rs.getString("medicine5");
                String lab_test = rs.getString("lab_test");
                String surgery_name=rs.getString("surgery_name");

                System.out.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s \n ", p_id,patient_name,disease,gender,doctor_id,dob,age, medicine1,medicine2,medicine3,medicine4,medicine5, lab_test,surgery_name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showAppointedPatients(int doctor_id) throws Exception{
        Connection con=mySqlConnection.getConnection();
        String select="Select * from Appointment where doctor_id="+doctor_id+";";
        Statement st= con.createStatement();
        ResultSet rs= st.executeQuery(select);
        System.out.println("Patient ID          Appointment Date");
        while (rs.next()){
            int patient_id=rs.getInt("patient_id");
            String appoint_date=rs.getString("appointment_date");
            System.out.format("%s                %s\n",patient_id,appoint_date);
        }

    }
}



