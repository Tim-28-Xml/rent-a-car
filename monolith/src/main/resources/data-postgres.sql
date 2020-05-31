--Permissions

insert into permission(name) values ('ROLE_ADMIN');
insert into permission(name) values ('ROLE_AGENT');
insert into permission(name) values ('ROLE_USER');

insert into permission(name) values ('CREATE_AD');
insert into permission(name) values ('CREATE_REVIEW');
insert into permission(name) values ('USE_CART');
insert into permission(name) values ('ORDER');

--Codebook
insert into codebook(id) values (-1);

--BrandModels object
insert into brand_model(brand) values ('Mercedes');
insert into brand_model(brand) values ('Audi');
insert into brand_model(brand) values ('BMW');

-- models list in BrandModels object
insert into brand_model_models(brand_model_brand, models) values ('Mercedes', 'C class');
insert into brand_model_models(brand_model_brand, models) values ('Mercedes', 'E class');
insert into brand_model_models(brand_model_brand, models) values ('Mercedes', 'S class');

insert into brand_model_models(brand_model_brand, models) values ('BMW', '5 series');
insert into brand_model_models(brand_model_brand, models) values ('BMW', '3 series');

insert into brand_model_models(brand_model_brand, models) values ('Audi', 'A4');
insert into brand_model_models(brand_model_brand, models) values ('Audi', 'A6');
insert into brand_model_models(brand_model_brand, models) values ('Audi', 'A8');

--Codebook brandModels list
insert into codebook_brand_models(codebook_id, brand_models_brand) values (-1, 'Mercedes');
insert into codebook_brand_models(codebook_id, brand_models_brand) values (-1, 'BMW');
insert into codebook_brand_models(codebook_id, brand_models_brand) values (-1, 'Audi');

--Codebook fueltypes
insert into codebook_fuel_types(codebook_id, fuel_types) values (-1, 'Diesel');
insert into codebook_fuel_types(codebook_id, fuel_types) values (-1, 'Petrol');

--Codebook transmissions
insert into codebook_transmission_types(codebook_id, transmission_types) values  (-1, 'Manual 5 Gear');
insert into codebook_transmission_types(codebook_id, transmission_types) values  (-1, 'Automatic 8 Gear');

--Codebook car classes
insert into codebook_car_classes(codebook_id, car_classes) values (-1, 'Sedan');
insert into codebook_car_classes(codebook_id, car_classes) values (-1, 'SUV');
insert into codebook_car_classes(codebook_id, car_classes) values (-1, 'Hatchback');

--Admin
insert into users(dtype,id,email,enabled,is_activated,reset_pass,password,username,address,mbr,name,quota,firstname,lastname) values ('Admin',-1,'admin@gmail.com','true','true',null,'$2y$12$Q3xvElaABTnytfulcYp2MeSjhj6Ac/oOuOu1KJ/bX9WbWvorvJava','admin1',null,null,null,null,null,null);
insert into user_permissions(user_id,permission_id) values (-1,'ROLE_ADMIN');

--Users and permissions
insert into users(dtype,id,email,enabled,is_activated,reset_pass,password,username,address,mbr,name,quota,firstname,lastname) values ('EndUser',-2,'enduser@gmail.com','true','true',null,'$2y$12$Q3xvElaABTnytfulcYp2MeSjhj6Ac/oOuOu1KJ/bX9WbWvorvJava','user1',null,null,null,null,'Ana','Jankovic');
insert into user_permissions(user_id,permission_id) values (-2,'ROLE_USER');
insert into user_permissions(user_id,permission_id) values (-2,'CREATE_AD');
insert into user_permissions(user_id,permission_id) values (-2,'USE_CART');
insert into user_permissions(user_id,permission_id) values (-2,'ORDER');
insert into user_blocked_permissions(user_id,blocked_permissions) values (-2, 'CREATE_REVIEW');

insert into users(dtype,id,email,enabled,is_activated,reset_pass,password,username,address,mbr,name,quota,firstname,lastname) values ('EndUser',-3,'enduser2@gmail.com','true','true',null,'$2y$12$Q3xvElaABTnytfulcYp2MeSjhj6Ac/oOuOu1KJ/bX9WbWvorvJava','user2',null,null,null,null,'Marko','Milic');
insert into user_permissions(user_id,permission_id) values (-3,'ROLE_USER');
insert into user_permissions(user_id,permission_id) values (-3,'CREATE_AD');
insert into user_permissions(user_id,permission_id) values (-3,'USE_CART');
insert into user_permissions(user_id,permission_id) values (-3,'CREATE_REVIEW');
insert into user_blocked_permissions(user_id,blocked_permissions) values (-3, 'ORDER');

insert into users(dtype,id,email,enabled,is_activated,reset_pass,password,username,address,mbr,name,quota,firstname,lastname) values ('Agent',-4,'agent@gmail.com','true','true',null,'$2y$12$Q3xvElaABTnytfulcYp2MeSjhj6Ac/oOuOu1KJ/bX9WbWvorvJava','agent1','Ravanicka 12',12,null,1,null,null);
insert into user_permissions(user_id,permission_id) values (-4,'ROLE_AGENT');
insert into user_permissions(user_id,permission_id) values (-4,'CREATE_REVIEW');
insert into user_blocked_permissions(user_id,blocked_permissions) values (-4, 'CREATE_AD');

insert into users(dtype,id,email,enabled,is_activated,reset_pass,password,username,address,mbr,name,quota,firstname,lastname) values ('EndUser',-5,'tamaraa.jancic@gmail.com','false','false',null,'$2y$12$Q3xvElaABTnytfulcYp2MeSjhj6Ac/oOuOu1KJ/bX9WbWvorvJava','user5',null,null,null,null,'Maja','Jovic');
insert into user_permissions(user_id,permission_id) values (-5,'ROLE_USER');
insert into user_permissions(user_id,permission_id) values (-5,'CREATE_AD');
insert into user_permissions(user_id,permission_id) values (-5,'USE_CART');
insert into user_permissions(user_id,permission_id) values (-5,'ORDER');
insert into user_permissions(user_id,permission_id) values (-5,'CREATE_REVIEW');

--car
insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-1,'AUDI','sedan',false,1,'diesel',50000,1000,'A6','automatic');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id) values (-1,-1,null,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-2,'RENAULT','hatchback',false,0,'diesel',50000,1000,'Clio','manual');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id) values (-2,-2,null,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-3,'BMW','sedan',false,1,'diesel',66000,5000,'3','manual');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id) values (-3,-3,null,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-4,'Volkswagen','hatchback',true,2,'diesel',30000,2000,'Golf 7','automatic');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id) values (-4,-4,null,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-5,'BMW','coupe',true,0,'diesel',50000,1000,'3','automatic');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id) values (-5,-5,null,null,-4);