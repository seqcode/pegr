-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: pegr
-- ------------------------------------------------------
-- Server version	5.7.9

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
  PRIMARY KEY (`ID`,`AUTHOR`,`FILENAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('1455126553565-1','dus73 (generated)','changelog.groovy','2016-02-10 14:55:50',1,'EXECUTED','3:3124e75e9a6c35733f9229dcd07c6e47','Create Table','',NULL,'2.0.5'),('1455126553565-10','dus73 (generated)','changelog.groovy','2016-02-10 14:55:51',10,'EXECUTED','3:41a2f99b038d9a740a6bada7a0534b3a','Create Table','',NULL,'2.0.5'),('1455126553565-100','dus73 (generated)','changelog.groovy','2016-02-10 14:56:31',241,'EXECUTED','3:5916ed4e73dc2b5a8ad16470dcf3ab4c','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-101','dus73 (generated)','changelog.groovy','2016-02-10 14:56:31',242,'EXECUTED','3:304f19c28cce6d5db5f42ba3c942162d','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-102','dus73 (generated)','changelog.groovy','2016-02-10 14:56:31',243,'EXECUTED','3:ef13e16eb2af38bc96ed62c46168071a','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-103','dus73 (generated)','changelog.groovy','2016-02-10 14:56:31',244,'EXECUTED','3:c5082c4d2bf92ed83986ee4468c2d917','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-104','dus73 (generated)','changelog.groovy','2016-02-10 14:56:32',245,'EXECUTED','3:569bcb4599fb0080ceb8e0376b756c83','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-105','dus73 (generated)','changelog.groovy','2016-02-10 14:56:32',246,'EXECUTED','3:5f5b9ee9a071599cc3b91104b5b20db5','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-106','dus73 (generated)','changelog.groovy','2016-02-10 14:56:32',247,'EXECUTED','3:64dadca7959f6109978dfb88f31fd2a5','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-107','dus73 (generated)','changelog.groovy','2016-02-10 14:56:32',248,'EXECUTED','3:8ca5668e911f01692029df08640a0cd7','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-108','dus73 (generated)','changelog.groovy','2016-02-10 14:56:33',249,'EXECUTED','3:9f52cb6ae36a2068daf16e79556eb041','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-109','dus73 (generated)','changelog.groovy','2016-02-10 14:56:33',250,'EXECUTED','3:500a289a798a51f9852529ca700c2ff3','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-11','dus73 (generated)','changelog.groovy','2016-02-10 14:55:51',11,'EXECUTED','3:9e31b591851fc5d7da32b77a5e14e1bb','Create Table','',NULL,'2.0.5'),('1455126553565-110','dus73 (generated)','changelog.groovy','2016-02-10 14:56:33',251,'EXECUTED','3:47fea2315c80c313b668eb727734b0ef','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-111','dus73 (generated)','changelog.groovy','2016-02-10 14:56:33',252,'EXECUTED','3:3793b1c3c0d8298f04a77af02f5189da','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-112','dus73 (generated)','changelog.groovy','2016-02-10 14:56:34',253,'EXECUTED','3:6f10a42315cd876ba16aa3d16018780c','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-113','dus73 (generated)','changelog.groovy','2016-02-10 14:56:34',254,'EXECUTED','3:bfcdd8940d88ab1576837e2d3adb2ba2','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-114','dus73 (generated)','changelog.groovy','2016-02-10 14:56:35',255,'EXECUTED','3:9a80f7731210cf939229f13bdc75eddb','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-115','dus73 (generated)','changelog.groovy','2016-02-10 14:56:35',256,'EXECUTED','3:34d0e37e4d6650392a8eda01c0189d15','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-116','dus73 (generated)','changelog.groovy','2016-02-10 14:56:35',257,'EXECUTED','3:4d3c55d69b0d203c467eb19fc32a409c','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-117','dus73 (generated)','changelog.groovy','2016-02-10 14:56:35',258,'EXECUTED','3:0142e67cc1bc5497e90fe32608f75f00','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-118','dus73 (generated)','changelog.groovy','2016-02-10 14:56:35',259,'EXECUTED','3:79335c9a482cbd9c31b5698488c11805','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-119','dus73 (generated)','changelog.groovy','2016-02-10 14:56:35',260,'EXECUTED','3:63f2f3057a2fa4b2deb75ed1780c416a','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-12','dus73 (generated)','changelog.groovy','2016-02-10 14:55:51',12,'EXECUTED','3:1be9dd7fcf3c522a08f67aa61474ffe4','Create Table','',NULL,'2.0.5'),('1455126553565-120','dus73 (generated)','changelog.groovy','2016-02-10 14:56:36',261,'EXECUTED','3:eef3a4b9df217ddd524120a86ed3aafa','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-121','dus73 (generated)','changelog.groovy','2016-02-10 14:56:36',262,'EXECUTED','3:3fba9283766ce85b73338c093af734b0','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-122','dus73 (generated)','changelog.groovy','2016-02-10 14:56:36',263,'EXECUTED','3:2ecd23e2d5c464524711a7cd366e4be9','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-123','dus73 (generated)','changelog.groovy','2016-02-10 14:56:37',264,'EXECUTED','3:3b9cb5807e37a8042f07e1b0c58d5b26','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-124','dus73 (generated)','changelog.groovy','2016-02-10 14:56:37',265,'EXECUTED','3:c8dc7ee6609dc87d2c955e6cb2f915e5','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-125','dus73 (generated)','changelog.groovy','2016-02-10 14:56:37',266,'EXECUTED','3:07e8d33e9bb7c018bd2421b93f67bbcc','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-126','dus73 (generated)','changelog.groovy','2016-02-10 14:56:37',267,'EXECUTED','3:ef76b21eaa711839cb65545a25002f65','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-127','dus73 (generated)','changelog.groovy','2016-02-10 14:56:37',268,'EXECUTED','3:f59906c247ec347e4795c9d2fe7551dc','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-128','dus73 (generated)','changelog.groovy','2016-02-10 14:56:38',269,'EXECUTED','3:5c15cb1131742f4535019ae82f784cfe','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-129','dus73 (generated)','changelog.groovy','2016-02-10 14:56:38',270,'EXECUTED','3:a0d37a406418bb949e541adb7095827d','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-13','dus73 (generated)','changelog.groovy','2016-02-10 14:55:51',13,'EXECUTED','3:3773a5435d7875886b05becacfa2b1ca','Create Table','',NULL,'2.0.5'),('1455126553565-130','dus73 (generated)','changelog.groovy','2016-02-10 14:56:38',271,'EXECUTED','3:65e1af6f49477dcb98cb3f43cdd1ffb0','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-131','dus73 (generated)','changelog.groovy','2016-02-10 14:56:38',272,'EXECUTED','3:af02e66a50f078d846f5b867cfeadcf6','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-132','dus73 (generated)','changelog.groovy','2016-02-10 14:56:38',273,'EXECUTED','3:5d072eea9dd4456d0bcee6d2a4f2a424','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-133','dus73 (generated)','changelog.groovy','2016-02-10 14:56:38',274,'EXECUTED','3:e93e42501c2ed1f86e2948e651105b36','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-134','dus73 (generated)','changelog.groovy','2016-02-10 14:56:39',275,'EXECUTED','3:70981ccd580a48de62888da2b74f813f','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-135','dus73 (generated)','changelog.groovy','2016-02-10 14:56:39',276,'EXECUTED','3:bb0e000e8a93518001c47328c488ed99','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-136','dus73 (generated)','changelog.groovy','2016-02-10 14:56:39',277,'EXECUTED','3:1fbebe23e46cc71419d778990fa69836','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-137','dus73 (generated)','changelog.groovy','2016-02-10 14:56:39',278,'EXECUTED','3:6eb1cc569d232b2b8c2359b8bae5a9df','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-138','dus73 (generated)','changelog.groovy','2016-02-10 14:56:39',279,'EXECUTED','3:3966da5419afbc37c83746a9fae97ddc','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-139','dus73 (generated)','changelog.groovy','2016-02-10 14:56:40',280,'EXECUTED','3:fb35f95d8613d8be1de687bd0a1ec784','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-14','dus73 (generated)','changelog.groovy','2016-02-10 14:55:52',14,'EXECUTED','3:465edd15ecc58ac41ba354722107a2d4','Create Table','',NULL,'2.0.5'),('1455126553565-140','dus73 (generated)','changelog.groovy','2016-02-10 14:56:40',281,'EXECUTED','3:93011090c5420c0527897e3ecfcfb45b','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-141','dus73 (generated)','changelog.groovy','2016-02-10 14:56:40',282,'EXECUTED','3:a5b62218b0d7a9adee90d2eaa65cc4ea','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-142','dus73 (generated)','changelog.groovy','2016-02-10 14:56:40',283,'EXECUTED','3:f74eb22fde27454bae40acc6c050b98b','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-143','dus73 (generated)','changelog.groovy','2016-02-10 14:56:40',284,'EXECUTED','3:7cefbebc7786f1a1393feb10b666d172','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-144','dus73 (generated)','changelog.groovy','2016-02-10 14:56:40',285,'EXECUTED','3:3f83fee9dc90845fbf8e6b69547e4d44','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-145','dus73 (generated)','changelog.groovy','2016-02-10 14:56:41',286,'EXECUTED','3:ea42929b5754bdc19372d05275b28ec0','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-146','dus73 (generated)','changelog.groovy','2016-02-10 14:56:41',287,'EXECUTED','3:457450d09b8351f4a3ece2f034561c64','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-147','dus73 (generated)','changelog.groovy','2016-02-10 14:56:41',288,'EXECUTED','3:47bd8e7a7242390a66fbd4b66c499fc8','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-148','dus73 (generated)','changelog.groovy','2016-02-10 14:56:41',289,'EXECUTED','3:3793521ee6baf17b23d0e965baae1b30','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-149','dus73 (generated)','changelog.groovy','2016-02-10 14:56:41',290,'EXECUTED','3:a118944a3f82e8ac908b1fde27d20f89','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-15','dus73 (generated)','changelog.groovy','2016-02-10 14:55:52',15,'EXECUTED','3:7e95e6a5da23b173d69d55af68049de9','Create Table','',NULL,'2.0.5'),('1455126553565-150','dus73 (generated)','changelog.groovy','2016-02-10 14:56:41',291,'EXECUTED','3:f8e6abc32acdc8dcc7ff0a572845bc81','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-151','dus73 (generated)','changelog.groovy','2016-02-10 14:56:42',292,'EXECUTED','3:0633bf840db6fd99b4487b64b4af7c4c','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-152','dus73 (generated)','changelog.groovy','2016-02-10 14:56:42',293,'EXECUTED','3:180a004611161d7176306a2feb076c38','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-153','dus73 (generated)','changelog.groovy','2016-02-10 14:56:42',294,'EXECUTED','3:a667b5299c9c21ba3bebea720a9c1154','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-154','dus73 (generated)','changelog.groovy','2016-02-10 14:56:42',295,'EXECUTED','3:c188630bd41f79b62057d2bcf51f3a91','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-155','dus73 (generated)','changelog.groovy','2016-02-10 14:56:42',296,'EXECUTED','3:2721b4b2fd2d4324d7fe08bb167d5a1d','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-156','dus73 (generated)','changelog.groovy','2016-02-10 14:56:42',297,'EXECUTED','3:a820684b429b3c4a413b53057cbf181d','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-157','dus73 (generated)','changelog.groovy','2016-02-10 14:56:43',298,'EXECUTED','3:a7c8a54b2408462677b1408431df592f','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-158','dus73 (generated)','changelog.groovy','2016-02-10 14:56:43',299,'EXECUTED','3:03ba8089c390f7dcd0ff431309682060','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-159','dus73 (generated)','changelog.groovy','2016-02-10 14:56:43',300,'EXECUTED','3:089df6f969c98cf1ddd4225a1579e29a','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-16','dus73 (generated)','changelog.groovy','2016-02-10 14:55:52',16,'EXECUTED','3:d35cb5834406385972708fe80cf81e06','Create Table','',NULL,'2.0.5'),('1455126553565-160','dus73 (generated)','changelog.groovy','2016-02-10 14:56:43',301,'EXECUTED','3:ab7dce1b0fdf821160c82900a19d86ae','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-161','dus73 (generated)','changelog.groovy','2016-02-10 14:56:43',302,'EXECUTED','3:a61038649e8d76bb664f7a7cbdff9da9','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-162','dus73 (generated)','changelog.groovy','2016-02-10 14:56:44',303,'EXECUTED','3:6a03c567f7165867b07ed703ce579bca','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-163','dus73 (generated)','changelog.groovy','2016-02-10 14:56:00',68,'EXECUTED','3:e16cad3ca7a74bb104c9a6f6852730f4','Create Index','',NULL,'2.0.5'),('1455126553565-164','dus73 (generated)','changelog.groovy','2016-02-10 14:56:00',69,'EXECUTED','3:fca3d1ee4343ac15e51f8510f66b9f28','Create Index','',NULL,'2.0.5'),('1455126553565-165','dus73 (generated)','changelog.groovy','2016-02-10 14:56:00',70,'EXECUTED','3:caa3d28838e79871b77cf8281ee93290','Create Index','',NULL,'2.0.5'),('1455126553565-166','dus73 (generated)','changelog.groovy','2016-02-10 14:56:00',71,'EXECUTED','3:7932981ae12e1d0a2dec587ef4bbdda7','Create Index','',NULL,'2.0.5'),('1455126553565-167','dus73 (generated)','changelog.groovy','2016-02-10 14:56:00',72,'EXECUTED','3:b02e77b68db418167f4a792bb1e8921f','Create Index','',NULL,'2.0.5'),('1455126553565-168','dus73 (generated)','changelog.groovy','2016-02-10 14:56:01',73,'EXECUTED','3:3d26bc6f2c98ab78ef632faeb9f1b419','Create Index','',NULL,'2.0.5'),('1455126553565-169','dus73 (generated)','changelog.groovy','2016-02-10 14:56:01',74,'EXECUTED','3:4af69f5b1764e2456594a9dc748194d1','Create Index','',NULL,'2.0.5'),('1455126553565-17','dus73 (generated)','changelog.groovy','2016-02-10 14:55:52',17,'EXECUTED','3:74be56d00898f7b0b27c5f39be109cb3','Create Table','',NULL,'2.0.5'),('1455126553565-170','dus73 (generated)','changelog.groovy','2016-02-10 14:56:01',75,'EXECUTED','3:0917d06e327b8edfce2ba833ec069f5d','Create Index','',NULL,'2.0.5'),('1455126553565-171','dus73 (generated)','changelog.groovy','2016-02-10 14:56:01',76,'EXECUTED','3:82ae048534cb3c55825e4cd623812c6c','Create Index','',NULL,'2.0.5'),('1455126553565-172','dus73 (generated)','changelog.groovy','2016-02-10 14:56:01',77,'EXECUTED','3:ffc80e43a0912cc9cf0e9bbed18350c1','Create Index','',NULL,'2.0.5'),('1455126553565-173','dus73 (generated)','changelog.groovy','2016-02-10 14:56:01',78,'EXECUTED','3:05be2009192855a47b090bf065f72e33','Create Index','',NULL,'2.0.5'),('1455126553565-174','dus73 (generated)','changelog.groovy','2016-02-10 14:56:02',79,'EXECUTED','3:30e6df3c9c3e94c1279dd102f4afb6d4','Create Index','',NULL,'2.0.5'),('1455126553565-175','dus73 (generated)','changelog.groovy','2016-02-10 14:56:02',80,'EXECUTED','3:d4f9483144de99b075b373ffd792e2a6','Create Index','',NULL,'2.0.5'),('1455126553565-176','dus73 (generated)','changelog.groovy','2016-02-10 14:56:02',81,'EXECUTED','3:acf38e29cca22b7f11bc8d3ffdca8c07','Create Index','',NULL,'2.0.5'),('1455126553565-177','dus73 (generated)','changelog.groovy','2016-02-10 14:56:02',82,'EXECUTED','3:b84497ac98a5e3f3edd262ea6270d4e9','Create Index','',NULL,'2.0.5'),('1455126553565-178','dus73 (generated)','changelog.groovy','2016-02-10 14:56:02',83,'EXECUTED','3:cf9eb812c942eba4828a37b66ccd39b5','Create Index','',NULL,'2.0.5'),('1455126553565-179','dus73 (generated)','changelog.groovy','2016-02-10 14:56:02',84,'EXECUTED','3:22c93755f748c6e0c9173f5425288761','Create Index','',NULL,'2.0.5'),('1455126553565-18','dus73 (generated)','changelog.groovy','2016-02-10 14:55:52',18,'EXECUTED','3:84db5760deb3fa9678c7fd11acd1a749','Create Table','',NULL,'2.0.5'),('1455126553565-180','dus73 (generated)','changelog.groovy','2016-02-10 14:56:03',85,'EXECUTED','3:e32770a6861c1ae3c03e19544c988557','Create Index','',NULL,'2.0.5'),('1455126553565-181','dus73 (generated)','changelog.groovy','2016-02-10 14:56:03',86,'EXECUTED','3:368edf77a5eada428131e8bae3cb53aa','Create Index','',NULL,'2.0.5'),('1455126553565-182','dus73 (generated)','changelog.groovy','2016-02-10 14:56:03',87,'EXECUTED','3:cfd0e5ca960ec4227dd139edead29315','Create Index','',NULL,'2.0.5'),('1455126553565-183','dus73 (generated)','changelog.groovy','2016-02-10 14:56:03',88,'EXECUTED','3:eba4ef3ad38935d292867a1f87d96226','Create Index','',NULL,'2.0.5'),('1455126553565-184','dus73 (generated)','changelog.groovy','2016-02-10 14:56:03',89,'EXECUTED','3:3404e1ab8d58cef8dbf7268c82067dab','Create Index','',NULL,'2.0.5'),('1455126553565-185','dus73 (generated)','changelog.groovy','2016-02-10 14:56:04',90,'EXECUTED','3:1e7d55b331315136c3b4a161bff064d5','Create Index','',NULL,'2.0.5'),('1455126553565-186','dus73 (generated)','changelog.groovy','2016-02-10 14:56:04',91,'EXECUTED','3:910066995a7d3543b645bf6c6050c4bd','Create Index','',NULL,'2.0.5'),('1455126553565-187','dus73 (generated)','changelog.groovy','2016-02-10 14:56:04',92,'EXECUTED','3:b21f4e440e86e29475bbc321da658be8','Create Index','',NULL,'2.0.5'),('1455126553565-188','dus73 (generated)','changelog.groovy','2016-02-10 14:56:04',93,'EXECUTED','3:e46165242d9436d9da533203dbbbe44f','Create Index','',NULL,'2.0.5'),('1455126553565-189','dus73 (generated)','changelog.groovy','2016-02-10 14:56:05',94,'EXECUTED','3:bc7a4a7db37a77f02441faf05ce603e6','Create Index','',NULL,'2.0.5'),('1455126553565-19','dus73 (generated)','changelog.groovy','2016-02-10 14:55:52',19,'EXECUTED','3:149a53b56dc5fb596a0c18b7aade46dc','Create Table','',NULL,'2.0.5'),('1455126553565-190','dus73 (generated)','changelog.groovy','2016-02-10 14:56:05',95,'EXECUTED','3:49a7644537b0523fb60b02855af3978b','Create Index','',NULL,'2.0.5'),('1455126553565-191','dus73 (generated)','changelog.groovy','2016-02-10 14:56:05',96,'EXECUTED','3:27651f4e42c33ad12c7bf52af1f8977c','Create Index','',NULL,'2.0.5'),('1455126553565-192','dus73 (generated)','changelog.groovy','2016-02-10 14:56:05',97,'EXECUTED','3:9aa00d694e60eb8d090b21516624d74c','Create Index','',NULL,'2.0.5'),('1455126553565-193','dus73 (generated)','changelog.groovy','2016-02-10 14:56:05',98,'EXECUTED','3:184060808bee141ec1a956f66b5957c1','Create Index','',NULL,'2.0.5'),('1455126553565-194','dus73 (generated)','changelog.groovy','2016-02-10 14:56:05',99,'EXECUTED','3:cc35ef67ea483f936e663dfa2a4b524e','Create Index','',NULL,'2.0.5'),('1455126553565-195','dus73 (generated)','changelog.groovy','2016-02-10 14:56:06',100,'EXECUTED','3:f0a056915a1a0d924c3ce49c914782a5','Create Index','',NULL,'2.0.5'),('1455126553565-196','dus73 (generated)','changelog.groovy','2016-02-10 14:56:06',101,'EXECUTED','3:45b1d5f654af21422c2bb760007818b9','Create Index','',NULL,'2.0.5'),('1455126553565-197','dus73 (generated)','changelog.groovy','2016-02-10 14:56:06',102,'EXECUTED','3:607edec87c102456fa8ab160093a3526','Create Index','',NULL,'2.0.5'),('1455126553565-198','dus73 (generated)','changelog.groovy','2016-02-10 14:56:06',103,'EXECUTED','3:f1062890db6b2315d254b45b344daaed','Create Index','',NULL,'2.0.5'),('1455126553565-199','dus73 (generated)','changelog.groovy','2016-02-10 14:56:06',104,'EXECUTED','3:6e360506c04f2cbaff0d33a1e716f631','Create Index','',NULL,'2.0.5'),('1455126553565-2','dus73 (generated)','changelog.groovy','2016-02-10 14:55:50',2,'EXECUTED','3:6e09277d3b4ee0d4f3d213cbd06dc872','Create Table','',NULL,'2.0.5'),('1455126553565-20','dus73 (generated)','changelog.groovy','2016-02-10 14:55:52',20,'EXECUTED','3:958a327e620e6f69723a8c79c42f59ff','Create Table','',NULL,'2.0.5'),('1455126553565-200','dus73 (generated)','changelog.groovy','2016-02-10 14:56:07',105,'EXECUTED','3:f3b3ed8167797cd28947c6459ab39850','Create Index','',NULL,'2.0.5'),('1455126553565-201','dus73 (generated)','changelog.groovy','2016-02-10 14:56:07',106,'EXECUTED','3:19d68bf1ec947154268949278f355bf9','Create Index','',NULL,'2.0.5'),('1455126553565-202','dus73 (generated)','changelog.groovy','2016-02-10 14:56:07',107,'EXECUTED','3:820e5343240f918a0ad17de861271b74','Create Index','',NULL,'2.0.5'),('1455126553565-203','dus73 (generated)','changelog.groovy','2016-02-10 14:56:07',108,'EXECUTED','3:41f1bba7427e4bd4eec9fcaff3308c50','Create Index','',NULL,'2.0.5'),('1455126553565-204','dus73 (generated)','changelog.groovy','2016-02-10 14:56:07',109,'EXECUTED','3:4e2cde61ec80fb1ba5748f09b4489270','Create Index','',NULL,'2.0.5'),('1455126553565-205','dus73 (generated)','changelog.groovy','2016-02-10 14:56:07',110,'EXECUTED','3:bffa3650bd6344224a7e9722eca357d6','Create Index','',NULL,'2.0.5'),('1455126553565-206','dus73 (generated)','changelog.groovy','2016-02-10 14:56:08',111,'EXECUTED','3:9666507925fb4b7acd8b905ca936284f','Create Index','',NULL,'2.0.5'),('1455126553565-207','dus73 (generated)','changelog.groovy','2016-02-10 14:56:08',112,'EXECUTED','3:f13dc4bd47727531a467cafe0b1260dd','Create Index','',NULL,'2.0.5'),('1455126553565-208','dus73 (generated)','changelog.groovy','2016-02-10 14:56:08',113,'EXECUTED','3:d7eeb5f37b9bd0da302a008274d16e16','Create Index','',NULL,'2.0.5'),('1455126553565-209','dus73 (generated)','changelog.groovy','2016-02-10 14:56:08',114,'EXECUTED','3:816e504428b9de190901a2a23393461d','Create Index','',NULL,'2.0.5'),('1455126553565-21','dus73 (generated)','changelog.groovy','2016-02-10 14:55:53',21,'EXECUTED','3:f2f70bfb96f3b39ff9e9fc7cf313ea48','Create Table','',NULL,'2.0.5'),('1455126553565-210','dus73 (generated)','changelog.groovy','2016-02-10 14:56:08',115,'EXECUTED','3:2fd36b04162c500a1c7670eb2bd28175','Create Index','',NULL,'2.0.5'),('1455126553565-211','dus73 (generated)','changelog.groovy','2016-02-10 14:56:08',116,'EXECUTED','3:2cb101d9db84e56ca52124a1b5265d14','Create Index','',NULL,'2.0.5'),('1455126553565-212','dus73 (generated)','changelog.groovy','2016-02-10 14:56:09',117,'EXECUTED','3:02792f8cc6f814d0bdab281e165f7ebd','Create Index','',NULL,'2.0.5'),('1455126553565-213','dus73 (generated)','changelog.groovy','2016-02-10 14:56:09',118,'EXECUTED','3:ddde7fd0695792f017df8025ec28629e','Create Index','',NULL,'2.0.5'),('1455126553565-214','dus73 (generated)','changelog.groovy','2016-02-10 14:56:09',119,'EXECUTED','3:048df4d907a4f5e9a8bee5de49907f50','Create Index','',NULL,'2.0.5'),('1455126553565-215','dus73 (generated)','changelog.groovy','2016-02-10 14:56:09',120,'EXECUTED','3:cb92063b435fd466e1ec055d01f18b5d','Create Index','',NULL,'2.0.5'),('1455126553565-216','dus73 (generated)','changelog.groovy','2016-02-10 14:56:09',121,'EXECUTED','3:75c3c9d31b102505ed1f9fc32e616208','Create Index','',NULL,'2.0.5'),('1455126553565-217','dus73 (generated)','changelog.groovy','2016-02-10 14:56:09',122,'EXECUTED','3:23f2231709d1c798b72df8ece208478c','Create Index','',NULL,'2.0.5'),('1455126553565-218','dus73 (generated)','changelog.groovy','2016-02-10 14:56:10',123,'EXECUTED','3:6c6b62f20068f69f188dc6e0ac207139','Create Index','',NULL,'2.0.5'),('1455126553565-219','dus73 (generated)','changelog.groovy','2016-02-10 14:56:10',124,'EXECUTED','3:6e584021777fa389392703b4a8e46b70','Create Index','',NULL,'2.0.5'),('1455126553565-22','dus73 (generated)','changelog.groovy','2016-02-10 14:55:53',22,'EXECUTED','3:bf6b036931f5867c2c6cd2ab64efb1d0','Create Table','',NULL,'2.0.5'),('1455126553565-220','dus73 (generated)','changelog.groovy','2016-02-10 14:56:10',125,'EXECUTED','3:69565dbc3eb2b0d0bc1a6e8b333d47ca','Create Index','',NULL,'2.0.5'),('1455126553565-221','dus73 (generated)','changelog.groovy','2016-02-10 14:56:10',126,'EXECUTED','3:77c6b977966a3b415b8ac3b4fb1e7189','Create Index','',NULL,'2.0.5'),('1455126553565-222','dus73 (generated)','changelog.groovy','2016-02-10 14:56:10',127,'EXECUTED','3:902104e10c10a9b249b6810be7c4b6e3','Create Index','',NULL,'2.0.5'),('1455126553565-223','dus73 (generated)','changelog.groovy','2016-02-10 14:56:10',128,'EXECUTED','3:4465942cd4b6daf9d6c0111ac70322b5','Create Index','',NULL,'2.0.5'),('1455126553565-224','dus73 (generated)','changelog.groovy','2016-02-10 14:56:10',129,'EXECUTED','3:a35085d54b109eff1852f6e638952007','Create Index','',NULL,'2.0.5'),('1455126553565-225','dus73 (generated)','changelog.groovy','2016-02-10 14:56:11',130,'EXECUTED','3:9656bac3ed63185a88b253f2aea36481','Create Index','',NULL,'2.0.5'),('1455126553565-226','dus73 (generated)','changelog.groovy','2016-02-10 14:56:11',131,'EXECUTED','3:40435da2c817b66eb9d7f6f1c23cdd63','Create Index','',NULL,'2.0.5'),('1455126553565-227','dus73 (generated)','changelog.groovy','2016-02-10 14:56:11',132,'EXECUTED','3:97072d1607a141a51e28a291ca2e87a3','Create Index','',NULL,'2.0.5'),('1455126553565-228','dus73 (generated)','changelog.groovy','2016-02-10 14:56:11',133,'EXECUTED','3:1641440a9172bb711b7924c15e25f6f8','Create Index','',NULL,'2.0.5'),('1455126553565-229','dus73 (generated)','changelog.groovy','2016-02-10 14:56:11',134,'EXECUTED','3:177fd11f133f54fc5ee5282ae9785053','Create Index','',NULL,'2.0.5'),('1455126553565-23','dus73 (generated)','changelog.groovy','2016-02-10 14:55:53',23,'EXECUTED','3:01d948e9a3031cb80d156b4a9859f00a','Create Table','',NULL,'2.0.5'),('1455126553565-230','dus73 (generated)','changelog.groovy','2016-02-10 14:56:11',135,'EXECUTED','3:c9fef2bc60537db2ffd1bfa0aff479ce','Create Index','',NULL,'2.0.5'),('1455126553565-231','dus73 (generated)','changelog.groovy','2016-02-10 14:56:12',136,'EXECUTED','3:ff3a7068397dcdba0569a1784128f45e','Create Index','',NULL,'2.0.5'),('1455126553565-232','dus73 (generated)','changelog.groovy','2016-02-10 14:56:12',137,'EXECUTED','3:2be33e35e32115bfd4345ef470ef8de8','Create Index','',NULL,'2.0.5'),('1455126553565-233','dus73 (generated)','changelog.groovy','2016-02-10 14:56:12',138,'EXECUTED','3:6b4b47f8000aa83d80c669a6eb4a7e6f','Create Index','',NULL,'2.0.5'),('1455126553565-234','dus73 (generated)','changelog.groovy','2016-02-10 14:56:12',139,'EXECUTED','3:2ef2d3adeda6a52508df1ceadf498575','Create Index','',NULL,'2.0.5'),('1455126553565-235','dus73 (generated)','changelog.groovy','2016-02-10 14:56:12',140,'EXECUTED','3:d2a629c27f8468eb1a5c1ece48391fa1','Create Index','',NULL,'2.0.5'),('1455126553565-236','dus73 (generated)','changelog.groovy','2016-02-10 14:56:12',141,'EXECUTED','3:0722323d86eabd24b60e8e2d4afdc283','Create Index','',NULL,'2.0.5'),('1455126553565-237','dus73 (generated)','changelog.groovy','2016-02-10 14:56:12',142,'EXECUTED','3:ed09504a1290418844cbffc610b08071','Create Index','',NULL,'2.0.5'),('1455126553565-238','dus73 (generated)','changelog.groovy','2016-02-10 14:56:13',143,'EXECUTED','3:1fb76493c506b8d0b477890ece0ccc6e','Create Index','',NULL,'2.0.5'),('1455126553565-239','dus73 (generated)','changelog.groovy','2016-02-10 14:56:13',144,'EXECUTED','3:3d243c8580c09d094abe95bfa24e0fdb','Create Index','',NULL,'2.0.5'),('1455126553565-24','dus73 (generated)','changelog.groovy','2016-02-10 14:55:53',24,'EXECUTED','3:25f63c9381358260f8f8fb81c4c139cd','Create Table','',NULL,'2.0.5'),('1455126553565-240','dus73 (generated)','changelog.groovy','2016-02-10 14:56:13',145,'EXECUTED','3:e37605eca05d66d0b86a689fb3a3cb7e','Create Index','',NULL,'2.0.5'),('1455126553565-241','dus73 (generated)','changelog.groovy','2016-02-10 14:56:13',146,'EXECUTED','3:da5f6a10c51c7e8d8b44220f72b83dc0','Create Index','',NULL,'2.0.5'),('1455126553565-242','dus73 (generated)','changelog.groovy','2016-02-10 14:56:13',147,'EXECUTED','3:e309c309dbcf714d68a0d377356d6b18','Create Index','',NULL,'2.0.5'),('1455126553565-243','dus73 (generated)','changelog.groovy','2016-02-10 14:56:13',148,'EXECUTED','3:a093d907e790ba6b90ee519446b20027','Create Index','',NULL,'2.0.5'),('1455126553565-244','dus73 (generated)','changelog.groovy','2016-02-10 14:56:14',149,'EXECUTED','3:ff29fba6230a4cc1ead55eea2c1623d5','Create Index','',NULL,'2.0.5'),('1455126553565-245','dus73 (generated)','changelog.groovy','2016-02-10 14:56:14',150,'EXECUTED','3:b31ec5f1629d4a79c7765737a3a1c04d','Create Index','',NULL,'2.0.5'),('1455126553565-246','dus73 (generated)','changelog.groovy','2016-02-10 14:56:14',151,'EXECUTED','3:d1d8552ee57c149690b1fe6943a82e48','Create Index','',NULL,'2.0.5'),('1455126553565-247','dus73 (generated)','changelog.groovy','2016-02-10 14:56:14',152,'EXECUTED','3:ef9c3387d3a36d4b67520d1b5f5fec4e','Create Index','',NULL,'2.0.5'),('1455126553565-248','dus73 (generated)','changelog.groovy','2016-02-10 14:56:14',153,'EXECUTED','3:a093e79f3bcf535ce9802801f85a0a27','Create Index','',NULL,'2.0.5'),('1455126553565-249','dus73 (generated)','changelog.groovy','2016-02-10 14:56:14',154,'EXECUTED','3:c72fda61acec70db56082cb6de701595','Create Index','',NULL,'2.0.5'),('1455126553565-25','dus73 (generated)','changelog.groovy','2016-02-10 14:55:53',25,'EXECUTED','3:6ddeb5b1ce3798bc5ed30e247dac100a','Create Table','',NULL,'2.0.5'),('1455126553565-250','dus73 (generated)','changelog.groovy','2016-02-10 14:56:15',155,'EXECUTED','3:e432ddc33bc96903f2635108dd705d4a','Create Index','',NULL,'2.0.5'),('1455126553565-251','dus73 (generated)','changelog.groovy','2016-02-10 14:56:15',156,'EXECUTED','3:bdaf3ab6ee96c6873b9e4cbec09637e1','Create Index','',NULL,'2.0.5'),('1455126553565-252','dus73 (generated)','changelog.groovy','2016-02-10 14:56:15',157,'EXECUTED','3:a574a7bb0c5120a0b7705f36a04e240e','Create Index','',NULL,'2.0.5'),('1455126553565-253','dus73 (generated)','changelog.groovy','2016-02-10 14:56:15',158,'EXECUTED','3:985fed27db478ff860727301300b986d','Create Index','',NULL,'2.0.5'),('1455126553565-254','dus73 (generated)','changelog.groovy','2016-02-10 14:56:15',159,'EXECUTED','3:f847d40230fbdab06c2ae09a4d2e19d7','Create Index','',NULL,'2.0.5'),('1455126553565-255','dus73 (generated)','changelog.groovy','2016-02-10 14:56:16',160,'EXECUTED','3:16a11628923105d4225ddbdbb8e46b91','Create Index','',NULL,'2.0.5'),('1455126553565-256','dus73 (generated)','changelog.groovy','2016-02-10 14:56:16',161,'EXECUTED','3:cb320c7a3f1c6edb0262007d3a322a4c','Create Index','',NULL,'2.0.5'),('1455126553565-257','dus73 (generated)','changelog.groovy','2016-02-10 14:56:16',162,'EXECUTED','3:613d775765b6139d47654ef68e1ccc11','Create Index','',NULL,'2.0.5'),('1455126553565-258','dus73 (generated)','changelog.groovy','2016-02-10 14:56:16',163,'EXECUTED','3:bd643ddd00e46b4ad81fe1aa6b897024','Create Index','',NULL,'2.0.5'),('1455126553565-259','dus73 (generated)','changelog.groovy','2016-02-10 14:56:16',164,'EXECUTED','3:09f487dac68ebf7108f4da06965f911d','Create Index','',NULL,'2.0.5'),('1455126553565-26','dus73 (generated)','changelog.groovy','2016-02-10 14:55:53',26,'EXECUTED','3:4bded21cea749c36807f4bc21ecfa9e2','Create Table','',NULL,'2.0.5'),('1455126553565-260','dus73 (generated)','changelog.groovy','2016-02-10 14:56:16',165,'EXECUTED','3:7c2be055a746f19db12df217725870a4','Create Index','',NULL,'2.0.5'),('1455126553565-261','dus73 (generated)','changelog.groovy','2016-02-10 14:56:17',166,'EXECUTED','3:9eb6f1ce85d04189bf7a05ff37322b80','Create Index','',NULL,'2.0.5'),('1455126553565-262','dus73 (generated)','changelog.groovy','2016-02-10 14:56:17',167,'EXECUTED','3:9e02718a35218a3bcc0f07818ce74131','Create Index','',NULL,'2.0.5'),('1455126553565-263','dus73 (generated)','changelog.groovy','2016-02-10 14:56:17',168,'EXECUTED','3:4b1d12796851d148be9d54247e300cc6','Create Index','',NULL,'2.0.5'),('1455126553565-264','dus73 (generated)','changelog.groovy','2016-02-10 14:56:17',169,'EXECUTED','3:b8b80c96a62c300f11cee5a2543b6676','Create Index','',NULL,'2.0.5'),('1455126553565-265','dus73 (generated)','changelog.groovy','2016-02-10 14:56:17',170,'EXECUTED','3:fd1ba7b65d76bbe37278bd79ee942903','Create Index','',NULL,'2.0.5'),('1455126553565-266','dus73 (generated)','changelog.groovy','2016-02-10 14:56:17',171,'EXECUTED','3:0c828e0ee10d4a5fbd9e9cb0566cbcc3','Create Index','',NULL,'2.0.5'),('1455126553565-267','dus73 (generated)','changelog.groovy','2016-02-10 14:56:18',172,'EXECUTED','3:c9214f4c6b4d80b5f57461437c5a13be','Create Index','',NULL,'2.0.5'),('1455126553565-268','dus73 (generated)','changelog.groovy','2016-02-10 14:56:18',173,'EXECUTED','3:0d041098d7a5794017ac4f0bfafbe850','Create Index','',NULL,'2.0.5'),('1455126553565-269','dus73 (generated)','changelog.groovy','2016-02-10 14:56:18',174,'EXECUTED','3:b5357782e3a760c5e8002fa1af373fe7','Create Index','',NULL,'2.0.5'),('1455126553565-27','dus73 (generated)','changelog.groovy','2016-02-10 14:55:53',27,'EXECUTED','3:5b6a83018b78503b7eabc188cbe1c7a2','Create Table','',NULL,'2.0.5'),('1455126553565-270','dus73 (generated)','changelog.groovy','2016-02-10 14:56:18',175,'EXECUTED','3:3a80d4f39be5298070ae487d91ca4e9c','Create Index','',NULL,'2.0.5'),('1455126553565-271','dus73 (generated)','changelog.groovy','2016-02-10 14:56:18',176,'EXECUTED','3:f675897685e125d2dc3dee414531103e','Create Index','',NULL,'2.0.5'),('1455126553565-272','dus73 (generated)','changelog.groovy','2016-02-10 14:56:19',177,'EXECUTED','3:b36d5439f01bd6f0577cd3ad4df9104b','Create Index','',NULL,'2.0.5'),('1455126553565-273','dus73 (generated)','changelog.groovy','2016-02-10 14:56:19',178,'EXECUTED','3:a45a3d7b37652144336261df6d599971','Create Index','',NULL,'2.0.5'),('1455126553565-274','dus73 (generated)','changelog.groovy','2016-02-10 14:56:19',179,'EXECUTED','3:c97aa108504c57a296f71b22b27c7308','Create Index','',NULL,'2.0.5'),('1455126553565-275','dus73 (generated)','changelog.groovy','2016-02-10 14:56:19',180,'EXECUTED','3:3905171ae31c66f52b61b73503e30cc9','Create Index','',NULL,'2.0.5'),('1455126553565-276','dus73 (generated)','changelog.groovy','2016-02-10 14:56:19',181,'EXECUTED','3:5d1f4234f54a30cc41f38238d85a1974','Create Index','',NULL,'2.0.5'),('1455126553565-277','dus73 (generated)','changelog.groovy','2016-02-10 14:56:19',182,'EXECUTED','3:720bc1bb608a33f5fa6d64059a4c4647','Create Index','',NULL,'2.0.5'),('1455126553565-278','dus73 (generated)','changelog.groovy','2016-02-10 14:56:20',183,'EXECUTED','3:08968656869100b8035a1ce9bf8f93fc','Create Index','',NULL,'2.0.5'),('1455126553565-279','dus73 (generated)','changelog.groovy','2016-02-10 14:56:20',184,'EXECUTED','3:4b28324970f557409d818fe93734d378','Create Index','',NULL,'2.0.5'),('1455126553565-28','dus73 (generated)','changelog.groovy','2016-02-10 14:55:54',28,'EXECUTED','3:60ea127847e2a5b9fc84cbcee11d8096','Create Table','',NULL,'2.0.5'),('1455126553565-280','dus73 (generated)','changelog.groovy','2016-02-10 14:56:20',185,'EXECUTED','3:1e95cd79ea98aa8e64ad09041db32d4f','Create Index','',NULL,'2.0.5'),('1455126553565-281','dus73 (generated)','changelog.groovy','2016-02-10 14:56:20',186,'EXECUTED','3:17223bf919544cb16b29fc36c23c7f6c','Create Index','',NULL,'2.0.5'),('1455126553565-282','dus73 (generated)','changelog.groovy','2016-02-10 14:56:20',187,'EXECUTED','3:b7a549e5172015b06875211c80e3c76c','Create Index','',NULL,'2.0.5'),('1455126553565-283','dus73 (generated)','changelog.groovy','2016-02-10 14:56:20',188,'EXECUTED','3:efdabf25da32543c55c96c2e22ea4bd6','Create Index','',NULL,'2.0.5'),('1455126553565-284','dus73 (generated)','changelog.groovy','2016-02-10 14:56:21',189,'EXECUTED','3:6de47b79a7b307db1ebdd25258749e6f','Create Index','',NULL,'2.0.5'),('1455126553565-285','dus73 (generated)','changelog.groovy','2016-02-10 14:56:21',190,'EXECUTED','3:e244a027e02d1a139a14612376ffd44f','Create Index','',NULL,'2.0.5'),('1455126553565-286','dus73 (generated)','changelog.groovy','2016-02-10 14:56:21',191,'EXECUTED','3:2cdb9f93d43b5de2a1a41d4f17e9c6dc','Create Index','',NULL,'2.0.5'),('1455126553565-287','dus73 (generated)','changelog.groovy','2016-02-10 14:56:22',192,'EXECUTED','3:bff5013cfe919c890c325d61f9820dcc','Create Index','',NULL,'2.0.5'),('1455126553565-288','dus73 (generated)','changelog.groovy','2016-02-10 14:56:22',193,'EXECUTED','3:8d81fb7ad5518dc9c2ee94c10ec43ded','Create Index','',NULL,'2.0.5'),('1455126553565-289','dus73 (generated)','changelog.groovy','2016-02-10 14:56:22',194,'EXECUTED','3:5222e1d3b12054846c5b8c5767ef9cd2','Create Index','',NULL,'2.0.5'),('1455126553565-29','dus73 (generated)','changelog.groovy','2016-02-10 14:55:54',29,'EXECUTED','3:c4f8c6e6190cd0f67b86de982a3a4797','Create Table','',NULL,'2.0.5'),('1455126553565-290','dus73 (generated)','changelog.groovy','2016-02-10 14:56:22',195,'EXECUTED','3:289cd87a80d2bb5c9fa777a2a328346c','Create Index','',NULL,'2.0.5'),('1455126553565-291','dus73 (generated)','changelog.groovy','2016-02-10 14:56:22',196,'EXECUTED','3:ce22678978e31f86e6c2b7ba4f9acf86','Create Index','',NULL,'2.0.5'),('1455126553565-292','dus73 (generated)','changelog.groovy','2016-02-10 14:56:22',197,'EXECUTED','3:f0d6e8df19613685ec707b57ba90ba6f','Create Index','',NULL,'2.0.5'),('1455126553565-293','dus73 (generated)','changelog.groovy','2016-02-10 14:56:23',198,'EXECUTED','3:c45fdd4013f2ec9bb5a6778712a4bbdc','Create Index','',NULL,'2.0.5'),('1455126553565-294','dus73 (generated)','changelog.groovy','2016-02-10 14:56:23',199,'EXECUTED','3:9586cfc74b23a9fecdfc9b1cfa5c25ef','Create Index','',NULL,'2.0.5'),('1455126553565-295','dus73 (generated)','changelog.groovy','2016-02-10 14:56:23',200,'EXECUTED','3:11bcdef986dd4233f49afc6a85ca5129','Create Index','',NULL,'2.0.5'),('1455126553565-296','dus73 (generated)','changelog.groovy','2016-02-10 14:56:23',201,'EXECUTED','3:e10170b957a4c2078728a8a1636967b1','Create Index','',NULL,'2.0.5'),('1455126553565-297','dus73 (generated)','changelog.groovy','2016-02-10 14:56:24',202,'EXECUTED','3:b96c4dd4cefc1fbdc35570fad2bdcb42','Create Index','',NULL,'2.0.5'),('1455126553565-298','dus73 (generated)','changelog.groovy','2016-02-10 14:56:24',203,'EXECUTED','3:407d84efc0027f9689ae59dbfb5ccbd4','Create Index','',NULL,'2.0.5'),('1455126553565-299','dus73 (generated)','changelog.groovy','2016-02-10 14:56:24',204,'EXECUTED','3:95bffadc9cfacea99fda4a7806a76331','Create Index','',NULL,'2.0.5'),('1455126553565-3','dus73 (generated)','changelog.groovy','2016-02-10 14:55:50',3,'EXECUTED','3:ff279e527d8da1c01b9e5211b5025597','Create Table','',NULL,'2.0.5'),('1455126553565-30','dus73 (generated)','changelog.groovy','2016-02-10 14:55:54',30,'EXECUTED','3:297e5e195e625b8f50a93f707b504f8d','Create Table','',NULL,'2.0.5'),('1455126553565-300','dus73 (generated)','changelog.groovy','2016-02-10 14:56:24',205,'EXECUTED','3:e9ce8c44f50bf79916a4dffc46831da0','Create Index','',NULL,'2.0.5'),('1455126553565-301','dus73 (generated)','changelog.groovy','2016-02-10 14:56:24',206,'EXECUTED','3:16851b1412f1d2f6b7e88f8e430f1887','Create Index','',NULL,'2.0.5'),('1455126553565-302','dus73 (generated)','changelog.groovy','2016-02-10 14:56:24',207,'EXECUTED','3:a483930dcfa3926eebb3649b0748f361','Create Index','',NULL,'2.0.5'),('1455126553565-303','dus73 (generated)','changelog.groovy','2016-02-10 14:56:25',208,'EXECUTED','3:94d0f4b64e8b7b89b69c618c30766de8','Create Index','',NULL,'2.0.5'),('1455126553565-31','dus73 (generated)','changelog.groovy','2016-02-10 14:55:54',31,'EXECUTED','3:5d70c1ffc95209abf5377d0832890695','Create Table','',NULL,'2.0.5'),('1455126553565-32','dus73 (generated)','changelog.groovy','2016-02-10 14:55:54',32,'EXECUTED','3:a74acad63297139161c26e02a4fad1bd','Create Table','',NULL,'2.0.5'),('1455126553565-33','dus73 (generated)','changelog.groovy','2016-02-10 14:55:54',33,'EXECUTED','3:c7cb4b1cb7a94215e8990d1b822770ee','Create Table','',NULL,'2.0.5'),('1455126553565-34','dus73 (generated)','changelog.groovy','2016-02-10 14:55:54',34,'EXECUTED','3:af493a87158df6a41ae74f182b45e92a','Create Table','',NULL,'2.0.5'),('1455126553565-35','dus73 (generated)','changelog.groovy','2016-02-10 14:55:55',35,'EXECUTED','3:c50e874721ea0ea00b052464c6a0e08e','Create Table','',NULL,'2.0.5'),('1455126553565-36','dus73 (generated)','changelog.groovy','2016-02-10 14:55:55',36,'EXECUTED','3:c033154bee7217651b87d9344c9aa5b8','Create Table','',NULL,'2.0.5'),('1455126553565-37','dus73 (generated)','changelog.groovy','2016-02-10 14:55:55',37,'EXECUTED','3:c027b7a66651069c0992cb073d6375ac','Create Table','',NULL,'2.0.5'),('1455126553565-38','dus73 (generated)','changelog.groovy','2016-02-10 14:55:55',38,'EXECUTED','3:7a5c50f594d3f06d13eeeb096f9800b0','Create Table','',NULL,'2.0.5'),('1455126553565-39','dus73 (generated)','changelog.groovy','2016-02-10 14:55:55',39,'EXECUTED','3:9e41b7935d202b38f8a886c2022d541a','Create Table','',NULL,'2.0.5'),('1455126553565-4','dus73 (generated)','changelog.groovy','2016-02-10 14:55:50',4,'EXECUTED','3:8896858c7646ac9416d9de835199ab38','Create Table','',NULL,'2.0.5'),('1455126553565-40','dus73 (generated)','changelog.groovy','2016-02-10 14:55:55',40,'EXECUTED','3:ddafd12b32d39cecbe36f60c0b3dce9c','Create Table','',NULL,'2.0.5'),('1455126553565-41','dus73 (generated)','changelog.groovy','2016-02-10 14:55:55',41,'EXECUTED','3:8bac758ac7f5ca217cbab533f2d12d05','Create Table','',NULL,'2.0.5'),('1455126553565-42','dus73 (generated)','changelog.groovy','2016-02-10 14:55:56',42,'EXECUTED','3:58138a5db7551743fce6cdfa08726b35','Create Table','',NULL,'2.0.5'),('1455126553565-43','dus73 (generated)','changelog.groovy','2016-02-10 14:55:56',43,'EXECUTED','3:a2a999099db3fba866fdd419d237948b','Create Table','',NULL,'2.0.5'),('1455126553565-44','dus73 (generated)','changelog.groovy','2016-02-10 14:55:56',44,'EXECUTED','3:0379a171e9a3aac39d48b74b72e39eb1','Create Table','',NULL,'2.0.5'),('1455126553565-45','dus73 (generated)','changelog.groovy','2016-02-10 14:55:56',45,'EXECUTED','3:d4bc46e948d5384b78df807a48f3061d','Create Table','',NULL,'2.0.5'),('1455126553565-46','dus73 (generated)','changelog.groovy','2016-02-10 14:55:56',46,'EXECUTED','3:6e7cd3d38a9dfb203f1d1ecf505888f7','Create Table','',NULL,'2.0.5'),('1455126553565-47','dus73 (generated)','changelog.groovy','2016-02-10 14:55:57',47,'EXECUTED','3:3c79cbe0b2d297be21bed30ee53a1ace','Create Table','',NULL,'2.0.5'),('1455126553565-48','dus73 (generated)','changelog.groovy','2016-02-10 14:55:57',48,'EXECUTED','3:a179b03d1b7fa0210ac6114110b04a59','Create Table','',NULL,'2.0.5'),('1455126553565-49','dus73 (generated)','changelog.groovy','2016-02-10 14:55:57',49,'EXECUTED','3:adb5a19acc62c3cce96e777ca78c64e5','Create Table','',NULL,'2.0.5'),('1455126553565-5','dus73 (generated)','changelog.groovy','2016-02-10 14:55:50',5,'EXECUTED','3:2017ac82335da1822945d01e7b75dabc','Create Table','',NULL,'2.0.5'),('1455126553565-50','dus73 (generated)','changelog.groovy','2016-02-10 14:55:57',50,'EXECUTED','3:5b75354c0770c5181c99018a2cfb8c20','Create Table','',NULL,'2.0.5'),('1455126553565-51','dus73 (generated)','changelog.groovy','2016-02-10 14:55:57',51,'EXECUTED','3:ec41b113205ccc87659518988f785e1d','Create Table','',NULL,'2.0.5'),('1455126553565-52','dus73 (generated)','changelog.groovy','2016-02-10 14:55:57',52,'EXECUTED','3:08f992cbc1982177f4869ed6d1154b51','Create Table','',NULL,'2.0.5'),('1455126553565-53','dus73 (generated)','changelog.groovy','2016-02-10 14:55:57',53,'EXECUTED','3:69dc096bad6b60ba671951de88d2648a','Create Table','',NULL,'2.0.5'),('1455126553565-54','dus73 (generated)','changelog.groovy','2016-02-10 14:55:58',54,'EXECUTED','3:d7d154533865add66a51229a20e327da','Create Table','',NULL,'2.0.5'),('1455126553565-55','dus73 (generated)','changelog.groovy','2016-02-10 14:55:58',55,'EXECUTED','3:ef595ad51054b8b04a9d6987505c4b8d','Create Table','',NULL,'2.0.5'),('1455126553565-56','dus73 (generated)','changelog.groovy','2016-02-10 14:55:58',56,'EXECUTED','3:d0ae0b893803b33099caf5290fba7f3d','Create Table','',NULL,'2.0.5'),('1455126553565-57','dus73 (generated)','changelog.groovy','2016-02-10 14:55:58',57,'EXECUTED','3:f356929f60464b29d361d5d60662a83b','Create Table','',NULL,'2.0.5'),('1455126553565-58','dus73 (generated)','changelog.groovy','2016-02-10 14:55:58',58,'EXECUTED','3:2ede13a7fcc58b0c68797cfad472632f','Create Table','',NULL,'2.0.5'),('1455126553565-59','dus73 (generated)','changelog.groovy','2016-02-10 14:55:58',59,'EXECUTED','3:015bd854efedc412652a438e7e963c2b','Create Table','',NULL,'2.0.5'),('1455126553565-6','dus73 (generated)','changelog.groovy','2016-02-10 14:55:51',6,'EXECUTED','3:482132aba3bf914b9d63925d9e681f4f','Create Table','',NULL,'2.0.5'),('1455126553565-60','dus73 (generated)','changelog.groovy','2016-02-10 14:55:59',60,'EXECUTED','3:e3ef959d624767df1fbca686862ad033','Create Table','',NULL,'2.0.5'),('1455126553565-61','dus73 (generated)','changelog.groovy','2016-02-10 14:55:59',61,'EXECUTED','3:c0b82486b352e832ab67bd9301388a1a','Create Table','',NULL,'2.0.5'),('1455126553565-62','dus73 (generated)','changelog.groovy','2016-02-10 14:55:59',62,'EXECUTED','3:1dd76448876f7c05f426001fd1803740','Create Table','',NULL,'2.0.5'),('1455126553565-63','dus73 (generated)','changelog.groovy','2016-02-10 14:55:59',63,'EXECUTED','3:004b8ed199cea0cd5db0f0f79f62f368','Create Table','',NULL,'2.0.5'),('1455126553565-64','dus73 (generated)','changelog.groovy','2016-02-10 14:55:59',64,'EXECUTED','3:5d6a2d1a9b7b3e9ed866fb065d1982e3','Create Table','',NULL,'2.0.5'),('1455126553565-65','dus73 (generated)','changelog.groovy','2016-02-10 14:55:59',65,'EXECUTED','3:72f9de95b6f29b15162510bb5763a931','Create Table','',NULL,'2.0.5'),('1455126553565-66','dus73 (generated)','changelog.groovy','2016-02-10 14:56:00',66,'EXECUTED','3:835ac6478d62ec6c1976196cb51389fe','Create Table','',NULL,'2.0.5'),('1455126553565-67','dus73 (generated)','changelog.groovy','2016-02-10 14:56:00',67,'EXECUTED','3:a1ba1d0fec5721ed504fc540140d2257','Add Primary Key','',NULL,'2.0.5'),('1455126553565-68','dus73 (generated)','changelog.groovy','2016-02-10 14:56:25',209,'EXECUTED','3:4769c1ec6ae1788b6ae3f95e9f20bddf','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-69','dus73 (generated)','changelog.groovy','2016-02-10 14:56:25',210,'EXECUTED','3:9d7a6e64f1149d9c66bb8294c0c02bf8','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-7','dus73 (generated)','changelog.groovy','2016-02-10 14:55:51',7,'EXECUTED','3:00f45beee0c7cc5519673bfba1555db5','Create Table','',NULL,'2.0.5'),('1455126553565-70','dus73 (generated)','changelog.groovy','2016-02-10 14:56:25',211,'EXECUTED','3:6ff64e4b2409b5ad87f94e5bc2e71c9c','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-71','dus73 (generated)','changelog.groovy','2016-02-10 14:56:25',212,'EXECUTED','3:d2e15c61599806c9585fbc6d76ab8c6a','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-72','dus73 (generated)','changelog.groovy','2016-02-10 14:56:26',213,'EXECUTED','3:640c017d63e924af4f0e7d29af18d5c5','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-73','dus73 (generated)','changelog.groovy','2016-02-10 14:56:26',214,'EXECUTED','3:96fd1a8c7d3889923e8a3544636d93ac','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-74','dus73 (generated)','changelog.groovy','2016-02-10 14:56:26',215,'EXECUTED','3:0d845cbb78af1ba57764444feb102beb','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-75','dus73 (generated)','changelog.groovy','2016-02-10 14:56:26',216,'EXECUTED','3:33a3947ba0b2a5a22d957050427002bb','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-76','dus73 (generated)','changelog.groovy','2016-02-10 14:56:26',217,'EXECUTED','3:86033cca8bee1772fda49d641bac50ae','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-77','dus73 (generated)','changelog.groovy','2016-02-10 14:56:26',218,'EXECUTED','3:ef91f986443fc35c496c38321685542f','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-78','dus73 (generated)','changelog.groovy','2016-02-10 14:56:26',219,'EXECUTED','3:4b0f1e68c9d32fc453f1da264d1b29aa','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-79','dus73 (generated)','changelog.groovy','2016-02-10 14:56:27',220,'EXECUTED','3:e9c2bc93b7beb36592ddf81df40fb54c','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-8','dus73 (generated)','changelog.groovy','2016-02-10 14:55:51',8,'EXECUTED','3:e7a51059eb52ca206bae0d0d06d84bb9','Create Table','',NULL,'2.0.5'),('1455126553565-80','dus73 (generated)','changelog.groovy','2016-02-10 14:56:27',221,'EXECUTED','3:0ba1c639c6ce231a0da027add9a4f251','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-81','dus73 (generated)','changelog.groovy','2016-02-10 14:56:27',222,'EXECUTED','3:96029d581daf3af9a4b2e3fd5f859767','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-82','dus73 (generated)','changelog.groovy','2016-02-10 14:56:27',223,'EXECUTED','3:5ae5fd7967ef11b478dbf53b95aa74f4','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-83','dus73 (generated)','changelog.groovy','2016-02-10 14:56:27',224,'EXECUTED','3:d8ab3885874225bef2ca6d83ade3e3ee','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-84','dus73 (generated)','changelog.groovy','2016-02-10 14:56:27',225,'EXECUTED','3:46399da03c22785b9c7091ae983c19bd','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-85','dus73 (generated)','changelog.groovy','2016-02-10 14:56:27',226,'EXECUTED','3:e509cfd55344a06742b29b0c3f6a8fc1','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-86','dus73 (generated)','changelog.groovy','2016-02-10 14:56:28',227,'EXECUTED','3:04ea8ff9dedc06a32716840a671a3e71','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-87','dus73 (generated)','changelog.groovy','2016-02-10 14:56:28',228,'EXECUTED','3:f3ed9717e386296c2909bc1e0aed3ebf','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-88','dus73 (generated)','changelog.groovy','2016-02-10 14:56:28',229,'EXECUTED','3:9629f8179a34bb5c1ce9f469b387ae10','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-89','dus73 (generated)','changelog.groovy','2016-02-10 14:56:28',230,'EXECUTED','3:0c73237d3db9e01a2c7859700099d82c','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-9','dus73 (generated)','changelog.groovy','2016-02-10 14:55:51',9,'EXECUTED','3:a8d4693a72687d696a083cd8b164ed4a','Create Table','',NULL,'2.0.5'),('1455126553565-90','dus73 (generated)','changelog.groovy','2016-02-10 14:56:28',231,'EXECUTED','3:4314f6bcbe7a8779e1ab88bcaf5a9211','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-91','dus73 (generated)','changelog.groovy','2016-02-10 14:56:29',232,'EXECUTED','3:01be416446f295df3d3ea089356db2c9','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-92','dus73 (generated)','changelog.groovy','2016-02-10 14:56:29',233,'EXECUTED','3:f60ee340da7a580403127fdcec329b61','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-93','dus73 (generated)','changelog.groovy','2016-02-10 14:56:29',234,'EXECUTED','3:19fa867f9ddc3c73d8c87c170a276afd','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-94','dus73 (generated)','changelog.groovy','2016-02-10 14:56:29',235,'EXECUTED','3:9cbffee64c04fcfa16d995e9df5de5e6','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-95','dus73 (generated)','changelog.groovy','2016-02-10 14:56:29',236,'EXECUTED','3:0859a9696ed2dae222947947bfa72dd8','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-96','dus73 (generated)','changelog.groovy','2016-02-10 14:56:29',237,'EXECUTED','3:378fb105b0990d9b2f043e32c2076eeb','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-97','dus73 (generated)','changelog.groovy','2016-02-10 14:56:30',238,'EXECUTED','3:3beb6c208040f29caa7bdbbb0c3606ac','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-98','dus73 (generated)','changelog.groovy','2016-02-10 14:56:30',239,'EXECUTED','3:64021c748242c4f7416aad98ff6b7bba','Add Foreign Key Constraint','',NULL,'2.0.5'),('1455126553565-99','dus73 (generated)','changelog.groovy','2016-02-10 14:56:30',240,'EXECUTED','3:e6676110b27e224192ec8d5b296d2f8f','Add Foreign Key Constraint','',NULL,'2.0.5');
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553424` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ab_host`
--

LOCK TABLES `ab_host` WRITE;
/*!40000 ALTER TABLE `ab_host` DISABLE KEYS */;
INSERT INTO `ab_host` VALUES (1,0,'rabbit');
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  UNIQUE KEY `name_uniq_1455126553428` (`name`),
  UNIQUE KEY `short_name_uniq_1455126553429` (`short_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aligner`
--

LOCK TABLES `aligner` WRITE;
/*!40000 ALTER TABLE `aligner` DISABLE KEYS */;
/*!40000 ALTER TABLE `aligner` ENABLE KEYS */;
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
  `concentration` float NOT NULL,
  `external_id` varchar(255) DEFAULT NULL,
  `ig_type_id` bigint(20) DEFAULT NULL,
  `immunogene` varchar(255) DEFAULT NULL,
  `inventory_id` varchar(255) DEFAULT NULL,
  `lot_number` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5bpi6ah0csjh4g0n8ln93wx3h` (`ig_type_id`),
  KEY `FK_qjuxd91ddd77fufu1em86n2gb` (`ab_host_id`),
  KEY `FK_rhc520eoim7txw9d7kqpb90fw` (`company_id`),
  CONSTRAINT `FK_5bpi6ah0csjh4g0n8ln93wx3h` FOREIGN KEY (`ig_type_id`) REFERENCES `ig_type` (`id`),
  CONSTRAINT `FK_qjuxd91ddd77fufu1em86n2gb` FOREIGN KEY (`ab_host_id`) REFERENCES `ab_host` (`id`),
  CONSTRAINT `FK_rhc520eoim7txw9d7kqpb90fw` FOREIGN KEY (`company_id`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `antibody`
--

LOCK TABLES `antibody` WRITE;
/*!40000 ALTER TABLE `antibody` DISABLE KEYS */;
INSERT INTO `antibody` VALUES (1,0,1,'i5006',NULL,1,1,NULL,1,'Protein A',NULL,NULL,'{\"note\":\"IgG coupled to invitrogen Dynabeads\",\"ulSent\":\"3\",\"ulPerChIP\":\"3\"}'),(2,0,1,'07-729',NULL,2,1,NULL,1,'CTCF',NULL,'25117762','{\"ulSent\":\"5\",\"ulPerChIP\":\"5\"}'),(3,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,'{}'),(4,0,1,'i5006',NULL,1,0,NULL,1,'TAP',NULL,NULL,'{\"note\":\"IgG coupled to invitrogen Dynabeads\",\"ulSent\":\"500\",\"ulPerChIP\":\"50\"}');
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553446` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assay`
--

LOCK TABLES `assay` WRITE;
/*!40000 ALTER TABLE `assay` DISABLE KEYS */;
INSERT INTO `assay` VALUES (1,0,'TX'),(2,0,'XO');
/*!40000 ALTER TABLE `assay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_calling`
--

DROP TABLE IF EXISTS `base_calling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_calling` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `base_calling_version` varchar(10) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`base_calling_version`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_calling`
--

LOCK TABLES `base_calling` WRITE;
/*!40000 ALTER TABLE `base_calling` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_calling` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biological_replicate_samples`
--

DROP TABLE IF EXISTS `biological_replicate_samples`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biological_replicate_samples` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sample_id` bigint(20) NOT NULL,
  `set_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_set_id` (`sample_id`,`set_id`),
  KEY `FK_5cfsjvfvqbjtcx7t6ftka65pu` (`set_id`),
  KEY `FK_hlaud14xiftoss51nv2k9mp9n` (`sample_id`),
  CONSTRAINT `FK_5cfsjvfvqbjtcx7t6ftka65pu` FOREIGN KEY (`set_id`) REFERENCES `biological_replicate_set` (`id`),
  CONSTRAINT `FK_hlaud14xiftoss51nv2k9mp9n` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biological_replicate_samples`
--

LOCK TABLES `biological_replicate_samples` WRITE;
/*!40000 ALTER TABLE `biological_replicate_samples` DISABLE KEYS */;
/*!40000 ALTER TABLE `biological_replicate_samples` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biological_replicate_set`
--

DROP TABLE IF EXISTS `biological_replicate_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biological_replicate_set` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_giwv376p7duup0ii9vm5a41vt` (`project_id`),
  CONSTRAINT `FK_giwv376p7duup0ii9vm5a41vt` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biological_replicate_set`
--

LOCK TABLES `biological_replicate_set` WRITE;
/*!40000 ALTER TABLE `biological_replicate_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `biological_replicate_set` ENABLE KEYS */;
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
  `growth_media_id` bigint(20) DEFAULT NULL,
  `histology_id` bigint(20) DEFAULT NULL,
  `inventory_id` bigint(20) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `prep_user_id` bigint(20) DEFAULT NULL,
  `provider_lab_id` bigint(20) DEFAULT NULL,
  `provider_user_id` bigint(20) DEFAULT NULL,
  `sex_id` bigint(20) DEFAULT NULL,
  `strain_id` bigint(20) NOT NULL,
  `tissue_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_318alqytpqfjhixa0xumta27` (`provider_lab_id`),
  KEY `FK_3j4empi718wbs6weggsvx0vxf` (`sex_id`),
  KEY `FK_71fd478uakc4ikgvf7jkqrbic` (`inventory_id`),
  KEY `FK_7i3q0e2js5j13ybv0fpn6wsdj` (`strain_id`),
  KEY `FK_abm2plajs5iecdn4xw32iiq98` (`histology_id`),
  KEY `FK_dyakrl2jrduv34rm8abrpdpea` (`growth_media_id`),
  KEY `FK_fvxkssmfdikktj0688ybb3tbh` (`tissue_id`),
  KEY `FK_js2v7lu898c0p8ortg5xgxb0q` (`provider_user_id`),
  KEY `FK_osoanky7172r4r5skdfatpr2n` (`prep_user_id`),
  CONSTRAINT `FK_318alqytpqfjhixa0xumta27` FOREIGN KEY (`provider_lab_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK_3j4empi718wbs6weggsvx0vxf` FOREIGN KEY (`sex_id`) REFERENCES `sex` (`id`),
  CONSTRAINT `FK_71fd478uakc4ikgvf7jkqrbic` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`),
  CONSTRAINT `FK_7i3q0e2js5j13ybv0fpn6wsdj` FOREIGN KEY (`strain_id`) REFERENCES `strain` (`id`),
  CONSTRAINT `FK_abm2plajs5iecdn4xw32iiq98` FOREIGN KEY (`histology_id`) REFERENCES `histology` (`id`),
  CONSTRAINT `FK_dyakrl2jrduv34rm8abrpdpea` FOREIGN KEY (`growth_media_id`) REFERENCES `growth_media` (`id`),
  CONSTRAINT `FK_fvxkssmfdikktj0688ybb3tbh` FOREIGN KEY (`tissue_id`) REFERENCES `tissue` (`id`),
  CONSTRAINT `FK_js2v7lu898c0p8ortg5xgxb0q` FOREIGN KEY (`provider_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_osoanky7172r4r5skdfatpr2n` FOREIGN KEY (`prep_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cell_source`
--

LOCK TABLES `cell_source` WRITE;
/*!40000 ALTER TABLE `cell_source` DISABLE KEYS */;
INSERT INTO `cell_source` VALUES (1,0,NULL,NULL,1,NULL,1,NULL,3,NULL,3,NULL,2,NULL),(2,0,NULL,NULL,2,NULL,2,NULL,3,NULL,3,NULL,4,NULL),(3,0,NULL,NULL,2,NULL,3,NULL,3,NULL,3,NULL,5,NULL),(4,0,NULL,NULL,2,NULL,4,NULL,3,NULL,3,NULL,6,NULL),(5,0,NULL,NULL,2,NULL,5,NULL,3,NULL,3,NULL,7,NULL),(6,0,NULL,NULL,2,NULL,6,NULL,3,NULL,3,NULL,8,NULL),(7,0,NULL,NULL,2,NULL,7,NULL,3,NULL,3,NULL,9,NULL),(8,0,NULL,NULL,2,NULL,8,NULL,3,NULL,3,NULL,10,NULL),(9,0,NULL,NULL,2,NULL,9,NULL,3,NULL,3,NULL,11,NULL),(10,0,NULL,NULL,1,NULL,10,NULL,7,NULL,4,NULL,13,NULL);
/*!40000 ALTER TABLE `cell_source` ENABLE KEYS */;
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
  `compound` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553449` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cell_source_treatment`
--

LOCK TABLES `cell_source_treatment` WRITE;
/*!40000 ALTER TABLE `cell_source_treatment` DISABLE KEYS */;
INSERT INTO `cell_source_treatment` VALUES (1,0,NULL,NULL,'05_SDS',NULL),(2,0,NULL,NULL,'00_SDS',NULL),(3,0,NULL,NULL,'02_SDS',NULL),(4,0,NULL,NULL,'10_SDS',NULL);
/*!40000 ALTER TABLE `cell_source_treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cell_source_treatments`
--

DROP TABLE IF EXISTS `cell_source_treatments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cell_source_treatments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cell_source_id` bigint(20) NOT NULL,
  `treatment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_cell_source_id` (`treatment_id`,`cell_source_id`),
  KEY `FK_jggm0quygtml4729ct1v2li8q` (`treatment_id`),
  KEY `FK_xht7d6039ax9kbh4gmdceh5k` (`cell_source_id`),
  CONSTRAINT `FK_jggm0quygtml4729ct1v2li8q` FOREIGN KEY (`treatment_id`) REFERENCES `cell_source_treatment` (`id`),
  CONSTRAINT `FK_xht7d6039ax9kbh4gmdceh5k` FOREIGN KEY (`cell_source_id`) REFERENCES `cell_source` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cell_source_treatments`
--

LOCK TABLES `cell_source_treatments` WRITE;
/*!40000 ALTER TABLE `cell_source_treatments` DISABLE KEYS */;
INSERT INTO `cell_source_treatments` VALUES (1,1,1),(4,4,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(2,2,2),(3,3,3),(5,5,4);
/*!40000 ALTER TABLE `cell_source_treatments` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  UNIQUE KEY `name_uniq_1455126553451` (`name`),
  KEY `FK_qtx5yloqyq7hc1efournhys5u` (`genome_id`),
  CONSTRAINT `FK_qtx5yloqyq7hc1efournhys5u` FOREIGN KEY (`genome_id`) REFERENCES `genome` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chromosome`
--

LOCK TABLES `chromosome` WRITE;
/*!40000 ALTER TABLE `chromosome` DISABLE KEYS */;
/*!40000 ALTER TABLE `chromosome` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computing_infrastructure`
--

DROP TABLE IF EXISTS `computing_infrastructure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computing_infrastructure` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553452` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computing_infrastructure`
--

LOCK TABLES `computing_infrastructure` WRITE;
/*!40000 ALTER TABLE `computing_infrastructure` DISABLE KEYS */;
/*!40000 ALTER TABLE `computing_infrastructure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_pipeline`
--

DROP TABLE IF EXISTS `core_pipeline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_pipeline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `base_calling_id` bigint(20) NOT NULL,
  `core_pipeline_version` varchar(10) DEFAULT NULL,
  `data_processing_id` bigint(20) NOT NULL,
  `downstream_analysis_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `peak_finding_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`core_pipeline_version`,`name`),
  KEY `FK_ceyrkr3xuabv7koe9ro018awy` (`downstream_analysis_id`),
  KEY `FK_mpgn6hl6vvbrcq3dd3tp130lx` (`data_processing_id`),
  KEY `FK_qhv857uuhkmcorb4pio2vhpy3` (`base_calling_id`),
  KEY `FK_rm79jw9v0buyydkoy0nv05o52` (`peak_finding_id`),
  CONSTRAINT `FK_ceyrkr3xuabv7koe9ro018awy` FOREIGN KEY (`downstream_analysis_id`) REFERENCES `downstream_analysis` (`id`),
  CONSTRAINT `FK_mpgn6hl6vvbrcq3dd3tp130lx` FOREIGN KEY (`data_processing_id`) REFERENCES `data_processing` (`id`),
  CONSTRAINT `FK_qhv857uuhkmcorb4pio2vhpy3` FOREIGN KEY (`base_calling_id`) REFERENCES `base_calling` (`id`),
  CONSTRAINT `FK_rm79jw9v0buyydkoy0nv05o52` FOREIGN KEY (`peak_finding_id`) REFERENCES `peak_finding` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_pipeline`
--

LOCK TABLES `core_pipeline` WRITE;
/*!40000 ALTER TABLE `core_pipeline` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_pipeline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_processing`
--

DROP TABLE IF EXISTS `data_processing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_processing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553455` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_processing`
--

LOCK TABLES `data_processing` WRITE;
/*!40000 ALTER TABLE `data_processing` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_processing` ENABLE KEYS */;
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
  UNIQUE KEY `name_uniq_1455126553455` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `definition`
--

LOCK TABLES `definition` WRITE;
/*!40000 ALTER TABLE `definition` DISABLE KEYS */;
/*!40000 ALTER TABLE `definition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `downstream_analysis`
--

DROP TABLE IF EXISTS `downstream_analysis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `downstream_analysis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553456` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `downstream_analysis`
--

LOCK TABLES `downstream_analysis` WRITE;
/*!40000 ALTER TABLE `downstream_analysis` DISABLE KEYS */;
/*!40000 ALTER TABLE `downstream_analysis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_metadata`
--

DROP TABLE IF EXISTS `file_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_metadata` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `file_type_id` bigint(20) NOT NULL,
  `insertion_size` int(11) NOT NULL,
  `md5check_sum` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `sequence_alignment_id` bigint(20) NOT NULL,
  `standard_deviation` float NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`file_type_id`,`name`),
  KEY `FK_8ei1cybnbixggmxfhwril3vac` (`file_type_id`),
  KEY `FK_p0dwk23v4mpuph7bvybfimjn9` (`sequence_alignment_id`),
  CONSTRAINT `FK_8ei1cybnbixggmxfhwril3vac` FOREIGN KEY (`file_type_id`) REFERENCES `file_type` (`id`),
  CONSTRAINT `FK_p0dwk23v4mpuph7bvybfimjn9` FOREIGN KEY (`sequence_alignment_id`) REFERENCES `sequence_alignment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_metadata`
--

LOCK TABLES `file_metadata` WRITE;
/*!40000 ALTER TABLE `file_metadata` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_metadata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_type`
--

DROP TABLE IF EXISTS `file_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553458` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_type`
--

LOCK TABLES `file_type` WRITE;
/*!40000 ALTER TABLE `file_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_type` ENABLE KEYS */;
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
  `species_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8qyh94lw1ucpnn89d4dl4e5dr` (`species_id`),
  CONSTRAINT `FK_8qyh94lw1ucpnn89d4dl4e5dr` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genome`
--

LOCK TABLES `genome` WRITE;
/*!40000 ALTER TABLE `genome` DISABLE KEYS */;
INSERT INTO `genome` VALUES (1,0,'sacCer3',1),(2,0,'hg19',2),(3,0,'sg7',1);
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
  `species_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553459` (`name`),
  KEY `FK_d67vskwe5phnwub9cqiokalkx` (`species_id`),
  CONSTRAINT `FK_d67vskwe5phnwub9cqiokalkx` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `growth_media`
--

LOCK TABLES `growth_media` WRITE;
/*!40000 ALTER TABLE `growth_media` DISABLE KEYS */;
INSERT INTO `growth_media` VALUES (1,0,'YPD',1),(2,0,'DMEM',2);
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553459` (`name`),
  KEY `FK_jrq8s9u4ujdq8durk95ilfebo` (`parent_id`),
  CONSTRAINT `FK_jrq8s9u4ujdq8durk95ilfebo` FOREIGN KEY (`parent_id`) REFERENCES `histology` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `log` varchar(255) DEFAULT NULL,
  `object_id` int(11) NOT NULL,
  `object_type` varchar(255) NOT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fej8h317q2acuy144kav1oyxc` (`project_id`),
  KEY `FK_fuutexvtx28fs971iq0kbfbmp` (`user_id`),
  CONSTRAINT `FK_fej8h317q2acuy144kav1oyxc` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_fuutexvtx28fs971iq0kbfbmp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553461` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ig_type`
--

LOCK TABLES `ig_type` WRITE;
/*!40000 ALTER TABLE `ig_type` DISABLE KEYS */;
INSERT INTO `ig_type` VALUES (1,0,'IgG');
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
  `notes` varchar(255) DEFAULT NULL,
  `receiving_user_id` bigint(20) DEFAULT NULL,
  `source_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mnah8ybq73nyl126do8agmk08` (`receiving_user_id`),
  CONSTRAINT `FK_mnah8ybq73nyl126do8agmk08` FOREIGN KEY (`receiving_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,0,'2015-06-10 00:00:00',NULL,3,'INTERNAL'),(2,0,'2015-06-10 00:00:00',NULL,3,'INTERNAL'),(3,0,'2015-06-10 00:00:00',NULL,3,'INTERNAL'),(4,0,'2015-06-10 00:00:00',NULL,3,'INTERNAL'),(5,0,'2015-06-10 00:00:00',NULL,3,'INTERNAL'),(6,0,'2015-06-10 00:00:00',NULL,3,'INTERNAL'),(7,0,'2015-06-10 00:00:00',NULL,3,'INTERNAL'),(8,0,'2015-06-10 00:00:00',NULL,3,'INTERNAL'),(9,0,'2015-06-10 00:00:00',NULL,3,'INTERNAL'),(10,0,'2015-05-07 00:00:00',NULL,6,'EXTERNAL');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,0,'2015-06-15 00:00:00','150615','mjr26'),(2,0,NULL,'S003','cdk01');
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
  `barcode` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `reference_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_barcode` (`type_id`,`barcode`),
  KEY `FK_ccldfsomwnlcfqys42su71de3` (`parent_id`),
  KEY `FK_qxnbu16tlqfmub9pgfj3h2e41` (`type_id`),
  CONSTRAINT `FK_ccldfsomwnlcfqys42su71de3` FOREIGN KEY (`parent_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_qxnbu16tlqfmub9pgfj3h2e41` FOREIGN KEY (`type_id`) REFERENCES `item_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_bags`
--

DROP TABLE IF EXISTS `item_bags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_bags` (
  `item_id` bigint(20) NOT NULL,
  `protocol_instance_bag_id` bigint(20) NOT NULL,
  `bags_idx` int(11) DEFAULT NULL,
  KEY `FK_8cx51q97y1kfc9xpv1uxokl3a` (`protocol_instance_bag_id`),
  KEY `FK_n2qqb9a3kp6jjh4um4vl1g0jg` (`item_id`),
  CONSTRAINT `FK_8cx51q97y1kfc9xpv1uxokl3a` FOREIGN KEY (`protocol_instance_bag_id`) REFERENCES `protocol_instance_bag` (`id`),
  CONSTRAINT `FK_n2qqb9a3kp6jjh4um4vl1g0jg` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_bags`
--

LOCK TABLES `item_bags` WRITE;
/*!40000 ALTER TABLE `item_bags` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_bags` ENABLE KEYS */;
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
  `object_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553466` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_type`
--

LOCK TABLES `item_type` WRITE;
/*!40000 ALTER TABLE `item_type` DISABLE KEYS */;
INSERT INTO `item_type` VALUES (1,0,NULL,'Cell Source','CellSource'),(2,0,NULL,'Antibody','Antibody'),(3,0,NULL,'Sample','Sample');
/*!40000 ALTER TABLE `item_type` ENABLE KEYS */;
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
  `website` varchar(255) DEFAULT NULL,
  `class` varchar(255) NOT NULL,
  `billing_contact_id` bigint(20) DEFAULT NULL,
  `pi_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553467` (`name`),
  KEY `FK_57byxcy430qbl2gl7liup0py1` (`parent_id`),
  KEY `FK_6w4r8gx2jdvy193esigbpxxx6` (`billing_contact_id`),
  KEY `FK_l070gahmlj4g2sqbm72btw64e` (`address_id`),
  KEY `FK_shncwjk67uss09ivcrpvlv8xg` (`pi_id`),
  CONSTRAINT `FK_57byxcy430qbl2gl7liup0py1` FOREIGN KEY (`parent_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK_6w4r8gx2jdvy193esigbpxxx6` FOREIGN KEY (`billing_contact_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_l070gahmlj4g2sqbm72btw64e` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_shncwjk67uss09ivcrpvlv8xg` FOREIGN KEY (`pi_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` VALUES (1,0,NULL,'Sigma',NULL,NULL,NULL,'pegr.Company',NULL,NULL),(2,0,NULL,'Millipore',NULL,NULL,NULL,'pegr.Company',NULL,NULL);
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peak_finding`
--

DROP TABLE IF EXISTS `peak_finding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `peak_finding` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553468` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peak_finding`
--

LOCK TABLES `peak_finding` WRITE;
/*!40000 ALTER TABLE `peak_finding` DISABLE KEYS */;
/*!40000 ALTER TABLE `peak_finding` ENABLE KEYS */;
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
  `funding` varchar(255) DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553469` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `assay_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `details` longtext,
  `name` varchar(255) NOT NULL,
  `protocol_version` varchar(10) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`protocol_version`,`name`),
  KEY `FK_f2u9282jbe9eiy0fggmrvrt16` (`assay_id`),
  KEY `FK_mpt4h4wrqjngorh2706rvsqk2` (`user_id`),
  CONSTRAINT `FK_f2u9282jbe9eiy0fggmrvrt16` FOREIGN KEY (`assay_id`) REFERENCES `assay` (`id`),
  CONSTRAINT `FK_mpt4h4wrqjngorh2706rvsqk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol`
--

LOCK TABLES `protocol` WRITE;
/*!40000 ALTER TABLE `protocol` DISABLE KEYS */;
INSERT INTO `protocol` VALUES (1,0,NULL,NULL,NULL,'TX','1',NULL),(2,0,NULL,NULL,NULL,'XO','2.9.2',NULL);
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
  UNIQUE KEY `name_uniq_1455126553471` (`name`),
  KEY `FK_mm7fvjt84dbnbsnsbe9ik36xh` (`user_id`),
  CONSTRAINT `FK_mm7fvjt84dbnbsnsbe9ik36xh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol_group`
--

LOCK TABLES `protocol_group` WRITE;
/*!40000 ALTER TABLE `protocol_group` DISABLE KEYS */;
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
  `protocols_idx` int(11) DEFAULT NULL,
  KEY `FK_6i86p4hqvh803hqeam63pf0fo` (`protocol_id`),
  KEY `FK_6sjm1imt3f4b8rd492wm9vt7e` (`protocol_group_id`),
  CONSTRAINT `FK_6i86p4hqvh803hqeam63pf0fo` FOREIGN KEY (`protocol_id`) REFERENCES `protocol` (`id`),
  CONSTRAINT `FK_6sjm1imt3f4b8rd492wm9vt7e` FOREIGN KEY (`protocol_group_id`) REFERENCES `protocol_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol_group_protocols`
--

LOCK TABLES `protocol_group_protocols` WRITE;
/*!40000 ALTER TABLE `protocol_group_protocols` DISABLE KEYS */;
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
  PRIMARY KEY (`id`),
  KEY `FK_d4v22o39k3xu8m216w8r1nyx1` (`protocol_id`),
  KEY `FK_j952d9eb9yjue4b2xggu8ehib` (`bag_id`),
  KEY `FK_sgj3vhj59cxyb10md7ybwmtm7` (`user_id`),
  CONSTRAINT `FK_d4v22o39k3xu8m216w8r1nyx1` FOREIGN KEY (`protocol_id`) REFERENCES `protocol` (`id`),
  CONSTRAINT `FK_j952d9eb9yjue4b2xggu8ehib` FOREIGN KEY (`bag_id`) REFERENCES `protocol_instance_bag` (`id`),
  CONSTRAINT `FK_sgj3vhj59cxyb10md7ybwmtm7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `super_bag_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_issvbm80chseeymqst1rurxxd` (`super_bag_id`),
  KEY `FK_r1uxlmgfs4nieck8kad4wo79q` (`protocol_group_id`),
  CONSTRAINT `FK_issvbm80chseeymqst1rurxxd` FOREIGN KEY (`super_bag_id`) REFERENCES `protocol_instance_bag` (`id`),
  CONSTRAINT `FK_r1uxlmgfs4nieck8kad4wo79q` FOREIGN KEY (`protocol_group_id`) REFERENCES `protocol_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_protocol_instance_id` (`item_id`,`protocol_instance_id`),
  KEY `FK_2ehrfoqmnyg7wc3sv0owshi8l` (`item_id`),
  KEY `FK_dmt3slumwfqinb5qicjdvrilr` (`protocol_instance_id`),
  CONSTRAINT `FK_2ehrfoqmnyg7wc3sv0owshi8l` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_dmt3slumwfqinb5qicjdvrilr` FOREIGN KEY (`protocol_instance_id`) REFERENCES `protocol_instance` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol_instance_summary`
--

LOCK TABLES `protocol_instance_summary` WRITE;
/*!40000 ALTER TABLE `protocol_instance_summary` DISABLE KEYS */;
INSERT INTO `protocol_instance_summary` VALUES (1,0,'2015-06-10 00:00:00','{\"resin\":\"IgG coupled Dynabeads\",\"PCRCycle\":\"18\"}',1,'2015-06-10 00:00:00',3),(2,0,'2015-06-10 00:00:00','{\"resin\":\"ProA MagSeph\",\"PCRCycle\":\"18\"}',1,'2015-06-10 00:00:00',3),(3,0,'2015-06-10 00:00:00','{\"resin\":\"ProA MagSeph\",\"PCRCycle\":\"18\"}',1,'2015-06-10 00:00:00',3),(4,0,'2015-06-10 00:00:00','{\"resin\":\"ProA MagSeph\",\"PCRCycle\":\"18\"}',1,'2015-06-10 00:00:00',3),(5,0,'2015-06-10 00:00:00','{\"resin\":\"ProA MagSeph\",\"PCRCycle\":\"18\"}',1,'2015-06-10 00:00:00',3),(6,0,'2015-06-10 00:00:00','{\"resin\":\"ProA MagSeph\",\"PCRCycle\":\"18\"}',1,'2015-06-10 00:00:00',3),(7,0,'2015-06-10 00:00:00','{\"resin\":\"ProA MagSeph\",\"PCRCycle\":\"18\"}',1,'2015-06-10 00:00:00',3),(8,0,'2015-06-10 00:00:00','{\"resin\":\"ProA MagSeph\",\"PCRCycle\":\"18\"}',1,'2015-06-10 00:00:00',3),(9,0,'2015-06-10 00:00:00','{\"resin\":\"ProA MagSeph\",\"PCRCycle\":\"18\"}',1,'2015-06-10 00:00:00',3),(10,0,'2015-05-04 00:00:00','{\"resin\":\"IgG Dynabeads\",\"PCRCycle\":\"18\"}',2,'2015-05-04 00:00:00',8);
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
  UNIQUE KEY `unique_protocol_id` (`item_type_id`,`protocol_id`),
  KEY `FK_mi8fvx3awmigchnxb1s8keinu` (`item_type_id`),
  KEY `FK_p1p3edd8llir9a0qp24sj5cql` (`protocol_id`),
  CONSTRAINT `FK_mi8fvx3awmigchnxb1s8keinu` FOREIGN KEY (`item_type_id`) REFERENCES `item_type` (`id`),
  CONSTRAINT `FK_p1p3edd8llir9a0qp24sj5cql` FOREIGN KEY (`protocol_id`) REFERENCES `protocol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol_item_types`
--

LOCK TABLES `protocol_item_types` WRITE;
/*!40000 ALTER TABLE `protocol_item_types` DISABLE KEYS */;
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
  UNIQUE KEY `name_uniq_1455126553476` (`name`),
  UNIQUE KEY `short_name_uniq_1455126553477` (`short_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `read_type`
--

LOCK TABLES `read_type` WRITE;
/*!40000 ALTER TABLE `read_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `read_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requestmap`
--

DROP TABLE IF EXISTS `requestmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requestmap` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `config_attribute` varchar(255) NOT NULL,
  `http_method` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_url` (`http_method`,`url`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requestmap`
--

LOCK TABLES `requestmap` WRITE;
/*!40000 ALTER TABLE `requestmap` DISABLE KEYS */;
/*!40000 ALTER TABLE `requestmap` ENABLE KEYS */;
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
  UNIQUE KEY `authority_uniq_1455126553478` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,0,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
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
  `cell_number` int(11) DEFAULT NULL,
  `cell_source_id` bigint(20) DEFAULT NULL,
  `chromosome_amount` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `invoice_id` bigint(20) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `prtcl_inst_summary_id` bigint(20) DEFAULT NULL,
  `publication_reference` varchar(255) DEFAULT NULL,
  `requested_tag_number` int(11) DEFAULT NULL,
  `send_data_to_id` bigint(20) DEFAULT NULL,
  `spike_in_cell_source_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `target_id` bigint(20) DEFAULT NULL,
  `volume` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4lb993olybjsa6bqlbt90as3c` (`antibody_id`),
  KEY `FK_a1ro7uobsoyc87a51ssw7n5sw` (`cell_source_id`),
  KEY `FK_bcdouyjpgn9hqcd947o1f28jq` (`prtcl_inst_summary_id`),
  KEY `FK_cfxg067kqgsk55rjibh2pbnso` (`target_id`),
  KEY `FK_iab90bxgihv46lqg7ib51kmwn` (`spike_in_cell_source_id`),
  KEY `FK_psm0kim6hte7fy0577fwsbdi` (`send_data_to_id`),
  KEY `FK_tqw4ka9uvmywhb4gw0ib7nxy9` (`invoice_id`),
  CONSTRAINT `FK_4lb993olybjsa6bqlbt90as3c` FOREIGN KEY (`antibody_id`) REFERENCES `antibody` (`id`),
  CONSTRAINT `FK_a1ro7uobsoyc87a51ssw7n5sw` FOREIGN KEY (`cell_source_id`) REFERENCES `cell_source` (`id`),
  CONSTRAINT `FK_bcdouyjpgn9hqcd947o1f28jq` FOREIGN KEY (`prtcl_inst_summary_id`) REFERENCES `protocol_instance_summary` (`id`),
  CONSTRAINT `FK_cfxg067kqgsk55rjibh2pbnso` FOREIGN KEY (`target_id`) REFERENCES `target` (`id`),
  CONSTRAINT `FK_iab90bxgihv46lqg7ib51kmwn` FOREIGN KEY (`spike_in_cell_source_id`) REFERENCES `cell_source` (`id`),
  CONSTRAINT `FK_psm0kim6hte7fy0577fwsbdi` FOREIGN KEY (`send_data_to_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_tqw4ka9uvmywhb4gw0ib7nxy9` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample`
--

LOCK TABLES `sample` WRITE;
/*!40000 ALTER TABLE `sample` DISABLE KEYS */;
INSERT INTO `sample` VALUES (1,1,1,720,1,0,'2015-06-15 00:00:00',1,NULL,1,NULL,5,3,NULL,'COMPLETED',1,600),(2,1,2,10,2,0,'2015-06-15 00:00:00',1,NULL,2,NULL,20,3,NULL,'COMPLETED',2,600),(3,1,NULL,10,3,0,'2015-06-15 00:00:00',1,NULL,3,NULL,20,3,NULL,'COMPLETED',2,600),(4,1,NULL,10,4,0,'2015-06-15 00:00:00',1,NULL,4,NULL,20,3,NULL,'COMPLETED',2,600),(5,1,NULL,10,5,0,'2015-06-15 00:00:00',1,NULL,5,NULL,20,3,NULL,'COMPLETED',2,600),(6,1,NULL,20,6,0,'2015-06-15 00:00:00',1,NULL,6,NULL,20,3,NULL,'COMPLETED',2,600),(7,1,NULL,5,7,0,'2015-06-15 00:00:00',1,NULL,7,NULL,20,3,NULL,'COMPLETED',2,600),(8,1,NULL,2,8,0,'2015-06-15 00:00:00',1,NULL,8,NULL,20,3,NULL,'COMPLETED',2,600),(9,1,3,10,9,0,'2015-06-15 00:00:00',1,NULL,9,NULL,20,3,NULL,'COMPLETED',3,600),(10,1,4,1500,10,0,'2015-06-22 00:00:00',2,'Sonicated chromatin in FA buffer +0.1% SDS (no Triton); higher priority',10,NULL,15,5,NULL,'COMPLETED',4,50);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample_in_run`
--

LOCK TABLES `sample_in_run` WRITE;
/*!40000 ALTER TABLE `sample_in_run` DISABLE KEYS */;
INSERT INTO `sample_in_run` VALUES (1,0,'{\"concentration\":\"2\",\"quibitReading\":\"10\",\"quibitDilution\":\"200\"}',NULL,NULL,1,1,0),(2,0,'{\"concentration\":\"2\",\"quibitReading\":\"10\",\"quibitDilution\":\"200\"}',NULL,NULL,1,2,0),(3,0,'{\"concentration\":\"2\",\"quibitReading\":\"10\",\"quibitDilution\":\"200\"}',NULL,NULL,1,3,0),(4,0,'{\"concentration\":\"2\",\"quibitReading\":\"10\",\"quibitDilution\":\"200\"}',NULL,NULL,1,4,0),(5,0,'{\"concentration\":\"2\",\"quibitReading\":\"10\",\"quibitDilution\":\"200\"}',NULL,NULL,1,5,0),(6,0,'{\"concentration\":\"2\",\"quibitReading\":\"10\",\"quibitDilution\":\"200\"}',NULL,NULL,1,6,0),(7,0,'{\"concentration\":\"2\",\"quibitReading\":\"10\",\"quibitDilution\":\"200\"}',NULL,NULL,1,7,0),(8,0,'{\"concentration\":\"2\",\"quibitReading\":\"10\",\"quibitDilution\":\"200\"}',NULL,NULL,1,8,0),(9,0,'{\"concentration\":\"2\",\"quibitReading\":\"10\",\"quibitDilution\":\"200\"}',NULL,NULL,1,9,0),(10,0,'{}',NULL,'2015-06-22 00:00:00',2,10,0);
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
  `sample_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sample_id` (`index_id`,`sample_id`),
  KEY `FK_flne19kx2tprlhpbxh9a3xk8r` (`index_id`),
  KEY `FK_qh0akbm1a3893iwxbwqps9xmn` (`sample_id`),
  CONSTRAINT `FK_flne19kx2tprlhpbxh9a3xk8r` FOREIGN KEY (`index_id`) REFERENCES `sequence_index` (`id`),
  CONSTRAINT `FK_qh0akbm1a3893iwxbwqps9xmn` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample_sequence_indices`
--

LOCK TABLES `sample_sequence_indices` WRITE;
/*!40000 ALTER TABLE `sample_sequence_indices` DISABLE KEYS */;
/*!40000 ALTER TABLE `sample_sequence_indices` ENABLE KEYS */;
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
  `core_pipeline_id` bigint(20) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `file_paths` varchar(1000) DEFAULT NULL,
  `genome_id` bigint(20) NOT NULL,
  `is_preferred` bit(1) NOT NULL,
  `params` varchar(2000) DEFAULT NULL,
  `read_db_id` int(11) DEFAULT NULL,
  `sequencing_experiment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5tdpednm424bwlyaygxb91cj8` (`genome_id`),
  KEY `FK_8gcis40hwme6s7ywn4jikodd2` (`align_type_id`),
  KEY `FK_fee47iwmejna7h55jp8dpkowd` (`aligner_id`),
  KEY `FK_hc4f4br571qwgavrswy22w5wo` (`core_pipeline_id`),
  KEY `FK_js0whth80fyna834brwbbo8v0` (`sequencing_experiment_id`),
  CONSTRAINT `FK_5tdpednm424bwlyaygxb91cj8` FOREIGN KEY (`genome_id`) REFERENCES `genome` (`id`),
  CONSTRAINT `FK_8gcis40hwme6s7ywn4jikodd2` FOREIGN KEY (`align_type_id`) REFERENCES `align_type` (`id`),
  CONSTRAINT `FK_fee47iwmejna7h55jp8dpkowd` FOREIGN KEY (`aligner_id`) REFERENCES `aligner` (`id`),
  CONSTRAINT `FK_hc4f4br571qwgavrswy22w5wo` FOREIGN KEY (`core_pipeline_id`) REFERENCES `core_pipeline` (`id`),
  CONSTRAINT `FK_js0whth80fyna834brwbbo8v0` FOREIGN KEY (`sequencing_experiment_id`) REFERENCES `sequencing_experiment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence_alignment`
--

LOCK TABLES `sequence_alignment` WRITE;
/*!40000 ALTER TABLE `sequence_alignment` DISABLE KEYS */;
INSERT INTO `sequence_alignment` VALUES (1,0,NULL,NULL,NULL,'2016-02-10 15:25:52',NULL,1,'\0',NULL,NULL,1),(2,0,NULL,NULL,NULL,'2016-02-10 15:25:53',NULL,2,'\0',NULL,NULL,2),(3,0,NULL,NULL,NULL,'2016-02-10 15:25:53',NULL,2,'\0',NULL,NULL,3),(4,0,NULL,NULL,NULL,'2016-02-10 15:25:53',NULL,2,'\0',NULL,NULL,4),(5,0,NULL,NULL,NULL,'2016-02-10 15:25:53',NULL,2,'\0',NULL,NULL,5),(6,0,NULL,NULL,NULL,'2016-02-10 15:25:53',NULL,2,'\0',NULL,NULL,6),(7,0,NULL,NULL,NULL,'2016-02-10 15:25:53',NULL,2,'\0',NULL,NULL,7),(8,0,NULL,NULL,NULL,'2016-02-10 15:25:53',NULL,2,'\0',NULL,NULL,8),(9,0,NULL,NULL,NULL,'2016-02-10 15:25:53',NULL,2,'\0',NULL,NULL,9),(10,0,NULL,NULL,NULL,'2016-02-10 15:25:53',NULL,1,'\0',NULL,NULL,10),(11,0,NULL,NULL,NULL,'2016-02-10 15:25:53',NULL,3,'\0',NULL,NULL,10);
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
  `index_id` int(11) NOT NULL,
  `index_version` varchar(10) NOT NULL,
  `oligo` varchar(255) DEFAULT NULL,
  `sequence` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index_id` (`index_version`,`index_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence_index`
--

LOCK TABLES `sequence_index` WRITE;
/*!40000 ALTER TABLE `sequence_index` DISABLE KEYS */;
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
  `pool` varchar(255) DEFAULT NULL,
  `run_num` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gerfjuiu4n7m0iukeud2wrux7` (`user_id`),
  KEY `FK_l1ofj1csp7k439e52l32soay9` (`platform_id`),
  CONSTRAINT `FK_gerfjuiu4n7m0iukeud2wrux7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_l1ofj1csp7k439e52l32soay9` FOREIGN KEY (`platform_id`) REFERENCES `sequencing_platform` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence_run`
--

LOCK TABLES `sequence_run` WRITE;
/*!40000 ALTER TABLE `sequence_run` DISABLE KEYS */;
INSERT INTO `sequence_run` VALUES (1,0,'2015-06-15 00:00:00',NULL,'H7JNFBGXX',0,NULL,4,NULL,581,NULL),(2,0,'2015-06-22 00:00:00',NULL,'H7JH7BGXX',0,NULL,4,NULL,582,NULL);
/*!40000 ALTER TABLE `sequence_run` ENABLE KEYS */;
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
  `file_paths` varchar(500) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `number_reads` int(11) DEFAULT NULL,
  `public_db_id` varchar(255) DEFAULT NULL,
  `read_positions` varchar(255) DEFAULT NULL,
  `read_type_id` bigint(20) DEFAULT NULL,
  `sample_id` bigint(20) NOT NULL,
  `seq_id` varchar(255) DEFAULT NULL,
  `sequence_run_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3mx03u80x4ynhsx1i65yrrxcq` (`sequence_run_id`),
  KEY `FK_3s9e144d345uyhay28toy8jwg` (`read_type_id`),
  KEY `FK_7gsumwrx4h3nji2g2q5htn098` (`sample_id`),
  CONSTRAINT `FK_3mx03u80x4ynhsx1i65yrrxcq` FOREIGN KEY (`sequence_run_id`) REFERENCES `sequence_run` (`id`),
  CONSTRAINT `FK_3s9e144d345uyhay28toy8jwg` FOREIGN KEY (`read_type_id`) REFERENCES `read_type` (`id`),
  CONSTRAINT `FK_7gsumwrx4h3nji2g2q5htn098` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequencing_experiment`
--

LOCK TABLES `sequencing_experiment` WRITE;
/*!40000 ALTER TABLE `sequencing_experiment` DISABLE KEYS */;
INSERT INTO `sequencing_experiment` VALUES (1,0,NULL,NULL,NULL,NULL,'{\"rd1\":[\"1\",\"40\"],\"index\":[\"41\",\"52\"],\"rd2\":[\"53\",\"92\"]}',NULL,1,'58156',1),(2,0,NULL,NULL,NULL,NULL,'{\"rd1\":[\"1\",\"40\"],\"index\":[\"41\",\"52\"],\"rd2\":[\"53\",\"92\"]}',NULL,2,'58157',1),(3,0,NULL,NULL,NULL,NULL,'{\"rd1\":[\"1\",\"40\"],\"index\":[\"41\",\"52\"],\"rd2\":[\"53\",\"92\"]}',NULL,3,'58158',1),(4,0,NULL,NULL,NULL,NULL,'{\"rd1\":[\"1\",\"40\"],\"index\":[\"41\",\"52\"],\"rd2\":[\"53\",\"92\"]}',NULL,4,'58159',1),(5,0,NULL,NULL,NULL,NULL,'{\"rd1\":[\"1\",\"40\"],\"index\":[\"41\",\"52\"],\"rd2\":[\"53\",\"92\"]}',NULL,5,'58160',1),(6,0,NULL,NULL,NULL,NULL,'{\"rd1\":[\"1\",\"40\"],\"index\":[\"41\",\"52\"],\"rd2\":[\"53\",\"92\"]}',NULL,6,'58161',1),(7,0,NULL,NULL,NULL,NULL,'{\"rd1\":[\"1\",\"40\"],\"index\":[\"41\",\"52\"],\"rd2\":[\"53\",\"92\"]}',NULL,7,'58162',1),(8,0,NULL,NULL,NULL,NULL,'{\"rd1\":[\"1\",\"40\"],\"index\":[\"41\",\"52\"],\"rd2\":[\"53\",\"92\"]}',NULL,8,'58163',1),(9,0,NULL,NULL,NULL,NULL,'{\"rd1\":[\"1\",\"40\"],\"index\":[\"41\",\"52\"],\"rd2\":[\"53\",\"92\"]}',NULL,9,'58164',1),(10,0,NULL,NULL,NULL,NULL,'{\"rd1\":[\"1\",\"40\"],\"index\":[\"41\",\"52\"],\"rd2\":[\"53\",\"92\"]}',NULL,10,'58201',2);
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
  UNIQUE KEY `name_uniq_1455126553486` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequencing_platform`
--

LOCK TABLES `sequencing_platform` WRITE;
/*!40000 ALTER TABLE `sequencing_platform` DISABLE KEYS */;
INSERT INTO `sequencing_platform` VALUES (1,0,'SOLiD'),(2,0,'Illumina GA'),(3,0,'HiSeq 2000'),(4,0,'NextSeq 500');
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553487` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553487` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
INSERT INTO `species` VALUES (1,0,'Saccharomyces','cerevisiae',NULL),(2,0,'Homo','sapiens',NULL);
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
  `background_strain_id` bigint(20) DEFAULT NULL,
  `genetic_modification` varchar(255) DEFAULT NULL,
  `genotype` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `source_lab_id` bigint(20) DEFAULT NULL,
  `species_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4shdjcf8qcywolsnrmi3djhmu` (`parent_id`),
  KEY `FK_9fvgan0aoydiscvx11nhdhn98` (`background_strain_id`),
  KEY `FK_bd07vlra9sn9vs8po73rw89wg` (`source_lab_id`),
  KEY `FK_c5gb45n5okxh7bqig1nouw76t` (`species_id`),
  CONSTRAINT `FK_4shdjcf8qcywolsnrmi3djhmu` FOREIGN KEY (`parent_id`) REFERENCES `strain` (`id`),
  CONSTRAINT `FK_9fvgan0aoydiscvx11nhdhn98` FOREIGN KEY (`background_strain_id`) REFERENCES `strain` (`id`),
  CONSTRAINT `FK_bd07vlra9sn9vs8po73rw89wg` FOREIGN KEY (`source_lab_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK_c5gb45n5okxh7bqig1nouw76t` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strain`
--

LOCK TABLES `strain` WRITE;
/*!40000 ALTER TABLE `strain` DISABLE KEYS */;
INSERT INTO `strain` VALUES (1,0,NULL,NULL,NULL,'BY4741',NULL,NULL,NULL,1),(2,0,NULL,NULL,NULL,NULL,NULL,1,NULL,1),(3,0,NULL,NULL,NULL,'K562',NULL,NULL,NULL,2),(4,0,NULL,NULL,NULL,NULL,NULL,3,NULL,2),(5,0,NULL,NULL,NULL,NULL,NULL,3,NULL,2),(6,0,NULL,NULL,NULL,NULL,NULL,3,NULL,2),(7,0,NULL,NULL,NULL,NULL,NULL,3,NULL,2),(8,0,NULL,NULL,NULL,NULL,NULL,3,NULL,2),(9,0,NULL,NULL,NULL,NULL,NULL,3,NULL,2),(10,0,NULL,NULL,NULL,NULL,NULL,3,NULL,2),(11,0,NULL,NULL,NULL,NULL,NULL,3,NULL,2),(12,0,NULL,NULL,NULL,'S288C',NULL,NULL,NULL,1),(13,0,12,'RPB1-WT','MATa his3200 ura3-52 leu21 or 0 met150 trp163 lys2-128 gal1056 rpb1::CLONAT Ssl2-TAP::HIS3',NULL,NULL,12,NULL,1);
/*!40000 ALTER TABLE `strain` ENABLE KEYS */;
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
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `target_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`c_term_tag`,`n_term_tag`,`name`),
  KEY `FK_e5wo7j1f9so6mcll9vwtn6005` (`target_type_id`),
  CONSTRAINT `FK_e5wo7j1f9so6mcll9vwtn6005` FOREIGN KEY (`target_type_id`) REFERENCES `target_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `target`
--

LOCK TABLES `target` WRITE;
/*!40000 ALTER TABLE `target` DISABLE KEYS */;
INSERT INTO `target` VALUES (1,0,NULL,NULL,'NoTag',NULL,NULL),(2,0,NULL,NULL,'CTCF',NULL,NULL),(3,0,NULL,NULL,'NoAb',NULL,NULL),(4,0,'TAP',NULL,'Ssl2',NULL,1);
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
  UNIQUE KEY `name_uniq_1455126553490` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `target_type`
--

LOCK TABLES `target_type` WRITE;
/*!40000 ALTER TABLE `target_type` DISABLE KEYS */;
INSERT INTO `target_type` VALUES (1,0,NULL,'S1');
/*!40000 ALTER TABLE `target_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `computing_infrastructure_id` bigint(20) NOT NULL,
  `core_pipe_line_id` bigint(20) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `input_file_paths` varchar(255) DEFAULT NULL,
  `job_id` varchar(255) NOT NULL,
  `output_file_paths` varchar(255) DEFAULT NULL,
  `script_file_path` varchar(255) NOT NULL,
  `start_time` datetime NOT NULL,
  `status` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2ovu0g1rssdjbpgb6fj0l2vwy` (`computing_infrastructure_id`),
  KEY `FK_4fmjedju7b35tb5cr71n3ntb0` (`user_id`),
  KEY `FK_89y9w7ykrtfvr9quf0acepu0k` (`core_pipe_line_id`),
  CONSTRAINT `FK_2ovu0g1rssdjbpgb6fj0l2vwy` FOREIGN KEY (`computing_infrastructure_id`) REFERENCES `computing_infrastructure` (`id`),
  CONSTRAINT `FK_4fmjedju7b35tb5cr71n3ntb0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_89y9w7ykrtfvr9quf0acepu0k` FOREIGN KEY (`core_pipe_line_id`) REFERENCES `core_pipeline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technical_replicate_samples`
--

DROP TABLE IF EXISTS `technical_replicate_samples`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technical_replicate_samples` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sample_id` bigint(20) NOT NULL,
  `set_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_set_id` (`sample_id`,`set_id`),
  KEY `FK_bxc886ht20h9dyioee4mvtuhg` (`set_id`),
  KEY `FK_pgfytkmfc7o8hujxpm27nrupx` (`sample_id`),
  CONSTRAINT `FK_bxc886ht20h9dyioee4mvtuhg` FOREIGN KEY (`set_id`) REFERENCES `technical_replicate_set` (`id`),
  CONSTRAINT `FK_pgfytkmfc7o8hujxpm27nrupx` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technical_replicate_samples`
--

LOCK TABLES `technical_replicate_samples` WRITE;
/*!40000 ALTER TABLE `technical_replicate_samples` DISABLE KEYS */;
/*!40000 ALTER TABLE `technical_replicate_samples` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technical_replicate_set`
--

DROP TABLE IF EXISTS `technical_replicate_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technical_replicate_set` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9qiur0k1ju7xinurh5sy074fr` (`project_id`),
  CONSTRAINT `FK_9qiur0k1ju7xinurh5sy074fr` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technical_replicate_set`
--

LOCK TABLES `technical_replicate_set` WRITE;
/*!40000 ALTER TABLE `technical_replicate_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `technical_replicate_set` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq_1455126553493` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tissue`
--

LOCK TABLES `tissue` WRITE;
/*!40000 ALTER TABLE `tissue` DISABLE KEYS */;
/*!40000 ALTER TABLE `tissue` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_uniq_1455126553495` (`username`),
  KEY `FK_dhlcfg8h1drrgu0irs1ro3ohb` (`address_id`),
  KEY `FK_qdusuaq6oge31t7nlq10wm6ku` (`affiliation_id`),
  CONSTRAINT `FK_dhlcfg8h1drrgu0irs1ro3ohb` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_qdusuaq6oge31t7nlq10wm6ku` FOREIGN KEY (`affiliation_id`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,0,'\0','\0',NULL,NULL,NULL,'',NULL,'$2a$10$W/90F6RvGWz1tHxqjQAklOgRNChtFwkMTuSTxumqTnYkfzVs/bweG','\0',NULL,'labadmin'),(2,0,'\0','\0',NULL,NULL,'bfp2@psu.edu','','Pugh, Frank','$2a$10$W/90F6RvGWz1tHxqjQAklOgRNChtFwkMTuSTxumqTnYkfzVs/bweG','\0',NULL,'bfp2'),(3,0,'\0','\0',NULL,NULL,'mjr26@psu.edu','','Rossi, Matthew','$2a$10$W/90F6RvGWz1tHxqjQAklOgRNChtFwkMTuSTxumqTnYkfzVs/bweG','\0','5709946197','mjr26'),(4,0,'\0','\0',NULL,NULL,'huiyan@tamu.edu','','Jin, Huiyan','$2a$10$W/90F6RvGWz1tHxqjQAklOgRNChtFwkMTuSTxumqTnYkfzVs/bweG','\0','9798450429','huiyan'),(5,0,'\0','\0',NULL,NULL,'cdkaplan@tamu.edu','','Kaplan, Craig','$2a$10$W/90F6RvGWz1tHxqjQAklOgRNChtFwkMTuSTxumqTnYkfzVs/bweG','\0',NULL,'cdkaplan'),(6,0,'\0','\0',NULL,NULL,NULL,'','Farrell, Nina','$2a$10$W/90F6RvGWz1tHxqjQAklOgRNChtFwkMTuSTxumqTnYkfzVs/bweG','\0',NULL,'fn100'),(7,0,'\0','\0',NULL,NULL,NULL,'','Jin, H','$2a$10$W/90F6RvGWz1tHxqjQAklOgRNChtFwkMTuSTxumqTnYkfzVs/bweG','\0',NULL,'jh100'),(8,0,'\0','\0',NULL,NULL,NULL,'',NULL,'$2a$10$W/90F6RvGWz1tHxqjQAklOgRNChtFwkMTuSTxumqTnYkfzVs/bweG','\0',NULL,'npf5017');
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-10 15:27:21
