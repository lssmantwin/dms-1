package com.dms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.joda.time.LocalDateTime;

import com.dms.enums.CommissionStateEnum;
import com.dms.serializable.CommissionStateJacksonSerializable;
import com.dms.serializable.CommissionStateJacksonDeSerializable;
import com.dms.serializable.LocalDateTimeJacksonDeSerializable;
import com.dms.serializable.LocalDateTimeJacksonSerializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectCommissionDto implements Serializable {
	private Long id;
	private String designer;
	private String designerAssistant;
	private String acNumber;
	private String contractId;
	private String customerName;
	private BigDecimal contractTotal;
	private BigDecimal purchaseAgentFee;
	private BigDecimal projectChangeTotal;
	private BigDecimal customerPay;
	private BigDecimal payContractRatio;
	private BigDecimal payProjectRatio;
	private String contractState;
	@JsonSerialize(using = CommissionStateJacksonSerializable.class)
	@JsonDeserialize(using = CommissionStateJacksonDeSerializable.class)
	private CommissionStateEnum commissionState;
	private BigDecimal firstCommissionRate;
	private BigDecimal balanceCommissionRate;
	private BigDecimal firstCommission;
	private BigDecimal balanceCommission;
	private BigDecimal designCommissionRate;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime contractDate;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime firstCommissionDate;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime actualStartTime;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime actualEndTime;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime balanceTime;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime createdTime;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime updatedTime;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime balanceCommissionDate;
	private BigDecimal designerAssistantCommission;

	private BigDecimal designerAssistantCommissionRate;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime designerAssistantCommissionDate;
	private BigDecimal purchasingCost;
	private BigDecimal commissionBase;

	public BigDecimal getPurchasingCost() {
		return purchasingCost;
	}

	public void setPurchasingCost(BigDecimal purchasingCost) {
		this.purchasingCost = purchasingCost;
	}

	public BigDecimal getCommissionBase() {
		return commissionBase;
	}

	public void setCommissionBase(BigDecimal commissionBase) {
		this.commissionBase = commissionBase;
	}

	public Long getId() {
		return id;
	}

	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
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

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getContractTotal() {
		return contractTotal;
	}

	public void setContractTotal(BigDecimal contractTotal) {
		this.contractTotal = contractTotal;
	}

	public BigDecimal getPurchaseAgentFee() {
		return purchaseAgentFee;
	}

	public void setPurchaseAgentFee(BigDecimal purchaseAgentFee) {
		this.purchaseAgentFee = purchaseAgentFee;
	}

	public BigDecimal getProjectChangeTotal() {
		return projectChangeTotal;
	}

	public void setProjectChangeTotal(BigDecimal projectChangeTotal) {
		this.projectChangeTotal = projectChangeTotal;
	}

	public BigDecimal getCustomerPay() {
		return customerPay;
	}

	public void setCustomerPay(BigDecimal customerPay) {
		this.customerPay = customerPay;
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

	public String getContractState() {
		return contractState;
	}

	public void setContractState(String contractState) {
		this.contractState = contractState;
	}

	public void setCommissionState(CommissionStateEnum commissionState) {
		this.commissionState = commissionState;
	}

	public BigDecimal getDesignCommissionRate() {
		return designCommissionRate;
	}

	public void setDesignCommissionRate(BigDecimal designCommissionRate) {
		this.designCommissionRate = designCommissionRate;
	}

	public LocalDateTime getContractDate() {
		return contractDate;
	}

	public void setContractDate(LocalDateTime contractDate) {
		this.contractDate = contractDate;
	}

	public LocalDateTime getFirstCommissionDate() {
		return firstCommissionDate;
	}

	public void setFirstCommissionDate(LocalDateTime firstCommissionDate) {
		this.firstCommissionDate = firstCommissionDate;
	}

	public LocalDateTime getActualStartTime() {
		return actualStartTime;
	}

	public void setActualStartTime(LocalDateTime actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	public LocalDateTime getActualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(LocalDateTime actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	public LocalDateTime getBalanceTime() {
		return balanceTime;
	}

	public void setBalanceTime(LocalDateTime balanceTime) {
		this.balanceTime = balanceTime;
	}

	public LocalDateTime getBalanceCommissionDate() {
		return balanceCommissionDate;
	}

	public void setBalanceCommissionDate(LocalDateTime balanceCommissionDate) {
		this.balanceCommissionDate = balanceCommissionDate;
	}

	public BigDecimal getDesignerAssistantCommissionRate() {
		return designerAssistantCommissionRate;
	}

	public void setDesignerAssistantCommissionRate(BigDecimal designerAssistantCommissionRate) {
		this.designerAssistantCommissionRate = designerAssistantCommissionRate;
	}

	public CommissionStateEnum getCommissionState() {
		return commissionState;
	}

	public BigDecimal getFirstCommission() {
		return firstCommission;
	}

	public void setFirstCommission(BigDecimal firstCommission) {
		this.firstCommission = firstCommission;
	}

	public BigDecimal getBalanceCommission() {
		return balanceCommission;
	}

	public void setBalanceCommission(BigDecimal balanceCommission) {
		this.balanceCommission = balanceCommission;
	}

	public BigDecimal getFirstCommissionRate() {
		return firstCommissionRate;
	}

	public void setFirstCommissionRate(BigDecimal firstCommissionRate) {
		this.firstCommissionRate = firstCommissionRate;
	}

	public BigDecimal getBalanceCommissionRate() {
		return balanceCommissionRate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBalanceCommissionRate(BigDecimal balanceCommissionRate) {
		this.balanceCommissionRate = balanceCommissionRate;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}

	public BigDecimal getDesignerAssistantCommission() {
		return designerAssistantCommission;
	}

	public void setDesignerAssistantCommission(BigDecimal designerAssistantCommission) {
		this.designerAssistantCommission = designerAssistantCommission;
	}

	public LocalDateTime getDesignerAssistantCommissionDate() {
		return designerAssistantCommissionDate;
	}

	public void setDesignerAssistantCommissionDate(LocalDateTime designerAssistantCommissionDate) {
		this.designerAssistantCommissionDate = designerAssistantCommissionDate;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("designer: ").append(designer).append(", acNumber: ").append(acNumber).append(", contractId: ").append(contractId)
				.append(", customerName: ").append(customerName).toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ProjectCommissionDto that = (ProjectCommissionDto) o;

		return getAcNumber() != null ? getAcNumber().equals(that.getAcNumber()) : that.getAcNumber() == null;
	}

	@Override
	public int hashCode() {
		return getAcNumber() != null ? getAcNumber().hashCode() : 0;
	}
}
