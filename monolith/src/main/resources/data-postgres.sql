---Permissions

insert into permission(name) values ('ROLE_ADMIN');
insert into permission(name) values ('ROLE_AGENT');
insert into permission(name) values ('ROLE_USER');

insert into permission(name) values ('CREATE_AD');
insert into permission(name) values ('CREATE_REVIEW');
insert into permission(name) values ('USE_CART');
insert into permission(name) values ('ORDER');

--Admin
insert into users(dtype,id,email,reset_pass,password,username,address,mbr,name,quota,firstname,lastname) values ('Admin',-1,'admin@gmail.com',null,'$2y$12$8uNNYdoyVJf8iJTNCGKwoOYLY9YLc3quGBmrs3RGJbFVJ9yA6JxTy','admin',null,null,null,null,null,null);
insert into user_permissions(user_id,permission_id) values (-1,'ROLE_ADMIN');