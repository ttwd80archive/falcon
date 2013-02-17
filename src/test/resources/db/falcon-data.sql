-- MySQL dump 10.13  Distrib 5.5.28, for Win64 (x86)
--
-- Host: localhost    Database: falcon
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Dumping data for table `falcon_role`
--

LOCK TABLES `falcon_role` WRITE;
/*!40000 ALTER TABLE `falcon_role` DISABLE KEYS */;
INSERT INTO `falcon_role` (`role_name`) VALUES ('ROLE_ADMIN');
INSERT INTO `falcon_role` (`role_name`) VALUES ('ROLE_USER');
/*!40000 ALTER TABLE `falcon_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_user`
--

LOCK TABLES `falcon_user` WRITE;
/*!40000 ALTER TABLE `falcon_user` DISABLE KEYS */;
INSERT INTO `falcon_user` (`username`, `password`, `name`, `email`, `phone`) VALUES ('butterbun','c42dad8cd3f474de4a21f50c80c04326938037cd74f0adfcfa51c39f302ba276','Butterbun User','','');
INSERT INTO `falcon_user` (`username`, `password`, `name`, `email`, `phone`) VALUES ('inban','5727c6363d91fcc80a98f22a748c95fba96cc9e39110a23b9a38e6ea510476cb','Inban','','');
INSERT INTO `falcon_user` (`username`, `password`, `name`, `email`, `phone`) VALUES ('titiwangsa','bb1338008e7180b5b8246884e8c37eec9ac8869fccd3340a31e9cf67b375fd6e','Titi Wangsa bin Damhore','titi.wangsa.damhore@tabuk-tech.com','+60193012624');
/*!40000 ALTER TABLE `falcon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_user_role`
--

LOCK TABLES `falcon_user_role` WRITE;
/*!40000 ALTER TABLE `falcon_user_role` DISABLE KEYS */;
INSERT INTO `falcon_user_role` (`id`, `username`, `rolename`) VALUES (1,'inban','ROLE_USER');
/*!40000 ALTER TABLE `falcon_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

