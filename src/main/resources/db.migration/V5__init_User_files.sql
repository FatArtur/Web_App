create table Web_app.User_files(
 id int AUTO_INCREMENT PRIMARY KEY,
 user_id int NOT NULL REFERENCES Users (id),
 file_id int NOT NULL REFERENCES Files (id)
);

insert into user_files(user_id, file_id)
values(1, 1);

insert into user_files(user_id, file_id)
values(1, 3);

insert into user_files(user_id, file_id)
values(1, 2);

insert into user_files(user_id, file_id)
values(1, 4);

insert into user_files(user_id, file_id)
values(2, 6);

insert into user_files(user_id, file_id)
values(2, 5);

insert into user_files(user_id, file_id)
values(2, 1);

insert into user_files(user_id, file_id)
values(3, 3);

insert into user_files(user_id, file_id)
values(3, 2);

insert into user_files(user_id, file_id)
values(4, 4);

insert into user_files(user_id, file_id)
values(4, 6);

insert into user_files(user_id, file_id)
values(4, 5);

insert into user_files(user_id, file_id)
values(5, 5);

insert into user_files(user_id, file_id)
values(5, 4);

insert into user_files(user_id, file_id)
values(5, 2);

insert into user_files(user_id, file_id)
values(5, 1);

insert into user_files(user_id, file_id)
values(6, 6);

insert into user_files(user_id, file_id)
values(6, 4);

insert into user_files(user_id, file_id)
values(6, 2);

insert into user_files(user_id, file_id)
values(6, 1);