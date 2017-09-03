package com.dms.service.impl;

import java.util.List;

import com.dms.dto.SalaryBill;
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
	public List<SalaryBill> getSalaryBills(FinanceFilterRequest request) {
		return financeDao.getSalaryBills(request);
	}

	@Override
	public void lockFinances(String month) {
		financeDao.lockFinances(month);
	}
}
