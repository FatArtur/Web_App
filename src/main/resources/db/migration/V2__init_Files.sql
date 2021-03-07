create table Web_app.Files(
 id int AUTO_INCREMENT PRIMARY KEY,
 name varchar(45) NOT NULL UNIQUE,
 address varchar(45) NOT NULL UNIQUE,
 FileStatus varchar(45) NOT NULL
);

insert into files(name, address, FileStatus)
values('file_1', 'c:\files\file_1', 'ACTIVE');

insert into files(name, address, FileStatus)
values('file_2', 'c:\files\file_2', 'ACTIVE');

insert into files(name, address, FileStatus)
values('file_3', 'c:\files\file_3', 'ACTIVE');

insert into files(name, address, FileStatus)
values('file_4', 'c:\files\file_4', 'ACTIVE');

insert into files(name, address, FileStatus)
values('file_5', 'c:\files\file_5', 'ACTIVE');

insert into files(name, address, FileStatus)
values('file_6', 'c:\files\file_6', 'BANNED');

