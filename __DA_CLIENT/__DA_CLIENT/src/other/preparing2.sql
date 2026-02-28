-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.2.0 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6786
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for dacs1_data
CREATE DATABASE IF NOT EXISTS `dacs1_data` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dacs1_data`;

-- Dumping structure for table dacs1_data.calendar
CREATE TABLE IF NOT EXISTS `calendar` (
  `Calendar_Id` int NOT NULL AUTO_INCREMENT,
  `User_Id` int DEFAULT NULL,
  PRIMARY KEY (`Calendar_Id`),
  KEY `User_Id` (`User_Id`),
  CONSTRAINT `calendar_ibfk_1` FOREIGN KEY (`User_Id`) REFERENCES `users` (`User_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table dacs1_data.calendar: ~0 rows (approximately)
DELETE FROM `calendar`;

-- Dumping structure for table dacs1_data.chat2p
CREATE TABLE IF NOT EXISTS `chat2p` (
  `Chat2P_Id` int NOT NULL AUTO_INCREMENT,
  `History` longtext,
  `Timing` datetime DEFAULT NULL,
  PRIMARY KEY (`Chat2P_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table dacs1_data.chat2p: ~0 rows (approximately)
DELETE FROM `chat2p`;

-- Dumping structure for table dacs1_data.chat2p_contact
CREATE TABLE IF NOT EXISTS `chat2p_contact` (
  `Chat2P_Contact_Id` int NOT NULL AUTO_INCREMENT,
  `User_Id` int DEFAULT NULL,
  `Chat2P_Id` int DEFAULT NULL,
  PRIMARY KEY (`Chat2P_Contact_Id`),
  KEY `User_Id` (`User_Id`),
  KEY `Chat2P_Id` (`Chat2P_Id`),
  CONSTRAINT `chat2p_contact_ibfk_1` FOREIGN KEY (`User_Id`) REFERENCES `users` (`User_Id`),
  CONSTRAINT `chat2p_contact_ibfk_2` FOREIGN KEY (`Chat2P_Id`) REFERENCES `chat2p` (`Chat2P_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table dacs1_data.chat2p_contact: ~0 rows (approximately)
DELETE FROM `chat2p_contact`;

-- Dumping structure for table dacs1_data.chatgroup
CREATE TABLE IF NOT EXISTS `chatgroup` (
  `ChatGroup_Id` int NOT NULL AUTO_INCREMENT,
  `History` longtext,
  `Timing` datetime DEFAULT NULL,
  PRIMARY KEY (`ChatGroup_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table dacs1_data.chatgroup: ~0 rows (approximately)
DELETE FROM `chatgroup`;

-- Dumping structure for table dacs1_data.chatgroup_contact
CREATE TABLE IF NOT EXISTS `chatgroup_contact` (
  `ChatGroup_Contact_Id` int NOT NULL AUTO_INCREMENT,
  `User_Id` int DEFAULT NULL,
  `ChatGroup_Id` int DEFAULT NULL,
  PRIMARY KEY (`ChatGroup_Contact_Id`),
  KEY `User_Id` (`User_Id`),
  KEY `ChatGroup_Id` (`ChatGroup_Id`),
  CONSTRAINT `chatgroup_contact_ibfk_1` FOREIGN KEY (`User_Id`) REFERENCES `users` (`User_Id`),
  CONSTRAINT `chatgroup_contact_ibfk_2` FOREIGN KEY (`ChatGroup_Id`) REFERENCES `chatgroup` (`ChatGroup_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table dacs1_data.chatgroup_contact: ~0 rows (approximately)
DELETE FROM `chatgroup_contact`;

-- Dumping structure for table dacs1_data.community
CREATE TABLE IF NOT EXISTS `community` (
  `Project_Id` int NOT NULL AUTO_INCREMENT,
  `ProjectName` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`Project_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table dacs1_data.community: ~0 rows (approximately)
DELETE FROM `community`;

-- Dumping structure for table dacs1_data.community_contact
CREATE TABLE IF NOT EXISTS `community_contact` (
  `Community_Id` int NOT NULL AUTO_INCREMENT,
  `Project_Id` int DEFAULT NULL,
  `User_Id` int DEFAULT NULL,
  PRIMARY KEY (`Community_Id`),
  KEY `Project_Id` (`Project_Id`),
  KEY `User_Id` (`User_Id`),
  CONSTRAINT `community_contact_ibfk_1` FOREIGN KEY (`Project_Id`) REFERENCES `community` (`Project_Id`),
  CONSTRAINT `community_contact_ibfk_2` FOREIGN KEY (`User_Id`) REFERENCES `users` (`User_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table dacs1_data.community_contact: ~0 rows (approximately)
DELETE FROM `community_contact`;

-- Dumping structure for table dacs1_data.post
CREATE TABLE IF NOT EXISTS `post` (
  `Post_Id` int NOT NULL AUTO_INCREMENT,
  `Project_Id` int DEFAULT NULL,
  `User_Id` int DEFAULT NULL,
  `Timing` datetime DEFAULT NULL,
  `Content` longtext,
  `Comment` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`Post_Id`),
  KEY `Project_Id` (`Project_Id`),
  KEY `User_Id` (`User_Id`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`Project_Id`) REFERENCES `community` (`Project_Id`),
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`User_Id`) REFERENCES `users` (`User_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table dacs1_data.post: ~0 rows (approximately)
DELETE FROM `post`;

-- Dumping structure for table dacs1_data.users
CREATE TABLE IF NOT EXISTS `users` (
  `User_Id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(1000) DEFAULT NULL,
  `Fullname` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Birth` date DEFAULT NULL,
  `Email` varchar(1000) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Address` varchar(1000) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `Avatar` longblob,
  PRIMARY KEY (`User_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table dacs1_data.users: ~0 rows (approximately)
DELETE FROM `users`;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
