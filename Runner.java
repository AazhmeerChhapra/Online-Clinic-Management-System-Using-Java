package src;

import java.util.*;
public class Runner {
    public static void main(String[] args)  {

        Scanner in=new Scanner(System.in);
        Admin ad=new Admin();
        Pharmacy ph=new Pharmacy();
        generalDoctor gd=new generalDoctor();
        surgeonDoctor sg=new surgeonDoctor();
        RegisteredPatient rp= new RegisteredPatient();
        Lab lb=new Lab();
        PaymentDetails py=new PaymentDetails();
        Rider rd=new Rider();
        int menu;
        String doc_cat="";
        System.out.println("Enter how you would like to login:\n1-Doctor\n2-Patient\n3-Admin\n4-Rider");
        menu=in.nextInt();
        if (menu==1){
            in.nextLine();
            String menu2="";
            System.out.println("Select doctor category:\n sg- Surgeon Doctor\ngd- General doctor ");
            menu2=in.nextLine();
            if(menu2.equalsIgnoreCase("sg")){
                sg.login();
                if (sg.flag==1) {
                    int doc_opt = 0;
                    while (doc_opt != -1) {
                        int opt;
                        System.out.println("Enter what you want to do \n1-search Patient History\n2-Write Prescription\n3-Show Prescription");
                        opt = in.nextInt();
                        if (opt == 1) {
                            sg.searchPatientHistory();
                        } else if (opt == 2) {
                            sg.write_prescription(rp.timeSlot);
                        } else if (opt == 3) {

                            sg.Prescription();
                        } else if (opt == 4) {
                            try {
                                sg.showAppointedPatients(sg.doctor_id);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            System.out.println("Incorrect option selected");
                        }
                        System.out.println("Enter 0 to continue -1 to exit");
                        doc_opt=in.nextInt();
                    }
                }else {
                    System.out.println("Try Again\n");
                }
                }

             else if (menu2.equalsIgnoreCase("gd")) {
                gd.login();
                if (gd.flag == 1) {
                    int doc_opt = 0;
                    while (doc_opt != -1) {
                    int opt;
                    System.out.println("Enter what you want to do \n1-search Patient History\n2-Write Prescription\n3-Show Prescription\n4-Check Appointments");
                    opt = in.nextInt();
                    if (opt == 1) {
                     gd.searchPatientHistory();
                    } else if (opt == 2) {
                        gd.write_prescription(rp.timeSlot);
                    } else if (opt == 3) {
                        gd.Prescription();
                    } else if (opt==4) {
                        try {
                            gd.showAppointedPatients(gd.doctor_id);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("Incorrect option selected");
                    }
                        System.out.println("");
                        System.out.println("Enter 0 to continue -1 to exit");
                        doc_opt=in.nextInt();
                }}
                else {
                    System.out.println("Try Again\n");
                }
            }
            else{
                System.out.println("Not correct String typed ");
            }



        } else if (menu==2) {
            in.nextLine();
            System.out.println("Select\n 1- Registered Patient (rp)\n2- New Patient (np)");
            String p_opt=in.nextLine();
            if(p_opt.equalsIgnoreCase("np")){
                NewPatient np= new NewPatient();
                np.createAccount();
                System.out.println("\n Account created!! Now Login to profile\n");
            }
            rp.login();
            if (rp.flag==1) {
                try {
                    rp.showAllDoctors();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                int opt;
                int pat_opt = 0;
                while (pat_opt != -1){
                System.out.println("1-Search Doctor By name\n 2- Search doctor by disease\n3- Lab And Pharmacy ");
                opt = in.nextInt();
                if (opt == 1) {
                    rp.showDoctorsByName();
                    rp.confirmAppointment(rp.patient_id);
                } else if (opt == 2) {
                    rp.showDoctorByDisease();
                    rp.confirmAppointment(rp.patient_id);
                } else if (opt==3) {
                    in.nextLine();
                    System.out.println("Do you want to perform Lab test? Y or N: ");
                    String lab=in.nextLine();
                    if (lab.equalsIgnoreCase("Y")) {
                        lb.labinsert(rp.patient_id);


                        try {
                            lb.generateReport(rp.patient_id);
                            py.AddpaymentMethod(rp.patient_id,lb.test_charges);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Do you want to purchase Medicines? Y or N: ");
                    String pharmacy=in.nextLine();
                    if (pharmacy.equalsIgnoreCase("y")) {
                        try {

                            rp.placeOrder();
                            ph.addDrugToBill(rp.patient_id, rp.d_id, rp.number, rp.med1, rp.med2, rp.med3, rp.med4, rp.med5, rp.qty1, rp.qty2, rp.qty3, rp.qty4, rp.qty5, rp.price1, rp.price2, rp.price3, rp.price4, rp.price5);
                            py.AddpaymentMethod(rp.patient_id,ph.total);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                    System.out.println("Enter 0 to continue -1 to Exit: ");
                pat_opt=in.nextInt();
            }}
            else{
                System.out.println("try again\n");
            }

        } else if (menu==3) {
            ad.login();
            if (ad.flag==1) {
                int admin_opt = 0;
                while (admin_opt != -1) {
                    System.out.println("Select What you want to do:\n1-Add Doctors\n2-Show Doctors\n3-Add Supplier\n4-Show Supplier\n5-Add Delivery Boys\n6-Show Delivery Boys\n7-Add Medicine Stock\n8-Show Medicine Stock\n9- Send Stock Message to Supplier ");
                    int admin = in.nextInt();
                    if (admin == 1) {
                        int opt = 0;
                        int sel;
                        while (opt != -1) {
                            ad.addDoctors();
                            System.out.println("Enter another Doctor? 0 for yes -1 for no");
                            sel = in.nextInt();
                            opt = sel;
                        }
                    } else if (admin == 2) {
                        ad.showDoctors();
                    } else if (admin == 3) {
                        int opt = 0;
                        int sel;
                        while (opt != -1) {
                            ad.addSupplier();
                            System.out.println("Enter another Supplier? 0 for yes -1 for no");
                            sel = in.nextInt();
                            opt = sel;
                        }

                    } else if (admin == 4) {
                        ad.showSupplier();
                    } else if (admin == 5) {
                        int opt = 0;
                        int sel;
                        while (opt != -1) {
                            ad.addDeliver();
                            System.out.println("Enter another Delivery Boy? 0 for yes -1 for no");
                            sel = in.nextInt();
                            opt = sel;
                        }
                    } else if (admin == 6) {
                        ad.showDeliveryBoys();
                    } else if (admin == 7) {
                        int opt = 0;
                        int sel;
                        while (opt != -1) {
                            ad.addMedicine();
                            System.out.println("Enter another Medicine? 0 for yes -1 for no");
                            sel = in.nextInt();
                            opt = sel;
                        }


                    } else if (admin == 8) {
                        ad.showMedicine();

                    } else if (admin == 9) {
                        ad.sendMessageToSupplier();

                    }
                    System.out.println("Enter 0 to continue -1 to exit");
                    admin_opt=in.nextInt();
                }}

            else{
                    System.out.println("Try Again\n");
                }
            }
         else if (menu==4) {
            try {
                rd.login();
                if (rd.flag==1){
                    rd.updateDeliveryStatus();
                }
                else {
                    System.out.println("failed to login\n");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }
}
