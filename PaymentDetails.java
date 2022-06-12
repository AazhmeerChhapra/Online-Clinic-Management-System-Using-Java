package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
public class PaymentDetails {
	
double transaction_id;
int transactionAmount;
String transaction_date;
String creditCardNumber;

Scanner in = new Scanner(System.in);

public void AddpaymentMethod(int patient_id,int amount) {
	 
	 try {
		 Connection con = mySqlConnection.getConnection();
		 String creditCardNumber = "";
		 int transactionAmount;
		 int p_id=patient_id;
		 int flag2=0;
		 while (flag2!=1) {
			 System.out.println("Enter the transaction Amount : ");
			 transactionAmount = in.nextInt();
			 if (transactionAmount == amount) {
				 in.nextLine();
				 System.out.println("Enter your credit/ debit card number :");
				 creditCardNumber = in.nextLine();
				 flag2=1;
				 String AddPaymentDetailsQuery = "Insert into payment_details(patient_id,transaction_amount,credit_card_number) values (" + p_id + "," + transactionAmount + ",'" + creditCardNumber + "');";
				 PreparedStatement ps = con.prepareStatement(AddPaymentDetailsQuery);
				 ps.execute();
				 System.out.println("Transaction Successfull");
			 } else {
				 System.out.println("Enter correct amount: ");
			 }
		 }
	 }
	 catch (Exception e) 
	 {
           e.printStackTrace();
     }
}
}
