alter table salary_project_commission add first_commission_rate decimal(4,4);
alter table salary_project_commission add balance_commission_rate decimal(4,4);
alter table salary_project_commission add purchase_agent_contract_fee decimal(14,4);
alter table salary_project_commission add purchasing_cost  decimal(14,4);
alter table salary_project_commission add commission_base  decimal(14,4);
alter table salary_project_commission add design_commission  decimal(14,4);
ALTER TABLE salary_project_commission DROP COLUMN commission_state;
ALTER TABLE salary_project_commission ADD commission_state int not null DEFAULT 0;
ALTER TABLE salary_project_commission add created_time datetime;
ALTER TABLE salary_project_commission add updated_time datetime;
ALTER TABLE salary_project_commission add designer_assistant_commission_date datetime;
ALTER TABLE salary_project_commission add designer_assistant_commission  decimal(14,4);
ALTER TABLE salary_project_commission add employee_id bigint not null;
ALTER TABLE salary_project_commission add design_assistant_id bigint not null;

