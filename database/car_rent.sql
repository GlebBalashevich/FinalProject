-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: car_rent
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `car_views`
--

DROP TABLE IF EXISTS `car_views`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_views` (
  `view_id` bigint NOT NULL AUTO_INCREMENT,
  `exterior_small` varchar(255) NOT NULL,
  `exterior` varchar(255) NOT NULL,
  `interior` varchar(255) NOT NULL,
  `cars_id` bigint NOT NULL,
  PRIMARY KEY (`view_id`),
  UNIQUE KEY `car_views_view_id_uindex` (`view_id`),
  UNIQUE KEY `car_views_cars_id_uindex` (`cars_id`),
  CONSTRAINT `car_views_cars_car_id_fk` FOREIGN KEY (`cars_id`) REFERENCES `cars` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_views`
--

LOCK TABLES `car_views` WRITE;
/*!40000 ALTER TABLE `car_views` DISABLE KEYS */;
INSERT INTO `car_views` VALUES (1,'audi_q7_exterior_small.png','audi_q7_exterior.png','audi_q7_interior.png',1),(2,'bmw_3_exterior_small.png','bmw_3_exterior.png','bmw_3_interior.png',2),(3,'ford_transit_exterior_small.png','for_transit_exterior.png','ford_transit_interior.png',3),(4,'mercedes_e_exterior_small.png','mercedes_e_exterior.png','mercedes_e_interior.png',4),(5,'mercedes_vito_exterior_small.png','mercedes_vito_exterior.png','mercedes_vito_interior.png',5),(6,'skoda_octavia_exterior_small.png','skoda_octavia_exterior.png','skoda_octavia_interior.png',6),(7,'skoda_rapid_exterior_small.png','skoda_rapid_exterior.png','skoda_rapid_interior.png',7),(8,'toyota_camry_exterior_small.png','toyota_camry_exterior.png','toyoata_camry_interior.png',8),(9,'vw_golf_exterior_small.png','vw_golf_exterior.png','vw_golf_interior.png',9),(10,'vw_multivan_exterior_small.png','vw_multivan_exterior.png','vw_multivan_interior.png',10),(11,'vw_tiguan_exterior_small.png','vw_tiguan_exterior.png','vw_tiguan_interior.png',11),(12,'vw_touareg_exterior_small.png','vw_touareg_exterior.png','vw_touareg_interior.png',12);
/*!40000 ALTER TABLE `car_views` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `car_id` bigint NOT NULL AUTO_INCREMENT,
  `model` varchar(30) NOT NULL,
  `car_type` tinyint NOT NULL,
  `number_seats` tinyint NOT NULL,
  `rent_cost` int NOT NULL,
  `fuel_type` tinyint NOT NULL,
  `fuel_consumption` tinyint NOT NULL,
  `is_available` tinyint(1) NOT NULL,
  PRIMARY KEY (`car_id`),
  UNIQUE KEY `cars_car_id_uindex` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,'Audi Q7',0,5,145,0,12,1),(2,'BMW 3',1,5,90,1,13,1),(3,'Ford Transit',2,8,150,0,9,0),(4,'Mercedes E200',1,5,120,1,15,1),(5,'Mercedes Vito',2,7,180,0,12,1),(6,'Skoda Octavia',1,5,80,1,8,1),(7,'Skoda Rapid',1,5,60,1,6,1),(8,'Toyota Camry',1,5,120,1,13,0),(9,'VW Golf',1,4,50,1,6,1),(10,'VW Multivan',2,8,180,0,11,1),(11,'VW Tiguan',0,5,130,0,9,1),(12,'VW Touareg',0,7,180,0,12,1);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `date_from` bigint NOT NULL,
  `date_to` bigint NOT NULL,
  `amount` int NOT NULL,
  `order_status` tinyint NOT NULL,
  `order_car_id` bigint NOT NULL,
  `order_client_id` bigint NOT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `orders_order_id_uindex` (`order_id`),
  KEY `orders_cars_car_id_fk` (`order_car_id`),
  KEY `orders_users_user_id_fk` (`order_client_id`),
  CONSTRAINT `orders_cars_car_id_fk` FOREIGN KEY (`order_car_id`) REFERENCES `cars` (`car_id`),
  CONSTRAINT `orders_users_user_id_fk` FOREIGN KEY (`order_client_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1605128400000,1606424400000,1015,2,1,2),(2,1606165200000,1606424400000,720,2,5,3),(5,1606424400000,1606856400000,360,0,2,11),(6,1579467600000,1580504400000,1560,3,8,2),(7,1594414800000,1594501200000,360,3,10,2),(8,1608757200000,1609621200000,1980,0,5,2),(9,1607288400000,1639083600000,320,1,6,2),(10,1584219600000,1584478800000,360,3,2,2),(11,1601413200000,1601672400000,200,3,9,7),(12,1601326800000,1602018000000,540,3,7,10),(13,1600117200000,1600117200000,60,3,7,11),(14,1598821200000,1599253200000,780,3,11,6),(15,1602104400000,1602450000000,750,3,3,5),(16,1604264400000,1604696400000,720,3,4,3),(17,1602450000000,1602536400000,360,3,12,7),(18,1588194000000,1588971600000,800,3,6,5),(19,1591736400000,1592082000000,600,3,8,6),(20,1593550800000,1593982800000,780,3,11,3),(21,1593550800000,1593982800000,1080,3,12,7),(22,1586552400000,1586725200000,270,3,2,10),(23,1586898000000,1587416400000,1050,3,3,7),(24,1579035600000,1579381200000,300,3,7,10);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(65) NOT NULL,
  `user_role` tinyint NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `second_name` varchar(20) DEFAULT NULL,
  `driver_license` varchar(10) DEFAULT NULL,
  `phone_number` bigint DEFAULT NULL,
  `client_status` tinyint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `users_user_id_uindex` (`user_id`),
  UNIQUE KEY `users_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'balabasbk@gmail.com','60fe74406e7f353ed979f350f2fbb6a2e8690a5fa7d1b0c32983d1d8b3f95f67',2,NULL,NULL,NULL,NULL,NULL),(2,'balabasbk@mail.ru','c41b2f5d95f42a2647aa615ff44a4281d7a26a743086d395b54bb1ad838604f9',1,'Oleg','Borikov','3AV197082',375292020327,1),(3,'user1@bestcompany.by','bd5cf8347e036cabe6cd37323186a02ef6c3589d19daaee31eeb2ae3b1507ebe',1,'Ivan','Ivanov','1AA111111',NULL,1),(4,'user2@bestcompany.by','bd5cf8347e036cabe6cd37323186a02ef6c3589d19daaee31eeb2ae3b1507ebe',1,'Petr','Petrov','2AA222222',NULL,0),(5,'user3@bestcompany.by','bd5cf8347e036cabe6cd37323186a02ef6c3589d19daaee31eeb2ae3b1507ebe',1,'Артем','Артемов','3AA333333',375292020327,1),(6,'user4@bestcompany.by','bd5cf8347e036cabe6cd37323186a02ef6c3589d19daaee31eeb2ae3b1507ebe',1,'Alexey','Alexeev','4AA444444',375293030327,1),(7,'user5@bestcompany.by','bd5cf8347e036cabe6cd37323186a02ef6c3589d19daaee31eeb2ae3b1507ebe',1,'Victor','Lagov','5AA555555',NULL,1),(8,'user6@bestcompany.by','bd5cf8347e036cabe6cd37323186a02ef6c3589d19daaee31eeb2ae3b1507ebe',1,'Владимир','Павлов','6AA555666',375294040345,0),(9,'user8@bestcompany.by','bd5cf8347e036cabe6cd37323186a02ef6c3589d19daaee31eeb2ae3b1507ebe',1,'Andrew','Sizmov','7AA444333',NULL,2),(10,'user9@bestcompany.by','bd5cf8347e036cabe6cd37323186a02ef6c3589d19daaee31eeb2ae3b1507ebe',1,'Dmitrii','Kastuch','8AA333222',NULL,1),(11,'user10@bestcompany.by','bd5cf8347e036cabe6cd37323186a02ef6c3589d19daaee31eeb2ae3b1507ebe',1,'Олег','Дмитриев','9AA555444',375295052354,1),(13,'balabasbk@yandex.by','c41b2f5d95f42a2647aa615ff44a4281d7a26a743086d395b54bb1ad838604f9',1,'Глеб','Балашевич','7DS105744',375297072686,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-16 20:28:39
