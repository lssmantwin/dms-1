
 create table employee (
 	id bigint primary key identity(1,1),
  name varchar(32) not null,
 	position varchar(10) not null,
 	hiredate datetime not null,
 	base_wage decimal(10,2) not null,
 	bank_card_number varchar(30) not null,
 	overtime decimal(10,2),
  meals_subsidy decimal(10,2),
  secrecy_subsidy decimal(10,2),
  communication_fee decimal(10,2)
 )

