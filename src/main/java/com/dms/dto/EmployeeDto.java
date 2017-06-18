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
public class EmployeeDto implements Serializable {

	private String id;
	private String name;
	private String position;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime hiredate;
	private BigDecimal baseWage;
	private BigDecimal workingAgeSubsidy;
	private BigDecimal overtime;
	private BigDecimal mealsSubsidy;
	private BigDecimal secrecySubsidy;
	private BigDecimal communicationFee;
	private String bankCardNumber;
	private BigDecimal jywCommissionRatio;
	private BigDecimal commencementRatio;
	private BigDecimal completionRatio;
	private BigDecimal renovateCommissionRatio;
	private String identityCardNumber;
	private BigDecimal transportationAllowance;
	private BigDecimal postAllowance;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime leaveDate;
	private BigDecimal storageCharge;
	private BigDecimal totalCharge;
	private BigDecimal chargePerMonth;
	private BigDecimal charge;
	private int companyId;
	private String department;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	public BigDecimal getWorkingAgeSubsidy() {
		return workingAgeSubsidy;
	}

	public void setWorkingAgeSubsidy(BigDecimal workingAgeSubsidy) {
		this.workingAgeSubsidy = workingAgeSubsidy;
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

	public BigDecimal getJywCommissionRatio() {
		return jywCommissionRatio;
	}

	public void setJywCommissionRatio(BigDecimal jywCommissionRatio) {
		this.jywCommissionRatio = jywCommissionRatio;
	}

	public BigDecimal getCommencementRatio() {
		return commencementRatio;
	}

	public void setCommencementRatio(BigDecimal commencementRatio) {
		this.commencementRatio = commencementRatio;
	}

	public BigDecimal getCompletionRatio() {
		return completionRatio;
	}

	public void setCompletionRatio(BigDecimal completionRatio) {
		this.completionRatio = completionRatio;
	}

	public BigDecimal getRenovateCommossionRatio() {
		return renovateCommissionRatio;
	}

	public void setRenovateCommossionRatio(BigDecimal renovateCommissionRatio) {
		this.renovateCommissionRatio = renovateCommissionRatio;
	}

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	public BigDecimal getTransportationAllowance() {
		return transportationAllowance;
	}

	public void setTransportationAllowance(BigDecimal transportationAllowance) {
		this.transportationAllowance = transportationAllowance;
	}

	public BigDecimal getPostAllowance() {
		return postAllowance;
	}

	public void setPostAllowance(BigDecimal postAllowance) {
		this.postAllowance = postAllowance;
	}

	public LocalDateTime getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(LocalDateTime leaveDate) {
		this.leaveDate = leaveDate;
	}

	public BigDecimal getStorageCharge() {
		return storageCharge;
	}

	public void setStorageCharge(BigDecimal storageCharge) {
		this.storageCharge = storageCharge;
	}

	public BigDecimal getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}

	public BigDecimal getChargePerMonth() {
		return chargePerMonth;
	}

	public void setChargePerMonth(BigDecimal chargePerMonth) {
		this.chargePerMonth = chargePerMonth;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
