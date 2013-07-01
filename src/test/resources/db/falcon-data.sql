-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: localhost    Database: falcon
-- ------------------------------------------------------
-- Server version	5.6.10

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
/*!40000 ALTER TABLE `falcon_appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_appointment_patron`
--

LOCK TABLES `falcon_appointment_patron` WRITE;
/*!40000 ALTER TABLE `falcon_appointment_patron` DISABLE KEYS */;
/*!40000 ALTER TABLE `falcon_appointment_patron` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_feedback`
--

LOCK TABLES `falcon_feedback` WRITE;
/*!40000 ALTER TABLE `falcon_feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `falcon_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_location`
--

LOCK TABLES `falcon_location` WRITE;
/*!40000 ALTER TABLE `falcon_location` DISABLE KEYS */;
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
/*!40000 ALTER TABLE `falcon_patron` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `falcon_service`
--

LOCK TABLES `falcon_service` WRITE;
/*!40000 ALTER TABLE `falcon_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `falcon_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_staff`
--

LOCK TABLES `falcon_staff` WRITE;
/*!40000 ALTER TABLE `falcon_staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `falcon_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_user`
--

LOCK TABLES `falcon_user` WRITE;
/*!40000 ALTER TABLE `falcon_user` DISABLE KEYS */;
INSERT INTO `falcon_user` (`username`, `password`, `name`, `email`, `phone`, `nric`, `send_sms`, `send_email`, `valid`) VALUES ('sarawakshank@gmail.com','bf0d746d6cc8cd17d6683ffd4626c64988706a3312266e8fc3b37c4bf6b72304','shankar','sarawakshank@gmail.com','0222222222','999999999999',1,1,1);
INSERT INTO `falcon_user` (`username`, `password`, `name`, `email`, `phone`, `nric`, `send_sms`, `send_email`, `valid`) VALUES ('titiwangsa','bb1338008e7180b5b8246884e8c37eec9ac8869fccd3340a31e9cf67b375fd6e','Titi Wangsa bin Damhore','titi.wangsa.damhore@tabuk-tech.com','60193012624','800110125087',NULL,NULL,1);
/*!40000 ALTER TABLE `falcon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `falcon_user_role`
--

LOCK TABLES `falcon_user_role` WRITE;
/*!40000 ALTER TABLE `falcon_user_role` DISABLE KEYS */;
INSERT INTO `falcon_user_role` (`id`, `username`, `rolename`) VALUES (3,'sarawakshank@gmail.com','ROLE_ADMIN');
INSERT INTO `falcon_user_role` (`id`, `username`, `rolename`) VALUES (2,'titiwangsa','ROLE_ADMIN');
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

