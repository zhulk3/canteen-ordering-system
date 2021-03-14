/*
SQLyog v10.2 
MySQL - 5.5.27 : Database - canteen
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`canteen` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `canteen`;
/*
SQLyog v10.2 
MySQL - 5.5.27 : Database - canteen
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

USE `canteen`;

/*Table structure for table `admin_info` */

DROP TABLE IF EXISTS `admin_info`;

CREATE TABLE `admin_info` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

/*Data for the table `admin_info` */

insert  into `admin_info`(`id`,`name`,`pwd`) values (1,'firstcanteen','123456'),(2,'secondcanteen','123456'),(3,'thirdcanteen','123456'),(4,'fourthcanteen','123456'),(5,'fifthcanteen','123456'),(6,'adminstrationcanteen','123456');

/*Table structure for table `functions` */

DROP TABLE IF EXISTS `functions`;

CREATE TABLE `functions` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '功能菜单',
  `parentid` int(4) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `isleaf` bit(1) DEFAULT NULL,
  `nodeorder` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gbk;

/*Data for the table `functions` */

insert  into `functions`(`id`,`name`,`parentid`,`url`,`isleaf`,`nodeorder`) values (1,'中山大学食堂订餐管理后台',0,NULL,'\0',0),(2,'菜品管理',1,NULL,'\0',1),(3,'菜品列表',2,NULL,'',1),(4,'菜品类型列表',2,NULL,'',2),(5,'订单管理',1,NULL,'\0',2),(6,'查询订单',5,NULL,'',1),(7,'创建订单',5,NULL,'',2),(8,'客户管理',1,NULL,'\0',3),(9,'客户列表',8,NULL,'',1),(11,'退出系统',1,NULL,'',1);

/*Table structure for table `order_detail` */

DROP TABLE IF EXISTS `order_detail`;

