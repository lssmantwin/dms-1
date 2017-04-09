create table "user" (
    id int identity(1,1) primary key,
    username varchar(50) not null,
    password varchar(1000) not null
);