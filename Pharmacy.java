package src;

import java.sql.*;
import java.util.*;
public class Pharmacy {
	Scanner in = new Scanner(System.in);
	Drug d = new Drug();
	generalDoctor gd = new generalDoctor();
	RegisteredPatient rp = new RegisteredPatient();
	int qty1 = rp.qty1;
	int qty2 = rp.qty2;
	int qty3 = rp.qty3;
	int qty4 = rp.qty4;
	int qty5 = rp.qty5;
	String med1=rp.med1;
	String med2= rp.med2;
	String med3=rp.med3;
	String med4=rp.med4;
	String med5=rp.med5;
	int price1=rp.price1;
	int price2=rp.price2;
	int price3=rp.price3;
	int price4=rp.price4;
	int price5=rp.price5;
	int number=rp.number;
	int total;
	int d_id;

	public void searchStock() {
		String drugName;
		try {
			Connection con = mySqlConnection.getConnection();
			System.out.println("Enter drug name you want to search ! ");
			drugName = in.nextLine();

			String searchMedicine = "Select from medicine where drugName = '" + drugName + "';";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(searchMedicine);
			if (rs.next()) {
				System.out.println("Medicine is available.");
			} else
				System.out.println("Medicine is not available");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addDrugToBill(int patient_id,int doctor_id,int number,String med1,String med2,String med3, String med4, String med5,int qty1,int qty2, int qty3, int qty4, int qty5,int price1,int price2,int price3, int price4, int price5) {
		int pid=patient_id;
		String medicine1="";
		String medicine2="";
		String medicine3="";
		String medicine4="";
		String medicine5="";
		int quantity1=qty1;
		int quantity2=qty2;
		int quantity3=qty3;
		int quantity4=qty4;
		int quantity5=qty5;
		int pr1=price1;
		int pr2=price2;
		int pr3=price3;
		int pr4=price4;
		int pr5=price5;
int numbers=number;
//		System.out.println(med1);
		try {
			Connection con = mySqlConnection.getConnection();
			String fetch="Select * from Patient where patient_id="+patient_id+";";
			Statement st= con.createStatement();
			ResultSet rs=st.executeQuery(fetch);
			if (rs.next()) {
				medicine1 = rs.getString("medicine1");
				medicine2 = rs.getString("medicine2");
				medicine3 = rs.getString("medicine3");
				medicine4 = rs.getString("medicine4");
				medicine5 = rs.getString("medicine5");
				d_id = rs.getInt("doctor_id");
			}
			switch (numbers) {
				case 1:
					System.out.println("ok");
					total = price1 * qty1;
					String insertQuery = "Insert into Pharmacy value (" + pid + "," + d_id + ",'" + medicine1 + "'," + total + ");";
					PreparedStatement ps = con.prepareStatement(insertQuery);
					ps.execute();
					System.out.println("Medicine name        Unit Price           bought Units\n");
					System.out.format("%s                     %s                   %s\n", med1, price1, price1 * qty1);
					System.out.printf("Total Price is: %d\n",total);
					break;
				case 2:
					total = (price1 * qty1) + (price2 * qty2);
					String insertQuery2 = "Insert into Pharmacy value (" + pid + "," + d_id + ",'" + medicine1 + "," + medicine2 + "'," + total + ");";
					PreparedStatement ps2 = con.prepareStatement(insertQuery2);
					ps2.execute();
					System.out.println("Medicine name        Unit Price           bought Units\n");
					System.out.format("%s                     %s                   %s\n", med1, price1, price1 * qty1);
					System.out.format("%s                     %s                   %s\n", med2, price2, price2 * qty2);
					System.out.printf("Total Price is: %d\n",total);
                    break;
				case 3:
					total = (price1 * qty1) + (price2 * qty2) + (price3 * qty3);
					String insertQuery3 = "Insert into Pharmacy value (" + pid + "," + d_id + ",'" + medicine1 + "," + medicine2 + "," + medicine3 + "'," + total + ");";
					PreparedStatement ps3 = con.prepareStatement(insertQuery3);
					ps3.execute();
					System.out.println("Medicine name        Unit Price           bought Units\n");
					System.out.format("%s                     %s                   %s\n", med1, price1, price1 * qty1);
					System.out.format("%s                     %s                   %s\n", med2, price2, price2 * qty2);
					System.out.format("%s                     %s                   %s\n", med3, price3, price3 * qty3);
					System.out.printf("Total Price is: %d\n",total);
					break;
				case 4:
					total = (price1 * qty1) + (price2 * qty2) + (price3 * qty3) + (price4 * qty4);
					String insertQuery4 = "Insert into Pharmacy value (" + pid + "," + d_id + ",'" + medicine1 + "," + medicine2 + "," + medicine3 + "," + medicine4 + "'," + total + ");";
					PreparedStatement ps4 = con.prepareStatement(insertQuery4);
					ps4.execute();
					System.out.println("Medicine name        Unit Price           bought Units\n");

					System.out.format("%s                     %s                   %s\n", med1, price1, price1 * qty1);
					System.out.format("%s                     %s                   %s\n", med2, price2, price2 * qty2);
					System.out.format("%s                     %s                   %s\n", med3, price3, price3 * qty3);
					System.out.format("%s                     %s                   %s\n", med4, price4, price4 * qty4);
					System.out.printf("Total Price is: %d\n",total);
					break;
				case 5:
					total = (price1 * qty1) + (price2 * qty2) + (price3 * qty3) + (price4 * qty4) + (price5 * qty5);
					String insertQuery5 = "Insert into Pharmacy value (" + pid + "," + d_id + ",'" + medicine1 + "," + medicine2 + "," + medicine3 + "," + medicine4 + "," + medicine5 + "'," + total + ");";
					PreparedStatement ps5 = con.prepareStatement(insertQuery5);
					ps5.execute();
					System.out.println("Medicine name        Unit Price           bought Units\n");
					System.out.format("%s                     %s                   %s\n", med1, price1, price1 * qty1);
					System.out.format("%s                     %s                   %s\n", med2, price2, price2 * qty2);
					System.out.format("%s                     %s                   %s\n", med3, price3, price3 * qty3);
					System.out.format("%s                     %s                   %s\n", med4, price4, price4 * qty4);
					System.out.format("%s                     %s                   %s\n", med5, price5, price5 * qty5);
					System.out.printf("Total Price is: %d\n",total);
                    break;

			}


		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void bill() {
		try {
			Connection con = mySqlConnection.getConnection();


			String drugName = "Select drugname from pharmacybill ;";

			PreparedStatement ps = con.prepareStatement(drugName);

			String price = "select sum(saleprice) from pharmacybill ;";
			ps.execute();

			String addMedicine = "Insert into pharmacyBill values ('" + drugName + "','" + price + "'";


		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
