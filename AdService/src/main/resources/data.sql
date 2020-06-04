insert into user(id) values (-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-5,'AUDI','sedan',false,1,'diesel',50000,1000,'A6','automatic');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id) values (-1,-5,null,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-6,'RENAULT','hatchback',false,0,'diesel',50000,1000,'Clio','manual');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id) values (-2,-6,null,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-7,'BMW','sedan',false,1,'diesel',66000,5000,'3','manual');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id) values (-3,-7,null,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-8,'Volkswagen','hatchback',true,2,'diesel',30000,2000,'Golf 7','automatic');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id) values (-4,-8,null,null,-4);

insert into car(id,brand,car_class,cdw,child_seats,fuel,km,km_limit,model,transmission) values (-9,'BMW','coupe',true,0,'diesel',50000,1000,'3','automatic');
insert into ad(id,car_id,price_list_id,rent_request_id,user_id) values (-5,-9,null,null,-4);