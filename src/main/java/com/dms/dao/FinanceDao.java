package com.dms.dao;

import java.util.List;

import com.dms.dto.FinanceDto;
import com.dms.request.DataGridRequest;

public interface FinanceDao {

	List<FinanceDto> getFinances(DataGridRequest request);

	void saveFinance(FinanceDto financeDto);

	void updateFinance(FinanceDto financeDto);
}
