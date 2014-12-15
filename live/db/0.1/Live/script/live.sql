/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.32
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : live

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2014-12-15 17:57:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for buyrecored
-- ----------------------------
DROP TABLE IF EXISTS `buyrecored`;
CREATE TABLE `buyrecored` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `userid` bigint(11) NOT NULL COMMENT '用户id',
  `goodsid` bigint(20) NOT NULL COMMENT '商品id',
  `goodsname` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `costcoin` bigint(255) DEFAULT NULL COMMENT '话费钢镚',
  `createtime` datetime DEFAULT NULL COMMENT '消费时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buyrecored
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `quantity` bigint(11) DEFAULT NULL COMMENT '数量',
  `unit` varchar(5) DEFAULT NULL COMMENT '单位',
  `coin` bigint(11) DEFAULT NULL COMMENT '价钱(需要花费的钢镚数量）',
  `desc` varchar(500) DEFAULT NULL COMMENT '商品描述',
  `classify` tinyint(2) DEFAULT NULL COMMENT '商品分类',
  `isdelete` tinyint(2) DEFAULT NULL COMMENT '是否删除，0：未删除，1：已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for lvuser
-- ----------------------------
DROP TABLE IF EXISTS `lvuser`;
CREATE TABLE `lvuser` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) NOT NULL COMMENT '登录名',
  `nick` varchar(20) DEFAULT NULL COMMENT '昵称',
  `idxcode` varchar(15) DEFAULT NULL COMMENT '靓号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `userfrom` tinyint(2) DEFAULT NULL COMMENT '用户注册渠道，1：本平台，2：QQ',
  `oauthid` varchar(16) DEFAULT NULL COMMENT '第三方平台ID',
  `coin` bigint(11) DEFAULT NULL COMMENT '用户钢镚数量',
  `praise` bigint(11) DEFAULT NULL COMMENT '用户被赞次数',
  `photo` varchar(150) DEFAULT NULL COMMENT '用户头像地址（相对地址）',
  `mail` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(15) DEFAULT NULL COMMENT '用户电话',
  `qq` varchar(15) DEFAULT NULL COMMENT '用户QQ帐号',
  `flag` int(11) DEFAULT NULL COMMENT '扩展标位',
  `status` tinyint(2) DEFAULT NULL COMMENT '用户状态，1：启用，2：禁用',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lvuser
-- ----------------------------
INSERT INTO `lvuser` VALUES ('1', 'aaa', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `lvuser` VALUES ('2', 'bbb', null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for lvuserinfo
-- ----------------------------
DROP TABLE IF EXISTS `lvuserinfo`;
CREATE TABLE `lvuserinfo` (
  `id` bigint(11) NOT NULL COMMENT '对应userid',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sign` varchar(500) DEFAULT NULL COMMENT '个性签名',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lvuserinfo
-- ----------------------------

-- ----------------------------
-- Table structure for playrecored
-- ----------------------------
DROP TABLE IF EXISTS `playrecored`;
CREATE TABLE `playrecored` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `userid` bigint(11) DEFAULT NULL COMMENT '用户id',
  `idxcode` varchar(15) DEFAULT NULL COMMENT '靓号（冗余）',
  `usernick` varchar(20) DEFAULT NULL COMMENT '昵称（冗余）',
  `videoid` bigint(11) DEFAULT NULL COMMENT '视频ID',
  `videoname` varchar(100) DEFAULT NULL COMMENT '视频名称',
  `praise` bigint(11) DEFAULT NULL COMMENT '赞数',
  `starttime` datetime DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `playseconds` bigint(11) DEFAULT NULL COMMENT '播放秒数（endtime - starttime）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of playrecored
-- ----------------------------

-- ----------------------------
-- Table structure for praiserecored
-- ----------------------------
DROP TABLE IF EXISTS `praiserecored`;
CREATE TABLE `praiserecored` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `userid` bigint(11) DEFAULT NULL COMMENT '点赞用户id',
  `ownerid` bigint(11) DEFAULT NULL COMMENT '被赞用户id',
  `videoid` bigint(11) DEFAULT NULL COMMENT '被赞视频id',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of praiserecored
-- ----------------------------

-- ----------------------------
-- Table structure for roomsetting
-- ----------------------------
DROP TABLE IF EXISTS `roomsetting`;
CREATE TABLE `roomsetting` (
  `id` bigint(11) NOT NULL COMMENT '对应userid',
  `loginname` varchar(20) DEFAULT NULL COMMENT '登录名（冗余）',
  `backgrondimg` varchar(150) DEFAULT NULL COMMENT '房间背景图片地址（相对路径）',
  `roomname` varchar(500) DEFAULT NULL COMMENT '房间名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roomsetting
-- ----------------------------

-- ----------------------------
-- Table structure for userprops
-- ----------------------------
DROP TABLE IF EXISTS `userprops`;
CREATE TABLE `userprops` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `userid` bigint(11) DEFAULT NULL COMMENT '用户id',
  `propstype` tinyint(2) DEFAULT NULL COMMENT '物品类型,1：VIP ， 2：靓号',
  `propsname` varchar(255) DEFAULT NULL COMMENT '物品名称',
  `expirationtime` datetime DEFAULT NULL COMMENT '失效时间',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userprops
-- ----------------------------

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `videofrom` tinyint(2) DEFAULT NULL COMMENT '视频来源，1：优酷',
  `fromid` varchar(50) DEFAULT NULL COMMENT '视频来源网站ID',
  `videoname` varchar(100) DEFAULT NULL COMMENT '视频名称',
  `videoimg` varchar(150) DEFAULT NULL COMMENT '视频图片',
  `videolength` bigint(11) DEFAULT NULL COMMENT '视频时长（秒数）',
  `praise` bigint(11) DEFAULT NULL COMMENT '视频被赞次数',
  `flag` int(11) DEFAULT NULL COMMENT '扩展标位',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video
-- ----------------------------
