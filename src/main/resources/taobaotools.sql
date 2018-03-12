/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : taobaotools

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-03-12 19:51:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_analyzestorebill
-- ----------------------------
DROP TABLE IF EXISTS `t_analyzestorebill`;
CREATE TABLE `t_analyzestorebill` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `happen_date` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `catch_url` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `done_date` datetime DEFAULT NULL,
  `catch_remark` varchar(500) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_analyzestoredetail
-- ----------------------------
DROP TABLE IF EXISTS `t_analyzestoredetail`;
CREATE TABLE `t_analyzestoredetail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `analyze_store_bill_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_price` double DEFAULT NULL,
  `product_sale_quantity` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `product_taobao_id` varchar(255) DEFAULT NULL,
  `store_name` varchar(255) DEFAULT NULL,
  `source_data` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_authority`;
CREATE TABLE `t_authority` (
  `id` int(255) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `root_url` varchar(255) DEFAULT NULL COMMENT 'url路径名',
  `create_date_time` datetime NOT NULL COMMENT '创建时间',
  `operation_date_time` datetime NOT NULL COMMENT '操作时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_authoritydetail
-- ----------------------------
DROP TABLE IF EXISTS `t_authoritydetail`;
CREATE TABLE `t_authoritydetail` (
  `id` int(255) unsigned NOT NULL AUTO_INCREMENT,
  `head_id` int(255) unsigned NOT NULL COMMENT '权限模块Id',
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '可访问的url路径',
  `create_date_time` datetime NOT NULL COMMENT '创建时间',
  `operation_date_time` datetime NOT NULL COMMENT '操作时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_localcookie
-- ----------------------------
DROP TABLE IF EXISTS `t_localcookie`;
CREATE TABLE `t_localcookie` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(2048) DEFAULT NULL,
  `expires_at` bigint(20) DEFAULT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `secure` tinyint(1) DEFAULT NULL,
  `http_only` tinyint(1) DEFAULT NULL,
  `persistent` tinyint(1) DEFAULT NULL,
  `host_only` tinyint(1) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT '0',
  `type` varchar(255) DEFAULT NULL,
  `host` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_manyorderbill
-- ----------------------------
DROP TABLE IF EXISTS `t_manyorderbill`;
CREATE TABLE `t_manyorderbill` (
  `id` bigint(255) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(255) unsigned NOT NULL DEFAULT '0',
  `short_link` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `product_url` varchar(2048) DEFAULT NULL,
  `member_id` bigint(255) unsigned NOT NULL DEFAULT '0' COMMENT '会员Id',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_manyorderdetail
-- ----------------------------
DROP TABLE IF EXISTS `t_manyorderdetail`;
CREATE TABLE `t_manyorderdetail` (
  `id` bigint(255) unsigned NOT NULL AUTO_INCREMENT,
  `head_id` bigint(255) NOT NULL DEFAULT '0',
  `keywords` varchar(255) DEFAULT NULL,
  `limit_click_quantity` int(255) DEFAULT '0' COMMENT '限制点击量',
  `click_quantity` int(255) DEFAULT '0' COMMENT '已点击量',
  `click_count` int(255) DEFAULT '0' COMMENT '累计点击量',
  `product_url` varchar(2048) DEFAULT NULL,
  `taobao_word_id` bigint(20) DEFAULT NULL COMMENT '生成的淘口令Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `id` int(255) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `create_date_time` datetime NOT NULL COMMENT '创建时间',
  `operation_date_time` datetime NOT NULL COMMENT '操作时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `authority_id` int(255) DEFAULT NULL COMMENT '权限模块Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_productcatchlist
-- ----------------------------
DROP TABLE IF EXISTS `t_productcatchlist`;
CREATE TABLE `t_productcatchlist` (
  `keywords` varchar(255) DEFAULT NULL,
  `happen_date` date DEFAULT NULL,
  `product_ids` varchar(3000) DEFAULT NULL,
  `id` int(255) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_stuckfirstscreenbill
-- ----------------------------
DROP TABLE IF EXISTS `t_stuckfirstscreenbill`;
CREATE TABLE `t_stuckfirstscreenbill` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `keywords` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `taobao_word_id` bigint(20) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `competitor` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_taobaoshop
-- ----------------------------
DROP TABLE IF EXISTS `t_taobaoshop`;
CREATE TABLE `t_taobaoshop` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `lately_capture_date` datetime DEFAULT NULL,
  `total_sale_quantity` int(11) DEFAULT NULL,
  `total_sale` double(14,4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_taobaoshopcapturebill
-- ----------------------------
DROP TABLE IF EXISTS `t_taobaoshopcapturebill`;
CREATE TABLE `t_taobaoshopcapturebill` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `shop_url` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `done_date` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `removed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_taobaoshopproduct
-- ----------------------------
DROP TABLE IF EXISTS `t_taobaoshopproduct`;
CREATE TABLE `t_taobaoshopproduct` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `p_id` varchar(255) DEFAULT NULL,
  `price` double(14,4) DEFAULT NULL,
  `total_sale_quantity` int(11) DEFAULT NULL,
  `total_comment_quantity` int(11) DEFAULT NULL,
  `total_sale` double(14,4) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `happen_date` datetime DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_taobaoword
-- ----------------------------
DROP TABLE IF EXISTS `t_taobaoword`;
CREATE TABLE `t_taobaoword` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `text` varchar(255) DEFAULT NULL COMMENT '淘口令标题内容',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `logo_url` varchar(255) DEFAULT NULL,
  `ext` varchar(255) DEFAULT NULL,
  `url` varchar(2048) DEFAULT NULL,
  `tpwd` varchar(255) DEFAULT NULL COMMENT '淘口令',
  `failure_date` datetime DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_taobaowordbill
-- ----------------------------
DROP TABLE IF EXISTS `t_taobaowordbill`;
CREATE TABLE `t_taobaowordbill` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `taobao_word_id` bigint(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `url` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(255) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `create_date_time` datetime NOT NULL COMMENT '创建时间',
  `operation_date_time` datetime NOT NULL COMMENT '操作时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `authority_id` int(255) DEFAULT NULL COMMENT '权限Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
