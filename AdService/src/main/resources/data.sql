--Users
insert into user(username) values ('agent1');
insert into user(username) values ('user1');
insert into user(username) values ('marko');
insert into user(username) values ('branko');


--Cars and ads
/*insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-5,'AUDI','sedan',false,1,'diesel',50000,1000,'A6','automatic');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id,city) values (-1,-5,null,null,-4,'Novi Sad');

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-6,'RENAULT','hatchback',false,0,'diesel',50000,1000,'Clio','manual');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id,city) values (-2,-6,null,null,-4,'Beograd');

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-7,'BMW','sedan',false,1,'diesel',66000,5000,'3','manual');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id,city) values (-3,-7,null,null,-4,'Nis');

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-8,'Volkswagen','hatchback',true,2,'diesel',30000,2000,'Golf 7','automatic');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id,city) values (-4,-8,null,null,-4,'Subotica');

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-9,'BMW','coupe',true,0,'diesel',50000,1000,'3','automatic');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id,city) values (-5,-9,null,null,-4,'Zagreb');*/

--Date range objects

insert into date_range(id,end_date,start_date) values (-5,'2020-08-10','2020-08-01');
insert into date(id,date,date_range_id) values (-5,'2020-08-03',-5);
insert into date(id,date,date_range_id) values (-6,'2020-08-04',-5);
insert into date(id,date,date_range_id) values (-7,'2020-08-05',-5);
insert into date_range_dates(date_range_id,dates_id) values (-5,-5);
insert into date_range_dates(date_range_id,dates_id) values (-5,-6);
insert into date_range_dates(date_range_id,dates_id) values (-5,-7);


insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-9,'BMW','coupe',true,0,'diesel',50000,1000,'3','automatic');
insert into ad(id,car_id,price_list_id,user_username,city) values (-5,-9,null,'agent1','Zagreb');
insert into ad_rent_dates(ad_id,rent_dates_id) values (-5,-5);


insert into date_range(id,end_date,start_date) values (-6,'2020-07-10','2020-07-01');
insert into date(id,date,date_range_id) values (-8,'2020-07-03',-6);
insert into date(id,date,date_range_id) values (-9,'2020-07-04',-6);
insert into date(id,date,date_range_id) values (-10,'2020-07-05',-6);
insert into date_range_dates(date_range_id,dates_id) values (-6,-8);
insert into date_range_dates(date_range_id,dates_id) values (-6,-9);
insert into date_range_dates(date_range_id,dates_id) values (-6,-10);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-6,'RENAULT','hatchback',false,0,'diesel',50000,1000,'Clio','manual');
insert into ad(id,car_id,price_list_id,user_username,city) values (-2,-6,null,'agent1','Beograd');

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-11,'MAZDA','coupe',true,0,'diesel',50000,1000,'6','manual');
insert into ad(id,car_id,price_list_id,user_username,city) values (-11,-11,null,'marko','Nis');

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-13,'SKODA','coupe',true,0,'diesel',50000,1000,'Superb','automatic');
insert into ad(id,car_id,price_list_id,user_username,city) values (-13,-13,null,'branko','Subotica');


insert into ad_rent_dates(ad_id,rent_dates_id) values (-2,-6);
insert into ad_rent_dates(ad_id,rent_dates_id) values (-11,-6);
insert into ad_rent_dates(ad_id,rent_dates_id) values (-13,-6);

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

