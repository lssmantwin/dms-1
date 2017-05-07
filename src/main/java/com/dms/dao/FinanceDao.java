package com.dms.dao;

import com.dms.dto.FinanceDto;
import com.dms.request.DataGridRequest;

import java.util.List;

public interface FinanceDao {

    List<FinanceDto> getFinances(DataGridRequest request);

    void saveFinance(FinanceDto financeDto);

    void updateFinance(FinanceDto financeDto);
}
