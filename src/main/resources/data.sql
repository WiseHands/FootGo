insert ignore into role (id, name) values ('1', 'ROLE_ADMIN');
insert ignore into role (id, name) values ('2', 'ROLE_USER');
insert ignore into user (id, created_on, email, enabled, first_name, last_name, password) values ('1', '1970-01-01 00:00:00', 'footgo.league@gmail.com', true, 'First Name', 'Last Name', '$2a$10$pI/slAp/P.B6uZvpSYXEz.itqhQts6QoKDXnYYUZPGYXZh/ppslli');
insert ignore into user_roles (user_id, roles_id) values (1, 1);