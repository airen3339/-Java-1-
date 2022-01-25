/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50537
 Source Host           : localhost:3306
 Source Schema         : sys_wygl

 Target Server Type    : MySQL
 Target Server Version : 50537
 File Encoding         : 65001

 Date: 25/01/2022 20:55:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fee_park
-- ----------------------------
DROP TABLE IF EXISTS `fee_park`;
CREATE TABLE `fee_park`  (
  `park_fee_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `park_id` int(11) NULL DEFAULT NULL COMMENT '车位id',
  `pay_park_month` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '缴费年月',
  `pay_park_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '缴费金额',
  `pay_park_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0:未缴费  1：已缴费',
  `pay_park_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`park_fee_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fee_park
-- ----------------------------
INSERT INTO `fee_park` VALUES (1, 34, 13, '2022-02', 100.00, '1', '2022-01-25 20:27:42');

-- ----------------------------
-- Table structure for fee_power
-- ----------------------------
DROP TABLE IF EXISTS `fee_power`;
CREATE TABLE `fee_power`  (
  `power_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int(11) NULL DEFAULT NULL COMMENT '房屋id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `pay_power_month` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '缴费年月',
  `pay_power_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '缴费金额',
  `power_num` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '表显',
  `pay_power_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0:未缴费  1：已缴费',
  `pay_power_time` datetime NULL DEFAULT NULL COMMENT '缴费时间',
  PRIMARY KEY (`power_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fee_power
-- ----------------------------
INSERT INTO `fee_power` VALUES (35, 26, 34, '2022-01', 100.00, '1', '1', '2022-01-25 20:26:45');

-- ----------------------------
-- Table structure for fee_water
-- ----------------------------
DROP TABLE IF EXISTS `fee_water`;
CREATE TABLE `fee_water`  (
  `water_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int(11) NULL DEFAULT NULL COMMENT '房屋id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `pay_water_month` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '缴费年月',
  `pay_water_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '缴费金额',
  `water_num` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '表显',
  `pay_water_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0:未缴费  1：已缴费',
  `pay_water_time` datetime NULL DEFAULT NULL COMMENT '缴费时间',
  PRIMARY KEY (`water_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for house_building
-- ----------------------------
DROP TABLE IF EXISTS `house_building`;
CREATE TABLE `house_building`  (
  `build_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '栋数id',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '栋数类型 0：普通房 1：电梯房',
  `build_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '栋数名称',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`build_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of house_building
-- ----------------------------
INSERT INTO `house_building` VALUES (11, '1', 'A栋', 1);
INSERT INTO `house_building` VALUES (12, '0', 'B栋', 1);
INSERT INTO `house_building` VALUES (13, '0', 'C栋', 3);
INSERT INTO `house_building` VALUES (14, '1', 'E栋', 4);

-- ----------------------------
-- Table structure for house_list
-- ----------------------------
DROP TABLE IF EXISTS `house_list`;
CREATE TABLE `house_list`  (
  `house_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房屋id',
  `unit_id` int(11) NULL DEFAULT NULL COMMENT '单元id',
  `house_num` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '房屋编号',
  `house_area` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '房屋面积',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '使用状态 0:未使用 1：已使用',
  PRIMARY KEY (`house_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of house_list
-- ----------------------------
INSERT INTO `house_list` VALUES (23, 20, '1', '123', '0');
INSERT INTO `house_list` VALUES (26, 22, '101', '123', '0');

-- ----------------------------
-- Table structure for house_unit
-- ----------------------------
DROP TABLE IF EXISTS `house_unit`;
CREATE TABLE `house_unit`  (
  `unit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '单元id',
  `build_id` int(11) NULL DEFAULT NULL COMMENT '栋数id',
  `unit_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单元名称',
  PRIMARY KEY (`unit_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of house_unit
-- ----------------------------
INSERT INTO `house_unit` VALUES (20, 11, 'a');
INSERT INTO `house_unit` VALUES (21, 13, 'c');
INSERT INTO `house_unit` VALUES (22, 14, 'E101');

-- ----------------------------
-- Table structure for live_house
-- ----------------------------
DROP TABLE IF EXISTS `live_house`;
CREATE TABLE `live_house`  (
  `live_house_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `house_id` int(11) NULL DEFAULT NULL COMMENT '房屋id',
  `use_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '0:使用中 1： 解绑、退房',
  PRIMARY KEY (`live_house_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of live_house
-- ----------------------------
INSERT INTO `live_house` VALUES (30, 34, 26, '1');
INSERT INTO `live_house` VALUES (31, 34, 23, '1');

-- ----------------------------
-- Table structure for live_park
-- ----------------------------
DROP TABLE IF EXISTS `live_park`;
CREATE TABLE `live_park`  (
  `live_park_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `park_id` int(11) NULL DEFAULT NULL COMMENT '车位id',
  `live_statue` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '0:使用 1：解绑、退车位状态',
  PRIMARY KEY (`live_park_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of live_park
-- ----------------------------
INSERT INTO `live_park` VALUES (50, 34, 13, '1');

-- ----------------------------
-- Table structure for live_role
-- ----------------------------
DROP TABLE IF EXISTS `live_role`;
CREATE TABLE `live_role`  (
  `liv_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  PRIMARY KEY (`liv_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of live_role
-- ----------------------------
INSERT INTO `live_role` VALUES (38, 17, 34);

-- ----------------------------
-- Table structure for live_user
-- ----------------------------
DROP TABLE IF EXISTS `live_user`;
CREATE TABLE `live_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '业主id',
  `username` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '登录账号',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '业主电话',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '性别 0：男 1：女',
  `login_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '登录密码',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT ' 0：启用 1：禁用',
  `is_account_non_expired` int(255) NULL DEFAULT NULL COMMENT '帐户是否过期(1 未过期，0已过期)	',
  `is_account_non_locked` int(255) NULL DEFAULT NULL COMMENT ' 帐户是否被锁定(1 未过期，0已过期)	',
  `is_credentials_non_expired` int(255) NULL DEFAULT NULL COMMENT '密码是否过期(1 未过期，0已过期)	',
  `is_enabled` int(255) NULL DEFAULT NULL COMMENT '帐户是否可用(1 可用，0 删除用户)	',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of live_user
-- ----------------------------
INSERT INTO `live_user` VALUES (34, 'lisi', '17875184374', '1', 'lisi', '$2a$10$0RewsHRoDpk65bn.mtVToe0BWk5yjnf0YkNX2tWuZjeyL8O4aWZ.y', '0', 1, 1, 1, 1);

-- ----------------------------
-- Table structure for parking_list
-- ----------------------------
DROP TABLE IF EXISTS `parking_list`;
CREATE TABLE `parking_list`  (
  `park_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '车位id',
  `park_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '车位类型 0:地上 1：地下',
  `park_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '车位名称',
  `park_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '车位使用状态 0：未使用 1：已使用',
  `park_num` int(11) NULL DEFAULT NULL COMMENT '车位序号',
  PRIMARY KEY (`park_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of parking_list
-- ----------------------------
INSERT INTO `parking_list` VALUES (13, '0', 'E101', '0', 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级菜单id',
  `menu_label` varchar(56) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_code` varchar(56) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限字段',
  `name` varchar(56) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '路由名称',
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '路由地址',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组件路由',
  `type` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型 0：目录 1：菜单 2：按钮',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '序号',
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `parent_name` varchar(56) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上级菜单名称',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (3, 0, '系统管理', 'sys:system', '', '/system', '', '0', 'el-icon-menu', 1, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (4, 3, '员工管理', 'sys:sysUserList', 'sys:user:index', '/sysUserList', '/system/sysUserList', '1', 'el-icon-s-custom', 1, '', '系统管理');
INSERT INTO `sys_menu` VALUES (5, 3, '角色管理', 'sys:sysRoleList', 'sysRoleList', '/sysRoleList', '/system/sysRoleList', '1', 'el-icon-date', 2, '', '系统管理');
INSERT INTO `sys_menu` VALUES (6, 3, '权限管理', 'sys:sysMenuList', 'sysMenuList', '/sysMenuList', '/system/sysMenuList', '1', 'el-icon-school', 3, '', '系统管理');
INSERT INTO `sys_menu` VALUES (7, 4, '新增', 'sys:user:add', '', '', '', '2', '', 1, '', '员工管理');
INSERT INTO `sys_menu` VALUES (8, 4, '编辑', 'sys:user:edit', '', '', '', '2', '', 2, '', '员工管理');
INSERT INTO `sys_menu` VALUES (9, 4, '删除', 'sys:user:delete', '', '', '', '2', '', 3, '', '员工管理');
INSERT INTO `sys_menu` VALUES (10, 5, '新增', 'sys:role:add', '', '', '', '2', '', 1, '', '角色管理');
INSERT INTO `sys_menu` VALUES (11, 5, '编辑', 'sys:role:edit', '', '', '', '2', '', 2, '', '角色管理');
INSERT INTO `sys_menu` VALUES (12, 5, '删除', 'sys:role:delete', '', '', '', '2', '', 3, '', '角色管理');
INSERT INTO `sys_menu` VALUES (13, 6, '新增', 'sys:menu:add', '', '', '', '2', '', 1, '', '权限管理');
INSERT INTO `sys_menu` VALUES (14, 6, '编辑', 'sys:menu:edit', '', '', '', '2', '', 2, '', '权限管理');
INSERT INTO `sys_menu` VALUES (15, 6, '删除', 'sys:menu:delete', '', '', '', '2', '', 3, '', '权限管理');
INSERT INTO `sys_menu` VALUES (16, 0, '房屋管理', 'sys:home:index', '', '/sysHouse', '', '0', 'el-icon-s-help', 2, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (17, 16, '房屋列表', 'sys:house:list', 'sysHouseList', '/sysHouseList', '/house/houseList', '1', 'el-icon-mobile', 3, '', '房屋管理');
INSERT INTO `sys_menu` VALUES (18, 17, '新增', 'sys:house:add', '', '', '', '2', '', 1, '', '房屋列表');
INSERT INTO `sys_menu` VALUES (19, 17, '编辑', 'sys:house:edit', '', '', '', '2', '', 2, '', '房屋列表');
INSERT INTO `sys_menu` VALUES (20, 17, '删除', 'sys:house:delete', '', '', '', '2', '', 3, '', '房屋列表');
INSERT INTO `sys_menu` VALUES (21, 16, '单元管理', 'sys:houseUnit', 'houseUnit', '/houseUnit', '/house/houseUnit', '1', 'table', 2, '', '房屋管理');
INSERT INTO `sys_menu` VALUES (22, 16, '栋数管理', 'sys:houseBuilding', 'houseBuilding', '/houseBuilding', '/house/houseBuilding', '1', 'table', 1, '', '房屋管理');
INSERT INTO `sys_menu` VALUES (23, 22, '新增', 'sys:houseBuilding:add', '', '', '', '2', '', 1, '', '栋数管理');
INSERT INTO `sys_menu` VALUES (24, 22, '编辑', 'sys:houseBuilding:edit', '', '', '', '2', '', 2, '', '栋数管理');
INSERT INTO `sys_menu` VALUES (25, 22, '删除', 'sys:houseBuilding:delete', '', '', '', '2', '', 3, '', '栋数管理');
INSERT INTO `sys_menu` VALUES (26, 21, '新增', 'sys:houseUnit:add', '', '', '', '2', '', 1, '', '单元管理');
INSERT INTO `sys_menu` VALUES (27, 21, '编辑', 'sys:houseUnit:edit', '', '', '', '2', '', 2, '', '单元管理');
INSERT INTO `sys_menu` VALUES (28, 21, '删除', 'sys:houseUnit:delete', '', '', '', '2', '', 3, '', '单元管理');
INSERT INTO `sys_menu` VALUES (29, 0, '车位管理', 'sys:sysPark', '', '/sysPark', '', '0', 'el-icon-money', 3, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (30, 29, '车位管理', 'sys:parkList', 'parkList', '/parkList', '/park/parkList', '1', 'el-icon-money', 1, '', '车位管理');
INSERT INTO `sys_menu` VALUES (31, 30, '新增', 'sys:parkList:add', '', '', '', '2', '', 1, '', '车位管理');
INSERT INTO `sys_menu` VALUES (32, 30, '编辑', 'sys:parkList:edit', '', '', '', '2', '', 2, '', '车位管理');
INSERT INTO `sys_menu` VALUES (33, 30, '删除', 'sys:parkList:delete', '', '', '', '2', '', 3, '', '车位管理');
INSERT INTO `sys_menu` VALUES (34, 0, '业主管理', 'sys:live', '', '/live', '', '0', 'el-icon-s-grid', 4, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (35, 34, '业主列表', 'sys:liveUser', 'liveUser', '/liveUser', '/live/liveUser', '1', 'el-icon-s-data', 1, '', '业主管理');
INSERT INTO `sys_menu` VALUES (36, 35, '新增', 'sys:liveUser:add', '', '', '', '2', '', 1, '', '业主列表');
INSERT INTO `sys_menu` VALUES (37, 35, '编辑', 'sys:liveUser:edit', '', '', '', '2', '', 2, '', '业主列表');
INSERT INTO `sys_menu` VALUES (38, 35, '删除', 'sys:liveUser:delete', '', '', '', '2', '', 3, '', '业主列表');
INSERT INTO `sys_menu` VALUES (39, 35, '分配房屋', 'sys:liveUser:assignHome', '', '', '', '2', '', 4, '', '业主列表');
INSERT INTO `sys_menu` VALUES (40, 35, '分配车位', 'sys:liveUser:assignCar', '', '', '', '2', '', 6, '', '业主列表');
INSERT INTO `sys_menu` VALUES (41, 35, '退房', 'sys:liveUser:returnHome', '', '', '', '2', '', 7, '', '业主列表');
INSERT INTO `sys_menu` VALUES (42, 35, '退车位', 'sys:liveUser:returnCar', '', '', '', '2', '', 8, '', '业主列表');
INSERT INTO `sys_menu` VALUES (43, 0, '收费管理', 'sys:fee', '', '/fee', '', '0', 'el-icon-s-open', 5, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (44, 43, '电费管理', 'sys:feePower', 'feePower', '/feePower', '/fee/feePower', '1', 'el-icon-picture', 1, '', '收费管理');
INSERT INTO `sys_menu` VALUES (45, 43, '水费管理', 'sys:feeWater', 'feeWater', '/feeWater', '/fee/feeWater', '1', 'el-icon-s-data', 2, '', '收费管理');
INSERT INTO `sys_menu` VALUES (46, 43, '停车管理', 'sys:feePark', 'feePark', '/feePark', '/fee/feePark', '1', 'el-icon-s-order', 3, '', '收费管理');
INSERT INTO `sys_menu` VALUES (47, 44, '新增', 'sys:feePower:add', '', '', '', '2', '', 1, '', '电费管理');
INSERT INTO `sys_menu` VALUES (48, 44, '编辑', 'sys:feePower:edit', '', '', '', '2', '', 2, '', '电费管理');
INSERT INTO `sys_menu` VALUES (49, 44, '删除', 'sys:feePower:delete', '', '', '', '2', '', 3, '', '电费管理');
INSERT INTO `sys_menu` VALUES (50, 45, '新增', 'sys:feeWater:add', '', '', '', '2', '', 1, '', '水费管理');
INSERT INTO `sys_menu` VALUES (51, 45, '编辑', 'sys:feeWater:edit', '', '', '', '2', '', 2, '', '水费管理');
INSERT INTO `sys_menu` VALUES (52, 45, '删除', 'sys:feeWater:delete', '', '', '', '2', '', 3, '', '水费管理');
INSERT INTO `sys_menu` VALUES (53, 46, '新增', 'sys:feePark:add', '', '', '', '2', '', 1, '', '停车管理');
INSERT INTO `sys_menu` VALUES (54, 46, '编辑', 'sys:feePark:edit', '', '', '', '2', '', 2, '', '停车管理');
INSERT INTO `sys_menu` VALUES (55, 46, '删除', 'sys:feePark:delete', '', '', '', '2', '', 3, '', '停车管理');
INSERT INTO `sys_menu` VALUES (56, 0, '投诉管理', 'sys:userComplaint', '', '/userComplaint', '', '0', 'el-icon-date', 6, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (57, 56, '投诉列表', 'sys:userComplaintList', 'userComplaintList', '/userComplaintList', '/userComplaint/userComplaint', '1', 'el-icon-edit-outline', 1, '', '投诉管理');
INSERT INTO `sys_menu` VALUES (58, 56, '我的投诉', 'sys:myUserComplaint', 'myUserComplaint', '/myUserComplaint', '/userComplaint/myUserComplaint', '1', 'el-icon-menu', 2, '', '投诉管理');
INSERT INTO `sys_menu` VALUES (62, 58, '新增', 'sys:myUserComplaint:add', '', '', '', '2', '', 1, '', '我的投诉');
INSERT INTO `sys_menu` VALUES (63, 58, '编辑', 'sys:myUserComplaint:edit', '', '', '', '2', '', 2, '', '我的投诉');
INSERT INTO `sys_menu` VALUES (64, 58, '删除', 'sys:myUserComplaint:delete', '', '', '', '2', '', 3, '', '我的投诉');
INSERT INTO `sys_menu` VALUES (65, 0, '维修管理', 'sys:repairModel', '', '/repairModel', '', '0', 'el-icon-picture-outline', 7, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (66, 65, '维修列表', 'sys:repairList', 'repairList', '/repairList', '/repair/repairList', '1', 'el-icon-s-marketing', 1, '', '维修管理');
INSERT INTO `sys_menu` VALUES (67, 65, '我的维修', 'sys:myRepair', 'myRepair', '/myRepair', '/repair/myRepair', '1', 'el-icon-picture-outline', 2, '', '维修管理');
INSERT INTO `sys_menu` VALUES (68, 67, '新增', 'sys:myRepair:add', '', '', '', '2', '', 1, '', '我的维修');
INSERT INTO `sys_menu` VALUES (69, 67, '编辑', 'sys:myRepair:edit', '', '', '', '2', '', 2, '', '我的维修');
INSERT INTO `sys_menu` VALUES (70, 67, '删除', 'sys:myRepair:delete', '', '', '', '2', '', 3, '', '我的维修');
INSERT INTO `sys_menu` VALUES (71, 0, '公告管理', 'sys:notice', '', '/notice', '', '0', 'el-icon-document-copy', 8, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (72, 71, '公告列表', 'sys:noticeList', 'noticeList', '/noticeList', '/notice/noticeList', '1', 'el-icon-s-marketing', 1, '', '公告管理');
INSERT INTO `sys_menu` VALUES (73, 72, '新增', 'sys:noticeList:add', '', '', '', '2', '', 1, '', '公告列表');
INSERT INTO `sys_menu` VALUES (74, 72, '编辑', 'sys:noticeList:edit', '', '', '', '2', '', 2, '', '公告列表');
INSERT INTO `sys_menu` VALUES (75, 72, '删除', 'sys:noticeList:delete', '', '', '', '2', '', 3, '', '公告列表');
INSERT INTO `sys_menu` VALUES (76, 72, '查看', 'sys:notice:look', '', '', '', '2', '', 4, '', '公告列表');
INSERT INTO `sys_menu` VALUES (77, 4, '分配角色', 'sys:user:assignRole', '', '', '', '2', '', 5, '', '员工管理');
INSERT INTO `sys_menu` VALUES (78, 5, '分配权限', 'sys:role:assignMenu', '', '', '', '2', '', 4, '', '角色管理');
INSERT INTO `sys_menu` VALUES (79, 66, '处理', 'sys:repairList:do', '', '', '', '2', '', 1, '', '维修列表');
INSERT INTO `sys_menu` VALUES (80, 57, '处理', 'sys:myUserComplaint:do', '', '', '', '2', '', 1, '', '投诉列表');
INSERT INTO `sys_menu` VALUES (81, 44, '缴费', 'sys:feePower:pay', '', '', '', '2', '', 4, '', '电费管理');
INSERT INTO `sys_menu` VALUES (82, 46, '缴费', 'sys:feePark:pay', '', '', '', '2', '', 4, '', '停车管理');
INSERT INTO `sys_menu` VALUES (83, 45, '缴费', 'sys:feeWater:pay', '', '', '', '2', '', 4, '', '水费管理');
INSERT INTO `sys_menu` VALUES (84, 0, '缴费记录', 'sys:feeList', '', '/feeList', '', '0', 'el-icon-c-scale-to-original', 6, '', '顶级菜单');
INSERT INTO `sys_menu` VALUES (85, 84, '我的电费', 'sys:myPowerFee', 'myPowerFee', '/myPowerFee', '/feeList/myPowerFee', '1', 'el-icon-date', 1, '', '缴费记录');
INSERT INTO `sys_menu` VALUES (86, 84, '我的水费', 'sys:myWaterFee', 'myWaterFee', '/myWaterFee', '/feeList/myWaterFee', '1', 'el-icon-s-shop', 2, '', '缴费记录');
INSERT INTO `sys_menu` VALUES (87, 84, '我的停车费', 'sys:myParkFee', 'myParkFee', '/myParkFee', '/feeList/myParkFee', '1', 'el-icon-s-finance', 3, '', '缴费记录');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `notice_content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (4, NULL, '大家好', '欢迎来到本小区居住', '2022-01-25 20:29:50');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(56) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(56) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '管理权限');
INSERT INTO `sys_role` VALUES (15, 'root', '次级管理员');
INSERT INTO `sys_role` VALUES (16, '收费管理员', '');
INSERT INTO `sys_role` VALUES (17, '业主', '');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1039 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (28, 5, 13);
INSERT INTO `sys_role_menu` VALUES (29, 5, 14);
INSERT INTO `sys_role_menu` VALUES (30, 5, 6);
INSERT INTO `sys_role_menu` VALUES (31, 5, 15);
INSERT INTO `sys_role_menu` VALUES (32, 5, 16);
INSERT INTO `sys_role_menu` VALUES (33, 5, 17);
INSERT INTO `sys_role_menu` VALUES (34, 5, 1);
INSERT INTO `sys_role_menu` VALUES (35, 5, 5);
INSERT INTO `sys_role_menu` VALUES (321, 8, 8);
INSERT INTO `sys_role_menu` VALUES (322, 8, 9);
INSERT INTO `sys_role_menu` VALUES (323, 8, 76);
INSERT INTO `sys_role_menu` VALUES (324, 8, 3);
INSERT INTO `sys_role_menu` VALUES (325, 8, 4);
INSERT INTO `sys_role_menu` VALUES (326, 8, 71);
INSERT INTO `sys_role_menu` VALUES (327, 8, 72);
INSERT INTO `sys_role_menu` VALUES (328, 9, 76);
INSERT INTO `sys_role_menu` VALUES (329, 9, 71);
INSERT INTO `sys_role_menu` VALUES (330, 9, 72);
INSERT INTO `sys_role_menu` VALUES (502, 10, 58);
INSERT INTO `sys_role_menu` VALUES (503, 10, 62);
INSERT INTO `sys_role_menu` VALUES (504, 10, 63);
INSERT INTO `sys_role_menu` VALUES (505, 10, 64);
INSERT INTO `sys_role_menu` VALUES (506, 10, 67);
INSERT INTO `sys_role_menu` VALUES (507, 10, 68);
INSERT INTO `sys_role_menu` VALUES (508, 10, 69);
INSERT INTO `sys_role_menu` VALUES (509, 10, 70);
INSERT INTO `sys_role_menu` VALUES (510, 10, 76);
INSERT INTO `sys_role_menu` VALUES (511, 10, 84);
INSERT INTO `sys_role_menu` VALUES (512, 10, 85);
INSERT INTO `sys_role_menu` VALUES (513, 10, 86);
INSERT INTO `sys_role_menu` VALUES (514, 10, 87);
INSERT INTO `sys_role_menu` VALUES (515, 10, 56);
INSERT INTO `sys_role_menu` VALUES (516, 10, 65);
INSERT INTO `sys_role_menu` VALUES (517, 10, 71);
INSERT INTO `sys_role_menu` VALUES (518, 10, 72);
INSERT INTO `sys_role_menu` VALUES (519, 11, 4);
INSERT INTO `sys_role_menu` VALUES (520, 11, 7);
INSERT INTO `sys_role_menu` VALUES (521, 11, 8);
INSERT INTO `sys_role_menu` VALUES (522, 11, 9);
INSERT INTO `sys_role_menu` VALUES (523, 11, 77);
INSERT INTO `sys_role_menu` VALUES (524, 11, 5);
INSERT INTO `sys_role_menu` VALUES (525, 11, 10);
INSERT INTO `sys_role_menu` VALUES (526, 11, 11);
INSERT INTO `sys_role_menu` VALUES (527, 11, 12);
INSERT INTO `sys_role_menu` VALUES (528, 11, 78);
INSERT INTO `sys_role_menu` VALUES (529, 11, 16);
INSERT INTO `sys_role_menu` VALUES (530, 11, 17);
INSERT INTO `sys_role_menu` VALUES (531, 11, 18);
INSERT INTO `sys_role_menu` VALUES (532, 11, 19);
INSERT INTO `sys_role_menu` VALUES (533, 11, 20);
INSERT INTO `sys_role_menu` VALUES (534, 11, 21);
INSERT INTO `sys_role_menu` VALUES (535, 11, 26);
INSERT INTO `sys_role_menu` VALUES (536, 11, 27);
INSERT INTO `sys_role_menu` VALUES (537, 11, 28);
INSERT INTO `sys_role_menu` VALUES (538, 11, 22);
INSERT INTO `sys_role_menu` VALUES (539, 11, 23);
INSERT INTO `sys_role_menu` VALUES (540, 11, 24);
INSERT INTO `sys_role_menu` VALUES (541, 11, 25);
INSERT INTO `sys_role_menu` VALUES (542, 11, 29);
INSERT INTO `sys_role_menu` VALUES (543, 11, 30);
INSERT INTO `sys_role_menu` VALUES (544, 11, 31);
INSERT INTO `sys_role_menu` VALUES (545, 11, 32);
INSERT INTO `sys_role_menu` VALUES (546, 11, 33);
INSERT INTO `sys_role_menu` VALUES (547, 11, 34);
INSERT INTO `sys_role_menu` VALUES (548, 11, 35);
INSERT INTO `sys_role_menu` VALUES (549, 11, 36);
INSERT INTO `sys_role_menu` VALUES (550, 11, 37);
INSERT INTO `sys_role_menu` VALUES (551, 11, 38);
INSERT INTO `sys_role_menu` VALUES (552, 11, 39);
INSERT INTO `sys_role_menu` VALUES (553, 11, 40);
INSERT INTO `sys_role_menu` VALUES (554, 11, 41);
INSERT INTO `sys_role_menu` VALUES (555, 11, 42);
INSERT INTO `sys_role_menu` VALUES (556, 11, 43);
INSERT INTO `sys_role_menu` VALUES (557, 11, 44);
INSERT INTO `sys_role_menu` VALUES (558, 11, 47);
INSERT INTO `sys_role_menu` VALUES (559, 11, 48);
INSERT INTO `sys_role_menu` VALUES (560, 11, 49);
INSERT INTO `sys_role_menu` VALUES (561, 11, 81);
INSERT INTO `sys_role_menu` VALUES (562, 11, 45);
INSERT INTO `sys_role_menu` VALUES (563, 11, 50);
INSERT INTO `sys_role_menu` VALUES (564, 11, 51);
INSERT INTO `sys_role_menu` VALUES (565, 11, 52);
INSERT INTO `sys_role_menu` VALUES (566, 11, 83);
INSERT INTO `sys_role_menu` VALUES (567, 11, 46);
INSERT INTO `sys_role_menu` VALUES (568, 11, 53);
INSERT INTO `sys_role_menu` VALUES (569, 11, 54);
INSERT INTO `sys_role_menu` VALUES (570, 11, 55);
INSERT INTO `sys_role_menu` VALUES (571, 11, 82);
INSERT INTO `sys_role_menu` VALUES (572, 11, 56);
INSERT INTO `sys_role_menu` VALUES (573, 11, 57);
INSERT INTO `sys_role_menu` VALUES (574, 11, 80);
INSERT INTO `sys_role_menu` VALUES (575, 11, 58);
INSERT INTO `sys_role_menu` VALUES (576, 11, 62);
INSERT INTO `sys_role_menu` VALUES (577, 11, 63);
INSERT INTO `sys_role_menu` VALUES (578, 11, 64);
INSERT INTO `sys_role_menu` VALUES (579, 11, 65);
INSERT INTO `sys_role_menu` VALUES (580, 11, 66);
INSERT INTO `sys_role_menu` VALUES (581, 11, 79);
INSERT INTO `sys_role_menu` VALUES (582, 11, 67);
INSERT INTO `sys_role_menu` VALUES (583, 11, 68);
INSERT INTO `sys_role_menu` VALUES (584, 11, 69);
INSERT INTO `sys_role_menu` VALUES (585, 11, 70);
INSERT INTO `sys_role_menu` VALUES (586, 11, 71);
INSERT INTO `sys_role_menu` VALUES (587, 11, 72);
INSERT INTO `sys_role_menu` VALUES (588, 11, 73);
INSERT INTO `sys_role_menu` VALUES (589, 11, 74);
INSERT INTO `sys_role_menu` VALUES (590, 11, 75);
INSERT INTO `sys_role_menu` VALUES (591, 11, 76);
INSERT INTO `sys_role_menu` VALUES (592, 11, 84);
INSERT INTO `sys_role_menu` VALUES (593, 11, 85);
INSERT INTO `sys_role_menu` VALUES (594, 11, 86);
INSERT INTO `sys_role_menu` VALUES (595, 11, 87);
INSERT INTO `sys_role_menu` VALUES (596, 11, 3);
INSERT INTO `sys_role_menu` VALUES (597, 12, 4);
INSERT INTO `sys_role_menu` VALUES (598, 12, 7);
INSERT INTO `sys_role_menu` VALUES (599, 12, 8);
INSERT INTO `sys_role_menu` VALUES (600, 12, 9);
INSERT INTO `sys_role_menu` VALUES (601, 12, 77);
INSERT INTO `sys_role_menu` VALUES (602, 12, 5);
INSERT INTO `sys_role_menu` VALUES (603, 12, 10);
INSERT INTO `sys_role_menu` VALUES (604, 12, 11);
INSERT INTO `sys_role_menu` VALUES (605, 12, 12);
INSERT INTO `sys_role_menu` VALUES (606, 12, 78);
INSERT INTO `sys_role_menu` VALUES (607, 12, 16);
INSERT INTO `sys_role_menu` VALUES (608, 12, 17);
INSERT INTO `sys_role_menu` VALUES (609, 12, 18);
INSERT INTO `sys_role_menu` VALUES (610, 12, 19);
INSERT INTO `sys_role_menu` VALUES (611, 12, 20);
INSERT INTO `sys_role_menu` VALUES (612, 12, 21);
INSERT INTO `sys_role_menu` VALUES (613, 12, 26);
INSERT INTO `sys_role_menu` VALUES (614, 12, 27);
INSERT INTO `sys_role_menu` VALUES (615, 12, 28);
INSERT INTO `sys_role_menu` VALUES (616, 12, 22);
INSERT INTO `sys_role_menu` VALUES (617, 12, 23);
INSERT INTO `sys_role_menu` VALUES (618, 12, 24);
INSERT INTO `sys_role_menu` VALUES (619, 12, 25);
INSERT INTO `sys_role_menu` VALUES (620, 12, 29);
INSERT INTO `sys_role_menu` VALUES (621, 12, 30);
INSERT INTO `sys_role_menu` VALUES (622, 12, 31);
INSERT INTO `sys_role_menu` VALUES (623, 12, 32);
INSERT INTO `sys_role_menu` VALUES (624, 12, 33);
INSERT INTO `sys_role_menu` VALUES (625, 12, 34);
INSERT INTO `sys_role_menu` VALUES (626, 12, 35);
INSERT INTO `sys_role_menu` VALUES (627, 12, 36);
INSERT INTO `sys_role_menu` VALUES (628, 12, 37);
INSERT INTO `sys_role_menu` VALUES (629, 12, 38);
INSERT INTO `sys_role_menu` VALUES (630, 12, 39);
INSERT INTO `sys_role_menu` VALUES (631, 12, 40);
INSERT INTO `sys_role_menu` VALUES (632, 12, 41);
INSERT INTO `sys_role_menu` VALUES (633, 12, 42);
INSERT INTO `sys_role_menu` VALUES (634, 12, 43);
INSERT INTO `sys_role_menu` VALUES (635, 12, 44);
INSERT INTO `sys_role_menu` VALUES (636, 12, 47);
INSERT INTO `sys_role_menu` VALUES (637, 12, 48);
INSERT INTO `sys_role_menu` VALUES (638, 12, 49);
INSERT INTO `sys_role_menu` VALUES (639, 12, 81);
INSERT INTO `sys_role_menu` VALUES (640, 12, 45);
INSERT INTO `sys_role_menu` VALUES (641, 12, 50);
INSERT INTO `sys_role_menu` VALUES (642, 12, 51);
INSERT INTO `sys_role_menu` VALUES (643, 12, 52);
INSERT INTO `sys_role_menu` VALUES (644, 12, 83);
INSERT INTO `sys_role_menu` VALUES (645, 12, 46);
INSERT INTO `sys_role_menu` VALUES (646, 12, 53);
INSERT INTO `sys_role_menu` VALUES (647, 12, 54);
INSERT INTO `sys_role_menu` VALUES (648, 12, 55);
INSERT INTO `sys_role_menu` VALUES (649, 12, 82);
INSERT INTO `sys_role_menu` VALUES (650, 12, 56);
INSERT INTO `sys_role_menu` VALUES (651, 12, 57);
INSERT INTO `sys_role_menu` VALUES (652, 12, 80);
INSERT INTO `sys_role_menu` VALUES (653, 12, 58);
INSERT INTO `sys_role_menu` VALUES (654, 12, 62);
INSERT INTO `sys_role_menu` VALUES (655, 12, 63);
INSERT INTO `sys_role_menu` VALUES (656, 12, 64);
INSERT INTO `sys_role_menu` VALUES (657, 12, 65);
INSERT INTO `sys_role_menu` VALUES (658, 12, 66);
INSERT INTO `sys_role_menu` VALUES (659, 12, 79);
INSERT INTO `sys_role_menu` VALUES (660, 12, 67);
INSERT INTO `sys_role_menu` VALUES (661, 12, 68);
INSERT INTO `sys_role_menu` VALUES (662, 12, 69);
INSERT INTO `sys_role_menu` VALUES (663, 12, 70);
INSERT INTO `sys_role_menu` VALUES (664, 12, 71);
INSERT INTO `sys_role_menu` VALUES (665, 12, 72);
INSERT INTO `sys_role_menu` VALUES (666, 12, 73);
INSERT INTO `sys_role_menu` VALUES (667, 12, 74);
INSERT INTO `sys_role_menu` VALUES (668, 12, 75);
INSERT INTO `sys_role_menu` VALUES (669, 12, 76);
INSERT INTO `sys_role_menu` VALUES (670, 12, 84);
INSERT INTO `sys_role_menu` VALUES (671, 12, 85);
INSERT INTO `sys_role_menu` VALUES (672, 12, 86);
INSERT INTO `sys_role_menu` VALUES (673, 12, 87);
INSERT INTO `sys_role_menu` VALUES (674, 12, 3);
INSERT INTO `sys_role_menu` VALUES (675, 13, 43);
INSERT INTO `sys_role_menu` VALUES (676, 13, 44);
INSERT INTO `sys_role_menu` VALUES (677, 13, 47);
INSERT INTO `sys_role_menu` VALUES (678, 13, 48);
INSERT INTO `sys_role_menu` VALUES (679, 13, 49);
INSERT INTO `sys_role_menu` VALUES (680, 13, 81);
INSERT INTO `sys_role_menu` VALUES (681, 13, 45);
INSERT INTO `sys_role_menu` VALUES (682, 13, 50);
INSERT INTO `sys_role_menu` VALUES (683, 13, 51);
INSERT INTO `sys_role_menu` VALUES (684, 13, 52);
INSERT INTO `sys_role_menu` VALUES (685, 13, 83);
INSERT INTO `sys_role_menu` VALUES (686, 13, 46);
INSERT INTO `sys_role_menu` VALUES (687, 13, 53);
INSERT INTO `sys_role_menu` VALUES (688, 13, 54);
INSERT INTO `sys_role_menu` VALUES (689, 13, 55);
INSERT INTO `sys_role_menu` VALUES (690, 13, 82);
INSERT INTO `sys_role_menu` VALUES (691, 1, 3);
INSERT INTO `sys_role_menu` VALUES (692, 1, 4);
INSERT INTO `sys_role_menu` VALUES (693, 1, 7);
INSERT INTO `sys_role_menu` VALUES (694, 1, 8);
INSERT INTO `sys_role_menu` VALUES (695, 1, 9);
INSERT INTO `sys_role_menu` VALUES (696, 1, 77);
INSERT INTO `sys_role_menu` VALUES (697, 1, 5);
INSERT INTO `sys_role_menu` VALUES (698, 1, 10);
INSERT INTO `sys_role_menu` VALUES (699, 1, 11);
INSERT INTO `sys_role_menu` VALUES (700, 1, 12);
INSERT INTO `sys_role_menu` VALUES (701, 1, 78);
INSERT INTO `sys_role_menu` VALUES (702, 1, 6);
INSERT INTO `sys_role_menu` VALUES (703, 1, 13);
INSERT INTO `sys_role_menu` VALUES (704, 1, 14);
INSERT INTO `sys_role_menu` VALUES (705, 1, 15);
INSERT INTO `sys_role_menu` VALUES (706, 1, 16);
INSERT INTO `sys_role_menu` VALUES (707, 1, 17);
INSERT INTO `sys_role_menu` VALUES (708, 1, 18);
INSERT INTO `sys_role_menu` VALUES (709, 1, 19);
INSERT INTO `sys_role_menu` VALUES (710, 1, 20);
INSERT INTO `sys_role_menu` VALUES (711, 1, 21);
INSERT INTO `sys_role_menu` VALUES (712, 1, 26);
INSERT INTO `sys_role_menu` VALUES (713, 1, 27);
INSERT INTO `sys_role_menu` VALUES (714, 1, 28);
INSERT INTO `sys_role_menu` VALUES (715, 1, 22);
INSERT INTO `sys_role_menu` VALUES (716, 1, 23);
INSERT INTO `sys_role_menu` VALUES (717, 1, 24);
INSERT INTO `sys_role_menu` VALUES (718, 1, 25);
INSERT INTO `sys_role_menu` VALUES (719, 1, 29);
INSERT INTO `sys_role_menu` VALUES (720, 1, 30);
INSERT INTO `sys_role_menu` VALUES (721, 1, 31);
INSERT INTO `sys_role_menu` VALUES (722, 1, 32);
INSERT INTO `sys_role_menu` VALUES (723, 1, 33);
INSERT INTO `sys_role_menu` VALUES (724, 1, 34);
INSERT INTO `sys_role_menu` VALUES (725, 1, 35);
INSERT INTO `sys_role_menu` VALUES (726, 1, 36);
INSERT INTO `sys_role_menu` VALUES (727, 1, 37);
INSERT INTO `sys_role_menu` VALUES (728, 1, 38);
INSERT INTO `sys_role_menu` VALUES (729, 1, 39);
INSERT INTO `sys_role_menu` VALUES (730, 1, 40);
INSERT INTO `sys_role_menu` VALUES (731, 1, 41);
INSERT INTO `sys_role_menu` VALUES (732, 1, 42);
INSERT INTO `sys_role_menu` VALUES (733, 1, 43);
INSERT INTO `sys_role_menu` VALUES (734, 1, 44);
INSERT INTO `sys_role_menu` VALUES (735, 1, 47);
INSERT INTO `sys_role_menu` VALUES (736, 1, 48);
INSERT INTO `sys_role_menu` VALUES (737, 1, 49);
INSERT INTO `sys_role_menu` VALUES (738, 1, 81);
INSERT INTO `sys_role_menu` VALUES (739, 1, 45);
INSERT INTO `sys_role_menu` VALUES (740, 1, 50);
INSERT INTO `sys_role_menu` VALUES (741, 1, 51);
INSERT INTO `sys_role_menu` VALUES (742, 1, 52);
INSERT INTO `sys_role_menu` VALUES (743, 1, 83);
INSERT INTO `sys_role_menu` VALUES (744, 1, 46);
INSERT INTO `sys_role_menu` VALUES (745, 1, 53);
INSERT INTO `sys_role_menu` VALUES (746, 1, 54);
INSERT INTO `sys_role_menu` VALUES (747, 1, 55);
INSERT INTO `sys_role_menu` VALUES (748, 1, 82);
INSERT INTO `sys_role_menu` VALUES (749, 1, 56);
INSERT INTO `sys_role_menu` VALUES (750, 1, 57);
INSERT INTO `sys_role_menu` VALUES (751, 1, 80);
INSERT INTO `sys_role_menu` VALUES (752, 1, 58);
INSERT INTO `sys_role_menu` VALUES (753, 1, 62);
INSERT INTO `sys_role_menu` VALUES (754, 1, 63);
INSERT INTO `sys_role_menu` VALUES (755, 1, 64);
INSERT INTO `sys_role_menu` VALUES (756, 1, 65);
INSERT INTO `sys_role_menu` VALUES (757, 1, 66);
INSERT INTO `sys_role_menu` VALUES (758, 1, 79);
INSERT INTO `sys_role_menu` VALUES (759, 1, 67);
INSERT INTO `sys_role_menu` VALUES (760, 1, 68);
INSERT INTO `sys_role_menu` VALUES (761, 1, 69);
INSERT INTO `sys_role_menu` VALUES (762, 1, 70);
INSERT INTO `sys_role_menu` VALUES (763, 1, 71);
INSERT INTO `sys_role_menu` VALUES (764, 1, 72);
INSERT INTO `sys_role_menu` VALUES (765, 1, 73);
INSERT INTO `sys_role_menu` VALUES (766, 1, 74);
INSERT INTO `sys_role_menu` VALUES (767, 1, 75);
INSERT INTO `sys_role_menu` VALUES (768, 1, 76);
INSERT INTO `sys_role_menu` VALUES (769, 1, 84);
INSERT INTO `sys_role_menu` VALUES (770, 1, 85);
INSERT INTO `sys_role_menu` VALUES (771, 1, 86);
INSERT INTO `sys_role_menu` VALUES (772, 1, 87);
INSERT INTO `sys_role_menu` VALUES (851, 16, 43);
INSERT INTO `sys_role_menu` VALUES (852, 16, 44);
INSERT INTO `sys_role_menu` VALUES (853, 16, 47);
INSERT INTO `sys_role_menu` VALUES (854, 16, 48);
INSERT INTO `sys_role_menu` VALUES (855, 16, 49);
INSERT INTO `sys_role_menu` VALUES (856, 16, 81);
INSERT INTO `sys_role_menu` VALUES (857, 16, 45);
INSERT INTO `sys_role_menu` VALUES (858, 16, 50);
INSERT INTO `sys_role_menu` VALUES (859, 16, 51);
INSERT INTO `sys_role_menu` VALUES (860, 16, 52);
INSERT INTO `sys_role_menu` VALUES (861, 16, 83);
INSERT INTO `sys_role_menu` VALUES (862, 16, 46);
INSERT INTO `sys_role_menu` VALUES (863, 16, 53);
INSERT INTO `sys_role_menu` VALUES (864, 16, 54);
INSERT INTO `sys_role_menu` VALUES (865, 16, 55);
INSERT INTO `sys_role_menu` VALUES (866, 16, 82);
INSERT INTO `sys_role_menu` VALUES (867, 17, 58);
INSERT INTO `sys_role_menu` VALUES (868, 17, 62);
INSERT INTO `sys_role_menu` VALUES (869, 17, 63);
INSERT INTO `sys_role_menu` VALUES (870, 17, 64);
INSERT INTO `sys_role_menu` VALUES (871, 17, 67);
INSERT INTO `sys_role_menu` VALUES (872, 17, 68);
INSERT INTO `sys_role_menu` VALUES (873, 17, 69);
INSERT INTO `sys_role_menu` VALUES (874, 17, 70);
INSERT INTO `sys_role_menu` VALUES (875, 17, 76);
INSERT INTO `sys_role_menu` VALUES (876, 17, 84);
INSERT INTO `sys_role_menu` VALUES (877, 17, 85);
INSERT INTO `sys_role_menu` VALUES (878, 17, 86);
INSERT INTO `sys_role_menu` VALUES (879, 17, 87);
INSERT INTO `sys_role_menu` VALUES (880, 17, 56);
INSERT INTO `sys_role_menu` VALUES (881, 17, 65);
INSERT INTO `sys_role_menu` VALUES (882, 17, 71);
INSERT INTO `sys_role_menu` VALUES (883, 17, 72);
INSERT INTO `sys_role_menu` VALUES (961, 15, 4);
INSERT INTO `sys_role_menu` VALUES (962, 15, 7);
INSERT INTO `sys_role_menu` VALUES (963, 15, 8);
INSERT INTO `sys_role_menu` VALUES (964, 15, 9);
INSERT INTO `sys_role_menu` VALUES (965, 15, 77);
INSERT INTO `sys_role_menu` VALUES (966, 15, 5);
INSERT INTO `sys_role_menu` VALUES (967, 15, 10);
INSERT INTO `sys_role_menu` VALUES (968, 15, 11);
INSERT INTO `sys_role_menu` VALUES (969, 15, 12);
INSERT INTO `sys_role_menu` VALUES (970, 15, 78);
INSERT INTO `sys_role_menu` VALUES (971, 15, 16);
INSERT INTO `sys_role_menu` VALUES (972, 15, 17);
INSERT INTO `sys_role_menu` VALUES (973, 15, 18);
INSERT INTO `sys_role_menu` VALUES (974, 15, 19);
INSERT INTO `sys_role_menu` VALUES (975, 15, 20);
INSERT INTO `sys_role_menu` VALUES (976, 15, 21);
INSERT INTO `sys_role_menu` VALUES (977, 15, 26);
INSERT INTO `sys_role_menu` VALUES (978, 15, 27);
INSERT INTO `sys_role_menu` VALUES (979, 15, 28);
INSERT INTO `sys_role_menu` VALUES (980, 15, 22);
INSERT INTO `sys_role_menu` VALUES (981, 15, 23);
INSERT INTO `sys_role_menu` VALUES (982, 15, 24);
INSERT INTO `sys_role_menu` VALUES (983, 15, 25);
INSERT INTO `sys_role_menu` VALUES (984, 15, 29);
INSERT INTO `sys_role_menu` VALUES (985, 15, 30);
INSERT INTO `sys_role_menu` VALUES (986, 15, 31);
INSERT INTO `sys_role_menu` VALUES (987, 15, 32);
INSERT INTO `sys_role_menu` VALUES (988, 15, 33);
INSERT INTO `sys_role_menu` VALUES (989, 15, 34);
INSERT INTO `sys_role_menu` VALUES (990, 15, 35);
INSERT INTO `sys_role_menu` VALUES (991, 15, 36);
INSERT INTO `sys_role_menu` VALUES (992, 15, 37);
INSERT INTO `sys_role_menu` VALUES (993, 15, 38);
INSERT INTO `sys_role_menu` VALUES (994, 15, 39);
INSERT INTO `sys_role_menu` VALUES (995, 15, 40);
INSERT INTO `sys_role_menu` VALUES (996, 15, 41);
INSERT INTO `sys_role_menu` VALUES (997, 15, 42);
INSERT INTO `sys_role_menu` VALUES (998, 15, 43);
INSERT INTO `sys_role_menu` VALUES (999, 15, 44);
INSERT INTO `sys_role_menu` VALUES (1000, 15, 47);
INSERT INTO `sys_role_menu` VALUES (1001, 15, 48);
INSERT INTO `sys_role_menu` VALUES (1002, 15, 49);
INSERT INTO `sys_role_menu` VALUES (1003, 15, 81);
INSERT INTO `sys_role_menu` VALUES (1004, 15, 45);
INSERT INTO `sys_role_menu` VALUES (1005, 15, 50);
INSERT INTO `sys_role_menu` VALUES (1006, 15, 51);
INSERT INTO `sys_role_menu` VALUES (1007, 15, 52);
INSERT INTO `sys_role_menu` VALUES (1008, 15, 83);
INSERT INTO `sys_role_menu` VALUES (1009, 15, 46);
INSERT INTO `sys_role_menu` VALUES (1010, 15, 53);
INSERT INTO `sys_role_menu` VALUES (1011, 15, 54);
INSERT INTO `sys_role_menu` VALUES (1012, 15, 55);
INSERT INTO `sys_role_menu` VALUES (1013, 15, 82);
INSERT INTO `sys_role_menu` VALUES (1014, 15, 56);
INSERT INTO `sys_role_menu` VALUES (1015, 15, 57);
INSERT INTO `sys_role_menu` VALUES (1016, 15, 80);
INSERT INTO `sys_role_menu` VALUES (1017, 15, 58);
INSERT INTO `sys_role_menu` VALUES (1018, 15, 62);
INSERT INTO `sys_role_menu` VALUES (1019, 15, 63);
INSERT INTO `sys_role_menu` VALUES (1020, 15, 64);
INSERT INTO `sys_role_menu` VALUES (1021, 15, 65);
INSERT INTO `sys_role_menu` VALUES (1022, 15, 66);
INSERT INTO `sys_role_menu` VALUES (1023, 15, 79);
INSERT INTO `sys_role_menu` VALUES (1024, 15, 67);
INSERT INTO `sys_role_menu` VALUES (1025, 15, 68);
INSERT INTO `sys_role_menu` VALUES (1026, 15, 69);
INSERT INTO `sys_role_menu` VALUES (1027, 15, 70);
INSERT INTO `sys_role_menu` VALUES (1028, 15, 71);
INSERT INTO `sys_role_menu` VALUES (1029, 15, 72);
INSERT INTO `sys_role_menu` VALUES (1030, 15, 73);
INSERT INTO `sys_role_menu` VALUES (1031, 15, 74);
INSERT INTO `sys_role_menu` VALUES (1032, 15, 75);
INSERT INTO `sys_role_menu` VALUES (1033, 15, 76);
INSERT INTO `sys_role_menu` VALUES (1034, 15, 84);
INSERT INTO `sys_role_menu` VALUES (1035, 15, 85);
INSERT INTO `sys_role_menu` VALUES (1036, 15, 86);
INSERT INTO `sys_role_menu` VALUES (1037, 15, 87);
INSERT INTO `sys_role_menu` VALUES (1038, 15, 3);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `login_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '登录密码',
  `username` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '登录账户',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别 0：女 1：男',
  `id_card` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号码',
  `is_admin` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否是管理员 0：不是 1：是',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0：在职  1：离职',
  `is_used` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0：启用 1：禁用',
  `is_account_non_expired` int(255) NULL DEFAULT NULL COMMENT ' 帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` int(255) NULL DEFAULT NULL COMMENT '帐户是否被锁定(1 未锁定，0锁定)	',
  `is_credentials_non_expired` int(255) NULL DEFAULT NULL COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '帐户是否可用(1 可用，0 删除用户)		',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (17, 'admin', '$2a$10$8BEhdktbUkDwTZDoba7BhOS2Fk2fbFQqDi/pov2ZYkyGpQnCNqSX.', 'admin', '18922468764', '1', '445222200004201', '1', '0', '1', 1, 1, 1, '1');
INSERT INTO `sys_user` VALUES (30, 'zhangsan', '$2a$10$g1oaemRhj6t5kbSjIdREeexALQXg4LWsa6QW0iRuf1UQHj2Rw4v5G', 'root', '17874821631', '1', '445222200003945', NULL, '0', '0', 1, 1, 1, '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 17);
INSERT INTO `sys_user_role` VALUES (18, 15, 30);

-- ----------------------------
-- Table structure for user_complaint
-- ----------------------------
DROP TABLE IF EXISTS `user_complaint`;
CREATE TABLE `user_complaint`  (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主Id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `complaint_content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '投诉内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '投诉时间',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '处理状态 0：未处理 1：已处理',
  PRIMARY KEY (`complaint_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_complaint
-- ----------------------------
INSERT INTO `user_complaint` VALUES (8, 17, '垃圾处理', '楼下垃圾桶垃圾太多未及时处理', '2022-01-25 20:28:43', '1');

-- ----------------------------
-- Table structure for user_repair
-- ----------------------------
DROP TABLE IF EXISTS `user_repair`;
CREATE TABLE `user_repair`  (
  `repair_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系电话',
  `repair_address` tinytext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '维修地址',
  `repair_content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '维修内容',
  `commit_time` datetime NULL DEFAULT NULL COMMENT '报修时间',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '维修状态 0：未维修 1：已维修',
  PRIMARY KEY (`repair_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_repair
-- ----------------------------
INSERT INTO `user_repair` VALUES (8, 17, '17836154384', 'E栋', '漏水', '2022-01-25 20:29:20', '1');

SET FOREIGN_KEY_CHECKS = 1;
