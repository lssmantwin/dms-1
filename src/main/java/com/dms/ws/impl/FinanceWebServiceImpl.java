package com.dms.ws.impl;

import com.dms.dto.FinanceDto;
import com.dms.request.DataGridRequest;
import com.dms.request.FinanceRequest;
import com.dms.response.DataGridResponse;
import com.dms.service.EmployeeService;
import com.dms.service.FinanceService;
import com.dms.utils.PersonalIncomeTaxUtils;
import com.dms.ws.FinanceWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("financeWebService")
public class FinanceWebServiceImpl implements FinanceWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeWebServiceImpl.class);

	@Autowired
	private FinanceService financeService;
	@Autowired
	private EmployeeService employeeService;

	@Override
	public DataGridResponse<List<FinanceDto>> getFinances(String key, int pageIndex, int pageSize, String sortField, String sortOrder, String month) {

		LOGGER.info("get finances, key {}, pageIndex {}, pageSize {}, sortField {}, sortOrder {}", key, pageIndex, pageSize, sortField, sortOrder);

		DataGridRequest request = generateDataGridRequest(key, pageIndex, pageSize, sortField, sortOrder, month);

		int count = employeeService.getEmployeeCount(request);
		List<FinanceDto> finances = financeService.getFinances(request);

		DataGridResponse<List<FinanceDto>> response = new DataGridResponse<>();
		response.setTotal(count);
		response.setData(finances);

		return response;
	}

	@Override
	public void saveFinances(FinanceRequest request) {

		LOGGER.info("save finances, {}", request);

		for (FinanceDto financeDto : request.getFinances()) {
			financeDto.setMonth(request.getMonth());
			BigDecimal grossPay = financeDto.getBaseWage().add(financeDto.getOvertime()).add(financeDto.getMealsSubsidy()).add(financeDto.getSecrecySubsidy())
					.add(financeDto.getBonus()).add(financeDto.getWorkingAgeSubsidy()).add(financeDto.getPerformanceAppraisal())
					.add(financeDto.getCommunicationFee()).add(financeDto.getOtherSubsidy()).subtract(financeDto.getCharge())
					.subtract(financeDto.getExhibitionCharge()).subtract(financeDto.getCasualLeave()).subtract(financeDto.getSickLeave())
					.subtract(financeDto.getStorageCharge());
			financeDto.setGrossPay(grossPay);
			financeDto.setBeforeTaxSalary(financeDto.getGrossPay().subtract(financeDto.getMedicalInsurance()).subtract(financeDto.getHousingFund()));
			BigDecimal personalIncomeTax = PersonalIncomeTaxUtils.getPersonalIncomeTax(financeDto.getBeforeTaxSalary());
			financeDto.setPersonalIncomeTax(personalIncomeTax);
			financeDto.setAfterTaxSalary(financeDto.getBeforeTaxSalary().subtract(personalIncomeTax));
			if (financeDto.getId() == null) {
				financeService.saveFinance(financeDto);
			} else {
				financeService.updateFinance(financeDto);
			}
		}
	}

	private DataGridRequest generateDataGridRequest(String key, int pageIndex, int pageSize, String sortField, String sortOrder, String month) {
		DataGridRequest request = new DataGridRequest();
		request.setStart(pageIndex * pageSize + 1);
		request.setEnd((pageIndex + 1) * pageSize);
		request.setKey(key);
		request.setSortField(sortField);
		request.setSortOrder(sortOrder);
		request.setMonth(month);
		return request;
	}
}
