package com.dms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class FinanceDto implements Serializable {

	private Long id;
	private Long employeeId;
	private String employeeName;
	private String month;
	private BigDecimal baseWage;
	private BigDecimal overtime;
	private BigDecimal mealsSubsidy;
	private BigDecimal secrecySubsidy;
	private BigDecimal communicationFee;
	private String attendance;
	private BigDecimal bonus;
	private BigDecimal workingAgeSubsidy;
	private BigDecimal performanceAppraisal;
	private BigDecimal otherSubsidy;
	private BigDecimal exhibitionCharge;
	private BigDecimal charge;
	private String chargeComments;
	private BigDecimal casualLeave;
	private BigDecimal sickLeave;
	private BigDecimal storageCharge;
	private BigDecimal grossPay;
	private BigDecimal medicalInsurance;
	private BigDecimal housingFund;
	private BigDecimal beforeTaxSalary;
	private BigDecimal personalIncomeTax;
	private BigDecimal afterTaxSalary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getBaseWage() {
		return baseWage == null ? BigDecimal.ZERO : baseWage;
	}

	public void setBaseWage(BigDecimal baseWage) {
		this.baseWage = baseWage;
	}

	public BigDecimal getOvertime() {
		return overtime == null ? BigDecimal.ZERO : overtime;
	}

	public void setOvertime(BigDecimal overtime) {
		this.overtime = overtime;
	}

	public BigDecimal getMealsSubsidy() {
		return mealsSubsidy == null ? BigDecimal.ZERO : mealsSubsidy;
	}

	public void setMealsSubsidy(BigDecimal mealsSubsidy) {
		this.mealsSubsidy = mealsSubsidy;
	}

	public BigDecimal getSecrecySubsidy() {
		return secrecySubsidy == null ? BigDecimal.ZERO : secrecySubsidy;
	}

	public void setSecrecySubsidy(BigDecimal secrecySubsidy) {
		this.secrecySubsidy = secrecySubsidy;
	}

	public BigDecimal getCommunicationFee() {
		return communicationFee == null ? BigDecimal.ZERO : communicationFee;
	}

	public void setCommunicationFee(BigDecimal communicationFee) {
		this.communicationFee = communicationFee;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public BigDecimal getBonus() {
		return bonus == null ? BigDecimal.ZERO : bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public BigDecimal getWorkingAgeSubsidy() {
		return workingAgeSubsidy == null ? BigDecimal.ZERO : workingAgeSubsidy;
	}

	public void setWorkingAgeSubsidy(BigDecimal workingAgeSubsidy) {
		this.workingAgeSubsidy = workingAgeSubsidy;
	}

	public BigDecimal getPerformanceAppraisal() {
		return performanceAppraisal == null ? BigDecimal.ZERO : performanceAppraisal;
	}

	public void setPerformanceAppraisal(BigDecimal performanceAppraisal) {
		this.performanceAppraisal = performanceAppraisal;
	}

	public BigDecimal getOtherSubsidy() {
		return otherSubsidy == null ? BigDecimal.ZERO : otherSubsidy;
	}

	public void setOtherSubsidy(BigDecimal otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	public BigDecimal getExhibitionCharge() {
		return exhibitionCharge == null ? BigDecimal.ZERO : exhibitionCharge;
	}

	public void setExhibitionCharge(BigDecimal exhibitionCharge) {
		this.exhibitionCharge = exhibitionCharge;
	}

	public BigDecimal getCharge() {
		return charge == null ? BigDecimal.ZERO : charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public String getChargeComments() {
		return chargeComments;
	}

	public void setChargeComments(String chargeComments) {
		this.chargeComments = chargeComments;
	}

	public BigDecimal getCasualLeave() {
		return casualLeave == null ? BigDecimal.ZERO : casualLeave;
	}

	public void setCasualLeave(BigDecimal casualLeave) {
		this.casualLeave = casualLeave;
	}

	public BigDecimal getSickLeave() {
		return sickLeave == null ? BigDecimal.ZERO : sickLeave;
	}

	public void setSickLeave(BigDecimal sickLeave) {
		this.sickLeave = sickLeave;
	}

	public BigDecimal getStorageCharge() {
		return storageCharge == null ? BigDecimal.ZERO : storageCharge;
	}

	public void setStorageCharge(BigDecimal storageCharge) {
		this.storageCharge = storageCharge;
	}

	public BigDecimal getGrossPay() {
		return grossPay == null ? BigDecimal.ZERO : grossPay;
	}

	public void setGrossPay(BigDecimal grossPay) {
		this.grossPay = grossPay;
	}

	public BigDecimal getMedicalInsurance() {
		return medicalInsurance == null ? BigDecimal.ZERO : medicalInsurance;
	}

	public void setMedicalInsurance(BigDecimal medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public BigDecimal getHousingFund() {
		return housingFund == null ? BigDecimal.ZERO : housingFund;
	}

	public void setHousingFund(BigDecimal housingFund) {
		this.housingFund = housingFund;
	}

	public BigDecimal getBeforeTaxSalary() {
		return beforeTaxSalary == null ? BigDecimal.ZERO : beforeTaxSalary;
	}

	public void setBeforeTaxSalary(BigDecimal beforeTaxSalary) {
		this.beforeTaxSalary = beforeTaxSalary;
	}

	public BigDecimal getPersonalIncomeTax() {
		return personalIncomeTax == null ? BigDecimal.ZERO : personalIncomeTax;
	}

	public void setPersonalIncomeTax(BigDecimal personalIncomeTax) {
		this.personalIncomeTax = personalIncomeTax;
	}

	public BigDecimal getAfterTaxSalary() {
		return afterTaxSalary == null ? BigDecimal.ZERO : afterTaxSalary;
	}

	public void setAfterTaxSalary(BigDecimal afterTaxSalary) {
		this.afterTaxSalary = afterTaxSalary;
	}
}
