CREATE DATABASE  IF NOT EXISTS `bdmesaayudatec` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bdmesaayudatec`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: bdmesaayudatec
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `tb_departamento`
--

DROP TABLE IF EXISTS `tb_departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_departamento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `abreviacion` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_departamento`
--

LOCK TABLES `tb_departamento` WRITE;
/*!40000 ALTER TABLE `tb_departamento` DISABLE KEYS */;
INSERT INTO `tb_departamento` VALUES (1,'Sistemas','SIS'),(2,'Recursos Humanos','RH'),(3,'Sistemas','SIS'),(4,'Recursos Humanos','RH'),(5,'Mantenimiento','MTO');
/*!40000 ALTER TABLE `tb_departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_estados`
--

DROP TABLE IF EXISTS `tb_estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_estados` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_estados`
--

LOCK TABLES `tb_estados` WRITE;
/*!40000 ALTER TABLE `tb_estados` DISABLE KEYS */;
INSERT INTO `tb_estados` VALUES (1,'Pendiente por atender'),(2,'En proceso'),(3,'Resuelto'),(4,'Cancelado');
/*!40000 ALTER TABLE `tb_estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_historial_ticket`
--

DROP TABLE IF EXISTS `tb_historial_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_historial_ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_ticket` int NOT NULL,
  `id_usuario` int NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `fecha` datetime NOT NULL,
  `id_prioridad` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_historial_ticket_tb_ticket1_idx` (`id_ticket`),
  KEY `fk_historial_ticket_tb_prioridades1_idx` (`id_prioridad`),
  KEY `fk_historial_ticket_tb_usuario1_idx` (`id_usuario`),
  CONSTRAINT `fk_historial_ticket_tb_prioridades1` FOREIGN KEY (`id_prioridad`) REFERENCES `tb_prioridades` (`id`),
  CONSTRAINT `fk_historial_ticket_tb_ticket1` FOREIGN KEY (`id_ticket`) REFERENCES `tb_ticket` (`id`),
  CONSTRAINT `fk_historial_ticket_tb_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_historial_ticket`
--

LOCK TABLES `tb_historial_ticket` WRITE;
/*!40000 ALTER TABLE `tb_historial_ticket` DISABLE KEYS */;
INSERT INTO `tb_historial_ticket` VALUES (1,1,1,'Ticket creado por Carlos P√©rez, pendiente de revisi√≥n','2025-09-23 13:25:52',1),(2,1,1,'El agente cambi√≥ el estado a En proceso','2025-09-23 13:25:52',2),(3,2,2,'Ticket creado por Ana G√≥mez con prioridad alta','2025-09-23 13:25:52',3),(4,2,1,'Agente asignado, revisando el problema','2025-09-23 13:25:52',4);
/*!40000 ALTER TABLE `tb_historial_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_prioridades`
--

DROP TABLE IF EXISTS `tb_prioridades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_prioridades` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `valor` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_prioridades`
--

LOCK TABLES `tb_prioridades` WRITE;
/*!40000 ALTER TABLE `tb_prioridades` DISABLE KEYS */;
INSERT INTO `tb_prioridades` VALUES (1,'Baja',1),(2,'Media',2),(3,'Alta',3),(4,'Cr√≠tica',4);
/*!40000 ALTER TABLE `tb_prioridades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_roles`
--

DROP TABLE IF EXISTS `tb_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `permiso_registrar` tinyint NOT NULL,
  `permiso_seguimiento` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_roles`
--

LOCK TABLES `tb_roles` WRITE;
/*!40000 ALTER TABLE `tb_roles` DISABLE KEYS */;
INSERT INTO `tb_roles` VALUES (1,'Administrador',1,1),(2,'Agente',1,1),(3,'Solicitante',1,0);
/*!40000 ALTER TABLE `tb_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ticket`
--

DROP TABLE IF EXISTS `tb_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `folio` varchar(20) DEFAULT NULL,
  `registrado` datetime NOT NULL,
  `id_departamento` int DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  `id_status` int NOT NULL,
  `id_prioridad` int NOT NULL,
  `descripcion` text NOT NULL,
  `titulo` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `folio_UNIQUE` (`folio`),
  KEY `fk_Ticket_usuario1_idx` (`id_usuario`),
  KEY `fk_Ticket_estados1_idx` (`id_status`),
  KEY `fk_Ticket_prioridades1_idx` (`id_prioridad`),
  KEY `fk_Ticket_departamento1_idx` (`id_departamento`),
  CONSTRAINT `fk_Ticket_departamento1` FOREIGN KEY (`id_departamento`) REFERENCES `tb_departamento` (`id`),
  CONSTRAINT `fk_Ticket_estados1` FOREIGN KEY (`id_status`) REFERENCES `tb_estados` (`id`),
  CONSTRAINT `fk_Ticket_prioridades1` FOREIGN KEY (`id_prioridad`) REFERENCES `tb_prioridades` (`id`),
  CONSTRAINT `fk_Ticket_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ticket`
--

LOCK TABLES `tb_ticket` WRITE;
/*!40000 ALTER TABLE `tb_ticket` DISABLE KEYS */;
INSERT INTO `tb_ticket` VALUES (1,'F1758172342681','2025-09-17 23:12:22',1,1,1,1,'1','1'),(2,'F1758172441671','2025-09-17 23:14:01',1,1,3,4,'2','2'),(3,'F1758656031337','2025-09-23 13:33:51',1,1,1,1,'12','prueva'),(4,'F1759256405830','2025-09-30 12:20:05',1,1,2,3,'usuario administrador','Revicion 30 de septiembre');
/*!40000 ALTER TABLE `tb_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  `apellido_paterno` varchar(45) NOT NULL,
  `apellido_materno` varchar(45) DEFAULT NULL,
  `usuario` varchar(45) NOT NULL,
  `password_usuario` blob NOT NULL,
  `id_rol` int NOT NULL,
  `inhabilitado` tinyint NOT NULL,
  `id_departamento` int DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `fk_usuario_roles_idx` (`id_rol`),
  KEY `fk_usuario_departamento1_idx` (`id_departamento`),
  CONSTRAINT `fk_usuario_departamento1` FOREIGN KEY (`id_departamento`) REFERENCES `tb_departamento` (`id`),
  CONSTRAINT `fk_usuario_roles` FOREIGN KEY (`id_rol`) REFERENCES `tb_roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=ascii;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (1,'Carlos','Perez','Lopez','carlos.perez',_binary 'ŸÄLó\ÍfÒ\€$ˆñâJõÜP\Œp¢Ü¬≤ØE\€\–\—',1,0,1),(2,'Ana','Gomez',NULL,'ana.gomez',_binary 'la´ï\Àg\›	\ﬁV2≤R\Î≥r´éÉf	\ÿ0\«vê\Ô\È',3,0,2);
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bdmesaayudatec'
--

--
-- Dumping routines for database 'bdmesaayudatec'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-07 11:38:13
