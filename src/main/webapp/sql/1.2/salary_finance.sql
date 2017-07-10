alter table salary_finance add other_subsidy_cash decimal(10,2);
alter table salary_finance add post_allowance decimal(10,2);
EXEC sp_rename 'salary_finance.[other_subsidy]', 'other_subsidy_card', 'column';