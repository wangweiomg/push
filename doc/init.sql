-- CREATE DATABASE push default character set utf8mb4 collate utf8mb4_unicode_ci;
-- 用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  token BIGINT COMMENT 'token',
  login_name VARCHAR(30) COMMENT '登陆名',
  password VARCHAR(100) COMMENT '密码',
  name VARCHAR(30) COMMENT '用户真名',
  email VARCHAR(60) COMMENT '邮箱',
  mobile VARCHAR(30) COMMENT '手机号',
  status tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 1.启用中 2.已停用',
  open_id VARCHAR(60) COMMENT '微信open id',
  union_id VARCHAR(60) COMMENT '微信union id',
  nickname VARCHAR(30) COMMENT '用户昵称',
  sex TINYINT(1) COMMENT '1男2女3未知',
  sex_desc VARCHAR (30) COMMENT '性别描述',
  head_img_url VARCHAR(300) COMMENT '用户头像URL',
  country VARCHAR(30) COMMENT '用户所在国家',
  province VARCHAR(30) COMMENT '用户所在省份',
  city VARCHAR(30) COMMENT '用户所在城市',
  language VARCHAR(30) COMMENT '所用的语言',

  remark VARCHAR(100) COMMENT '备注',
  create_by int(11) COMMENT '创建人ID',
  create_at datetime COMMENT '创建时间',
  update_by int(11) COMMENT '更新人ID',
  update_at datetime COMMENT '更新时间',
  delete_flag tinyint(1) DEFAULT 0 COMMENT '删除标志1.是 0否',

  UNIQUE (token);

)COMMENT '用户';
CREATE INDEX idx_user_email ON t_user(email);
CREATE INDEX idx_user_mobile ON t_user(mobile);
CREATE INDEX idx_user_open_id ON t_user(open_id);
CREATE INDEX idx_user_union_id ON t_user(union_id);


DROP TABLE IF EXISTS sys_dict;
CREATE TABLE sys_dict (
  id INT(11) AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
  type VARCHAR(100) COMMENT '类型',
  label VARCHAR(100) COMMENT '标签',
  value VARCHAR(100) COMMENT '值',
  description VARCHAR(100) COMMENT '描述',
  sort INT(11) COMMENT '排序',
  remark VARCHAR(100) COMMENT '备注',
  create_by INT(11) COMMENT '创建人',
  create_at DATETIME COMMENT '创建时间',
  update_by INT(11) COMMENT '更新人',
  update_at DATETIME COMMENT '更新时间',
  delete_flag TINYINT(1) COMMENT '1 删除 0正常'

) COMMENT '字典';



DROP TABLE IF EXISTS t_channel;
CREATE TABLE t_channel (
  id int AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(300) NOT NULL COMMENT '通道名称',
  email VARCHAR(300) NOT NULL COMMENT '邮箱',
  user_id int NOT NULL COMMENT '用户id',
  send_key VARCHAR(50) NOT NULL UNIQUE  COMMENT 'sendkey',
  isforbidden CHAR(1) NOT NULL  COMMENT '是否禁用 1：是 0：否',
  ticket VARCHAR(100) COMMENT '二维码'
) COMMENT '通道表';

DROP TABLE IF EXISTS t_user_channel;
CREATE TABLE t_user_channel (
  user_id INT COMMENT '用户ID',
  channel_id INT COMMENT  '渠道ID',
  PRIMARY KEY (user_id, channel_id)
) COMMENT '用户渠道表';