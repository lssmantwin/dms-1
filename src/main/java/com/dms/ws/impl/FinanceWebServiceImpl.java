package com.dms.ws.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dms.aspect.CheckAuthority;
import com.dms.enums.ResponseEnum;
import com.dms.export.FinanceExportXls;
import com.dms.utils.DateUtils;
import com.dms.utils.FileFactory;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dto.ChargeDetailDto;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;
import com.dms.request.FinanceFilterRequest;
import com.dms.request.FinanceRequest;
import com.dms.response.DmsResponse;
import com.dms.service.ChargeService;
import com.dms.service.EmployeeService;
import com.dms.service.FinanceService;
import com.dms.utils.PersonalIncomeTaxUtils;
import com.dms.ws.FinanceWebService;

import javax.ws.rs.core.Response;

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
	@CheckAuthority
	public DmsResponse<List<FinanceDto>> getFinances(String employeeName, int pageIndex, int pageSize, String sortField, String sortOrder, String month) {
		LOGGER.info("get finances, employeeName {}, pageIndex {}, pageSize {}, sortField {}, sortOrder {}", employeeName, pageIndex, pageSize, sortField,
				sortOrder);
		FinanceFilterRequest request = generateFilterRequest(employeeName, pageIndex, pageSize, sortField, sortOrder, month);
		int count = employeeService.getEmployeeCount(request);
		List<FinanceDto> finances = financeService.getFinances(request);
		DmsResponse<List<FinanceDto>> response = new DmsResponse<>();
		response.setCode(ResponseEnum.SUCCESS);
		response.setTotal(count);
		response.setData(finances);
		return response;
	}

	@Override
	@CheckAuthority
	public void saveFinances(FinanceRequest request) {

		LOGGER.info("save finances, {}", request);

		for (FinanceDto financeDto : request.getFinances()) {
			financeDto.setMonth(request.getMonth());
			financeDto.setGrossPay(calculateGrossPay(financeDto));
			financeDto.setBeforeTaxSalary(calculateBeforeTaxSalary(financeDto));
			BigDecimal personalIncomeTax = PersonalIncomeTaxUtils.getPersonalIncomeTax(financeDto.getBeforeTaxSalary());
			financeDto.setPersonalIncomeTax(personalIncomeTax);
			financeDto.setAfterTaxSalary(financeDto.getBeforeTaxSalary().subtract(personalIncomeTax));
			if (financeDto.getId() == null) {
				financeService.saveFinance(financeDto);
				if (!financeDto.getAlreadyCharge()) {
					EmployeeDto employeeDto = employeeService.getEmployee(financeDto.getEmployeeId());
					if (employeeDto.getCharge() != null && employeeDto.getStorageCharge() != null
							&& employeeDto.getStorageCharge().intValue() > employeeDto.getCharge().intValue()) {
						BigDecimal charge = employeeDto.getChargePerMonth().add(employeeDto.getCharge() == null ? BigDecimal.ZERO : employeeDto.getCharge());

						ChargeDetailDto chargeDetailDto = new ChargeDetailDto();
						chargeDetailDto.setEmployeeId(Long.valueOf(employeeDto.getId()));
						String year = financeDto.getMonth().substring(0, 4);
						String month = financeDto.getMonth().substring(4);
						Date currentMonth = DateUtils.getFirstDayOfMonthDate(Integer.valueOf(year), Integer.valueOf(month));
						chargeDetailDto.setChargeTime(LocalDateTime.fromDateFields(currentMonth));
						chargeDetailDto.setCharge(employeeDto.getChargePerMonth());
						chargeDetailDto.setChargeBalance(employeeDto.getStorageCharge().subtract(charge));
						financeDto.setChargePerMonth(employeeDto.getChargePerMonth());
						chargeService.audit(chargeDetailDto);

						employeeService.updateCharge(Long.valueOf(employeeDto.getId()), charge);
					}
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
		if (financeDto.getBonusCard() != null) {
			grossPay = grossPay.add(financeDto.getBonusCard());
		}
		if (financeDto.getBonusCash() != null) {
			grossPay = grossPay.add(financeDto.getBonusCash());
		}
		if (financeDto.getWorkingAgeSubsidy() != null) {
			grossPay = grossPay.add(financeDto.getWorkingAgeSubsidy());
		}
		if (financeDto.getPerformanceAppraisalCard() != null) {
			grossPay = grossPay.add(financeDto.getPerformanceAppraisalCard());
		}
		if (financeDto.getPerformanceAppraisalCash() != null) {
			grossPay = grossPay.add(financeDto.getPerformanceAppraisalCash());
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

	private BigDecimal calculateBeforeTaxSalary(FinanceDto financeDto) {
		BigDecimal beforeTaxSalary = BigDecimal.ZERO;
		if (financeDto.getBaseWage() != null) {
			beforeTaxSalary = beforeTaxSalary.add(financeDto.getBaseWage());
		}
		if (financeDto.getOvertime() != null) {
			beforeTaxSalary = beforeTaxSalary.add(financeDto.getOvertime());
		}
		if (financeDto.getMealsSubsidy() != null) {
			beforeTaxSalary = beforeTaxSalary.add(financeDto.getMealsSubsidy());
		}
		if (financeDto.getSecrecySubsidy() != null) {
			beforeTaxSalary = beforeTaxSalary.add(financeDto.getSecrecySubsidy());
		}
		if (financeDto.getBonusCard() != null) {
			beforeTaxSalary = beforeTaxSalary.add(financeDto.getBonusCard());
		}
		if (financeDto.getWorkingAgeSubsidy() != null) {
			beforeTaxSalary = beforeTaxSalary.add(financeDto.getWorkingAgeSubsidy());
		}
		if (financeDto.getPerformanceAppraisalCard() != null) {
			beforeTaxSalary = beforeTaxSalary.add(financeDto.getPerformanceAppraisalCard());
		}
		if (financeDto.getCommunicationFee() != null) {
			beforeTaxSalary = beforeTaxSalary.add(financeDto.getCommunicationFee());
		}
		if (financeDto.getOtherSubsidy() != null) {
			beforeTaxSalary = beforeTaxSalary.add(financeDto.getOtherSubsidy());
		}
		if (financeDto.getCharge() != null) {
			beforeTaxSalary.subtract(financeDto.getCharge());
		}
		if (financeDto.getExhibitionCharge() != null) {
			beforeTaxSalary.subtract(financeDto.getExhibitionCharge());
		}
		if (financeDto.getCasualLeave() != null) {
			beforeTaxSalary.subtract(financeDto.getCasualLeave());
		}
		if (financeDto.getSickLeave() != null) {
			beforeTaxSalary.subtract(financeDto.getSickLeave());
		}
		if (financeDto.getStorageCharge() != null) {
			beforeTaxSalary.subtract(financeDto.getStorageCharge());
		}
		if (financeDto.getMedicalInsurance() != null) {
			beforeTaxSalary.subtract(financeDto.getMedicalInsurance());
		}
		if (financeDto.getHousingFund() != null) {
			beforeTaxSalary.subtract(financeDto.getHousingFund());
		}
		return beforeTaxSalary;
	}

	private FinanceFilterRequest generateFilterRequest(String employeeName, int pageIndex, int pageSize, String sortField, String sortOrder, String month) {
		FinanceFilterRequest request = new FinanceFilterRequest();
		request.setStart(pageIndex * pageSize + 1);
		request.setEnd((pageIndex + 1) * pageSize);
		request.setEmployeeName(employeeName);
		request.setSortField(sortField);
		request.setSortOrder(sortOrder);
		request.setMonth(month);
		return request;
	}

	@Override
	public Response export(String employeeName, String month) {

		LOGGER.info("export finance");

		FinanceFilterRequest request = generateFilterRequest(employeeName, 0, 0, null, null, month);
		List<FinanceDto> finances = financeService.getFinances(request);
		InputStream in = null;
		try {
			in = new FinanceExportXls(finances).getStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return FileFactory.getResponse(in, LocalDateTime.now().toString("yyyyMMddHHmmSS"));
	}
}
