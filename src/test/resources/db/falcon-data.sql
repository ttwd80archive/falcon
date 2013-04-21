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
-- Dumping data for table `falcon_appointment`
--

LOCK TABLES `falcon_appointment` WRITE;
/*!40000 ALTER TABLE `falcon_appointment` DISABLE KEYS */;
INSERT INTO `falcon_appointment` (`id`, `service`, `appointment_date`, `create_date`, `update_date`, `create_by`, `update_by`, `location`, `staff`, `appointment_date_end`) VALUES (38,1,'2013-04-01 02:10:00',NULL,NULL,NULL,NULL,5,1,'2013-04-01 03:00:00');
INSERT INTO `falcon_appointment` (`id`, `service`, `appointment_date`, `create_date`, `update_date`, `create_by`, `update_by`, `location`, `staff`, `appointment_date_end`) VALUES (39,1,'2013-04-01 02:12:00',NULL,NULL,NULL,NULL,5,1,'2013-04-01 03:12:00');
/*!40000 ALTER TABLE `falcon_appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_appointment_patron`
--

LOCK TABLES `falcon_appointment_patron` WRITE;
/*!40000 ALTER TABLE `falcon_appointment_patron` DISABLE KEYS */;
INSERT INTO `falcon_appointment_patron` (`id`, `appointment`, `patron`) VALUES (32,38,7);
INSERT INTO `falcon_appointment_patron` (`id`, `appointment`, `patron`) VALUES (33,38,8);
INSERT INTO `falcon_appointment_patron` (`id`, `appointment`, `patron`) VALUES (34,39,9);
/*!40000 ALTER TABLE `falcon_appointment_patron` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_location`
--

LOCK TABLES `falcon_location` WRITE;
/*!40000 ALTER TABLE `falcon_location` DISABLE KEYS */;
INSERT INTO `falcon_location` (`id`, `name`, `admin`) VALUES (5,'Panic Room','titiwangsa');
/*!40000 ALTER TABLE `falcon_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_message_log`
--

LOCK TABLES `falcon_message_log` WRITE;
/*!40000 ALTER TABLE `falcon_message_log` DISABLE KEYS */;
INSERT INTO `falcon_message_log` (`id`, `sender`, `destination`, `message_type`, `message`, `sent_time`, `error_message`) VALUES (1,'inban','Titi Wangsa bin Damhore <titi.wangsa.damhore@tabuk-tech.com>','mail','test abc 123','2013-02-18 21:41:28','org.springframework.mail.MailSendException: Mail server connection failed; nested exception is javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?. Failed messages: javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?; message exceptions (1) are:\nFailed message 1: javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?');
INSERT INTO `falcon_message_log` (`id`, `sender`, `destination`, `message_type`, `message`, `sent_time`, `error_message`) VALUES (2,'inban','Titi Wangsa bin Damhore <titi.wangsa.damhore@tabuk-tech.com>','mail','aabbcc','2013-02-18 21:46:54','org.springframework.mail.MailSendException: Mail server connection failed; nested exception is javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?. Failed messages: javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?; message exceptions (1) are:\nFailed message 1: javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?');
INSERT INTO `falcon_message_log` (`id`, `sender`, `destination`, `message_type`, `message`, `sent_time`, `error_message`) VALUES (3,'inban','Titi Wangsa bin Damhore <titi.wangsa.damhore@tabuk-tech.com>','mail','aabbcc','2013-02-18 21:48:28','org.springframework.mail.MailSendException: Mail server connection failed; nested exception is javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?. Failed messages: javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?; message exceptions (1) are:\nFailed message 1: javax.mail.MessagingException: Could not connect to SMTP host: email-smtp.us-east-1.amazonaws.com, port: 587;\n  nested exception is:\n	javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?');
INSERT INTO `falcon_message_log` (`id`, `sender`, `destination`, `message_type`, `message`, `sent_time`, `error_message`) VALUES (4,'inban','Titi Wangsa bin Damhore <titi.wangsa.damhore@tabuk-tech.com>','mail','aa bb cc','2013-02-18 21:50:15','org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 Transaction failed: Missing final \'@domain\'\n; message exceptions (1) are:\nFailed message 1: com.sun.mail.smtp.SMTPSendFailedException: 554 Transaction failed: Missing final \'@domain\'\n');
INSERT INTO `falcon_message_log` (`id`, `sender`, `destination`, `message_type`, `message`, `sent_time`, `error_message`) VALUES (5,'inban','\"Titi Wangsa bin Damhore\" <titi.wangsa.damhore@tabuk-tech.com>','mail','aa bb cc','2013-02-18 21:54:44','org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 Transaction failed: Missing final \'@domain\'\n; message exceptions (1) are:\nFailed message 1: com.sun.mail.smtp.SMTPSendFailedException: 554 Transaction failed: Missing final \'@domain\'\n');
INSERT INTO `falcon_message_log` (`id`, `sender`, `destination`, `message_type`, `message`, `sent_time`, `error_message`) VALUES (6,'inban','\"Titi Wangsa bin Damhore\" <titi.wangsa.damhore@tabuk-tech.com>','mail','aa bb cc','2013-02-18 21:56:56','org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 554 Transaction failed: Missing final \'@domain\'\n; message exceptions (1) are:\nFailed message 1: com.sun.mail.smtp.SMTPSendFailedException: 554 Transaction failed: Missing final \'@domain\'\n');
INSERT INTO `falcon_message_log` (`id`, `sender`, `destination`, `message_type`, `message`, `sent_time`, `error_message`) VALUES (7,'inban','\"Titi Wangsa bin Damhore\" <titi.wangsa.damhore@tabuk-tech.com>','mail','aa bb cc','2013-02-18 22:01:18','org.springframework.mail.MailSendException: Failed to close server connection after message failures; nested exception is javax.mail.MessagingException: Can\'t send command to SMTP host;\n  nested exception is:\n	java.net.SocketException: Connection closed by remote host. Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 421 Timeout waiting for data from client.\n; message exceptions (1) are:\nFailed message 1: com.sun.mail.smtp.SMTPSendFailedException: 421 Timeout waiting for data from client.\n');
INSERT INTO `falcon_message_log` (`id`, `sender`, `destination`, `message_type`, `message`, `sent_time`, `error_message`) VALUES (8,'inban','\"Titi Wangsa bin Damhore\" <titi.wangsa.damhore@tabuk-tech.com>','mail','aa bb cc','2013-02-18 22:01:48',NULL);
INSERT INTO `falcon_message_log` (`id`, `sender`, `destination`, `message_type`, `message`, `sent_time`, `error_message`) VALUES (9,'inban','\"Titi Wangsa bin Damhore\" <titi.wangsa.damhore@tabuk-tech.com>','mail','aa bb cc dd','2013-02-18 22:02:08',NULL);
/*!40000 ALTER TABLE `falcon_message_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_patron`
--

LOCK TABLES `falcon_patron` WRITE;
/*!40000 ALTER TABLE `falcon_patron` DISABLE KEYS */;
INSERT INTO `falcon_patron` (`id`, `patron`, `admin`) VALUES (7,'inban','titiwangsa');
INSERT INTO `falcon_patron` (`id`, `patron`, `admin`) VALUES (8,'shankar','titiwangsa');
INSERT INTO `falcon_patron` (`id`, `patron`, `admin`) VALUES (9,'helmy','titiwangsa');
/*!40000 ALTER TABLE `falcon_patron` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_role`
--

LOCK TABLES `falcon_role` WRITE;
/*!40000 ALTER TABLE `falcon_role` DISABLE KEYS */;
INSERT INTO `falcon_role` (`role_name`) VALUES ('ROLE_ADMIN');
INSERT INTO `falcon_role` (`role_name`) VALUES ('ROLE_PATRON');
INSERT INTO `falcon_role` (`role_name`) VALUES ('ROLE_USER');
/*!40000 ALTER TABLE `falcon_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_service`
--

LOCK TABLES `falcon_service` WRITE;
/*!40000 ALTER TABLE `falcon_service` DISABLE KEYS */;
INSERT INTO `falcon_service` (`id`, `name`, `admin`) VALUES (1,'Service 1','titiwangsa');
INSERT INTO `falcon_service` (`id`, `name`, `admin`) VALUES (3,'Servuce 2','titiwangsa');
INSERT INTO `falcon_service` (`id`, `name`, `admin`) VALUES (4,'test service ','titiwangsa');
INSERT INTO `falcon_service` (`id`, `name`, `admin`) VALUES (5,'check up','titiwangsa');
INSERT INTO `falcon_service` (`id`, `name`, `admin`) VALUES (6,'test my serv','titiwangsa');
INSERT INTO `falcon_service` (`id`, `name`, `admin`) VALUES (7,'service inner','titiwangsa');
/*!40000 ALTER TABLE `falcon_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_staff`
--

LOCK TABLES `falcon_staff` WRITE;
/*!40000 ALTER TABLE `falcon_staff` DISABLE KEYS */;
INSERT INTO `falcon_staff` (`id`, `name`, `nric`, `admin`, `hp_tel`, `email`, `send_sms`, `send_email`, `valid`) VALUES (1,'Nini Marina','850101235066','titiwangsa','0102345672','nini@tabuk-tech.com',1,0,1);
INSERT INTO `falcon_staff` (`id`, `name`, `nric`, `admin`, `hp_tel`, `email`, `send_sms`, `send_email`, `valid`) VALUES (4,'Shima shipa','791115025095','titiwangsa','0192612624','test@nowhere.com',1,0,1);
INSERT INTO `falcon_staff` (`id`, `name`, `nric`, `admin`, `hp_tel`, `email`, `send_sms`, `send_email`, `valid`) VALUES (12,'titi wangsa','7911231231231','titiwangsa','2893748927427','1ahakjkhf',1,1,0);
/*!40000 ALTER TABLE `falcon_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_user`
--

LOCK TABLES `falcon_user` WRITE;
/*!40000 ALTER TABLE `falcon_user` DISABLE KEYS */;
INSERT INTO `falcon_user` (`username`, `password`, `name`, `email`, `phone`, `nric`, `hp_tel`, `send_sms`, `send_email`) VALUES ('butterbun','c42dad8cd3f474de4a21f50c80c04326938037cd74f0adfcfa51c39f302ba276','Butterbun User','','',NULL,NULL,NULL,NULL);
INSERT INTO `falcon_user` (`username`, `password`, `name`, `email`, `phone`, `nric`, `hp_tel`, `send_sms`, `send_email`) VALUES ('helmy','cb1fbbab478f4029d854be20c0b21ba6bf2e67a3','helmy iqbal','nowhere@gmail.com',NULL,'800110124087','0192621624',1,1);
INSERT INTO `falcon_user` (`username`, `password`, `name`, `email`, `phone`, `nric`, `hp_tel`, `send_sms`, `send_email`) VALUES ('inban','5727c6363d91fcc80a98f22a748c95fba96cc9e39110a23b9a38e6ea510476cb','Inban Iyyadurai','inban.iyyadurai@apris-solutions.com','60123684124',NULL,NULL,NULL,NULL);
INSERT INTO `falcon_user` (`username`, `password`, `name`, `email`, `phone`, `nric`, `hp_tel`, `send_sms`, `send_email`) VALUES ('melissa','f092d4ac27c31e03eebd80b8634f9d62ca88dc2263b382861374d4992a3eec20','Melissa','','',NULL,NULL,NULL,NULL);
INSERT INTO `falcon_user` (`username`, `password`, `name`, `email`, `phone`, `nric`, `hp_tel`, `send_sms`, `send_email`) VALUES ('shankar','cda98a529a2cdac1ec3b067c56ffc1ca17cd5724515b1e6f95cfeca383b4ba6b','Shankar Krishnan','shankar.krishnan@apris-solutions.com','60166884401',NULL,NULL,NULL,NULL);
INSERT INTO `falcon_user` (`username`, `password`, `name`, `email`, `phone`, `nric`, `hp_tel`, `send_sms`, `send_email`) VALUES ('titiwangsa','bb1338008e7180b5b8246884e8c37eec9ac8869fccd3340a31e9cf67b375fd6e','Titi Wangsa bin Damhore','titi.wangsa.damhore@tabuk-tech.com','60193012624',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `falcon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_user_role`
--

LOCK TABLES `falcon_user_role` WRITE;
/*!40000 ALTER TABLE `falcon_user_role` DISABLE KEYS */;
INSERT INTO `falcon_user_role` (`id`, `username`, `rolename`) VALUES (4,'butterbun','ROLE_PATRON');
INSERT INTO `falcon_user_role` (`id`, `username`, `rolename`) VALUES (5,'inban','ROLE_PATRON');
INSERT INTO `falcon_user_role` (`id`, `username`, `rolename`) VALUES (1,'inban','ROLE_USER');
INSERT INTO `falcon_user_role` (`id`, `username`, `rolename`) VALUES (8,'shankar','ROLE_PATRON');
INSERT INTO `falcon_user_role` (`id`, `username`, `rolename`) VALUES (7,'shankar','ROLE_USER');
INSERT INTO `falcon_user_role` (`id`, `username`, `rolename`) VALUES (2,'titiwangsa','ROLE_ADMIN');
INSERT INTO `falcon_user_role` (`id`, `username`, `rolename`) VALUES (3,'titiwangsa','ROLE_USER');
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

-- Dump completed on 2013-04-21 12:07:59
