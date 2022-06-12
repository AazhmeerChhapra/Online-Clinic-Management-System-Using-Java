package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class Admin {

    Scanner in=new Scanner(System.in);
    int flag=0;
    public void login(){
		try{
		  Connection con=mySqlConnection.getConnection();
		  int doctor_id;
		  String username="";
		  System.out.println("Enter username: ");
		  username=in.nextLine();
		  String password="";
		  System.out.println("Enter Password: ");
		  password=in.nextLine();
	
		  try {
			  String loginQuery="Select * from Admin where admin_username='"+username+"' AND admin_password='"+password+"';";
			  Statement st = con.createStatement();
			  ResultSet rs = st.executeQuery(loginQuery);
			  if(rs.next()){
				  System.out.println("Login Successfull\n");
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
    public void sendMessageToSupplier(){
        System.out.println("Stock is about to finish!! Provide new stock as soon as possible\n");
    }
    public void addDoctors(){
        try {
            Connection con=mySqlConnection.getConnection();
            System.out.println("Enter Doctor's id: ");
            int doctor_id=in.nextInt();
            System.out.println("Enter Admin's Name: ");
            in.nextLine();
            String admin_name=in.nextLine();
            System.out.println("Enter Doctor's Name: ");
            String doctor_name=in.nextLine();
            System.out.println("Enter Doctor's Specialization: ");
            String specialization=in.nextLine();
            System.out.println("Enter Doctor's Gender: ");
            String gender=in.nextLine();
            System.out.println("Enter Doctor's Date of Birth: ");
            String dob=in.nextLine();
            System.out.println("Enter Doctor's Age: ");
            int age=in.nextInt();
            System.out.println("Enter Doctor's type: ");
            in.nextLine();
            String type=in.nextLine();
            System.out.println("Enter Doctor's username: ");
            String username=in.nextLine();
            System.out.println("Enter Doctor's Password: ");
            String password=in.nextLine();

            String doctorQuery="Insert into Doctor values ("+doctor_id+",'"+admin_name+"','"+doctor_name+"','"+specialization+"','"+gender+"','"+dob+"',"+age+",'"+username+"','"+password+"','"+type+"');";
            PreparedStatement ps=con.prepareStatement(doctorQuery);
            ps.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showDoctors(){
        try{
            Connection con=mySqlConnection.getConnection();
            String printQuery= "select * from Doctor;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(printQuery);

            while (rs.next()) {
                int doctor_id = rs.getInt("doctor_id");
                String doctor_name = rs.getString("doctor_name");
                String specialization = rs.getString("specialization");
                String gender = rs.getString("gender");
                String dob = rs.getString("dob");
                int age= rs.getInt("age");
                String type=rs.getString("doctor_type");
                System.out.format("%s, %s, %s, %s, %s, %s, %s \n",doctor_id,doctor_name,specialization,gender,dob,age,type);
            }
        }
        catch (Exception e){e.printStackTrace();}

    }
    public void addSupplier() {
        try {
        Connection con=mySqlConnection.getConnection();
        System.out.println("Enter Supplier's id: ");
        int supplier_id=in.nextInt();
        System.out.println("Enter supplier's Name: ");
            in.nextLine();
        String supplier_name=in.nextLine();
      System.out.println("Enter Supplier's brand");
      String supplier_brand=in.nextLine();

        String supplierQuery="Insert into Supplier values ("+supplier_id+",'"+supplier_name+"','"+supplier_brand+"');";
        PreparedStatement ps=con.prepareStatement(supplierQuery);
        ps.execute();
    }
        catch (Exception e){
        e.printStackTrace();
    }
}
public void showSupplier(){
    try{
        Connection con=mySqlConnection.getConnection();
        String printSupplierQuery= "select * from Supplier;";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(printSupplierQuery);

        while (rs.next()) {
            int supplier_id = rs.getInt("supplier_id");
            String supplier_name = rs.getString("supplier_name");
            String supplier_brand = rs.getString("supplier_brand");

            System.out.format("%s, %s, %s \n",supplier_id,supplier_name,supplier_brand);
        }
    }
    catch (Exception e){e.printStackTrace();}

}

    public void addDeliver() {
        try {
            Connection con=mySqlConnection.getConnection();
            System.out.println("Enter Delivery Boy's id: ");
            int deliver_id=in.nextInt();
            System.out.println("Enter Delivery Boy's Name: ");
            in.nextLine();
            String deliveryBoyName=in.nextLine();
            System.out.println("Enter Delivery boy username: ");
            String username=in.nextLine();
            System.out.println("Enter delivery boy password: ");
            String password=in.nextLine();

            System.out.println("Enter Delivery Boy's status");
            String status=in.nextLine();
            System.out.println("patient's id to whom medicine is to be delivered");
            int patient_id=in.nextInt();


            String supplierQuery="Insert into Delivery values ("+deliver_id+",'"+deliveryBoyName+"',"+patient_id+",'"+username+"','"+password+"','"+status+"');";
            PreparedStatement ps=con.prepareStatement(supplierQuery);
            ps.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showDeliveryBoys(){
        try{
            Connection con=mySqlConnection.getConnection();
            String printDeliveryQuery= "select * from Delivery;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(printDeliveryQuery);
            System.out.println("Delivery_boyid     delivery_boyname    Delivery boy status     Patient id");
            while (rs.next()) {
                int deliver_id = rs.getInt("delivery_boyid");
                String deliver_name = rs.getString("delovery_boyname");
                String deliverStatus = rs.getString("delivery_status");
                int p_id= rs.getInt("patient_id");

                System.out.format("%s                       %s,            %s,                     %s \n",deliver_id,deliver_name,deliverStatus,p_id);
            }
        }
        catch (Exception e){e.printStackTrace();}

    }
    public void addMedicine() {
        try {
            Connection con=mySqlConnection.getConnection();
            System.out.println("Enter Medicine's id: ");
            int medicine_id=in.nextInt();
            System.out.println("Enter Medicine's Name: ");
            in.nextLine();
            String medicineName=in.nextLine();
            System.out.println("Enter Medicine_brand: ");
            String brand=in.nextLine();
            System.out.println("supplier id who provided the stock: ");
            int supplier_id=in.nextInt();
            System.out.println("Enter quantity: ");
            int qty=in.nextInt();
            System.out.println("Enter Medicine Price: ");
            int price=in.nextInt();


            String MedicineQuery="Insert into Medicine values ("+medicine_id+",'"+medicineName+"','"+brand+"',"+supplier_id+","+qty+","+price+");";
            PreparedStatement ps=con.prepareStatement(MedicineQuery);
            ps.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showMedicine(){
        try{
            Connection con=mySqlConnection.getConnection();
            String printDeliveryQuery= "select * from Medicine where medicine_id>0;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(printDeliveryQuery);
            System.out.println("Medicine Id Medicine_name Medicine_brand supplier Quantity Price");
            while (rs.next()) {
                int medicine_id = rs.getInt("medicine_id");
                String medicine_name = rs.getString("medicine_name");
                String medicine_brand = rs.getString("medicine_brand");
                int s_id= rs.getInt("supplier_id");
                int qty= rs.getInt("medicine_qty");
                int price=rs.getInt("price");

                System.out.format("%s         %s       %s         %s           %s        %s \n",medicine_id,medicine_name,medicine_brand,s_id,qty,price);
            }
        }
        catch (Exception e){e.printStackTrace();}

    }
}
