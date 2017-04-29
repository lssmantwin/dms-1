 create table employee (
 	id bigint primary key identity(1,1),
  name varchar(32) not null,
 	position varchar(100) not null,
 	hiredate datetime not null,
 	base_wage decimal(10,2) not null,
 	working_age_subsidy decimal(10,2),
 	overtime decimal(10,2),
  meals_subsidy decimal(10,2),
  secrecy_subsidy decimal(10,2),
  communication_fee decimal(10,2),
  jyw_commission_ratio int,
  commencement_ratio int,
  completion_ratio int,
  renovate_commossion_ratio int,
  bank_card_number varchar(30) not null,
 )

