package com.dms.ws.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dto.ChargeDetailDto;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;
import com.dms.request.DataGridRequest;
import com.dms.request.FinanceRequest;
import com.dms.response.DataGridResponse;
import com.dms.service.ChargeService;
import com.dms.service.EmployeeService;
import com.dms.service.FinanceService;
import com.dms.utils.PersonalIncomeTaxUtils;
import com.dms.ws.FinanceWebService;

@Service("financeWebService")
public class FinanceWebServiceImpl implements FinanceWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeWebServiceImpl.class);

	@Autowired
	private FinanceService financeService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ChargeService chargeService;

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
			financeDto.setGrossPay(calculateGrossPay(financeDto));
			financeDto.setBeforeTaxSalary(financeDto.getGrossPay().subtract(financeDto.getMedicalInsurance()).subtract(financeDto.getHousingFund()));
			BigDecimal personalIncomeTax = PersonalIncomeTaxUtils.getPersonalIncomeTax(financeDto.getBeforeTaxSalary());
			financeDto.setPersonalIncomeTax(personalIncomeTax);
			financeDto.setAfterTaxSalary(financeDto.getBeforeTaxSalary().subtract(personalIncomeTax));
			if (financeDto.getId() == null) {
				financeService.saveFinance(financeDto);
				if (!financeDto.getAlreadyCharge()) {
					EmployeeDto employeeDto = employeeService.getEmployee(financeDto.getEmployeeId());

					BigDecimal charge = employeeDto.getChargePerMonth()
							.add(employeeDto.getCharge() == null ? BigDecimal.ZERO : employeeDto.getChargePerMonth());

					ChargeDetailDto chargeDetailDto = new ChargeDetailDto();
					chargeDetailDto.setEmployeeId(Long.valueOf(employeeDto.getId()));
					chargeDetailDto.setCharge(employeeDto.getChargePerMonth());
					chargeDetailDto.setChargeBalance(employeeDto.getTotalCharge().subtract(charge));

					chargeService.audit(chargeDetailDto);

					employeeService.updateCharge(Long.valueOf(employeeDto.getId()), charge);
				}
			} else {
				financeService.updateFinance(financeDto);
			}
		}
	}

	private BigDecimal calculateGrossPay(FinanceDto financeDto) {
		BigDecimal grossPay = BigDecimal.ZERO;
		if (financeDto.getBaseWage() != null) {
			grossPay = grossPay.add(financeDto.getBaseWage());
		}
		if (financeDto.getOvertime() != null) {
			grossPay = grossPay.add(financeDto.getOvertime());
		}
		if (financeDto.getMealsSubsidy() != null) {
			grossPay = grossPay.add(financeDto.getMealsSubsidy());
		}
		if (financeDto.getSecrecySubsidy() != null) {
			grossPay = grossPay.add(financeDto.getSecrecySubsidy());
		}
		if (financeDto.getBonus() != null) {
			grossPay = grossPay.add(financeDto.getBonus());
		}
		if (financeDto.getWorkingAgeSubsidy() != null) {
			grossPay = grossPay.add(financeDto.getWorkingAgeSubsidy());
		}
		if (financeDto.getPerformanceAppraisal() != null) {
			grossPay = grossPay.add(financeDto.getPerformanceAppraisal());
		}
		if (financeDto.getCommunicationFee() != null) {
			grossPay = grossPay.add(financeDto.getCommunicationFee());
		}
		if (financeDto.getOtherSubsidy() != null) {
			grossPay = grossPay.add(financeDto.getOtherSubsidy());
		}
		if (financeDto.getCharge() != null) {
			grossPay.subtract(financeDto.getCharge());
		}
		if (financeDto.getExhibitionCharge() != null) {
			grossPay.subtract(financeDto.getExhibitionCharge());
		}
		if (financeDto.getCasualLeave() != null) {
			grossPay.subtract(financeDto.getCasualLeave());
		}
		if (financeDto.getSickLeave() != null) {
			grossPay.subtract(financeDto.getSickLeave());
		}
		if (financeDto.getStorageCharge() != null) {
			grossPay.subtract(financeDto.getStorageCharge());
		}
		return grossPay;
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
