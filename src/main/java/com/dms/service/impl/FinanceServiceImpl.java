package com.dms.service.impl;

import com.dms.dao.FinanceDao;
import com.dms.dao.ProjectCommissionDao;
import com.dms.dto.FinanceDto;
import com.dms.dto.ProjectCommissionDto;
import com.dms.dto.SalaryBill;
import com.dms.request.FinanceFilterRequest;
import com.dms.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceDao financeDao;

    @Autowired
    private ProjectCommissionDao projectCommissionDao;


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
    public void synchronizeDesignerAssistantCommission(String currentMonth) {
        financeDao.synchronizeDesignerAssistantCommission(currentMonth);
    }

    @Override
    @Transactional
    public void synchronizeDesignerCommissions(String currentMonth) {

        List<ProjectCommissionDto> commissionDtos = projectCommissionDao.getCurrentMonthProjectCommissions(currentMonth);
        for (ProjectCommissionDto projectCommissionDto : commissionDtos) {
            FinanceFilterRequest financeFilterRequest = new FinanceFilterRequest();
            financeFilterRequest.setEmployeeName(projectCommissionDto.getDesigner());
            financeFilterRequest.setMonth(currentMonth);
            List<FinanceDto> financeDtos = financeDao.getFinances(financeFilterRequest);
            if (financeDtos != null && financeDtos.size() > 0) {
                FinanceDto financeDto = financeDtos.get(0);
                BigDecimal bonus = BigDecimal.ZERO;
                if (projectCommissionDto.getFirstCommission() != null) {
                    bonus = projectCommissionDto.getFirstCommission();
                }
                if (bonus != BigDecimal.ZERO) {
                    if (projectCommissionDto.getBalanceCommission() != null) {
                        bonus = bonus.add(projectCommissionDto.getBalanceCommission());
                    }
                } else {
                    bonus = projectCommissionDto.getBalanceCommission();
                }
                financeDto.setBonusCash(null);
                financeDto.setBonusCard(bonus);
                financeDto.setCommission(bonus);
                financeDto.setIschanged(false);
                financeDao.updateFinance(financeDto);
            }
        }
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
