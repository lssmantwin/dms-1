package com.dms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FinanceDto implements Serializable {

	private Long id;
	private Long employeeId;
	private String employeeName;
	private String identityCardNumber;
	private String bankCardNumber;
	private String month;
	private BigDecimal baseWage;
	private BigDecimal overtime;
	private BigDecimal mealsSubsidy;
	private BigDecimal secrecySubsidy;
	private BigDecimal communicationFee;
	private String attendance;
	private BigDecimal bonusCard;
	private BigDecimal bonusCash;
	private BigDecimal workingAgeSubsidy;
	private BigDecimal performanceAppraisalCard;
	private BigDecimal performanceAppraisalCash;
	private BigDecimal otherSubsidyCard;
	private BigDecimal otherSubsidyCash;
	private BigDecimal exhibitionCharge;
	// 扣款
	private BigDecimal charge;
	private String chargeComments;
	private BigDecimal otherCharge;
	private BigDecimal casualLeave;
	private BigDecimal sickLeave;
	private BigDecimal grossPay;
	private BigDecimal medicalInsurance;
	private BigDecimal housingFund;
	private BigDecimal beforeTaxSalary;
	private BigDecimal personalIncomeTax;
	private BigDecimal afterTaxSalary;
	private BigDecimal salaryCash;
	private Boolean alreadyCharge = Boolean.FALSE;
	private BigDecimal postAllowance;
	private BigDecimal contractWages;

	private BigDecimal commission;
	private int  isChanged;
	/* 员工管理 */
	// 总保管费
	private BigDecimal storageCharge;
	// 月保管费
	private BigDecimal chargePerMonth;
	// 总扣款总额
	private BigDecimal totalCharge;
	/* 员工管理 */
	// 当月需扣除的保管费：根据总保管费，月保管费和总扣款总额计算
	private BigDecimal storage;

	// 是否被修改过
	private Boolean ischanged;
	// 是否被锁定
	private Boolean lock = Boolean.FALSE;

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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getBaseWage() {
		return baseWage;
	}

	public void setBaseWage(BigDecimal baseWage) {
		this.baseWage = baseWage;
	}

	public BigDecimal getOvertime() {
		return overtime;
	}

	public void setOvertime(BigDecimal overtime) {
		this.overtime = overtime;
	}

	public BigDecimal getMealsSubsidy() {
		return mealsSubsidy;
	}

	public void setMealsSubsidy(BigDecimal mealsSubsidy) {
		this.mealsSubsidy = mealsSubsidy;
	}

	public BigDecimal getSecrecySubsidy() {
		return secrecySubsidy;
	}

	public void setSecrecySubsidy(BigDecimal secrecySubsidy) {
		this.secrecySubsidy = secrecySubsidy;
	}

	public BigDecimal getCommunicationFee() {
		return communicationFee;
	}

	public void setCommunicationFee(BigDecimal communicationFee) {
		this.communicationFee = communicationFee;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public BigDecimal getBonusCard() {
		return bonusCard;
	}

	public void setBonusCard(BigDecimal bonusCard) {
		this.bonusCard = bonusCard;
	}

	public BigDecimal getBonusCash() {
		return bonusCash;
	}

	public void setBonusCash(BigDecimal bonusCash) {
		this.bonusCash = bonusCash;
	}

	public BigDecimal getWorkingAgeSubsidy() {
		return workingAgeSubsidy;
	}

	public void setWorkingAgeSubsidy(BigDecimal workingAgeSubsidy) {
		this.workingAgeSubsidy = workingAgeSubsidy;
	}

	public BigDecimal getPerformanceAppraisalCard() {
		return performanceAppraisalCard;
	}

	public void setPerformanceAppraisalCard(BigDecimal performanceAppraisalCard) {
		this.performanceAppraisalCard = performanceAppraisalCard;
	}

	public BigDecimal getPerformanceAppraisalCash() {
		return performanceAppraisalCash;
	}

	public void setPerformanceAppraisalCash(BigDecimal performanceAppraisalCash) {
		this.performanceAppraisalCash = performanceAppraisalCash;
	}

	public BigDecimal getOtherSubsidyCard() {
		return otherSubsidyCard;
	}

	public void setOtherSubsidyCard(BigDecimal otherSubsidyCard) {
		this.otherSubsidyCard = otherSubsidyCard;
	}

	public BigDecimal getOtherSubsidyCash() {
		return otherSubsidyCash;
	}

	public void setOtherSubsidyCash(BigDecimal otherSubsidyCash) {
		this.otherSubsidyCash = otherSubsidyCash;
	}

	public BigDecimal getExhibitionCharge() {
		return exhibitionCharge;
	}

	public void setExhibitionCharge(BigDecimal exhibitionCharge) {
		this.exhibitionCharge = exhibitionCharge;
	}

	public BigDecimal getCharge() {
		return charge;
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

	public BigDecimal getOtherCharge() {
		return otherCharge;
	}

	public void setOtherCharge(BigDecimal otherCharge) {
		this.otherCharge = otherCharge;
	}

	public BigDecimal getCasualLeave() {
		return casualLeave;
	}

	public void setCasualLeave(BigDecimal casualLeave) {
		this.casualLeave = casualLeave;
	}

	public BigDecimal getSickLeave() {
		return sickLeave;
	}

	public void setSickLeave(BigDecimal sickLeave) {
		this.sickLeave = sickLeave;
	}

	public BigDecimal getStorageCharge() {
		return storageCharge;
	}

	public void setStorageCharge(BigDecimal storageCharge) {
		this.storageCharge = storageCharge;
	}

	public BigDecimal getGrossPay() {
		return grossPay;
	}

	public void setGrossPay(BigDecimal grossPay) {
		this.grossPay = grossPay;
	}

	public BigDecimal getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(BigDecimal medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public BigDecimal getHousingFund() {
		return housingFund;
	}

	public void setHousingFund(BigDecimal housingFund) {
		this.housingFund = housingFund;
	}

	public BigDecimal getBeforeTaxSalary() {
		return beforeTaxSalary;
	}

	public void setBeforeTaxSalary(BigDecimal beforeTaxSalary) {
		this.beforeTaxSalary = beforeTaxSalary;
	}

	public BigDecimal getPersonalIncomeTax() {
		return personalIncomeTax;
	}

	public void setPersonalIncomeTax(BigDecimal personalIncomeTax) {
		this.personalIncomeTax = personalIncomeTax;
	}

	public BigDecimal getAfterTaxSalary() {
		return afterTaxSalary;
	}

	public void setAfterTaxSalary(BigDecimal afterTaxSalary) {
		this.afterTaxSalary = afterTaxSalary;
	}

	public BigDecimal getSalaryCash() {
		return salaryCash;
	}

	public void setSalaryCash(BigDecimal salaryCash) {
		this.salaryCash = salaryCash;
	}

	public Boolean getAlreadyCharge() {
		return alreadyCharge;
	}

	public void setAlreadyCharge(Boolean alreadyCharge) {
		this.alreadyCharge = alreadyCharge;
	}

	public BigDecimal getPostAllowance() {
		return postAllowance;
	}

	public void setPostAllowance(BigDecimal postAllowance) {
		this.postAllowance = postAllowance;
	}

	public BigDecimal getContractWages() {
		return contractWages;
	}

	public void setContractWages(BigDecimal contractWages) {
		this.contractWages = contractWages;
	}

	public BigDecimal getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}

	public BigDecimal getStorage() {
		return storage;
	}

	public void setStorage(BigDecimal storage) {
		this.storage = storage;
	}

	public BigDecimal getChargePerMonth() {
		return chargePerMonth;
	}

	public void setChargePerMonth(BigDecimal chargePerMonth) {
		this.chargePerMonth = chargePerMonth;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public int getIsChanged() {
		return isChanged;
	}


	public Boolean getIschanged() {
		return ischanged;
	}

	public void setIschanged(Boolean ischanged) {
		this.ischanged = ischanged;
	}

	public Boolean isLock() {
		return lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}
}
