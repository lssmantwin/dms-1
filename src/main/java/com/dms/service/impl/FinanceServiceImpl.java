package com.dms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.FinanceDao;
import com.dms.dto.FinanceDto;
import com.dms.request.FinanceFilterRequest;
import com.dms.service.FinanceService;

@Service
public class FinanceServiceImpl implements FinanceService {

	@Autowired
	private FinanceDao financeDao;

	@Override
	public List<FinanceDto> getFinances(FinanceFilterRequest request) {
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

	@Override
	public void updateCommission(FinanceDto financeDto) {
		financeDao.updateFinance(financeDto);
	}

	@Override
	public void updateCommissions(List<FinanceDto> financeDtos) {

	}
}
