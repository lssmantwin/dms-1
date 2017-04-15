package com.dms.ws.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.domain.*;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;
import com.dms.request.FinanceRequest;
import com.dms.service.EmployeeService;
import com.dms.utils.PersonalIncomeTaxUtils;
import com.dms.ws.EmployeeWebService;

@Service("employeeWebService")
public class EmployeeWebServiceImpl implements EmployeeWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeWebServiceImpl.class);

	@Autowired
	private EmployeeService employeeService;

	@Override
	public List<Attendance> getAttendance() {
		return employeeService.getAttendance();
	}

	@Override
	public List<Month> getMonths() {
		return employeeService.getMonths();
	}

	@Override
	public List<Position> getPositions() {

		LOGGER.info("get positions");

		return employeeService.getPositions();

	}

	@Override
	public void saveEmployees(List<EmployeeDto> employeeDtos) {

		LOGGER.info("save employees, {}", employeeDtos);

		for (EmployeeDto employeeDto : employeeDtos) {
			if (StringUtils.isBlank(employeeDto.getId())) {
				employeeService.saveEmployee(employeeDto);
			} else {
				employeeService.updateEmployee(employeeDto);
			}
		}
	}

	@Override
	public MiniResponse<List<EmployeeDto>> getEmployees(String key, int pageIndex, int pageSize, String sortField, String sortOrder) {

		LOGGER.info("get employees, key {}, pageIndex {}, pageSize {}, sortField {}, sortOrder {}", key, pageIndex, pageSize, sortField, sortOrder);

		MiniRequest request = generateMiniRequest(key, pageIndex, pageSize, sortField, sortOrder);

		int count = employeeService.getEmployeeCount(request);
		List<EmployeeDto> employees = employeeService.getEmployees(request);

		MiniResponse<List<EmployeeDto>> response = new MiniResponse<>();
		response.setTotal(count);
		response.setData(employees);

		return response;
	}

	@Override
	public MiniResponse<List<FinanceDto>> getFinances(String key, int pageIndex, int pageSize, String sortField, String sortOrder) {

		LOGGER.info("get finances, key {}, pageIndex {}, pageSize {}, sortField {}, sortOrder {}", key, pageIndex, pageSize, sortField, sortOrder);

		MiniRequest request = generateMiniRequest(key, pageIndex, pageSize, sortField, sortOrder);

		int count = employeeService.getEmployeeCount(request);
		List<FinanceDto> finances = employeeService.getFinances(request);

		MiniResponse<List<FinanceDto>> response = new MiniResponse<>();
		response.setTotal(count);
		response.setData(finances);

		return response;
	}

	private MiniRequest generateMiniRequest(String key, int pageIndex, int pageSize, String sortField, String sortOrder) {
		MiniRequest request = new MiniRequest();
		request.setStart(pageIndex * pageSize + 1);
		request.setEnd((pageIndex + 1) * pageSize);
		request.setKey(key);
		request.setSortField(sortField);
		request.setSortOrder(sortOrder);
		return request;
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
				employeeService.saveFinance(financeDto);
			} else {
				employeeService.updateFinance(financeDto);
			}
		}
	}

}
