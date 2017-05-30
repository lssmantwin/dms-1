 create table charge_detail (
 	id bigint primary key identity(1,1),
 	charge_time datetime,
 	employee_id bigint,
  charge decimal(10,2),
  charge_balance decimal(10,2)
 )

