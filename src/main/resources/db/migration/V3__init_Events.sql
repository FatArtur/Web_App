create table Web_app.Events(
 id int AUTO_INCREMENT PRIMARY KEY,
 data date NOT NULL,
 file_id int NOT NULL REFERENCES Files (id)
);

insert into events(data, file_id)
values('2020-12-01', 4);

insert into events(data, file_id)
values('2020-12-04', 3);

insert into events(data, file_id)
values('2020-03-01', 2);

insert into events(data, file_id)
values('2020-06-06', 1);

insert into events(data, file_id)
values('2020-12-01', 6);

insert into events(data, file_id)
values('2020-05-06', 5);

insert into events(data, file_id)
values('2020-12-12', 4);

insert into events(data, file_id)
values('2020-11-12', 2);

insert into events(data, file_id)
values('2020-07-12', 2);

insert into events(data, file_id)
values('2020-08-07', 1);

insert into events(data, file_id)
values('2020-12-03', 6);

insert into events(data, file_id)
values('2020-11-12', 5);