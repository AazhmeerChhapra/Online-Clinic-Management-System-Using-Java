package src;
import java.sql.*;
import java.util.*;
import java.util.Date;



public class generalDoctor extends Doctor {

    Scanner in = new Scanner(System.in);
    String medicine1="no medicine";
    String medicine2="no medicine";
    String medicine3="no medicine";
    String medicine4="no medicine";
    String medicine5="no medicine";
    String lab_test = "";

    String patient_date="";
    int doctor_id;
    int flag=0;
//    RegisteredPatient rp= new RegisteredPatient();
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
          String loginQuery="Select * from Doctor where doctor_username='"+username+"' AND Password='"+password+"';";
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

        try {
            Connection con = mySqlConnection.getConnection();
            int patient_id;
            String disease="";
            String sel="";
            System.out.println("Enter Patient id: ");
            patient_id=in.nextInt();
            String appQuery="Select * from Patient where patient_id="+patient_id+";";
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(appQuery);
            if (rs.next()){
                patient_date= rs.getString("appointment_date");
            }
in.nextLine();
System.out.println("Enter Disease name: ");
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

            String query = "update Patient set disease='"+disease+"',appointment_date='"+patient_date+"', medicine1='" + medicine1 + "', medicine2='" + medicine2 +"', medicine3='" + medicine3 +"',medicine4='"+medicine4+"',medicine5='"+medicine5+"',lab_test='" + lab_test + "'  where patient_id="+patient_id+";";
            String medicalQuery="Insert into MedicalHistory values ("+patient_id+",'"+medicine1+","+medicine2+","+medicine3+","+medicine4+","+medicine5+"');";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            PreparedStatement pp=con.prepareStatement(medicalQuery);
            pp.execute();
            String status="Treated";
            String appointQuery="Update Appointment set appointment_status='"+status+"' where appointment_date='"+patient_date+"';";
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
                String medicinee1 = rs.getString("medicine1");
                String medicinee2 = rs.getString("medicine2");
                String medicinee3 = rs.getString("medicine3");
                String medicinee4 = rs.getString("medicine4");
                String medicinee5 = rs.getString("medicine5");
                String lab_testt = rs.getString("lab_test");
                System.out.format("%s, %s, %s, %s, %s, %s, %s \n ", p_id, medicinee1,medicinee2,medicinee3,medicinee4,medicinee5, lab_testt);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchPatientHistory(){
        try {
            int patient_id;
            System.out.println("Enter Patient id: ");
            patient_id = in.nextInt();
            Connection con = mySqlConnection.getConnection();
            String printQuery = "select * from MedicalHistory where patient_id=" + patient_id + ";";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(printQuery);
            while (rs.next()) {
                    int p_id = rs.getInt("patient_id");
                    String medicine5 = rs.getString("medicine_name");
                    System.out.format("%s, %s \n ",p_id,medicine5);
            }

        }catch (Exception e) {
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


