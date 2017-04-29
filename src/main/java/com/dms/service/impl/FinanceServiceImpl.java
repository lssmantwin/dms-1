package com.dms.service.impl;

import com.dms.dao.FinanceDao;
import com.dms.dto.FinanceDto;
import com.dms.request.DataGridRequest;
import com.dms.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {

	@Autowired
	private FinanceDao financeDao;

	@Override
	public List<FinanceDto> getFinances(DataGridRequest request) {
		return financeDao.getFinances(request);
	}

	@Override
	public void saveFinance(FinanceDto financeDto) {
		financeDao.saveFinance(financeDto);
	}

	@Override
	public void updateFinance(FinanceDto financeDto) {
		financeDao.updateFinance(financeDto);
	}
}
