/*
 Navicat Premium Data Transfer

 Source Server         : Thinkpad
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : train

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 13/01/2018 14:47:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station`  (
  `station_id` int(11) NOT NULL AUTO_INCREMENT,
  `station_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`station_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of station
-- ----------------------------
INSERT INTO `station` VALUES (1, '北京');
INSERT INTO `station` VALUES (2, '杭州');
INSERT INTO `station` VALUES (3, '厦门');

-- ----------------------------
-- Table structure for station_train
-- ----------------------------
DROP TABLE IF EXISTS `station_train`;
CREATE TABLE `station_train`  (
  `train_id` int(11) NOT NULL,
  `station_id` int(255) NOT NULL,
  `distance` double NOT NULL,
  `arrive_time` time(0) NOT NULL,
  `leave_time` time(0) NOT NULL,
  PRIMARY KEY (`train_id`, `station_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of station_train
-- ----------------------------
INSERT INTO `station_train` VALUES (1, 1, 100, '10:00:00', '11:00:00');
INSERT INTO `station_train` VALUES (1, 2, 200, '11:05:00', '12:00:00');
INSERT INTO `station_train` VALUES (3, 2, 890, '11:00:00', '12:00:00');
INSERT INTO `station_train` VALUES (3, 3, 1000, '01:00:00', '00:00:01');

-- ----------------------------
-- Table structure for train
-- ----------------------------
DROP TABLE IF EXISTS `train`;
CREATE TABLE `train`  (
  `train_id` int(11) NOT NULL AUTO_INCREMENT,
  `train_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `train_route` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`train_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of train
-- ----------------------------
INSERT INTO `train` VALUES (1, 'G1', '1,2,5,6');
INSERT INTO `train` VALUES (2, 'G2', '3,5,8');
INSERT INTO `train` VALUES (3, 'G3', '1,2,4,7');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, '2', '2', 1);
INSERT INTO `user` VALUES (11, '1', '1', 0);

SET FOREIGN_KEY_CHECKS = 1;
