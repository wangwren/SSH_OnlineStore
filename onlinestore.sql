/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : onlinestore

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 19/06/2018 10:41:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adminuser
-- ----------------------------
DROP TABLE IF EXISTS `adminuser`;
CREATE TABLE `adminuser`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE,
  INDEX `FK302BCFE59951210`(`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for categorysecond
-- ----------------------------
DROP TABLE IF EXISTS `categorysecond`;
CREATE TABLE `categorysecond`  (
  `csid` int(11) NOT NULL AUTO_INCREMENT,
  `csname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`csid`) USING BTREE,
  INDEX `FK936FCAF21DB1FD15`(`cid`) USING BTREE,
  CONSTRAINT `FK936FCAF21DB1FD15` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) NULL DEFAULT NULL,
  `subtotal` double NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  `oid` int(11) NULL DEFAULT NULL,
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`itemid`) USING BTREE,
  INDEX `FKE8B2AB6166C01961`(`oid`) USING BTREE,
  INDEX `FKE8B2AB6171DB7AE4`(`pid`) USING BTREE,
  CONSTRAINT `FKE8B2AB6166C01961` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKE8B2AB6171DB7AE4` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `total` double NULL DEFAULT NULL,
  `ordertime` datetime(0) NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `addr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE,
  INDEX `FKC3DF62E5AA3D9C7`(`uid`) USING BTREE,
  CONSTRAINT `FKC3DF62E5AA3D9C7` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `market_price` double NULL DEFAULT NULL,
  `shop_price` double NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pdesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_hot` int(11) NULL DEFAULT NULL,
  `pdate` datetime(0) NULL DEFAULT NULL,
  `csid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `FKED8DCCEFB9B74E02`(`csid`) USING BTREE,
  CONSTRAINT `FKED8DCCEFB9B74E02` FOREIGN KEY (`csid`) REFERENCES `categorysecond` (`csid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for relationship
-- ----------------------------
DROP TABLE IF EXISTS `relationship`;
CREATE TABLE `relationship`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sizeid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sizeid`(`sizeid`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `relationship_ibfk_1` FOREIGN KEY (`sizeid`) REFERENCES `size` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `relationship_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for size
-- ----------------------------
DROP TABLE IF EXISTS `size`;
CREATE TABLE `size`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `XL` int(11) NULL DEFAULT NULL,
  `XXL` int(11) NULL DEFAULT NULL,
  `S` int(11) NULL DEFAULT NULL,
  `M` int(11) NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `size_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
