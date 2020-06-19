insert into user(username) values ('user1');
insert into user(username) values ('user2');
insert into user(username) values ('user5');
insert into user(username) values ('admin1');
insert into user(username) values ('agent1');


insert into rent_request(id, creation_time, request_status, reservation_time, creator_username, owner_username) values (-1, '2020-06-08 15:14:14', 0, null , 'user1', 'agent1');
insert into rent_request(id, creation_time, request_status, reservation_time, creator_username, owner_username) values (-2, '2020-06-08 15:14:14', 1, null , 'user1', 'agent1');
insert into rent_request(id, creation_time, request_status, reservation_time, creator_username, owner_username) values (-3, '2020-06-08 15:14:14', 2, null , 'user1', 'agent1');
insert into rent_request(id, creation_time, request_status, reservation_time, creator_username, owner_username) values (-4, '2020-06-08 15:14:14', 3, null , 'user1', 'agent1');

insert into ad_date_range(id, ad_id, start, end) values (-1, -2, '2020-09-08', '2020-09-08');
insert into ad_date_range(id, ad_id, start, end) values (-2, -5, '2020-09-08', '2020-09-08');
insert into ad_date_range(id, ad_id, start, end) values (-3, -2, '2020-09-08', '2020-09-08');
insert into ad_date_range(id, ad_id, start, end) values (-4, -2, '2020-09-08', '2020-09-08');
insert into ad_date_range(id, ad_id, start, end) values (-5, -2, '2020-09-08', '2020-09-08');

insert into rent_request_ads_with_dates(rent_request_id, ads_with_dates_id) values (-1, -1);
insert into rent_request_ads_with_dates(rent_request_id, ads_with_dates_id) values (-1, -2);
insert into rent_request_ads_with_dates(rent_request_id, ads_with_dates_id) values (-2, -3);
insert into rent_request_ads_with_dates(rent_request_id, ads_with_dates_id) values (-3, -4);
insert into rent_request_ads_with_dates(rent_request_id, ads_with_dates_id) values (-4, -5);
