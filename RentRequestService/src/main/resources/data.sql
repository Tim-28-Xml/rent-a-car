insert into user(username) values ('user1');
insert into user(username) values ('user2');
insert into user(username) values ('user5');
insert into user(username) values ('admin1');
insert into user(username) values ('agent1');


insert into rent_request(id, creation_time, request_status, reservation_time, creator_username, owner_username) values (-1, '2020-06-08 15:14:14', 0, null , 'user1', 'agent1');
insert into rent_request(id, creation_time, request_status, reservation_time, creator_username, owner_username) values (-2, '2020-06-08 15:14:14', 1, null , 'user1', 'agent1');
insert into rent_request(id, creation_time, request_status, reservation_time, creator_username, owner_username) values (-3, '2020-06-08 15:14:14', 2, null , 'user1', 'agent1');
insert into rent_request(id, creation_time, request_status, reservation_time, creator_username, owner_username) values (-4, '2020-06-08 15:14:14', 3, null , 'user1', 'agent1');

insert into rent_request_ads(rent_request_id, ads) values (-1, -2);
insert into rent_request_ads(rent_request_id, ads) values (-1, -5);
insert into rent_request_ads(rent_request_id, ads) values (-2, -2);
insert into rent_request_ads(rent_request_id, ads) values (-3, -2);
insert into rent_request_ads(rent_request_id, ads) values (-4, -2);
