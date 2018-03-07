USE db_front;

-- 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  `id` VARCHAR(32) PRIMARY KEY,
  `name` VARCHAR(16) NOT NULL COMMENT '用户名',
  `pass` VARCHAR(16) NOT NULL COMMENT '密码',
  `phone` VARCHAR(11) NOT NULL COMMENT '手机号',
  `email` VARCHAR(32) COMMENT '邮箱',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(正常/封禁)',
  `last_login_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '最后登录时间',
  `last_login_ip` INTEGER UNSIGNED COMMENT '最后登录ip',
  `last_login_local` VARCHAR(32) COMMENT '最后登录地点',
  `delete_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '逻辑删除标志',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  UNIQUE INDEX uniq_phone(phone),
  UNIQUE INDEX uniq_name(name),
  UNIQUE INDEX uniq_email(email)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='用户表';

-- 账户信息表
DROP TABLE IF EXISTS account;
CREATE TABLE account(
  `id` VARCHAR(32) PRIMARY KEY,
  `nickname` VARCHAR(16) NOT NULL COMMENT '昵称/用户名',
  `phone` VARCHAR(11) NOT NULL COMMENT '手机号',
  `email` VARCHAR(32) COMMENT '邮箱',
  `avatar` VARCHAR(32) COMMENT '头像',
  `age` INTEGER DEFAULT 18 COMMENT '年龄',
  `sex` TINYINT DEFAULT 1 COMMENT '性别',
  `desc` VARCHAR(64) COMMENT '描述',
  `qq` VARCHAR(64) COMMENT 'qq号',
  `wechat` VARCHAR(64) COMMENT '微信号',
  `realname` VARCHAR(16) COMMENT '真实姓名',
  `identity` VARCHAR(18) COMMENT '身份证号',
  `type` TINYINT NOT NULL DEFAULT 1 COMMENT '类型(普通/高级/会员)',
  `delete_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '逻辑删除标志',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  UNIQUE INDEX uniq_nickname(nickname),
  UNIQUE INDEX uniq_phone(phone),
  UNIQUE INDEX uniq_email(email),
  UNIQUE INDEX uniq_qq(qq),
  UNIQUE INDEX uniq_wechat(wechat),
  UNIQUE INDEX uniq_identity(identity)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='账户信息表';

-- 附件表
DROP TABLE IF EXISTS attachment;
CREATE TABLE attachment(
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL COMMENT '附件名(新名称)',
  `old_name` VARCHAR(32) NOT NULL COMMENT '原始名',
  `name_postfix` VARCHAR(16) COMMENT '后缀名',
  `size` BIGINT NOT NULL COMMENT '大小',
  `type` TINYINT NOT NULL DEFAULT 1 COMMENT '类型(图片/视频/音频/文本)',
  `path_prefix` VARCHAR(64) NOT NULL COMMENT '路径前缀',
  `delete_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '逻辑删除标志',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  UNIQUE INDEX uniq_name(name)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='附件表';


