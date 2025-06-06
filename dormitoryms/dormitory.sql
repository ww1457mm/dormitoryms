
--
-- Host: 127.0.0.1    Database: dormitory
-- ------------------------------------------------------
-- Server version	8.0.19



--
-- Current Database: `dormitory`
--

CREATE DATABASE `dormitory`;

USE `dormitory`;

--
-- Table structure for table `absent`
--

DROP TABLE IF EXISTS `absent`;
CREATE TABLE `absent` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `building_id` int DEFAULT NULL,
                          `dormitory_id` int DEFAULT NULL,
                          `student_id` int DEFAULT NULL,
                          `dormitory_admin_id` int DEFAULT NULL,
                          `create_date` varchar(20) DEFAULT NULL,
                          `reason` varchar(20) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `absent`
--

LOCK TABLES `absent` WRITE;
INSERT INTO `absent` VALUES (1,1,1,1,1,'2025-04-16','请假'),
                            (2,2,2,7,2,'2025-04-26','请假');
UNLOCK TABLES;

--
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;

CREATE TABLE `building` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(50) DEFAULT NULL,
                            `introduction` varchar(1000) DEFAULT NULL,
                            `admin_id` int DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
INSERT INTO `building` VALUES (1,'1号楼','大数据学院宿舍楼',1),
                              (2,'2号楼','电子信息学院宿舍楼',2);
UNLOCK TABLES;

--
-- Table structure for table `dormitory`
--

DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `building_id` int DEFAULT NULL,
                             `name` varchar(20) DEFAULT NULL,
                             `type` int DEFAULT NULL,
                             `available` int DEFAULT NULL,
                             `telephone` varchar(20) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dormitory`
--

LOCK TABLES `dormitory` WRITE;
INSERT INTO `dormitory` VALUES (1,1,'101',4,0,'20250501'),
                               (2,2,'201',10,6,'20250502');
UNLOCK TABLES;

--
-- Table structure for table `dormitory_admin`
--

DROP TABLE IF EXISTS `dormitory_admin`;
CREATE TABLE `dormitory_admin` (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `username` varchar(20) DEFAULT NULL,
                                   `password` varchar(20) DEFAULT NULL,
                                   `name` varchar(20) DEFAULT NULL,
                                   `gender` varchar(10) DEFAULT NULL,
                                   `telephone` varchar(20) DEFAULT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `dormitory_admin`
--

LOCK TABLES `dormitory_admin` WRITE;
INSERT INTO `dormitory_admin` VALUES (1,'ll','123123','李四','男','15677413303'),
                                     (2,'zz','123123','张三','男','15677413304');
UNLOCK TABLES;

--
-- Table structure for table `moveout`
--

DROP TABLE IF EXISTS `moveout`;
CREATE TABLE `moveout` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `student_id` varchar(11) DEFAULT NULL,
                           `dormitory_id` varchar(50) DEFAULT NULL,
                           `reason` varchar(11) DEFAULT NULL,
                           `create_date` varchar(20) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `moveout`
--

LOCK TABLES `moveout` WRITE;
INSERT INTO `moveout` VALUES (1,'9','2','退学','2024-012-17'),
                             (2,'10','2','休学','2025-05-20');
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `number` varchar(11) DEFAULT NULL,
                           `name` varchar(20) DEFAULT NULL,
                           `gender` varchar(20) DEFAULT NULL,
                           `dormitory_id` int DEFAULT NULL,
                           `state` varchar(20) DEFAULT NULL,
                           `create_date` varchar(20) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES
                          (1,'001','王一','男',1,'入住','2024-09-01'),
                          (2,'002','王二','男',1,'入住','2024-09-01'),
                          (3,'003','王三','男',1,'入住','2024-09-01'),
                          (4,'004','王四','男',1,'入住','2024-09-01'),
                          (5,'005','王五','男',2,'入住','2024-09-01'),
                          (6,'006','王六','男',2,'入住','2024-09-01'),
                          (7,'007','王七','男',2,'入住','2024-09-01'),
                          (8,'008','王八','男',2,'入住','2024-09-01'),
                          (9,'009','王九','男',2,'迁出','2024-09-01'),
                          (10,'010','王十','男',2,'迁出','2024-09-01');
UNLOCK TABLES;

--
-- Table structure for table `system_admin`
--

DROP TABLE IF EXISTS `system_admin`;

CREATE TABLE `system_admin` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `username` varchar(20) DEFAULT NULL,
                                `password` varchar(20) DEFAULT NULL,
                                `name` varchar(20) DEFAULT NULL,
                                `telephone` varchar(20) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `system_admin`
--

LOCK TABLES `system_admin` WRITE;
INSERT INTO `system_admin` VALUES (1,'admin1','123123','管理员1','15677413301');
UNLOCK TABLES;

