package com.dms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.joda.time.LocalDateTime;

import com.dms.serializable.LocalDateTimeJacksonDeSerializable;
import com.dms.serializable.LocalDateTimeJacksonSerializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalaryBill implements Serializable {

	private String month;
	private int companyId;
	private String employeeName;
	private String position;
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime hiredate;
	private BigDecimal baseWage;
	private BigDecimal otherSubsidyCard;
	private BigDecimal mealsSubsidy;
	private BigDecimal secrecySubsidy;
	private BigDecimal workingAgeSubsidy;
	private BigDecimal communicationFee;
	private BigDecimal charge;
	private BigDecimal exhibitionCharge;
	private BigDecimal casualLeave;
	private BigDecimal sickLeave;
	private BigDecimal storage;
	private BigDecimal grossPay;
	private BigDecimal medicalInsurance;
	private BigDecimal housingFund;
	private BigDecimal beforeTaxSalary;
	private BigDecimal personalIncomeTax;
	private BigDecimal afterTaxSalary;
	private String bankCardNumber;
	private String identityCardNumber;
	private BigDecimal performanceAppraisalCard;
	private BigDecimal bonusCard;

	private BigDecimal postAllowance;
	private BigDecimal performanceAppraisalCash;
	private BigDecimal bonusCash;
	private BigDecimal otherSubsidyCash;
	private BigDecimal otherCharge;
	private BigDecimal salaryCash;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public LocalDateTime getHiredate() {
		return hiredate;
	}

	public void setHiredate(LocalDateTime hiredate) {
		this.hiredate = hiredate;
	}

	public BigDecimal getBaseWage() {
		return baseWage;
	}

	public void setBaseWage(BigDecimal baseWage) {
		this.baseWage = baseWage;
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

	public BigDecimal getWorkingAgeSubsidy() {
		return workingAgeSubsidy;
	}

	public void setWorkingAgeSubsidy(BigDecimal workingAgeSubsidy) {
		this.workingAgeSubsidy = workingAgeSubsidy;
	}

	public BigDecimal getCommunicationFee() {
		return communicationFee;
	}

	public void setCommunicationFee(BigDecimal communicationFee) {
		this.communicationFee = communicationFee;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getExhibitionCharge() {
		return exhibitionCharge;
	}

	public void setExhibitionCharge(BigDecimal exhibitionCharge) {
		this.exhibitionCharge = exhibitionCharge;
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

	public BigDecimal getStorage() {
		return storage;
	}

	public void setStorage(BigDecimal storage) {
		this.storage = storage;
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

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	public BigDecimal getPostAllowance() {
		return postAllowance;
	}

	public void setPostAllowance(BigDecimal postAllowance) {
		this.postAllowance = postAllowance;
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

	public BigDecimal getSalaryCash() {
		return salaryCash;
	}

	public void setSalaryCash(BigDecimal salaryCash) {
		this.salaryCash = salaryCash;
	}

	public BigDecimal getOtherCharge() {
		return otherCharge;
	}

	public void setOtherCharge(BigDecimal otherCharge) {
		this.otherCharge = otherCharge;
	}
}
