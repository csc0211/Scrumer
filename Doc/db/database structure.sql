/*
SQLyog Enterprise - MySQL GUI v6.56
MySQL - 5.6.15 : Database - scrumer
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`scrumer` /*!40100 DEFAULT CHARACTER SET utf8 */;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `story` */

DROP TABLE IF EXISTS `story`;

CREATE TABLE `story` (
  `id` varchar(36) NOT NULL,
  `projectid` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL DEFAULT '',
  `parentid` varchar(36) NOT NULL DEFAULT '0',
  `state` int(11) NOT NULL DEFAULT '0',
  `priority` int(11) NOT NULL DEFAULT '1',
  `creatorid` varchar(36) NOT NULL DEFAULT '',
  `createtime` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `story_user` */

DROP TABLE IF EXISTS `story_user`;

CREATE TABLE `story_user` (
  `id` varchar(36) NOT NULL,
  `storyid` varchar(36) NOT NULL,
  `userid` varchar(36) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  `starttime` varchar(30) NOT NULL DEFAULT '',
  `finishtime` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `storyid` (`storyid`,`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
