DROP DATABASE IF EXISTS Wholesale_Distributor;
CREATE DATABASE IF NOT EXISTS Wholesale_Distributor;
SHOW DATABASES ;
USE Wholesale_Distributor;
#-------------------


DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    CustId VARCHAR(6),
    CustTitle VARCHAR(5) NOT NULL DEFAULT '-',
    CustName VARCHAR(30) NOT NULL DEFAULT 'Unknown',
    CustAddress VARCHAR(30),
    City VARCHAR(20),
    province VARCHAR(20),
    PostalCode VARCHAR(9),
    CONSTRAINT PRIMARY KEY (CustId)
    );
SHOW TABLES ;
DESCRIBE Customer;
#---------------------

DROP TABLE IF EXISTS `Order`;
CREATE TABLE IF NOT EXISTS `Order`(
    OrderID VARCHAR(6),
    OrderDate DATE,
    CustID VARCHAR(6),
    cost DOUBLE ,
    CONSTRAINT PRIMARY KEY (OrderID),
    CONSTRAINT FOREIGN KEY (CustID) REFERENCES Customer(CustId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE `Order`;
#-----------------------

DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item(
    ItemCode VARCHAR(6),
    Description VARCHAR(50),
    PackSize VARCHAR(20),
    UnitPrice DOUBLE DEFAULT 0.00,
    QtyOnHand INT(5),
    FirstQtyOnHand INT(5),
    CONSTRAINT PRIMARY KEY (ItemCode)
    );
SHOW TABLES ;
DESCRIBE Item;
#------------------------

DROP TABLE IF EXISTS `Order Detail`;
CREATE TABLE IF NOT EXISTS `Order Detail`(
    OrderID VARCHAR(6),
    ItemCode VARCHAR(6),
    OrderQTY INT(11),
    Discount DOUBLE,
    CONSTRAINT PRIMARY KEY (OrderID, ItemCode),
    CONSTRAINT FOREIGN KEY (ItemCode) REFERENCES Item(ItemCode) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (OrderID) REFERENCES `Order`(OrderID) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE `Order Detail`;
#---------------------------

SELECT * FROM Item;
SELECT * FROM Customer;
SELECT * FROM `Order`;
SELECT * FROM `Order Detail`;


#--------------------------------------------Another Database DBM final Practical Exam Training------------------------------------------------
DROP DATABASE IF EXISTS EXAM2021;
CREATE  DATABASE IF NOT  EXISTS EXAM2021;

SHOW DATABASES;

USE EXAM2021;

DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    CID VARCHAR(6),
    Name VARCHAR(10) NOT NULL,
    Address TEXT NOT NULL,
    Contact INT NOT NULL,
    Email TEXT NOT NULL,
    CONSTRAINT PRIMARY KEY (CID)
    );
SHOW TABLES ;
DESCRIBE Table_employee_detail;

INSERT INTO Customer VALUES ('C01','Nimal','No:20,Galle',0772266363,'Nimal@yahoo.com'),
('C02','Sasanka','No:30,Galle',0736636636,'Sas@yahoo.com'),
('C03','Lakshman','No:39',0772737377,'Laks@ijse.lk'),
('C04','Mohan','No:90,Galle',0773664772,'Mohan@ijse.lk');

SELECT * FROM Customer;


DROP TABLE IF EXISTS Project;
CREATE TABLE IF NOT EXISTS Project(
    PID VARCHAR(6),
    CID VARCHAR(6) NOT NULL,
    EstimatedCost DECIMAL(10,2) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    CONSTRAINT PRIMARY KEY (PID),
    CONSTRAINT FOREIGN KEY (CID) REFERENCES Customer (CID) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE Project;

INSERT INTO Project VALUES ('P01','C01',60000000.00,'2013-01-01','2014-01-01'),
('P02','C01',45000000.00,'2013-02-01','2014-04-20'),
('P03','C02',36000000.00,'2013-03-01','2014-01-20'),
('P04','C03',23000000.00,'2015-04-01','2015-04-02');

SELECT * FROM Project;


DROP TABLE IF EXISTS Material;
CREATE TABLE IF NOT EXISTS Material(
    MID VARCHAR(6),
    Name VARCHAR(10) NOT NULL,
    sellingPrice DECIMAL(10,2) NOT NULL,
    buyingPrice DECIMAL(10,2) NOT NULL,
    qtyOnHand INT NOT NULL,
    packSize VARCHAR(5)NOT NULL,
    CONSTRAINT PRIMARY KEY (MID)
    );
SHOW TABLES ;
DESCRIBE Material;


INSERT INTO Material VALUES ('M01','Cement-mer',700.00,625.00,40,'50kg'),
('M02','Cement-mer',750.00,650.00,50,'50kg'),
('M03','Sand',2500.00,2350.00,25,'Cube'),
('M04','Coil',2400.00,2300.00,80,'Rim');

SELECT * FROM Material;


DROP TABLE IF EXISTS Project_material_detail;
CREATE TABLE IF NOT EXISTS Project_material_detail(
    PID VARCHAR(6),
    MID VARCHAR(6),
    Date DATE NOT NULL,
    QTY INT NOT NULL,
    CONSTRAINT PRIMARY KEY (PID, MID),
    CONSTRAINT FOREIGN KEY (PID) REFERENCES Project(PID) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (MID) REFERENCES Material (MID) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE Table_employee_salary;

INSERT INTO Project_material_detail VALUES ('P01','M01','2013-03-01',5),
('P01','M03','2013-03-03',1),
('P02','M01','2013-04-04',1);

SELECT * FROM Table_employee_salary;

#--------------------------------------
CONSTRAINT FOREIGN KEY(Empid) REFERENCES Table_employee_detail(Empid) ON DELETE CASCADE ON UPDATE CASCADE
-------------------------------------------------------------------------------
SELECT C.CID,C.Name,P.PID FROM Customer C INNER JOIN Project P ON C.CID=P.CID;

#--------------------------------------

SELECT PID, CID, DATEDIFF(endDate, startDate) AS FinishDays FROM Project;

 #-----------------------------------

 SELECT C.CID,C.Name,P.PID FROM Customer C LEFT JOIN Project P ON C.CID=P.CID WHERE
 P.PID=NULL;

 SELECT CID,SUM(EstimatedCost) AS TOTAL_COST FROM Project GROUP BY CID;