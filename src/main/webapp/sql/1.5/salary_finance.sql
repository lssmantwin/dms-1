alter table salary_finance add commission decimal(10,2);
alter table salary_finance add ischanged bit not null default 0;
alter table salary_finance add lock bit not null default 0;

