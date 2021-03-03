create table Web_app.User_events(
 id int AUTO_INCREMENT PRIMARY KEY,
 user_id int NOT NULL REFERENCES Users (id),
 event_id int NOT NULL REFERENCES Events (id)
);

insert into user_events(user_id, event_id)
values(1, 1);

insert into user_events(user_id, event_id)
values(1, 2);

insert into user_events(user_id, event_id)
values(1, 3);

insert into user_events(user_id, event_id)
values(2, 4);

insert into user_events(user_id, event_id)
values(3, 5);

insert into user_events(user_id, event_id)
values(3, 6);

insert into user_events(user_id, event_id)
values(1, 7);

insert into user_events(user_id, event_id)
values(4, 8);

insert into user_events(user_id, event_id)
values(5, 9);

insert into user_events(user_id, event_id)
values(6, 10);

insert into user_events(user_id, event_id)
values(6, 11);

insert into user_events(user_id, event_id)
values(6, 12);

