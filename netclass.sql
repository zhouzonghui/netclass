-- MySQL dump 10.13  Distrib 5.1.68, for Win64 (unknown)
--
-- Host: localhost    Database: netclass
-- ------------------------------------------------------
-- Server version	5.1.68-community

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
-- Table structure for table `t_class`
--

DROP TABLE IF EXISTS `t_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_class` (
  `c_id` int(2) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(20) NOT NULL,
  `c_time` int(2) NOT NULL,
  PRIMARY KEY (`c_id`),
  KEY `c_id` (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_class`
--

LOCK TABLES `t_class` WRITE;
/*!40000 ALTER TABLE `t_class` DISABLE KEYS */;
INSERT INTO `t_class` VALUES (1,'计算机网络',1234),(2,'操作系统',2143);
/*!40000 ALTER TABLE `t_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_file`
--

DROP TABLE IF EXISTS `t_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_file` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(100) NOT NULL,
  `f_url` varchar(500) NOT NULL,
  `f_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_file`
--

LOCK TABLES `t_file` WRITE;
/*!40000 ALTER TABLE `t_file` DISABLE KEYS */;
INSERT INTO `t_file` VALUES (1,'安卓.txt','E:\\tomcat4me2014\\apache-tomcat-7.0.61\\webapps\\netclass\\files\\安卓.txt','2015-05-20 07:41:52'),(3,'6 毕业设计(论文)中期检查表.doc','E:\\tomcat4me2014\\apache-tomcat-7.0.61\\webapps\\netclass\\files\\6 毕业设计(论文)中期检查表.doc','2015-05-20 08:56:43'),(4,'云计算下教学互动平台的探究和设计_薛嘉.caj','E:\\tomcat4me2014\\apache-tomcat-7.0.61\\webapps\\netclass\\files\\云计算下教学互动平台的探究和设计_薛嘉.caj','2015-05-20 09:04:15');
/*!40000 ALTER TABLE `t_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_notice`
--

DROP TABLE IF EXISTS `t_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_notice` (
  `n_id` int(10) NOT NULL AUTO_INCREMENT,
  `n_info` varchar(2047) NOT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_notice`
--

LOCK TABLES `t_notice` WRITE;
/*!40000 ALTER TABLE `t_notice` DISABLE KEYS */;
INSERT INTO `t_notice` VALUES (1,'fsadfadsfdasfsadfasd'),(2,'fasfdsafadsfasfdsfdsf231'),(3,'旅途中的风景在我们的前行的路上不断变换，看到长颈鹿温馨的画面，相濡以沫，彼此疼惜，成为我最美好也是最难忘的风景线，告诉我们怎样去爱一个人。旅行，可以让人忘记烦恼，旅行，不在乎你们走多远，而是陪在你身边的人懂你，一直疼爱着你，那么人生的风景里就不会再孤单。'),(4,'44444444444444444'),(5,'收拾简单的行李，来一场说走就走的周末旅行。背着包，我们踏上火车去南昌动物园。下了火车有点远，车饶了几条街，终于到达地点，映入眼帘的是平躺的牛木桩，雕刻着红色字体“南昌动物园”，心中不免有点兴奋。'),(6,'不去想，你深邃的眼眸里究竟藏着多少期待与感伤？不去问春去夏来，谁又会在你安静的心湖里泛起涟漪？只想将淡淡的喜忧，安放于清婉的字里行间。我的想念与寂寞里，有最真的你自己。因了这份懂得，我愿做莲一样的女子，守着有你的一片心空，寂静清芬。'),(7,'月色溶溶的夜晚，看窗外花影稀疏，樱花落雨，想着你披一身月色，引清风拾阶而上。在窗前无语凝笑，看我将万千心事调墨为羹。促膝而坐，只点一盏袅袅的灯，笼一怀惺惺相惜的情'),(8,'时光如水，一不小心又走过了一个花期，你是否还保留那年那时我拍给你梅开时的照片，你是否依旧把她珍藏在心底？掌心的花，浅浅的盛开，缠绵着心湖，带着些许忧伤，些许甜美，些许希冀，清新脱俗的季节，醉了日夜萦绕在心间的期盼。'),(9,'白落梅说：情到深时，总免不了问一句：“为什么要让我遇到你。”是啊，假如没有相遇，我也只是一粒平凡的尘土，每天为了生活忙忙碌碌，湮没在茫茫人海中。因为有了相遇，一切开始改变，有了责任和担当，有了喜悦和痛苦。所以有时候宁可不要相遇，宁可一生不要握手，但人生倘若没有相遇，又将会是多么的索然无味。'),(10,'依在四月的眉弯，捻一缕花香，萦绕在心底的是你的懂得，寂寥的心里，满是疼惜，想起院子里的榆叶梅此时已是花开茶靡，落下的花瓣再也无人拾起，不免有一丝感伤滑过心间，想着花开时那淡淡的粉白，满院子都是清香的气息。'),(11,'终究是四月的天气，即便是下着蒙蒙细雨，空气中依旧泛着融融的暖意，清风送爽，草木拔节生长，山川披上了崭新的绿衣，色彩缤纷的花儿争奇斗艳，给温情的四月增添了诗意。'),(13,'教学互动系统后台管理教学互动系统后台管理教学互动系统后台管理教学互动系统后台管理教学互动系统后台管理');
/*!40000 ALTER TABLE `t_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_question`
--

DROP TABLE IF EXISTS `t_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_question` (
  `q_id` int(11) NOT NULL AUTO_INCREMENT,
  `q_title` varchar(60) NOT NULL,
  `q_content` varchar(2047) NOT NULL,
  `q_student_id` varchar(20) NOT NULL,
  `q_answer` varchar(2047) DEFAULT '',
  `q_isanswered` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`q_id`),
  KEY `fk2` (`q_student_id`),
  CONSTRAINT `fk2` FOREIGN KEY (`q_student_id`) REFERENCES `t_student` (`s_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_question`
--

LOCK TABLES `t_question` WRITE;
/*!40000 ALTER TABLE `t_question` DISABLE KEYS */;
INSERT INTO `t_question` VALUES (1,'不知道作业','回哦去我看看书店长相信心','201102020144','',0),(2,'111','了旅途斤斤计较空落落具体体检健健康康了','201102020144','',0);
/*!40000 ALTER TABLE `t_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_student`
--

DROP TABLE IF EXISTS `t_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_student` (
  `s_id` varchar(20) NOT NULL,
  `s_name` varchar(20) NOT NULL,
  `s_password` varchar(40) NOT NULL DEFAULT '827ccb0eea8a706c4c34a16891f84e7b',
  `s_gender` varchar(1) NOT NULL,
  `s_score` float(4,1) DEFAULT '0.0',
  `s_class_id` int(2) NOT NULL,
  PRIMARY KEY (`s_id`),
  KEY `fk1` (`s_class_id`),
  CONSTRAINT `fk1` FOREIGN KEY (`s_class_id`) REFERENCES `t_class` (`c_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_student`
--

LOCK TABLES `t_student` WRITE;
/*!40000 ALTER TABLE `t_student` DISABLE KEYS */;
INSERT INTO `t_student` VALUES ('201102020144','王五','9336ebf25087d91c818ee6e9ec29f8c1','m',78.0,1),('201102020314','张三','827ccb0eea8a706c4c34a16891f84e7b','f',0.0,1),('201102020315','李四','827ccb0eea8a706c4c34a16891f84e7b','m',56.0,1),('201102020320','赵六','827ccb0eea8a706c4c34a16891f84e7b','f',0.0,1);
/*!40000 ALTER TABLE `t_student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-20 20:55:59
