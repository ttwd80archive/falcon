# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.5.20)
# Database: falcon
# Generation Time: 2013-05-26 08:32:24 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table falcon_appointment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `falcon_appointment`;

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
  `appointment_date_end` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_falcon_appointment_falcon_location` (`location`),
  KEY `staff` (`staff`),
  KEY `service` (`service`),
  CONSTRAINT `falcon_appointment_ibfk_1` FOREIGN KEY (`staff`) REFERENCES `falcon_staff` (`id`),
  CONSTRAINT `falcon_appointment_ibfk_2` FOREIGN KEY (`service`) REFERENCES `falcon_service` (`id`),
  CONSTRAINT `FK_falcon_appointment_falcon_location` FOREIGN KEY (`location`) REFERENCES `falcon_location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table falcon_appointment_patron
# ------------------------------------------------------------

DROP TABLE IF EXISTS `falcon_appointment_patron`;

CREATE TABLE `falcon_appointment_patron` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `appointment` int(11) unsigned NOT NULL,
  `patron` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_falcon_patron_falcon_appointment` (`appointment`),
  KEY `patron` (`patron`),
  CONSTRAINT `falcon_appointment_patron_ibfk_1` FOREIGN KEY (`patron`) REFERENCES `falcon_patron` (`id`),
  CONSTRAINT `FK_falcon_patron_falcon_appointment` FOREIGN KEY (`appointment`) REFERENCES `falcon_appointment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table falcon_location
# ------------------------------------------------------------

DROP TABLE IF EXISTS `falcon_location`;

CREATE TABLE `falcon_location` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 DEFAULT '',
  `admin` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `valid` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin` (`admin`),
  CONSTRAINT `falcon_location_ibfk_1` FOREIGN KEY (`admin`) REFERENCES `falcon_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table falcon_message_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `falcon_message_log`;

CREATE TABLE `falcon_message_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `sender` varchar(100) COLLATE utf8_bin NOT NULL,
  `destination` varchar(100) COLLATE utf8_bin NOT NULL,
  `message_type` varchar(10) COLLATE utf8_bin NOT NULL,
  `message` varchar(1024) COLLATE utf8_bin NOT NULL,
  `sent_time` datetime NOT NULL,
  `error_message` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table falcon_patron
# ------------------------------------------------------------

DROP TABLE IF EXISTS `falcon_patron`;

CREATE TABLE `falcon_patron` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `patron` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `admin` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `patron` (`patron`),
  KEY `admin` (`admin`),
  CONSTRAINT `falcon_patron_ibfk_2` FOREIGN KEY (`patron`) REFERENCES `falcon_user` (`username`),
  CONSTRAINT `falcon_patron_ibfk_3` FOREIGN KEY (`admin`) REFERENCES `falcon_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table falcon_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `falcon_role`;

CREATE TABLE `falcon_role` (
  `role_name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table falcon_service
# ------------------------------------------------------------

DROP TABLE IF EXISTS `falcon_service`;

CREATE TABLE `falcon_service` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `admin` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `valid` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin` (`admin`),
  CONSTRAINT `falcon_service_ibfk_1` FOREIGN KEY (`admin`) REFERENCES `falcon_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table falcon_staff
# ------------------------------------------------------------

DROP TABLE IF EXISTS `falcon_staff`;

CREATE TABLE `falcon_staff` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `nric` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `admin` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `hp_tel` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `send_sms` tinyint(1) unsigned DEFAULT NULL,
  `send_email` tinyint(1) unsigned DEFAULT NULL,
  `valid` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin` (`admin`),
  CONSTRAINT `falcon_staff_ibfk_1` FOREIGN KEY (`admin`) REFERENCES `falcon_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table falcon_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `falcon_user`;

CREATE TABLE `falcon_user` (
  `username` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '',
  `password` char(64) COLLATE utf8_bin NOT NULL,
  `name` varchar(200) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '',
  `phone` varchar(25) COLLATE utf8_bin NOT NULL DEFAULT '',
  `nric` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '',
  `send_sms` tinyint(1) unsigned DEFAULT NULL,
  `send_email` tinyint(1) unsigned DEFAULT NULL,
  `valid` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `nric` (`nric`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table falcon_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `falcon_user_role`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
