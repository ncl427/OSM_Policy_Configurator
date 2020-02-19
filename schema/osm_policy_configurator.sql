/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : osm_policy_configurator

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 13/02/2020 13:27:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ns_descriptor
-- ----------------------------
DROP TABLE IF EXISTS `ns_descriptor`;
CREATE TABLE `ns_descriptor`  (
  `nsDescriptorId` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `refId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `vnfDescriptorId` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`nsDescriptorId`) USING BTREE,
  INDEX `nsDescriptorId`(`nsDescriptorId`) USING BTREE,
  INDEX `nsDescriptorId_2`(`nsDescriptorId`) USING BTREE,
  INDEX `ns_descriptor_ibfk_1`(`vnfDescriptorId`) USING BTREE,
  CONSTRAINT `ns_descriptor_ibfk_1` FOREIGN KEY (`vnfDescriptorId`) REFERENCES `vnf_descriptor` (`vnfdescriptorid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ns_instance
-- ----------------------------
DROP TABLE IF EXISTS `ns_instance`;
CREATE TABLE `ns_instance`  (
  `nsInstanceId` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `refId` int(11) NULL DEFAULT NULL,
  `json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `nstDescriptorId` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`nsInstanceId`) USING BTREE,
  INDEX `ns_instance_ibfk_1`(`nstDescriptorId`) USING BTREE,
  CONSTRAINT `ns_instance_ibfk_1` FOREIGN KEY (`nstDescriptorId`) REFERENCES `nst_descriptor` (`nstdescriptorid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for nst_descriptor
-- ----------------------------
DROP TABLE IF EXISTS `nst_descriptor`;
CREATE TABLE `nst_descriptor`  (
  `nstDescriptorId` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `refId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `nsDescriptorId` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`nstDescriptorId`) USING BTREE,
  INDEX `nstDescriptorId`(`nstDescriptorId`) USING BTREE,
  INDEX `nst_descriptor_ibfk_1`(`nsDescriptorId`) USING BTREE,
  INDEX `nstDescriptorId_2`(`nstDescriptorId`) USING BTREE,
  CONSTRAINT `nst_descriptor_ibfk_1` FOREIGN KEY (`nsDescriptorId`) REFERENCES `ns_descriptor` (`nsdescriptorid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vnf_descriptor
-- ----------------------------
DROP TABLE IF EXISTS `vnf_descriptor`;
CREATE TABLE `vnf_descriptor`  (
  `vnfDescriptorId` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `refId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`vnfDescriptorId`) USING BTREE,
  INDEX `vnfDescriptorId`(`vnfDescriptorId`) USING BTREE,
  INDEX `vnfDescriptorId_2`(`vnfDescriptorId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
