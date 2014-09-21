-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.30-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema hastane
--

CREATE DATABASE IF NOT EXISTS hastane;
USE hastane;

--
-- Definition of table `hasta_bilgi`
--

DROP TABLE IF EXISTS `hasta_bilgi`;
CREATE TABLE `hasta_bilgi` (
  `h_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `h_adi` varchar(20) NOT NULL,
  `h_soyadi` varchar(20) NOT NULL,
  `tc` varchar(11) NOT NULL,
  `d_yeri` varchar(30) NOT NULL,
  `d_yili` int(11) NOT NULL,
  `kurumu` varchar(45) NOT NULL,
  PRIMARY KEY (`h_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hasta_bilgi`
--

/*!40000 ALTER TABLE `hasta_bilgi` DISABLE KEYS */;
INSERT INTO `hasta_bilgi` (`h_id`,`h_adi`,`h_soyadi`,`tc`,`d_yeri`,`d_yili`,`kurumu`) VALUES 
 (1,'Hasancan','Özcan','14856972364','Istanbul',1994,'SGK'),
 (2,'Ahmet','Çetin','85632176932','Sinop',1994,'SGK'),
 (3,'Anil','Ercan','45628136879','Ankara',1975,'Emekli Sandigi'),
 (4,'Tolga','Han','36521859752','Samsun',1980,'SGK'),
 (5,'Hakan','Ak','42789986451','Istanbul',1990,'SGK'),
 (6,'Mehmet','Temiz','45645632137','Agri',1980,'SGK'),
 (7,'Ali','Türk','43486213789','Van',1993,'SGK'),
 (8,'Erkut','Tek','21348645871','Izmir',1991,'SGK'),
 (9,'Salih','Maraz','45678132674','Ankara',1971,'Emekli Sandigi'),
 (10,'Zafer','Üzüm','12387456484','Trabzon',1976,'Emekli Sandigi'),
 (11,'Selda','Kor','68736123213','Tekirdag',1977,'SGK'),
 (12,'Vildan','Sarar','45789213346','Izmir',1965,'Emekli Sandigi'),
 (13,'Cagatay','Atsiz','78654368731','Aydin',1963,'Emekli Sandigi'),
 (14,'Yasin','Çorak','45678128764','Düzce',1985,'SGK'),
 (15,'Muge','Sari','78912321846','Antalya',1979,'SGK'),
 (16,'Emrah','Bagcan','77985651230','Kars',1969,'Emekli Sandigi'),
 (17,'Nihat','Kara','15304562045','Istanbul',1972,'Emekli Sandigi'),
 (18,'Merve','Aslan','41304806473','Adana',1994,'SGK'),
 (19,'Sercan','Siyah','10023010408','Canakkale',1993,'SGK'),
 (20,'Emre','Tok','53210840666','Kocaeli',1992,'SGK'),
 (21,'jTextField1','jTextField2','12312312311','jTextField4',1111,'jTextField6');
/*!40000 ALTER TABLE `hasta_bilgi` ENABLE KEYS */;


--
-- Definition of table `tahliller`
--

DROP TABLE IF EXISTS `tahliller`;
CREATE TABLE `tahliller` (
  `t_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sira_no` int(10) unsigned NOT NULL,
  `tc` varchar(11) NOT NULL,
  `t_adi` varchar(30) NOT NULL,
  `t_kodu` varchar(45) NOT NULL,
  `t_tarihi` varchar(50) DEFAULT NULL,
  `t_fiyati` int(10) unsigned NOT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tahliller`
--

/*!40000 ALTER TABLE `tahliller` DISABLE KEYS */;
INSERT INTO `tahliller` (`t_id`,`sira_no`,`tc`,`t_adi`,`t_kodu`,`t_tarihi`,`t_fiyati`) VALUES 
 (1,1,'14856972364','Kan','10','11.01.2011',10),
 (2,2,'85632176932','Kan','27','20.05.2011',10),
 (3,3,'45628136879','Idrar','33','06.07.2010',10),
 (4,4,'36521859752','Kan','25','05.05.2010',5),
 (5,5,'42789986451','Kan','15','15.02.2011',10),
 (6,6,'45645632137','Kan','37','27.09.2011',10),
 (7,7,'43486213789','Idrar','43','06.06.2011',20),
 (8,8,'21348645871','Kan','12','21.03.2010',5),
 (9,9,'45678132674','Kan','41','04.05.2010',5),
 (10,10,'12387456484','Kan','65','12.03.2011',10),
 (11,11,'68736123213','Kan','35','06.08.2011',10),
 (12,12,'45789213346','Idrar','61','04.08.2010',10),
 (13,13,'78654368731','Kan','52','07.12.2009',3),
 (14,14,'45678128764','Kan','53','12.12.2011',10),
 (15,15,'78912321846','Kan','47','13.12.2010',5),
 (16,16,'77985651230','Kan','55','15.01.2010',5),
 (17,17,'15304562045','Kan','49','30.11.2011',10),
 (18,18,'41304806473','Idrar','59','28.11.2011',20),
 (19,19,'10023010408','Kan','67','10.02.2010',5),
 (20,20,'53210840666','Kan','71','21.08.2011',10),
 (21,44,'12312312312','jTextField3','jTextField4','jTextField5',15);
/*!40000 ALTER TABLE `tahliller` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
