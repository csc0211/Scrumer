/*
SQLyog Enterprise - MySQL GUI v6.56
MySQL - 5.6.25 : Database - scrumer
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`scrumer` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `scrumer`;

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` varchar(36) NOT NULL,
  `teamid` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL DEFAULT '',
  `creatorid` varchar(36) NOT NULL DEFAULT '',
  `createtime` varchar(50) NOT NULL DEFAULT '',
  `state` int(11) NOT NULL DEFAULT '0',
  `sectionid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert  into `project`(`id`,`teamid`,`name`,`description`,`creatorid`,`createtime`,`state`,`sectionid`) values ('1','1','Scrumer��Ŀ','���ݿ�����Ŀʵ��','1','2015-08-18 12:56:00',0,'1'),('2','1','��������','ÿһ����ÿһ��С��, Ը���к����������û�У�Ը���ڲ�����ѧ��ȱ���Ը�㱻�ܶ��˰������û�У�Ը���ڼ�į��ѧ����ݡ�Ը��һ��һ��ÿ�춼����˯����Ȼ�ѡ�','1','2015-08-18 12:56:00',0,'1'),('3','1','�������','���϶��飬����ʹ�˸�������','1','2015-08-18 12:56:00',0,'1'),('4','1','������·','����Ӧ����һ��˵�߾��ߵ�����','1','2015-08-18 12:56:00',0,'1');

/*Table structure for table `state` */

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `id` varchar(36) NOT NULL,
  `statevalue` int(11) NOT NULL DEFAULT '0',
  `statename` varchar(50) NOT NULL,
  `ispool` tinyint(4) NOT NULL DEFAULT '0',
  `sectionid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `state` */

insert  into `state`(`id`,`statevalue`,`statename`,`ispool`,`sectionid`) values ('1',0,'������',1,'1'),('2',1,'������',0,'1'),('3',2,'������',1,'1'),('4',3,'������',0,'1'),('5',4,'�����',1,'1');

/*Table structure for table `story` */

DROP TABLE IF EXISTS `story`;

CREATE TABLE `story` (
  `id` varchar(36) NOT NULL,
  `projectid` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL DEFAULT '',
  `stateid` varchar(36) NOT NULL,
  `priority` int(11) NOT NULL DEFAULT '1',
  `creatorid` varchar(36) NOT NULL DEFAULT '',
  `createtime` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `story` */

insert  into `story`(`id`,`projectid`,`name`,`description`,`stateid`,`priority`,`creatorid`,`createtime`) values ('1','1','��¼������','��½��������Ҫ��ʾ������','1',1,'1','2015-8-18 13:00:00'),('2','1','����','������ö���MD5����','1',1,'1','2015-8-18 13:00:00');

/*Table structure for table `story_user` */

DROP TABLE IF EXISTS `story_user`;

CREATE TABLE `story_user` (
  `id` varchar(36) NOT NULL,
  `storyid` varchar(36) NOT NULL,
  `userid` varchar(36) NOT NULL,
  `stateid` varchar(36) NOT NULL,
  `starttime` varchar(30) NOT NULL DEFAULT '',
  `finishtime` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `storyid` (`storyid`,`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `story_user` */

/*Table structure for table `team` */

DROP TABLE IF EXISTS `team`;

CREATE TABLE `team` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL DEFAULT '',
  `creatorid` varchar(36) NOT NULL DEFAULT '',
  `createtime` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `team` */

insert  into `team`(`id`,`name`,`description`,`creatorid`,`createtime`) values ('1','һ���','','1','2015-08-18 12:54:00');

/*Table structure for table `team_user` */

DROP TABLE IF EXISTS `team_user`;

CREATE TABLE `team_user` (
  `id` varchar(36) NOT NULL,
  `teamid` varchar(36) NOT NULL,
  `userid` varchar(36) NOT NULL,
  `jointime` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `teamid` (`teamid`,`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `team_user` */

insert  into `team_user`(`id`,`teamid`,`userid`,`jointime`) values ('1','1','1','2015-8-18 13:00:00');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `tel` varchar(20) NOT NULL DEFAULT '',
  `email` varchar(50) NOT NULL DEFAULT '',
  `qq` varchar(50) NOT NULL DEFAULT '',
  `weibo` varchar(50) NOT NULL DEFAULT '',
  `weixin` varchar(50) NOT NULL DEFAULT '',
  `registertime` varchar(50) NOT NULL DEFAULT '',
  `photourl` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`password`,`tel`,`email`,`qq`,`weibo`,`weixin`,`registertime`,`photourl`) values ('1','Mimo','123456','13880019261','yixiu.tan@gmail.com','','','','','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
