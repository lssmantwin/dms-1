alter table project_commission add first_commision_rate decimal(4,4);
alter table project_commission add balance_commision_rate decimal(4,4);
alter table project_commision add purchase_agent_contract_fee decimal(4,4);
alter table project_commision add purchasing_cost  decimal(4,4);
alter table project_commision add commision_base  decimal(4,4);
alter table project_commision add design_commision  decimal(4,4);
employee_id bigint not null,