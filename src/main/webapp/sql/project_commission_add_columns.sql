alter table project_commission add first_commission_rate decimal(4,4);
alter table project_commission add balance_commission_rate decimal(4,4);
alter table project_commission add purchase_agent_contract_fee decimal(4,4);
alter table project_commission add purchasing_cost  decimal(4,4);
alter table project_commission add commission_base  decimal(4,4);
alter table project_commission add design_commission  decimal(4,4);
alter table project_commission alter commission_state  default("未提");
ALTER TABLE project_commission DROP COLUMN commission_state
ALTER TABLE project_commission ADD commission_state varchar(10) not null DEFAULT '未提'
alter table project_commission add created_time datetime;
alter table project_commission add update_time datetime;