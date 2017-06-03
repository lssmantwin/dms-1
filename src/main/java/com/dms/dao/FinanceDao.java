package com.dms.dao;

import java.util.List;

import com.dms.dto.FinanceDto;
import com.dms.request.FinanceFilterRequest;

public interface FinanceDao {

	List<FinanceDto> getFinances(FinanceFilterRequest request);

	void saveFinance(FinanceDto financeDto);

	void updateFinance(FinanceDto financeDto);

	void updateCommission(FinanceDto financeDto);

	void updateCommissions(List<FinanceDto> financeDtos);

	void updateFinanceCommission(FinanceDto financeDto);


}
