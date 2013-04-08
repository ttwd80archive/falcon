-- MySQL dump 10.13  Distrib 5.5.20, for osx10.6 (i386)
--
-- Host: localhost    Database: falcon
-- ------------------------------------------------------
-- Server version	5.5.20

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
-- Table structure for table `falcon_appointment`
--

DROP TABLE IF EXISTS `falcon_appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `falcon_appointment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `service` int(11) unsigned DEFAULT NULL,
  `appointment_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `location` int(11) unsigned DEFAULT NULL,
  `staff` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_falcon_appointment_falcon_location` (`location`),
  KEY `staff` (`staff`),
  KEY `service` (`service`),
  CONSTRAINT `falcon_appointment_ibfk_2` FOREIGN KEY (`service`) REFERENCES `falcon_service` (`id`),
  CONSTRAINT `falcon_appointment_ibfk_1` FOREIGN KEY (`staff`) REFERENCES `falcon_staff` (`id`),
  CONSTRAINT `FK_falcon_appointment_falcon_location` FOREIGN KEY (`location`) REFERENCES `falcon_location` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `falcon_appointment`
--

LOCK TABLES `falcon_appointment` WRITE;
/*!40000 ALTER TABLE `falcon_appointment` DISABLE KEYS */;
INSERT INTO `falcon_appointment` VALUES (24,3,'2013-04-12 10:07:00',NULL,NULL,NULL,NULL,5,1),(25,3,'2013-04-15 03:07:00',NULL,NULL,NULL,NULL,5,1),(26,1,'2013-04-15 06:07:00',NULL,NULL,NULL,NULL,5,1),(27,1,'2013-04-15 10:07:00',NULL,NULL,NULL,NULL,5,1),(28,1,'2013-04-23 10:07:00',NULL,NULL,NULL,NULL,5,1),(29,1,'2013-04-23 13:07:00',NULL,NULL,NULL,NULL,5,1),(30,1,'2013-04-23 00:08:00',NULL,NULL,NULL,NULL,5,1),(31,1,'2013-04-23 05:08:00',NULL,NULL,NULL,NULL,5,1),(32,1,'2013-04-02 04:05:00',NULL,NULL,NULL,NULL,5,1);
/*!40000 ALTER TABLE `falcon_appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `falcon_appointment_patron`
--

DROP TABLE IF EXISTS `falcon_appointment_patron`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `falcon_appointment_patron` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `appointment` int(11) unsigned NOT NULL,
  `patron` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_falcon_patron_falcon_user` (`patron`),
  KEY `FK_falcon_patron_falcon_appointment` (`appointment`),
  CONSTRAINT `FK_falcon_patron_falcon_appointment` FOREIGN KEY (`appointment`) REFERENCES `falcon_appointment` (`id`),
  CONSTRAINT `FK_falcon_patron_falcon_user` FOREIGN KEY (`patron`) REFERENCES `falcon_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `falcon_appointment_patron`
--

LOCK TABLES `falcon_appointment_patron` WRITE;
/*!40000 ALTER TABLE `falcon_appointment_patron` DISABLE KEYS */;
INSERT INTO `falcon_appointment_patron` VALUES (19,24,'inban'),(20,25,'shankar'),(21,26,'inban'),(22,27,'shankar'),(23,28,'inban'),(24,29,'inban'),(25,30,'inban'),(26,31,'inban'),(27,32,'inban');
/*!40000 ALTER TABLE `falcon_appointment_patron` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `falcon_location`
--

DROP TABLE IF EXISTS `falcon_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `falcon_location` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 DEFAULT '',
  `admin` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `admin` (`admin`),
  CONSTRAINT `falcon_location_ibfk_1` FOREIGN KEY (`admin`) REFERENCES `falcon_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `falcon_location`
--

LOCK TABLES `falcon_location` WRITE;
/*!40000 ALTER TABLE `falcon_location` DISABLE KEYS */;
INSERT INTO `falcon_location` VALUES (5,'Panic Room','titiwangsa');
/*!40000 ALTER TABLE `falcon_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `falcon_message_log`
--

DROP TABLE IF EXISTS `falcon_message_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `falcon_message_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `sender` varchar(100) COLLATE utf8_bin NOT NULL,
  `destination` varchar(100) COLLATE utf8_bin NOT NULL,
  `message_type` varchar(10) COLLATE utf8_bin NOT NULL,
  `message` varchar(1024) COLLATE utf8_bin NOT NULL,
  `sent_time` datetime NOT NULL,
  `error_message` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `falcon_message_log`
--

LOCK TABLES `falcon_message_log` WRITE;
/*!40000 ALTER TABLE `falcon_message_log` DISABLE KEYS */;
INSERT INTO `falcon_message_log` VALUES (1,'inban','Titi Wangsa bin Damhore <titi.wangsa.damhore@tabuk-tech.com>','mail','test abc 123','2013-02-18 21:41:28','org.springframework.mail.MailSendException: Mail server connection failed; nested exception is javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?. Failed messages: javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?; message exceptions (1) are:\nFailed message 1: javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?'),(2,'inban','Titi Wangsa bin Damhore <titi.wangsa.damhore@tabuk-tech.com>','mail','aabbcc','2013-02-18 21:46:54','org.springframework.mail.MailSendException: Mail server connection failed; nested exception is javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?. Failed messages: javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?; message exceptions (1) are:\nFailed message 1: javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?'),(3,'inban','Titi Wangsa bin Damhore <titi.wangsa.damhore@tabuk-tech.com>','mail','aabbcc','2013-02-18 21:48:28','org.springframework.mail.MailSendException: Mail server connection failed; nested exception is javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?. Failed messages: javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?; message exceptions (1) are:\nFailed message 1: javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?'),(4,'inban','Titi Wangsa bin Damhore <titi.wangsa.damhore@tabuk-tech.com>','mail','aa bb cc','2013-02-18 21:50:15','org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 Transaction failed: Missing final \'@domain\'\n; message exceptions (1) are:\nFailed message 1: com.sun.mail.smtp.SMTPSendFailedException: 554 Transaction failed: Missing final \'@domain\'\n'),(5,'inban','\"Titi Wangsa bin Damhore\" <titi.wangsa.damhore@tabuk-tech.com>','mail','aa bb cc','2013-02-18 21:54:44','org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 Transaction failed: Missing final \'@domain\'\n; message exceptions (1) are:\nFailed message 1: com.sun.mail.smtp.SMTPSendFailedException: 554 Transaction failed: Missing final \'@domain\'\n'),(6,'inban','\"Titi Wangsa bin Damhore\" <titi.wangsa.damhore@tabuk-tech.com>','mail','aa bb cc','2013-02-18 21:56:56','org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 Transaction failed: Missing final \'@domain\'\n; message exceptions (1) are:\nFailed message 1: com.sun.mail.smtp.SMTPSendFailedException: 554 Transaction failed: Missing final \'@domain\'\n'),(7,'inban','\"Titi Wangsa bin Damhore\" <titi.wangsa.damhore@tabuk-tech.com>','mail','aa bb cc','2013-02-18 22:01:18','org.springframework.mail.MailSendException: Failed to close server connection after message failures; nested exception is javax.mail.MessagingException: Can\'t send command to SMTP host;\n  nested exception is:\n	java.net.SocketException: Connection closed by remote host. Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 421 Timeout waiting for data from client.\n; message exceptions (1) are:\nFailed message 1: com.sun.mail.smtp.SMTPSendFailedException: 421 Timeout waiting for data from client.\n'),(8,'inban','\"Titi Wangsa bin Damhore\" <titi.wangsa.damhore@tabuk-tech.com>','mail','aa bb cc','2013-02-18 22:01:48',NULL),(9,'inban','\"Titi Wangsa bin Damhore\" <titi.wangsa.damhore@tabuk-tech.com>','mail','aa bb cc dd','2013-02-18 22:02:08',NULL);
/*!40000 ALTER TABLE `falcon_message_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `falcon_patron`
--

DROP TABLE IF EXISTS `falcon_patron`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `falcon_patron` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `patron` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `admin` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `patron` (`patron`),
  KEY `admin` (`admin`),
  CONSTRAINT `falcon_patron_ibfk_3` FOREIGN KEY (`admin`) REFERENCES `falcon_user` (`username`),
  CONSTRAINT `falcon_patron_ibfk_2` FOREIGN KEY (`patron`) REFERENCES `falcon_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `falcon_patron`
--

LOCK TABLES `falcon_patron` WRITE;
/*!40000 ALTER TABLE `falcon_patron` DISABLE KEYS */;
INSERT INTO `falcon_patron` VALUES (7,'inban','titiwangsa'),(8,'shankar','titiwangsa'),(9,'helmy','titiwangsa');
/*!40000 ALTER TABLE `falcon_patron` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `falcon_role`
--

DROP TABLE IF EXISTS `falcon_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `falcon_role` (
  `role_name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `falcon_role`
--

LOCK TABLES `falcon_role` WRITE;
/*!40000 ALTER TABLE `falcon_role` DISABLE KEYS */;
INSERT INTO `falcon_role` VALUES ('ROLE_ADMIN'),('ROLE_PATRON'),('ROLE_USER');
/*!40000 ALTER TABLE `falcon_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `falcon_service`
--

DROP TABLE IF EXISTS `falcon_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `falcon_service` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `admin` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `admin` (`admin`),
  CONSTRAINT `falcon_service_ibfk_1` FOREIGN KEY (`admin`) REFERENCES `falcon_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `falcon_service`
--

LOCK TABLES `falcon_service` WRITE;
/*!40000 ALTER TABLE `falcon_service` DISABLE KEYS */;
INSERT INTO `falcon_service` VALUES (1,'Service 1','titiwangsa'),(3,'Servuce 2','titiwangsa'),(4,'test service ','titiwangsa'),(5,'check up','titiwangsa'),(6,'test my serv','titiwangsa'),(7,'service inner','titiwangsa');
/*!40000 ALTER TABLE `falcon_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `falcon_staff`
--

DROP TABLE IF EXISTS `falcon_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `falcon_staff` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `nric` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `admin` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `hp_tel` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `send_sms` tinyint(1) unsigned DEFAULT NULL,
  `send_email` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin` (`admin`),
  CONSTRAINT `falcon_staff_ibfk_1` FOREIGN KEY (`admin`) REFERENCES `falcon_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `falcon_staff`
--

LOCK TABLES `falcon_staff` WRITE;
/*!40000 ALTER TABLE `falcon_staff` DISABLE KEYS */;
INSERT INTO `falcon_staff` VALUES (1,'Nini Marina','850101235066','titiwangsa',NULL,'nini@tabuk-tech.com',0,0),(2,'Helmy Iqbal bin Ambotang','801115025097','titiwangsa','0192612624','test@gmail.com',1,1),(4,'Shima shipa','791115025095','titiwangsa','0192612624','test@nowhere.com',1,0),(6,'Ahmad shahirul alim','891112145675','titiwangsa','0123990988','nobody@gmail.com',1,1);
/*!40000 ALTER TABLE `falcon_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `falcon_user`
--

DROP TABLE IF EXISTS `falcon_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `falcon_user` (
  `username` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '',
  `password` char(64) COLLATE utf8_bin NOT NULL,
  `name` varchar(200) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin DEFAULT '',
  `phone` varchar(25) COLLATE utf8_bin DEFAULT '',
  `nric` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `hp_tel` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `send_sms` tinyint(1) unsigned DEFAULT NULL,
  `send_email` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `falcon_user`
--

LOCK TABLES `falcon_user` WRITE;
/*!40000 ALTER TABLE `falcon_user` DISABLE KEYS */;
INSERT INTO `falcon_user` VALUES ('butterbun','c42dad8cd3f474de4a21f50c80c04326938037cd74f0adfcfa51c39f302ba276','Butterbun User','','',NULL,NULL,NULL,NULL),('helmy','cb1fbbab478f4029d854be20c0b21ba6bf2e67a3','helmy iqbal','nowhere@gmail.com',NULL,'800110124087','0192621624',1,1),('inban','5727c6363d91fcc80a98f22a748c95fba96cc9e39110a23b9a38e6ea510476cb','Inban Iyyadurai','inban.iyyadurai@apris-solutions.com','60123684124',NULL,NULL,NULL,NULL),('melissa','f092d4ac27c31e03eebd80b8634f9d62ca88dc2263b382861374d4992a3eec20','Melissa','','',NULL,NULL,NULL,NULL),('shankar','cda98a529a2cdac1ec3b067c56ffc1ca17cd5724515b1e6f95cfeca383b4ba6b','Shankar Krishnan','shankar.krishnan@apris-solutions.com','60166884401',NULL,NULL,NULL,NULL),('titiwangsa','bb1338008e7180b5b8246884e8c37eec9ac8869fccd3340a31e9cf67b375fd6e','Titi Wangsa bin Damhore','titi.wangsa.damhore@tabuk-tech.com','60193012624',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `falcon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `falcon_user_role`
--

DROP TABLE IF EXISTS `falcon_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `falcon_user_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8_bin NOT NULL,
  `rolename` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_rolename` (`username`,`rolename`),
  KEY `FK_falcon_user_role_falcon_user` (`username`),
  KEY `rolename` (`rolename`),
  CONSTRAINT `FK_falcon_user_role_falcon_role` FOREIGN KEY (`rolename`) REFERENCES `falcon_role` (`role_name`),
  CONSTRAINT `FK_falcon_user_role_falcon_user` FOREIGN KEY (`username`) REFERENCES `falcon_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `falcon_user_role`
--

LOCK TABLES `falcon_user_role` WRITE;
/*!40000 ALTER TABLE `falcon_user_role` DISABLE KEYS */;
INSERT INTO `falcon_user_role` VALUES (4,'butterbun','ROLE_PATRON'),(5,'inban','ROLE_PATRON'),(1,'inban','ROLE_USER'),(8,'shankar','ROLE_PATRON'),(7,'shankar','ROLE_USER'),(2,'titiwangsa','ROLE_ADMIN'),(3,'titiwangsa','ROLE_USER');
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