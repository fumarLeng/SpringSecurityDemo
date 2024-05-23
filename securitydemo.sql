-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: hr_manager
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_sys_my_permission`
--

DROP TABLE IF EXISTS `t_sys_my_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_my_permission` (
  `sid` varchar(50) NOT NULL COMMENT '主鍵SID',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父節點ID',
  `parent_name` varchar(100) DEFAULT NULL COMMENT '父節點名稱',
  `permission_name` varchar(100) DEFAULT NULL COMMENT '權限名稱',
  `permission_code` varchar(100) DEFAULT NULL COMMENT '授權標識符',
  `router_path` varchar(255) DEFAULT NULL COMMENT '路由地址',
  `router_name` varchar(100) DEFAULT NULL COMMENT '路由名稱',
  `auth_url` varchar(255) DEFAULT NULL COMMENT '授權路徑（對應文件在項目的地址）',
  `order_no` int DEFAULT NULL COMMENT '序號（用於排序）',
  `type` varchar(1) DEFAULT NULL COMMENT '類型 0 目錄 1 選單 2 按鈕',
  `icon` varchar(100) DEFAULT NULL COMMENT '圖標',
  `remark` varchar(255) DEFAULT NULL COMMENT '備註',
  `insert_user` varchar(50) DEFAULT NULL COMMENT '創建人',
  `insert_time` date DEFAULT NULL COMMENT '創建時間',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` date DEFAULT NULL COMMENT '更新時間',
  `license_code` varchar(20) DEFAULT NULL COMMENT '授權碼',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_my_permission`
--

LOCK TABLES `t_sys_my_permission` WRITE;
/*!40000 ALTER TABLE `t_sys_my_permission` DISABLE KEYS */;
INSERT INTO `t_sys_my_permission` VALUES ('1','1','1','1','1','1','1','1',1,'1','1','1','1','2024-05-21','1','2024-05-21','1');
/*!40000 ALTER TABLE `t_sys_my_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_my_role`
--

DROP TABLE IF EXISTS `t_sys_my_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_my_role` (
  `sid` varchar(50) NOT NULL COMMENT '主鍵SID',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名稱',
  `remark` varchar(255) DEFAULT NULL COMMENT '備註',
  `insert_user` varchar(50) DEFAULT NULL COMMENT '創建人',
  `insert_time` date DEFAULT NULL COMMENT '創建時間',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` date DEFAULT NULL COMMENT '更新時間',
  `status` varchar(255) DEFAULT NULL COMMENT '是否使用 0 禁用 1 使用',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_my_role`
--

LOCK TABLES `t_sys_my_role` WRITE;
/*!40000 ALTER TABLE `t_sys_my_role` DISABLE KEYS */;
INSERT INTO `t_sys_my_role` VALUES ('1','manager','1','1','2024-05-21','1','2024-05-21','1');
/*!40000 ALTER TABLE `t_sys_my_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_my_role_permission`
--

DROP TABLE IF EXISTS `t_sys_my_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_my_role_permission` (
  `sid` varchar(50) NOT NULL COMMENT '主鍵SID',
  `role_sid` varchar(50) DEFAULT NULL COMMENT '角色SID',
  `permission_sid` varchar(50) DEFAULT NULL COMMENT '權限SID',
  `insert_user` varchar(50) DEFAULT NULL COMMENT '創建人',
  `insert_time` date DEFAULT NULL COMMENT '創建時間',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_my_role_permission`
--

LOCK TABLES `t_sys_my_role_permission` WRITE;
/*!40000 ALTER TABLE `t_sys_my_role_permission` DISABLE KEYS */;
INSERT INTO `t_sys_my_role_permission` VALUES ('1','1','1','1','2024-05-21');
/*!40000 ALTER TABLE `t_sys_my_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_my_user`
--

DROP TABLE IF EXISTS `t_sys_my_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_my_user` (
  `sid` varchar(50) NOT NULL COMMENT '主鍵SID',
  `user_no` varchar(50) DEFAULT NULL COMMENT '用戶登入帳號',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用戶名稱',
  `password` varchar(100) DEFAULT NULL COMMENT '用戶密碼',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '用戶暱稱',
  `phone_number` varchar(50) DEFAULT NULL COMMENT '手機號碼',
  `email` varchar(50) DEFAULT NULL COMMENT '電子郵件',
  `department_id` varchar(50) DEFAULT NULL COMMENT '部門ID',
  `department_name` varchar(100) DEFAULT NULL COMMENT '部門名稱',
  `is_admin` varchar(1) DEFAULT NULL COMMENT '是否為管理員 0 否 1 是',
  `sex` varchar(1) DEFAULT NULL COMMENT '性別 0 男 1 女',
  `post_id` varchar(50) DEFAULT NULL COMMENT '職位ID',
  `post_name` varchar(100) DEFAULT NULL COMMENT '職位名稱',
  `is_account_non_expired` tinyint(1) DEFAULT NULL COMMENT '帳戶是否過期',
  `is_account_non_locked` tinyint(1) DEFAULT NULL COMMENT '帳戶是否被鎖定',
  `is_credentials_non_expired` tinyint(1) DEFAULT NULL COMMENT '密碼是否過期',
  `is_enabled` tinyint(1) DEFAULT NULL COMMENT '帳戶是否可用',
  `insert_user` varchar(50) DEFAULT NULL COMMENT '創建人',
  `insert_time` date DEFAULT NULL COMMENT '創建時間',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` date DEFAULT NULL COMMENT '更新時間',
  `license_code` varchar(20) DEFAULT NULL COMMENT '授權碼',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_my_user`
--

LOCK TABLES `t_sys_my_user` WRITE;
/*!40000 ALTER TABLE `t_sys_my_user` DISABLE KEYS */;
INSERT INTO `t_sys_my_user` VALUES ('1','user','Leo','123456','Leo','0900000000','gmail@gmail.com','1','最高管理部門','1','0','1','管理員',0,0,0,0,'1','2024-05-21','1','2024-05-21','1');
/*!40000 ALTER TABLE `t_sys_my_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_my_user_role`
--

DROP TABLE IF EXISTS `t_sys_my_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_my_user_role` (
  `sid` varchar(50) NOT NULL COMMENT '主鍵SID',
  `role_sid` varchar(50) DEFAULT NULL COMMENT '角色SID',
  `user_sid` varchar(50) DEFAULT NULL COMMENT '用戶SID',
  `insert_user` varchar(50) DEFAULT NULL COMMENT '創建人',
  `insert_time` date DEFAULT NULL COMMENT '創建時間',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_my_user_role`
--

LOCK TABLES `t_sys_my_user_role` WRITE;
/*!40000 ALTER TABLE `t_sys_my_user_role` DISABLE KEYS */;
INSERT INTO `t_sys_my_user_role` VALUES ('1','1','1','1','2024-05-21');
/*!40000 ALTER TABLE `t_sys_my_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-23 16:13:32
