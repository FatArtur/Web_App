create table Web_app.Users(
 id int AUTO_INCREMENT PRIMARY KEY,
 name varchar(45) NOT NULL UNIQUE,
 account_id int NOT NULL REFERENCES Accounts (id)
);

insert into users(name, account_id)
values('Ivan', 3);

insert into users(name, account_id)
values('Boris', 2);

insert into users(name, account_id)
values('Dima', 1);

insert into users(name, account_id)
values('Eugene', 4);

insert into users(name, account_id)
values('Artur', 6);

insert into users(name, account_id)
values('Denis', 5);