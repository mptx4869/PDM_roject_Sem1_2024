CREATE DATABASE  IF NOT EXISTS `invoice_bill` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `invoice_bill`;
-- MySQL dump 10.13  Distrib 8.0.40, for macos14 (arm64)
--
-- Host: 127.0.0.1    Database: invoice_bill
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Contain`
--

DROP TABLE IF EXISTS `Contain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Contain` (
  `Product_ID` int NOT NULL,
  `Invoice_ID` int NOT NULL,
  `Amount` int DEFAULT NULL,
  PRIMARY KEY (`Product_ID`,`Invoice_ID`),
  KEY `Invoice_ID` (`Invoice_ID`),
  CONSTRAINT `contain_ibfk_1` FOREIGN KEY (`Product_ID`) REFERENCES `Product` (`Product_ID`),
  CONSTRAINT `contain_ibfk_2` FOREIGN KEY (`Invoice_ID`) REFERENCES `Invoice` (`Invoice_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contain`
--

LOCK TABLES `Contain` WRITE;
/*!40000 ALTER TABLE `Contain` DISABLE KEYS */;
INSERT INTO `Contain` VALUES (1,1,2),(1,8,2),(1,19,1),(1,20,1),(2,2,4),(3,3,1),(3,7,1),(3,20,1),(4,5,1),(5,1,3),(5,2,5),(5,8,3),(5,20,1),(6,4,5),(6,9,4),(7,4,6),(7,7,7),(8,3,2),(8,6,1),(9,5,2),(9,9,1),(10,6,3),(11,19,1);
/*!40000 ALTER TABLE `Contain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `Customer_ID` int NOT NULL AUTO_INCREMENT,
  `Customer_Name` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  PRIMARY KEY (`Customer_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'Alice Green','alice.green@example.com'),(2,'Mark Brown','mark.brown@example.com'),(3,'Linda White','linda.white@example.com'),(4,'Sarah Black','sarah.black@example.com'),(5,'James Lee','james.lee@example.com'),(6,'Robert Blue','robert.blue@example.com'),(7,'Emma White','emma.white@example.com'),(8,'David Black','david.black@example.com'),(9,'Sophia Johnson','sophia.johnson@example.com'),(10,'Michael Clark','michael.clark@example.com'),(11,'Tham Le','thamle@gmail.com');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer_Phone`
--

DROP TABLE IF EXISTS `Customer_Phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer_Phone` (
  `Phone_ID` int NOT NULL AUTO_INCREMENT,
  `Customer_ID` int DEFAULT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Phone_ID`),
  KEY `Customer_ID` (`Customer_ID`),
  CONSTRAINT `customer_phone_ibfk_1` FOREIGN KEY (`Customer_ID`) REFERENCES `Customer` (`Customer_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer_Phone`
--

LOCK TABLES `Customer_Phone` WRITE;
/*!40000 ALTER TABLE `Customer_Phone` DISABLE KEYS */;
INSERT INTO `Customer_Phone` VALUES (1,1,'+84 912345678'),(2,2,'+84 987654321'),(3,3,'+84 923456789'),(4,4,'+84 934567890'),(5,5,'+84 945678901'),(6,6,'+84 956789012'),(7,7,'+84 967890123'),(8,8,'+84 978901234'),(9,9,'+84 989012345'),(10,10,'+84 990123456'),(11,11,'113');
/*!40000 ALTER TABLE `Customer_Phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Invoice`
--

DROP TABLE IF EXISTS `Invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Invoice` (
  `Invoice_ID` int NOT NULL AUTO_INCREMENT,
  `Customer_ID` int DEFAULT NULL,
  `Date` date NOT NULL,
  `Total_Amount` decimal(10,2) NOT NULL,
  `Payment_ID` int DEFAULT NULL,
  PRIMARY KEY (`Invoice_ID`),
  KEY `Customer_ID` (`Customer_ID`),
  KEY `Payment_ID` (`Payment_ID`),
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`Customer_ID`) REFERENCES `Customer` (`Customer_ID`) ON DELETE CASCADE,
  CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`Payment_ID`) REFERENCES `Payment` (`Payment_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Invoice`
--

LOCK TABLES `Invoice` WRITE;
/*!40000 ALTER TABLE `Invoice` DISABLE KEYS */;
INSERT INTO `Invoice` VALUES (1,1,'2024-01-15',130.00,1),(2,2,'2024-02-10',1250.00,2),(3,3,'2024-03-20',700.00,3),(4,4,'2024-04-25',525.00,4),(5,5,'2024-05-05',900.00,1),(6,6,'2024-06-10',600.00,2),(7,7,'2024-07-15',925.00,3),(8,8,'2024-08-01',130.00,4),(9,9,'2024-09-05',110.00,1),(19,11,'2024-12-07',200.00,3),(20,11,'2024-12-07',460.00,1);
/*!40000 ALTER TABLE `Invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Payment`
--

DROP TABLE IF EXISTS `Payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Payment` (
  `Payment_ID` int NOT NULL,
  `Payment_Method` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Payment_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
INSERT INTO `Payment` VALUES (1,'Credit Card'),(2,'PayPal'),(3,'Cash'),(4,'Debit Card');
/*!40000 ALTER TABLE `Payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Price_List`
--

DROP TABLE IF EXISTS `Price_List`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Price_List` (
  `Product_ID` int NOT NULL,
  `Date_Effective` date NOT NULL,
  `Price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`Product_ID`,`Date_Effective`),
  CONSTRAINT `price_list_ibfk_1` FOREIGN KEY (`Product_ID`) REFERENCES `Product` (`Product_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Price_List`
--

LOCK TABLES `Price_List` WRITE;
/*!40000 ALTER TABLE `Price_List` DISABLE KEYS */;
INSERT INTO `Price_List` VALUES (1,'2024-01-01',50.00),(2,'2024-01-01',300.00),(3,'2024-01-01',400.00),(4,'2024-01-01',800.00),(5,'2024-01-01',10.00),(6,'2024-01-01',15.00),(7,'2024-01-01',75.00),(8,'2024-01-01',150.00),(9,'2024-01-01',50.00),(10,'2024-01-01',150.00),(11,'2024-12-07',150.00),(12,'2024-12-07',50.00);
/*!40000 ALTER TABLE `Price_List` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Product` (
  `Product_ID` int NOT NULL AUTO_INCREMENT,
  `Product_Name` varchar(255) NOT NULL,
  `Category` varchar(100) DEFAULT NULL,
  `BarCode` varchar(100) DEFAULT NULL,
  `Price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`Product_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,'Headphones','Electronics','3344556677',50.00),(2,'Sofa','Furniture','4455667788',300.00),(3,'Washing Machine','Appliances','5566778899',400.00),(4,'Refrigerator','Appliances','6677889900',800.00),(5,'Coffee Mug','Kitchen','7788990011',10.00),(6,'T-shirt','Clothing','8899001122',15.00),(7,'Chair','Furniture','9911223344',75.00),(8,'Microwave','Appliances','1231231234',150.00),(9,'Blender','Kitchen','9876543210',50.00),(10,'Smartwatch','Electronics','5675675678',150.00),(11,'Table','..','123',150.00),(12,'Chair','Furniture ','11111111',50.00);
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-07 20:45:21
