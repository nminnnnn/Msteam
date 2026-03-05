-- Bo sung bang user va user_account cho dang ky/dang nhap (server can, preparing2.sql khong co)
-- Chay file nay VAO database dacs1_data sau khi da import preparing2.sql
-- Cach chay: type "duong_dan\patch_user_tables.sql" | docker exec -i msteam-mysql mysql -uroot dacs1_data

USE dacs1_data;

CREATE TABLE IF NOT EXISTS `user` (
  `User_ID` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  PRIMARY KEY (`User_ID`),
  UNIQUE KEY `UserName` (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `user_account` (
  `User_ID` int NOT NULL,
  `UserName` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Phone` varchar(50) DEFAULT NULL,
  `Address` varchar(500) DEFAULT NULL,
  `Avatar` longblob,
  `status` int DEFAULT 1,
  PRIMARY KEY (`User_ID`),
  CONSTRAINT `user_account_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
