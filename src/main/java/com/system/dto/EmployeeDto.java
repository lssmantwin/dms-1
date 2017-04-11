package com.system.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmployeeDto implements Serializable {

	private String id;
	private String name;
	private String position;
	private String hiredate;
	private String baseWage;
	private String bankCardNumber;
	private BigDecimal overtime;
	private BigDecimal mealsSubsidy;
	private BigDecimal secrecySubsidy;
	private BigDecimal communicationFee;

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

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getBaseWage() {
		return baseWage;
	}

	public void setBaseWage(String baseWage) {
		this.baseWage = baseWage;
	}

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
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
}
