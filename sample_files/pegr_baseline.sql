-- MariaDB dump 10.17  Distrib 10.5.5-MariaDB, for osx10.15 (x86_64)
--
-- Host: localhost    Database: pegr
-- ------------------------------------------------------
-- Server version	10.5.5-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(63) NOT NULL,
  `AUTHOR` varchar(63) NOT NULL,
  `FILENAME` varchar(200) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`,`AUTHOR`,`FILENAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('1470678902832-1','dus73 (generated)','changelog.groovy','2016-08-08 13:57:41',1,'EXECUTED','8:54c3d37d73af0836c00019466da199a7','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-10','dus73 (generated)','changelog.groovy','2016-08-08 13:57:43',10,'EXECUTED','8:6a11dc93f43ae46d7f861b0e040faf6b','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-100','dus73 (generated)','changelog.groovy','2016-08-08 13:58:25',240,'EXECUTED','8:0b16017befa092bf76fb778f05548d4c','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-101','dus73 (generated)','changelog.groovy','2016-08-08 13:58:26',241,'EXECUTED','8:ace23225b4684ef41084891622826eb5','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-102','dus73 (generated)','changelog.groovy','2016-08-08 13:58:26',242,'EXECUTED','8:f3c10ea4f72b095df5980d199420b03a','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-103','dus73 (generated)','changelog.groovy','2016-08-08 13:58:26',243,'EXECUTED','8:276743dcdef28eac9b2526cb460f8ee9','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-104','dus73 (generated)','changelog.groovy','2016-08-08 13:58:26',244,'EXECUTED','8:f08da5a2d4f5fd98b254c64ed809be19','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-105','dus73 (generated)','changelog.groovy','2016-08-08 13:58:26',245,'EXECUTED','8:76fdfe53d6709a4c7eb0c0f447865960','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-106','dus73 (generated)','changelog.groovy','2016-08-08 13:58:26',246,'EXECUTED','8:acc555e62ca3f8b96d715edcaf6c97ef','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-107','dus73 (generated)','changelog.groovy','2016-08-08 13:58:27',247,'EXECUTED','8:237581aaa4bf4d7398535bb2ba6739d2','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-108','dus73 (generated)','changelog.groovy','2016-08-08 13:58:27',248,'EXECUTED','8:c18d56fa3e13f0dc204980498f1777ce','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-109','dus73 (generated)','changelog.groovy','2016-08-08 13:58:27',249,'EXECUTED','8:8d8c82307ec1d0881e2b0c079c3d9d31','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-11','dus73 (generated)','changelog.groovy','2016-08-08 13:57:43',11,'EXECUTED','8:90a53a80a593d77e32fd0fe081eed503','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-110','dus73 (generated)','changelog.groovy','2016-08-08 13:58:27',250,'EXECUTED','8:eade0183256e880af19dafa7cd6e92bc','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-111','dus73 (generated)','changelog.groovy','2016-08-08 13:58:28',251,'EXECUTED','8:48b121f5d0218260676e56cbd550d217','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-112','dus73 (generated)','changelog.groovy','2016-08-08 13:58:28',252,'EXECUTED','8:ec7e96bc1ba44d4191b349898428ed8b','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-113','dus73 (generated)','changelog.groovy','2016-08-08 13:58:28',253,'EXECUTED','8:e44a2a6d29dc1b2fa2e058c664de9b86','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-114','dus73 (generated)','changelog.groovy','2016-08-08 13:58:28',254,'EXECUTED','8:282b4c8d657f52c85bf3dccb2f937a06','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-115','dus73 (generated)','changelog.groovy','2016-08-08 13:58:28',255,'EXECUTED','8:eb72e5f51ed17e05c91822f233d6a41a','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-116','dus73 (generated)','changelog.groovy','2016-08-08 13:58:29',256,'EXECUTED','8:c9833fa26d7b022af02537cf4c6a7d6c','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-117','dus73 (generated)','changelog.groovy','2016-08-08 13:58:29',257,'EXECUTED','8:32af261a0d4cb235594f9a3ba09c680a','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-118','dus73 (generated)','changelog.groovy','2016-08-08 13:58:29',258,'EXECUTED','8:5e524aecb5aadbef961b3ef13ddb6cc9','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-119','dus73 (generated)','changelog.groovy','2016-08-08 13:58:29',259,'EXECUTED','8:0173ca6ad2f7b8537dc1c6b75594ff25','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-12','dus73 (generated)','changelog.groovy','2016-08-08 13:57:43',12,'EXECUTED','8:0ac57314644466d6792e24396208d180','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-120','dus73 (generated)','changelog.groovy','2016-08-08 13:58:29',260,'EXECUTED','8:a2eb2dac746dddf43f014d711988b65d','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-121','dus73 (generated)','changelog.groovy','2016-08-08 13:58:29',261,'EXECUTED','8:9067a87692d31c78dc4569ae646ea5d2','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-122','dus73 (generated)','changelog.groovy','2016-08-08 13:58:30',262,'EXECUTED','8:44247fd958581126767667595ca57e58','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-123','dus73 (generated)','changelog.groovy','2016-08-08 13:58:30',263,'EXECUTED','8:99b4125d465bde7f62a7b38e09a80ce3','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-124','dus73 (generated)','changelog.groovy','2016-08-08 13:58:30',264,'EXECUTED','8:5460211fea898148133c819f62bdb2a9','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-125','dus73 (generated)','changelog.groovy','2016-08-08 13:58:30',265,'EXECUTED','8:0cc9b291204658145f0779beea4106fb','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-126','dus73 (generated)','changelog.groovy','2016-08-08 13:58:30',266,'EXECUTED','8:7f3a2072458f91e184e238fe61524ce3','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-127','dus73 (generated)','changelog.groovy','2016-08-08 13:58:30',267,'EXECUTED','8:3fd603c4aae023b06c6066897484d731','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-128','dus73 (generated)','changelog.groovy','2016-08-08 13:58:31',268,'EXECUTED','8:f9ef6eccd3e1f4087373f0b69ccb8537','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-129','dus73 (generated)','changelog.groovy','2016-08-08 13:58:31',269,'EXECUTED','8:6d21695864fd3023be169febff081ee4','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-13','dus73 (generated)','changelog.groovy','2016-08-08 13:57:43',13,'EXECUTED','8:95dabfba97897ee62c9e99ef1e022de8','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-130','dus73 (generated)','changelog.groovy','2016-08-08 13:58:31',270,'EXECUTED','8:ad7566f6bd3fcbcf1ae18281a422b547','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-131','dus73 (generated)','changelog.groovy','2016-08-08 13:58:31',271,'EXECUTED','8:c1e1e81743323a6dbf44501b95bcd1e3','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-132','dus73 (generated)','changelog.groovy','2016-08-08 13:58:32',272,'EXECUTED','8:477722ef54a1a884ab8840d93162f67c','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-133','dus73 (generated)','changelog.groovy','2016-08-08 13:58:32',273,'EXECUTED','8:8da6bfe71d9f015dc2be579da4edb9dc','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-134','dus73 (generated)','changelog.groovy','2016-08-08 13:58:32',274,'EXECUTED','8:9188c9461826554f9c9c78bb31e9fb68','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-135','dus73 (generated)','changelog.groovy','2016-08-08 13:58:32',275,'EXECUTED','8:720f3276a89086dac2f62619da416024','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-136','dus73 (generated)','changelog.groovy','2016-08-08 13:58:32',276,'EXECUTED','8:7ae030337a2396fa2b0a791899f33416','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-137','dus73 (generated)','changelog.groovy','2016-08-08 13:58:33',277,'EXECUTED','8:7faeee8872d817ed6ef3264490fca5c6','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-138','dus73 (generated)','changelog.groovy','2016-08-08 13:58:33',278,'EXECUTED','8:5251f5fed2ddf81e1f8167f445d587e3','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-139','dus73 (generated)','changelog.groovy','2016-08-08 13:58:33',279,'EXECUTED','8:233b437b0b98d5577bdac84e4a8de67c','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-14','dus73 (generated)','changelog.groovy','2016-08-08 13:57:43',14,'EXECUTED','8:9443ec298d0fad56dcddde4b39eb84a7','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-140','dus73 (generated)','changelog.groovy','2016-08-08 13:58:33',280,'EXECUTED','8:cf3e440cd5890e200ba202705fffe0be','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-141','dus73 (generated)','changelog.groovy','2016-08-08 13:58:34',281,'EXECUTED','8:923457430237f9d45a22cb31a5b00f23','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-142','dus73 (generated)','changelog.groovy','2016-08-08 13:58:34',282,'EXECUTED','8:4515204ddcabfa2ce0dbf66b90770cdf','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-143','dus73 (generated)','changelog.groovy','2016-08-08 13:58:34',283,'EXECUTED','8:c1d80fd45740fac4e2bbe03dbc91f2e1','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-144','dus73 (generated)','changelog.groovy','2016-08-08 13:58:35',284,'EXECUTED','8:0e97620fab0fe325b9cc130253561ceb','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-145','dus73 (generated)','changelog.groovy','2016-08-08 13:58:35',285,'EXECUTED','8:9d72871e891230da4990815d96373963','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-146','dus73 (generated)','changelog.groovy','2016-08-08 13:58:35',286,'EXECUTED','8:3163a3fbef62cc11eb1b27f5a8a04959','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-147','dus73 (generated)','changelog.groovy','2016-08-08 13:58:35',287,'EXECUTED','8:ab7cd4af9eae739f6e8abbef7f6bdc43','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-148','dus73 (generated)','changelog.groovy','2016-08-08 13:58:35',288,'EXECUTED','8:f6a2b0a479552170c330eec7ba21899b','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-149','dus73 (generated)','changelog.groovy','2016-08-08 13:58:35',289,'EXECUTED','8:0edb9c1bf593650275e07e852b61e282','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-15','dus73 (generated)','changelog.groovy','2016-08-08 13:57:43',15,'EXECUTED','8:996f55fda2bc047476a15b3109f06746','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-150','dus73 (generated)','changelog.groovy','2016-08-08 13:58:35',290,'EXECUTED','8:621f4dd2c123d75d4217d3dda175232d','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-151','dus73 (generated)','changelog.groovy','2016-08-08 13:58:36',291,'EXECUTED','8:6cc2a7f8da0ebf4018fecabe90fbc638','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-152','dus73 (generated)','changelog.groovy','2016-08-08 13:58:36',292,'EXECUTED','8:521212084e7f3ae5c0fac4269cc16be7','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-153','dus73 (generated)','changelog.groovy','2016-08-08 13:58:36',293,'EXECUTED','8:cf70dd1aa32a3ac88ea9cd703a77b7bf','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-154','dus73 (generated)','changelog.groovy','2016-08-08 13:58:36',294,'EXECUTED','8:666410e89bc9638ee85082a5f50caa9a','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-155','dus73 (generated)','changelog.groovy','2016-08-08 13:58:36',295,'EXECUTED','8:61d4236ab65d27021f959f51f951b2e3','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-156','dus73 (generated)','changelog.groovy','2016-08-08 13:58:37',296,'EXECUTED','8:1d844f3950525ba2db556a014ab23ac7','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-157','dus73 (generated)','changelog.groovy','2016-08-08 13:58:37',297,'EXECUTED','8:7d6e35e7e8fdd62ebd39ace097276685','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-158','dus73 (generated)','changelog.groovy','2016-08-08 13:58:37',298,'EXECUTED','8:6ed6bf83dad9d4785cf7d888091d0089','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-159','dus73 (generated)','changelog.groovy','2016-08-08 13:58:37',299,'EXECUTED','8:51b888de52a45ad0809af54952ba6ab6','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-16','dus73 (generated)','changelog.groovy','2016-08-08 13:57:43',16,'EXECUTED','8:1c817d00785d583b6ff577a1204773e1','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-160','dus73 (generated)','changelog.groovy','2016-08-08 13:58:37',300,'EXECUTED','8:55cc67f9c327ab32f4da4b92c0097829','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-161','dus73 (generated)','changelog.groovy','2016-08-08 13:58:37',301,'EXECUTED','8:695b325de75918af8b07848cab754077','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-162','dus73 (generated)','changelog.groovy','2016-08-08 13:58:38',302,'EXECUTED','8:e4aa4ef1230db6540c4f582e3fb368cf','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-163','dus73 (generated)','changelog.groovy','2016-08-08 13:58:38',303,'EXECUTED','8:4b7c3373c1a690294c59640e358081b7','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-164','dus73 (generated)','changelog.groovy','2016-08-08 13:58:38',304,'EXECUTED','8:a5a68f460a2ce15d13ba9cdea9723795','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-165','dus73 (generated)','changelog.groovy','2016-08-08 13:58:38',305,'EXECUTED','8:0468481beb4452b25651d4d8e06052fa','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-166','dus73 (generated)','changelog.groovy','2016-08-08 13:57:51',67,'EXECUTED','8:636259f6e43a9bbb79856f0861d24be2','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-167','dus73 (generated)','changelog.groovy','2016-08-08 13:57:52',68,'EXECUTED','8:a35d49faa0e65ae578e66de624cb0664','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-168','dus73 (generated)','changelog.groovy','2016-08-08 13:57:52',69,'EXECUTED','8:11830a8b92fe99d04cd73e7e26f8ea1a','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-169','dus73 (generated)','changelog.groovy','2016-08-08 13:57:52',70,'EXECUTED','8:7ea032c81074a3ad3bf34205dad93732','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-17','dus73 (generated)','changelog.groovy','2016-08-08 13:57:44',17,'EXECUTED','8:6e24f8c1b65ca5eb137b5f59b5de3729','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-170','dus73 (generated)','changelog.groovy','2016-08-08 13:57:52',71,'EXECUTED','8:ec475df6ecd649315bd02d3d1f11e120','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-171','dus73 (generated)','changelog.groovy','2016-08-08 13:57:52',72,'EXECUTED','8:da7ebd3774d6a62cfe4e2e9a5fb32909','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-172','dus73 (generated)','changelog.groovy','2016-08-08 13:57:52',73,'EXECUTED','8:0b7fdd50cc14348cadb1c4ed72799137','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-173','dus73 (generated)','changelog.groovy','2016-08-08 13:57:53',74,'EXECUTED','8:4f7a9abe9f20ff4ef7ae6dc4342e1821','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-174','dus73 (generated)','changelog.groovy','2016-08-08 13:57:53',75,'EXECUTED','8:0167a9d8727835bd414341d2c6033b16','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-175','dus73 (generated)','changelog.groovy','2016-08-08 13:57:53',76,'EXECUTED','8:84fdf8e323cdef408b629aa359ab49b3','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-176','dus73 (generated)','changelog.groovy','2016-08-08 13:57:54',77,'EXECUTED','8:9d2b788033370b5c6820262858b4e890','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-177','dus73 (generated)','changelog.groovy','2016-08-08 13:57:54',78,'EXECUTED','8:d79a3446629c1b7c7e7dbcd60514ef6a','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-178','dus73 (generated)','changelog.groovy','2016-08-08 13:57:54',79,'EXECUTED','8:87b251e25780bbee3764dcbbd5a24290','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-179','dus73 (generated)','changelog.groovy','2016-08-08 13:57:54',80,'EXECUTED','8:91b4d15b3595ded33db805485594cba9','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-18','dus73 (generated)','changelog.groovy','2016-08-08 13:57:44',18,'EXECUTED','8:455707cd5bd8c2d948e2b5fa30b3e128','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-180','dus73 (generated)','changelog.groovy','2016-08-08 13:57:55',81,'EXECUTED','8:238a95d185c2e56b9347098b37c1f37d','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-181','dus73 (generated)','changelog.groovy','2016-08-08 13:57:55',82,'EXECUTED','8:50274e7be199389b35ff703407a667fd','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-182','dus73 (generated)','changelog.groovy','2016-08-08 13:57:55',83,'EXECUTED','8:5e8b78ef483c6835e86e7ed289405163','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-183','dus73 (generated)','changelog.groovy','2016-08-08 13:57:55',84,'EXECUTED','8:7a92a86c4647bb4ca3d98f2015d02d9d','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-184','dus73 (generated)','changelog.groovy','2016-08-08 13:57:55',85,'EXECUTED','8:68b81dad983b1da62481ed43524337bd','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-185','dus73 (generated)','changelog.groovy','2016-08-08 13:57:55',86,'EXECUTED','8:2ae132a6b31887b757773db7642388e5','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-186','dus73 (generated)','changelog.groovy','2016-08-08 13:57:56',87,'EXECUTED','8:b49058cc7546fe8fad0d47f9a9ad7cd6','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-187','dus73 (generated)','changelog.groovy','2016-08-08 13:57:56',88,'EXECUTED','8:ea8caa47fda6a10ca5a035d7f4edeab7','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-188','dus73 (generated)','changelog.groovy','2016-08-08 13:57:56',89,'EXECUTED','8:794f68ccdd2324039c61a00d47ff70b3','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-189','dus73 (generated)','changelog.groovy','2016-08-08 13:57:56',90,'EXECUTED','8:b8de3c5b18bdeabd581e0e6b38efa415','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-19','dus73 (generated)','changelog.groovy','2016-08-08 13:57:44',19,'EXECUTED','8:9a3559a100d14befd0fb51ee5ed52c31','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-190','dus73 (generated)','changelog.groovy','2016-08-08 13:57:56',91,'EXECUTED','8:aeb335836cc8be67b1d5e57f5982478f','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-191','dus73 (generated)','changelog.groovy','2016-08-08 13:57:57',92,'EXECUTED','8:a7888327d65d26b03affdc5882ccf428','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-192','dus73 (generated)','changelog.groovy','2016-08-08 13:57:57',93,'EXECUTED','8:13b0682d547f8ff744e80bb954483b4a','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-193','dus73 (generated)','changelog.groovy','2016-08-08 13:57:57',94,'EXECUTED','8:8254b844f164b7fbe30f857e8c0560ac','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-194','dus73 (generated)','changelog.groovy','2016-08-08 13:57:57',95,'EXECUTED','8:05fe40dcb4c76289b7dd2d22a2a5524b','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-195','dus73 (generated)','changelog.groovy','2016-08-08 13:57:57',96,'EXECUTED','8:5c4cdaeef20f35e081fd633919a34b78','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-196','dus73 (generated)','changelog.groovy','2016-08-08 13:57:57',97,'EXECUTED','8:3cabe8c2d610e4818566c25e9b6f58fe','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-197','dus73 (generated)','changelog.groovy','2016-08-08 13:57:58',98,'EXECUTED','8:1e3bb9ca6aca2ce2bbc222c7f4db1ec2','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-198','dus73 (generated)','changelog.groovy','2016-08-08 13:57:58',99,'EXECUTED','8:c7d96b2ba2be0cb7f2b843fad85ee4ca','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-199','dus73 (generated)','changelog.groovy','2016-08-08 13:57:58',100,'EXECUTED','8:8d6e3523892be3189b516e970859de59','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-2','dus73 (generated)','changelog.groovy','2016-08-08 13:57:41',2,'EXECUTED','8:78ba94fc4ba2d20e113ef5db85d3cb05','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-20','dus73 (generated)','changelog.groovy','2016-08-08 13:57:44',20,'EXECUTED','8:ae923116836295e5e293a19a95c9c07b','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-200','dus73 (generated)','changelog.groovy','2016-08-08 13:57:58',101,'EXECUTED','8:6994c79890ea7acc7132a0ed0870c1d4','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-201','dus73 (generated)','changelog.groovy','2016-08-08 13:57:58',102,'EXECUTED','8:e496c61423d525b45da9e1ac0d6a6dd0','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-202','dus73 (generated)','changelog.groovy','2016-08-08 13:57:58',103,'EXECUTED','8:579b5ac2ae5f9aef2930a52e17907e46','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-203','dus73 (generated)','changelog.groovy','2016-08-08 13:57:59',104,'EXECUTED','8:262a86477b469adfd4b5a6dd28a0e7aa','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-204','dus73 (generated)','changelog.groovy','2016-08-08 13:57:59',105,'EXECUTED','8:82cf867a04edc141739e7d938855af3b','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-205','dus73 (generated)','changelog.groovy','2016-08-08 13:57:59',106,'EXECUTED','8:c90cb9eff677258b9fdff87663c1f7a2','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-206','dus73 (generated)','changelog.groovy','2016-08-08 13:57:59',107,'EXECUTED','8:00ac0a4b425c5dbb6850d81e004f60cb','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-207','dus73 (generated)','changelog.groovy','2016-08-08 13:57:59',108,'EXECUTED','8:1fa15769788f55e4be49f3f971d2dafb','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-208','dus73 (generated)','changelog.groovy','2016-08-08 13:58:00',109,'EXECUTED','8:5402fbacc2ca6d9d4065d3bb6bd8f455','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-209','dus73 (generated)','changelog.groovy','2016-08-08 13:58:00',110,'EXECUTED','8:8c625aa47fa78539aaf1c2ba5dbb79ff','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-21','dus73 (generated)','changelog.groovy','2016-08-08 13:57:44',21,'EXECUTED','8:c5710bffed4e094ee024567c0d49f024','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-210','dus73 (generated)','changelog.groovy','2016-08-08 13:58:00',111,'EXECUTED','8:958cbd290f54511ef48d77a0c430efdf','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-211','dus73 (generated)','changelog.groovy','2016-08-08 13:58:00',112,'EXECUTED','8:9be1590cde168b109e794656608745a7','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-212','dus73 (generated)','changelog.groovy','2016-08-08 13:58:00',113,'EXECUTED','8:0b904c7a7b4d71ae5fbf226cf29d4c69','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-213','dus73 (generated)','changelog.groovy','2016-08-08 13:58:00',114,'EXECUTED','8:8c01f2f4b92c66b860102472a5bfc78c','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-214','dus73 (generated)','changelog.groovy','2016-08-08 13:58:01',115,'EXECUTED','8:b8b7d37586b6f9271a3366b0bc31edee','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-215','dus73 (generated)','changelog.groovy','2016-08-08 13:58:01',116,'EXECUTED','8:d18999c2f9f77b73bc183b8cd5b66176','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-216','dus73 (generated)','changelog.groovy','2016-08-08 13:58:01',117,'EXECUTED','8:cd9a738ce5a76916377c36ea23a23f3b','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-217','dus73 (generated)','changelog.groovy','2016-08-08 13:58:01',118,'EXECUTED','8:4fd6a5e2225df21ac0e2f7c0f178d90b','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-218','dus73 (generated)','changelog.groovy','2016-08-08 13:58:01',119,'EXECUTED','8:5eca30dc465d4f7ddae45985f9d3607e','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-219','dus73 (generated)','changelog.groovy','2016-08-08 13:58:02',120,'EXECUTED','8:a604aab0a5a0245b127861b61d35a5fe','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-22','dus73 (generated)','changelog.groovy','2016-08-08 13:57:44',22,'EXECUTED','8:33de5c2887332a0e2a7b4713d063b7df','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-220','dus73 (generated)','changelog.groovy','2016-08-08 13:58:02',121,'EXECUTED','8:c51a3ca8378c1ecbf63c77f0cf006e31','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-221','dus73 (generated)','changelog.groovy','2016-08-08 13:58:02',122,'EXECUTED','8:c96c1789ca25dea1a2eea267bc29c38b','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-222','dus73 (generated)','changelog.groovy','2016-08-08 13:58:02',123,'EXECUTED','8:e5dfe01a733d1b83d646f8b5741a83c3','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-223','dus73 (generated)','changelog.groovy','2016-08-08 13:58:02',124,'EXECUTED','8:04b3e7d08db0eca0460bf780a6adb359','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-224','dus73 (generated)','changelog.groovy','2016-08-08 13:58:03',125,'EXECUTED','8:a0a82ee0699c8c1723850fa04562952a','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-225','dus73 (generated)','changelog.groovy','2016-08-08 13:58:03',126,'EXECUTED','8:2f1ec37482ab9d6aa807b1d8bc7a2e58','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-226','dus73 (generated)','changelog.groovy','2016-08-08 13:58:03',127,'EXECUTED','8:eaf4ba8d42cb8c7dd9cc80ed131fc309','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-227','dus73 (generated)','changelog.groovy','2016-08-08 13:58:03',128,'EXECUTED','8:99ad4cd970294eaf854f4de6a551b4b7','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-228','dus73 (generated)','changelog.groovy','2016-08-08 13:58:03',129,'EXECUTED','8:5af74390ec66ecfe3f10d96571f636b6','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-229','dus73 (generated)','changelog.groovy','2016-08-08 13:58:03',130,'EXECUTED','8:49c8b24072e7e1fbbca63fd4406a6ab2','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-23','dus73 (generated)','changelog.groovy','2016-08-08 13:57:44',23,'EXECUTED','8:56906bd3bc03f8ef0ee33c0772668107','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-230','dus73 (generated)','changelog.groovy','2016-08-08 13:58:04',131,'EXECUTED','8:2fb7320c6c036e7be08e37e4214d5357','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-231','dus73 (generated)','changelog.groovy','2016-08-08 13:58:04',132,'EXECUTED','8:259dbfb8204583624b2d2177a7c0f8dd','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-232','dus73 (generated)','changelog.groovy','2016-08-08 13:58:04',133,'EXECUTED','8:57aee11b5ff614099143e59693a08c51','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-233','dus73 (generated)','changelog.groovy','2016-08-08 13:58:04',134,'EXECUTED','8:0bd478fe971069e060ec044daba1ecf3','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-234','dus73 (generated)','changelog.groovy','2016-08-08 13:58:04',135,'EXECUTED','8:b0bad741672c17e62eeb4490758d084f','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-235','dus73 (generated)','changelog.groovy','2016-08-08 13:58:05',136,'EXECUTED','8:05cba3eca307c513f0233c13b929697a','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-236','dus73 (generated)','changelog.groovy','2016-08-08 13:58:05',137,'EXECUTED','8:0c85c15ea65aa76e58181aeff7c9ebbd','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-237','dus73 (generated)','changelog.groovy','2016-08-08 13:58:05',138,'EXECUTED','8:cb81b8873c9b44860d349b7b8aa47ca9','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-238','dus73 (generated)','changelog.groovy','2016-08-08 13:58:05',139,'EXECUTED','8:2b286516f102d5decdd2ce237ae54643','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-239','dus73 (generated)','changelog.groovy','2016-08-08 13:58:05',140,'EXECUTED','8:69270d9e89ce5fad49a664d7b2c4f828','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-24','dus73 (generated)','changelog.groovy','2016-08-08 13:57:45',24,'EXECUTED','8:cba578b1a50af166c67bc3051e339a07','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-240','dus73 (generated)','changelog.groovy','2016-08-08 13:58:06',141,'EXECUTED','8:a99a30d74e44883ef1c331c1ce253b1a','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-241','dus73 (generated)','changelog.groovy','2016-08-08 13:58:06',142,'EXECUTED','8:cf90fe4501f0c6465b96cb33eca79f2b','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-242','dus73 (generated)','changelog.groovy','2016-08-08 13:58:06',143,'EXECUTED','8:0a581436732c1c26f7a9c101d7b5e5d7','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-243','dus73 (generated)','changelog.groovy','2016-08-08 13:58:06',144,'EXECUTED','8:0ece385d793b33977b239cd8291b5590','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-244','dus73 (generated)','changelog.groovy','2016-08-08 13:58:06',145,'EXECUTED','8:1d1d71d74048c41e5ca5228703998ae5','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-245','dus73 (generated)','changelog.groovy','2016-08-08 13:58:07',146,'EXECUTED','8:a15ca0e462e5ea84c2535d2315828e9d','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-246','dus73 (generated)','changelog.groovy','2016-08-08 13:58:07',147,'EXECUTED','8:23487acf75f78ff4608db285851b3118','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-247','dus73 (generated)','changelog.groovy','2016-08-08 13:58:07',148,'EXECUTED','8:68f6a58790eba82744d4392262a8f7c5','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-248','dus73 (generated)','changelog.groovy','2016-08-08 13:58:07',149,'EXECUTED','8:2ff8218c717dc3999a3875ed83180036','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-249','dus73 (generated)','changelog.groovy','2016-08-08 13:58:07',150,'EXECUTED','8:63934ec9dcba4f87731b543197071ffa','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-25','dus73 (generated)','changelog.groovy','2016-08-08 13:57:45',25,'EXECUTED','8:f1b8233b6b2e980580b1c7161f5a01fc','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-250','dus73 (generated)','changelog.groovy','2016-08-08 13:58:07',151,'EXECUTED','8:b149ce90653de960d3203ee21625b2a4','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-251','dus73 (generated)','changelog.groovy','2016-08-08 13:58:08',152,'EXECUTED','8:88bef38aaadb1e23c703446deea4dc18','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-252','dus73 (generated)','changelog.groovy','2016-08-08 13:58:08',153,'EXECUTED','8:634cafe7ca35714beb39078fc1fb6b42','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-253','dus73 (generated)','changelog.groovy','2016-08-08 13:58:08',154,'EXECUTED','8:c9e75376aed3235bba93e8be210cbd44','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-254','dus73 (generated)','changelog.groovy','2016-08-08 13:58:08',155,'EXECUTED','8:1dbae41a10cc2192901d77d2e1480e3b','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-255','dus73 (generated)','changelog.groovy','2016-08-08 13:58:08',156,'EXECUTED','8:7514bf4c2d3d060af046597882f5646a','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-256','dus73 (generated)','changelog.groovy','2016-08-08 13:58:09',157,'EXECUTED','8:d180850dbf76479050eccd2643622771','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-257','dus73 (generated)','changelog.groovy','2016-08-08 13:58:09',158,'EXECUTED','8:bad332dcce8fe6b23788344c3e995c1e','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-258','dus73 (generated)','changelog.groovy','2016-08-08 13:58:09',159,'EXECUTED','8:7e3f9729889afd211784a3d31ca87f6a','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-259','dus73 (generated)','changelog.groovy','2016-08-08 13:58:09',160,'EXECUTED','8:da1ce86aea507f91c15f6786cb35b9d4','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-26','dus73 (generated)','changelog.groovy','2016-08-08 13:57:45',26,'EXECUTED','8:bc8c14ff5e099661c18c55ea6a6a529a','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-260','dus73 (generated)','changelog.groovy','2016-08-08 13:58:09',161,'EXECUTED','8:c3fad448de3c1a62ffc752f29b10f33a','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-261','dus73 (generated)','changelog.groovy','2016-08-08 13:58:09',162,'EXECUTED','8:7de0cbce75f543be839617a2094c7a4b','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-262','dus73 (generated)','changelog.groovy','2016-08-08 13:58:10',163,'EXECUTED','8:9058b8799a3d53b08036e60cb8110890','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-263','dus73 (generated)','changelog.groovy','2016-08-08 13:58:10',164,'EXECUTED','8:925d6caacfa616565afbe67602edb6cd','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-264','dus73 (generated)','changelog.groovy','2016-08-08 13:58:10',165,'EXECUTED','8:b6f4b2eea7a61ea8d7e3b1e796d4f2c1','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-265','dus73 (generated)','changelog.groovy','2016-08-08 13:58:10',166,'EXECUTED','8:a76ff0c9d88814d05c33cbef283a3df4','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-266','dus73 (generated)','changelog.groovy','2016-08-08 13:58:11',167,'EXECUTED','8:4a784f3dd8a640a732a5faffe6df35ae','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-267','dus73 (generated)','changelog.groovy','2016-08-08 13:58:11',168,'EXECUTED','8:f1a8d373c35c77dbef7816047e64e3ea','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-268','dus73 (generated)','changelog.groovy','2016-08-08 13:58:11',169,'EXECUTED','8:f1a608196d280d2c2a167442ce21cac9','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-269','dus73 (generated)','changelog.groovy','2016-08-08 13:58:11',170,'EXECUTED','8:37fe5818c3be7255df315c2fd9e612db','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-27','dus73 (generated)','changelog.groovy','2016-08-08 13:57:45',27,'EXECUTED','8:864ba36ee5ac9b5fa774cf510ccdffd1','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-270','dus73 (generated)','changelog.groovy','2016-08-08 13:58:11',171,'EXECUTED','8:f6782da6fb2585b57a102f7b3ed326f7','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-271','dus73 (generated)','changelog.groovy','2016-08-08 13:58:12',172,'EXECUTED','8:df37adcd50c7ca92cd5bed1dd4799b64','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-272','dus73 (generated)','changelog.groovy','2016-08-08 13:58:12',173,'EXECUTED','8:0a31c7c6a2d496e7dcc1abcfbda03678','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-273','dus73 (generated)','changelog.groovy','2016-08-08 13:58:12',174,'EXECUTED','8:38e1def850144d47cefde8fda29eeebf','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-274','dus73 (generated)','changelog.groovy','2016-08-08 13:58:12',175,'EXECUTED','8:1ccbdf0243cea60a0632901d19a45b49','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-275','dus73 (generated)','changelog.groovy','2016-08-08 13:58:12',176,'EXECUTED','8:c4924814df9f671c29f8fcd8e497b176','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-276','dus73 (generated)','changelog.groovy','2016-08-08 13:58:13',177,'EXECUTED','8:4fc48fe40e76bf22b58de964263a306a','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-277','dus73 (generated)','changelog.groovy','2016-08-08 13:58:13',178,'EXECUTED','8:7c917e29ba32123bb9638c996110f207','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-278','dus73 (generated)','changelog.groovy','2016-08-08 13:58:13',179,'EXECUTED','8:96bdb2dab7e17375b4c72afd0a130f03','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-279','dus73 (generated)','changelog.groovy','2016-08-08 13:58:13',180,'EXECUTED','8:fb9e644efd9133742e9cc7027e658b57','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-28','dus73 (generated)','changelog.groovy','2016-08-08 13:57:45',28,'EXECUTED','8:563bc875eb3f996cb7da0c7828e22b68','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-280','dus73 (generated)','changelog.groovy','2016-08-08 13:58:13',181,'EXECUTED','8:8c98c0bf9954b98d05f1fcf8b9192b2e','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-281','dus73 (generated)','changelog.groovy','2016-08-08 13:58:14',182,'EXECUTED','8:686c8655e184dbdad3283bec20dc5729','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-282','dus73 (generated)','changelog.groovy','2016-08-08 13:58:14',183,'EXECUTED','8:31206a14671cda45e98147d444bce1d6','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-283','dus73 (generated)','changelog.groovy','2016-08-08 13:58:14',184,'EXECUTED','8:f73fac052eb47b5b58465f602e8c3410','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-284','dus73 (generated)','changelog.groovy','2016-08-08 13:58:14',185,'EXECUTED','8:efa6c81061762f0013c061c5e42d22d6','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-285','dus73 (generated)','changelog.groovy','2016-08-08 13:58:14',186,'EXECUTED','8:9f73387ae2205b2d5df8cc8883d9d627','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-286','dus73 (generated)','changelog.groovy','2016-08-08 13:58:14',187,'EXECUTED','8:bb136ab7e0d18f495e77273c78103ee6','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-287','dus73 (generated)','changelog.groovy','2016-08-08 13:58:15',188,'EXECUTED','8:9980a1dff9f124fc20f5a93cceafe669','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-288','dus73 (generated)','changelog.groovy','2016-08-08 13:58:15',189,'EXECUTED','8:fc42758362ddb489fe7ddea937f41f25','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-289','dus73 (generated)','changelog.groovy','2016-08-08 13:58:15',190,'EXECUTED','8:409ad36aa9b9c7900a1a0a225c353db7','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-29','dus73 (generated)','changelog.groovy','2016-08-08 13:57:45',29,'EXECUTED','8:00681213d8c0d941d1ceb52952437096','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-290','dus73 (generated)','changelog.groovy','2016-08-08 13:58:15',191,'EXECUTED','8:b7a1986bdec3252186af1b0f70aff97e','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-291','dus73 (generated)','changelog.groovy','2016-08-08 13:58:16',192,'EXECUTED','8:ce8113b4bb7752424dda16ec7d84e217','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-292','dus73 (generated)','changelog.groovy','2016-08-08 13:58:16',193,'EXECUTED','8:e33d99ad1eccb2a440799580a5f23d51','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-293','dus73 (generated)','changelog.groovy','2016-08-08 13:58:16',194,'EXECUTED','8:00ea920bcb51a097c4696b55c6f92efd','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-294','dus73 (generated)','changelog.groovy','2016-08-08 13:58:16',195,'EXECUTED','8:be5a33b1e432590ac808843a432d93b7','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-295','dus73 (generated)','changelog.groovy','2016-08-08 13:58:16',196,'EXECUTED','8:b980c354e9afed9750a5febf29a6a165','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-296','dus73 (generated)','changelog.groovy','2016-08-08 13:58:17',197,'EXECUTED','8:e093cb695b0fb97ef7f23b23e45192ee','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-297','dus73 (generated)','changelog.groovy','2016-08-08 13:58:17',198,'EXECUTED','8:3b56c2af267e9e7d37b32e0f42c91f20','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-298','dus73 (generated)','changelog.groovy','2016-08-08 13:58:17',199,'EXECUTED','8:54bc03fc9e413d2c073070d19be1f357','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-299','dus73 (generated)','changelog.groovy','2016-08-08 13:58:17',200,'EXECUTED','8:8139b3584f4e37487d2dbbe458b753ff','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-3','dus73 (generated)','changelog.groovy','2016-08-08 13:57:42',3,'EXECUTED','8:aad1c58326f604a8f171d59a73dc7d07','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-30','dus73 (generated)','changelog.groovy','2016-08-08 13:57:45',30,'EXECUTED','8:93c45aec9b754dcda6574e52e91ec0c0','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-300','dus73 (generated)','changelog.groovy','2016-08-08 13:58:17',201,'EXECUTED','8:55bb537cd9daa2900a8a8595cd1a230a','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-301','dus73 (generated)','changelog.groovy','2016-08-08 13:58:18',202,'EXECUTED','8:6f9b3f75bbf2aec480473c6164806306','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-302','dus73 (generated)','changelog.groovy','2016-08-08 13:58:18',203,'EXECUTED','8:968908466b87600e21767ce2ca65c1a2','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-303','dus73 (generated)','changelog.groovy','2016-08-08 13:58:18',204,'EXECUTED','8:a469340d4b821277281070d1c6b158f4','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-304','dus73 (generated)','changelog.groovy','2016-08-08 13:58:18',205,'EXECUTED','8:7e289df93c23f6791e4d8227772c7133','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-305','dus73 (generated)','changelog.groovy','2016-08-08 13:58:18',206,'EXECUTED','8:4c56f546b62a6824d1c473a3fd81cb62','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-31','dus73 (generated)','changelog.groovy','2016-08-08 13:57:46',31,'EXECUTED','8:970ae437633aa353d5b1e57aa6a16c83','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-32','dus73 (generated)','changelog.groovy','2016-08-08 13:57:46',32,'EXECUTED','8:506a27155a3d036986758040475d4e9d','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-33','dus73 (generated)','changelog.groovy','2016-08-08 13:57:46',33,'EXECUTED','8:3ae85736b8d9b9b6b243416c317b1767','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-34','dus73 (generated)','changelog.groovy','2016-08-08 13:57:46',34,'EXECUTED','8:f41fdf1fc48a40c998bac56453370947','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-35','dus73 (generated)','changelog.groovy','2016-08-08 13:57:46',35,'EXECUTED','8:15c4540316dab6eeb1060e5b782b4bad','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-36','dus73 (generated)','changelog.groovy','2016-08-08 13:57:46',36,'EXECUTED','8:60746a756c7b540ceb131c5a1f7be52a','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-37','dus73 (generated)','changelog.groovy','2016-08-08 13:57:47',37,'EXECUTED','8:cc560cdfcc517abf175cdcc6028d9759','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-38','dus73 (generated)','changelog.groovy','2016-08-08 13:57:47',38,'EXECUTED','8:86809b3fe45b80f37cf0d235edc195fe','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-39','dus73 (generated)','changelog.groovy','2016-08-08 13:57:47',39,'EXECUTED','8:a683ce67fcc1d74fca96f514ce4bf9f5','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-4','dus73 (generated)','changelog.groovy','2016-08-08 13:57:42',4,'EXECUTED','8:1889ea51fa2b7b774a214c3a12a27a21','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-40','dus73 (generated)','changelog.groovy','2016-08-08 13:57:47',40,'EXECUTED','8:c6ec464553ef7aac0eafd46f38f28d01','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-41','dus73 (generated)','changelog.groovy','2016-08-08 13:57:47',41,'EXECUTED','8:016489851ecb0a75be43a037516414cf','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-42','dus73 (generated)','changelog.groovy','2016-08-08 13:57:48',42,'EXECUTED','8:8f51b51796ee67cce23b9a57cbf2c8de','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-43','dus73 (generated)','changelog.groovy','2016-08-08 13:57:48',43,'EXECUTED','8:f73c044ad249fb490dc5d04c90cd40e1','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-44','dus73 (generated)','changelog.groovy','2016-08-08 13:57:48',44,'EXECUTED','8:234ba7f2bb2563276762b597862169bb','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-45','dus73 (generated)','changelog.groovy','2016-08-08 13:57:48',45,'EXECUTED','8:97294352a4bd464e54b2776a8cc08e3e','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-46','dus73 (generated)','changelog.groovy','2016-08-08 13:57:48',46,'EXECUTED','8:2e49ad1f902e5e0a6587cc85ca2f033d','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-47','dus73 (generated)','changelog.groovy','2016-08-08 13:57:48',47,'EXECUTED','8:2ccb58c51cc9dceed0cee589cf74e716','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-48','dus73 (generated)','changelog.groovy','2016-08-08 13:57:48',48,'EXECUTED','8:f0804d7b95ccf2e74edacf42f8d6bc68','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-49','dus73 (generated)','changelog.groovy','2016-08-08 13:57:49',49,'EXECUTED','8:7f0a2c11338e73ac0437407c94b3c7e9','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-5','dus73 (generated)','changelog.groovy','2016-08-08 13:57:42',5,'EXECUTED','8:8924a5a44e88a70a0967f28464342fbf','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-50','dus73 (generated)','changelog.groovy','2016-08-08 13:57:49',50,'EXECUTED','8:ffe6b253586f03b8c0a8963b6d4fd4fb','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-51','dus73 (generated)','changelog.groovy','2016-08-08 13:57:49',51,'EXECUTED','8:86dbcdcb568dd309386e52eba2788195','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-52','dus73 (generated)','changelog.groovy','2016-08-08 13:57:49',52,'EXECUTED','8:7189717267706c52062f229991e030ba','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-53','dus73 (generated)','changelog.groovy','2016-08-08 13:57:49',53,'EXECUTED','8:c891f2603f20a1d7c7daff1568019d44','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-54','dus73 (generated)','changelog.groovy','2016-08-08 13:57:49',54,'EXECUTED','8:9d75ce7874c188af0913fb51a55d9050','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-55','dus73 (generated)','changelog.groovy','2016-08-08 13:57:49',55,'EXECUTED','8:1b4ba554a1a4b03fbfb38ab763a7fb08','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-56','dus73 (generated)','changelog.groovy','2016-08-08 13:57:50',56,'EXECUTED','8:12b9c0c9942313778354e05d47b1f99e','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-57','dus73 (generated)','changelog.groovy','2016-08-08 13:57:50',57,'EXECUTED','8:07d60333d1880298d132ad4de7fce88a','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-58','dus73 (generated)','changelog.groovy','2016-08-08 13:57:50',58,'EXECUTED','8:1ab8867c96e2dab06b325f796208e646','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-59','dus73 (generated)','changelog.groovy','2016-08-08 13:57:50',59,'EXECUTED','8:7f6b776f55fa339a91ebcb193af4b4fa','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-6','dus73 (generated)','changelog.groovy','2016-08-08 13:57:42',6,'EXECUTED','8:47718820d6fe348994653627886b0026','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-60','dus73 (generated)','changelog.groovy','2016-08-08 13:57:50',60,'EXECUTED','8:1560c4d4b55d019dcc6b311337fcef3a','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-61','dus73 (generated)','changelog.groovy','2016-08-08 13:57:50',61,'EXECUTED','8:f39befd690b3f57d1c0b527ca5656c29','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-62','dus73 (generated)','changelog.groovy','2016-08-08 13:57:51',62,'EXECUTED','8:9dca2a46111e91a9ccc8c9b19950a9ee','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-63','dus73 (generated)','changelog.groovy','2016-08-08 13:57:51',63,'EXECUTED','8:3fea810fecec27ef35994b5d225c30b7','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-64','dus73 (generated)','changelog.groovy','2016-08-08 13:57:51',64,'EXECUTED','8:7ae50a1d38d237cca7b004303eb7fb7d','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-65','dus73 (generated)','changelog.groovy','2016-08-08 13:57:51',65,'EXECUTED','8:7eed1f364fa6c774962fc070cda99d40','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-66','dus73 (generated)','changelog.groovy','2016-08-08 13:57:51',66,'EXECUTED','8:a605bc0bdf05ba56d13b33160d27bd65','Add Primary Key','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-67','dus73 (generated)','changelog.groovy','2016-08-08 13:58:19',207,'EXECUTED','8:6d6d6fcfbe1a0bfdd0a815062f701361','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-68','dus73 (generated)','changelog.groovy','2016-08-08 13:58:19',208,'EXECUTED','8:b94cf4e973eb108d9b92b2ebf090fcd0','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-69','dus73 (generated)','changelog.groovy','2016-08-08 13:58:19',209,'EXECUTED','8:1e3be283bc61ec8875738fb0249372ed','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-7','dus73 (generated)','changelog.groovy','2016-08-08 13:57:42',7,'EXECUTED','8:165129219931ae2db8d01d1e7e1529e6','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-70','dus73 (generated)','changelog.groovy','2016-08-08 13:58:19',210,'EXECUTED','8:037315f2ee07dfdffb1ea23d94bf7cf9','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-71','dus73 (generated)','changelog.groovy','2016-08-08 13:58:19',211,'EXECUTED','8:1188e159e50b7f74875f0818abe6de62','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-72','dus73 (generated)','changelog.groovy','2016-08-08 13:58:20',212,'EXECUTED','8:00f6e0be4bf3dde3c33dcca965efb15c','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-73','dus73 (generated)','changelog.groovy','2016-08-08 13:58:20',213,'EXECUTED','8:ebf35f271911820d57c9af6ce28c69e9','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-74','dus73 (generated)','changelog.groovy','2016-08-08 13:58:20',214,'EXECUTED','8:86205815fc24f225a0913ca7689c232f','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-75','dus73 (generated)','changelog.groovy','2016-08-08 13:58:20',215,'EXECUTED','8:e3c7fca1a23594f66649731f6b06e442','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-76','dus73 (generated)','changelog.groovy','2016-08-08 13:58:20',216,'EXECUTED','8:781248e34b477236249401e220d6e20d','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-77','dus73 (generated)','changelog.groovy','2016-08-08 13:58:20',217,'EXECUTED','8:478c33b89d918901eb9aebbb75c4a36e','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-78','dus73 (generated)','changelog.groovy','2016-08-08 13:58:21',218,'EXECUTED','8:b4829ba758637d1eeb6df812d7881247','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-79','dus73 (generated)','changelog.groovy','2016-08-08 13:58:21',219,'EXECUTED','8:90b34db92dff2a16c5eb58d4536a98f8','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-8','dus73 (generated)','changelog.groovy','2016-08-08 13:57:42',8,'EXECUTED','8:a886a38ecf1bf464dab1d083ed805b86','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-80','dus73 (generated)','changelog.groovy','2016-08-08 13:58:21',220,'EXECUTED','8:ae03353667dafe1fb0d00bdfbe2cb717','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-81','dus73 (generated)','changelog.groovy','2016-08-08 13:58:21',221,'EXECUTED','8:4e3662200c6e8472d357ac7d5f22d526','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-82','dus73 (generated)','changelog.groovy','2016-08-08 13:58:21',222,'EXECUTED','8:32561c19907d83b487820b40d463e997','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-83','dus73 (generated)','changelog.groovy','2016-08-08 13:58:21',223,'EXECUTED','8:2ff3a7c20b60ea1017e91d62cb7e1c6a','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-84','dus73 (generated)','changelog.groovy','2016-08-08 13:58:22',224,'EXECUTED','8:b6a6c8a631f3d25c0622ab5b7d07b84c','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-85','dus73 (generated)','changelog.groovy','2016-08-08 13:58:22',225,'EXECUTED','8:84e57b81fd922accca04194b8158f4d2','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-86','dus73 (generated)','changelog.groovy','2016-08-08 13:58:22',226,'EXECUTED','8:01bf83036e40db34e315e4af741f9c1f','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-87','dus73 (generated)','changelog.groovy','2016-08-08 13:58:22',227,'EXECUTED','8:1bd3369ad64b3405ebe23f89123fd7c8','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-88','dus73 (generated)','changelog.groovy','2016-08-08 13:58:22',228,'EXECUTED','8:0858bcad8157a0bc3135a04632d39ace','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-89','dus73 (generated)','changelog.groovy','2016-08-08 13:58:23',229,'EXECUTED','8:f7e8832a8d9a6bd8081d3ec46a85a80d','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-9','dus73 (generated)','changelog.groovy','2016-08-08 13:57:42',9,'EXECUTED','8:6e1cb46e48aae6e20ebca30f8e3e2fd7','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-90','dus73 (generated)','changelog.groovy','2016-08-08 13:58:23',230,'EXECUTED','8:b0673005c7b582de1997274a31598d2c','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-91','dus73 (generated)','changelog.groovy','2016-08-08 13:58:24',231,'EXECUTED','8:04ca3e882ca95196e3a53cacb9870a0a','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-92','dus73 (generated)','changelog.groovy','2016-08-08 13:58:24',232,'EXECUTED','8:f5b9d52f6ae40a8da1c512b6275b7eb0','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-93','dus73 (generated)','changelog.groovy','2016-08-08 13:58:24',233,'EXECUTED','8:9759a56ebd21999f2b94e53ed2c1e480','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-94','dus73 (generated)','changelog.groovy','2016-08-08 13:58:24',234,'EXECUTED','8:f0dfbe80ceaebab0b4ea04025bb92059','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-95','dus73 (generated)','changelog.groovy','2016-08-08 13:58:25',235,'EXECUTED','8:580211cf4c1c1e234703087a826d31d7','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-96','dus73 (generated)','changelog.groovy','2016-08-08 13:58:25',236,'EXECUTED','8:121c8758698ea8d9be0a5e337282142a','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-97','dus73 (generated)','changelog.groovy','2016-08-08 13:58:25',237,'EXECUTED','8:76bbb8d5f84bf59ae6948fb4432a0c58','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-98','dus73 (generated)','changelog.groovy','2016-08-08 13:58:25',238,'EXECUTED','8:b3cc2b768af3bd6a3a023e28b8760020','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1470678902832-99','dus73 (generated)','changelog.groovy','2016-08-08 13:58:25',239,'EXECUTED','8:d90ebdecb3caa35b4bb3b30c061a1018','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1471313502353-1','danyingshao (generated)','addHistoryIdToAlignment.groovy','2016-08-16 15:05:31',306,'EXECUTED','8:175dbb76c1dbc71bf4f82f79613f048a','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1471313502353-10','danyingshao (generated)','addHistoryIdToAlignment.groovy','2016-08-16 15:05:31',311,'EXECUTED','8:7f102fcaebe568e910bfc6cadb9cff58','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1471313502353-11','danyingshao (generated)','addHistoryIdToAlignment.groovy','2016-08-16 15:05:31',310,'EXECUTED','8:2e562ca58020424ce178572d63f8bb26','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1471313502353-2','danyingshao (generated)','addHistoryIdToAlignment.groovy','2016-08-16 15:05:31',307,'EXECUTED','8:c4116bfff5ed27eeb83dd7036f97a157','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1471313502353-3','danyingshao (generated)','addHistoryIdToAlignment.groovy','2016-08-16 15:05:31',308,'EXECUTED','8:4fdd8f202772f4024286397e908a0271','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1471313502353-4','danyingshao (generated)','addHistoryIdToAlignment.groovy','2016-08-16 15:05:31',309,'EXECUTED','8:042297c1e4cccce036e024f09abed278','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1471366553380-10','dus73 (generated)','removeHistoryIdFromAnalysis.groovy','2016-08-16 15:07:58',316,'EXECUTED','8:dd06786be2506e335538f6831a7a718b','Drop Column','',NULL,'2.0.5',NULL,NULL,NULL),('1471366553380-11','dus73 (generated)','removeHistoryIdFromAnalysis.groovy','2016-08-16 15:07:58',317,'EXECUTED','8:8a0bce5ac381149d8e896f74f1c0ee6d','Drop Column','',NULL,'2.0.5',NULL,NULL,NULL),('1471366553380-12','dus73 (generated)','removeHistoryIdFromAnalysis.groovy','2016-08-16 15:07:58',318,'EXECUTED','8:70125365b8f3b15f270ab2a79d77128e','Drop Column','',NULL,'2.0.5',NULL,NULL,NULL),('1471366553380-6','dus73 (generated)','removeHistoryIdFromAnalysis.groovy','2016-08-16 15:07:57',312,'EXECUTED','8:9095437d97fb7dcafaf134f7a46a01b2','Add Not-Null Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1471366553380-7','dus73 (generated)','removeHistoryIdFromAnalysis.groovy','2016-08-16 15:07:57',313,'EXECUTED','8:8b32274e3f1e483ff4f0c7bc0307c683','Add Not-Null Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1471366553380-8','dus73 (generated)','removeHistoryIdFromAnalysis.groovy','2016-08-16 15:07:58',314,'EXECUTED','8:dac347d4f1f00a1a763325ce1ea23b0c','Drop Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1471366553380-9','dus73 (generated)','removeHistoryIdFromAnalysis.groovy','2016-08-16 15:07:58',315,'EXECUTED','8:2083534acf6e4251d335f48da3a95d1c','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-1','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',319,'EXECUTED','8:e2fd4f134840f617af906d926411c9c6','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-10','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',323,'EXECUTED','8:ed4e6db86212b9d6aedb621256cdfc76','Drop Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-11','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',331,'EXECUTED','8:b464805101f267d958332c85cc34c560','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-12','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',332,'EXECUTED','8:53eb5adfd185d155e9963b1d29c2fd19','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-13','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',333,'EXECUTED','8:098ad5ceb078055a82cd303b9f0a7e61','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-14','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',334,'EXECUTED','8:bb86fbafae2395ad5271d96b6cfc803c','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-15','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',324,'EXECUTED','8:4b35298b40b900ea0c2cb9be98ed3a8f','Drop Index','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-16','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',325,'EXECUTED','8:231445fc4c24fa69c2752081530de88a','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-17','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',326,'EXECUTED','8:34c9336cbd2fe285ea8a6aff596e0408','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-18','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',327,'EXECUTED','8:e5118ecb1422b61e24be1935f28db0d1','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-19','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',328,'EXECUTED','8:862d4831d822aecdc573817b7b6c7e95','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-2','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',320,'EXECUTED','8:32643171618b8d0ac153d98eb6b2bf3e','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-20','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',329,'EXECUTED','8:602e98781002952b5b258ee656b8a01f','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-21','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',330,'EXECUTED','8:8a32a81c9872150e3deda9d45264cd31','Drop Table','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-3','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',321,'EXECUTED','8:23a3be9551f6bdbfc792241184f234fd','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1471396412843-9','danyingshao (generated)','changeInstanceItem.groovy','2016-08-18 11:20:42',322,'EXECUTED','8:ab94ab111b36dfed4c366062ccc2b134','Drop Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1471827341742-1','danyingshao (generated)','changeChores.groovy','2016-08-22 08:47:41',335,'EXECUTED','8:9fe03d790c59f30f08df26f931384472','Modify data type','',NULL,'2.0.5',NULL,NULL,NULL),('1471974065450-1','dus73 (generated)','changeGenome.groovy','2016-08-23 18:56:28',336,'EXECUTED','8:956899c4f35e514227cd9ddb12104f07','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1471974065450-2','dus73 (generated)','changeGenome.groovy','2016-08-23 18:56:28',337,'EXECUTED','8:1995eb7aa13581ad14535691e12ec230','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-1','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:41',338,'EXECUTED','8:54a4d893f20bd2a722a733a0fd5c9673','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-10','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',347,'EXECUTED','8:e7d62d217041bb404823e6f9b03e6ba9','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-11','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',348,'EXECUTED','8:b42dd8a9faec967d0c8c4ca4fb4dc840','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-12','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',349,'EXECUTED','8:98a97a7e9e482a4397b8be532331df2f','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-13','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',350,'EXECUTED','8:2307b21f51bee5699e849624032bc6b2','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-14','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',351,'EXECUTED','8:fe75065b555809cb663b4a884d4b986c','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-15','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',352,'EXECUTED','8:05dbb4a08342d984fba04a0277d18b31','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-16','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',353,'EXECUTED','8:f91243e29ee91a482b03470835611a56','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-17','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',354,'EXECUTED','8:b770a3b5f49b1f54bc42f861036cca76','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-18','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',355,'EXECUTED','8:3c8c70a6fd5c5cd59deeb87b54f4273d','Custom SQL, Add Not-Null Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-19','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',356,'EXECUTED','8:7edbd116de9bac69d1695dd377a625fe','Drop Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-2','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:41',339,'EXECUTED','8:814a37f606431887309f4bc5fe437135','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-20','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',357,'EXECUTED','8:49a6ce988954d9106d15693141a8bac3','Drop Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-21','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',380,'EXECUTED','8:6f3f03ed604202cebdce6060b995929d','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-22','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',381,'EXECUTED','8:f5aa5236817ae14fd38669dcfc9d6e37','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-23','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',382,'EXECUTED','8:745e614d741a3c75504e02e6db0c7845','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-24','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',383,'EXECUTED','8:235611ffa174702088c10a1342dbc987','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-25','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',384,'EXECUTED','8:88e046c05ddf3a3bc68a7e7591de5398','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-26','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:45',385,'EXECUTED','8:6d4dc41a49476c20c7f1d21da0e796c2','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-27','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:45',386,'EXECUTED','8:d972a3963a9c4d1d5b91f4b4aa2de031','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-28','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:46',387,'EXECUTED','8:c4df07d759e51e33df7827d5bb395682','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-29','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',358,'EXECUTED','8:3f1d3617fce92040340e53ebf50d952c','Drop Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-3','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:41',340,'EXECUTED','8:591c9cc0adc9434e58e1da55704b5b4b','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-30','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',359,'EXECUTED','8:8e47ba747406f9dcd61941633d672003','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-31','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',360,'EXECUTED','8:67bb59bcbddb42feb98ef9a34b3b5357','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-32','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',361,'EXECUTED','8:8af2756e7777de6b09bedecaa0f15997','Custom SQL (x2), Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-33','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',362,'EXECUTED','8:d56f7eba2ad8d6c10b1a9d6c9f5334c7','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-34','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',363,'EXECUTED','8:872ba28355f90a07a95cf47935e53c2e','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-35','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',364,'EXECUTED','8:d73fc2478c7d948c2cb400b3f911fc02','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-36','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',365,'EXECUTED','8:4edaf8fd51a94955785706513e744ce4','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-37','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',366,'EXECUTED','8:6d5850b41c4c6c6e26de466d52f91ad8','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-38','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',367,'EXECUTED','8:b3edea6dd7093a7a7a6e27091b636ad4','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-39','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',368,'EXECUTED','8:939c88df0d8b333e8596a59d4c3f2b9b','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-4','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:41',341,'EXECUTED','8:3edeee8be2f9c756525cb4db3c37ef6a','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-40','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',369,'EXECUTED','8:f92908199237c12edc9271043be5d81d','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-41','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',370,'EXECUTED','8:2e6bb66f98e1ee8fb4b3f00ce2730cd8','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-42','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:43',371,'EXECUTED','8:5fd6d642c8c81425165c3e51a3add3ef','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-43','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',372,'EXECUTED','8:f0f92b5bd6683fe78fedc0742b0c2940','Drop Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-44','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',373,'EXECUTED','8:816ed7fc4fcda90dd51c92aca454fa0d','Drop Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-45','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',374,'EXECUTED','8:1ace5bad4a1850c8db51ef21c3a068f6','Drop Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-46','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',375,'EXECUTED','8:5110cb1ce7799847a605e79b3386998c','Drop Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-47','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',376,'EXECUTED','8:e011cf8c780491534a2ab965e90868fc','Drop Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-48','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',377,'EXECUTED','8:72fa6a7e12be1ed23c44381e875bcf04','Drop Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-49','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',378,'EXECUTED','8:5fadb814052fda82b8544485ba3bb08e','Drop Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-5','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',342,'EXECUTED','8:86cacc3f59d0ea27fcf427df6859c7e8','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-50','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:44',379,'EXECUTED','8:2b4cd0534845433c651557459ff6e1c6','Drop Table','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-6','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',343,'EXECUTED','8:c43a4d11afdca117b6fb9e8b7a9a67a0','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-7','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',344,'EXECUTED','8:dae0b844d87955e16c2da43b8af38f38','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-8','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',345,'EXECUTED','8:80f4ce665dca7286eab7c787b96613ed','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1472352936325-9','danyingshao (generated)','addCohort.groovy','2016-08-28 22:05:42',346,'EXECUTED','8:0d34560da580530ace67e87568c523a1','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1473096571923-1','danyingshao (generated)','updateItem.groovy','2016-09-07 11:40:16',388,'EXECUTED','8:981ca580d36ce0b06455107934a7a21c','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1473096571923-2','danyingshao (generated)','updateItem.groovy','2016-09-07 11:40:16',389,'EXECUTED','8:9e51244ffef79c1e7594d9447e7284aa','Drop Index','',NULL,'2.0.5',NULL,NULL,NULL),('1473096571923-3','danyingshao (generated)','updateItem.groovy','2016-09-07 11:40:17',390,'EXECUTED','8:62a1a8fad7e4a12a753817862bad74b3','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1475780890127-1','dus73 (generated)','addProjectBag.groovy','2016-10-11 22:47:48',391,'EXECUTED','8:01a0e14d9c60c1e5d75c5de57b4ca135','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1475780890127-2','dus73 (generated)','addProjectBag.groovy','2016-10-11 22:47:48',395,'EXECUTED','8:e2bba6597153043b28a3394eef0da27f','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1475780890127-3','dus73 (generated)','addProjectBag.groovy','2016-10-11 22:47:48',396,'EXECUTED','8:fb6d418c5f349e35a5622e93b0116589','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1475780890127-4','dus73 (generated)','addProjectBag.groovy','2016-10-11 22:47:48',392,'EXECUTED','8:138a1ae02adc94620c4b08532830d700','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1475780890127-5','dus73 (generated)','addProjectBag.groovy','2016-10-11 22:47:48',393,'EXECUTED','8:0fc8d1c5119c8bac3d6afab80b68d24b','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1475780890127-6','dus73 (generated)','addProjectBag.groovy','2016-10-11 22:47:48',394,'EXECUTED','8:397692855e66caeeb1b7ddeae31dc0f8','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1476105580424-1','dus73 (generated)','addProjectItem.groovy','2016-10-11 22:47:48',397,'EXECUTED','8:54dd4f53d16a9236df64a4647e4f3e14','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1476105580424-2','dus73 (generated)','addProjectItem.groovy','2016-10-11 22:47:48',399,'EXECUTED','8:f3a41fcf2b2c973a438de676d58f4725','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1476105580424-3','dus73 (generated)','addProjectItem.groovy','2016-10-11 22:47:48',398,'EXECUTED','8:aa89fc55d232e425b9c235ac23b1a1de','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1476405265440-1','danyingshao (generated)','addStep.groovy','2016-10-14 11:02:56',400,'EXECUTED','8:50b078acc3429c20c739f451e47db2cf','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1477660961013-1','dus73 (generated)','addCohortImages.groovy','2016-10-28 10:46:57',401,'EXECUTED','8:046f6239678fcec5e6826390760ac1fd','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1477921051005-1','dus73 (generated)','addCellSourceStatus.groovy','2016-11-01 10:26:25',402,'EXECUTED','8:1d43adac68262f4486aabd219f4c8985','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1478552128314-1','dus73 (generated)','addCohortNotes.groovy','2016-11-11 15:36:42',403,'EXECUTED','8:4c39fd6edc3f871ef7992d0cd64b1c5c','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1478552128314-2','dus73 (generated)','addCohortNotes.groovy','2016-11-11 15:36:42',404,'EXECUTED','8:9dd03a0e93d7aaac738487461d983e61','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-1','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:41',405,'EXECUTED','8:5e51e95c83d9e1c30cd51d9c73ce2297','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-10','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:42',422,'EXECUTED','8:29c1cd08d9f5299c4f9efb96c2b272d3','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-11','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:42',423,'EXECUTED','8:2d5f1eacbf9c8468fe1582694cd745ef','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-12','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:42',424,'EXECUTED','8:fe05f481fcc31f2a2bf57e14b455fcf4','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-13','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:41',412,'EXECUTED','8:06aad3a7cde0cd9eadea5cd9e6f0675c','Drop Index','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-14','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:41',413,'EXECUTED','8:6dad0c1bb4b281c70cf657ae60d373c8','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-15','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:41',414,'EXECUTED','8:31b0e1224cf6d2b39df50acbd2a38df4','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-16','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:41',415,'EXECUTED','8:c9adb61d881f2e2e25708b0037d5a641','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-17','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:41',416,'EXECUTED','8:84923b22474ffc92bfea28a719898f58','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-18','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:42',417,'EXECUTED','8:fa127567aded8e7ad541af47f4457a45','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-19','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:42',418,'EXECUTED','8:f5490f99dfbb49eddf65e03e80a52f3c','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-2','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:41',406,'EXECUTED','8:95158983734e7655db765ef94955a7af','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-20','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:42',419,'EXECUTED','8:04d7678f3d71a389db26ba88e2ea85af','Drop Table','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-3','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:41',407,'EXECUTED','8:68018e478f6405e35dcbb4bf0634ec5b','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-4','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:41',408,'EXECUTED','8:a159f16358082da0bb801e877d37bc7e','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-5','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:41',409,'EXECUTED','8:4f801e42eb0b62cfdcbb26f739d324f9','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-6','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:41',410,'EXECUTED','8:c5bf2eb6154e6a467f6be5f219df1248','Add Primary Key','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-7','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:41',411,'EXECUTED','8:a409f6dff7bb881445fa75ba6e2f3330','Add Primary Key','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-8','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:42',420,'EXECUTED','8:5f8ea57ca3cae0a062b1b5ecb98b7865','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1479613639392-9','danyingshao (generated)','addGroupRole.groovy','2016-11-21 20:59:42',421,'EXECUTED','8:605b2427d04f221c04dec77faad0426c','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1480617832506-1','dus73 (generated)','addLaneStats.groovy','2016-12-05 12:36:06',425,'EXECUTED','8:b2f5688333e4a499bb701716e34ac6e3','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1480617832506-2','dus73 (generated)','addLaneStats.groovy','2016-12-05 12:36:06',426,'EXECUTED','8:4ad49bdb73e7ddfca72a3174c3468f92','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1480617832506-3','dus73 (generated)','addLaneStats.groovy','2016-12-05 12:36:06',427,'EXECUTED','8:5e226d95ad8d80d2240d8d9d0ed68a92','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1480617832506-4','dus73 (generated)','addLaneStats.groovy','2016-12-05 12:36:06',428,'EXECUTED','8:8bb8f46687f8a726d04b6dc2cc331de8','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1480617832506-5','dus73 (generated)','addLaneStats.groovy','2016-12-05 12:36:06',429,'EXECUTED','8:5588a55b651ee70903bd6972161b0fd1','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1480617832506-6','dus73 (generated)','addLaneStats.groovy','2016-12-05 12:36:06',430,'EXECUTED','8:502df57f18da6b9267bbb861b3e45283','Modify data type','',NULL,'2.0.5',NULL,NULL,NULL),('1480617832506-7','dus73 (generated)','addLaneStats.groovy','2016-12-05 12:36:06',432,'EXECUTED','8:58b81238de9b6bd2794f181ffdd3ee7a','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1480617832506-8','dus73 (generated)','addLaneStats.groovy','2016-12-05 12:36:06',431,'EXECUTED','8:1ceec434f6bb7790c2a3678ea2d95e76','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1480627226009-1','dus73 (generated)','addSampleNaturalId.groovy','2016-12-05 12:36:07',433,'EXECUTED','8:63c7b0e36464e000934e04fa8ea0b22b','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1480971923830-1','dus73 (generated)','addCellSourceBatch.groovy','2016-12-08 19:20:13',434,'EXECUTED','8:a516ed35ff10b9cabd9dc7f2130afa8d','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1480971923830-2','dus73 (generated)','addCellSourceBatch.groovy','2016-12-08 19:20:13',435,'EXECUTED','8:445ad90192839824853ea659873335c4','Create Table','',NULL,'2.0.5',NULL,NULL,NULL),('1480971923830-3','dus73 (generated)','addCellSourceBatch.groovy','2016-12-08 19:20:14',439,'EXECUTED','8:0be8c3fa58bdd20830d29ab0a7dc037a','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1480971923830-4','dus73 (generated)','addCellSourceBatch.groovy','2016-12-08 19:20:14',440,'EXECUTED','8:0af75c6a5c6b76de20928924f6ad1f44','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1480971923830-5','dus73 (generated)','addCellSourceBatch.groovy','2016-12-08 19:20:14',441,'EXECUTED','8:211b7ac9b41d9a1b57519a734c1db877','Add Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1480971923830-6','dus73 (generated)','addCellSourceBatch.groovy','2016-12-08 19:20:14',436,'EXECUTED','8:6fb53e0456148b13664b2e5cca366d7f','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1480971923830-7','dus73 (generated)','addCellSourceBatch.groovy','2016-12-08 19:20:14',437,'EXECUTED','8:3bb618f097b930b524cdc0392dbe3cca','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1480971923830-8','dus73 (generated)','addCellSourceBatch.groovy','2016-12-08 19:20:14',438,'EXECUTED','8:daa209bb1bd3805173c17d6219751718','Create Index','',NULL,'2.0.5',NULL,NULL,NULL),('1481230470768-1','dus73 (generated)','addItemStatus.groovy','2016-12-08 19:20:14',442,'EXECUTED','8:04944a66c7e4de7de7721583016d5df8','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1481317918893-1','dus73 (generated)','removeCohortUnique.groovy','2016-12-13 10:44:04',443,'EXECUTED','8:befe39526e9b59eca58adda7c7376136','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1481317918893-2','dus73 (generated)','removeCohortUnique.groovy','2016-12-13 10:44:04',444,'EXECUTED','8:aecaff45179892cd8db7cfed9a74b0c3','Drop Index','',NULL,'2.0.5',NULL,NULL,NULL),('1481830867639-1','dus73 (generated)','addProtocolImages.groovy','2016-12-16 09:33:48',445,'EXECUTED','8:def1651c4a31c1d4ed4a3800a11aa65d','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1481830867639-2','dus73 (generated)','addProtocolImages.groovy','2016-12-16 09:33:48',446,'EXECUTED','8:ecec16cd74d4e2a7ca80d1dc925f7ba4','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1484332960349-1','dus73 (generated)','addBatchNotes.groovy','2017-01-13 16:44:39',447,'EXECUTED','8:479c299df8cd3758e0693b9132589126','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1484579194244-1','dus73 (generated)','batchNotesNullable.groovy','2017-01-16 10:45:52',448,'EXECUTED','8:3cbca8d1710a20a5dcde6fb82e28dac2','Drop Not-Null Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1485202170886-1','dus73 (generated)','addSampleRecommend.groovy','2017-01-25 10:23:14',449,'EXECUTED','8:25277fa8c2cbb447b720de938178ed65','Add Column','',NULL,'2.0.5',NULL,NULL,NULL),('1485202170886-2','dus73 (generated)','addSampleRecommend.groovy','2017-01-25 10:23:15',450,'EXECUTED','8:467d777e27dc7e2d13cef6c543f0d511','Drop Foreign Key Constraint','',NULL,'2.0.5',NULL,NULL,NULL),('1485202170886-3','dus73 (generated)','addSampleRecommend.groovy','2017-01-25 10:23:16',451,'EXECUTED','8:8c1ecceac8b472d38d220ed3f2969d03','Drop Column','',NULL,'2.0.5',NULL,NULL,NULL),('1485202170886-4','dus73 (generated)','addSampleRecommend.groovy','2017-01-25 10:23:16',452,'EXECUTED','8:6354f133bab605544c21d5fc86fb7f37','Drop Table','',NULL,'2.0.5',NULL,NULL,NULL),('1576267457331-1','danyingshao (generated)','historyAndWorkflowUrl.groovy','2020-01-09 13:16:16',457,'EXECUTED','8:f0888e7a88ed458d228182f39fb4a3b3','addDefaultValue columnName=pipeline_version, tableName=pipeline','',NULL,'3.6.1',NULL,NULL,'8593776019'),('1576267457331-2','danyingshao (generated)','historyAndWorkflowUrl.groovy','2020-01-09 13:16:16',458,'EXECUTED','8:768643b4d582e22bee55af630932dd9d','addDefaultValue columnName=status, tableName=item','',NULL,'3.6.1',NULL,NULL,'8593776019'),('1576267457331-49','danyingshao (generated)','historyAndWorkflowUrl.groovy','2020-01-09 13:16:16',453,'EXECUTED','8:f5c3ff21e291e4fcb20601d19f52079f','addColumn tableName=sequence_alignment','',NULL,'3.6.1',NULL,NULL,'8593776019'),('1576267457331-50','danyingshao (generated)','historyAndWorkflowUrl.groovy','2020-01-09 13:16:16',454,'EXECUTED','8:d5a2e88d000e6e639eee7da6bdef78e8','addColumn tableName=history','',NULL,'3.6.1',NULL,NULL,'8593776019'),('1576267457331-51','danyingshao (generated)','historyAndWorkflowUrl.groovy','2020-01-09 13:16:16',455,'EXECUTED','8:5bc165dc474ba2c5eb175dd5fe38ebf3','addColumn tableName=pipeline','',NULL,'3.6.1',NULL,NULL,'8593776019'),('1576267457331-52','danyingshao (generated)','historyAndWorkflowUrl.groovy','2020-01-09 13:16:16',456,'EXECUTED','8:6125662e1b9e6ea72c39b77644c6ac56','dropColumn columnName=log, tableName=history','',NULL,'3.6.1',NULL,NULL,'8593776019'),('1576768506139-1','danyingshao (generated)','updateProtocolGroupProtocols.groovy','2020-01-09 13:16:16',461,'EXECUTED','8:6d3d503304839a7e345fc9bbb1117d27','addNotNullConstraint columnName=protocols_idx, tableName=protocol_group_protocols','',NULL,'3.6.1',NULL,NULL,'8593776019'),('1576768506139-48','danyingshao (generated)','updateProtocolGroupProtocols.groovy','2020-01-09 13:16:16',459,'EXECUTED','8:6e9463a3295cf4d02c39c8cf89eb8196','addColumn tableName=protocol_group_protocols','',NULL,'3.6.1',NULL,NULL,'8593776019'),('1576768506139-50','danyingshao (generated)','updateProtocolGroupProtocols.groovy','2020-01-09 13:16:16',460,'EXECUTED','8:0d8ccead57ebc7ec2040434646f9cc07','addUniqueConstraint constraintName=UK413d0a510ff03d1f19c83af9ae36, tableName=protocol_group_protocols','',NULL,'3.6.1',NULL,NULL,'8593776019'),('1580764544352-1','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',462,'EXECUTED','8:7300da3e68cf1926d7924cf402364f72','createTable tableName=ab_host','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-10','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',471,'EXECUTED','8:5543220c3feaf6919a151229cf2a2cae','createTable tableName=cell_source_batch','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-100','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',561,'EXECUTED','8:74b7087c422ca449ca16e477f30924ef','addUniqueConstraint constraintName=UC_TISSUENAME_COL, tableName=tissue','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-101','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',562,'EXECUTED','8:7d6f99505a893a0dc7d6f2d7a120a9f1','addUniqueConstraint constraintName=UC_USERUSERNAME_COL, tableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-102','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',563,'EXECUTED','8:5c89d8aa95234ed67f4433c3a94e19fd','addUniqueConstraint constraintName=UK16c0fb5f244d0452a2e49aed563f, tableName=project_funding','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-103','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',564,'EXECUTED','8:30b7788f58d1c3320f5074d792fd2f5d','addUniqueConstraint constraintName=UK1a92d74db3df102928b5f605132a, tableName=control_sample','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-104','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',565,'EXECUTED','8:ee62dd252c99cc45e44506aaffc4af8e','addUniqueConstraint constraintName=UK3932af47b0357d2b4c5a261822ee, tableName=species','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-105','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',566,'EXECUTED','8:0d8ccead57ebc7ec2040434646f9cc07','addUniqueConstraint constraintName=UK413d0a510ff03d1f19c83af9ae36, tableName=protocol_group_protocols','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-106','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',567,'EXECUTED','8:a98bf02d01801aaa6a13fc509cc26a92','addUniqueConstraint constraintName=UK4b14a8d38495c6eec56d6123f6f5, tableName=sample_treatments','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-107','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',568,'EXECUTED','8:7561419356916b73f44688f2f3580305','addUniqueConstraint constraintName=UK68b1ef05059e7f9742c5997e6f74, tableName=replicate_samples','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-108','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',569,'EXECUTED','8:ea2194664c50e13871746a7454832918','addUniqueConstraint constraintName=UK69c2d8246f1bc2e5e8d02ada96db, tableName=protocol_item_types','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-109','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',570,'EXECUTED','8:b0bb0c4a8a1bd01feb99bc6e7a9fe0fe','addUniqueConstraint constraintName=UK8d22b11cbab7be2f38b4c8c2dd1d, tableName=protocol','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-11','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',472,'EXECUTED','8:d4868259a5507ccb56603bec814b10df','createTable tableName=cell_source_treatment','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-110','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',571,'EXECUTED','8:bfd341dc7ff1244c921237c94e5f9b2a','addUniqueConstraint constraintName=UK8f7674b6c279c21eb5c882b40655, tableName=sample_in_run','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-111','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',572,'EXECUTED','8:594caebc09b3f29167086a5a67f98f57','addUniqueConstraint constraintName=UKb4d526519821b65515d64f1ac54b, tableName=project_bags','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-112','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',573,'EXECUTED','8:aa78c944ffd9aadd918dee7540a817d0','addUniqueConstraint constraintName=UKb8ab847080355581b293e4a87bcb, tableName=aligner','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-113','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',574,'EXECUTED','8:d46d0e06ac24970fcfe263848a029e3a','addUniqueConstraint constraintName=UKba7773a538d4770149b421f84583, tableName=pool_samples','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-114','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',575,'EXECUTED','8:0fc3018ed146e516a0d106fcd45ced2a','addUniqueConstraint constraintName=UKc01a900836fd7e6df0dbdc887877, tableName=target','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-115','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',576,'EXECUTED','8:27717ff424c839062d784bc3f8e5c0b5','addUniqueConstraint constraintName=UKc9cafa65fdeb3f03632764f330f8, tableName=funding','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-116','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',577,'EXECUTED','8:3b91e7857d55f4127fac8d565b2616d2','addUniqueConstraint constraintName=UKdc9d49ed85d397cd09bb505033ce, tableName=report_alignments','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-117','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',578,'EXECUTED','8:752fe044e76269fa22d2a688e0e22262','addUniqueConstraint constraintName=UKeac55f11282de8304404741a9798, tableName=item_antibody','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-118','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',579,'EXECUTED','8:e2f8b3c57160a60ddcae2d09e48e6f7b','addUniqueConstraint constraintName=UKef3609f74c61b7bd7d0ec184c0b0, tableName=sequence_alignment','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-119','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',580,'EXECUTED','8:2c4ec9f52e68b598f9c3e528f06d5c8f','addUniqueConstraint constraintName=UKf01a4847e4bdb6a7b8198123f4ae, tableName=pipeline','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-12','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',473,'EXECUTED','8:b17f7fec81da1195b30ae4e14b0d3672','createTable tableName=chores','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-120','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',581,'EXECUTED','8:1625119a5c9bff90e004f2ea5b71cbed','addUniqueConstraint constraintName=UKf38f5325c5da49e7b9b5d51d5226, tableName=project_samples','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-121','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',582,'EXECUTED','8:d8a9c8643a7e8db907e8351e10f1b67e','addUniqueConstraint constraintName=UKfbf64ee15963f7e643e3a681061d, tableName=project_user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-122','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',583,'EXECUTED','8:221b769f88aaec3c528136dbce645ad0','addForeignKeyConstraint baseTableName=cell_source_batch, constraintName=FK14vul503srgn35l05e3v1yt51, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-123','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',584,'EXECUTED','8:d5e422843e71265996044dbf1b3b1faf','addForeignKeyConstraint baseTableName=pool_samples, constraintName=FK1elc3dx8od4b22mfnj7ems7oa, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-124','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',585,'EXECUTED','8:cbbbd907fd88e785ac90171ec276026b','addForeignKeyConstraint baseTableName=protocol_group_protocols, constraintName=FK1iuch12ayvha1jsq3r49e8e2j, referencedTableName=protocol_group','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-125','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',586,'EXECUTED','8:01279f89f56dbdd3f6beb074268e2448','addForeignKeyConstraint baseTableName=sample, constraintName=FK1rdptfp7u4obmb8eikuw5nnrf, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-126','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',587,'EXECUTED','8:e532b467a6d132d4dab456d60a6b5857','addForeignKeyConstraint baseTableName=genome, constraintName=FK1v0f049a0ljh0ohpc585xwb10, referencedTableName=species','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-127','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',588,'EXECUTED','8:33a818f2c148cb1901a462e4dc54bfa3','addForeignKeyConstraint baseTableName=sequencing_experiment, constraintName=FK2a04ld6c4th452p48yqrqdhlq, referencedTableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-128','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',589,'EXECUTED','8:4482a79fe732bb81f26b6a5b12cd39ff','addForeignKeyConstraint baseTableName=sample, constraintName=FK2hvogurfkgktbxxvdo2csoa8v, referencedTableName=antibody','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-129','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',590,'EXECUTED','8:f087f37b8736ab19903c2aa3a2821d8f','addForeignKeyConstraint baseTableName=sequencing_cohort, constraintName=FK2kjc8ji0k7cxldfqvqqmr6qpq, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-13','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',474,'EXECUTED','8:01d7d2770382940313ffa0172086f629','createTable tableName=chrom_sequence','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-130','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',591,'EXECUTED','8:977c4bb1e530a2341f206f8e5c647a0d','addForeignKeyConstraint baseTableName=strain, constraintName=FK2ouetcuudlbctcmfse7fuuuo8, referencedTableName=strain','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-131','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',592,'EXECUTED','8:19c9829259f481492dd873790175be12','addForeignKeyConstraint baseTableName=protocol, constraintName=FK2ugvxqbj45ie8pfijctcp5m44, referencedTableName=assay','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-132','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',593,'EXECUTED','8:7869b2f585a92601be2cae56aab34bbf','addForeignKeyConstraint baseTableName=antibody, constraintName=FK2wi5vsoyxjfxl0mc5v1mm9dts, referencedTableName=target','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-133','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',594,'EXECUTED','8:938d25314c5a3a29fa05596b5f0de151','addForeignKeyConstraint baseTableName=project_bags, constraintName=FK2xobskjd8bp5b128rh1duijt0, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-134','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',595,'EXECUTED','8:b21bbe65c0b94649ff24231af8e2e97e','addForeignKeyConstraint baseTableName=report_alignments, constraintName=FK33tihrorftk27epehcm1be8ci, referencedTableName=sequence_alignment','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-135','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',596,'EXECUTED','8:d3cf6be4d664eff82f48af81e23dda91','addForeignKeyConstraint baseTableName=antibody, constraintName=FK38l2x1xyohqre04awha89rr15, referencedTableName=ig_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-136','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',597,'EXECUTED','8:07dcfe4d5c9700e14e10e98ce158d032','addForeignKeyConstraint baseTableName=protocol_instance, constraintName=FK3kt03fn9v9icvw7iam3ri2q2e, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-137','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',598,'EXECUTED','8:042aeccb03c96f969d8423d9811923d0','addForeignKeyConstraint baseTableName=project_funding, constraintName=FK3sab04b4896qhtxtm9kmwb6p3, referencedTableName=funding','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-138','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',599,'EXECUTED','8:124704a66e5c1df79db3700462e141fb','addForeignKeyConstraint baseTableName=cell_source, constraintName=FK49xxhx356gij34jl4uxnwknyw, referencedTableName=organization','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-139','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',600,'EXECUTED','8:7f660e5d88bdb3355c6485f4961778ab','addForeignKeyConstraint baseTableName=project_user, constraintName=FK4jl2o131jivd80xsuw6pivnbx, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-14','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',475,'EXECUTED','8:f23812808d1cfcc45827d6e0412eeb56','createTable tableName=chromosome','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-140','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',601,'EXECUTED','8:576241ca3ab54379e1ed47e9e9e6d4a2','addForeignKeyConstraint baseTableName=user_role_group, constraintName=FK4u9p3asv1hflckgxegcafajkm, referencedTableName=role_group','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-141','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',602,'EXECUTED','8:e2e12d0879dab96928fb57cc3e141edc','addForeignKeyConstraint baseTableName=project_user, constraintName=FK4ug72llnm0n7yafwntgdswl3y, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-142','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',603,'EXECUTED','8:f6d830a9a5b613170806fc45cdd4599a','addForeignKeyConstraint baseTableName=sample_in_run, constraintName=FK50pk7cu1ix56x21n43qpmc9rs, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-143','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',604,'EXECUTED','8:c22f6bdea27bf7eae888ed6da7facb97','addForeignKeyConstraint baseTableName=protocol_instance_items, constraintName=FK521y62ybe1gxfnde38pvohr14, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-144','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',605,'EXECUTED','8:5ae774b15088113a566de04c576d9919','addForeignKeyConstraint baseTableName=sample, constraintName=FK579rlyfhja2i87deo6dgndfnj, referencedTableName=cell_source','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-145','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',606,'EXECUTED','8:20ccca54d94f957c913c84bd1aaa5090','addForeignKeyConstraint baseTableName=sequence_run, constraintName=FK5gbjvf2tscq7vyhwbadq2jbtx, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-146','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',607,'EXECUTED','8:15f12e3c9415bc0ddd7cf464a5819387','addForeignKeyConstraint baseTableName=control_sample, constraintName=FK5luc1hgx7j675npfvs5k3jngq, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-147','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',608,'EXECUTED','8:f90848bc247d0bb31f254d75a7dff008','addForeignKeyConstraint baseTableName=strain, constraintName=FK6ikjjjvxicw58055xdk37tfj0, referencedTableName=species','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-148','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',609,'EXECUTED','8:6266d51f55a306761517e1b802bda933','addForeignKeyConstraint baseTableName=protocol_instance_summary, constraintName=FK6vn6v7g4c33vcd6pja47o9lxm, referencedTableName=protocol','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-149','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',610,'EXECUTED','8:50477e9df773cc248ca0bbf62caecb29','addForeignKeyConstraint baseTableName=organization, constraintName=FK6xw1bsvdkcf10rvj1feetks4p, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-15','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',476,'EXECUTED','8:1367c9d2b419674419a131c2324c0fe0','createTable tableName=control_sample','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-150','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',611,'EXECUTED','8:61c8c23790a42322909fddcce6ea6ed9','addForeignKeyConstraint baseTableName=target, constraintName=FK7fd5o5g240p415qfh8ojmj9bo, referencedTableName=target_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-151','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',612,'EXECUTED','8:b11af0ac78231d324bba1ae0edededcb','addForeignKeyConstraint baseTableName=user_role, constraintName=FK859n2jvi8ivhui0rl0esws6o, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-152','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',613,'EXECUTED','8:7a714f1b0102c62370fd3f6a571540d7','addForeignKeyConstraint baseTableName=cell_source, constraintName=FK868cjl2jx1t1enrfst5ce1un9, referencedTableName=strain','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-153','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',614,'EXECUTED','8:1545659cc99a3ca3716081019ec5f7d5','addForeignKeyConstraint baseTableName=sample_sequence_indices, constraintName=FK8dhe3xyoc3br0fr7uws3svyds, referencedTableName=sequence_index','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-154','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',615,'EXECUTED','8:055a433bd76e0c7e6140cdb7b7f8516e','addForeignKeyConstraint baseTableName=protocol_instance_items, constraintName=FK8pjccf4a3c9n2ja8k9cq6v38p, referencedTableName=protocol_instance','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-155','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',616,'EXECUTED','8:dee084f27fb06247a6295f8dbc5df120','addForeignKeyConstraint baseTableName=report_alignments, constraintName=FK8ui05vjl12r7jwq8bdye2jsig, referencedTableName=summary_report','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-156','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',617,'EXECUTED','8:1929ad8e1a476b24ec58d9fb43c3e191','addForeignKeyConstraint baseTableName=project_samples, constraintName=FK95dx7fkv6r65xk5r9qcng8cin, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-157','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',618,'EXECUTED','8:5a586e76adeee9d709629c17ed2ecc18','addForeignKeyConstraint baseTableName=organization, constraintName=FK97eigsvq2tsrd2bge4ox651wh, referencedTableName=address','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-158','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',619,'EXECUTED','8:42605c0b6d98f36e29e13730c065aa8d','addForeignKeyConstraint baseTableName=batch_cell_sources, constraintName=FK99x1hjei6ogdcxm1w4fxvfr6q, referencedTableName=cell_source','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-159','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',620,'EXECUTED','8:c337388e6175d2671bb90873534e4ae4','addForeignKeyConstraint baseTableName=cell_source, constraintName=FK9bw1i4huctjx4330y93axijm1, referencedTableName=tissue','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-16','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',477,'EXECUTED','8:751fa61c17baa762e00c2eb51ccfdb25','createTable tableName=definition','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-160','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',621,'EXECUTED','8:dca93cc0c78b243178779449cc830326','addForeignKeyConstraint baseTableName=project_funding, constraintName=FK9l2tp6kpxf1us967h9092rsu3, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-161','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',622,'EXECUTED','8:edd07cd691777ae894d0588cd047f117','addForeignKeyConstraint baseTableName=user_role, constraintName=FKa68196081fvovjhkek5m97n3y, referencedTableName=role','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-162','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',623,'EXECUTED','8:5769eb93f8c30b5cf8c3e5e137c500ab','addForeignKeyConstraint baseTableName=chromosome, constraintName=FKam69crakd25kjy7d2rqiy5108, referencedTableName=genome','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-163','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',624,'EXECUTED','8:77df3c31e3dd3003a198b6261a6c55ca','addForeignKeyConstraint baseTableName=run_stats, constraintName=FKar55v5cio0nmrqv7opgg1n8cl, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-164','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',625,'EXECUTED','8:574b0585f524d5117fe9612052f7ef8b','addForeignKeyConstraint baseTableName=project_bags, constraintName=FKb4e1wnuhpvapbhnyjs6dqk8mo, referencedTableName=protocol_instance_bag','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-165','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',626,'EXECUTED','8:b8651c3ed970fb7dd7680fc3e908f8d1','addForeignKeyConstraint baseTableName=protocol_item_types, constraintName=FKb5ck29pggprrfleshlkhx5qr8, referencedTableName=item_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-166','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',627,'EXECUTED','8:16c7577159d877d21fcac61967e49ffe','addForeignKeyConstraint baseTableName=replicate_set, constraintName=FKbesib3k7784q0fr1a2si9y576, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-167','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',628,'EXECUTED','8:ad5f8feb9581f1d8018bdd3b846424e1','addForeignKeyConstraint baseTableName=role_group_role, constraintName=FKbo8cffthrm8wxgtnhg0i8dcxw, referencedTableName=role','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-168','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',629,'EXECUTED','8:83a19185ced91c62b015e9b9b745029f','addForeignKeyConstraint baseTableName=sample, constraintName=FKbpci4r4ynak9f0hby2pmaw0bo, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-169','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',630,'EXECUTED','8:3f0fbc6d7123fd85f1d76e318653885a','addForeignKeyConstraint baseTableName=organization, constraintName=FKc30yedjwp9qw1f3nn2ytda7tj, referencedTableName=organization','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-17','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',478,'EXECUTED','8:2462b8863a89c2acd7c6f39a5db8662c','createTable tableName=funding','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-170','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',631,'EXECUTED','8:73f92cc12bfb4510a53b9e827323c9ce','addForeignKeyConstraint baseTableName=sequencing_experiment, constraintName=FKcb969swv4c6xbdb3kw93txww1, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-171','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',632,'EXECUTED','8:fffa7ffe1e7133bd26cac11a8373a318','addForeignKeyConstraint baseTableName=batch_cell_sources, constraintName=FKcjurv5d0t0u6yx5tl79fh0ka4, referencedTableName=cell_source_batch','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-172','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',633,'EXECUTED','8:161075f6df87d970d990bf8452f2eb76','addForeignKeyConstraint baseTableName=sample, constraintName=FKcpc4hhf4ntlq02q576nu2ky44, referencedTableName=assay','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-173','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',634,'EXECUTED','8:6287090ef5b33d5ebc5809f586d67759','addForeignKeyConstraint baseTableName=user, constraintName=FKddefmvbrws3hvl5t0hnnsv8ox, referencedTableName=address','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-174','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',635,'EXECUTED','8:483bccc50eb97b2d756f456b82225988','addForeignKeyConstraint baseTableName=cell_source, constraintName=FKdspbw8rsnb9h8bttdb5a4qyq, referencedTableName=inventory','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-175','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',636,'EXECUTED','8:a597656af612a2af9627be7923bec254','addForeignKeyConstraint baseTableName=sequence_alignment, constraintName=FKdvg4g4a0pby4puygnvyof5cu4, referencedTableName=sequencing_experiment','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-176','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',637,'EXECUTED','8:8085461eba9aaa0f6855a52ce4549bb9','addForeignKeyConstraint baseTableName=token, constraintName=FKe32ek7ixanakfqsdaokm4q9y2, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-177','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',638,'EXECUTED','8:ac815a5185e1d4e10b08d6ec5c77dc86','addForeignKeyConstraint baseTableName=organization, constraintName=FKepyd6uqewfcp3xjvkr28mksul, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-178','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',639,'EXECUTED','8:1617beb1fe2a72d6aa99a570bce51bca','addForeignKeyConstraint baseTableName=user_role_group, constraintName=FKexdgxebi2id0avjqhenhkmem9, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-179','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',640,'EXECUTED','8:241c737a6ee8eeb0172a9b368896e36f','addForeignKeyConstraint baseTableName=analysis, constraintName=FKey194jla0jtivm1mgn7tn7g0i, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-18','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',479,'EXECUTED','8:961e3120e23e7ff36a349ef43695fdaf','createTable tableName=genome','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-180','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',641,'EXECUTED','8:42b297456d68d1c14cf4e6d7e51f4068','addForeignKeyConstraint baseTableName=sequence_run, constraintName=FKf27lk1j5r09jgtjl198kdtqje, referencedTableName=run_stats','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-181','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',642,'EXECUTED','8:0f4a010a9cfff07c5a944dbaa76c58e1','addForeignKeyConstraint baseTableName=sample_treatments, constraintName=FKf2vfwolapiodnq3iy2hi4gs9a, referencedTableName=cell_source_treatment','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-182','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',643,'EXECUTED','8:d7919b4a0f315ad87357fb46d69ecdd1','addForeignKeyConstraint baseTableName=item, constraintName=FKf60hnjyqgladtp0jw5o0n4e9u, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-183','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',644,'EXECUTED','8:3fac537574d68a159cc3f681544bd596','addForeignKeyConstraint baseTableName=inventory, constraintName=FKf7o8jsln5b13i6p3k5y75cgjo, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-184','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',645,'EXECUTED','8:d1b037096497b156ea38cd934da8a135','addForeignKeyConstraint baseTableName=cell_source, constraintName=FKfc39not4lgs7gunubj62b5nsx, referencedTableName=sex','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-185','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',646,'EXECUTED','8:ede80123514ccb1ca84200e3f6506020','addForeignKeyConstraint baseTableName=item_antibody, constraintName=FKg3hetijuop4bqe2dnhdqhgo51, referencedTableName=antibody','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-186','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',647,'EXECUTED','8:c8102f6a6c008f6349141c495a6fb350','addForeignKeyConstraint baseTableName=item, constraintName=FKg9lymegmlbvqtjrwpvkcdo5gr, referencedTableName=item_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-187','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',648,'EXECUTED','8:4fd82cb6452fa1a8b79358f2a3a7cb16','addForeignKeyConstraint baseTableName=sequencing_experiment, constraintName=FKgbnh1t5mfymeuu3ltqk2q8kp0, referencedTableName=read_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-188','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',649,'EXECUTED','8:d6381894e8491a5a8f1e0b9122ccd83b','addForeignKeyConstraint baseTableName=protocol_instance_bag, constraintName=FKgf7cmwy4enta8934copgfm80b, referencedTableName=protocol_group','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-189','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',650,'EXECUTED','8:34d461393e4e88720fb326adbf75e313','addForeignKeyConstraint baseTableName=user, constraintName=FKgrefofj2fru3hqquh5ep0mviq, referencedTableName=organization','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-19','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',480,'EXECUTED','8:20c5b4767a441835de36e04262bf9e42','createTable tableName=growth_media','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-190','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',651,'EXECUTED','8:ff9a6a343e211ad29399a89e1dc6d1c9','addForeignKeyConstraint baseTableName=item, constraintName=FKh4epdoqikj4sfedlxcc9dwwnl, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-191','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',652,'EXECUTED','8:963a25cf1c602b447e2c1f3dff3fd6c8','addForeignKeyConstraint baseTableName=sample, constraintName=FKhflf768ds23ev1ywoqsp42p96, referencedTableName=target','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-192','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',653,'EXECUTED','8:2797f5364ecb54a65398b35b3af0a5c4','addForeignKeyConstraint baseTableName=protocol_group, constraintName=FKhin7nog9se54hev1i9697ux4t, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-193','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',654,'EXECUTED','8:56fc68c1229ece00a8fc87902c47b6bc','addForeignKeyConstraint baseTableName=cell_source, constraintName=FKhyjubhp4aikim11btnsynpfgd, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-194','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',655,'EXECUTED','8:cbc049fdc0b5004d09bfe83e2f07e42d','addForeignKeyConstraint baseTableName=protocol_instance_summary, constraintName=FKi70wyau9uwahxc4rtkhwf3n23, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-195','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',656,'EXECUTED','8:5a756ceb2bd60f53e2e7cce37d54c51d','addForeignKeyConstraint baseTableName=item_sequence_indices, constraintName=FKjhtgywgjb12yr7ljvpfn5s8eq, referencedTableName=sequence_index','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-196','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',657,'EXECUTED','8:c3880dd8f322e9932170161fad706228','addForeignKeyConstraint baseTableName=sequence_run, constraintName=FKjjf30r4ua6ru9e0ls0qhbwiei, referencedTableName=sequencing_platform','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-197','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',658,'EXECUTED','8:ca85adf4e158f30a4e72e419855a30a3','addForeignKeyConstraint baseTableName=sample_in_run, constraintName=FKjroa7jvf2q9kf1f3fasxiy735, referencedTableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-198','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',659,'EXECUTED','8:9e999e8cce01a003938448abe0b1e963','addForeignKeyConstraint baseTableName=protocol_item_types, constraintName=FKjw224bhrckc7lnuqc9r8p9y1g, referencedTableName=protocol','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-199','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',660,'EXECUTED','8:55e4a59154d7086e9f53d8299ec218fd','addForeignKeyConstraint baseTableName=antibody, constraintName=FKjwhhs6m573clakbtqb6ggmfn, referencedTableName=ab_host','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-2','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',463,'EXECUTED','8:c50f809a6ec5d05b76668bd4b2fce439','createTable tableName=address','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-20','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',481,'EXECUTED','8:3bf6ecb5ff5858af71b1f9ee5aa1e4c8','createTable tableName=histology','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-200','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',661,'EXECUTED','8:e763efd032149d60daf698099d24c915','addForeignKeyConstraint baseTableName=item_type, constraintName=FKk3eijjkf35q5w944pwbb412u6, referencedTableName=item_type_category','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-201','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',662,'EXECUTED','8:5e630b9c26e59baf4540288fc47bbe02','addForeignKeyConstraint baseTableName=project_samples, constraintName=FKk7psrd21b6ftrh8y2xfm7yx7j, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-202','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',663,'EXECUTED','8:910943faeeed714dcb32369dde33896f','addForeignKeyConstraint baseTableName=protocol_instance, constraintName=FKl3j40ti2vyc6c4w1kbp6eb3u6, referencedTableName=protocol_instance_bag','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-203','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',664,'EXECUTED','8:af6075a3513d21d1c29c53047472018d','addForeignKeyConstraint baseTableName=chrom_sequence, constraintName=FKl7bmacg1b4eca57cotc7ils74, referencedTableName=chromosome','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-204','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',665,'EXECUTED','8:ab774fe3a399a1faceb3d62fbd2420cc','addForeignKeyConstraint baseTableName=role_group_role, constraintName=FKlu0ge9c3rhabcfu59589trt1m, referencedTableName=role_group','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-205','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',666,'EXECUTED','8:e9628fff1844d76c158db34c4f6130ea','addForeignKeyConstraint baseTableName=item_sequence_indices, constraintName=FKm08yv7tx0ymrjv7un1wncej95, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-206','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',667,'EXECUTED','8:48bd9dc84014f96286d7fe4f9097bfbe','addForeignKeyConstraint baseTableName=sequence_alignment, constraintName=FKm3h3wvtgyqcbyp7kaac35wj57, referencedTableName=genome','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-207','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',668,'EXECUTED','8:b64ff583e4e147c557cebbf4d8782598','addForeignKeyConstraint baseTableName=sequence_run, constraintName=FKmcddrdwrtqwy0h5wfiplislxj, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-208','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',669,'EXECUTED','8:e30eeb64f5d49ac9324be5c53bfac5a9','addForeignKeyConstraint baseTableName=sequencing_experiment, constraintName=FKmqikne1qqkrnll81iu1sfre, referencedTableName=sequencing_cohort','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-209','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',670,'EXECUTED','8:2ce4904609f8a8d1978fc1c8b4cb31a7','addForeignKeyConstraint baseTableName=history, constraintName=FKn4gjyu69m6xa5f3bot571imbe, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-21','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',482,'EXECUTED','8:e8b39dc757b2f08a20c9f2c7b327eb8d','createTable tableName=history','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-210','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',671,'EXECUTED','8:0ce0335eb0e391ab740a6103ee0d2eb4','addForeignKeyConstraint baseTableName=histology, constraintName=FKn6wxriw2r0bt5lepofmjshgrc, referencedTableName=histology','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-211','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',672,'EXECUTED','8:45d7bccb64382b585d7303ccd734ffe6','addForeignKeyConstraint baseTableName=cell_source, constraintName=FKndexf4a4taolqvll9kmbh8kkg, referencedTableName=histology','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-212','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',673,'EXECUTED','8:73ec926ac04701fef2b7516be5ecb01e','addForeignKeyConstraint baseTableName=history, constraintName=FKo85y510lpxvd8vyk32diab6qq, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-213','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',674,'EXECUTED','8:0fec3567f93b5ed74fa9b52a78d4dcd2','addForeignKeyConstraint baseTableName=sample, constraintName=FKo86ao5favegpdv2p45ekxus9o, referencedTableName=invoice','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-214','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',675,'EXECUTED','8:0c2aad14023f1e6bbd018ef98d28757c','addForeignKeyConstraint baseTableName=sequence_alignment, constraintName=FKok3dqhynp748nr1ur1firmt14, referencedTableName=aligner','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-215','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',676,'EXECUTED','8:5a12c10f1569c3e8e3ea5d2d4a7d81b5','addForeignKeyConstraint baseTableName=item_antibody, constraintName=FKoklrp1sxftr4iyjrxjhiykio2, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-216','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',677,'EXECUTED','8:4a468ac8352ab55e5a3b0d06dc24579f','addForeignKeyConstraint baseTableName=sample, constraintName=FKorx0c9myw8smxka4o3ir837ps, referencedTableName=cell_source','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-217','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',678,'EXECUTED','8:55de7235a1a462d3b39c53d8c7b39c92','addForeignKeyConstraint baseTableName=sample, constraintName=FKp8cqy6861ds7dco3txn0ioh3c, referencedTableName=protocol_instance_summary','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-218','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',679,'EXECUTED','8:95ad75cc5015daab3129bf61d546e7a6','addForeignKeyConstraint baseTableName=sequencing_cohort, constraintName=FKq03aoroe14bkb93n2jpcmfpn, referencedTableName=summary_report','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-219','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',680,'EXECUTED','8:b77f80ed785e225b5706b4e5d3b1f509','addForeignKeyConstraint baseTableName=sequence_alignment, constraintName=FKq4d03x395u6vghiwp58qmqdf7, referencedTableName=pipeline','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-22','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',483,'EXECUTED','8:eeb5b31cc9a7539d48bb8aaeb6872893','createTable tableName=ig_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-220','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',681,'EXECUTED','8:48aace6405edd5caf604d87fbfa4dccd','addForeignKeyConstraint baseTableName=sequencing_cohort, constraintName=FKqe8146g7qcr91mhbhlnkowoq5, referencedTableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-221','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',682,'EXECUTED','8:f1571353a843130413b4a28df8203bd9','addForeignKeyConstraint baseTableName=protocol_instance, constraintName=FKqhnl5itkka9paugla9ss836wb, referencedTableName=protocol','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-222','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',683,'EXECUTED','8:fcef96ec06d6fe5dca2a2c7260e95d78','addForeignKeyConstraint baseTableName=pool_samples, constraintName=FKqou16vog2akhov2b03410qna, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-223','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',684,'EXECUTED','8:d549a2faf992528e3e8513ab762f09bb','addForeignKeyConstraint baseTableName=protocol, constraintName=FKqtg4kcmhhbl3evj87vvf71xvf, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-224','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',685,'EXECUTED','8:ab3a80e148dcee043f31cef32a755cd2','addForeignKeyConstraint baseTableName=replicate_samples, constraintName=FKqwoluiec2sxnxtbhuoj4e7858, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-225','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',686,'EXECUTED','8:b96550fcecc885d186d3e581ce5f5a86','addForeignKeyConstraint baseTableName=item, constraintName=FKqwonew7r6scr7si8k2cei8wjo, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-226','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',687,'EXECUTED','8:4e5504e5d56080ed05f63c9405d116ca','addForeignKeyConstraint baseTableName=antibody, constraintName=FKr7hx5ykvqtjo1slxyedbeon0j, referencedTableName=organization','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-227','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',688,'EXECUTED','8:dfeeb9a0514f3abfcb0530715969ec4f','addForeignKeyConstraint baseTableName=cell_source, constraintName=FKroomr52lbjofo8ly77vayrsuu, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-228','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',689,'EXECUTED','8:b9cde3f8aa6a865b1d4afed09c2fa58a','addForeignKeyConstraint baseTableName=sample_treatments, constraintName=FKrq3vygba0p5l99k8ej2msf758, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-229','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',690,'EXECUTED','8:07279fbe4068014e172a047cbd029dd3','addForeignKeyConstraint baseTableName=replicate_samples, constraintName=FKrvwftgvok41hx8n02aym7slsp, referencedTableName=replicate_set','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-23','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',484,'EXECUTED','8:bcd27c274f84dcb58ea9a86be2eb04a4','createTable tableName=inventory','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-230','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',691,'EXECUTED','8:addf7258f2c422bacf73d7930dcd7c2f','addForeignKeyConstraint baseTableName=strain, constraintName=FKrwpjbguvhieg265qb4sjmofnr, referencedTableName=organization','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-231','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',692,'EXECUTED','8:051bfbd9c8cf44edd98f56e0f19440a7','addForeignKeyConstraint baseTableName=antibody, constraintName=FKrwq0pnfbecmygn4xofqr4qtw3, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-232','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',693,'EXECUTED','8:e8649e00d8a21abf9d14d95d7b55f1e4','addForeignKeyConstraint baseTableName=control_sample, constraintName=FKs2ks4b5o1f4a670k9ynottyrp, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-233','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',694,'EXECUTED','8:573baa0a3433baa788abebc0ed59c0da','addForeignKeyConstraint baseTableName=cell_source, constraintName=FKs656nbjgufpx0t833xbu78mck, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-234','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',695,'EXECUTED','8:62f38505c983161a1926118f736955c7','addForeignKeyConstraint baseTableName=analysis, constraintName=FKsmacedtxj270658jj6r8hh5qb, referencedTableName=sequence_alignment','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-235','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',696,'EXECUTED','8:79efaf1754a76b54022d1dc43f35fa9f','addForeignKeyConstraint baseTableName=protocol_group_protocols, constraintName=FKsp79j0667g22q9e72dp0617sd, referencedTableName=protocol','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-236','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',697,'EXECUTED','8:9085ae6dcee7713a74d2000bf5021664','addForeignKeyConstraint baseTableName=sequence_alignment, constraintName=FKsw06tb5ys7obob4e2cpovuwr8, referencedTableName=align_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-237','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',698,'EXECUTED','8:cf73480e27b35a928e0714b9baa5ee9d','addForeignKeyConstraint baseTableName=sample, constraintName=FKswwuixwogcl48lwy3ucvvqaub, referencedTableName=growth_media','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-238','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',699,'EXECUTED','8:a3df5dfc847cdccaf44efad7f61198c3','addForeignKeyConstraint baseTableName=sample_sequence_indices, constraintName=FKtbax39c2nyeqk46yiwk6xj9l, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-239','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',700,'EXECUTED','8:198cc5d7aa8dc247e7651ed8059cdf61','addForeignKeyConstraint baseTableName=growth_media, constraintName=FKte5f01yhaud29abdr878pjgsi, referencedTableName=species','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-24','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',485,'EXECUTED','8:bdc3ec2fbb355270510500f2f7a8f817','createTable tableName=invoice','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-25','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',486,'EXECUTED','8:a913aa4c773268ee0dee598b5a55bef2','createTable tableName=item','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-26','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',487,'EXECUTED','8:7aba465d038ee89e7cd86fac7436ebb1','createTable tableName=item_antibody','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-27','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',488,'EXECUTED','8:b7a389692f89e6cba6f16a2f3074ce1e','createTable tableName=item_sequence_indices','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-28','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',489,'EXECUTED','8:a5afba94060cc8c0249bd5c56c10fc31','createTable tableName=item_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-29','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',490,'EXECUTED','8:44d1aa9fa2657917c70e748cbcba27dd','createTable tableName=item_type_category','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-3','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',464,'EXECUTED','8:e6b78b423cac57d76901bc898d617b0e','createTable tableName=align_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-30','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',491,'EXECUTED','8:9debd724090a9b345fe6da3dadbad03e','createTable tableName=organization','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-31','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',492,'EXECUTED','8:e212aca255942b7d7fa21d138feff7bb','createTable tableName=pipeline','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-32','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',493,'EXECUTED','8:1f9bd32747f167621bf1f62cb9d0407e','createTable tableName=pool_samples','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-33','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',494,'EXECUTED','8:ae33c4ef0f802f4a9e14e9fded38267a','createTable tableName=project','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-34','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',495,'EXECUTED','8:8f8852bc84cc2bbdb234f760422c2fb8','createTable tableName=project_bags','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-35','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',496,'EXECUTED','8:8ef5fec78093f4aaaebfef7573e24679','createTable tableName=project_funding','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-36','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',497,'EXECUTED','8:e89532589d5fe13d81a6d3348e80525b','createTable tableName=project_samples','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-37','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',498,'EXECUTED','8:9b40eec18ef26cafec8c51fae40bc5a2','createTable tableName=project_user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-38','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',499,'EXECUTED','8:988610d4c395f1a4798c872bd9a5967e','createTable tableName=protocol','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-39','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',500,'EXECUTED','8:e0be8ba6fcdb9951f21cfb07d8615201','createTable tableName=protocol_group','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-4','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',465,'EXECUTED','8:c677e3448869d131ea3eb78d6921678e','createTable tableName=aligner','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-40','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',501,'EXECUTED','8:536887a4f9305e0bac78b0769a5ba96c','createTable tableName=protocol_group_protocols','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-41','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',502,'EXECUTED','8:e6c9cec2a28ff312ecf2ef4e7c11c29b','createTable tableName=protocol_instance','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-42','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',503,'EXECUTED','8:ca8e6cb0a19900ac7e355e23193ff5a2','createTable tableName=protocol_instance_bag','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-43','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',504,'EXECUTED','8:a8415d5b650e31c364faf8853e9ab783','createTable tableName=protocol_instance_items','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-44','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',505,'EXECUTED','8:ef4a980a23f4ad6007bb1d985cd49989','createTable tableName=protocol_instance_summary','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-45','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',506,'EXECUTED','8:cb4c0f3c04fef437d20aac9eedcd49be','createTable tableName=protocol_item_types','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-46','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',507,'EXECUTED','8:db0415894c2d1488496804adac468154','createTable tableName=read_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-47','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',508,'EXECUTED','8:d2d096a31928818ec401d00f8e49278f','createTable tableName=replicate_samples','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-48','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',509,'EXECUTED','8:ee795edcb15ba2bad823234642c766e0','createTable tableName=replicate_set','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-49','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',510,'EXECUTED','8:a5e555191ece91663aec1bd1cb4e528d','createTable tableName=report_alignments','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-5','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',466,'EXECUTED','8:ccc4411824f853afd87b44265cba54f9','createTable tableName=analysis','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-50','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',511,'EXECUTED','8:d98f1f191189f135d62602990e7ddb32','createTable tableName=role','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-51','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',512,'EXECUTED','8:c2eb7705294021215384f180db9c96fd','createTable tableName=role_group','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-52','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',513,'EXECUTED','8:30b930611b059e4ddf0fb6f8301f0a06','createTable tableName=role_group_role','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-53','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',514,'EXECUTED','8:087974db8d8a17a6ae063988d694052d','createTable tableName=run_stats','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-54','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',515,'EXECUTED','8:0ccf21d0341999ddedeb2df54093f7cc','createTable tableName=sample','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-55','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',516,'EXECUTED','8:b710a17e7f53976cdcc67771771282a9','createTable tableName=sample_in_run','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-56','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',517,'EXECUTED','8:0ea9315da1aca97e486d23041b870d84','createTable tableName=sample_sequence_indices','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-57','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',518,'EXECUTED','8:7be70664055b59b9850996b85fdfa4b6','createTable tableName=sample_treatments','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-58','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',519,'EXECUTED','8:3617b94b4af5cbead6dbbb5ea4280295','createTable tableName=sequence_alignment','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-59','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',520,'EXECUTED','8:b7ee176648c454a0185409c8ea94c4f1','createTable tableName=sequence_index','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-6','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',467,'EXECUTED','8:a4ae196c7262639b005ba41ad652933a','createTable tableName=antibody','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-60','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',521,'EXECUTED','8:ef23f2114c0f29b8b8adf856313632bc','createTable tableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-61','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',522,'EXECUTED','8:af23f75a0fd357072b1a8a32cd7500df','createTable tableName=sequencing_cohort','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-62','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',523,'EXECUTED','8:2311f725da9719b10478312619deeb96','createTable tableName=sequencing_experiment','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-63','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',524,'EXECUTED','8:da8ef34f7844bc77ed0241c960b1e034','createTable tableName=sequencing_platform','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-64','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',525,'EXECUTED','8:88f9b01cdfc57f348c32249162e1040b','createTable tableName=sex','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-65','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',526,'EXECUTED','8:7d488fabf1827cff083802cc319e5a6d','createTable tableName=species','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-66','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',527,'EXECUTED','8:216ee06f704287dfe48d3e3233e66757','createTable tableName=strain','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-67','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',528,'EXECUTED','8:a7f847bd61e35c4326ec78b328862599','createTable tableName=summary_report','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-68','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',529,'EXECUTED','8:77514f1dc663293ce40f281c375cf59e','createTable tableName=target','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-69','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',530,'EXECUTED','8:fe21686eb7515b2e02b0251b3f197097','createTable tableName=target_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-7','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',468,'EXECUTED','8:97e8c4142a954cd286debe82192f2b0e','createTable tableName=assay','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-70','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',531,'EXECUTED','8:8ce651ab5d7cef0a117a0312df8a0cd4','createTable tableName=tissue','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-71','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',532,'EXECUTED','8:f589188aef0197181fcefa8de05545af','createTable tableName=token','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-72','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',533,'EXECUTED','8:77eb644bf5a199ea6470996128793b83','createTable tableName=user','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-73','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',534,'EXECUTED','8:bc393321ff0633db20714833999b406d','createTable tableName=user_role','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-74','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',535,'EXECUTED','8:b2ba40e71e82347d46e045c0b11d20c9','createTable tableName=user_role_group','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-75','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',536,'EXECUTED','8:57bde8f523161c8f0788a6fecefbb1f0','addUniqueConstraint constraintName=UC_AB_HOSTNAME_COL, tableName=ab_host','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-76','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',537,'EXECUTED','8:990e23cf760c725a9e2f8b419ff91795','addUniqueConstraint constraintName=UC_ALIGN_TYPENAME_COL, tableName=align_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-77','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',538,'EXECUTED','8:53e5dc66b3ac981d36170742537d2395','addUniqueConstraint constraintName=UC_ALIGN_TYPESHORT_NAME_COL, tableName=align_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-78','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',539,'EXECUTED','8:23af7b9e9b84424597b7b4778a4670a5','addUniqueConstraint constraintName=UC_ASSAYNAME_COL, tableName=assay','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-79','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',540,'EXECUTED','8:bda66e3edceb5c22de3d4bd7776838d8','addUniqueConstraint constraintName=UC_CELL_SOURCE_TREATMENTNAME_COL, tableName=cell_source_treatment','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-8','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',469,'EXECUTED','8:50c86dd292ff57c743be3797b2f01e87','createTable tableName=batch_cell_sources','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-80','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',541,'EXECUTED','8:c72ba9a028370959b8d9556ff7ccd606','addUniqueConstraint constraintName=UC_CHORESNAME_COL, tableName=chores','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-81','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',542,'EXECUTED','8:c05426646ca0b2932cd859662959f3cc','addUniqueConstraint constraintName=UC_CHROMOSOMENAME_COL, tableName=chromosome','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-82','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',543,'EXECUTED','8:c1ba283cf38ce4cb3738442046aea49b','addUniqueConstraint constraintName=UC_DEFINITIONNAME_COL, tableName=definition','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-83','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',544,'EXECUTED','8:58e4847a3cb398d43ae42f0917387cae','addUniqueConstraint constraintName=UC_GROWTH_MEDIANAME_COL, tableName=growth_media','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-84','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',545,'EXECUTED','8:b1f2a8e869c755b9af455b7f8975c651','addUniqueConstraint constraintName=UC_HISTOLOGYNAME_COL, tableName=histology','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-85','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',546,'EXECUTED','8:b372c46838e13e8ec5fd22b951c71c8f','addUniqueConstraint constraintName=UC_IG_TYPENAME_COL, tableName=ig_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-86','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',547,'EXECUTED','8:ff41db1713644f36cc8057067e3243ab','addUniqueConstraint constraintName=UC_ITEMBARCODE_COL, tableName=item','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-87','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',548,'EXECUTED','8:c69ed7c6101b90e6a3582aac440a241f','addUniqueConstraint constraintName=UC_ITEM_TYPENAME_COL, tableName=item_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-88','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',549,'EXECUTED','8:2c31b039e6f5d377628138bb9b00eb06','addUniqueConstraint constraintName=UC_ITEM_TYPE_CATEGORYNAME_COL, tableName=item_type_category','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-89','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',550,'EXECUTED','8:5f40eb9d7f0544b76ed3416bbe19d66b','addUniqueConstraint constraintName=UC_ORGANIZATIONNAME_COL, tableName=organization','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-9','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:54',470,'EXECUTED','8:d66553c04705131e9ebeb48a40240878','createTable tableName=cell_source','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-90','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',551,'EXECUTED','8:671751aef5ac174afdd2ad8e9484d3ef','addUniqueConstraint constraintName=UC_PIPELINEWORKFLOW_ID_COL, tableName=pipeline','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-91','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',552,'EXECUTED','8:31087754826d3f8a25bc9a328fc0d9fb','addUniqueConstraint constraintName=UC_PROJECTNAME_COL, tableName=project','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-92','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',553,'EXECUTED','8:0e9aed0ec2a008e552352fc51f083daa','addUniqueConstraint constraintName=UC_PROTOCOL_GROUPNAME_COL, tableName=protocol_group','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-93','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',554,'EXECUTED','8:e3d4cf5ab973eb2237fc849f2b57b6b2','addUniqueConstraint constraintName=UC_READ_TYPENAME_COL, tableName=read_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-94','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',555,'EXECUTED','8:e0c3b089507419847b3d595975d1348d','addUniqueConstraint constraintName=UC_READ_TYPESHORT_NAME_COL, tableName=read_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-95','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',556,'EXECUTED','8:d427b1cfc330c83c9e56ab2e1ae78fbb','addUniqueConstraint constraintName=UC_ROLEAUTHORITY_COL, tableName=role','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-96','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',557,'EXECUTED','8:c675fe0c837bdb7afe90e34fc5227904','addUniqueConstraint constraintName=UC_ROLE_GROUPNAME_COL, tableName=role_group','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-97','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',558,'EXECUTED','8:1af1c028da938591dcfebbe65a7d7360','addUniqueConstraint constraintName=UC_SEQUENCING_PLATFORMNAME_COL, tableName=sequencing_platform','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-98','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',559,'EXECUTED','8:904d80804b6d5288d8412c4bdb047ba9','addUniqueConstraint constraintName=UC_SEXNAME_COL, tableName=sex','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1580764544352-99','danyingshao (generated)','changelog.groovy','2020-11-23 14:57:55',560,'EXECUTED','8:531a0563f5fd61c86873b8e8974d385c','addUniqueConstraint constraintName=UC_TARGET_TYPENAME_COL, tableName=target_type','',NULL,'3.6.1',NULL,NULL,'6161474548'),('1605124372433-47','danyingshao (generated)','updateProtocol.groovy','2020-11-23 15:00:05',701,'EXECUTED','8:5631275be27676c0ee55775d37e34ae2','addColumn tableName=protocol','',NULL,'3.6.1',NULL,NULL,'6161604875'),('1605124372433-48','danyingshao (generated)','updateProtocol.groovy','2020-11-23 15:00:05',702,'EXECUTED','8:35803be9f73d7494c658a3c86d1c3822','addUniqueConstraint constraintName=UKccd9281ba8d5bd121364d7b5eb3c, tableName=protocol_item_types','',NULL,'3.6.1',NULL,NULL,'6161604875'),('1605124372433-49','danyingshao (generated)','updateProtocol.groovy','2020-11-23 15:00:05',703,'EXECUTED','8:a708347a35cd96a5f30568c48daf015f','dropUniqueConstraint constraintName=unique_protocol_id, tableName=protocol_item_types','',NULL,'3.6.1',NULL,NULL,'6161604875'),('1608661967757-48','danyingshao (generated)','assay_description.groovy','2021-01-04 14:48:14',704,'EXECUTED','8:b884834e1be285d02bc35022bf6b32ce','addColumn tableName=assay','',NULL,'3.6.1',NULL,NULL,'9789694312'),('1611490736543-48','danyingshao (generated)','run_name.groovy','2021-02-02 09:51:21',705,'EXECUTED','8:a8edf75f2f91f2bf788c777e02aafd6d','addColumn tableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'2277480526'),('1611490736543-49','danyingshao (generated)','run_name.groovy','2021-02-02 09:51:21',706,'EXECUTED','8:593e3fe060ab12db9f486b9c3ba71de9','addUniqueConstraint constraintName=UC_SEQUENCE_RUNRUN_NAME_COL, tableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'2277480526'),('1611490736543-50','danyingshao (generated)','run_name.groovy','2021-02-02 09:51:21',707,'EXECUTED','8:a8622ddaa69ac2fcea75274371f81001','dropColumn columnName=run_num_alias, tableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'2277480526'),('1611662015663-49','danyingshao (generated)','delete_run_num.groovy','2021-02-02 10:13:52',708,'EXECUTED','8:a3884608cad1ac7606a15a9cd2bea1ec','dropColumn columnName=run_num, tableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'2278832345'),('1611863993806-49','danyingshao (generated)','add_feature.groovy','2021-02-02 10:13:52',709,'EXECUTED','8:d0789430a937720fa7bbb2d54f46a03f','createTable tableName=reference_feature','',NULL,'3.6.1',NULL,NULL,'2278832345'),('1611863993806-50','danyingshao (generated)','add_feature.groovy','2021-02-02 10:13:52',710,'EXECUTED','8:b6af337a168b7bc11be9d5ba739d3214','addForeignKeyConstraint baseTableName=reference_feature, constraintName=FKcy4t95j1gcwtgimf4oxyyjm46, referencedTableName=genome','',NULL,'3.6.1',NULL,NULL,'2278832345'),('1617641836355-49','danyingshao (generated)','item_active.groovy','2021-04-07 12:33:50',711,'EXECUTED','8:9540c46d28f7270d4b3aa41e9ba3f7ac','addColumn tableName=item','',NULL,'3.6.1',NULL,NULL,'7813230314'),('1618278662989-49','danyingshao (generated)','growth_media_species.groovy','2021-04-12 22:00:50',712,'EXECUTED','8:9613628854804bb037c73ec34899a5f1','dropForeignKeyConstraint baseTableName=growth_media, constraintName=FK_d67vskwe5phnwub9cqiokalkx','',NULL,'3.6.1',NULL,NULL,'8279250373'),('1618278662989-50','danyingshao (generated)','growth_media_species.groovy','2021-04-12 22:00:50',713,'EXECUTED','8:678cd40bdb39eda07ef9d935605f5a1f','dropColumn columnName=species_id, tableName=growth_media','',NULL,'3.6.1',NULL,NULL,'8279250373'),('1620671544566-1','danyingshao (generated)','update_item.groovy','2021-05-10 14:46:44',714,'EXECUTED','8:cbd2584fa7dcff98c9c1df5a1f5ac112','addNotNullConstraint columnName=barcode, tableName=item','',NULL,'3.6.1',NULL,NULL,'0672403914'),('1620671544566-2','danyingshao (generated)','update_item.groovy','2021-05-10 14:46:44',715,'EXECUTED','8:1068284d5c2e1d7868193a6953748ab2','addNotNullConstraint columnName=name, tableName=item','',NULL,'3.6.1',NULL,NULL,'0672403914');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` tinyint(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,0,NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ab_host`
