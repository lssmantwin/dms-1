package com.dms.request;

import java.io.Serializable;

public class ProjectCommissionFilterRequest extends BaseFilterRequest implements Serializable {

	private String designer;
	private String contractState;
	private String commissionState;
	private String contractDate;
	private String firstCommissionDate;
	private String actualStartTime;
	private String actualEndTime;
	private String balanceTime;
	private String balanceCommissionDate;

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

	public String getCommissionState() {
		return commissionState;
	}

	public void setCommissionState(String commissionState) {
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
}
