insert into user(id) values (-4);
insert into user(id) values (-2);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-5,'AUDI','sedan',false,1,'diesel',50000,1000,'A6','automatic');
insert into ad(id,city,car_id,price_list_id,rent_request_id,user_id) values (-1,'Novi Sad',-5,null,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-6,'RENAULT','hatchback',false,0,'diesel',50000,1000,'Clio','manual');
insert into ad(id,city,car_id,price_list_id,rent_request_id,user_id) values (-2,'Novi Sad',-6,null,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-7,'BMW','sedan',false,1,'diesel',66000,5000,'3','manual');
insert into ad(id,city,car_id,price_list_id,rent_request_id,user_id) values (-3,'Beograd',-7,null,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-8,'Volkswagen','hatchback',true,2,'diesel',30000,2000,'Golf 7','automatic');
insert into ad(id,city,car_id,price_list_id,rent_request_id,user_id) values (-4,'Novi Sad',-8,null,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-9,'BMW','coupe',true,0,'diesel',50000,1000,'3','automatic');
insert into ad(id,city,car_id,price_list_id,rent_request_id,user_id) values (-5,'Nis',-9,null,null,-4);

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

