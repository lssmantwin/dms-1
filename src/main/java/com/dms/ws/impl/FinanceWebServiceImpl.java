package com.dms.ws.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import com.dms.dto.SalaryBill;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.aspect.CheckAuthority;
import com.dms.dto.ChargeDetailDto;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;
import com.dms.enums.ResponseEnum;
import com.dms.export.FinanceExportXls;
import com.dms.request.FinanceFilterRequest;
import com.dms.request.FinanceRequest;
import com.dms.response.DmsResponse;
import com.dms.service.ChargeService;
import com.dms.service.EmployeeService;
import com.dms.service.FinanceService;
import com.dms.utils.DateUtils;
import com.dms.utils.FileFactory;
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
	@CheckAuthority
	public DmsResponse getFinances(String employeeName, int pageIndex, int pageSize, String sortField, String sortOrder, String month) {
		LOGGER.info("get finances, employeeName {}, pageIndex {}, pageSize {}, sortField {}, sortOrder {}", employeeName, pageIndex, pageSize, sortField,
				sortOrder);
		FinanceFilterRequest request = generateFilterRequest(employeeName, pageIndex, pageSize, sortField, sortOrder, month);
		int count = employeeService.getEmployeeCount(request);
		List<FinanceDto> finances = financeService.getFinances(request);
		for (FinanceDto finance : finances) {
			if (finance.getContractWages() == null) {
				finance.setContractWages(calculateContractWages(finance));
			}
		}
		DmsResponse<List<FinanceDto>> response = new DmsResponse<>();
		response.setCode(ResponseEnum.SUCCESS);
		response.setTotal(count);
		response.setData(finances);
		return response;
	}

	@Override
	@CheckAuthority
	public DmsResponse saveFinances(FinanceRequest request) {

		LOGGER.info("save finances, {}", request);

		for (FinanceDto financeDto : request.getFinances()) {
			financeDto.setMonth(request.getMonth());
			financeDto.setStorageCharge(financeDto.getChargePerMonth());
			financeDto.setGrossPay(calculateGrossPay(financeDto));
			financeDto.setBeforeTaxSalary(calculateBeforeTaxSalary(financeDto));
			BigDecimal personalIncomeTax = PersonalIncomeTaxUtils.getPersonalIncomeTax(financeDto.getBeforeTaxSalary());
			financeDto.setPersonalIncomeTax(personalIncomeTax);
			financeDto.setAfterTaxSalary(financeDto.getBeforeTaxSalary().subtract(personalIncomeTax));
			financeDto.setSalaryCash(calculateSalaryCash(financeDto));
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
		DmsResponse<List<FinanceDto>> response = new DmsResponse<>();
		response.setCode(ResponseEnum.SUCCESS);
		return response;
	}

	// 合同工资
	private BigDecimal calculateContractWages(FinanceDto financeDto) {
		BigDecimal contractWages = BigDecimal.ZERO;
		if (financeDto.getBaseWage() != null) {
			contractWages = contractWages.add(financeDto.getBaseWage());
		}
		if (financeDto.getOvertime() != null) {
			contractWages = contractWages.add(financeDto.getOvertime());
		}
		if (financeDto.getMealsSubsidy() != null) {
			contractWages = contractWages.add(financeDto.getMealsSubsidy());
		}
		if (financeDto.getSecrecySubsidy() != null) {
			contractWages = contractWages.add(financeDto.getSecrecySubsidy());
		}
		if (financeDto.getWorkingAgeSubsidy() != null) {
			contractWages = contractWages.add(financeDto.getWorkingAgeSubsidy());
		}
		if (financeDto.getCommunicationFee() != null) {
			contractWages = contractWages.add(financeDto.getCommunicationFee());
		}
		return contractWages;
	}

	private BigDecimal calculateGrossPay(FinanceDto financeDto) {
		// 应发工资 = 基本工资 + 加班 + 餐补 + 保密 + 提成（卡） + 工龄 + 绩效 （卡）
		// + 通讯费 + 其他补贴（卡） - 扣款 - 会展扣款 - 事假 - 病假 - 保管费
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
		if (financeDto.getWorkingAgeSubsidy() != null) {
			grossPay = grossPay.add(financeDto.getWorkingAgeSubsidy());
		}
		if (financeDto.getPerformanceAppraisalCard() != null) {
			grossPay = grossPay.add(financeDto.getPerformanceAppraisalCard());
		}
		if (financeDto.getCommunicationFee() != null) {
			grossPay = grossPay.add(financeDto.getCommunicationFee());
		}
		if (financeDto.getOtherSubsidyCard() != null) {
			grossPay = grossPay.add(financeDto.getOtherSubsidyCard());
		}
		if (financeDto.getCharge() != null) {
			grossPay = grossPay.subtract(financeDto.getCharge());
		}
		if (financeDto.getExhibitionCharge() != null) {
			grossPay = grossPay.subtract(financeDto.getExhibitionCharge());
		}
		if (financeDto.getCasualLeave() != null) {
			grossPay = grossPay.subtract(financeDto.getCasualLeave());
		}
		if (financeDto.getSickLeave() != null) {
			grossPay = grossPay.subtract(financeDto.getSickLeave());
		}
		if (financeDto.getStorageCharge() != null) {
			grossPay = grossPay.subtract(financeDto.getStorageCharge());
		}
		return grossPay;
	}

	private BigDecimal calculateBeforeTaxSalary(FinanceDto financeDto) {
		// 税前工资 = 基本工资 + 加班 + 餐补 + 保密 + 提成（卡） + 工龄 + 绩效 （卡）
		// + 通讯费 + 其他补贴（卡） - 扣款 - 会展扣款 - 事假 - 病假 - 保管费 - 公积金 - 社保
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
		if (financeDto.getOtherSubsidyCard() != null) {
			beforeTaxSalary = beforeTaxSalary.add(financeDto.getOtherSubsidyCard());
		}
		if (financeDto.getCharge() != null) {
			beforeTaxSalary = beforeTaxSalary.subtract(financeDto.getCharge());
		}
		if (financeDto.getExhibitionCharge() != null) {
			beforeTaxSalary = beforeTaxSalary.subtract(financeDto.getExhibitionCharge());
		}
		if (financeDto.getCasualLeave() != null) {
			beforeTaxSalary = beforeTaxSalary.subtract(financeDto.getCasualLeave());
		}
		if (financeDto.getSickLeave() != null) {
			beforeTaxSalary = beforeTaxSalary.subtract(financeDto.getSickLeave());
		}
		if (financeDto.getStorageCharge() != null) {
			beforeTaxSalary = beforeTaxSalary.subtract(financeDto.getStorageCharge());
		}
		if (financeDto.getMedicalInsurance() != null) {
			beforeTaxSalary = beforeTaxSalary.subtract(financeDto.getMedicalInsurance());
		}
		if (financeDto.getHousingFund() != null) {
			beforeTaxSalary = beforeTaxSalary.subtract(financeDto.getHousingFund());
		}
		return beforeTaxSalary;
	}

	private BigDecimal calculateSalaryCash(FinanceDto financeDto) {
		BigDecimal salaryCash = BigDecimal.ZERO;
		// 岗位津贴
		if (financeDto.getPostAllowance() != null) {
			salaryCash = salaryCash.add(financeDto.getPostAllowance());
		}
		// 绩效现金
		if (financeDto.getPerformanceAppraisalCash() != null) {
			salaryCash = salaryCash.add(financeDto.getPerformanceAppraisalCash());
		}
		// 其他补贴现金
		if (financeDto.getOtherSubsidyCash() != null) {
			salaryCash = salaryCash.add(financeDto.getOtherSubsidyCash());
		}
		// 提成现金
		if (financeDto.getBonusCash() != null) {
			salaryCash = salaryCash.add(financeDto.getBonusCash());
		}
		// 其他扣款
		if (financeDto.getOtherCharge() != null) {
			salaryCash = salaryCash.subtract(financeDto.getOtherCharge());
		}
		return salaryCash;
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
		List<SalaryBill> bills = financeService.getSalaryBills(request);
		InputStream in = null;
		try {
			in = new FinanceExportXls(bills).getStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return FileFactory.getResponse(in, LocalDateTime.now().toString("yyyyMMddHHmmSS"));
	}
}
