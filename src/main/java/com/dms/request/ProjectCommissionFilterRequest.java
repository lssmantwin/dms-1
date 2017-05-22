package com.dms.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.dms.enums.CommissionStateEnum;

public class ProjectCommissionFilterRequest extends BaseFilterRequest implements Serializable {

	private String designer;
	private String designerAssistant;
	private String contractState;
	private String acNumber;
	private String contractId;
	private CommissionStateEnum commissionState;
	private String contractDate;
	private String firstCommissionDate;
	private String actualStartTime;
	private String actualEndTime;
	private String balanceTime;
	private String balanceCommissionDate;
	private BigDecimal payContractRatio;
	private BigDecimal payProjectRatio;

	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	public String getContractState() {
		return contractState;
	}

	public void setContractState(String contractState) {
		this.contractState = contractState;
	}

	public CommissionStateEnum getCommissionState() {
		return commissionState;
	}

	public void setCommissionState(CommissionStateEnum commissionState) {
		this.commissionState = commissionState;
	}

	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

	public String getFirstCommissionDate() {
		return firstCommissionDate;
	}

	public void setFirstCommissionDate(String firstCommissionDate) {
		this.firstCommissionDate = firstCommissionDate;
	}

	public String getActualStartTime() {
		return actualStartTime;
	}

	public void setActualStartTime(String actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	public String getActualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(String actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	public String getBalanceTime() {
		return balanceTime;
	}

	public void setBalanceTime(String balanceTime) {
		this.balanceTime = balanceTime;
	}

	public String getBalanceCommissionDate() {
		return balanceCommissionDate;
	}

	public void setBalanceCommissionDate(String balanceCommissionDate) {
		this.balanceCommissionDate = balanceCommissionDate;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getPayContractRatio() {
		return payContractRatio;
	}

	public void setPayContractRatio(BigDecimal payContractRatio) {
		this.payContractRatio = payContractRatio;
	}

	public BigDecimal getPayProjectRatio() {
		return payProjectRatio;
	}

	public void setPayProjectRatio(BigDecimal payProjectRatio) {
		this.payProjectRatio = payProjectRatio;
	}


	public String getDesignerAssistant() {
		return designerAssistant;
	}

	public void setDesignerAssistant(String designerAssistant) {
		this.designerAssistant = designerAssistant;
	}

	public String getAcNumber() {
		return acNumber;
	}

	public void setAcNumber(String acNumber) {
		this.acNumber = acNumber;
	}
}
