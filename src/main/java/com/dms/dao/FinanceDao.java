package com.dms.dao;

import java.util.List;

import com.dms.dto.FinanceDto;
import com.dms.dto.SalaryBill;
import com.dms.request.FinanceFilterRequest;

public interface FinanceDao {

	List<FinanceDto> getFinances(FinanceFilterRequest request);

	List<FinanceDto> getFinance(FinanceDto financeDto);

	void saveFinance(FinanceDto financeDto);

	void updateFinance(FinanceDto financeDto);

	void updateCommission(FinanceDto financeDto);

	List<SalaryBill> getSalaryBills(FinanceFilterRequest request);

	void lockFinances(String month);
}
