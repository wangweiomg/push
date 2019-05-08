CREATE DATABASE push DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE t_user (
  id int AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(30) NOT NULL COMMENT '名字',
  password VARCHAR(60) NOT NULL COMMENT '密码',
  email VARCHAR(30) COMMENT '邮箱',
  phone VARCHAR(30) COMMENT '电话',
  remark VARCHAR(30) COMMENT '备注'
) COMMENT '用户表';

INSERT INTO t_user (name, password, email, phone, remark) VALUES ('jack', '123456', 'jack@163.com', '18911331234', 'test');
INSERT INTO t_user (name, password, email, phone, remark) VALUES ('rose', 'abced', 'rose@yahoo.com', '13711338884', 'test');
INSERT INTO t_user (name, password, email, phone, remark) VALUES ('bob', 'qwerdf', 'bob@gmail.com', '18911330987', 'test');

CREATE TABLE sys_users (
  id int AUTO_INCREMENT PRIMARY KEY ,
  openid VARCHAR(32) NOT NULL COMMENT '维系ID',
  isadmin CHAR(1) NOT NULL COMMENT '是否管理员',
  isforbidden CHAR(1) NOT NULL COMMENT '是否禁用'
) COMMENT '系统用户表';
-- 测试数据
INSERT INTO `sys_users` VALUES ('1', 'sdfsdfsdf', '1', '0');
INSERT INTO `sys_users` VALUES ('2', 'fdfsdfds', '0', '0');
INSERT INTO `sys_users` VALUES ('3', 'fhhngff', '0', '0');
INSERT INTO `sys_users` VALUES ('4', 'dffdf', '0', '1');

CREATE TABLE channel (
  id int AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(300) NOT NULL COMMENT '通道名称',
  email VARCHAR(300) NOT NULL COMMENT '邮箱',
  userid int NOT NULL COMMENT '用户id',
  sendkey VARCHAR(50) NOT NULL  COMMENT 'sendkey',
  isforbidden CHAR(1) NOT NULL  COMMENT '是否禁用 1：是 0：否'
) COMMENT '通道表';