--

DROP TABLE IF EXISTS `ab_host`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ab_host` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902654` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ab_host`
--

LOCK TABLES `ab_host` WRITE;
/*!40000 ALTER TABLE `ab_host` DISABLE KEYS */;
/*!40000 ALTER TABLE `ab_host` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `city` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `line1` varchar(255) NOT NULL,
  `line2` varchar(255) DEFAULT NULL,
  `postal_code` varchar(10) NOT NULL,
  `state` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `align_type`
--

DROP TABLE IF EXISTS `align_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `align_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `short_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902659` (`name`),
  UNIQUE KEY `short_name_uniq_1470678902660` (`short_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `align_type`
--

LOCK TABLES `align_type` WRITE;
/*!40000 ALTER TABLE `align_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `align_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aligner`
--

DROP TABLE IF EXISTS `aligner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aligner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `aligner_version` varchar(10) DEFAULT NULL,
  `software` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_software` (`aligner_version`,`software`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aligner`
--

LOCK TABLES `aligner` WRITE;
/*!40000 ALTER TABLE `aligner` DISABLE KEYS */;
/*!40000 ALTER TABLE `aligner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `analysis`
--

DROP TABLE IF EXISTS `analysis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `analysis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `alignment_id` bigint(20) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `datasets` longtext DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `parameters` longtext DEFAULT NULL,
  `statistics` longtext DEFAULT NULL,
  `step_id` varchar(255) DEFAULT NULL,
  `tool` varchar(255) NOT NULL,
  `date` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `step` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jegylnmnp65k1q1698nu5sjjw` (`alignment_id`),
  KEY `FK_iw1m6hhffn4kgjwh96ejux11m` (`user_id`),
  CONSTRAINT `FK_iw1m6hhffn4kgjwh96ejux11m` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_jegylnmnp65k1q1698nu5sjjw` FOREIGN KEY (`alignment_id`) REFERENCES `sequence_alignment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `analysis`
--

LOCK TABLES `analysis` WRITE;
/*!40000 ALTER TABLE `analysis` DISABLE KEYS */;
/*!40000 ALTER TABLE `analysis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `antibody`
--

DROP TABLE IF EXISTS `antibody`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `antibody` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `ab_host_id` bigint(20) DEFAULT NULL,
  `catalog_number` varchar(255) DEFAULT NULL,
  `clonal` varchar(255) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `concentration` float DEFAULT NULL,
  `default_target_id` bigint(20) DEFAULT NULL,
  `external_id` varchar(255) DEFAULT NULL,
  `ig_type_id` bigint(20) DEFAULT NULL,
  `immunogene` varchar(255) DEFAULT NULL,
  `inventory_id` varchar(255) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `lot_number` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_54ep9wcwdrl5kkuq231173s2f` (`item_id`),
  KEY `FK_5bpi6ah0csjh4g0n8ln93wx3h` (`ig_type_id`),
  KEY `FK_m6sxsi7lepp2alm9d8832uv26` (`default_target_id`),
  KEY `FK_qjuxd91ddd77fufu1em86n2gb` (`ab_host_id`),
  KEY `FK_rhc520eoim7txw9d7kqpb90fw` (`company_id`),
  CONSTRAINT `FK_54ep9wcwdrl5kkuq231173s2f` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_5bpi6ah0csjh4g0n8ln93wx3h` FOREIGN KEY (`ig_type_id`) REFERENCES `ig_type` (`id`),
  CONSTRAINT `FK_m6sxsi7lepp2alm9d8832uv26` FOREIGN KEY (`default_target_id`) REFERENCES `target` (`id`),
  CONSTRAINT `FK_qjuxd91ddd77fufu1em86n2gb` FOREIGN KEY (`ab_host_id`) REFERENCES `ab_host` (`id`),
  CONSTRAINT `FK_rhc520eoim7txw9d7kqpb90fw` FOREIGN KEY (`company_id`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `antibody`
--

LOCK TABLES `antibody` WRITE;
/*!40000 ALTER TABLE `antibody` DISABLE KEYS */;
/*!40000 ALTER TABLE `antibody` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assay`
--

DROP TABLE IF EXISTS `assay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902672` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assay`
--

LOCK TABLES `assay` WRITE;
/*!40000 ALTER TABLE `assay` DISABLE KEYS */;
INSERT INTO `assay` VALUES (1,0,'ChIP-seq','Chromatin Immunoprecipitation');
/*!40000 ALTER TABLE `assay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch_cell_sources`
--

DROP TABLE IF EXISTS `batch_cell_sources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `batch_cell_sources` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `batch_id` bigint(20) NOT NULL,
  `cell_source_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dxfv34tuthnspoi2l6g7oiw4p` (`batch_id`),
  KEY `FK_q2lpcxg9qdtvvx0qcqhn6vhvi` (`cell_source_id`),
  CONSTRAINT `FK_dxfv34tuthnspoi2l6g7oiw4p` FOREIGN KEY (`batch_id`) REFERENCES `cell_source_batch` (`id`),
  CONSTRAINT `FK_q2lpcxg9qdtvvx0qcqhn6vhvi` FOREIGN KEY (`cell_source_id`) REFERENCES `cell_source` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_cell_sources`
--

LOCK TABLES `batch_cell_sources` WRITE;
/*!40000 ALTER TABLE `batch_cell_sources` DISABLE KEYS */;
/*!40000 ALTER TABLE `batch_cell_sources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cell_source`
--

DROP TABLE IF EXISTS `cell_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cell_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `age` varchar(255) DEFAULT NULL,
  `biological_source_id` varchar(255) DEFAULT NULL,
  `histology_id` bigint(20) DEFAULT NULL,
  `inventory_id` bigint(20) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `prep_user_id` bigint(20) DEFAULT NULL,
  `provider_lab_id` bigint(20) DEFAULT NULL,
  `provider_user_id` bigint(20) DEFAULT NULL,
  `sex_id` bigint(20) DEFAULT NULL,
  `strain_id` bigint(20) NOT NULL,
  `tissue_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_318alqytpqfjhixa0xumta27` (`provider_lab_id`),
  KEY `FK_3j4empi718wbs6weggsvx0vxf` (`sex_id`),
  KEY `FK_71fd478uakc4ikgvf7jkqrbic` (`inventory_id`),
  KEY `FK_7i3q0e2js5j13ybv0fpn6wsdj` (`strain_id`),
  KEY `FK_abm2plajs5iecdn4xw32iiq98` (`histology_id`),
  KEY `FK_fvxkssmfdikktj0688ybb3tbh` (`tissue_id`),
  KEY `FK_js2v7lu898c0p8ortg5xgxb0q` (`provider_user_id`),
  KEY `FK_mrpb1l0o1f7urfo78pf1dn2sl` (`item_id`),
  KEY `FK_osoanky7172r4r5skdfatpr2n` (`prep_user_id`),
  CONSTRAINT `FK_318alqytpqfjhixa0xumta27` FOREIGN KEY (`provider_lab_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK_3j4empi718wbs6weggsvx0vxf` FOREIGN KEY (`sex_id`) REFERENCES `sex` (`id`),
  CONSTRAINT `FK_71fd478uakc4ikgvf7jkqrbic` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`),
  CONSTRAINT `FK_7i3q0e2js5j13ybv0fpn6wsdj` FOREIGN KEY (`strain_id`) REFERENCES `strain` (`id`),
  CONSTRAINT `FK_abm2plajs5iecdn4xw32iiq98` FOREIGN KEY (`histology_id`) REFERENCES `histology` (`id`),
  CONSTRAINT `FK_fvxkssmfdikktj0688ybb3tbh` FOREIGN KEY (`tissue_id`) REFERENCES `tissue` (`id`),
  CONSTRAINT `FK_js2v7lu898c0p8ortg5xgxb0q` FOREIGN KEY (`provider_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_mrpb1l0o1f7urfo78pf1dn2sl` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_osoanky7172r4r5skdfatpr2n` FOREIGN KEY (`prep_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cell_source`
--

LOCK TABLES `cell_source` WRITE;
/*!40000 ALTER TABLE `cell_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `cell_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cell_source_batch`
--

DROP TABLE IF EXISTS `cell_source_batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cell_source_batch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7gfjxyn93jlkww2lllmwvxwvq` (`user_id`),
  CONSTRAINT `FK_7gfjxyn93jlkww2lllmwvxwvq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cell_source_batch`
--

LOCK TABLES `cell_source_batch` WRITE;
/*!40000 ALTER TABLE `cell_source_batch` DISABLE KEYS */;
/*!40000 ALTER TABLE `cell_source_batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cell_source_treatment`
--

DROP TABLE IF EXISTS `cell_source_treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cell_source_treatment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` longtext DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902686` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cell_source_treatment`
--

LOCK TABLES `cell_source_treatment` WRITE;
/*!40000 ALTER TABLE `cell_source_treatment` DISABLE KEYS */;
/*!40000 ALTER TABLE `cell_source_treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chores`
--

DROP TABLE IF EXISTS `chores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` longtext DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902687` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chores`
--

LOCK TABLES `chores` WRITE;
/*!40000 ALTER TABLE `chores` DISABLE KEYS */;
INSERT INTO `chores` VALUES (1,440,'RunsInQueue',NULL),(2,236,'PriorRunFolder',NULL),(3,1,'QC_SETTINGS','[{\"key\":\"totalReads\",\"name\":\"Total Reads\",\"numFormat\":\"###,###,###\",\"min\":100000.0,\"max\":2.0E8,\"reference_min\":\"requestedTags\",\"reference_min_ratio\":0.1},{\"key\":\"adapterDimerPct\",\"name\":\"Adapter Dimers\",\"numFormat\":\"#0.##%\",\"min\":0.0,\"max\":0.1},{\"key\":\"mappedPct\",\"name\":\"Mapped Reads\",\"numFormat\":\"#0.##%\",\"min\":0.5},{\"key\":\"uniquelyMappedPct\",\"name\":\"Uniquely Mapped\",\"numFormat\":\"#0.##%\",\"min\":0.4},{\"key\":\"deduplicatedPct\",\"name\":\"Deduplicated\",\"numFormat\":\"#0.##%\",\"min\":0.3},{\"key\":\"duplicationLevel\",\"name\":\"Duplication Level\",\"numFormat\":\"#0.##%\",\"max\":0.7}]'),(5,1,'PurgeAlignmentsConfig',NULL),(6,4,'YEAST_QC_SETTINGS','[{\"group\":\"Tap Tag\",\"key\":\"detectedTapTag\",\"name\":\"Detected\"},{\"group\":\"Tap Tag\",\"key\":\"detectedTagCount\",\"name\":\"Detected Count\"},{\"group\":\"Tap Tag\",\"key\":\"otherTapTag\",\"name\":\"Other\"},{\"group\":\"Tap Tag\",\"key\":\"otherTagCount\",\"name\":\"Other Count\"},{\"key\":\"dedupUniquelyMappedReads\",\"name\":\"Deduplicated\",\"numFormat\":\"###,###,###\"},{\"key\":\"mappedPct\",\"name\":\"Mappability\",\"numFormat\":\"#0.##\"},{\"key\":\"adapterDimerPct\",\"name\":\"Adapter Dimer\",\"numFormat\":\"#0.##\"},{\"key\":\"duplicationLevel\",\"name\":\"Duplication Level\",\"numFormat\":\"#0.##\"},{\"key\":\"multiGPS\",\"name\":\"multiGPS\"},{\"key\":\"sigPeakPairs\",\"name\":\"Significant PeakPairs\",\"numFormat\":\"###,###,###\"},{\"key\":\"memER\",\"name\":\"Motif Consensus\"},{\"key\":\"roc\",\"name\":\"Motif ROC\"},{\"key\":\"stamp\",\"name\":\"Motif Match\"},{\"key\":\"enrichedSegments\",\"name\":\"Enriched Segments\"},{\"key\":\"nucleosomeEnrichment\",\"name\":\"Nucleosome Enrichment\"},{\"key\":\"go\",\"name\":\"Stress Gene\"},{\"key\":\"polIILevel\",\"name\":\"Pol II\"},{\"key\":\"exprsLevel\",\"name\":\"Expression\"}]'),(7,0,'YEAST_QC_SETTINGS_META','[\"group\", \"key\", \"name\", \"numFormat\"]'),(8,0,'QC_SETTINGS_META','[\"key\", \"name\", \"numFormat\", \"min\", \"max\", \"reference_min\", \"reference_min_ratio\", \"reference_max\", \"reference_max_ratio\"]'),(9,0,'DYNAMIC_ANALYSIS_STEPS','[\"multiGPS\",\"significanceTester\",\"stamp\",\"nucleosomeEnrichmentProfiler\",\"pointEnrichmentTester\",\"tableLookup\",\"memER\", \"tapTagID\"]'),(10,2,'DECISION_TREE_YEAST_QC','{\"colors\":{\"Done; failed\":\"#d9534f\",\"Done; low exprs\":\"#f0ad4e\",\"Done; stress gene\":\"#f0ad4e\",\"Done; success\":\"#5cb85c\",\"Re-sequence\":\"#d9534f\",\"other\":\"Gray\",\"re-ChIP\":\"#d9534f\"},\"treeData\":{\"children\":[{\"children\":[{\"flag\":\"yes\",\"name\":\"Done; success\"},{\"children\":[{\"flag\":\"yes\",\"marks\":[\"go\"],\"name\":\"Done; stress gene\"},{\"children\":[{\"flag\":\"yes\",\"marks\":[\"polIILevel\",\"exprsLevel\"],\"name\":\"Done; low exprs\"},{\"flag\":\"no\",\"marks\":[\"stamp\",\"multiGPS\",\"sigPeakPairs\",\"nucleosomeEnrichment\"],\"name\":\"Done; failed\"}],\"conditions\":[{\"name\":\"pol II\",\"op\":\"<\",\"p\":\"polIILevel\",\"v\":0.25},{\"name\":\"Expression\",\"op\":\"<\",\"p\":\"exprsLevel\",\"v\":0.25}],\"flag\":\"no\",\"logic\":\"AND\"}],\"conditions\":[{\"name\":\"Stress Gene\",\"p\":\"go\"}],\"flag\":\"no\"}],\"conditions\":[{\"name\":\"Motif Match\",\"op\":\"=\",\"p\":\"stamp\",\"v\":\"Yes\"},{\"op\":\">\",\"p\":\"multiGPS\",\"v\":100},{\"op\":\">\",\"p\":\"sigPeakPairs\",\"v\":200},{\"name\":\"Nucleosome Enrichment\",\"op\":\">\",\"p\":\"nucleosomeEnrichment\",\"v\":1.35},{\"name\":\"Enriched Segments\",\"op\":\"\",\"p\":\"enrichedSegments\"}],\"flag\":\"yes\",\"logic\":\"OR\"},{\"children\":[{\"color\":\"\",\"flag\":\"yes\",\"name\":\"Re-sequence\"},{\"children\":[{\"flag\":\"yes\",\"marks\":[\"go\"],\"name\":\"Done; stress gene\"},{\"children\":[{\"flag\":\"yes\",\"marks\":[\"polIILevel\",\"exprsLevel\"],\"name\":\"Done; low exprs\"},{\"flag\":\"no\",\"marks\":[\"dedupUniquelyMappedReads\",\"mappedPct\",\"adapterDimerPct\",\"duplicationLevel\"],\"name\":\"re-ChIP\"}],\"conditions\":[{\"name\":\"pol II\",\"op\":\"<\",\"p\":\"polIILevel\",\"v\":0.25},{\"name\":\"Expression\",\"op\":\"<\",\"p\":\"exprsLevel\",\"v\":0.25}],\"flag\":\"no\",\"logic\":\"AND\"}],\"conditions\":[{\"name\":\"Stress Gene\",\"p\":\"go\"}],\"flag\":\"no\"}],\"conditions\":[{\"name\":\"Mapped\",\"op\":\">\",\"p\":\"mappedPct\",\"v\":0.5},{\"name\":\"Adapter Dimer\",\"op\":\"<\",\"p\":\"adapterDimerPct\",\"v\":0.15},{\"name\":\"Duplication Level\",\"op\":\"<\",\"p\":\"duplicationLevel\",\"v\":0.7}],\"flag\":\"no\",\"logic\":\"AND\"}],\"conditions\":[{\"name\":\"Deduplicated Reads\",\"op\":\">\",\"p\":\"dedupUniquelyMappedReads\",\"v\":200000}]}}');
/*!40000 ALTER TABLE `chores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chrom_sequence`
--

DROP TABLE IF EXISTS `chrom_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chrom_sequence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `chromosome_id` bigint(20) NOT NULL,
  `length` int(11) NOT NULL,
  `sequence` longtext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lese17egs9gkqmyudn2wh5il2` (`chromosome_id`),
  CONSTRAINT `FK_lese17egs9gkqmyudn2wh5il2` FOREIGN KEY (`chromosome_id`) REFERENCES `chromosome` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chrom_sequence`
--

LOCK TABLES `chrom_sequence` WRITE;
/*!40000 ALTER TABLE `chrom_sequence` DISABLE KEYS */;
/*!40000 ALTER TABLE `chrom_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chromosome`
--

DROP TABLE IF EXISTS `chromosome`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chromosome` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `genome_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902689` (`name`),
  KEY `FK_qtx5yloqyq7hc1efournhys5u` (`genome_id`),
  CONSTRAINT `FK_qtx5yloqyq7hc1efournhys5u` FOREIGN KEY (`genome_id`) REFERENCES `genome` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chromosome`
--

LOCK TABLES `chromosome` WRITE;
/*!40000 ALTER TABLE `chromosome` DISABLE KEYS */;
/*!40000 ALTER TABLE `chromosome` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control_sample`
--

DROP TABLE IF EXISTS `control_sample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `control_sample` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `control_sample_id` bigint(20) NOT NULL,
  `sample_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sample_id` (`control_sample_id`,`sample_id`),
  KEY `FK_n5xni9ruiej5e6p37qkxc0ha8` (`sample_id`),
  KEY `FK_pmyy4lhr2rnht0taqgm4rpfq3` (`control_sample_id`),
  CONSTRAINT `FK_n5xni9ruiej5e6p37qkxc0ha8` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`),
  CONSTRAINT `FK_pmyy4lhr2rnht0taqgm4rpfq3` FOREIGN KEY (`control_sample_id`) REFERENCES `sample` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control_sample`
--

LOCK TABLES `control_sample` WRITE;
/*!40000 ALTER TABLE `control_sample` DISABLE KEYS */;
/*!40000 ALTER TABLE `control_sample` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `definition`
--

DROP TABLE IF EXISTS `definition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `definition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `content` varchar(5000) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902690` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `definition`
--

LOCK TABLES `definition` WRITE;
/*!40000 ALTER TABLE `definition` DISABLE KEYS */;
/*!40000 ALTER TABLE `definition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funding`
--

DROP TABLE IF EXISTS `funding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funding` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funding`
--

LOCK TABLES `funding` WRITE;
/*!40000 ALTER TABLE `funding` DISABLE KEYS */;
/*!40000 ALTER TABLE `funding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genome`
--

DROP TABLE IF EXISTS `genome`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genome` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `species_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8qyh94lw1ucpnn89d4dl4e5dr` (`species_id`),
  CONSTRAINT `FK_8qyh94lw1ucpnn89d4dl4e5dr` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genome`
--

LOCK TABLES `genome` WRITE;
/*!40000 ALTER TABLE `genome` DISABLE KEYS */;
INSERT INTO `genome` VALUES (1,0,'sacCer3',1,NULL,'http://downloads.yeastgenome.org/sequence/S288C_reference/genome_releases/'),(2,1,'sg7',1,NULL,'http://sgd-archive.yeastgenome.org/sequence/S288C_reference/genome_releases/S288C_reference_genome_R55-1-1_20061110.tgz'),(3,0,'mm9',2,NULL,'http://hgdownload.cse.ucsc.edu/downloads.html'),(4,0,'mm10',2,NULL,'http://hgdownload.cse.ucsc.edu/downloads.html'),(5,0,'dm3',3,NULL,'http://hgdownload.cse.ucsc.edu/downloads.html'),(6,0,'hg19',4,NULL,'http://hgdownload.cse.ucsc.edu/downloads.html'),(7,0,'hg18',4,NULL,'http://hgdownload.cse.ucsc.edu/downloads.html'),(8,0,'sp8',8,NULL,'http://downloads.yeastgenome.org/sequence/S288C_reference/genome_releases/'),(10,1,'hg38',4,NULL,'http://hgdownload.cse.ucsc.edu/downloads.html'),(13,1,'ec2',10,NULL,'http://www.genome.wisc.edu/sequencing.htm'),(14,0,'tair10',21,NULL,'ftp://ftp.arabidopsis.org/home/tair/Sequences/whole_chromosomes/'),(15,0,'bosTau7',15,NULL,'http://hgdownload.cse.ucsc.edu/downloads.html#cow'),(17,1,'ce10',17,NULL,'http://hgdownload.cse.ucsc.edu/downloads.html'),(18,1,'ycp50',10,NULL,NULL),(22,0,'rn5',25,NULL,'http://hgdownload.cse.ucsc.edu/downloads.html'),(26,0,'dm5',3,NULL,NULL),(27,0,'pf12',41,NULL,NULL),(29,0,'pf24',41,NULL,NULL),(32,0,'osaIRGSP',44,NULL,NULL),(33,0,'sacCer3_cegr',1,NULL,NULL),(34,1,'A22',46,NULL,'http://www.candidagenome.org/download/sequence/C_albicans_SC5314/Assembly22/current/'),(44,1,'ASM294v2',8,NULL,NULL);
/*!40000 ALTER TABLE `genome` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `growth_media`
--

DROP TABLE IF EXISTS `growth_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `growth_media` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902693` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `growth_media`
--

LOCK TABLES `growth_media` WRITE;
/*!40000 ALTER TABLE `growth_media` DISABLE KEYS */;
INSERT INTO `growth_media` VALUES (128,0,'YPD','Y'),(129,0,'LB','Y'),(130,0,'DMEM','Y');
/*!40000 ALTER TABLE `growth_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `histology`
--

DROP TABLE IF EXISTS `histology`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `histology` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902694` (`name`),
  KEY `FK_jrq8s9u4ujdq8durk95ilfebo` (`parent_id`),
  CONSTRAINT `FK_jrq8s9u4ujdq8durk95ilfebo` FOREIGN KEY (`parent_id`) REFERENCES `histology` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `histology`
--

LOCK TABLES `histology` WRITE;
/*!40000 ALTER TABLE `histology` DISABLE KEYS */;
/*!40000 ALTER TABLE `histology` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `action` varchar(255) NOT NULL,
  `date_created` datetime NOT NULL,
  `object_id` int(11) NOT NULL,
  `object_type` varchar(255) NOT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fej8h317q2acuy144kav1oyxc` (`project_id`),
  KEY `FK_fuutexvtx28fs971iq0kbfbmp` (`user_id`),
  CONSTRAINT `FK_fej8h317q2acuy144kav1oyxc` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_fuutexvtx28fs971iq0kbfbmp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ig_type`
--

DROP TABLE IF EXISTS `ig_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ig_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902696` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ig_type`
--

LOCK TABLES `ig_type` WRITE;
/*!40000 ALTER TABLE `ig_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `ig_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_received` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `receiving_user_id` bigint(20) DEFAULT NULL,
  `source_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mnah8ybq73nyl126do8agmk08` (`receiving_user_id`),
  CONSTRAINT `FK_mnah8ybq73nyl126do8agmk08` FOREIGN KEY (`receiving_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `invoice_num` varchar(255) DEFAULT NULL,
  `service_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `barcode` varchar(255) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `customized_fields` longtext DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) NOT NULL DEFAULT 'GOOD',
  `last_updated` datetime DEFAULT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `barcode_uniq_1473096571187` (`barcode`),
  KEY `FK_br92r4wqm19mvpcyhxn5lg7m7` (`user_id`),
  KEY `FK_ccldfsomwnlcfqys42su71de3` (`parent_id`),
  KEY `FK_qxnbu16tlqfmub9pgfj3h2e41` (`type_id`),
  KEY `FK_7rlelwn0ya4pik9fcudk0vv9r` (`project_id`),
  CONSTRAINT `FK_7rlelwn0ya4pik9fcudk0vv9r` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_br92r4wqm19mvpcyhxn5lg7m7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ccldfsomwnlcfqys42su71de3` FOREIGN KEY (`parent_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_qxnbu16tlqfmub9pgfj3h2e41` FOREIGN KEY (`type_id`) REFERENCES `item_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_antibody`
--

DROP TABLE IF EXISTS `item_antibody`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_antibody` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `antibody_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_item_id` (`antibody_id`,`item_id`),
  KEY `FK_f68ojp3d1waxqqeoojl8j6nf5` (`item_id`),
  KEY `FK_jftvriocthroiuhj650ixyt1c` (`antibody_id`),
  CONSTRAINT `FK_f68ojp3d1waxqqeoojl8j6nf5` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_jftvriocthroiuhj650ixyt1c` FOREIGN KEY (`antibody_id`) REFERENCES `antibody` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_antibody`
--

LOCK TABLES `item_antibody` WRITE;
/*!40000 ALTER TABLE `item_antibody` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_antibody` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_sequence_indices`
--

DROP TABLE IF EXISTS `item_sequence_indices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_sequence_indices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `index_id` bigint(20) NOT NULL,
  `index_in_set` int(11) DEFAULT NULL,
  `item_id` bigint(20) NOT NULL,
  `set_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8ip2o04e3gn7ulqvtxwpk2ycr` (`item_id`),
  KEY `FK_fudqc8t4xmiaqk7ins2e4dsxs` (`index_id`),
  CONSTRAINT `FK_8ip2o04e3gn7ulqvtxwpk2ycr` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_fudqc8t4xmiaqk7ins2e4dsxs` FOREIGN KEY (`index_id`) REFERENCES `sequence_index` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_sequence_indices`
--

LOCK TABLES `item_sequence_indices` WRITE;
/*!40000 ALTER TABLE `item_sequence_indices` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_sequence_indices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_type`
--

DROP TABLE IF EXISTS `item_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `fields` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902700` (`name`),
  KEY `FK_94ymay19alxgutio16kgowv93` (`category_id`),
  CONSTRAINT `FK_94ymay19alxgutio16kgowv93` FOREIGN KEY (`category_id`) REFERENCES `item_type_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_type`
--

LOCK TABLES `item_type` WRITE;
/*!40000 ALTER TABLE `item_type` DISABLE KEYS */;
INSERT INTO `item_type` VALUES (1,0,NULL,'Antibody',1),(2,0,'Vendor,Catalog Number,Lot Number','1X PBS',11),(3,0,'Date Created','10mM Tris-HCl with 150mM NaCl',11),(4,0,'Date Created','1M Tris-HCl pH 8.0',11),(5,0,'Date Created','5M NaCl',11),(6,0,'Date Created','10X CPI',11),(7,0,'Date Created','Farnham Lysis Buffer',11),(8,0,'Date Created','RIPA Nuclear Lysis Buffer',11),(9,0,'Date Created','2M KCl',11),(10,0,'Date Created','20% NP-40 (IPEGAL)',11),(11,0,'Date Created','25% Triton X-100',11),(12,0,'Date Created','10% NaDeoxycholate',11),(13,0,'Date Created','20% SDS',11),(14,0,'Date Created','NaCl 250 Buffer',11),(15,0,'Date Created','LiCl 250 Buffer',11),(16,0,'Date Created','1M HEPES-KOH pH 7.5',11),(17,0,'Date Created','ChIP Elution Buffer',11),(18,0,'Date Created','500mM EDTA',11),(19,0,'Vendor,Catalog Number,Lot Number','10X NEBuffer2',11),(20,0,'Date Created','End Repair Buffer',11),(21,0,'Date Created','Ligation Buffer',11),(22,0,'Vendor,Catalog Number,Lot Number','16% Formaldehyde - Single Use',2),(23,0,'Vendor,Catalog Number,Lot Number','Glycine',2),(24,0,'Vendor,Catalog Number,Lot Number','Tris-Cl Salt',2),(25,0,'Vendor,Catalog Number,Lot Number','NaCl Salt',2),(26,0,'Vendor,Catalog Number,Lot Number','CPI tablet',2),(27,0,'Vendor,Catalog Number,Lot Number','KCl Salt',2),(28,0,'Vendor,Catalog Number,Lot Number','100% NP-40',2),(29,0,'Vendor,Catalog Number,Lot Number','100% Triton X-100',2),(30,0,'Vendor,Catalog Number,Lot Number','NaDeoxycholate Salt',2),(31,0,'Vendor,Catalog Number,Lot Number','Sodium Dodecyl Sulfate (SDS)',2),(32,0,'Vendor,Catalog Number,Lot Number','HEPES Potassium Salt',2),(33,0,'Vendor,Catalog Number,Lot Number','EDTA Salt',2),(34,0,'Vendor,Catalog Number,Lot Number','dATP Solution (100 mM)',2),(35,0,'Vendor,Catalog Number,Lot Number','dCTP Solution (100 mM)',2),(36,0,'Vendor,Catalog Number,Lot Number','dGTP Solution (100 mM)',2),(37,0,'Vendor,Catalog Number,Lot Number','dTTP Solution (100 mM)',2),(38,0,'Vendor,Catalog Number,Lot Number','Klenow Fragment (3’ -> 5’ exo-)',3),(39,0,'Vendor,Catalog Number,Lot Number','T4 Polynucleotide Kinase (PNK)',3),(40,0,'Vendor,Catalog Number,Lot Number','T4 DNA Ligase',3),(41,0,'Vendor,Catalog Number,Lot Number','Phusion Hot Start II DNA Polymerase',3),(42,0,'Vendor,Catalog Number,Lot Number','Proteinase K',3),(43,0,'Vendor,Catalog Number','Diagenode Pico',5),(44,0,'Vendor,Catalog Number,Lot Number','Dynabeads Protein G',6),(45,0,'Vendor,Catalog Number,Lot Number','Dynabeads Protein A',6),(46,0,'Vendor,Catalog Number,Lot Number','AMPure Beads',6),(47,0,'Vendor,Catalog Number,Lot Number','Agarose',6),(48,0,'Date Created','TruSeq Fork Adapter',10),(49,0,'Date Created','Cell Pellet',7),(50,0,'Date Created','Crosslinked Cell Pellet',8),(51,0,'Date Created','Sonicated Chromatin',8),(52,0,'Date Created','2.5M Glycine',11),(53,0,'Date Created','DNA Library',8),(54,0,'Date Created','1M LiCl',11),(55,0,'Date Created','10mM Tris-HCl pH 8.0',11),(56,0,'Date Created','1N KOH',11),(57,0,'Vendor,Catalog Number,Lot Number','5X Phusion HF Buffer',11),(58,0,'Vendor,Catalog Number,Lot Number','LiCl Salt',2),(59,0,'Vendor,Catalog Number,Lot Number','KOH Salt',2),(60,0,'Date Created','3mM dNTPs',2),(61,0,'Date Created','3mM dATP',2),(62,0,'Date Created','25mM dNTPs',2),(63,0,'Vendor,Catalog Number,Lot Number','T4 DNA Polymerase',3);
/*!40000 ALTER TABLE `item_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_type_category`
--

DROP TABLE IF EXISTS `item_type_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_type_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `super_category` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1472352933587` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_type_category`
--

LOCK TABLES `item_type_category` WRITE;
/*!40000 ALTER TABLE `item_type_category` DISABLE KEYS */;
INSERT INTO `item_type_category` VALUES (1,0,'Antibody','ANTIBODY'),(2,0,'Chemical','OTHER'),(3,0,'Enzyme','OTHER'),(5,0,'Equipment','OTHER'),(6,0,'General Supply','OTHER'),(7,0,'Cell Stock','TRACED_SAMPLE'),(8,0,'Biosample','TRACED_SAMPLE'),(9,0,'Sample Pool','SAMPLE_POOL'),(10,0,'Oligos','OTHER'),(11,0,'Buffer','OTHER'),(12,0,'Media','OTHER'),(13,0,'Plasmid','OTHER');
/*!40000 ALTER TABLE `item_type_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `class` varchar(255) NOT NULL,
  `billing_contact_id` bigint(20) DEFAULT NULL,
  `pi_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902701` (`name`),
  KEY `FK_57byxcy430qbl2gl7liup0py1` (`parent_id`),
  KEY `FK_6w4r8gx2jdvy193esigbpxxx6` (`billing_contact_id`),
  KEY `FK_l070gahmlj4g2sqbm72btw64e` (`address_id`),
  KEY `FK_shncwjk67uss09ivcrpvlv8xg` (`pi_id`),
  CONSTRAINT `FK_57byxcy430qbl2gl7liup0py1` FOREIGN KEY (`parent_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK_6w4r8gx2jdvy193esigbpxxx6` FOREIGN KEY (`billing_contact_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_l070gahmlj4g2sqbm72btw64e` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_shncwjk67uss09ivcrpvlv8xg` FOREIGN KEY (`pi_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pipeline`
--

DROP TABLE IF EXISTS `pipeline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pipeline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `pipeline_version` varchar(255) NOT NULL DEFAULT '0.0.0',
  `steps` longtext NOT NULL,
  `workflow_id` varchar(255) NOT NULL DEFAULT 'N/A',
  `workflow_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `workflow_id` (`workflow_id`),
  UNIQUE KEY `unique_name` (`pipeline_version`,`name`),
  UNIQUE KEY `workflow_id_uniq_1472352933591` (`workflow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pipeline`
--

LOCK TABLES `pipeline` WRITE;
/*!40000 ALTER TABLE `pipeline` DISABLE KEYS */;
INSERT INTO `pipeline` VALUES (1,2,'paired','paired_002 workflow','002','[[\"input_dataset_r1_output_stats\",\"fastqRead1\"],[\"input_dataset_r2_output_stats\",\"fastqRead2\"],[\"fastqc_output_stats\",\"fastqc\"],[\"fastqc_output_stats2\",\"fastqc\"],[\"mark_duplicates_bam_output_stats\",\"markDuplicates\"],[\"samtool_filter2_output_stats\",\"samtoolFilter\"],[\"pe_histogram_output_stats\",\"peHistogram\"],[\"bam_to_scidx_output_stats\",\"bamToScidx\"],[\"genetrack_output_stats\",\"genetrack\"],[\"bedtools_intersectbed_output_stats\",\"bedtoolsIntersect\"],[\"cwpair2_output_stats\",\"cwpair2\"],[\"extract_genomic_dna_output_stats\",\"extractGenomicDNA\"],[\"extract_genomic_dna_output_stats2\",\"extractGenomicDNA\"],[\"repeatmasker_wrapper_output_stats\",\"repeatMasker\"],[\"repeatmasker_wrapper_output_stats2\",\"repeatMasker\"],[\"meme_meme_output_stats\",\"meme\"],[\"meme_fimo_output_stats\",\"fimo\"],[\"extract_genomic_dna_output_stats3\",\"extractGenomicDNA\"],[\"fasta_nucleotide_color_plot_output_stats\",\"fourColorPlot\"],[\"tag_pileup_frequency_output_stats\",\"tagPileup\"]]','f2db41e1fa331b3e','https://raw.githubusercontent.com/CEGRcode/pegr-galaxy_tools/main/workflows/paired_002.ga'),(2,2,'single','single_002 workflow','002','[[\"input_dataset_r1_output_stats\",\"fastqRead1\"],[\"fastqc_output_stats\",\"fastqc\"],[\"bwa_mem_output_stats_single\",\"bwaMem\"],[\"samtool_filter2_output_stats\",\"samtoolFilter\"],[\"bam_to_scidx_output_stats\",\"bamToScidx\"],[\"genetrack_output_stats\",\"genetrack\"],[\"bedtools_intersectbed_output_stats\",\"bedtoolsIntersect\"],[\"cwpair2_output_stats\",\"cwpair2\"],[\"extract_genomic_dna_output_stats\",\"extractGenomicDNA\"],[\"extract_genomic_dna_output_stats2\",\"extractGenomicDNA\"],[\"repeatmasker_wrapper_output_stats\",\"repeatMasker\"],[\"repeatmasker_wrapper_output_stats2\",\"repeatMasker\"],[\"meme_meme_output_stats\",\"meme\"],[\"meme_fimo_output_stats\",\"fimo\"],[\"extract_genomic_dna_output_stats3\",\"extractGenomicDNA\"],[\"fasta_nucleotide_color_plot_output_stats\",\"fourColorPlot\"],[\"tag_pileup_frequency_output_stats\",\"tagPileup\"]]','a799d38679e985db','https://raw.githubusercontent.com/CEGRcode/pegr-galaxy_tools/main/workflows/single_002.ga');
/*!40000 ALTER TABLE `pipeline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pool_samples`
--

DROP TABLE IF EXISTS `pool_samples`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pool_samples` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pool_id` bigint(20) NOT NULL,
  `sample_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sample_id` (`pool_id`,`sample_id`),
  KEY `FK_ad5h1h1ndrx123chi4kvxkijg` (`sample_id`),
  KEY `FK_ek00bovrrt47tc8bpgn5g2gdg` (`pool_id`),
  CONSTRAINT `FK_ad5h1h1ndrx123chi4kvxkijg` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`),
  CONSTRAINT `FK_ek00bovrrt47tc8bpgn5g2gdg` FOREIGN KEY (`pool_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pool_samples`
--

LOCK TABLES `pool_samples` WRITE;
/*!40000 ALTER TABLE `pool_samples` DISABLE KEYS */;
/*!40000 ALTER TABLE `pool_samples` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `notes` longtext DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902705` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_bags`
--

DROP TABLE IF EXISTS `project_bags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_bags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bag_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_project_id` (`bag_id`,`project_id`),
  KEY `FK_fc80yrft9ah9jrxcf1comtkag` (`project_id`),
  KEY `FK_t52g06gh1mit551a04c7r9u11` (`bag_id`),
  CONSTRAINT `FK_fc80yrft9ah9jrxcf1comtkag` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_t52g06gh1mit551a04c7r9u11` FOREIGN KEY (`bag_id`) REFERENCES `protocol_instance_bag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_bags`
--

LOCK TABLES `project_bags` WRITE;
/*!40000 ALTER TABLE `project_bags` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_bags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_funding`
--

DROP TABLE IF EXISTS `project_funding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_funding` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `funding_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_project_id` (`funding_id`,`project_id`),
  KEY `FK_6e9lgt30h05twwt72veu2hrt1` (`funding_id`),
  KEY `FK_clw28uyswyfeglm9v1kdx0pmy` (`project_id`),
  CONSTRAINT `FK_6e9lgt30h05twwt72veu2hrt1` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`id`),
  CONSTRAINT `FK_clw28uyswyfeglm9v1kdx0pmy` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_funding`
--

LOCK TABLES `project_funding` WRITE;
/*!40000 ALTER TABLE `project_funding` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_funding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_samples`
--

DROP TABLE IF EXISTS `project_samples`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_samples` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(20) NOT NULL,
  `sample_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_project_id` (`sample_id`,`project_id`),
  KEY `FK_bcuquxjs68mn5vyq7d0yphn8p` (`project_id`),
  KEY `FK_qfu2n2hwoukibpvvx51eftp9y` (`sample_id`),
  CONSTRAINT `FK_bcuquxjs68mn5vyq7d0yphn8p` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_qfu2n2hwoukibpvvx51eftp9y` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_samples`
--

LOCK TABLES `project_samples` WRITE;
/*!40000 ALTER TABLE `project_samples` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_samples` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_user`
--

DROP TABLE IF EXISTS `project_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `project_role` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_project_id` (`user_id`,`project_id`),
  KEY `FK_d6kfrxuqknbxrlxhwmn66a3kg` (`user_id`),
  KEY `FK_ptwhmsh2vocln8sffhyvr2ohm` (`project_id`),
  CONSTRAINT `FK_d6kfrxuqknbxrlxhwmn66a3kg` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ptwhmsh2vocln8sffhyvr2ohm` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_user`
--

LOCK TABLES `project_user` WRITE;
/*!40000 ALTER TABLE `project_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `protocol`
--

DROP TABLE IF EXISTS `protocol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `protocol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `add_antibody` bit(1) DEFAULT NULL,
  `add_index` bit(1) DEFAULT NULL,
  `assay_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `protocol_version` varchar(10) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`protocol_version`,`name`),
  KEY `FK_f2u9282jbe9eiy0fggmrvrt16` (`assay_id`),
  KEY `FK_mpt4h4wrqjngorh2706rvsqk2` (`user_id`),
  CONSTRAINT `FK_f2u9282jbe9eiy0fggmrvrt16` FOREIGN KEY (`assay_id`) REFERENCES `assay` (`id`),
  CONSTRAINT `FK_mpt4h4wrqjngorh2706rvsqk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol`
--

LOCK TABLES `protocol` WRITE;
/*!40000 ALTER TABLE `protocol` DISABLE KEYS */;
INSERT INTO `protocol` VALUES (1,0,'\0','\0',NULL,'Makes 2.5M Glycine for quenching formaldehyde','2.5M Glycine Recipe','1',NULL,'Y',1,'protocols/2.5M_Glycine_Recipe.pdf',NULL,NULL),(2,0,'\0','\0',NULL,'Makes 1M LiCl stock solution','1M LiCl Recipe','1',NULL,'Y',1,'protocols/1M_LiCl_Buffer_Recipe.pdf',NULL,NULL),(3,0,'\0','\0',NULL,'Makes 5M NaCl stock solution','5M NaCl Recipe','1',NULL,'Y',1,'protocols/5M_NaCl_Buffer_Recipe.pdf',NULL,NULL),(4,0,'\0','\0',NULL,'Makes 20% NP-40 stock solution','20% NP-40 Recipe','1',NULL,'Y',1,'protocols/10Per_NP40_Recipe.pdf',NULL,NULL),(5,0,'\0','\0',NULL,'Makes 20% SDS stock solution','20% SDS Recipe','1',NULL,'Y',1,'protocols/20Per_SDS_Recipe.pdf',NULL,NULL),(6,0,'\0','\0',NULL,'Makes 25% Triton X-100 stock solution','25% Triton X-100 Recipe','1',NULL,'Y',1,'protocols/25Per_Triton_X100_Recipe.pdf',NULL,NULL),(7,0,'\0','\0',NULL,'Makes 10% NaDeoxycholate stock solution','10% NaDeoxycholate Recipe','1',NULL,'Y',1,'protocols/10Per_Sodium_Deoxycholate_Recipe.pdf',NULL,NULL),(8,0,'\0','\0',NULL,'Makes 1M Tris-HCl buffer stock solution','1M Tris-HCl Recipe','1',NULL,'Y',1,'protocols/1M_TrisHCl_Buffer_Recipe.pdf',NULL,NULL),(9,0,'\0','\0',NULL,'Makes 2M KCl stock solution','2M KCl Recipe','1',NULL,'Y',1,'protocols/2M_KCl_Buffer_Recipe.pdf',NULL,NULL),(10,0,'\0','\0',NULL,'Makes 500mM EDTA stock solution','500mM EDTA Recipe','1',NULL,'Y',1,'protocols/500mM_EDTA_Buffer_Recipe.pdf',NULL,NULL),(11,0,'\0','\0',NULL,'Makes 10mM Tris-HCl buffer stock solution','10mM Tris-HCl Recipe','1',NULL,'Y',1,'protocols/10mM_TrisHCl_Buffer_Recipe.pdf',NULL,NULL),(12,0,'\0','\0',1,'Makes ChIP Elution Buffer','ChIP Elution Buffer Recipe','1',NULL,'Y',1,'protocols/ChIP_Elution_Buffer_Recipe.pdf',NULL,NULL),(13,0,'\0','\0',NULL,'Makes Farnham Lysis Buffer','Farnham Lysis Buffer Recipe','1',NULL,'Y',1,'protocols/Farnham_Lysis_Buffer_Recipe.pdf',NULL,NULL),(14,0,'\0','\0',NULL,'Makes RIPA Nuclear Lysis Buffer','RIPA Nuclear Lysis Buffer','1',NULL,'Y',1,'protocols/RIPA_Nuclear_Lysis_Buffer_Recipe.pdf',NULL,NULL),(15,0,'\0','\0',NULL,'Makes LiCl 250 Wash Buffer','LiCl 250 Wash Buffer Recipe','1',NULL,'Y',1,'protocols/LiCl250_Wash_Buffer_Recipe.pdf',NULL,NULL),(16,0,'\0','\0',NULL,'Makes 1N KOH stock solution','1N KOH Recipe','1',NULL,'Y',1,'protocols/1N_KOH_Recipe.pdf',NULL,NULL),(17,0,'\0','\0',NULL,'Makes 1M HEPES-KOH stock solution','1M HEPES-KOH Recipe','1',NULL,'Y',1,'protocols/1M_HEPES-KOH_Recipe.pdf',NULL,NULL),(18,0,'\0','\0',NULL,'Makes NaCl 250 Wash Buffer','NaCl 250 Wash Buffer Recipe','1',NULL,'Y',1,'protocols/NaCl250_Wash_Buffer_Recipe.pdf',NULL,NULL),(19,0,'\0','\0',NULL,'Makes 3mM dNTP stock solution','3mM dNTP Stock Recipe','1',NULL,'Y',1,'protocols/3mM_dNTP_Recipe.pdf',NULL,NULL),(20,0,'','\0',1,'Perform ChIP reaction','ChIP Reaction','1',NULL,'Y',1,'protocols/ChIP_Reaction_Protocol.pdf',NULL,NULL),(21,0,'\0','\0',1,'Polish ChIP DNA ends','ChIP DNA End Repair','1',NULL,'Y',1,'protocols/ChIP_DNA_End_Repair.pdf',NULL,NULL),(22,0,'\0','\0',NULL,'Makes 3mM dATP stock solution','3mM dATP Stock Recipe','1',NULL,'Y',1,'protocols/3mM_dATP_Recipe.pdf',NULL,NULL),(23,0,'\0','\0',1,'Add A-overhang to polished ChIP DNA','ChIP DNA A-tailing Reaction','1',NULL,'Y',1,'protocols/ChIP_DNA_A-tailing_Reaction.pdf',NULL,NULL),(24,0,'\0','\0',1,'Add TruSeq fork adapters to A-tailed ChIP DNA','ChIP DNA TruSeq Adapter Ligation','1',NULL,'Y',1,'protocols/ChIP_DNA_TruSeq_Adapter Ligation.pdf',NULL,NULL),(25,0,'\0','\0',1,'Elute and purify ChIP DNA','ChIP DNA Elution and Purification','1',NULL,'Y',1,'protocols/ChIP_DNA_Elution_and_Purification.pdf',NULL,NULL),(26,0,'\0','\0',NULL,'Makes 25mM dNTP stock solution','25mM dNTP Stock Recipe','1',NULL,'Y',1,'protocols/25mM_dNTP_Recipe.pdf',NULL,NULL),(27,0,'\0','',1,'Amplify Illumina libraries','ChIP DNA PCR','1',NULL,'Y',1,'protocols/ChIP_DNA_PCR.pdf',NULL,NULL);
/*!40000 ALTER TABLE `protocol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `protocol_group`
--

DROP TABLE IF EXISTS `protocol_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `protocol_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902708` (`name`),
  KEY `FK_mm7fvjt84dbnbsnsbe9ik36xh` (`user_id`),
  CONSTRAINT `FK_mm7fvjt84dbnbsnsbe9ik36xh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol_group`
--

LOCK TABLES `protocol_group` WRITE;
/*!40000 ALTER TABLE `protocol_group` DISABLE KEYS */;
INSERT INTO `protocol_group` VALUES (2,0,'2021-06-21 18:09:24','ChIP-seq Protocol',NULL);
/*!40000 ALTER TABLE `protocol_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `protocol_group_protocols`
--

DROP TABLE IF EXISTS `protocol_group_protocols`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `protocol_group_protocols` (
  `protocol_id` bigint(20) NOT NULL,
  `protocol_group_id` bigint(20) NOT NULL,
  `protocols_idx` int(11) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK413d0a510ff03d1f19c83af9ae36` (`protocols_idx`,`protocol_group_id`,`protocol_id`),
  KEY `FK_6i86p4hqvh803hqeam63pf0fo` (`protocol_id`),
  KEY `FK_6sjm1imt3f4b8rd492wm9vt7e` (`protocol_group_id`),
  CONSTRAINT `FK_6i86p4hqvh803hqeam63pf0fo` FOREIGN KEY (`protocol_id`) REFERENCES `protocol` (`id`),
  CONSTRAINT `FK_6sjm1imt3f4b8rd492wm9vt7e` FOREIGN KEY (`protocol_group_id`) REFERENCES `protocol_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol_group_protocols`
--

LOCK TABLES `protocol_group_protocols` WRITE;
/*!40000 ALTER TABLE `protocol_group_protocols` DISABLE KEYS */;
INSERT INTO `protocol_group_protocols` VALUES (20,2,0,2),(21,2,1,3),(23,2,2,4),(24,2,3,5),(25,2,4,6),(27,2,5,7);
/*!40000 ALTER TABLE `protocol_group_protocols` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `protocol_instance`
--

DROP TABLE IF EXISTS `protocol_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `protocol_instance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `bag_id` bigint(20) DEFAULT NULL,
  `bag_idx` int(11) NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `protocol_id` bigint(20) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_d4v22o39k3xu8m216w8r1nyx1` (`protocol_id`),
  KEY `FK_j952d9eb9yjue4b2xggu8ehib` (`bag_id`),
  KEY `FK_sgj3vhj59cxyb10md7ybwmtm7` (`user_id`),
  CONSTRAINT `FK_d4v22o39k3xu8m216w8r1nyx1` FOREIGN KEY (`protocol_id`) REFERENCES `protocol` (`id`),
  CONSTRAINT `FK_j952d9eb9yjue4b2xggu8ehib` FOREIGN KEY (`bag_id`) REFERENCES `protocol_instance_bag` (`id`),
  CONSTRAINT `FK_sgj3vhj59cxyb10md7ybwmtm7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol_instance`
--

LOCK TABLES `protocol_instance` WRITE;
/*!40000 ALTER TABLE `protocol_instance` DISABLE KEYS */;
/*!40000 ALTER TABLE `protocol_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `protocol_instance_bag`
--

DROP TABLE IF EXISTS `protocol_instance_bag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `protocol_instance_bag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `protocol_group_id` bigint(20) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r1uxlmgfs4nieck8kad4wo79q` (`protocol_group_id`),
  CONSTRAINT `FK_r1uxlmgfs4nieck8kad4wo79q` FOREIGN KEY (`protocol_group_id`) REFERENCES `protocol_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol_instance_bag`
--

LOCK TABLES `protocol_instance_bag` WRITE;
/*!40000 ALTER TABLE `protocol_instance_bag` DISABLE KEYS */;
/*!40000 ALTER TABLE `protocol_instance_bag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `protocol_instance_items`
--

DROP TABLE IF EXISTS `protocol_instance_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `protocol_instance_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) NOT NULL,
  `protocol_instance_id` bigint(20) NOT NULL,
  `function` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2ehrfoqmnyg7wc3sv0owshi8l` (`item_id`),
  KEY `FK_dmt3slumwfqinb5qicjdvrilr` (`protocol_instance_id`),
  CONSTRAINT `FK_2ehrfoqmnyg7wc3sv0owshi8l` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_dmt3slumwfqinb5qicjdvrilr` FOREIGN KEY (`protocol_instance_id`) REFERENCES `protocol_instance` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol_instance_items`
--

LOCK TABLES `protocol_instance_items` WRITE;
/*!40000 ALTER TABLE `protocol_instance_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `protocol_instance_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `protocol_instance_summary`
--

DROP TABLE IF EXISTS `protocol_instance_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `protocol_instance_summary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `protocol_id` bigint(20) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5v0brjc04upo9ksqnq3bivxni` (`protocol_id`),
  KEY `FK_8idyxguxexanjiixc15ylqewv` (`user_id`),
  CONSTRAINT `FK_5v0brjc04upo9ksqnq3bivxni` FOREIGN KEY (`protocol_id`) REFERENCES `protocol` (`id`),
  CONSTRAINT `FK_8idyxguxexanjiixc15ylqewv` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol_instance_summary`
--

LOCK TABLES `protocol_instance_summary` WRITE;
/*!40000 ALTER TABLE `protocol_instance_summary` DISABLE KEYS */;
/*!40000 ALTER TABLE `protocol_instance_summary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `protocol_item_types`
--

DROP TABLE IF EXISTS `protocol_item_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `protocol_item_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `function` varchar(255) NOT NULL,
  `item_type_id` bigint(20) NOT NULL,
  `protocol_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKccd9281ba8d5bd121364d7b5eb3c` (`function`,`item_type_id`,`protocol_id`),
  KEY `FK_mi8fvx3awmigchnxb1s8keinu` (`item_type_id`),
  KEY `FK_p1p3edd8llir9a0qp24sj5cql` (`protocol_id`),
  CONSTRAINT `FK_mi8fvx3awmigchnxb1s8keinu` FOREIGN KEY (`item_type_id`) REFERENCES `item_type` (`id`),
  CONSTRAINT `FK_p1p3edd8llir9a0qp24sj5cql` FOREIGN KEY (`protocol_id`) REFERENCES `protocol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=367 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol_item_types`
--

LOCK TABLES `protocol_item_types` WRITE;
/*!40000 ALTER TABLE `protocol_item_types` DISABLE KEYS */;
INSERT INTO `protocol_item_types` VALUES (334,'CHILD',51,20),(339,'CHILD',51,21),(346,'CHILD',51,23),(356,'CHILD',53,25),(365,'CHILD',53,27),(286,'END_PRODUCT',4,8),(276,'END_PRODUCT',5,3),(302,'END_PRODUCT',7,13),(307,'END_PRODUCT',8,14),(288,'END_PRODUCT',9,9),(278,'END_PRODUCT',10,4),(282,'END_PRODUCT',11,6),(284,'END_PRODUCT',12,7),(280,'END_PRODUCT',13,5),(323,'END_PRODUCT',14,18),(312,'END_PRODUCT',15,15),(317,'END_PRODUCT',16,17),(297,'END_PRODUCT',17,12),(290,'END_PRODUCT',18,10),(272,'END_PRODUCT',52,1),(274,'END_PRODUCT',54,2),(292,'END_PRODUCT',55,11),(314,'END_PRODUCT',56,16),(328,'END_PRODUCT',60,19),(341,'END_PRODUCT',61,22),(361,'END_PRODUCT',62,26),(333,'PARENT',51,20),(338,'PARENT',51,21),(345,'PARENT',51,23),(355,'PARENT',51,25),(364,'PARENT',53,27),(303,'SHARED',2,14),(291,'SHARED',4,11),(293,'SHARED',4,12),(298,'SHARED',4,13),(308,'SHARED',4,15),(295,'SHARED',5,12),(319,'SHARED',5,18),(299,'SHARED',9,13),(300,'SHARED',10,13),(304,'SHARED',10,14),(310,'SHARED',10,15),(301,'SHARED',11,13),(321,'SHARED',11,18),(305,'SHARED',12,14),(311,'SHARED',12,15),(322,'SHARED',12,18),(296,'SHARED',13,12),(306,'SHARED',13,14),(331,'SHARED',14,20),(350,'SHARED',14,25),(332,'SHARED',15,20),(351,'SHARED',15,25),(318,'SHARED',16,18),(352,'SHARED',17,25),(294,'SHARED',18,12),(320,'SHARED',18,18),(342,'SHARED',19,23),(335,'SHARED',21,21),(347,'SHARED',21,24),(271,'SHARED',23,1),(285,'SHARED',24,8),(275,'SHARED',25,3),(287,'SHARED',27,9),(277,'SHARED',28,4),(281,'SHARED',29,6),(283,'SHARED',30,7),(279,'SHARED',31,5),(315,'SHARED',32,17),(289,'SHARED',33,10),(324,'SHARED',34,19),(340,'SHARED',34,22),(357,'SHARED',34,26),(325,'SHARED',35,19),(358,'SHARED',35,26),(326,'SHARED',36,19),(359,'SHARED',36,26),(327,'SHARED',37,19),(360,'SHARED',37,26),(344,'SHARED',38,23),(337,'SHARED',39,21),(348,'SHARED',40,24),(362,'SHARED',41,27),(353,'SHARED',42,25),(329,'SHARED',45,20),(354,'SHARED',46,25),(349,'SHARED',48,24),(309,'SHARED',54,15),(330,'SHARED',55,20),(316,'SHARED',56,17),(363,'SHARED',57,27),(273,'SHARED',58,2),(313,'SHARED',59,16),(366,'SHARED',60,21),(343,'SHARED',61,23),(336,'SHARED',63,21);
/*!40000 ALTER TABLE `protocol_item_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `read_type`
--

DROP TABLE IF EXISTS `read_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `read_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `short_name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902716` (`name`),
  UNIQUE KEY `short_name_uniq_1470678902716` (`short_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `read_type`
--

LOCK TABLES `read_type` WRITE;
/*!40000 ALTER TABLE `read_type` DISABLE KEYS */;
INSERT INTO `read_type` VALUES (1,0,'Paired End',NULL,'PE'),(2,0,'Single Read',NULL,'SR');
/*!40000 ALTER TABLE `read_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reference_feature`
--

DROP TABLE IF EXISTS `reference_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reference_feature` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `genome_id` bigint(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `filename` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcy4t95j1gcwtgimf4oxyyjm46` (`genome_id`),
  CONSTRAINT `FKcy4t95j1gcwtgimf4oxyyjm46` FOREIGN KEY (`genome_id`) REFERENCES `genome` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference_feature`
--

LOCK TABLES `reference_feature` WRITE;
/*!40000 ALTER TABLE `reference_feature` DISABLE KEYS */;
/*!40000 ALTER TABLE `reference_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `replicate_samples`
--

DROP TABLE IF EXISTS `replicate_samples`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `replicate_samples` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sample_id` bigint(20) NOT NULL,
  `set_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_set_id` (`sample_id`,`set_id`),
  KEY `FK_b7fffn7tv5cj5s4eslmipmujb` (`set_id`),
  KEY `FK_ced3uejqos7cnb2vaupk3qk1h` (`sample_id`),
  CONSTRAINT `FK_b7fffn7tv5cj5s4eslmipmujb` FOREIGN KEY (`set_id`) REFERENCES `replicate_set` (`id`),
  CONSTRAINT `FK_ced3uejqos7cnb2vaupk3qk1h` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replicate_samples`
--

LOCK TABLES `replicate_samples` WRITE;
/*!40000 ALTER TABLE `replicate_samples` DISABLE KEYS */;
/*!40000 ALTER TABLE `replicate_samples` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `replicate_set`
--

DROP TABLE IF EXISTS `replicate_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `replicate_set` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qg0x2qhnutq0rw27iglu1o49u` (`project_id`),
  CONSTRAINT `FK_qg0x2qhnutq0rw27iglu1o49u` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replicate_set`
--

LOCK TABLES `replicate_set` WRITE;
/*!40000 ALTER TABLE `replicate_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `replicate_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_alignments`
--

DROP TABLE IF EXISTS `report_alignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_alignments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alignment_id` bigint(20) NOT NULL,
  `report_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_report_id` (`alignment_id`,`report_id`),
  KEY `FK_invrh9of0ndhq9wetgqupqjuu` (`alignment_id`),
  KEY `FK_jgmi242smigukk2otj453sld8` (`report_id`),
  CONSTRAINT `FK_invrh9of0ndhq9wetgqupqjuu` FOREIGN KEY (`alignment_id`) REFERENCES `sequence_alignment` (`id`),
  CONSTRAINT `FK_jgmi242smigukk2otj453sld8` FOREIGN KEY (`report_id`) REFERENCES `summary_report` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_alignments`
--

LOCK TABLES `report_alignments` WRITE;
/*!40000 ALTER TABLE `report_alignments` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_alignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `authority_uniq_1470678902719` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,0,'ROLE_ADMIN'),(2,0,'ROLE_MEMBER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_group`
--

DROP TABLE IF EXISTS `role_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1479613636890` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_group`
--

LOCK TABLES `role_group` WRITE;
/*!40000 ALTER TABLE `role_group` DISABLE KEYS */;
INSERT INTO `role_group` VALUES (1,0,'Admin'),(8,0,'Member'),(9,0,'Guest');
/*!40000 ALTER TABLE `role_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_group_role`
--

DROP TABLE IF EXISTS `role_group_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_group_role` (
  `role_group_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_group_id`,`role_id`),
  KEY `FK_cqr9g8iosupkylg4fw10paumq` (`role_group_id`),
  KEY `FK_dxc3snhixkg9qn3c46p6hyjli` (`role_id`),
  CONSTRAINT `FK_cqr9g8iosupkylg4fw10paumq` FOREIGN KEY (`role_group_id`) REFERENCES `role_group` (`id`),
  CONSTRAINT `FK_dxc3snhixkg9qn3c46p6hyjli` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_group_role`
--

LOCK TABLES `role_group_role` WRITE;
/*!40000 ALTER TABLE `role_group_role` DISABLE KEYS */;
INSERT INTO `role_group_role` VALUES (1,1),(8,2);
/*!40000 ALTER TABLE `role_group_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `run_stats`
--

DROP TABLE IF EXISTS `run_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `run_stats` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `cluster_num` float DEFAULT NULL,
  `cycles` varchar(255) DEFAULT NULL,
  `library_loaded_fmol` float DEFAULT NULL,
  `library_loaded_pm` float DEFAULT NULL,
  `library_std_dev` float DEFAULT NULL,
  `library_stock` float DEFAULT NULL,
  `library_volume` float DEFAULT NULL,
  `pcr_cycles` int(11) DEFAULT NULL,
  `pct_aligned_to_phix` float DEFAULT NULL,
  `pct_library_std_dev` float DEFAULT NULL,
  `pct_pf` float DEFAULT NULL,
  `pctq30` float DEFAULT NULL,
  `pct_unmatched_indices` float DEFAULT NULL,
  `phixloaded` float DEFAULT NULL,
  `q_pcr_conc` float DEFAULT NULL,
  `qidx` float DEFAULT NULL,
  `qubit_conc` float DEFAULT NULL,
  `read_pf` float DEFAULT NULL,
  `seq_ctrl` varchar(255) DEFAULT NULL,
  `sr_or_pe` varchar(255) DEFAULT NULL,
  `total_reads` float DEFAULT NULL,
  `unmatched_indices` float DEFAULT NULL,
  `library_pool_archive_id` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `q_pcr_date` datetime DEFAULT NULL,
  `technician_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fot189hkkf2166h0g0pm3fpjp` (`technician_id`),
  CONSTRAINT `FK_fot189hkkf2166h0g0pm3fpjp` FOREIGN KEY (`technician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `run_stats`
--

LOCK TABLES `run_stats` WRITE;
/*!40000 ALTER TABLE `run_stats` DISABLE KEYS */;
/*!40000 ALTER TABLE `run_stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sample`
--

DROP TABLE IF EXISTS `sample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sample` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `antibody_id` bigint(20) DEFAULT NULL,
  `antibody_notes` varchar(255) DEFAULT NULL,
  `assay_id` bigint(20) DEFAULT NULL,
  `cell_number` double DEFAULT NULL,
  `cell_source_id` bigint(20) DEFAULT NULL,
  `chromosome_amount` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `growth_media_id` bigint(20) DEFAULT NULL,
  `invoice_id` bigint(20) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `note` longtext DEFAULT NULL,
  `prtcl_inst_summary_id` bigint(20) DEFAULT NULL,
  `publication_reference` varchar(255) DEFAULT NULL,
  `requested_genomes` varchar(255) DEFAULT NULL,
  `requested_tag_number` double DEFAULT NULL,
  `send_data_to_id` bigint(20) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `source_id` varchar(255) DEFAULT NULL,
  `spike_in_cell_source_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `target_id` bigint(20) DEFAULT NULL,
  `volume` double DEFAULT NULL,
  `natural_id` varchar(255) DEFAULT NULL,
  `recommend` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4lb993olybjsa6bqlbt90as3c` (`antibody_id`),
  KEY `FK_9l7trxp5onscu1yk6fcvenjq6` (`item_id`),
  KEY `FK_a1ro7uobsoyc87a51ssw7n5sw` (`cell_source_id`),
  KEY `FK_bcdouyjpgn9hqcd947o1f28jq` (`prtcl_inst_summary_id`),
  KEY `FK_cfxg067kqgsk55rjibh2pbnso` (`target_id`),
  KEY `FK_dhxymxy1936tgj756fntpg6xw` (`assay_id`),
  KEY `FK_iab90bxgihv46lqg7ib51kmwn` (`spike_in_cell_source_id`),
  KEY `FK_ppsd5p8eqaveimasoymlfgcny` (`growth_media_id`),
  KEY `FK_psm0kim6hte7fy0577fwsbdi` (`send_data_to_id`),
  KEY `FK_tqw4ka9uvmywhb4gw0ib7nxy9` (`invoice_id`),
  CONSTRAINT `FK_4lb993olybjsa6bqlbt90as3c` FOREIGN KEY (`antibody_id`) REFERENCES `antibody` (`id`),
  CONSTRAINT `FK_9l7trxp5onscu1yk6fcvenjq6` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_a1ro7uobsoyc87a51ssw7n5sw` FOREIGN KEY (`cell_source_id`) REFERENCES `cell_source` (`id`),
  CONSTRAINT `FK_bcdouyjpgn9hqcd947o1f28jq` FOREIGN KEY (`prtcl_inst_summary_id`) REFERENCES `protocol_instance_summary` (`id`),
  CONSTRAINT `FK_cfxg067kqgsk55rjibh2pbnso` FOREIGN KEY (`target_id`) REFERENCES `target` (`id`),
  CONSTRAINT `FK_dhxymxy1936tgj756fntpg6xw` FOREIGN KEY (`assay_id`) REFERENCES `assay` (`id`),
  CONSTRAINT `FK_iab90bxgihv46lqg7ib51kmwn` FOREIGN KEY (`spike_in_cell_source_id`) REFERENCES `cell_source` (`id`),
  CONSTRAINT `FK_ppsd5p8eqaveimasoymlfgcny` FOREIGN KEY (`growth_media_id`) REFERENCES `growth_media` (`id`),
  CONSTRAINT `FK_psm0kim6hte7fy0577fwsbdi` FOREIGN KEY (`send_data_to_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_tqw4ka9uvmywhb4gw0ib7nxy9` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample`
--

LOCK TABLES `sample` WRITE;
/*!40000 ALTER TABLE `sample` DISABLE KEYS */;
/*!40000 ALTER TABLE `sample` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sample_in_run`
--

DROP TABLE IF EXISTS `sample_in_run`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sample_in_run` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `params` varchar(255) DEFAULT NULL,
  `pool` varchar(255) DEFAULT NULL,
  `pool_date` datetime DEFAULT NULL,
  `run_id` bigint(20) NOT NULL,
  `sample_id` bigint(20) NOT NULL,
  `volume_to_pool` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sample_id` (`run_id`,`sample_id`),
  KEY `FK_993bhxmuyjlul88wourwo5eyw` (`run_id`),
  KEY `FK_fsayjgiqhlt7cctq8n1escwvk` (`sample_id`),
  CONSTRAINT `FK_993bhxmuyjlul88wourwo5eyw` FOREIGN KEY (`run_id`) REFERENCES `sequence_run` (`id`),
  CONSTRAINT `FK_fsayjgiqhlt7cctq8n1escwvk` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample_in_run`
--

LOCK TABLES `sample_in_run` WRITE;
/*!40000 ALTER TABLE `sample_in_run` DISABLE KEYS */;
/*!40000 ALTER TABLE `sample_in_run` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sample_sequence_indices`
--

DROP TABLE IF EXISTS `sample_sequence_indices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sample_sequence_indices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `index_id` bigint(20) NOT NULL,
  `index_in_set` int(11) DEFAULT NULL,
  `sample_id` bigint(20) NOT NULL,
  `set_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_flne19kx2tprlhpbxh9a3xk8r` (`index_id`),
  KEY `FK_qh0akbm1a3893iwxbwqps9xmn` (`sample_id`),
  CONSTRAINT `FK_flne19kx2tprlhpbxh9a3xk8r` FOREIGN KEY (`index_id`) REFERENCES `sequence_index` (`id`),
  CONSTRAINT `FK_qh0akbm1a3893iwxbwqps9xmn` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample_sequence_indices`
--

LOCK TABLES `sample_sequence_indices` WRITE;
/*!40000 ALTER TABLE `sample_sequence_indices` DISABLE KEYS */;
/*!40000 ALTER TABLE `sample_sequence_indices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sample_treatments`
--

DROP TABLE IF EXISTS `sample_treatments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sample_treatments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sample_id` bigint(20) NOT NULL,
  `treatment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sample_id` (`treatment_id`,`sample_id`),
  KEY `FK_cldug6dnvxfcnd89wfrgg9e7i` (`sample_id`),
  KEY `FK_ehfppi0f0g2lojkytdeaij3lp` (`treatment_id`),
  CONSTRAINT `FK_cldug6dnvxfcnd89wfrgg9e7i` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`),
  CONSTRAINT `FK_ehfppi0f0g2lojkytdeaij3lp` FOREIGN KEY (`treatment_id`) REFERENCES `cell_source_treatment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample_treatments`
--

LOCK TABLES `sample_treatments` WRITE;
/*!40000 ALTER TABLE `sample_treatments` DISABLE KEYS */;
/*!40000 ALTER TABLE `sample_treatments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence_alignment`
--

DROP TABLE IF EXISTS `sequence_alignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence_alignment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `align_type_id` bigint(20) DEFAULT NULL,
  `aligner_id` bigint(20) DEFAULT NULL,
  `avg_insert_size` float DEFAULT NULL,
  `bam_file` varchar(1000) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `dedup_uniquely_mapped_reads` bigint(20) DEFAULT NULL,
  `genome_id` bigint(20) NOT NULL,
  `genome_coverage` float DEFAULT NULL,
  `is_preferred` bit(1) NOT NULL DEFAULT b'1',
  `mapped_reads` bigint(20) DEFAULT NULL,
  `params` varchar(2000) DEFAULT NULL,
  `pe_histogram` varchar(1000) DEFAULT NULL,
  `read_db_id` int(11) DEFAULT NULL,
  `seq_duplication_level` float DEFAULT NULL,
  `sequencing_experiment_id` bigint(20) NOT NULL,
  `std_dev_insert_size` float DEFAULT NULL,
  `uniquely_mapped_reads` bigint(20) DEFAULT NULL,
  `history_id` varchar(255) NOT NULL,
  `pipeline_id` bigint(20) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `history_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_history_id` (`pipeline_id`,`genome_id`,`sequencing_experiment_id`,`history_id`),
  KEY `FK_5tdpednm424bwlyaygxb91cj8` (`genome_id`),
  KEY `FK_8gcis40hwme6s7ywn4jikodd2` (`align_type_id`),
  KEY `FK_fee47iwmejna7h55jp8dpkowd` (`aligner_id`),
  KEY `FK_js0whth80fyna834brwbbo8v0` (`sequencing_experiment_id`),
  KEY `FK_ep9ilxtqeiwutdrjx5hp1xmax` (`pipeline_id`),
  CONSTRAINT `FK_5tdpednm424bwlyaygxb91cj8` FOREIGN KEY (`genome_id`) REFERENCES `genome` (`id`),
  CONSTRAINT `FK_8gcis40hwme6s7ywn4jikodd2` FOREIGN KEY (`align_type_id`) REFERENCES `align_type` (`id`),
  CONSTRAINT `FK_ep9ilxtqeiwutdrjx5hp1xmax` FOREIGN KEY (`pipeline_id`) REFERENCES `pipeline` (`id`),
  CONSTRAINT `FK_fee47iwmejna7h55jp8dpkowd` FOREIGN KEY (`aligner_id`) REFERENCES `aligner` (`id`),
  CONSTRAINT `FK_js0whth80fyna834brwbbo8v0` FOREIGN KEY (`sequencing_experiment_id`) REFERENCES `sequencing_experiment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence_alignment`
--

LOCK TABLES `sequence_alignment` WRITE;
/*!40000 ALTER TABLE `sequence_alignment` DISABLE KEYS */;
/*!40000 ALTER TABLE `sequence_alignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence_index`
--

DROP TABLE IF EXISTS `sequence_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence_index` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `index_id` varchar(255) NOT NULL,
  `index_version` varchar(10) NOT NULL,
  `oligo` varchar(255) DEFAULT NULL,
  `sequence` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1063 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence_index`
--

LOCK TABLES `sequence_index` WRITE;
/*!40000 ALTER TABLE `sequence_index` DISABLE KEYS */;
INSERT INTO `sequence_index` VALUES (1,0,'1','Single',NULL,'ATCACGCGATGT','Y'),(2,0,'2','Single',NULL,'CGATGTTTAGGC','Y'),(3,0,'3','Single',NULL,'TTAGGCTGACCA','Y'),(4,0,'4','Single',NULL,'TGACCAACAGTG','Y'),(5,0,'5','Single',NULL,'ACAGTGGCCAAT','Y'),(6,0,'6','Single',NULL,'GCCAATCAGATC','Y'),(7,0,'7','Single',NULL,'CAGATCACTTGA','Y'),(8,0,'8','Single',NULL,'ACTTGAGATCAG','Y'),(9,0,'9','Single',NULL,'GATCAGTAGCTT','Y'),(10,0,'10','Single',NULL,'TAGCTTGGCTAC','Y'),(11,0,'11','Single',NULL,'GGCTACCTTGTA','Y'),(12,0,'12','Single',NULL,'CTTGTAAAGACT','Y'),(13,0,'13','Single',NULL,'AAGACTCCACGC','Y'),(14,0,'14','Single',NULL,'CCACGCGATATA','Y'),(15,0,'15','Single',NULL,'GATATATACAGC','Y'),(16,0,'16','Single',NULL,'TACAGCAAACAT','Y'),(17,0,'17','Single',NULL,'TTCGTTGTGGTA','Y'),(18,0,'18','Single',NULL,'TGGTTGACCGAG','Y'),(19,0,'19','Single',NULL,'TCCCAACTTCGC','Y'),(20,0,'20','Single',NULL,'CAACCTTTACAG','Y'),(21,0,'21','Single',NULL,'AATGGCCTCTAT','Y'),(22,0,'22','Single',NULL,'GGTGTAAGGTAA','Y'),(23,0,'23','Single',NULL,'ATCGTACAACTC','Y'),(24,0,'24','Single',NULL,'GGTACTCACTTA','Y'),(25,0,'25','Single',NULL,'TCTGGTGACATT','Y'),(26,0,'26','Single',NULL,'GCAATAGCTGCT','Y'),(27,0,'27','Single',NULL,'ACCCTGTACCCT','Y'),(28,0,'28','Single',NULL,'CGTTCAATCCAA','Y'),(29,0,'29','Single',NULL,'TACGGGTCTTTA','Y'),(30,0,'30','Single',NULL,'GTATACAGAAGG','Y'),(31,0,'31','Single',NULL,'GCTCGCTACTTC','Y'),(32,0,'32','Single',NULL,'TCGAAATCGCGG','Y'),(33,0,'33','Single',NULL,'CCTTCGTCCAAC','Y'),(34,0,'34','Single',NULL,'CGCGTAACTGTA','Y'),(35,0,'35','Single',NULL,'GGAACTAGCCGT','Y'),(36,0,'36','Single',NULL,'GTGGGATGTTTC','Y'),(37,0,'37','Single',NULL,'CAATACTCTGAC','Y'),(38,0,'38','Single',NULL,'ATCAGTGAGGCC','Y'),(39,0,'39','Single',NULL,'GTGTGTGTCAGG','Y'),(40,0,'40','Single',NULL,'TTCGTGCCTGCT','Y'),(41,0,'41','Single',NULL,'GAAGTTGGAAGT','Y'),(42,0,'42','Single',NULL,'ACATGATCGTTC','Y'),(43,0,'43','Single',NULL,'TGAATCCCACAG','Y'),(44,0,'44','Single',NULL,'TGGCACCGATTA','Y'),(45,0,'45','Single',NULL,'CCAGATGATCGT','Y'),(46,0,'46','Single',NULL,'GCCGATTCGGAA','Y'),(47,0,'47','Single',NULL,'AGGTAGTCCTCA','Y'),(48,0,'48','Single',NULL,'CTTCAGTTCGCC','Y'),(49,0,'49','Single',NULL,'GGAGGTTATCCG','Y'),(50,0,'50','Single',NULL,'AGAGTCCTGAGC','Y'),(51,0,'51','Single',NULL,'TCAAGGCATCGG','Y'),(52,0,'52','Single',NULL,'CGGTTAGTGGTT','Y'),(53,0,'53','Single',NULL,'CTCGCTTCACTT','Y'),(54,0,'54','Single',NULL,'CGATTTCTCAAG','Y'),(55,0,'55','Single',NULL,'CGTGAGGCATTG','Y'),(56,0,'56','Single',NULL,'AACCGTTCCAGA','Y'),(57,0,'57','Single',NULL,'TGTAATTGTCGC','Y'),(58,0,'58','Single',NULL,'TTCTCACCTTTC','Y'),(59,0,'59','Single',NULL,'CTTACGAGGCAT','Y'),(60,0,'60','Single',NULL,'GGTTTGGCCATA','Y'),(61,0,'61','Single',NULL,'TCAAACCGTGTT','Y'),(62,0,'62','Single',NULL,'AGGCATCTTACG','Y'),(63,0,'63','Single',NULL,'GTGCCATAACCA','Y'),(64,0,'64','Single',NULL,'CAAGCTCTGATT','Y'),(65,0,'65','Single',NULL,'ATGGACCGAACC','Y'),(66,0,'66','Single',NULL,'GAGCTTCTGGGA','Y'),(67,0,'67','Single',NULL,'GCTTCGGTAGAT','Y'),(68,0,'68','Single',NULL,'ATAACCCGCCAA','Y'),(69,0,'69','Single',NULL,'GCTTGACGTATT','Y'),(70,0,'70','Single',NULL,'TGGGCAACCTGA','Y'),(71,0,'71','Single',NULL,'AGCTGTTGTTTG','Y'),(72,0,'72','Single',NULL,'TTCCAGCTAATG','Y'),(73,0,'73','Single',NULL,'GGAGTCGGTCTA','Y'),(74,0,'74','Single',NULL,'CTCTACCTCTAC','Y'),(75,0,'75','Single',NULL,'GCTAAACCGTCA','Y'),(76,0,'76','Single',NULL,'GGCGAATCTTGG','Y'),(77,0,'77','Single',NULL,'GAGAGCTCTACG','Y'),(78,0,'78','Single',NULL,'TGTGAGCACGGT','Y'),(79,0,'79','Single',NULL,'TTCGCTAACAGG','Y'),(80,0,'80','Single',NULL,'CGATCCGTATTA','Y'),(81,0,'81','Single',NULL,'TCGCATGAAGTC','Y'),(82,0,'82','Single',NULL,'AACCATCGGGTG','Y'),(83,0,'83','Single',NULL,'TGGACTTACCAG','Y'),(84,0,'84','Single',NULL,'TTGGTTTCGAGT','Y'),(85,0,'85','Single',NULL,'GCTAATTACGCT','Y'),(86,0,'86','Single',NULL,'ATCTCTGGCATA','Y'),(87,0,'87','Single',NULL,'TGCTCTAGTGGA','Y'),(88,0,'88','Single',NULL,'GTGGAACCACGT','Y'),(89,0,'89','Single',NULL,'ACGATGCGACCA','Y'),(90,0,'90','Single',NULL,'CCTGTATCCCGT','Y'),(91,0,'91','Single',NULL,'CAAGCATGCCTA','Y'),(92,0,'92','Single',NULL,'TGTCTGCGGAAG','Y'),(93,0,'93','Single',NULL,'ATCCAACGTTCA','Y'),(94,0,'94','Single',NULL,'AAGCGTGTCGTT','Y'),(95,0,'95','Single',NULL,'GTGTCTACATTG','Y'),(96,0,'96','Single',NULL,'TGACTGAAGGCC','Y'),(592,0,'iA','Duo',NULL,'CTCTCTAT','Y'),(593,0,'iB','Duo',NULL,'TATCCTCT','Y'),(594,0,'iC','Duo',NULL,'GTAAGGAG','Y'),(595,0,'iD','Duo',NULL,'ACTGCATA','Y'),(596,0,'iE','Duo',NULL,'AAGGAGTA','Y'),(597,0,'iF','Duo',NULL,'CTAAGCCT','Y'),(598,0,'iG','Duo',NULL,'CGTCTAAT','Y'),(599,0,'iH','Duo',NULL,'TCTCTCCG','Y'),(600,0,'iI','Duo',NULL,'TCGACTAG','Y'),(601,0,'iJ','Duo',NULL,'TTCTAGCT','Y'),(602,0,'iK','Duo',NULL,'CCTAGAGT','Y'),(603,0,'iL','Duo',NULL,'GCGTAAGA','Y'),(604,0,'iM','Duo',NULL,'CTATTAAG','Y'),(605,0,'iN','Duo',NULL,'AAGGCTAT','Y'),(606,0,'iO','Duo',NULL,'GAGCCTTA','Y'),(607,0,'iP','Duo',NULL,'TTATGCGA','Y'),(608,0,'i01','Duo',NULL,'TCGCCTTA','Y'),(609,0,'i02','Duo',NULL,'CTAGTACG','Y'),(610,0,'i03','Duo',NULL,'TTCTGCCT','Y'),(611,0,'i04','Duo',NULL,'GCTCAGGA','Y'),(612,0,'i05','Duo',NULL,'AGGAGTCC','Y'),(613,0,'i06','Duo',NULL,'CATGCCTA','Y'),(614,0,'i07','Duo',NULL,'GTAGAGAG','Y'),(615,0,'i08','Duo',NULL,'CAGCCTCG','Y'),(616,0,'i09','Duo',NULL,'TGCCTCTT','Y'),(617,0,'i10','Duo',NULL,'TCCTCTAC','Y'),(618,0,'i11','Duo',NULL,'TCATGAGC','Y'),(619,0,'i12','Duo',NULL,'CCTGAGAT','Y'),(620,0,'i13','Duo',NULL,'TAGCGAGT','Y'),(621,0,'i14','Duo',NULL,'GTAGCTCC','Y'),(622,0,'i15','Duo',NULL,'TACTACGC','Y'),(623,0,'i16','Duo',NULL,'AGGCTCCG','Y'),(624,0,'i17','Duo',NULL,'GCAGCGTA','Y'),(625,0,'i18','Duo',NULL,'CTGCGCAT','Y'),(626,0,'i19','Duo',NULL,'GAGCGCTA','Y'),(627,0,'i20','Duo',NULL,'CGCTCAGT','Y'),(628,0,'i21','Duo',NULL,'GTCTTAGG','Y'),(629,0,'i22','Duo',NULL,'ACTGATCG','Y'),(630,0,'i23','Duo',NULL,'TAGCTGCA','Y'),(631,0,'i24','Duo',NULL,'GACGTCGA','Y');
/*!40000 ALTER TABLE `sequence_index` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence_run`
--

DROP TABLE IF EXISTS `sequence_run`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence_run` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `directory_name` varchar(255) DEFAULT NULL,
  `fc_id` varchar(255) DEFAULT NULL,
  `lane` int(11) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `platform_id` bigint(20) NOT NULL,
  `pool_item_id` bigint(20) DEFAULT NULL,
  `run_stats_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `run_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_SEQUENCE_RUNRUN_NAME_COL` (`run_name`),
  KEY `FK_5cc4mivg8mvxoipvbrl09geqj` (`run_stats_id`),
  KEY `FK_gerfjuiu4n7m0iukeud2wrux7` (`user_id`),
  KEY `FK_l1ofj1csp7k439e52l32soay9` (`platform_id`),
  KEY `FK_nb6cvqg7y8ruphnbfber6qjsb` (`pool_item_id`),
  CONSTRAINT `FK_5cc4mivg8mvxoipvbrl09geqj` FOREIGN KEY (`run_stats_id`) REFERENCES `run_stats` (`id`),
  CONSTRAINT `FK_gerfjuiu4n7m0iukeud2wrux7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_l1ofj1csp7k439e52l32soay9` FOREIGN KEY (`platform_id`) REFERENCES `sequencing_platform` (`id`),
  CONSTRAINT `FK_nb6cvqg7y8ruphnbfber6qjsb` FOREIGN KEY (`pool_item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence_run`
--

LOCK TABLES `sequence_run` WRITE;
/*!40000 ALTER TABLE `sequence_run` DISABLE KEYS */;
/*!40000 ALTER TABLE `sequence_run` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequencing_cohort`
--

DROP TABLE IF EXISTS `sequencing_cohort`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequencing_cohort` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `report_id` bigint(20) DEFAULT NULL,
  `run_id` bigint(20) NOT NULL,
  `images` longtext DEFAULT NULL,
  `notes` longtext DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_724ffp5jxmqpde8496in7kggu` (`run_id`),
  KEY `FK_8ae2rfhbp4pr7xk6kqpa38818` (`project_id`),
  KEY `FK_ptpiv5b92sigq6bm3l854sgj8` (`report_id`),
  CONSTRAINT `FK_724ffp5jxmqpde8496in7kggu` FOREIGN KEY (`run_id`) REFERENCES `sequence_run` (`id`),
  CONSTRAINT `FK_8ae2rfhbp4pr7xk6kqpa38818` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_ptpiv5b92sigq6bm3l854sgj8` FOREIGN KEY (`report_id`) REFERENCES `summary_report` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequencing_cohort`
--

LOCK TABLES `sequencing_cohort` WRITE;
/*!40000 ALTER TABLE `sequencing_cohort` DISABLE KEYS */;
/*!40000 ALTER TABLE `sequencing_cohort` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequencing_experiment`
--

DROP TABLE IF EXISTS `sequencing_experiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequencing_experiment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `adapter_dimer_count` bigint(20) DEFAULT NULL,
  `fastq_file` varchar(1000) DEFAULT NULL,
  `fastqc_report` varchar(1000) DEFAULT NULL,
  `index_mismatch` int(11) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `public_db_id` varchar(255) DEFAULT NULL,
  `read_positions` varchar(255) DEFAULT NULL,
  `read_type_id` bigint(20) DEFAULT NULL,
  `sample_id` bigint(20) NOT NULL,
  `sequence_run_id` bigint(20) DEFAULT NULL,
  `total_reads` bigint(20) DEFAULT NULL,
  `cohort_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3mx03u80x4ynhsx1i65yrrxcq` (`sequence_run_id`),
  KEY `FK_3s9e144d345uyhay28toy8jwg` (`read_type_id`),
  KEY `FK_7gsumwrx4h3nji2g2q5htn098` (`sample_id`),
  KEY `FK_91odwapnmtkkmkbfserv13a80` (`cohort_id`),
  CONSTRAINT `FK_3mx03u80x4ynhsx1i65yrrxcq` FOREIGN KEY (`sequence_run_id`) REFERENCES `sequence_run` (`id`),
  CONSTRAINT `FK_3s9e144d345uyhay28toy8jwg` FOREIGN KEY (`read_type_id`) REFERENCES `read_type` (`id`),
  CONSTRAINT `FK_7gsumwrx4h3nji2g2q5htn098` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`),
  CONSTRAINT `FK_91odwapnmtkkmkbfserv13a80` FOREIGN KEY (`cohort_id`) REFERENCES `sequencing_cohort` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequencing_experiment`
--

LOCK TABLES `sequencing_experiment` WRITE;
/*!40000 ALTER TABLE `sequencing_experiment` DISABLE KEYS */;
/*!40000 ALTER TABLE `sequencing_experiment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequencing_platform`
--

DROP TABLE IF EXISTS `sequencing_platform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequencing_platform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902736` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequencing_platform`
--

LOCK TABLES `sequencing_platform` WRITE;
/*!40000 ALTER TABLE `sequencing_platform` DISABLE KEYS */;
INSERT INTO `sequencing_platform` VALUES (6,0,'NextSeq 500'),(7,0,'NextSeq 550'),(8,0,'MiSeq'),(9,0,'NovaSeq 6000'),(10,0,'PacBio Sequel II');
/*!40000 ALTER TABLE `sequencing_platform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sex`
--

DROP TABLE IF EXISTS `sex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sex` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902736` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sex`
--

LOCK TABLES `sex` WRITE;
/*!40000 ALTER TABLE `sex` DISABLE KEYS */;
/*!40000 ALTER TABLE `sex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `species` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `genus_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`genus_name`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
INSERT INTO `species` VALUES (1,0,'Saccharomyces','cerevisiae',NULL,NULL),(2,0,'Mus','musculus',NULL,NULL),(3,0,'Drosophila','melanogaster',NULL,NULL),(4,0,'Homo','sapiens',NULL,NULL),(8,0,'Schizosaccharomcyes','pombe',NULL,NULL),(10,0,'Escherichia','coli',NULL,NULL),(15,0,'Bos','taurus',NULL,NULL),(17,0,'Caenorhabditis','elegans',NULL,NULL),(21,0,'Arabidopsis','thaliana',NULL,NULL),(25,1,'Rattus','Norvegicus',NULL,NULL),(41,0,'Plasmodium','falciparum',NULL,NULL),(44,0,'Oryza','Sativa',NULL,NULL),(46,0,'Candida','albicans',NULL,NULL);
/*!40000 ALTER TABLE `species` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strain`
--

DROP TABLE IF EXISTS `strain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strain` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `genetic_modification` varchar(255) DEFAULT NULL,
  `genotype` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `source_lab_id` bigint(20) DEFAULT NULL,
  `species_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4shdjcf8qcywolsnrmi3djhmu` (`parent_id`),
  KEY `FK_bd07vlra9sn9vs8po73rw89wg` (`source_lab_id`),
  KEY `FK_c5gb45n5okxh7bqig1nouw76t` (`species_id`),
  CONSTRAINT `FK_4shdjcf8qcywolsnrmi3djhmu` FOREIGN KEY (`parent_id`) REFERENCES `strain` (`id`),
  CONSTRAINT `FK_bd07vlra9sn9vs8po73rw89wg` FOREIGN KEY (`source_lab_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK_c5gb45n5okxh7bqig1nouw76t` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strain`
--

LOCK TABLES `strain` WRITE;
/*!40000 ALTER TABLE `strain` DISABLE KEYS */;
/*!40000 ALTER TABLE `strain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `summary_report`
--

DROP TABLE IF EXISTS `summary_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summary_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summary_report`
--

LOCK TABLES `summary_report` WRITE;
/*!40000 ALTER TABLE `summary_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `summary_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `target`
--

DROP TABLE IF EXISTS `target`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `target` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `c_term_tag` varchar(255) DEFAULT NULL,
  `n_term_tag` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `target_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`c_term_tag`,`n_term_tag`,`name`),
  KEY `FK_e5wo7j1f9so6mcll9vwtn6005` (`target_type_id`),
  CONSTRAINT `FK_e5wo7j1f9so6mcll9vwtn6005` FOREIGN KEY (`target_type_id`) REFERENCES `target_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `target`
--

LOCK TABLES `target` WRITE;
/*!40000 ALTER TABLE `target` DISABLE KEYS */;
/*!40000 ALTER TABLE `target` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `target_type`
--

DROP TABLE IF EXISTS `target_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `target_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902739` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `target_type`
--

LOCK TABLES `target_type` WRITE;
/*!40000 ALTER TABLE `target_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `target_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tissue`
--

DROP TABLE IF EXISTS `tissue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tissue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1470678902740` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tissue`
--

LOCK TABLES `tissue` WRITE;
/*!40000 ALTER TABLE `tissue` DISABLE KEYS */;
/*!40000 ALTER TABLE `tissue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g7im3j7f0g31yhl6qco2iboy5` (`user_id`),
  CONSTRAINT `FK_g7im3j7f0g31yhl6qco2iboy5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `affiliation_id` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `password_expired` bit(1) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `api_key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_uniq_1470678902741` (`username`),
  KEY `FK_dhlcfg8h1drrgu0irs1ro3ohb` (`address_id`),
  KEY `FK_qdusuaq6oge31t7nlq10wm6ku` (`affiliation_id`),
  CONSTRAINT `FK_dhlcfg8h1drrgu0irs1ro3ohb` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_qdusuaq6oge31t7nlq10wm6ku` FOREIGN KEY (`affiliation_id`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,6,'\0','\0',NULL,NULL,NULL,'','lab admin','{bcrypt}$2a$10$/giOljS9vOY72gzp4p4.t.dE/UwOdEAX7Nhbajd2OzHeRZZDQgK2.','\0',NULL,'labadmin','BsdrDjUykrdcYQRAUuIXW7PgrvgQ2yw9');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_apcc8lxk2xnug8377fatvbn04` (`user_id`),
  KEY `FK_it77eq964jhfqtu54081ebtio` (`role_id`),
  CONSTRAINT `FK_apcc8lxk2xnug8377fatvbn04` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_it77eq964jhfqtu54081ebtio` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_group`
--

DROP TABLE IF EXISTS `user_role_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_group` (
  `role_group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_group_id`,`user_id`),
  KEY `FK_9se04wabb1vif6yjcfged3s2p` (`role_group_id`),
  KEY `FK_d9ttuc99ggo3nld2pd96x6pe7` (`user_id`),
  CONSTRAINT `FK_9se04wabb1vif6yjcfged3s2p` FOREIGN KEY (`role_group_id`) REFERENCES `role_group` (`id`),
  CONSTRAINT `FK_d9ttuc99ggo3nld2pd96x6pe7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_group`
--

LOCK TABLES `user_role_group` WRITE;
/*!40000 ALTER TABLE `user_role_group` DISABLE KEYS */;
INSERT INTO `user_role_group` VALUES (1,1);
/*!40000 ALTER TABLE `user_role_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-01 13:00:44
