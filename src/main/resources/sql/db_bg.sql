-- 数据库创建
-- DROP DATABASE IF EXISTS db_background;
-- CREATE DATABASE db_background DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;;

-- 选择数据库
USE db_background;

-- 管理员表
DROP TABLE IF EXISTS admin;
CREATE TABLE admin (
  `id` VARCHAR(32) PRIMARY KEY,
  `name` VARCHAR(16) NOT NULL COMMENT '用户名(即登录名)',
  `pass` VARCHAR(16) NOT NULL COMMENT '密码',
  `phone` VARCHAR(11) NOT NULL COMMENT '手机号',
  `email` VARCHAR(32) COMMENT '邮箱',
  `avatar` VARCHAR(32) COMMENT '头像',
  `age` INTEGER DEFAULT 18 COMMENT '年龄',
  `sex` TINYINT DEFAULT 1 COMMENT '性别',
  `desc` VARCHAR(64) COMMENT '描述',
  `type` TINYINT NOT NULL DEFAULT 1 COMMENT '类型(一级/二级/三级/四级)',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(正常/受限/封禁)',
  `last_login_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '最后登录时间',
  `last_login_ip` INTEGER UNSIGNED COMMENT '最后登录ip',
  `last_login_local` VARCHAR(32) COMMENT '最后登录地点',
  `delete_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '逻辑删除标志',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  UNIQUE INDEX uniq_name(name)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='管理员表';

-- 公告表
DROP TABLE IF EXISTS notice;
CREATE TABLE notice (
  `id` VARCHAR(32) PRIMARY KEY,
  `name` VARCHAR(16) NOT NULL COMMENT '名称',
  `desc` VARCHAR(128) NOT NULL COMMENT '描述',
  `type` TINYINT NOT NULL DEFAULT 1 COMMENT '类型(自定义/一般/普通/警告/危险)',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(已公布/未公布)',
  `ip` INTEGER UNSIGNED COMMENT 'ip地址',
  `operator_id` VARCHAR(32) NOT NULL COMMENT '操作者id',
  `delete_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '逻辑删除标志',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间'
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='公告表';

-- 系统日志表
DROP TABLE IF EXISTS sys_log;
CREATE TABLE sys_log (
  `id` VARCHAR(32) PRIMARY KEY,
  `local` VARCHAR(32) COMMENT '位置',
  `desc` VARCHAR(128) COMMENT '描述',
  `type` TINYINT NOT NULL DEFAULT 1 COMMENT '类型(信息/调试/警告/错误)',
  `ip` INTEGER UNSIGNED COMMENT 'ip地址',
  `operator_type` TINYINT NOT NULL COMMENT '操作者类型(用户/管理员)',
  `operator_id` VARCHAR(32) NOT NULL COMMENT '操作者id',
  `delete_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '逻辑删除标志',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间'
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='系统日志表(记录系统运行日志)';

-- 字典类型表
DROP TABLE IF EXISTS dictionary_type;
CREATE TABLE dictionary_type (
  `id` VARCHAR(32) PRIMARY KEY,
  `code` TINYINT NOT NULL COMMENT '类型代码',
  `name` VARCHAR(8) NOT NULL COMMENT '类型名称',
  `desc` VARCHAR(64) COMMENT '类型描述',
  `delete_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '逻辑删除标志',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  UNIQUE INDEX uniq_code(code),
  UNIQUE INDEX uniq_name(name)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='字典类型表';

-- 字典明细表
DROP TABLE IF EXISTS dictionary_info;
CREATE TABLE dictionary_info(
  `id` VARCHAR(32) PRIMARY KEY,
  `code` TINYINT NOT NULL COMMENT '字典代码',
  `name` VARCHAR(8) NOT NULL COMMENT '字典名称',
  `rank` TINYINT NOT NULL COMMENT '等级',
  `desc` VARCHAR(64) COMMENT '描述',
  `dictionary_type` VARCHAR(32) NOT NULL COMMENT '字典类型(外键->字典类型表)',
  `delete_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '逻辑删除标志',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  UNIQUE INDEX uniq_code(code),
  UNIQUE INDEX uniq_name(name)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='字典明细表';


