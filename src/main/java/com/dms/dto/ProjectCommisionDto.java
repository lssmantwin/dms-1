package com.dms.dto;

import com.dms.domain.ProjectCommision;
import org.joda.time.LocalDateTime;

import java.math.BigDecimal;

public class ProjectCommisionDto {

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
    private String firstCommision;
    private String balanceCommision;
    private BigDecimal designCommisionRate;
    private BigDecimal designerAssistantCommisionRate;
    private LocalDateTime contractDate;
    private LocalDateTime firstCommisionDate;
    private LocalDateTime actualStartTime;
    private LocalDateTime actualEndTime;
    private LocalDateTime balanceTime;
    private LocalDateTime balanceCommisionDate;


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

    public String getFirstCommision() {
        return firstCommision;
    }

    public void setFirstCommision(String firstCommision) {
        this.firstCommision = firstCommision;
    }

    public String getBalanceCommision() {
        return balanceCommision;
    }

    public void setBalanceCommision(String balanceCommision) {
        this.balanceCommision = balanceCommision;
    }

    public BigDecimal getDesignCommisionRate() {
        return designCommisionRate;
    }

    public void setDesignCommisionRate(BigDecimal designCommisionRate) {
        this.designCommisionRate = designCommisionRate;
    }

    public BigDecimal getDesginerAssistantCommisionRate() {
        return designerAssistantCommisionRate;
    }

    public void setDesginerAssistantCommisionRate(BigDecimal desginerAssistantCommisionRate) {
        this.designerAssistantCommisionRate = desginerAssistantCommisionRate;
    }

    public LocalDateTime getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDateTime contractDate) {
        this.contractDate = contractDate;
    }

    public LocalDateTime getFirstCommisionDate() {
        return firstCommisionDate;
    }

    public void setFirstCommisionDate(LocalDateTime firstCommisionDate) {
        this.firstCommisionDate = firstCommisionDate;
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

    public LocalDateTime getBalanceCommisionDate() {
        return balanceCommisionDate;
    }

    public void setBalanceCommisionDate(LocalDateTime balanceCommisionDate) {
        this.balanceCommisionDate = balanceCommisionDate;
    }

    @Override
    public String toString() {
        return "ProjectCommision{" +
                "designer='" + designer + '\'' +
                ", acNumber='" + acNumber + '\'' +
                ", contractId='" + contractId + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectCommisionDto that = (ProjectCommisionDto) o;

        return getAcNumber() != null ? getAcNumber().equals(that.getAcNumber()) : that.getAcNumber() == null;
    }

    @Override
    public int hashCode() {
        return getAcNumber() != null ? getAcNumber().hashCode() : 0;
    }

}
