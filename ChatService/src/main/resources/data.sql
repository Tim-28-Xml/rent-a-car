insert into user(username) values ('user1');
insert into user(username) values ('agent1');
insert into user(username) values ('maja');
insert into user(username) values ('firma rent');

insert into message(id,content,time,receiver_username,sender_username, read) values (-1,'Da li auto ima blutut?','2020-06-14 10:43:11', 'user1', 'agent1', true);
insert into message(id,content,time,receiver_username,sender_username, read) values (-2,'Koje boje imate u ponudi?','2020-06-16 15:43:54', 'user1', 'agent1',true);
insert into message(id,content,time,receiver_username,sender_username, read) values (-3,'Da li mogu da vozim vozilo van drzave?','2020-05-15 16:22:08', 'user1', 'agent1',true);
insert into message(id,content,time,receiver_username,sender_username, read) values (-4,'Koje je godiste vozilo?','2020-06-03 10:11:13', 'user1', 'maja',true);
insert into message(id,content,time,receiver_username,sender_username, read) values (-6,'Moze sve.','2020-05-15 17:15:04', 'agent1', 'user1',true);
insert into message(id,content,time,receiver_username,sender_username, read) values (-7,'Vozilo poseduje blutut.','2020-06-15 10:00:01', 'agent1', 'user1',true);
insert into message(id,content,time,receiver_username,sender_username, read) values (-8,'Jedina boja je crna.','2020-06-16 17:50:41', 'agent1', 'user1',true);
insert into message(id,content,time,receiver_username,sender_username, read) values (-9,'Vozilo je 2016 godiste.','2020-06-04 10:33:21', 'maja', 'user1',false);
