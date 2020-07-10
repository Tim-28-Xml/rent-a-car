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
insert into brands(brand) values ('Mercedes');
insert into brands(brand) values ('Audi');
insert into brands(brand) values ('BMW');

-- models list in BrandModels object
insert into brands_models(brands_brand, models) values ('Mercedes', 'C class');
insert into brands_models(brands_brand, models) values ('Mercedes', 'E class');
insert into brands_models(brands_brand, models) values ('Mercedes', 'S class');

insert into brands_models(brands_brand, models) values ('BMW', '5 series');
insert into brands_models(brands_brand, models) values ('BMW', '3 series');

insert into brands_models(brands_brand, models) values ('Audi', 'A4');
insert into brands_models(brands_brand, models) values ('Audi', 'A6');
insert into brands_models(brands_brand, models) values ('Audi', 'A8');

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
insert into user_permissions(user_id,permission_id) values (-4,'CREATE_AD');

insert into users(dtype,id,email,enabled,is_activated,reset_pass,password,username,address,mbr,name,quota,firstname,lastname) values ('EndUser',-5,'tamaraa.jancic@gmail.com','false','false',null,'$2y$12$Q3xvElaABTnytfulcYp2MeSjhj6Ac/oOuOu1KJ/bX9WbWvorvJava','user5',null,null,null,null,'Maja','Jovic');
insert into user_permissions(user_id,permission_id) values (-5,'ROLE_USER');
insert into user_permissions(user_id,permission_id) values (-5,'CREATE_AD');
insert into user_permissions(user_id,permission_id) values (-5,'USE_CART');
insert into user_permissions(user_id,permission_id) values (-5,'ORDER');
insert into user_permissions(user_id,permission_id) values (-5,'CREATE_REVIEW');

--car
insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-1,'AUDI','sedan',false,1,'diesel',50000,1000,'A6','automatic');
insert into ad(id,car_id,price_list_id,user_id) values (-1,-1,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-2,'RENAULT','hatchback',false,0,'diesel',50000,1000,'Clio','manual');
insert into ad(id,car_id,price_list_id,user_id) values (-2,-2,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-3,'BMW','sedan',false,1,'diesel',66000,5000,'3','manual');
insert into ad(id,car_id,price_list_id,user_id) values (-3,-3,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-4,'Volkswagen','hatchback',true,2,'diesel',30000,2000,'Golf 7','automatic');
insert into ad(id,car_id,price_list_id,user_id) values (-4,-4,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-5,'BMW','coupe',true,0,'diesel',50000,1000,'3','automatic');
insert into ad(id,car_id,price_list_id,user_id) values (-5,-5,null,-4);

insert into rent_request(id, creation_time, has_report, request_status, reservation_time, creator_id, owner_id) values (-1, '2020-06-08 15:14:14', 'false', 0, null , -2, -4);
insert into rent_request(id, creation_time, has_report, request_status, reservation_time, creator_id, owner_id) values (-2, '2020-06-08 15:14:14', 'false', 1, null , -2, -4);
insert into rent_request(id, creation_time, has_report, request_status, reservation_time, creator_id, owner_id) values (-3, '2020-06-08 15:14:14', 'false', 2, null , -2, -4);
insert into rent_request(id, creation_time, has_report, request_status, reservation_time, creator_id, owner_id) values (-4, '2020-06-08 15:14:14', 'false', 3, null , -2, -4);
insert into rent_request(id, creation_time, has_report, request_status, reservation_time, creator_id, owner_id) values (-7, '2020-06-08 15:14:14', 'false', 2, null , -2, -4);

insert into ad_date_range(id, ad_id, start, end_date) values (-1, -2, '2020-03-08', '2020-05-08');
insert into ad_date_range(id, ad_id, start, end_date) values (-2, -5, '2020-06-08', '2020-06-18');
insert into ad_date_range(id, ad_id, start, end_date) values (-3, -2, '2020-06-08', '2020-06-19');
insert into ad_date_range(id, ad_id, start, end_date) values (-4, -3, '2020-03-03', '2020-06-08');
insert into ad_date_range(id, ad_id, start, end_date) values (-5, -4, '2020-09-08', '2020-09-08');
insert into ad_date_range(id, ad_id, start, end_date) values (-6, -5, '2020-01-08', '2020-02-08');

insert into rent_request_ads_with_dates(rent_request_id, ads_with_dates_id) values (-1, -1);
insert into rent_request_ads_with_dates(rent_request_id, ads_with_dates_id) values (-1, -2);
insert into rent_request_ads_with_dates(rent_request_id, ads_with_dates_id) values (-2, -3);
insert into rent_request_ads_with_dates(rent_request_id, ads_with_dates_id) values (-3, -4);
insert into rent_request_ads_with_dates(rent_request_id, ads_with_dates_id) values (-4, -5);
insert into rent_request_ads_with_dates(rent_request_id, ads_with_dates_id) values (-7, -6);

insert into ad_rent_requests(ad_id, request_id) values (-1, -1);
insert into ad_rent_requests(ad_id, request_id) values (-2, -2);
insert into ad_rent_requests(ad_id, request_id) values (-3, -3);
insert into ad_rent_requests(ad_id, request_id) values (-4, -4);
insert into ad_rent_requests(ad_id, request_id) values (-5, -7);

insert into review(id,title,content,rating,time,ad_id,creator_id,approved) values ( -4,'Odlicno',
'Kola su super a pregovori sa firmom su odlicno prosli,moja preporuka!',5,'2020-03-06 20:41:05.0+00
',-5,-2,true);

insert into review(id,title,content,rating,time,ad_id,creator_id,approved) values ( -5,'Katastrofa',
'Dali su mi kola bez punog rezervoara. Nikad vise necu iznajmiti kola od ove firme',1,'2020-01-01 11:11:05.0+00
',-2,-3,false);


insert into review(id,title,content,rating,time,ad_id,creator_id,approved) values ( -6,'Uredu',
'Sve je bilo okej.',3,'2020-05-02 12:00:05.0+00
',-5,-5,false);
