-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: restaurant
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `cost` decimal(10,3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'black tea','from kenia',50.000),(2,'green tea','from japan',50.000),(3,'coffee','from java island',100.000),(4,'cheesecake','with strawberry',250.000),(5,'tiramisu','with raspberry',300.000);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items_orders`
--

DROP TABLE IF EXISTS `items_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items_orders` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `orders_id` int(10) NOT NULL,
  `items_id` int(10) NOT NULL,
  `quantity` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `items_orders_orders_id_fk` (`orders_id`),
  KEY `items_orders_items_id_fk` (`items_id`),
  CONSTRAINT `items_orders_items_id_fk` FOREIGN KEY (`items_id`) REFERENCES `items` (`id`),
  CONSTRAINT `items_orders_orders_id_fk` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items_orders`
--

LOCK TABLES `items_orders` WRITE;
/*!40000 ALTER TABLE `items_orders` DISABLE KEYS */;
INSERT INTO `items_orders` VALUES (1,1,1,1),(2,1,4,1),(3,2,3,1),(4,2,4,1),(5,3,3,4),(6,4,1,2),(7,4,5,2),(8,5,2,1),(9,5,5,1),(10,6,1,1),(11,6,2,1),(12,6,4,1),(13,6,5,1),(14,7,1,2),(15,7,5,2);
/*!40000 ALTER TABLE `items_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `officiants`
--

DROP TABLE IF EXISTS `officiants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `officiants` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `second_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `officiants`
--

LOCK TABLES `officiants` WRITE;
/*!40000 ALTER TABLE `officiants` DISABLE KEYS */;
INSERT INTO `officiants` VALUES (1,'ivan','ivanov'),(2,'petr','petrov'),(3,'sidor','sidorov');
/*!40000 ALTER TABLE `officiants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `officiant_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `orders_officiants_id_fk` (`officiant_id`),
  CONSTRAINT `orders_officiants_id_fk` FOREIGN KEY (`officiant_id`) REFERENCES `officiants` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2019-01-08',1),(2,'2019-01-09',2),(3,'2019-01-09',3),(4,'2019-01-09',2),(5,'2019-01-10',3),(6,'2019-01-10',1),(7,'2019-01-11',2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-13  0:41:39
