-- db_front测试数据
USE db_front;
TRUNCATE TABLE user;
TRUNCATE TABLE account;

INSERT INTO user(id, phone, name, email, pass, status, last_login_ip, last_login_local)
VALUES
  ('410093246937563136', '15538306625', 'leaf', '806569552@qq.com', 'ecc8066511c4df6c', 1, INET_ATON('255.255.255.255'), '郑州市金水区文化路97号'),
  ('410093692271984644', '15538306624', 'leaf1', '3273104264@qq.com', 'd45465d3e25dc8b6', 1, INET_ATON('255.255.255.255'), '郑州市金水区文化路97号'),
  ('410093692271984643', '15538306623', 'leaf2', '3273104263@qq.com', 'd45465d3e25dc8b6', 1, INET_ATON('255.255.255.255'), '郑州市金水区文化路97号'),
  ('410093692271984642', '15538306622', 'leaf3', '3273104262qq.com', 'd45465d3e25dc8b6', 1, INET_ATON('255.255.255.255'), '郑州市金水区文化路97号'),
  ('410093692271984641', '15538306621', 'leaf4', '3273104261@qq.com', 'd45465d3e25dc8b6', 1, INET_ATON('255.255.255.255'), '郑州市金水区文化路97号');

INSERT INTO account(id, nickname, phone, email)
VALUES
  ('410093246937563136', 'leaf', '15538306625', '806569552@qq.com'),
  ('410093692271984644', 'leaf1', '15538306624', '3273104264@qq.com'),
  ('410093692271984643', 'leaf2', '15538306623', '3273104263@qq.com'),
  ('410093692271984642', 'leaf3', '15538306622', '3273104262@qq.com'),
  ('410093692271984641', 'leaf4', '15538306621', '3273104261@qq.com');

-- db_auth测试数据
USE db_auth;
TRUNCATE TABLE role;
TRUNCATE TABLE user_role;
TRUNCATE TABLE permission;
TRUNCATE TABLE role_permission;
TRUNCATE TABLE resource;
TRUNCATE TABLE resource_perm;
TRUNCATE TABLE admin_role;

INSERT INTO role(id, name, `desc`, type)
VALUES(1, 'user', '用户', 1), (2, 'admin', '管理员', 2);

INSERT INTO user_role
VALUES
  ('410093246937563136', 1),
  ('410093692271984644', 1),
  ('410093692271984643', 1),
  ('410093692271984642', 1),
  ('410093692271984641', 1);

INSERT INTO admin_role(admin_id, role_id)
VALUES ('410962076853338112', 2);


INSERT INTO permission(id, name, `desc`, type)
VALUES (1, 'user', '用户访问权限', 1), (2, 'admin', '管理员访问权限', 2);

INSERT INTO role_permission(role_id, permission_id)
VALUES (1, 1), (2, 1), (2, 2);

INSERT INTO resource_role(resource_id, role_id)
VALUES (1, 1), (2, 2);

INSERT INTO resource(id, name, `desc`, path, type)
VALUES
  (1, 'login', '用户登陆', '/user/login', 2),
  (2, 'scanAllResources', '持久化资源列表', '/resource/scanAllResources', 1);



-- db_background测试数据
USE db_background;
TRUNCATE TABLE admin;
INSERT INTO
  admin(id, name, pass, phone, email, type, status, last_login_ip, last_login_local)
VALUES ('410962076853338112', 'admin', '2942ac7f99804ca7', '15538306625', '806569552@qq.com', 1, 1, INET_ATON('255.255.255.255'), '郑州市金水区文化路97号');

