--Users--
insert into user (username) values ('agent1');
insert into user( username) values ('user1');
insert into user( username) values ('user2');
insert into user( username) values ('user5');

--Ads--

insert into ad(id) values (-5);
insert into ad(id) values (-2);

--Reviews--
insert into review(id,title,content,rating,time,ad_id,creator_username,approved) values ( -4,'Odlicno',
'Kola su super a pregovori sa firmom su odlicno prosli,moja preporuka!',10,'2020-03-06 20:41:05.0+00
',-5,'user1',true);

insert into review(id,title,content,rating,time,ad_id,creator_username,approved) values ( -5,'Katastrofa',
'Dali su mi kola bez punog rezervoara. Nikad vise necu iznajmiti kola od ove firme',1,'2020-01-01 11:11:05.0+00
',-2,'user2',false);


insert into review(id,title,content,rating,time,ad_id,creator_username,approved) values ( -6,'Uredu',
'Sve je bilo okej.',7,'2020-05-02 12:00:05.0+00
',-5,'user5',false);






