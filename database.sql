# creation database 

CREATE DATABASE DataHotel;

USE DataHotel;

# creation tables

CREATE TABLE user(user_id int not null auto_increment,
first_name varchar(50) not null,
last_name varchar(50) not null,
email varchar(50) not null,
password varchar(100) not null,
cin varchar(50) not null unique ,
phone_number varchar(50) not null,
is_admin boolean default false,
primary key(user_id));

CREATE TABLE room( room_number int not null,id_admin int not null,
type_room varchar(50) not null,
available boolean default true,
night_price decimal(10,2) not null,
primary key(room_number),
FOREIGN KEY (id_admin)
REFERENCES user (user_id)
ON DELETE CASCADE
ON UPDATE CASCADE);

CREATE TABLE reservation(reserv_id int not null auto_increment,
user_reserv_id int not null,
room_reserv_id int not null,
check_in_date Date not null,
check_out_date Date not null,
person_number int not null,
primary key(reserv_id),
FOREIGN KEY (room_reserv_id)
REFERENCES ROOM(room_number)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (user_reserv_id)
REFERENCES USER(user_id)
ON DELETE CASCADE
ON UPDATE CASCADE);

CREATE TABLE invoice(invoice_id int not null auto_increment,
invoice_user_id int not null,
price decimal(10,2) not null,
payment_date Date not null,
primary key(invoice_id),
FOREIGN KEY (invoice_user_id)
REFERENCES USER(user_id)
ON DELETE CASCADE
ON UPDATE CASCADE); 


#INSERT

# INSERT ADMIN SYSTEM
INSERT INTO user(user_id,first_name,last_name,email,password,cin,phone_number,is_admin)
VALUES(1,'Admin', 'Admin', 'admin@example.com', 'admin', 'admin', '555555555',1);

INSERT INTO user(first_name,last_name,email,password,cin,phone_number)
VALUES('Jane', 'Smith', 'jane.smith@example.com', 'anotherpassword', 'CD987654', '987654321'),
('Bob', 'Johnson', 'bob.johnson@example.com', 'password456', 'IJ345678', 444555666),
('Alice', 'Martin', 'alice.martin@example.com', 'motdepasse123', 'GH789012', '111222333');

INSERT INTO room(room_number,id_admin,type_room,night_price)
values(1,1,"double",1000.00),
(2,1,"family",1000.00),
(4,1,"suite",500.00),
(5,1,"deluxe",1500.00),
(6,1,"simple",700.00);

INSERT INTO reservation(check_in_date,check_out_date,person_number,user_reserv_id,room_reserv_id)
VALUES("2024-01-03","2024-01-04",1,3,6),
("2024-01-07","2024-01-10",3,4,4),
("2024-01-10","2024-01-15",2,2,5),
("2024-02-01","2024-02-02",4,2,2);

INSERT INTO invoice (price, payment_date,invoice_user_id)
VALUES (1000.00, '2024-01-01',2),
(1500.00, '2024-01-02',3),
(500.00, '2024-01-03',4),
(700.00, '2024-01-04',2),
(1000.00, '2024-01-01',3);