CREATE TABLE `order_detail` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '订单明细id',
  `oid` int(4) DEFAULT NULL COMMENT '订单id',
  `pid` int(4) DEFAULT NULL COMMENT '产品id',
  `num` int(4) DEFAULT NULL COMMENT '购买数量',
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  KEY `oid` (`oid`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `order_info` (`id`),
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `product_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `order_detail` */

insert  into `order_detail`(`id`,`oid`,`pid`,`num`) values (1,1,1,1),(2,1,2,1),(3,2,4,1),(4,2,5,1),(5,2,8,1);

/*Table structure for table `order_info` */

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `uid` int(4) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `ordertime` datetime DEFAULT NULL,
  `orderprice` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `order_info_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `order_info` */

insert  into `order_info`(`id`,`uid`,`status`,`ordertime`,`orderprice`) values (1,1,'未付款','2018-05-12 00:00:00','10687.00'),(2,2,'已付款','2018-05-09 00:00:00','12997.00');

/*Table structure for table `powers` */

DROP TABLE IF EXISTS `powers`;

CREATE TABLE `powers` (
  `aid` int(4) NOT NULL,
  `fid` int(4) NOT NULL,
  PRIMARY KEY (`aid`,`fid`),
  KEY `fid` (`fid`),
  KEY `aid` (`aid`),
  CONSTRAINT `powers_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `admin_info` (`id`),
  CONSTRAINT `powers_ibfk_2` FOREIGN KEY (`fid`) REFERENCES `functions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `powers` */

insert  into `powers`(`aid`,`fid`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,11);

/*Table structure for table `product_info` */

DROP TABLE IF EXISTS `product_info`;

CREATE TABLE `product_info` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) DEFAULT NULL COMMENT '菜品编号',
  `name` varchar(255) DEFAULT NULL COMMENT '菜品名称',
  `tid` int(4) DEFAULT NULL COMMENT '菜品类别',
  `pic` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `num` int(4) unsigned zerofill DEFAULT NULL COMMENT '菜品库存',
  `price` decimal(10,0) unsigned zerofill DEFAULT NULL COMMENT '菜品小图',
  `intro` longtext COMMENT '菜品简介',
  `status` int(4) DEFAULT '1' COMMENT '菜品状态',
  PRIMARY KEY (`id`),
  KEY `tid` (`tid`),
  CONSTRAINT `product_info_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `product_info` */

insert  into `product_info`(`id`,`code`,`name`,`tid`,`pic`,`num`,`price`,`intro`,`status`) values 
(1,'1378538','鱼香肉丝',1,'1378538.jpg',0100,'0000004','菜品以泡辣椒，子姜，大蒜，糖和醋炒制猪里脊肉丝而成，由民国时期的一位川菜大厨所创制，相传灵感来自泡椒肉丝',1),
(2,'1309456','宫保鸡丁',1,'1309456.jpg',00200,'0000005','宫保鸡丁选用鸡肉为主料，佐以花生米、辣椒等辅料烹制而成。红而不辣、辣而不猛、香辣味浓、肉质滑脆',1),
(3,'1999938','红烧大虾',2,'1999938.jpg',00099,'0000005','对虾以其肉厚、味鲜、色美闻名',1),
(4,'1466274','四喜丸子',2,'1466274.jpg',0100,'0000004','由四个色、香、味俱佳的肉丸组成，寓人生福、禄、寿、喜四大喜事',1),
(5,'1981672','香芋扣肉',3,'1981672.jpg',0100,'0000005','即肉经炸后切成长方形块状，和芋头相间拼摆在海碗中，蒸透了再覆扣在大盘中上席',1),
(6,'1904696','红烧大裙翅',3,'1904696.jpg',0012,'0000004','为“翅王”吴銮首创。用裙翅炮制而成，故名。 原料众多，工序复杂，清脆爽滑，营养丰富，是传统粤菜的代表作',1),
(7,'751624','清蒸加力鱼',6,'751624.jpg',0100,'0000003','此鱼煎，炒、焖、灴、炸均宜，清蒸最妙，原质原味俱在，汤色清澈，鱼肉嫩滑，味道醇香',1),
(8,'977433','糖醋排骨',2,'977433.jpg',0100,'0000003','酸甜适中，不油不腻，口感丰富细腻，不会觉得任何一种调料的突兀',1),
(9,'1143562','王老吉',7,'1143562.jpg',0100,'0000003','固体凉茶、低糖凉茶和无糖凉茶，开创了凉茶产品新形态',1),
(10,'1560207','雪碧',7,'1560207.jpg',0100,'0000004','一款柠檬味汽水饮料，由可口可乐公司推出的第三个品牌',1),
(11,'1721668','西湖醋鱼',5,'1721668.jpg',0100,'0000004','色泽红亮，肉质鲜嫩，酸甜清香，口感软嫩，带有蟹味',1),
(12,'823125','东坡肉',6,'823125.jpg',0100,'0000005','成品菜都是码得整整齐齐的麻将块儿，红得透亮，色如玛瑙，夹起一块尝尝，软而不烂，肥而不腻',1);

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

/*Data for the table `type` */

insert  into `type`(`id`,`name`) values (1,'东海小鲜'),(2,'经典川湘'),(3,'海派家常'),(4,'魔都小菜'),(5,'鲜蔬靓汤'),(6,'甜品点心'),(7,'饮料');

DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
  
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*Table structure for table `user_info` */
insert  into `school`(`id`,`name`) values(1,'中国语言文学系'),(2,'历史学系'),(3,'管理学院'),(4,'心理学系'),(5,'计算机学院'),(6,'物理学院'),(7,'化学学院'),(8,'国际关系学院');
DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `userName` varchar(16) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `realName` varchar(8) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `regDate` date DEFAULT NULL,
  `status` int(4) DEFAULT '1',
  `sid` int(4) DEFAULT NULL COMMENT '学院',
  PRIMARY KEY (`id`),
  KEY `sid` (`sid`),
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `school` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */
insert  into `user_info`(`id`,`userName`,`password`,`realName`,`sex`,`address`,`email`,`regDate`,`status`,`sid`) values 
(1,'tom','123456','汤姆','女','慎思园八号楼','tom@123.com','2013-07-14',1,2),
(2,'john','123456','约翰','女','至善园6号楼','wen@135.com','2013-07-14',1,4),
(3,'my','123456','my','男','明德园7号楼','a@135.com','2015-09-16',1,5),
(4,'sj','123456','sj','男','慎思园3号楼','b@135.com','2015-09-16',1,4),
(5,'lxf','123456','lxf','男','格致园8号楼','c@135.com','2015-09-16',1,7),
(6,'lj','123456','lj','男','明德园2号楼','a@135.com','2015-09-20',1,8);/* Procedure structure for procedure `sp_sale` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_sale` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sale`(c int)
BEGIN	
	declare stmt varchar(2000);
	set @sqlstr=concat("SELECT p.id AS id, p.name AS NAME,SUM(od.num) AS total ,SUM(od.num)*price AS money
	FROM order_detail od, product_info p 
	WHERE p.id=od.p_id 
	GROUP BY p.id,p.name,p.price ORDER BY total DESC LIMIT 1,",c);
     prepare stmt from @sqlstr;
     execute stmt;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
