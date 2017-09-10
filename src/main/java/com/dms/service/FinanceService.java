package com.dms.service;

import java.util.List;

import com.dms.dto.FinanceDto;
import com.dms.dto.SalaryBill;
import com.dms.request.FinanceFilterRequest;

public interface FinanceService {

	List<FinanceDto> getFinances(FinanceFilterRequest request);

	void saveFinance(FinanceDto financeDto);

	void updateFinance(FinanceDto financeDto);

	void synchronizeDesignerCommissions(String currentMonth);

	void synchronizeDesignerAssistantCommission(String currentMonth);

	List<SalaryBill> getSalaryBills(FinanceFilterRequest request);

}
