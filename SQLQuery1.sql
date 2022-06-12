
create database oose2;
use oose2;
create table Patient(
patient_id int primary key,
patient_name varchar(255),
disease varchar(255),
gender varchar(255),
doctor_id int ,
dob date,
age int,
patient_username varchar(255),
PasswordHash BINARY(64),
foreign key(doctor_id) references Doctor(doctor_id)
);
alter table Doctormedicine
drop column PasswordHash;
alter table Doctor
add Password varchar(255);
create table Doctor(
doctor_id int primary key,
admin_name varchar(255),
doctor_name varchar(255),
specialization varchar(255),
gender varchar(255),
dob date,
age int,
doctor_username varchar(255),
PasswordHash BINARY(64),
foreign key (admin_name) references Admin(admin_username)
);

Create table Appointment(
patient_id int ,
doctor_id int ,
appointment_date datetime,
foreign key (patient_id) references Patient(patient_id),
foreign key (doctor_id) references Doctor(doctor_id)
);
Create table Payment_Details(
patient_id int,
transaction_id int primary key,
transaction_amount int,
credit_card_number varchar(255),
foreign key(patient_id) references Patient(patient_id)
);

create table Lab(
patient_id int ,
doctor_id int ,
test_name varchar(255),
test_charges int,
patient_report varbinary(1000),
foreign key(patient_id) references Patient(patient_id),
foreign key (doctor_id) references Doctor(doctor_id)
);
create table Supplier(
supplier_id int primary key,
supplier_name varchar(255) not null,
supplier_brand varchar(255) not null
);
create table Medicine(
medicine_id int,
medicine_name varchar(255) primary key,
medicine_brand varchar(255),
supplier_id int,
foreign key(supplier_id) references Supplier(supplier_id)
);
create table Pharmacy(
patient_id int ,
doctor_id int,
medicine_name varchar(255),
total_bill int,
foreign key (patient_id) references Patient(patient_id),
foreign key (doctor_id) references Doctor(doctor_id),
foreign key (medicine_name) references Medicine(medicine_name)
);
create table Admin(
admin_username varchar(255) primary key,
admin_password Binary(64) Not Null,
admin_gender varchar(255)
);
alter table Patient
add pharmacy_medicine varchar(255);
alter table Patient
add lab_test varchar(255) default "not required";
alter table Patient
add surgery_required varchar(255) default "NO";
alter table Patient
add surgery_name varchar (255) default "No Surgery";

create table Delivery(
delivery_boyid int primary key,
delovery_boyname varchar(255),
delivery_boystatus varchar(255),
patient_id int,
foreign key(patient_id) references Patient(patient_id)
);
select * from Medicine;
Insert into Patient(patient_id,patient_name,gender,doctor_id,dob,age,patient_username,PasswordHash,appointment_date) values(3,'abc','male',1,'2002-09-26',19,'abc','xess','2022-06-01');
Insert into Patient(patient_id,patient_name,disease,gender,doctor_id,dob,age,patient_username,PasswordHash) values(2,'def','piles','male',1,'2002-09-26',19,'def','xes3');
Alter table Patient 
add appointment_date datetime;
Alter table Patient 
drop column appointment_date;
Alter table Patient
add medicine1 varchar(255) default "no medicine";
Alter table Patient
add medicine2 varchar(255) default "no medicine";
Alter table Patient
add medicine3 varchar(255) default "no medicine";
Alter table Patient
add medicine4 varchar(255) default "no medicine";
Alter table Patient
add medicine5 varchar(255) default "no medicine";
Alter table Medicine 
add medicine_qty int;
create table MedicalHistory(
patient_id int,
medicine_name varchar(255),	
foreign key(patient_id) references Patient(patient_id)
);
Select medicine_qty from Medicine where medicine_name='pan';
select * from Doctor;
insert into Medicine value(0,'no medicine','abott',1,50,0);
insert into Medicine value(3,'disprin','abott',1,50);
insert into Medicine value(4,'fexet','abott',1,50);
insert into Medicine value(5,'move','abott',1,50);
Insert into Appointment values(1,1,'2022-06-09');
Drop table MedicalHistory;
Insert into Doctor values(5,'abc','jbj','piles','male','1980-05-06',42,'jbj','jbj231');
Update Medicine set medicine_qty=50 where medicine_name='pan';
Select * from Doctor ;
alter table Patient
drop column PasswordHash;
Alter table Patient
add password varchar(255) not null;
Select * from Patient;
Select * from medicine;
update Patient set password='abc1' where patient_id=1;
update Patient set password='def2' where patient_id=2;
update Patient set password='abc3' where patient_id=3;
update Patient set patient_username='aazhmeer' where patient_id=4;
update Appointment set appointment_date='2022-07-09 11:30:50' where patient_id=1;
alter table Doctor
add doctor_type varchar(255);
update Lab set test_name='He is okay just doing acting ' where patient_id=1;
delete from Doctor where doctor_id=3;
alter table Lab
add patient_report varchar(255) not null;
update Patient set lab_test='blood' where patient_id=4;
delete from Appointment where patient_id>=1 ;
alter table Appointment
drop appointment_date;
alter table Lab
add appointment_date datetime unique;
alter table Lab
add foreign key(appointment_date) references Appointment(appointment_date);
select * from Payment;
update Patient set appointment_date='2022-09-06 11:30:00' where patient_id=4;
select * from delivery;
alter table delivery
add delivery_status varchar(255);
alter table delivery
add password varchar(255);
Update delivery set password='ok2' where delivery_boyid=1;
alter table Medicine
add column price int;
Update Medicine set price=50 where medicine_name='move';
alter table Pharmacy
drop constraint pharmacy_ibfk_3;
Alter table Appointment
add appointment_status varchar(255);
Select * from Medicine;
Alter table Admin
add admin_password varchar(255);
select * from Patient;
Select * from delivery;
update Admin set admin_password='abc2' where admin_username='abc';
Update  Appointment set appointment_status='Treated' where patient_id>=1;
Select * from MedicalHistory WHERE patient_id=5;
insert into MedicalHistory values(5,'pan,disprin,synflex');
Delete from Patient where patient_id=6;
Alter table Patient
add foreign key(medicine5) references Medicine(medicine_name);
Update Patient set doctor_id=1 where patient_id=4;
Select * from Appointment;
update appointment set appointment_date=null where patient_id=3;
Alter table payment_details
add transaction_id int auto_increment primary key;
update Doctor set doctor_name='amna' where doctor_id=2;

