---Permissions

insert into permission(name) values ('ROLE_ADMIN');
insert into permission(name) values ('ROLE_AGENT');
insert into permission(name) values ('ROLE_USER');

insert into permission(name) values ('CREATE_AD');
insert into permission(name) values ('CREATE_REVIEW');
insert into permission(name) values ('USE_CART');
insert into permission(name) values ('ORDER');

--Admin
insert into users(dtype,id,email,reset_pass,password,username,address,mbr,name,quota,firstname,lastname) values ('Admin',-1,'admin@gmail.com',null,'$2y$12$Q3xvElaABTnytfulcYp2MeSjhj6Ac/oOuOu1KJ/bX9WbWvorvJava','admin1',null,null,null,null,null,null);
insert into user_permissions(user_id,permission_id) values (-1,'ROLE_ADMIN');

insert into users(dtype,id,email,reset_pass,password,username,address,mbr,name,quota,firstname,lastname) values ('EndUser',-2,'enduser@gmail.com',null,'$2y$12$Q3xvElaABTnytfulcYp2MeSjhj6Ac/oOuOu1KJ/bX9WbWvorvJava','user1',null,null,null,null,'Ana','Jankovic');
insert into user_permissions(user_id,permission_id) values (-2,'ROLE_USER');
insert into user_permissions(user_id,permission_id) values (-2,'CREATE_AD');
insert into user_permissions(user_id,permission_id) values (-2,'USE_CART');
insert into user_permissions(user_id,permission_id) values (-2,'ORDER');

insert into users(dtype,id,email,reset_pass,password,username,address,mbr,name,quota,firstname,lastname) values ('EndUser',-3,'enduser2@gmail.com',null,'$2y$12$Q3xvElaABTnytfulcYp2MeSjhj6Ac/oOuOu1KJ/bX9WbWvorvJava','user2',null,null,null,null,'Marko','Milic');
insert into user_permissions(user_id,permission_id) values (-3,'ROLE_USER');
insert into user_permissions(user_id,permission_id) values (-3,'CREATE_AD');
insert into user_permissions(user_id,permission_id) values (-3,'USE_CART');
insert into user_permissions(user_id,permission_id) values (-3,'CREATE_REVIEW');

insert into users(dtype,id,email,reset_pass,password,username,address,mbr,name,quota,firstname,lastname) values ('Agent',-4,'agent@gmail.com',null,'$2y$12$Q3xvElaABTnytfulcYp2MeSjhj6Ac/oOuOu1KJ/bX9WbWvorvJava','agent1','Ravanicka 12',12,null,1,null,null);
insert into user_permissions(user_id,permission_id) values (-4,'ROLE_AGENT');
insert into user_permissions(user_id,permission_id) values (-4,'CREATE_REVIEW');
