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
