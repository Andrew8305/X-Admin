USE db_auth;

-- 角色表
DROP TABLE IF EXISTS role;
CREATE TABLE role (
  `id` VARCHAR(32) PRIMARY KEY,
  `name` VARCHAR(16) NOT NULL COMMENT '角色名',
  `desc` VARCHAR(32) COMMENT '角色描述',
  `type` TINYINT NOT NULL COMMENT '角色类型(用户/管理员)',
  `delete_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '逻辑删除标志',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  UNIQUE INDEX uniq_name(name)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='角色表';

-- 用户角色表(关联表)
DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role (
  `user_id` VARCHAR(32) NOT NULL COMMENT '用户id',
  `role_id` VARCHAR(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (user_id, role_id)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='用户角色表';

-- 管理员角色表(关联表)
DROP TABLE IF EXISTS admin_role;
CREATE TABLE admin_role (
  `admin_id` VARCHAR(32) NOT NULL COMMENT '管理员id',
  `role_id` VARCHAR(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (admin_id, role_id)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='管理员角色表';

-- 权限表
DROP TABLE IF EXISTS permission;
CREATE TABLE permission (
  `id` VARCHAR(32) PRIMARY KEY,
  `name` VARCHAR(16) NOT NULL COMMENT '权限名',
  `desc` VARCHAR(32) COMMENT '权限描述',
  `type` TINYINT NOT NULL COMMENT '权限类型(用户/管理员)',
  `delete_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '逻辑删除标志',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  UNIQUE INDEX uniq_name(name)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='权限表';

-- 角色权限表(关联表)
DROP TABLE IF EXISTS role_permission;
CREATE TABLE role_permission (
  `role_id` VARCHAR(32) NOT NULL COMMENT '角色id',
  `permission_id` VARCHAR(32) NOT NULL COMMENT '权限id',
  PRIMARY KEY (role_id, permission_id)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='角色权限表';

-- 资源表
DROP TABLE IF EXISTS resource;
CREATE TABLE resource (
  `id` VARCHAR(32) PRIMARY KEY,
  `parent_id` VARCHAR(32) NOT NULL COMMENT '父资源id',
  `name` VARCHAR(64) COMMENT '资源名',
  `desc` VARCHAR(64) COMMENT '资源描述',
  `path` VARCHAR(64) COMMENT '资源路径',
  `type` TINYINT NOT NULL COMMENT '资源类型(*/get/post/put/delete)',
  `delete_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '逻辑删除标志',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  UNIQUE INDEX uniq_path(path)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='资源表';

-- 资源权限表(关联表)
DROP TABLE IF EXISTS resource_perm;
CREATE TABLE resource_perm (
  `resource_id` VARCHAR(32) NOT NULL COMMENT '资源id',
  `permission_id` VARCHAR(32) NOT NULL COMMENT '权限id',
  PRIMARY KEY (resource_id, permission_id)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='资源权限表';

-- 资源角色表(关联表)
DROP TABLE IF EXISTS resource_role;
CREATE TABLE resource_role (
  `resource_id` VARCHAR(32) NOT NULL COMMENT '资源id',
  `role_id` VARCHAR(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (resource_id, role_id)
) ENGINE=innodb, CHARSET=utf8mb4, COMMENT='资源角色表';

