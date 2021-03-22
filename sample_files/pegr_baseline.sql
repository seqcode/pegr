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
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
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
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('1580764544352-1','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:51',1,'EXECUTED','8:7300da3e68cf1926d7924cf402364f72','createTable tableName=ab_host','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-2','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:51',2,'EXECUTED','8:c50f809a6ec5d05b76668bd4b2fce439','createTable tableName=address','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-3','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:51',3,'EXECUTED','8:e6b78b423cac57d76901bc898d617b0e','createTable tableName=align_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-4','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:51',4,'EXECUTED','8:c677e3448869d131ea3eb78d6921678e','createTable tableName=aligner','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-5','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:51',5,'EXECUTED','8:ccc4411824f853afd87b44265cba54f9','createTable tableName=analysis','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-6','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:51',6,'EXECUTED','8:a4ae196c7262639b005ba41ad652933a','createTable tableName=antibody','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-7','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:51',7,'EXECUTED','8:97e8c4142a954cd286debe82192f2b0e','createTable tableName=assay','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-8','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:51',8,'EXECUTED','8:50c86dd292ff57c743be3797b2f01e87','createTable tableName=batch_cell_sources','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-9','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:51',9,'EXECUTED','8:d66553c04705131e9ebeb48a40240878','createTable tableName=cell_source','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-10','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',10,'EXECUTED','8:5543220c3feaf6919a151229cf2a2cae','createTable tableName=cell_source_batch','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-11','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',11,'EXECUTED','8:d4868259a5507ccb56603bec814b10df','createTable tableName=cell_source_treatment','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-12','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',12,'EXECUTED','8:b17f7fec81da1195b30ae4e14b0d3672','createTable tableName=chores','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-13','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',13,'EXECUTED','8:01d7d2770382940313ffa0172086f629','createTable tableName=chrom_sequence','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-14','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',14,'EXECUTED','8:f23812808d1cfcc45827d6e0412eeb56','createTable tableName=chromosome','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-15','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',15,'EXECUTED','8:1367c9d2b419674419a131c2324c0fe0','createTable tableName=control_sample','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-16','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',16,'EXECUTED','8:751fa61c17baa762e00c2eb51ccfdb25','createTable tableName=definition','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-17','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',17,'EXECUTED','8:2462b8863a89c2acd7c6f39a5db8662c','createTable tableName=funding','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-18','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',18,'EXECUTED','8:961e3120e23e7ff36a349ef43695fdaf','createTable tableName=genome','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-19','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',19,'EXECUTED','8:20c5b4767a441835de36e04262bf9e42','createTable tableName=growth_media','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-20','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',20,'EXECUTED','8:3bf6ecb5ff5858af71b1f9ee5aa1e4c8','createTable tableName=histology','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-21','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',21,'EXECUTED','8:e8b39dc757b2f08a20c9f2c7b327eb8d','createTable tableName=history','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-22','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',22,'EXECUTED','8:eeb5b31cc9a7539d48bb8aaeb6872893','createTable tableName=ig_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-23','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',23,'EXECUTED','8:bcd27c274f84dcb58ea9a86be2eb04a4','createTable tableName=inventory','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-24','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',24,'EXECUTED','8:bdc3ec2fbb355270510500f2f7a8f817','createTable tableName=invoice','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-25','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',25,'EXECUTED','8:a913aa4c773268ee0dee598b5a55bef2','createTable tableName=item','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-26','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',26,'EXECUTED','8:7aba465d038ee89e7cd86fac7436ebb1','createTable tableName=item_antibody','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-27','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',27,'EXECUTED','8:b7a389692f89e6cba6f16a2f3074ce1e','createTable tableName=item_sequence_indices','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-28','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',28,'EXECUTED','8:a5afba94060cc8c0249bd5c56c10fc31','createTable tableName=item_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-29','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',29,'EXECUTED','8:44d1aa9fa2657917c70e748cbcba27dd','createTable tableName=item_type_category','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-30','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',30,'EXECUTED','8:9debd724090a9b345fe6da3dadbad03e','createTable tableName=organization','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-31','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',31,'EXECUTED','8:e212aca255942b7d7fa21d138feff7bb','createTable tableName=pipeline','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-32','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',32,'EXECUTED','8:1f9bd32747f167621bf1f62cb9d0407e','createTable tableName=pool_samples','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-33','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',33,'EXECUTED','8:ae33c4ef0f802f4a9e14e9fded38267a','createTable tableName=project','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-34','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',34,'EXECUTED','8:8f8852bc84cc2bbdb234f760422c2fb8','createTable tableName=project_bags','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-35','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',35,'EXECUTED','8:8ef5fec78093f4aaaebfef7573e24679','createTable tableName=project_funding','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-36','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',36,'EXECUTED','8:e89532589d5fe13d81a6d3348e80525b','createTable tableName=project_samples','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-37','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',37,'EXECUTED','8:9b40eec18ef26cafec8c51fae40bc5a2','createTable tableName=project_user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-38','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:52',38,'EXECUTED','8:988610d4c395f1a4798c872bd9a5967e','createTable tableName=protocol','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-39','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',39,'EXECUTED','8:e0be8ba6fcdb9951f21cfb07d8615201','createTable tableName=protocol_group','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-40','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',40,'EXECUTED','8:536887a4f9305e0bac78b0769a5ba96c','createTable tableName=protocol_group_protocols','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-41','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',41,'EXECUTED','8:e6c9cec2a28ff312ecf2ef4e7c11c29b','createTable tableName=protocol_instance','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-42','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',42,'EXECUTED','8:ca8e6cb0a19900ac7e355e23193ff5a2','createTable tableName=protocol_instance_bag','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-43','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',43,'EXECUTED','8:a8415d5b650e31c364faf8853e9ab783','createTable tableName=protocol_instance_items','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-44','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',44,'EXECUTED','8:ef4a980a23f4ad6007bb1d985cd49989','createTable tableName=protocol_instance_summary','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-45','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',45,'EXECUTED','8:cb4c0f3c04fef437d20aac9eedcd49be','createTable tableName=protocol_item_types','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-46','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',46,'EXECUTED','8:db0415894c2d1488496804adac468154','createTable tableName=read_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-47','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',47,'EXECUTED','8:d2d096a31928818ec401d00f8e49278f','createTable tableName=replicate_samples','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-48','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',48,'EXECUTED','8:ee795edcb15ba2bad823234642c766e0','createTable tableName=replicate_set','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-49','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',49,'EXECUTED','8:a5e555191ece91663aec1bd1cb4e528d','createTable tableName=report_alignments','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-50','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',50,'EXECUTED','8:d98f1f191189f135d62602990e7ddb32','createTable tableName=role','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-51','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',51,'EXECUTED','8:c2eb7705294021215384f180db9c96fd','createTable tableName=role_group','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-52','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',52,'EXECUTED','8:30b930611b059e4ddf0fb6f8301f0a06','createTable tableName=role_group_role','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-53','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',53,'EXECUTED','8:087974db8d8a17a6ae063988d694052d','createTable tableName=run_stats','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-54','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',54,'EXECUTED','8:0ccf21d0341999ddedeb2df54093f7cc','createTable tableName=sample','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-55','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',55,'EXECUTED','8:b710a17e7f53976cdcc67771771282a9','createTable tableName=sample_in_run','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-56','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',56,'EXECUTED','8:0ea9315da1aca97e486d23041b870d84','createTable tableName=sample_sequence_indices','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-57','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',57,'EXECUTED','8:7be70664055b59b9850996b85fdfa4b6','createTable tableName=sample_treatments','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-58','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',58,'EXECUTED','8:3617b94b4af5cbead6dbbb5ea4280295','createTable tableName=sequence_alignment','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-59','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',59,'EXECUTED','8:b7ee176648c454a0185409c8ea94c4f1','createTable tableName=sequence_index','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-60','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',60,'EXECUTED','8:ef23f2114c0f29b8b8adf856313632bc','createTable tableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-61','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',61,'EXECUTED','8:af23f75a0fd357072b1a8a32cd7500df','createTable tableName=sequencing_cohort','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-62','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',62,'EXECUTED','8:2311f725da9719b10478312619deeb96','createTable tableName=sequencing_experiment','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-63','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',63,'EXECUTED','8:da8ef34f7844bc77ed0241c960b1e034','createTable tableName=sequencing_platform','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-64','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',64,'EXECUTED','8:88f9b01cdfc57f348c32249162e1040b','createTable tableName=sex','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-65','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',65,'EXECUTED','8:7d488fabf1827cff083802cc319e5a6d','createTable tableName=species','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-66','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',66,'EXECUTED','8:216ee06f704287dfe48d3e3233e66757','createTable tableName=strain','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-67','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:53',67,'EXECUTED','8:a7f847bd61e35c4326ec78b328862599','createTable tableName=summary_report','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-68','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',68,'EXECUTED','8:77514f1dc663293ce40f281c375cf59e','createTable tableName=target','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-69','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',69,'EXECUTED','8:fe21686eb7515b2e02b0251b3f197097','createTable tableName=target_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-70','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',70,'EXECUTED','8:8ce651ab5d7cef0a117a0312df8a0cd4','createTable tableName=tissue','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-71','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',71,'EXECUTED','8:f589188aef0197181fcefa8de05545af','createTable tableName=token','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-72','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',72,'EXECUTED','8:77eb644bf5a199ea6470996128793b83','createTable tableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-73','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',73,'EXECUTED','8:bc393321ff0633db20714833999b406d','createTable tableName=user_role','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-74','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',74,'EXECUTED','8:b2ba40e71e82347d46e045c0b11d20c9','createTable tableName=user_role_group','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-75','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',75,'EXECUTED','8:57bde8f523161c8f0788a6fecefbb1f0','addUniqueConstraint constraintName=UC_AB_HOSTNAME_COL, tableName=ab_host','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-76','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',76,'EXECUTED','8:990e23cf760c725a9e2f8b419ff91795','addUniqueConstraint constraintName=UC_ALIGN_TYPENAME_COL, tableName=align_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-77','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',77,'EXECUTED','8:53e5dc66b3ac981d36170742537d2395','addUniqueConstraint constraintName=UC_ALIGN_TYPESHORT_NAME_COL, tableName=align_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-78','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',78,'EXECUTED','8:23af7b9e9b84424597b7b4778a4670a5','addUniqueConstraint constraintName=UC_ASSAYNAME_COL, tableName=assay','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-79','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',79,'EXECUTED','8:bda66e3edceb5c22de3d4bd7776838d8','addUniqueConstraint constraintName=UC_CELL_SOURCE_TREATMENTNAME_COL, tableName=cell_source_treatment','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-80','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',80,'EXECUTED','8:c72ba9a028370959b8d9556ff7ccd606','addUniqueConstraint constraintName=UC_CHORESNAME_COL, tableName=chores','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-81','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',81,'EXECUTED','8:c05426646ca0b2932cd859662959f3cc','addUniqueConstraint constraintName=UC_CHROMOSOMENAME_COL, tableName=chromosome','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-82','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',82,'EXECUTED','8:c1ba283cf38ce4cb3738442046aea49b','addUniqueConstraint constraintName=UC_DEFINITIONNAME_COL, tableName=definition','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-83','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',83,'EXECUTED','8:58e4847a3cb398d43ae42f0917387cae','addUniqueConstraint constraintName=UC_GROWTH_MEDIANAME_COL, tableName=growth_media','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-84','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',84,'EXECUTED','8:b1f2a8e869c755b9af455b7f8975c651','addUniqueConstraint constraintName=UC_HISTOLOGYNAME_COL, tableName=histology','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-85','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',85,'EXECUTED','8:b372c46838e13e8ec5fd22b951c71c8f','addUniqueConstraint constraintName=UC_IG_TYPENAME_COL, tableName=ig_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-86','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',86,'EXECUTED','8:ff41db1713644f36cc8057067e3243ab','addUniqueConstraint constraintName=UC_ITEMBARCODE_COL, tableName=item','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-87','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',87,'EXECUTED','8:c69ed7c6101b90e6a3582aac440a241f','addUniqueConstraint constraintName=UC_ITEM_TYPENAME_COL, tableName=item_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-88','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',88,'EXECUTED','8:2c31b039e6f5d377628138bb9b00eb06','addUniqueConstraint constraintName=UC_ITEM_TYPE_CATEGORYNAME_COL, tableName=item_type_category','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-89','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',89,'EXECUTED','8:5f40eb9d7f0544b76ed3416bbe19d66b','addUniqueConstraint constraintName=UC_ORGANIZATIONNAME_COL, tableName=organization','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-90','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',90,'EXECUTED','8:671751aef5ac174afdd2ad8e9484d3ef','addUniqueConstraint constraintName=UC_PIPELINEWORKFLOW_ID_COL, tableName=pipeline','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-91','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',91,'EXECUTED','8:31087754826d3f8a25bc9a328fc0d9fb','addUniqueConstraint constraintName=UC_PROJECTNAME_COL, tableName=project','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-92','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',92,'EXECUTED','8:0e9aed0ec2a008e552352fc51f083daa','addUniqueConstraint constraintName=UC_PROTOCOL_GROUPNAME_COL, tableName=protocol_group','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-93','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',93,'EXECUTED','8:e3d4cf5ab973eb2237fc849f2b57b6b2','addUniqueConstraint constraintName=UC_READ_TYPENAME_COL, tableName=read_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-94','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',94,'EXECUTED','8:e0c3b089507419847b3d595975d1348d','addUniqueConstraint constraintName=UC_READ_TYPESHORT_NAME_COL, tableName=read_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-95','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',95,'EXECUTED','8:d427b1cfc330c83c9e56ab2e1ae78fbb','addUniqueConstraint constraintName=UC_ROLEAUTHORITY_COL, tableName=role','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-96','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:54',96,'EXECUTED','8:c675fe0c837bdb7afe90e34fc5227904','addUniqueConstraint constraintName=UC_ROLE_GROUPNAME_COL, tableName=role_group','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-97','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',97,'EXECUTED','8:1af1c028da938591dcfebbe65a7d7360','addUniqueConstraint constraintName=UC_SEQUENCING_PLATFORMNAME_COL, tableName=sequencing_platform','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-98','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',98,'EXECUTED','8:904d80804b6d5288d8412c4bdb047ba9','addUniqueConstraint constraintName=UC_SEXNAME_COL, tableName=sex','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-99','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',99,'EXECUTED','8:531a0563f5fd61c86873b8e8974d385c','addUniqueConstraint constraintName=UC_TARGET_TYPENAME_COL, tableName=target_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-100','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',100,'EXECUTED','8:74b7087c422ca449ca16e477f30924ef','addUniqueConstraint constraintName=UC_TISSUENAME_COL, tableName=tissue','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-101','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',101,'EXECUTED','8:7d6f99505a893a0dc7d6f2d7a120a9f1','addUniqueConstraint constraintName=UC_USERUSERNAME_COL, tableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-102','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',102,'EXECUTED','8:5c89d8aa95234ed67f4433c3a94e19fd','addUniqueConstraint constraintName=UK16c0fb5f244d0452a2e49aed563f, tableName=project_funding','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-103','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',103,'EXECUTED','8:30b7788f58d1c3320f5074d792fd2f5d','addUniqueConstraint constraintName=UK1a92d74db3df102928b5f605132a, tableName=control_sample','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-104','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',104,'EXECUTED','8:ee62dd252c99cc45e44506aaffc4af8e','addUniqueConstraint constraintName=UK3932af47b0357d2b4c5a261822ee, tableName=species','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-105','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',105,'EXECUTED','8:0d8ccead57ebc7ec2040434646f9cc07','addUniqueConstraint constraintName=UK413d0a510ff03d1f19c83af9ae36, tableName=protocol_group_protocols','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-106','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',106,'EXECUTED','8:a98bf02d01801aaa6a13fc509cc26a92','addUniqueConstraint constraintName=UK4b14a8d38495c6eec56d6123f6f5, tableName=sample_treatments','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-107','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',107,'EXECUTED','8:7561419356916b73f44688f2f3580305','addUniqueConstraint constraintName=UK68b1ef05059e7f9742c5997e6f74, tableName=replicate_samples','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-108','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',108,'EXECUTED','8:ea2194664c50e13871746a7454832918','addUniqueConstraint constraintName=UK69c2d8246f1bc2e5e8d02ada96db, tableName=protocol_item_types','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-109','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',109,'EXECUTED','8:b0bb0c4a8a1bd01feb99bc6e7a9fe0fe','addUniqueConstraint constraintName=UK8d22b11cbab7be2f38b4c8c2dd1d, tableName=protocol','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-110','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',110,'EXECUTED','8:bfd341dc7ff1244c921237c94e5f9b2a','addUniqueConstraint constraintName=UK8f7674b6c279c21eb5c882b40655, tableName=sample_in_run','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-111','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',111,'EXECUTED','8:594caebc09b3f29167086a5a67f98f57','addUniqueConstraint constraintName=UKb4d526519821b65515d64f1ac54b, tableName=project_bags','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-112','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',112,'EXECUTED','8:aa78c944ffd9aadd918dee7540a817d0','addUniqueConstraint constraintName=UKb8ab847080355581b293e4a87bcb, tableName=aligner','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-113','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',113,'EXECUTED','8:d46d0e06ac24970fcfe263848a029e3a','addUniqueConstraint constraintName=UKba7773a538d4770149b421f84583, tableName=pool_samples','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-114','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',114,'EXECUTED','8:0fc3018ed146e516a0d106fcd45ced2a','addUniqueConstraint constraintName=UKc01a900836fd7e6df0dbdc887877, tableName=target','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-115','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',115,'EXECUTED','8:27717ff424c839062d784bc3f8e5c0b5','addUniqueConstraint constraintName=UKc9cafa65fdeb3f03632764f330f8, tableName=funding','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-116','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',116,'EXECUTED','8:3b91e7857d55f4127fac8d565b2616d2','addUniqueConstraint constraintName=UKdc9d49ed85d397cd09bb505033ce, tableName=report_alignments','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-117','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',117,'EXECUTED','8:752fe044e76269fa22d2a688e0e22262','addUniqueConstraint constraintName=UKeac55f11282de8304404741a9798, tableName=item_antibody','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-118','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',118,'EXECUTED','8:e2f8b3c57160a60ddcae2d09e48e6f7b','addUniqueConstraint constraintName=UKef3609f74c61b7bd7d0ec184c0b0, tableName=sequence_alignment','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-119','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',119,'EXECUTED','8:2c4ec9f52e68b598f9c3e528f06d5c8f','addUniqueConstraint constraintName=UKf01a4847e4bdb6a7b8198123f4ae, tableName=pipeline','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-120','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',120,'EXECUTED','8:1625119a5c9bff90e004f2ea5b71cbed','addUniqueConstraint constraintName=UKf38f5325c5da49e7b9b5d51d5226, tableName=project_samples','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-121','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',121,'EXECUTED','8:d8a9c8643a7e8db907e8351e10f1b67e','addUniqueConstraint constraintName=UKfbf64ee15963f7e643e3a681061d, tableName=project_user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-122','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',122,'EXECUTED','8:221b769f88aaec3c528136dbce645ad0','addForeignKeyConstraint baseTableName=cell_source_batch, constraintName=FK14vul503srgn35l05e3v1yt51, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-123','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',123,'EXECUTED','8:d5e422843e71265996044dbf1b3b1faf','addForeignKeyConstraint baseTableName=pool_samples, constraintName=FK1elc3dx8od4b22mfnj7ems7oa, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-124','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',124,'EXECUTED','8:cbbbd907fd88e785ac90171ec276026b','addForeignKeyConstraint baseTableName=protocol_group_protocols, constraintName=FK1iuch12ayvha1jsq3r49e8e2j, referencedTableName=protocol_group','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-125','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',125,'EXECUTED','8:01279f89f56dbdd3f6beb074268e2448','addForeignKeyConstraint baseTableName=sample, constraintName=FK1rdptfp7u4obmb8eikuw5nnrf, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-126','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:55',126,'EXECUTED','8:e532b467a6d132d4dab456d60a6b5857','addForeignKeyConstraint baseTableName=genome, constraintName=FK1v0f049a0ljh0ohpc585xwb10, referencedTableName=species','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-127','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',127,'EXECUTED','8:33a818f2c148cb1901a462e4dc54bfa3','addForeignKeyConstraint baseTableName=sequencing_experiment, constraintName=FK2a04ld6c4th452p48yqrqdhlq, referencedTableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-128','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',128,'EXECUTED','8:4482a79fe732bb81f26b6a5b12cd39ff','addForeignKeyConstraint baseTableName=sample, constraintName=FK2hvogurfkgktbxxvdo2csoa8v, referencedTableName=antibody','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-129','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',129,'EXECUTED','8:f087f37b8736ab19903c2aa3a2821d8f','addForeignKeyConstraint baseTableName=sequencing_cohort, constraintName=FK2kjc8ji0k7cxldfqvqqmr6qpq, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-130','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',130,'EXECUTED','8:977c4bb1e530a2341f206f8e5c647a0d','addForeignKeyConstraint baseTableName=strain, constraintName=FK2ouetcuudlbctcmfse7fuuuo8, referencedTableName=strain','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-131','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',131,'EXECUTED','8:19c9829259f481492dd873790175be12','addForeignKeyConstraint baseTableName=protocol, constraintName=FK2ugvxqbj45ie8pfijctcp5m44, referencedTableName=assay','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-132','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',132,'EXECUTED','8:7869b2f585a92601be2cae56aab34bbf','addForeignKeyConstraint baseTableName=antibody, constraintName=FK2wi5vsoyxjfxl0mc5v1mm9dts, referencedTableName=target','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-133','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',133,'EXECUTED','8:938d25314c5a3a29fa05596b5f0de151','addForeignKeyConstraint baseTableName=project_bags, constraintName=FK2xobskjd8bp5b128rh1duijt0, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-134','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',134,'EXECUTED','8:b21bbe65c0b94649ff24231af8e2e97e','addForeignKeyConstraint baseTableName=report_alignments, constraintName=FK33tihrorftk27epehcm1be8ci, referencedTableName=sequence_alignment','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-135','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',135,'EXECUTED','8:d3cf6be4d664eff82f48af81e23dda91','addForeignKeyConstraint baseTableName=antibody, constraintName=FK38l2x1xyohqre04awha89rr15, referencedTableName=ig_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-136','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',136,'EXECUTED','8:07dcfe4d5c9700e14e10e98ce158d032','addForeignKeyConstraint baseTableName=protocol_instance, constraintName=FK3kt03fn9v9icvw7iam3ri2q2e, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-137','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',137,'EXECUTED','8:042aeccb03c96f969d8423d9811923d0','addForeignKeyConstraint baseTableName=project_funding, constraintName=FK3sab04b4896qhtxtm9kmwb6p3, referencedTableName=funding','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-138','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',138,'EXECUTED','8:124704a66e5c1df79db3700462e141fb','addForeignKeyConstraint baseTableName=cell_source, constraintName=FK49xxhx356gij34jl4uxnwknyw, referencedTableName=organization','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-139','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',139,'EXECUTED','8:7f660e5d88bdb3355c6485f4961778ab','addForeignKeyConstraint baseTableName=project_user, constraintName=FK4jl2o131jivd80xsuw6pivnbx, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-140','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',140,'EXECUTED','8:576241ca3ab54379e1ed47e9e9e6d4a2','addForeignKeyConstraint baseTableName=user_role_group, constraintName=FK4u9p3asv1hflckgxegcafajkm, referencedTableName=role_group','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-141','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',141,'EXECUTED','8:e2e12d0879dab96928fb57cc3e141edc','addForeignKeyConstraint baseTableName=project_user, constraintName=FK4ug72llnm0n7yafwntgdswl3y, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-142','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:56',142,'EXECUTED','8:f6d830a9a5b613170806fc45cdd4599a','addForeignKeyConstraint baseTableName=sample_in_run, constraintName=FK50pk7cu1ix56x21n43qpmc9rs, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-143','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',143,'EXECUTED','8:c22f6bdea27bf7eae888ed6da7facb97','addForeignKeyConstraint baseTableName=protocol_instance_items, constraintName=FK521y62ybe1gxfnde38pvohr14, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-144','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',144,'EXECUTED','8:5ae774b15088113a566de04c576d9919','addForeignKeyConstraint baseTableName=sample, constraintName=FK579rlyfhja2i87deo6dgndfnj, referencedTableName=cell_source','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-145','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',145,'EXECUTED','8:20ccca54d94f957c913c84bd1aaa5090','addForeignKeyConstraint baseTableName=sequence_run, constraintName=FK5gbjvf2tscq7vyhwbadq2jbtx, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-146','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',146,'EXECUTED','8:15f12e3c9415bc0ddd7cf464a5819387','addForeignKeyConstraint baseTableName=control_sample, constraintName=FK5luc1hgx7j675npfvs5k3jngq, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-147','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',147,'EXECUTED','8:f90848bc247d0bb31f254d75a7dff008','addForeignKeyConstraint baseTableName=strain, constraintName=FK6ikjjjvxicw58055xdk37tfj0, referencedTableName=species','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-148','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',148,'EXECUTED','8:6266d51f55a306761517e1b802bda933','addForeignKeyConstraint baseTableName=protocol_instance_summary, constraintName=FK6vn6v7g4c33vcd6pja47o9lxm, referencedTableName=protocol','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-149','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',149,'EXECUTED','8:50477e9df773cc248ca0bbf62caecb29','addForeignKeyConstraint baseTableName=organization, constraintName=FK6xw1bsvdkcf10rvj1feetks4p, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-150','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',150,'EXECUTED','8:61c8c23790a42322909fddcce6ea6ed9','addForeignKeyConstraint baseTableName=target, constraintName=FK7fd5o5g240p415qfh8ojmj9bo, referencedTableName=target_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-151','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',151,'EXECUTED','8:b11af0ac78231d324bba1ae0edededcb','addForeignKeyConstraint baseTableName=user_role, constraintName=FK859n2jvi8ivhui0rl0esws6o, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-152','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',152,'EXECUTED','8:7a714f1b0102c62370fd3f6a571540d7','addForeignKeyConstraint baseTableName=cell_source, constraintName=FK868cjl2jx1t1enrfst5ce1un9, referencedTableName=strain','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-153','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',153,'EXECUTED','8:1545659cc99a3ca3716081019ec5f7d5','addForeignKeyConstraint baseTableName=sample_sequence_indices, constraintName=FK8dhe3xyoc3br0fr7uws3svyds, referencedTableName=sequence_index','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-154','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',154,'EXECUTED','8:055a433bd76e0c7e6140cdb7b7f8516e','addForeignKeyConstraint baseTableName=protocol_instance_items, constraintName=FK8pjccf4a3c9n2ja8k9cq6v38p, referencedTableName=protocol_instance','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-155','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',155,'EXECUTED','8:dee084f27fb06247a6295f8dbc5df120','addForeignKeyConstraint baseTableName=report_alignments, constraintName=FK8ui05vjl12r7jwq8bdye2jsig, referencedTableName=summary_report','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-156','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',156,'EXECUTED','8:1929ad8e1a476b24ec58d9fb43c3e191','addForeignKeyConstraint baseTableName=project_samples, constraintName=FK95dx7fkv6r65xk5r9qcng8cin, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-157','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',157,'EXECUTED','8:5a586e76adeee9d709629c17ed2ecc18','addForeignKeyConstraint baseTableName=organization, constraintName=FK97eigsvq2tsrd2bge4ox651wh, referencedTableName=address','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-158','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',158,'EXECUTED','8:42605c0b6d98f36e29e13730c065aa8d','addForeignKeyConstraint baseTableName=batch_cell_sources, constraintName=FK99x1hjei6ogdcxm1w4fxvfr6q, referencedTableName=cell_source','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-159','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:57',159,'EXECUTED','8:c337388e6175d2671bb90873534e4ae4','addForeignKeyConstraint baseTableName=cell_source, constraintName=FK9bw1i4huctjx4330y93axijm1, referencedTableName=tissue','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-160','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',160,'EXECUTED','8:dca93cc0c78b243178779449cc830326','addForeignKeyConstraint baseTableName=project_funding, constraintName=FK9l2tp6kpxf1us967h9092rsu3, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-161','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',161,'EXECUTED','8:edd07cd691777ae894d0588cd047f117','addForeignKeyConstraint baseTableName=user_role, constraintName=FKa68196081fvovjhkek5m97n3y, referencedTableName=role','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-162','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',162,'EXECUTED','8:5769eb93f8c30b5cf8c3e5e137c500ab','addForeignKeyConstraint baseTableName=chromosome, constraintName=FKam69crakd25kjy7d2rqiy5108, referencedTableName=genome','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-163','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',163,'EXECUTED','8:77df3c31e3dd3003a198b6261a6c55ca','addForeignKeyConstraint baseTableName=run_stats, constraintName=FKar55v5cio0nmrqv7opgg1n8cl, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-164','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',164,'EXECUTED','8:574b0585f524d5117fe9612052f7ef8b','addForeignKeyConstraint baseTableName=project_bags, constraintName=FKb4e1wnuhpvapbhnyjs6dqk8mo, referencedTableName=protocol_instance_bag','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-165','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',165,'EXECUTED','8:b8651c3ed970fb7dd7680fc3e908f8d1','addForeignKeyConstraint baseTableName=protocol_item_types, constraintName=FKb5ck29pggprrfleshlkhx5qr8, referencedTableName=item_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-166','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',166,'EXECUTED','8:16c7577159d877d21fcac61967e49ffe','addForeignKeyConstraint baseTableName=replicate_set, constraintName=FKbesib3k7784q0fr1a2si9y576, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-167','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',167,'EXECUTED','8:ad5f8feb9581f1d8018bdd3b846424e1','addForeignKeyConstraint baseTableName=role_group_role, constraintName=FKbo8cffthrm8wxgtnhg0i8dcxw, referencedTableName=role','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-168','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',168,'EXECUTED','8:83a19185ced91c62b015e9b9b745029f','addForeignKeyConstraint baseTableName=sample, constraintName=FKbpci4r4ynak9f0hby2pmaw0bo, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-169','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',169,'EXECUTED','8:3f0fbc6d7123fd85f1d76e318653885a','addForeignKeyConstraint baseTableName=organization, constraintName=FKc30yedjwp9qw1f3nn2ytda7tj, referencedTableName=organization','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-170','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',170,'EXECUTED','8:73f92cc12bfb4510a53b9e827323c9ce','addForeignKeyConstraint baseTableName=sequencing_experiment, constraintName=FKcb969swv4c6xbdb3kw93txww1, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-171','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',171,'EXECUTED','8:fffa7ffe1e7133bd26cac11a8373a318','addForeignKeyConstraint baseTableName=batch_cell_sources, constraintName=FKcjurv5d0t0u6yx5tl79fh0ka4, referencedTableName=cell_source_batch','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-172','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',172,'EXECUTED','8:161075f6df87d970d990bf8452f2eb76','addForeignKeyConstraint baseTableName=sample, constraintName=FKcpc4hhf4ntlq02q576nu2ky44, referencedTableName=assay','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-173','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',173,'EXECUTED','8:6287090ef5b33d5ebc5809f586d67759','addForeignKeyConstraint baseTableName=user, constraintName=FKddefmvbrws3hvl5t0hnnsv8ox, referencedTableName=address','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-174','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',174,'EXECUTED','8:483bccc50eb97b2d756f456b82225988','addForeignKeyConstraint baseTableName=cell_source, constraintName=FKdspbw8rsnb9h8bttdb5a4qyq, referencedTableName=inventory','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-175','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:58',175,'EXECUTED','8:a597656af612a2af9627be7923bec254','addForeignKeyConstraint baseTableName=sequence_alignment, constraintName=FKdvg4g4a0pby4puygnvyof5cu4, referencedTableName=sequencing_experiment','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-176','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',176,'EXECUTED','8:8085461eba9aaa0f6855a52ce4549bb9','addForeignKeyConstraint baseTableName=token, constraintName=FKe32ek7ixanakfqsdaokm4q9y2, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-177','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',177,'EXECUTED','8:ac815a5185e1d4e10b08d6ec5c77dc86','addForeignKeyConstraint baseTableName=organization, constraintName=FKepyd6uqewfcp3xjvkr28mksul, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-178','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',178,'EXECUTED','8:1617beb1fe2a72d6aa99a570bce51bca','addForeignKeyConstraint baseTableName=user_role_group, constraintName=FKexdgxebi2id0avjqhenhkmem9, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-179','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',179,'EXECUTED','8:241c737a6ee8eeb0172a9b368896e36f','addForeignKeyConstraint baseTableName=analysis, constraintName=FKey194jla0jtivm1mgn7tn7g0i, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-180','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',180,'EXECUTED','8:42b297456d68d1c14cf4e6d7e51f4068','addForeignKeyConstraint baseTableName=sequence_run, constraintName=FKf27lk1j5r09jgtjl198kdtqje, referencedTableName=run_stats','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-181','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',181,'EXECUTED','8:0f4a010a9cfff07c5a944dbaa76c58e1','addForeignKeyConstraint baseTableName=sample_treatments, constraintName=FKf2vfwolapiodnq3iy2hi4gs9a, referencedTableName=cell_source_treatment','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-182','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',182,'EXECUTED','8:d7919b4a0f315ad87357fb46d69ecdd1','addForeignKeyConstraint baseTableName=item, constraintName=FKf60hnjyqgladtp0jw5o0n4e9u, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-183','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',183,'EXECUTED','8:3fac537574d68a159cc3f681544bd596','addForeignKeyConstraint baseTableName=inventory, constraintName=FKf7o8jsln5b13i6p3k5y75cgjo, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-184','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',184,'EXECUTED','8:d1b037096497b156ea38cd934da8a135','addForeignKeyConstraint baseTableName=cell_source, constraintName=FKfc39not4lgs7gunubj62b5nsx, referencedTableName=sex','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-185','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',185,'EXECUTED','8:ede80123514ccb1ca84200e3f6506020','addForeignKeyConstraint baseTableName=item_antibody, constraintName=FKg3hetijuop4bqe2dnhdqhgo51, referencedTableName=antibody','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-186','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',186,'EXECUTED','8:c8102f6a6c008f6349141c495a6fb350','addForeignKeyConstraint baseTableName=item, constraintName=FKg9lymegmlbvqtjrwpvkcdo5gr, referencedTableName=item_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-187','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',187,'EXECUTED','8:4fd82cb6452fa1a8b79358f2a3a7cb16','addForeignKeyConstraint baseTableName=sequencing_experiment, constraintName=FKgbnh1t5mfymeuu3ltqk2q8kp0, referencedTableName=read_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-188','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',188,'EXECUTED','8:d6381894e8491a5a8f1e0b9122ccd83b','addForeignKeyConstraint baseTableName=protocol_instance_bag, constraintName=FKgf7cmwy4enta8934copgfm80b, referencedTableName=protocol_group','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-189','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',189,'EXECUTED','8:34d461393e4e88720fb326adbf75e313','addForeignKeyConstraint baseTableName=user, constraintName=FKgrefofj2fru3hqquh5ep0mviq, referencedTableName=organization','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-190','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',190,'EXECUTED','8:ff9a6a343e211ad29399a89e1dc6d1c9','addForeignKeyConstraint baseTableName=item, constraintName=FKh4epdoqikj4sfedlxcc9dwwnl, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-191','danyingshao (generated)','changelog.groovy','2020-02-03 16:18:59',191,'EXECUTED','8:963a25cf1c602b447e2c1f3dff3fd6c8','addForeignKeyConstraint baseTableName=sample, constraintName=FKhflf768ds23ev1ywoqsp42p96, referencedTableName=target','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-192','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',192,'EXECUTED','8:2797f5364ecb54a65398b35b3af0a5c4','addForeignKeyConstraint baseTableName=protocol_group, constraintName=FKhin7nog9se54hev1i9697ux4t, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-193','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',193,'EXECUTED','8:56fc68c1229ece00a8fc87902c47b6bc','addForeignKeyConstraint baseTableName=cell_source, constraintName=FKhyjubhp4aikim11btnsynpfgd, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-194','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',194,'EXECUTED','8:cbc049fdc0b5004d09bfe83e2f07e42d','addForeignKeyConstraint baseTableName=protocol_instance_summary, constraintName=FKi70wyau9uwahxc4rtkhwf3n23, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-195','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',195,'EXECUTED','8:5a756ceb2bd60f53e2e7cce37d54c51d','addForeignKeyConstraint baseTableName=item_sequence_indices, constraintName=FKjhtgywgjb12yr7ljvpfn5s8eq, referencedTableName=sequence_index','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-196','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',196,'EXECUTED','8:c3880dd8f322e9932170161fad706228','addForeignKeyConstraint baseTableName=sequence_run, constraintName=FKjjf30r4ua6ru9e0ls0qhbwiei, referencedTableName=sequencing_platform','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-197','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',197,'EXECUTED','8:ca85adf4e158f30a4e72e419855a30a3','addForeignKeyConstraint baseTableName=sample_in_run, constraintName=FKjroa7jvf2q9kf1f3fasxiy735, referencedTableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-198','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',198,'EXECUTED','8:9e999e8cce01a003938448abe0b1e963','addForeignKeyConstraint baseTableName=protocol_item_types, constraintName=FKjw224bhrckc7lnuqc9r8p9y1g, referencedTableName=protocol','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-199','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',199,'EXECUTED','8:55e4a59154d7086e9f53d8299ec218fd','addForeignKeyConstraint baseTableName=antibody, constraintName=FKjwhhs6m573clakbtqb6ggmfn, referencedTableName=ab_host','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-200','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',200,'EXECUTED','8:e763efd032149d60daf698099d24c915','addForeignKeyConstraint baseTableName=item_type, constraintName=FKk3eijjkf35q5w944pwbb412u6, referencedTableName=item_type_category','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-201','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',201,'EXECUTED','8:5e630b9c26e59baf4540288fc47bbe02','addForeignKeyConstraint baseTableName=project_samples, constraintName=FKk7psrd21b6ftrh8y2xfm7yx7j, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-202','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',202,'EXECUTED','8:910943faeeed714dcb32369dde33896f','addForeignKeyConstraint baseTableName=protocol_instance, constraintName=FKl3j40ti2vyc6c4w1kbp6eb3u6, referencedTableName=protocol_instance_bag','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-203','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',203,'EXECUTED','8:af6075a3513d21d1c29c53047472018d','addForeignKeyConstraint baseTableName=chrom_sequence, constraintName=FKl7bmacg1b4eca57cotc7ils74, referencedTableName=chromosome','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-204','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',204,'EXECUTED','8:ab774fe3a399a1faceb3d62fbd2420cc','addForeignKeyConstraint baseTableName=role_group_role, constraintName=FKlu0ge9c3rhabcfu59589trt1m, referencedTableName=role_group','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-205','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',205,'EXECUTED','8:e9628fff1844d76c158db34c4f6130ea','addForeignKeyConstraint baseTableName=item_sequence_indices, constraintName=FKm08yv7tx0ymrjv7un1wncej95, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-206','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',206,'EXECUTED','8:48bd9dc84014f96286d7fe4f9097bfbe','addForeignKeyConstraint baseTableName=sequence_alignment, constraintName=FKm3h3wvtgyqcbyp7kaac35wj57, referencedTableName=genome','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-207','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',207,'EXECUTED','8:b64ff583e4e147c557cebbf4d8782598','addForeignKeyConstraint baseTableName=sequence_run, constraintName=FKmcddrdwrtqwy0h5wfiplislxj, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-208','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:00',208,'EXECUTED','8:e30eeb64f5d49ac9324be5c53bfac5a9','addForeignKeyConstraint baseTableName=sequencing_experiment, constraintName=FKmqikne1qqkrnll81iu1sfre, referencedTableName=sequencing_cohort','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-209','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:01',209,'EXECUTED','8:2ce4904609f8a8d1978fc1c8b4cb31a7','addForeignKeyConstraint baseTableName=history, constraintName=FKn4gjyu69m6xa5f3bot571imbe, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-210','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:01',210,'EXECUTED','8:0ce0335eb0e391ab740a6103ee0d2eb4','addForeignKeyConstraint baseTableName=histology, constraintName=FKn6wxriw2r0bt5lepofmjshgrc, referencedTableName=histology','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-211','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:01',211,'EXECUTED','8:45d7bccb64382b585d7303ccd734ffe6','addForeignKeyConstraint baseTableName=cell_source, constraintName=FKndexf4a4taolqvll9kmbh8kkg, referencedTableName=histology','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-212','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:01',212,'EXECUTED','8:73ec926ac04701fef2b7516be5ecb01e','addForeignKeyConstraint baseTableName=history, constraintName=FKo85y510lpxvd8vyk32diab6qq, referencedTableName=project','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-213','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:01',213,'EXECUTED','8:0fec3567f93b5ed74fa9b52a78d4dcd2','addForeignKeyConstraint baseTableName=sample, constraintName=FKo86ao5favegpdv2p45ekxus9o, referencedTableName=invoice','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-214','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:01',214,'EXECUTED','8:0c2aad14023f1e6bbd018ef98d28757c','addForeignKeyConstraint baseTableName=sequence_alignment, constraintName=FKok3dqhynp748nr1ur1firmt14, referencedTableName=aligner','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-215','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:01',215,'EXECUTED','8:5a12c10f1569c3e8e3ea5d2d4a7d81b5','addForeignKeyConstraint baseTableName=item_antibody, constraintName=FKoklrp1sxftr4iyjrxjhiykio2, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-216','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:01',216,'EXECUTED','8:4a468ac8352ab55e5a3b0d06dc24579f','addForeignKeyConstraint baseTableName=sample, constraintName=FKorx0c9myw8smxka4o3ir837ps, referencedTableName=cell_source','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-217','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:01',217,'EXECUTED','8:55de7235a1a462d3b39c53d8c7b39c92','addForeignKeyConstraint baseTableName=sample, constraintName=FKp8cqy6861ds7dco3txn0ioh3c, referencedTableName=protocol_instance_summary','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-218','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:01',218,'EXECUTED','8:95ad75cc5015daab3129bf61d546e7a6','addForeignKeyConstraint baseTableName=sequencing_cohort, constraintName=FKq03aoroe14bkb93n2jpcmfpn, referencedTableName=summary_report','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-219','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:01',219,'EXECUTED','8:b77f80ed785e225b5706b4e5d3b1f509','addForeignKeyConstraint baseTableName=sequence_alignment, constraintName=FKq4d03x395u6vghiwp58qmqdf7, referencedTableName=pipeline','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-220','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:01',220,'EXECUTED','8:48aace6405edd5caf604d87fbfa4dccd','addForeignKeyConstraint baseTableName=sequencing_cohort, constraintName=FKqe8146g7qcr91mhbhlnkowoq5, referencedTableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-221','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',221,'EXECUTED','8:f1571353a843130413b4a28df8203bd9','addForeignKeyConstraint baseTableName=protocol_instance, constraintName=FKqhnl5itkka9paugla9ss836wb, referencedTableName=protocol','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-222','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',222,'EXECUTED','8:fcef96ec06d6fe5dca2a2c7260e95d78','addForeignKeyConstraint baseTableName=pool_samples, constraintName=FKqou16vog2akhov2b03410qna, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-223','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',223,'EXECUTED','8:d549a2faf992528e3e8513ab762f09bb','addForeignKeyConstraint baseTableName=protocol, constraintName=FKqtg4kcmhhbl3evj87vvf71xvf, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-224','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',224,'EXECUTED','8:ab3a80e148dcee043f31cef32a755cd2','addForeignKeyConstraint baseTableName=replicate_samples, constraintName=FKqwoluiec2sxnxtbhuoj4e7858, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-225','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',225,'EXECUTED','8:b96550fcecc885d186d3e581ce5f5a86','addForeignKeyConstraint baseTableName=item, constraintName=FKqwonew7r6scr7si8k2cei8wjo, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-226','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',226,'EXECUTED','8:4e5504e5d56080ed05f63c9405d116ca','addForeignKeyConstraint baseTableName=antibody, constraintName=FKr7hx5ykvqtjo1slxyedbeon0j, referencedTableName=organization','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-227','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',227,'EXECUTED','8:dfeeb9a0514f3abfcb0530715969ec4f','addForeignKeyConstraint baseTableName=cell_source, constraintName=FKroomr52lbjofo8ly77vayrsuu, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-228','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',228,'EXECUTED','8:b9cde3f8aa6a865b1d4afed09c2fa58a','addForeignKeyConstraint baseTableName=sample_treatments, constraintName=FKrq3vygba0p5l99k8ej2msf758, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-229','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',229,'EXECUTED','8:07279fbe4068014e172a047cbd029dd3','addForeignKeyConstraint baseTableName=replicate_samples, constraintName=FKrvwftgvok41hx8n02aym7slsp, referencedTableName=replicate_set','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-230','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',230,'EXECUTED','8:addf7258f2c422bacf73d7930dcd7c2f','addForeignKeyConstraint baseTableName=strain, constraintName=FKrwpjbguvhieg265qb4sjmofnr, referencedTableName=organization','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-231','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',231,'EXECUTED','8:051bfbd9c8cf44edd98f56e0f19440a7','addForeignKeyConstraint baseTableName=antibody, constraintName=FKrwq0pnfbecmygn4xofqr4qtw3, referencedTableName=item','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-232','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',232,'EXECUTED','8:e8649e00d8a21abf9d14d95d7b55f1e4','addForeignKeyConstraint baseTableName=control_sample, constraintName=FKs2ks4b5o1f4a670k9ynottyrp, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-233','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',233,'EXECUTED','8:573baa0a3433baa788abebc0ed59c0da','addForeignKeyConstraint baseTableName=cell_source, constraintName=FKs656nbjgufpx0t833xbu78mck, referencedTableName=user','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-234','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',234,'EXECUTED','8:62f38505c983161a1926118f736955c7','addForeignKeyConstraint baseTableName=analysis, constraintName=FKsmacedtxj270658jj6r8hh5qb, referencedTableName=sequence_alignment','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-235','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',235,'EXECUTED','8:79efaf1754a76b54022d1dc43f35fa9f','addForeignKeyConstraint baseTableName=protocol_group_protocols, constraintName=FKsp79j0667g22q9e72dp0617sd, referencedTableName=protocol','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-236','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:02',236,'EXECUTED','8:9085ae6dcee7713a74d2000bf5021664','addForeignKeyConstraint baseTableName=sequence_alignment, constraintName=FKsw06tb5ys7obob4e2cpovuwr8, referencedTableName=align_type','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-237','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:03',237,'EXECUTED','8:cf73480e27b35a928e0714b9baa5ee9d','addForeignKeyConstraint baseTableName=sample, constraintName=FKswwuixwogcl48lwy3ucvvqaub, referencedTableName=growth_media','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-238','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:03',238,'EXECUTED','8:a3df5dfc847cdccaf44efad7f61198c3','addForeignKeyConstraint baseTableName=sample_sequence_indices, constraintName=FKtbax39c2nyeqk46yiwk6xj9l, referencedTableName=sample','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1580764544352-239','danyingshao (generated)','changelog.groovy','2020-02-03 16:19:03',239,'EXECUTED','8:198cc5d7aa8dc247e7651ed8059cdf61','addForeignKeyConstraint baseTableName=growth_media, constraintName=FKte5f01yhaud29abdr878pjgsi, referencedTableName=species','',NULL,'3.6.1',NULL,NULL,'0764730937'),('1605124372433-47','danyingshao (generated)','updateProtocol.groovy','2021-03-22 18:05:26',240,'EXECUTED','8:5631275be27676c0ee55775d37e34ae2','addColumn tableName=protocol','',NULL,'3.6.1',NULL,NULL,'6450725852'),('1605124372433-48','danyingshao (generated)','updateProtocol.groovy','2021-03-22 18:05:26',241,'EXECUTED','8:35803be9f73d7494c658a3c86d1c3822','addUniqueConstraint constraintName=UKccd9281ba8d5bd121364d7b5eb3c, tableName=protocol_item_types','',NULL,'3.6.1',NULL,NULL,'6450725852'),('1605124372433-49','danyingshao (generated)','updateProtocol.groovy','2021-03-22 18:21:31',242,'EXECUTED','8:0564339c1a098ee72ed601c90e532fcc','dropUniqueConstraint constraintName=UK69c2d8246f1bc2e5e8d02ada96db, tableName=protocol_item_types','',NULL,'3.6.1',NULL,NULL,'6451691326'),('1608661967757-48','danyingshao (generated)','assay_description.groovy','2021-03-22 18:21:31',243,'EXECUTED','8:b884834e1be285d02bc35022bf6b32ce','addColumn tableName=assay','',NULL,'3.6.1',NULL,NULL,'6451691326'),('1611490736543-48','danyingshao (generated)','run_name.groovy','2021-03-22 18:21:31',244,'EXECUTED','8:a8edf75f2f91f2bf788c777e02aafd6d','addColumn tableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'6451691326'),('1611490736543-49','danyingshao (generated)','run_name.groovy','2021-03-22 18:21:31',245,'EXECUTED','8:593e3fe060ab12db9f486b9c3ba71de9','addUniqueConstraint constraintName=UC_SEQUENCE_RUNRUN_NAME_COL, tableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'6451691326'),('1611490736543-50','danyingshao (generated)','run_name.groovy','2021-03-22 18:21:31',246,'EXECUTED','8:a8622ddaa69ac2fcea75274371f81001','dropColumn columnName=run_num_alias, tableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'6451691326'),('1611662015663-49','danyingshao (generated)','delete_run_num.groovy','2021-03-22 18:21:31',247,'EXECUTED','8:a3884608cad1ac7606a15a9cd2bea1ec','dropColumn columnName=run_num, tableName=sequence_run','',NULL,'3.6.1',NULL,NULL,'6451691326'),('1611863993806-49','danyingshao (generated)','add_feature.groovy','2021-03-22 18:21:31',248,'EXECUTED','8:d0789430a937720fa7bbb2d54f46a03f','createTable tableName=reference_feature','',NULL,'3.6.1',NULL,NULL,'6451691326'),('1611863993806-50','danyingshao (generated)','add_feature.groovy','2021-03-22 18:21:31',249,'EXECUTED','8:b6af337a168b7bc11be9d5ba739d3214','addForeignKeyConstraint baseTableName=reference_feature, constraintName=FKcy4t95j1gcwtgimf4oxyyjm46, referencedTableName=genome','',NULL,'3.6.1',NULL,NULL,'6451691326');
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
  `LOCKED` bit(1) NOT NULL,
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
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,'\0',NULL,NULL);
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
  UNIQUE KEY `UC_AB_HOSTNAME_COL` (`name`)
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
  `line2` varchar(255) DEFAULT NULL,
  `postal_code` varchar(10) NOT NULL,
  `line1` varchar(255) NOT NULL,
  `city` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
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
  `short_name` varchar(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_ALIGN_TYPENAME_COL` (`name`),
  UNIQUE KEY `UC_ALIGN_TYPESHORT_NAME_COL` (`short_name`)
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
  `software` varchar(255) NOT NULL,
  `aligner_version` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKb8ab847080355581b293e4a87bcb` (`aligner_version`,`software`)
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
  `date` datetime DEFAULT NULL,
  `datasets` longtext DEFAULT NULL,
  `tool` varchar(255) NOT NULL,
  `step_id` varchar(255) DEFAULT NULL,
  `parameters` longtext DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `statistics` longtext DEFAULT NULL,
  `step` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKey194jla0jtivm1mgn7tn7g0i` (`user_id`),
  KEY `FKsmacedtxj270658jj6r8hh5qb` (`alignment_id`),
  CONSTRAINT `FKey194jla0jtivm1mgn7tn7g0i` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKsmacedtxj270658jj6r8hh5qb` FOREIGN KEY (`alignment_id`) REFERENCES `sequence_alignment` (`id`)
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
  `concentration` float DEFAULT NULL,
  `catalog_number` varchar(255) DEFAULT NULL,
  `immunogene` varchar(255) DEFAULT NULL,
  `lot_number` varchar(255) DEFAULT NULL,
  `ig_type_id` bigint(20) DEFAULT NULL,
  `default_target_id` bigint(20) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `clonal` varchar(255) DEFAULT NULL,
  `inventory_id` varchar(255) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `ab_host_id` bigint(20) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `external_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2wi5vsoyxjfxl0mc5v1mm9dts` (`default_target_id`),
  KEY `FK38l2x1xyohqre04awha89rr15` (`ig_type_id`),
  KEY `FKjwhhs6m573clakbtqb6ggmfn` (`ab_host_id`),
  KEY `FKr7hx5ykvqtjo1slxyedbeon0j` (`company_id`),
  KEY `FKrwq0pnfbecmygn4xofqr4qtw3` (`item_id`),
  CONSTRAINT `FK2wi5vsoyxjfxl0mc5v1mm9dts` FOREIGN KEY (`default_target_id`) REFERENCES `target` (`id`),
  CONSTRAINT `FK38l2x1xyohqre04awha89rr15` FOREIGN KEY (`ig_type_id`) REFERENCES `ig_type` (`id`),
  CONSTRAINT `FKjwhhs6m573clakbtqb6ggmfn` FOREIGN KEY (`ab_host_id`) REFERENCES `ab_host` (`id`),
  CONSTRAINT `FKr7hx5ykvqtjo1slxyedbeon0j` FOREIGN KEY (`company_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FKrwq0pnfbecmygn4xofqr4qtw3` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
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
  UNIQUE KEY `UC_ASSAYNAME_COL` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assay`
--

LOCK TABLES `assay` WRITE;
/*!40000 ALTER TABLE `assay` DISABLE KEYS */;
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
  `cell_source_id` bigint(20) NOT NULL,
  `batch_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK99x1hjei6ogdcxm1w4fxvfr6q` (`cell_source_id`),
  KEY `FKcjurv5d0t0u6yx5tl79fh0ka4` (`batch_id`),
  CONSTRAINT `FK99x1hjei6ogdcxm1w4fxvfr6q` FOREIGN KEY (`cell_source_id`) REFERENCES `cell_source` (`id`),
  CONSTRAINT `FKcjurv5d0t0u6yx5tl79fh0ka4` FOREIGN KEY (`batch_id`) REFERENCES `cell_source_batch` (`id`)
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
  `sex_id` bigint(20) DEFAULT NULL,
  `prep_user_id` bigint(20) DEFAULT NULL,
  `tissue_id` bigint(20) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `histology_id` bigint(20) DEFAULT NULL,
  `provider_user_id` bigint(20) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `biological_source_id` varchar(255) DEFAULT NULL,
  `inventory_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `strain_id` bigint(20) NOT NULL,
  `provider_lab_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK49xxhx356gij34jl4uxnwknyw` (`provider_lab_id`),
  KEY `FK868cjl2jx1t1enrfst5ce1un9` (`strain_id`),
  KEY `FK9bw1i4huctjx4330y93axijm1` (`tissue_id`),
  KEY `FKdspbw8rsnb9h8bttdb5a4qyq` (`inventory_id`),
  KEY `FKfc39not4lgs7gunubj62b5nsx` (`sex_id`),
  KEY `FKhyjubhp4aikim11btnsynpfgd` (`item_id`),
  KEY `FKndexf4a4taolqvll9kmbh8kkg` (`histology_id`),
  KEY `FKroomr52lbjofo8ly77vayrsuu` (`prep_user_id`),
  KEY `FKs656nbjgufpx0t833xbu78mck` (`provider_user_id`),
  CONSTRAINT `FK49xxhx356gij34jl4uxnwknyw` FOREIGN KEY (`provider_lab_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK868cjl2jx1t1enrfst5ce1un9` FOREIGN KEY (`strain_id`) REFERENCES `strain` (`id`),
  CONSTRAINT `FK9bw1i4huctjx4330y93axijm1` FOREIGN KEY (`tissue_id`) REFERENCES `tissue` (`id`),
  CONSTRAINT `FKdspbw8rsnb9h8bttdb5a4qyq` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`),
  CONSTRAINT `FKfc39not4lgs7gunubj62b5nsx` FOREIGN KEY (`sex_id`) REFERENCES `sex` (`id`),
  CONSTRAINT `FKhyjubhp4aikim11btnsynpfgd` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FKndexf4a4taolqvll9kmbh8kkg` FOREIGN KEY (`histology_id`) REFERENCES `histology` (`id`),
  CONSTRAINT `FKroomr52lbjofo8ly77vayrsuu` FOREIGN KEY (`prep_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKs656nbjgufpx0t833xbu78mck` FOREIGN KEY (`provider_user_id`) REFERENCES `user` (`id`)
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
  KEY `FK14vul503srgn35l05e3v1yt51` (`user_id`),
  CONSTRAINT `FK14vul503srgn35l05e3v1yt51` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `status` varchar(255) DEFAULT NULL,
  `note` longtext DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_CELL_SOURCE_TREATMENTNAME_COL` (`name`)
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
  UNIQUE KEY `UC_CHORESNAME_COL` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chores`
--

LOCK TABLES `chores` WRITE;
/*!40000 ALTER TABLE `chores` DISABLE KEYS */;
INSERT INTO `chores` VALUES (1,0,'RunsInQueue',NULL),(2,0,'PriorRunFolder',NULL);
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
  `sequence` longtext NOT NULL,
  `chromosome_id` bigint(20) NOT NULL,
  `length` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl7bmacg1b4eca57cotc7ils74` (`chromosome_id`),
  CONSTRAINT `FKl7bmacg1b4eca57cotc7ils74` FOREIGN KEY (`chromosome_id`) REFERENCES `chromosome` (`id`)
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
  UNIQUE KEY `UC_CHROMOSOMENAME_COL` (`name`),
  KEY `FKam69crakd25kjy7d2rqiy5108` (`genome_id`),
  CONSTRAINT `FKam69crakd25kjy7d2rqiy5108` FOREIGN KEY (`genome_id`) REFERENCES `genome` (`id`)
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
  `sample_id` bigint(20) NOT NULL,
  `control_sample_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK1a92d74db3df102928b5f605132a` (`control_sample_id`,`sample_id`),
  KEY `FK5luc1hgx7j675npfvs5k3jngq` (`sample_id`),
  CONSTRAINT `FK5luc1hgx7j675npfvs5k3jngq` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`),
  CONSTRAINT `FKs2ks4b5o1f4a670k9ynottyrp` FOREIGN KEY (`control_sample_id`) REFERENCES `sample` (`id`)
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
  `name` varchar(255) NOT NULL,
  `content` varchar(5000) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_DEFINITIONNAME_COL` (`name`)
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
  UNIQUE KEY `UKc9cafa65fdeb3f03632764f330f8` (`name`)
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
  `url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `species_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1v0f049a0ljh0ohpc585xwb10` (`species_id`),
  CONSTRAINT `FK1v0f049a0ljh0ohpc585xwb10` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genome`
--

LOCK TABLES `genome` WRITE;
/*!40000 ALTER TABLE `genome` DISABLE KEYS */;
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
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_GROWTH_MEDIANAME_COL` (`name`),
  KEY `FKte5f01yhaud29abdr878pjgsi` (`species_id`),
  CONSTRAINT `FKte5f01yhaud29abdr878pjgsi` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `growth_media`
--

LOCK TABLES `growth_media` WRITE;
/*!40000 ALTER TABLE `growth_media` DISABLE KEYS */;
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
  `status` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_HISTOLOGYNAME_COL` (`name`),
  KEY `FKn6wxriw2r0bt5lepofmjshgrc` (`parent_id`),
  CONSTRAINT `FKn6wxriw2r0bt5lepofmjshgrc` FOREIGN KEY (`parent_id`) REFERENCES `histology` (`id`)
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
  `object_id` int(11) NOT NULL,
  `object_type` varchar(255) NOT NULL,
  `date_created` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `action` varchar(255) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn4gjyu69m6xa5f3bot571imbe` (`user_id`),
  KEY `FKo85y510lpxvd8vyk32diab6qq` (`project_id`),
  CONSTRAINT `FKn4gjyu69m6xa5f3bot571imbe` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKo85y510lpxvd8vyk32diab6qq` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
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
  UNIQUE KEY `UC_IG_TYPENAME_COL` (`name`)
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
  `receiving_user_id` bigint(20) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `date_received` datetime DEFAULT NULL,
  `source_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf7o8jsln5b13i6p3k5y75cgjo` (`receiving_user_id`),
  CONSTRAINT `FKf7o8jsln5b13i6p3k5y75cgjo` FOREIGN KEY (`receiving_user_id`) REFERENCES `user` (`id`)
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
  `last_updated` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `customized_fields` longtext DEFAULT NULL,
  `barcode` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) NOT NULL,
  `status` varchar(255) NOT NULL DEFAULT 'GOOD',
  `project_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_ITEMBARCODE_COL` (`barcode`),
  KEY `FKf60hnjyqgladtp0jw5o0n4e9u` (`project_id`),
  KEY `FKg9lymegmlbvqtjrwpvkcdo5gr` (`type_id`),
  KEY `FKh4epdoqikj4sfedlxcc9dwwnl` (`user_id`),
  KEY `FKqwonew7r6scr7si8k2cei8wjo` (`parent_id`),
  CONSTRAINT `FKf60hnjyqgladtp0jw5o0n4e9u` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FKg9lymegmlbvqtjrwpvkcdo5gr` FOREIGN KEY (`type_id`) REFERENCES `item_type` (`id`),
  CONSTRAINT `FKh4epdoqikj4sfedlxcc9dwwnl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqwonew7r6scr7si8k2cei8wjo` FOREIGN KEY (`parent_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `item_id` bigint(20) NOT NULL,
  `antibody_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKeac55f11282de8304404741a9798` (`antibody_id`,`item_id`),
  KEY `FKoklrp1sxftr4iyjrxjhiykio2` (`item_id`),
  CONSTRAINT `FKg3hetijuop4bqe2dnhdqhgo51` FOREIGN KEY (`antibody_id`) REFERENCES `antibody` (`id`),
  CONSTRAINT `FKoklrp1sxftr4iyjrxjhiykio2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
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
  `item_id` bigint(20) NOT NULL,
  `index_id` bigint(20) NOT NULL,
  `set_id` int(11) DEFAULT NULL,
  `index_in_set` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjhtgywgjb12yr7ljvpfn5s8eq` (`index_id`),
  KEY `FKm08yv7tx0ymrjv7un1wncej95` (`item_id`),
  CONSTRAINT `FKjhtgywgjb12yr7ljvpfn5s8eq` FOREIGN KEY (`index_id`) REFERENCES `sequence_index` (`id`),
  CONSTRAINT `FKm08yv7tx0ymrjv7un1wncej95` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
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
  `name` varchar(255) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `fields` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_ITEM_TYPENAME_COL` (`name`),
  KEY `FKk3eijjkf35q5w944pwbb412u6` (`category_id`),
  CONSTRAINT `FKk3eijjkf35q5w944pwbb412u6` FOREIGN KEY (`category_id`) REFERENCES `item_type_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_type`
--

LOCK TABLES `item_type` WRITE;
/*!40000 ALTER TABLE `item_type` DISABLE KEYS */;
INSERT INTO `item_type` VALUES (1,0,'Antibody',1,NULL);
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
  `super_category` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_ITEM_TYPE_CATEGORYNAME_COL` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_type_category`
--

LOCK TABLES `item_type_category` WRITE;
/*!40000 ALTER TABLE `item_type_category` DISABLE KEYS */;
INSERT INTO `item_type_category` VALUES (1,0,'ANTIBODY','Antibody');
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
  `status` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `class` varchar(255) NOT NULL,
  `pi_id` bigint(20) DEFAULT NULL,
  `billing_contact_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_ORGANIZATIONNAME_COL` (`name`),
  KEY `FK6xw1bsvdkcf10rvj1feetks4p` (`billing_contact_id`),
  KEY `FK97eigsvq2tsrd2bge4ox651wh` (`address_id`),
  KEY `FKc30yedjwp9qw1f3nn2ytda7tj` (`parent_id`),
  KEY `FKepyd6uqewfcp3xjvkr28mksul` (`pi_id`),
  CONSTRAINT `FK6xw1bsvdkcf10rvj1feetks4p` FOREIGN KEY (`billing_contact_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK97eigsvq2tsrd2bge4ox651wh` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKc30yedjwp9qw1f3nn2ytda7tj` FOREIGN KEY (`parent_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FKepyd6uqewfcp3xjvkr28mksul` FOREIGN KEY (`pi_id`) REFERENCES `user` (`id`)
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
  `workflow_id` varchar(255) NOT NULL DEFAULT 'N/A',
  `pipeline_version` varchar(255) NOT NULL DEFAULT '0.0.0',
  `workflow_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `steps` longtext NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_PIPELINEWORKFLOW_ID_COL` (`workflow_id`),
  UNIQUE KEY `UKf01a4847e4bdb6a7b8198123f4ae` (`pipeline_version`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pipeline`
--

LOCK TABLES `pipeline` WRITE;
/*!40000 ALTER TABLE `pipeline` DISABLE KEYS */;
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
  `sample_id` bigint(20) NOT NULL,
  `pool_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKba7773a538d4770149b421f84583` (`pool_id`,`sample_id`),
  KEY `FK1elc3dx8od4b22mfnj7ems7oa` (`sample_id`),
  CONSTRAINT `FK1elc3dx8od4b22mfnj7ems7oa` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`),
  CONSTRAINT `FKqou16vog2akhov2b03410qna` FOREIGN KEY (`pool_id`) REFERENCES `item` (`id`)
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
  `last_updated` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `notes` longtext DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_PROJECTNAME_COL` (`name`)
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
  `project_id` bigint(20) NOT NULL,
  `bag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKb4d526519821b65515d64f1ac54b` (`bag_id`,`project_id`),
  KEY `FK2xobskjd8bp5b128rh1duijt0` (`project_id`),
  CONSTRAINT `FK2xobskjd8bp5b128rh1duijt0` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FKb4e1wnuhpvapbhnyjs6dqk8mo` FOREIGN KEY (`bag_id`) REFERENCES `protocol_instance_bag` (`id`)
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
  UNIQUE KEY `UK16c0fb5f244d0452a2e49aed563f` (`funding_id`,`project_id`),
  KEY `FK9l2tp6kpxf1us967h9092rsu3` (`project_id`),
  CONSTRAINT `FK3sab04b4896qhtxtm9kmwb6p3` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`id`),
  CONSTRAINT `FK9l2tp6kpxf1us967h9092rsu3` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
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
  `sample_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKf38f5325c5da49e7b9b5d51d5226` (`sample_id`,`project_id`),
  KEY `FK95dx7fkv6r65xk5r9qcng8cin` (`project_id`),
  CONSTRAINT `FK95dx7fkv6r65xk5r9qcng8cin` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FKk7psrd21b6ftrh8y2xfm7yx7j` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`)
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
  `project_role` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfbf64ee15963f7e643e3a681061d` (`user_id`,`project_id`),
  KEY `FK4ug72llnm0n7yafwntgdswl3y` (`project_id`),
  CONSTRAINT `FK4jl2o131jivd80xsuw6pivnbx` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK4ug72llnm0n7yafwntgdswl3y` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
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
  `short_name` varchar(255) DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  `assay_id` bigint(20) DEFAULT NULL,
  `protocol_version` varchar(10) DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  `add_antibody` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `add_index` bit(1) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8d22b11cbab7be2f38b4c8c2dd1d` (`protocol_version`,`name`),
  KEY `FK2ugvxqbj45ie8pfijctcp5m44` (`assay_id`),
  KEY `FKqtg4kcmhhbl3evj87vvf71xvf` (`user_id`),
  CONSTRAINT `FK2ugvxqbj45ie8pfijctcp5m44` FOREIGN KEY (`assay_id`) REFERENCES `assay` (`id`),
  CONSTRAINT `FKqtg4kcmhhbl3evj87vvf71xvf` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protocol`
--

LOCK TABLES `protocol` WRITE;
/*!40000 ALTER TABLE `protocol` DISABLE KEYS */;
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
  UNIQUE KEY `UC_PROTOCOL_GROUPNAME_COL` (`name`),
  KEY `FKhin7nog9se54hev1i9697ux4t` (`user_id`),
  CONSTRAINT `FKhin7nog9se54hev1i9697ux4t` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `protocol_id` bigint(20) NOT NULL,
  `protocols_idx` int(11) NOT NULL,
  `protocol_group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK413d0a510ff03d1f19c83af9ae36` (`protocols_idx`,`protocol_group_id`,`protocol_id`),
  KEY `FK1iuch12ayvha1jsq3r49e8e2j` (`protocol_group_id`),
  KEY `FKsp79j0667g22q9e72dp0617sd` (`protocol_id`),
  CONSTRAINT `FK1iuch12ayvha1jsq3r49e8e2j` FOREIGN KEY (`protocol_group_id`) REFERENCES `protocol_group` (`id`),
  CONSTRAINT `FKsp79j0667g22q9e72dp0617sd` FOREIGN KEY (`protocol_id`) REFERENCES `protocol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `end_time` datetime DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  `bag_id` bigint(20) DEFAULT NULL,
  `protocol_id` bigint(20) DEFAULT NULL,
  `bag_idx` int(11) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3kt03fn9v9icvw7iam3ri2q2e` (`user_id`),
  KEY `FKl3j40ti2vyc6c4w1kbp6eb3u6` (`bag_id`),
  KEY `FKqhnl5itkka9paugla9ss836wb` (`protocol_id`),
  CONSTRAINT `FK3kt03fn9v9icvw7iam3ri2q2e` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKl3j40ti2vyc6c4w1kbp6eb3u6` FOREIGN KEY (`bag_id`) REFERENCES `protocol_instance_bag` (`id`),
  CONSTRAINT `FKqhnl5itkka9paugla9ss836wb` FOREIGN KEY (`protocol_id`) REFERENCES `protocol` (`id`)
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
  `protocol_group_id` bigint(20) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgf7cmwy4enta8934copgfm80b` (`protocol_group_id`),
  CONSTRAINT `FKgf7cmwy4enta8934copgfm80b` FOREIGN KEY (`protocol_group_id`) REFERENCES `protocol_group` (`id`)
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
  KEY `FK521y62ybe1gxfnde38pvohr14` (`item_id`),
  KEY `FK8pjccf4a3c9n2ja8k9cq6v38p` (`protocol_instance_id`),
  CONSTRAINT `FK521y62ybe1gxfnde38pvohr14` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK8pjccf4a3c9n2ja8k9cq6v38p` FOREIGN KEY (`protocol_instance_id`) REFERENCES `protocol_instance` (`id`)
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
  `protocol_id` bigint(20) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6vn6v7g4c33vcd6pja47o9lxm` (`protocol_id`),
  KEY `FKi70wyau9uwahxc4rtkhwf3n23` (`user_id`),
  CONSTRAINT `FK6vn6v7g4c33vcd6pja47o9lxm` FOREIGN KEY (`protocol_id`) REFERENCES `protocol` (`id`),
  CONSTRAINT `FKi70wyau9uwahxc4rtkhwf3n23` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
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
  `protocol_id` bigint(20) NOT NULL,
  `function` varchar(255) NOT NULL,
  `item_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKccd9281ba8d5bd121364d7b5eb3c` (`function`,`item_type_id`,`protocol_id`),
  KEY `FKjw224bhrckc7lnuqc9r8p9y1g` (`protocol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `short_name` varchar(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_READ_TYPENAME_COL` (`name`),
  UNIQUE KEY `UC_READ_TYPESHORT_NAME_COL` (`short_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `read_type`
--

LOCK TABLES `read_type` WRITE;
/*!40000 ALTER TABLE `read_type` DISABLE KEYS */;
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
  UNIQUE KEY `UK68b1ef05059e7f9742c5997e6f74` (`sample_id`,`set_id`),
  KEY `FKrvwftgvok41hx8n02aym7slsp` (`set_id`),
  CONSTRAINT `FKqwoluiec2sxnxtbhuoj4e7858` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`),
  CONSTRAINT `FKrvwftgvok41hx8n02aym7slsp` FOREIGN KEY (`set_id`) REFERENCES `replicate_set` (`id`)
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
  `type` varchar(255) NOT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbesib3k7784q0fr1a2si9y576` (`project_id`),
  CONSTRAINT `FKbesib3k7784q0fr1a2si9y576` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
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
  UNIQUE KEY `UKdc9d49ed85d397cd09bb505033ce` (`alignment_id`,`report_id`),
  KEY `FK8ui05vjl12r7jwq8bdye2jsig` (`report_id`),
  CONSTRAINT `FK33tihrorftk27epehcm1be8ci` FOREIGN KEY (`alignment_id`) REFERENCES `sequence_alignment` (`id`),
  CONSTRAINT `FK8ui05vjl12r7jwq8bdye2jsig` FOREIGN KEY (`report_id`) REFERENCES `summary_report` (`id`)
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
  UNIQUE KEY `UC_ROLEAUTHORITY_COL` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
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
  UNIQUE KEY `UC_ROLE_GROUPNAME_COL` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_group`
--

LOCK TABLES `role_group` WRITE;
/*!40000 ALTER TABLE `role_group` DISABLE KEYS */;
INSERT INTO `role_group` VALUES (1,0,'Admin');
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
  KEY `FKbo8cffthrm8wxgtnhg0i8dcxw` (`role_id`),
  CONSTRAINT `FKbo8cffthrm8wxgtnhg0i8dcxw` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKlu0ge9c3rhabcfu59589trt1m` FOREIGN KEY (`role_group_id`) REFERENCES `role_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_group_role`
--

LOCK TABLES `role_group_role` WRITE;
/*!40000 ALTER TABLE `role_group_role` DISABLE KEYS */;
INSERT INTO `role_group_role` VALUES (1,1);
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
  `phixloaded` float DEFAULT NULL,
  `q_pcr_conc` float DEFAULT NULL,
  `sr_or_pe` varchar(255) DEFAULT NULL,
  `read_pf` float DEFAULT NULL,
  `library_volume` float DEFAULT NULL,
  `pct_aligned_to_phix` float DEFAULT NULL,
  `q_pcr_date` datetime DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `qubit_conc` float DEFAULT NULL,
  `qidx` float DEFAULT NULL,
  `cluster_num` float DEFAULT NULL,
  `library_stock` float DEFAULT NULL,
  `seq_ctrl` varchar(255) DEFAULT NULL,
  `pct_pf` float DEFAULT NULL,
  `library_pool_archive_id` varchar(255) DEFAULT NULL,
  `library_loaded_fmol` float DEFAULT NULL,
  `library_loaded_pm` float DEFAULT NULL,
  `pcr_cycles` int(11) DEFAULT NULL,
  `unmatched_indices` float DEFAULT NULL,
  `total_reads` float DEFAULT NULL,
  `pct_library_std_dev` float DEFAULT NULL,
  `cycles` varchar(255) DEFAULT NULL,
  `library_std_dev` float DEFAULT NULL,
  `pctq30` float DEFAULT NULL,
  `technician_id` bigint(20) DEFAULT NULL,
  `pct_unmatched_indices` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKar55v5cio0nmrqv7opgg1n8cl` (`technician_id`),
  CONSTRAINT `FKar55v5cio0nmrqv7opgg1n8cl` FOREIGN KEY (`technician_id`) REFERENCES `user` (`id`)
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
  `date` datetime DEFAULT NULL,
  `requested_genomes` varchar(255) DEFAULT NULL,
  `assay_id` bigint(20) DEFAULT NULL,
  `publication_reference` varchar(255) DEFAULT NULL,
  `natural_id` varchar(255) DEFAULT NULL,
  `antibody_notes` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `antibody_id` bigint(20) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `source_id` varchar(255) DEFAULT NULL,
  `volume` double DEFAULT NULL,
  `cell_source_id` bigint(20) DEFAULT NULL,
  `prtcl_inst_summary_id` bigint(20) DEFAULT NULL,
  `target_id` bigint(20) DEFAULT NULL,
  `send_data_to_id` bigint(20) DEFAULT NULL,
  `growth_media_id` bigint(20) DEFAULT NULL,
  `chromosome_amount` double DEFAULT NULL,
  `requested_tag_number` double DEFAULT NULL,
  `cell_number` double DEFAULT NULL,
  `spike_in_cell_source_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `note` longtext DEFAULT NULL,
  `invoice_id` bigint(20) DEFAULT NULL,
  `recommend` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1rdptfp7u4obmb8eikuw5nnrf` (`item_id`),
  KEY `FK2hvogurfkgktbxxvdo2csoa8v` (`antibody_id`),
  KEY `FK579rlyfhja2i87deo6dgndfnj` (`cell_source_id`),
  KEY `FKbpci4r4ynak9f0hby2pmaw0bo` (`send_data_to_id`),
  KEY `FKcpc4hhf4ntlq02q576nu2ky44` (`assay_id`),
  KEY `FKhflf768ds23ev1ywoqsp42p96` (`target_id`),
  KEY `FKo86ao5favegpdv2p45ekxus9o` (`invoice_id`),
  KEY `FKorx0c9myw8smxka4o3ir837ps` (`spike_in_cell_source_id`),
  KEY `FKp8cqy6861ds7dco3txn0ioh3c` (`prtcl_inst_summary_id`),
  KEY `FKswwuixwogcl48lwy3ucvvqaub` (`growth_media_id`),
  CONSTRAINT `FK1rdptfp7u4obmb8eikuw5nnrf` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK2hvogurfkgktbxxvdo2csoa8v` FOREIGN KEY (`antibody_id`) REFERENCES `antibody` (`id`),
  CONSTRAINT `FK579rlyfhja2i87deo6dgndfnj` FOREIGN KEY (`cell_source_id`) REFERENCES `cell_source` (`id`),
  CONSTRAINT `FKbpci4r4ynak9f0hby2pmaw0bo` FOREIGN KEY (`send_data_to_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKcpc4hhf4ntlq02q576nu2ky44` FOREIGN KEY (`assay_id`) REFERENCES `assay` (`id`),
  CONSTRAINT `FKhflf768ds23ev1ywoqsp42p96` FOREIGN KEY (`target_id`) REFERENCES `target` (`id`),
  CONSTRAINT `FKo86ao5favegpdv2p45ekxus9o` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  CONSTRAINT `FKorx0c9myw8smxka4o3ir837ps` FOREIGN KEY (`spike_in_cell_source_id`) REFERENCES `cell_source` (`id`),
  CONSTRAINT `FKp8cqy6861ds7dco3txn0ioh3c` FOREIGN KEY (`prtcl_inst_summary_id`) REFERENCES `protocol_instance_summary` (`id`),
  CONSTRAINT `FKswwuixwogcl48lwy3ucvvqaub` FOREIGN KEY (`growth_media_id`) REFERENCES `growth_media` (`id`)
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
  `pool_date` datetime DEFAULT NULL,
  `sample_id` bigint(20) NOT NULL,
  `volume_to_pool` float DEFAULT NULL,
  `pool` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `run_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8f7674b6c279c21eb5c882b40655` (`run_id`,`sample_id`),
  KEY `FK50pk7cu1ix56x21n43qpmc9rs` (`sample_id`),
  CONSTRAINT `FK50pk7cu1ix56x21n43qpmc9rs` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`),
  CONSTRAINT `FKjroa7jvf2q9kf1f3fasxiy735` FOREIGN KEY (`run_id`) REFERENCES `sequence_run` (`id`)
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
  `sample_id` bigint(20) NOT NULL,
  `set_id` int(11) DEFAULT NULL,
  `index_in_set` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8dhe3xyoc3br0fr7uws3svyds` (`index_id`),
  KEY `FKtbax39c2nyeqk46yiwk6xj9l` (`sample_id`),
  CONSTRAINT `FK8dhe3xyoc3br0fr7uws3svyds` FOREIGN KEY (`index_id`) REFERENCES `sequence_index` (`id`),
  CONSTRAINT `FKtbax39c2nyeqk46yiwk6xj9l` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`)
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
  UNIQUE KEY `UK4b14a8d38495c6eec56d6123f6f5` (`treatment_id`,`sample_id`),
  KEY `FKrq3vygba0p5l99k8ej2msf758` (`sample_id`),
  CONSTRAINT `FKf2vfwolapiodnq3iy2hi4gs9a` FOREIGN KEY (`treatment_id`) REFERENCES `cell_source_treatment` (`id`),
  CONSTRAINT `FKrq3vygba0p5l99k8ej2msf758` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`)
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
  `read_db_id` int(11) DEFAULT NULL,
  `genome_id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `dedup_uniquely_mapped_reads` bigint(20) DEFAULT NULL,
  `pipeline_id` bigint(20) NOT NULL,
  `genome_coverage` float DEFAULT NULL,
  `sequencing_experiment_id` bigint(20) NOT NULL,
  `align_type_id` bigint(20) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `aligner_id` bigint(20) DEFAULT NULL,
  `bam_file` varchar(1000) DEFAULT NULL,
  `std_dev_insert_size` float DEFAULT NULL,
  `mapped_reads` bigint(20) DEFAULT NULL,
  `is_preferred` bit(1) NOT NULL DEFAULT b'1',
  `avg_insert_size` float DEFAULT NULL,
  `history_url` varchar(255) DEFAULT NULL,
  `pe_histogram` varchar(1000) DEFAULT NULL,
  `seq_duplication_level` float DEFAULT NULL,
  `params` varchar(2000) DEFAULT NULL,
  `history_id` varchar(255) NOT NULL,
  `uniquely_mapped_reads` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKef3609f74c61b7bd7d0ec184c0b0` (`pipeline_id`,`genome_id`,`sequencing_experiment_id`,`history_id`),
  KEY `FKdvg4g4a0pby4puygnvyof5cu4` (`sequencing_experiment_id`),
  KEY `FKm3h3wvtgyqcbyp7kaac35wj57` (`genome_id`),
  KEY `FKok3dqhynp748nr1ur1firmt14` (`aligner_id`),
  KEY `FKsw06tb5ys7obob4e2cpovuwr8` (`align_type_id`),
  CONSTRAINT `FKdvg4g4a0pby4puygnvyof5cu4` FOREIGN KEY (`sequencing_experiment_id`) REFERENCES `sequencing_experiment` (`id`),
  CONSTRAINT `FKm3h3wvtgyqcbyp7kaac35wj57` FOREIGN KEY (`genome_id`) REFERENCES `genome` (`id`),
  CONSTRAINT `FKok3dqhynp748nr1ur1firmt14` FOREIGN KEY (`aligner_id`) REFERENCES `aligner` (`id`),
  CONSTRAINT `FKq4d03x395u6vghiwp58qmqdf7` FOREIGN KEY (`pipeline_id`) REFERENCES `pipeline` (`id`),
  CONSTRAINT `FKsw06tb5ys7obob4e2cpovuwr8` FOREIGN KEY (`align_type_id`) REFERENCES `align_type` (`id`)
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
  `sequence` varchar(255) NOT NULL,
  `index_version` varchar(10) NOT NULL,
  `oligo` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `pool_item_id` bigint(20) DEFAULT NULL,
  `lane` int(11) DEFAULT NULL,
  `run_stats_id` bigint(20) DEFAULT NULL,
  `platform_id` bigint(20) NOT NULL,
  `directory_name` varchar(255) DEFAULT NULL,
  `fc_id` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `run_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_SEQUENCE_RUNRUN_NAME_COL` (`run_name`),
  KEY `FK5gbjvf2tscq7vyhwbadq2jbtx` (`user_id`),
  KEY `FKf27lk1j5r09jgtjl198kdtqje` (`run_stats_id`),
  KEY `FKjjf30r4ua6ru9e0ls0qhbwiei` (`platform_id`),
  KEY `FKmcddrdwrtqwy0h5wfiplislxj` (`pool_item_id`),
  CONSTRAINT `FK5gbjvf2tscq7vyhwbadq2jbtx` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKf27lk1j5r09jgtjl198kdtqje` FOREIGN KEY (`run_stats_id`) REFERENCES `run_stats` (`id`),
  CONSTRAINT `FKjjf30r4ua6ru9e0ls0qhbwiei` FOREIGN KEY (`platform_id`) REFERENCES `sequencing_platform` (`id`),
  CONSTRAINT `FKmcddrdwrtqwy0h5wfiplislxj` FOREIGN KEY (`pool_item_id`) REFERENCES `item` (`id`)
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
  `name` varchar(255) DEFAULT NULL,
  `images` longtext DEFAULT NULL,
  `notes` longtext DEFAULT NULL,
  `report_id` bigint(20) DEFAULT NULL,
  `run_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2kjc8ji0k7cxldfqvqqmr6qpq` (`project_id`),
  KEY `FKq03aoroe14bkb93n2jpcmfpn` (`report_id`),
  KEY `FKqe8146g7qcr91mhbhlnkowoq5` (`run_id`),
  CONSTRAINT `FK2kjc8ji0k7cxldfqvqqmr6qpq` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FKq03aoroe14bkb93n2jpcmfpn` FOREIGN KEY (`report_id`) REFERENCES `summary_report` (`id`),
  CONSTRAINT `FKqe8146g7qcr91mhbhlnkowoq5` FOREIGN KEY (`run_id`) REFERENCES `sequence_run` (`id`)
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
  `fastqc_report` varchar(1000) DEFAULT NULL,
  `fastq_file` varchar(1000) DEFAULT NULL,
  `index_mismatch` int(11) DEFAULT NULL,
  `read_type_id` bigint(20) DEFAULT NULL,
  `public_db_id` varchar(255) DEFAULT NULL,
  `adapter_dimer_count` bigint(20) DEFAULT NULL,
  `sample_id` bigint(20) NOT NULL,
  `sequence_run_id` bigint(20) DEFAULT NULL,
  `cohort_id` bigint(20) DEFAULT NULL,
  `total_reads` bigint(20) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `read_positions` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2a04ld6c4th452p48yqrqdhlq` (`sequence_run_id`),
  KEY `FKcb969swv4c6xbdb3kw93txww1` (`sample_id`),
  KEY `FKgbnh1t5mfymeuu3ltqk2q8kp0` (`read_type_id`),
  KEY `FKmqikne1qqkrnll81iu1sfre` (`cohort_id`),
  CONSTRAINT `FK2a04ld6c4th452p48yqrqdhlq` FOREIGN KEY (`sequence_run_id`) REFERENCES `sequence_run` (`id`),
  CONSTRAINT `FKcb969swv4c6xbdb3kw93txww1` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`id`),
  CONSTRAINT `FKgbnh1t5mfymeuu3ltqk2q8kp0` FOREIGN KEY (`read_type_id`) REFERENCES `read_type` (`id`),
  CONSTRAINT `FKmqikne1qqkrnll81iu1sfre` FOREIGN KEY (`cohort_id`) REFERENCES `sequencing_cohort` (`id`)
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
  UNIQUE KEY `UC_SEQUENCING_PLATFORMNAME_COL` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequencing_platform`
--

LOCK TABLES `sequencing_platform` WRITE;
/*!40000 ALTER TABLE `sequencing_platform` DISABLE KEYS */;
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
  UNIQUE KEY `UC_SEXNAME_COL` (`name`)
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
  `name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `genus_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3932af47b0357d2b4c5a261822ee` (`genus_name`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
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
  `source_lab_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `genotype` varchar(255) DEFAULT NULL,
  `species_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ouetcuudlbctcmfse7fuuuo8` (`parent_id`),
  KEY `FK6ikjjjvxicw58055xdk37tfj0` (`species_id`),
  KEY `FKrwpjbguvhieg265qb4sjmofnr` (`source_lab_id`),
  CONSTRAINT `FK2ouetcuudlbctcmfse7fuuuo8` FOREIGN KEY (`parent_id`) REFERENCES `strain` (`id`),
  CONSTRAINT `FK6ikjjjvxicw58055xdk37tfj0` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`),
  CONSTRAINT `FKrwpjbguvhieg265qb4sjmofnr` FOREIGN KEY (`source_lab_id`) REFERENCES `organization` (`id`)
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
  `version` bigint(20) NOT NULL,
  `date` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
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
  `name` varchar(255) DEFAULT NULL,
  `target_type_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `n_term_tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKc01a900836fd7e6df0dbdc887877` (`c_term_tag`,`n_term_tag`,`name`),
  KEY `FK7fd5o5g240p415qfh8ojmj9bo` (`target_type_id`),
  CONSTRAINT `FK7fd5o5g240p415qfh8ojmj9bo` FOREIGN KEY (`target_type_id`) REFERENCES `target_type` (`id`)
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
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_TARGET_TYPENAME_COL` (`name`)
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
  UNIQUE KEY `UC_TISSUENAME_COL` (`name`)
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
  `user_id` bigint(20) NOT NULL,
  `token` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe32ek7ixanakfqsdaokm4q9y2` (`user_id`),
  CONSTRAINT `FKe32ek7ixanakfqsdaokm4q9y2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
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
  `phone` varchar(20) DEFAULT NULL,
  `password_expired` bit(1) NOT NULL,
  `account_expired` bit(1) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `api_key` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `affiliation_id` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_USERUSERNAME_COL` (`username`),
  KEY `FKddefmvbrws3hvl5t0hnnsv8ox` (`address_id`),
  KEY `FKgrefofj2fru3hqquh5ep0mviq` (`affiliation_id`),
  CONSTRAINT `FKddefmvbrws3hvl5t0hnnsv8ox` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKgrefofj2fru3hqquh5ep0mviq` FOREIGN KEY (`affiliation_id`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,0,NULL,'\0','\0',NULL,NULL,'labadmin','\0','$2a$10$GUWb/bzco/gKZ./OMGvMLO6nC01beJMhd5.BPSn8MskzZ5E59WXde',NULL,'',NULL,NULL);
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
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
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
  KEY `FKexdgxebi2id0avjqhenhkmem9` (`user_id`),
  CONSTRAINT `FK4u9p3asv1hflckgxegcafajkm` FOREIGN KEY (`role_group_id`) REFERENCES `role_group` (`id`),
  CONSTRAINT `FKexdgxebi2id0avjqhenhkmem9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
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

-- Dump completed on 2021-03-22 18:23:41
