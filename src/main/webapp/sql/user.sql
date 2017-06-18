create table "user"(
  id bigint primary key identity(1,1),
  username varchar(255) not null unique,
  password varchar(255) not null,
  created_on datetime not null,
  last_login_on datetime not null,
  login_count int not null default 1
)