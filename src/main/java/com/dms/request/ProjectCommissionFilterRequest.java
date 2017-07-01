package com.dms.request;

import com.dms.enums.CommissionStateEnum;
import com.dms.utils.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProjectCommissionFilterRequest extends BaseFilterRequest implements Serializable {

    private String designer;
    private String designerAssistant;
    private String contractState;
    private String acNumber;
    private String contractId;
    private CommissionStateEnum commissionState;
    private String contractDate;
    private String firstCommissionDate;
    private String firstCommissionStartDate;
    private String firstCommissionEndDate;
    private String actualStartTime;
    private String actualEndTime;
    private String balanceTime;
    private String balanceCommissionDate;
    private String balanceCommissionStartDate;
    private String balanceCommissionEndDate;

    private String designerAssistantCommissionDate;
    private String designerAssistantCommissionStartDate;
    private String designerAssistantCommissionEndDate;
    private BigDecimal payContractRatio;
    private BigDecimal payProjectRatio;
    private String branch;

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

    public String getFirstCommissionStartDate() {
        if (firstCommissionDate != null) {
            String[] ymDate = firstCommissionDate.split("\\-");
            return DateUtils.getFirstDayOfMontString(Integer.valueOf(ymDate[0]), Integer.valueOf(ymDate[1]));

        }
        return null;
    }

    public void setFirstCommissionStartDate(String firstCommissionStartDate) {
        this.firstCommissionStartDate = firstCommissionStartDate;
    }

    public String getFirstCommissionEndDate() {
        if (firstCommissionDate != null) {
            String[] ymDate = firstCommissionDate.split("\\-");
            return DateUtils.getLastDayOfMonthString(Integer.valueOf(ymDate[0]), Integer.valueOf(ymDate[1]));

        }
        return null;
    }

    public void setFirstCommissionEndDate(String firstCommissionEndDate) {
        this.firstCommissionEndDate = firstCommissionEndDate;
    }

    public String getBalanceCommissionStartDate() {
        if (balanceCommissionDate != null) {
            String[] ymDate = balanceCommissionDate.split("\\-");
            return DateUtils.getFirstDayOfMontString(Integer.valueOf(ymDate[0]), Integer.valueOf(ymDate[1]));

        }
        return null;
    }

    public void setBalanceCommissionStartDate(String balanceCommissionStartDate) {
        this.balanceCommissionStartDate = balanceCommissionStartDate;
    }

    public String getBalanceCommissionEndDate() {
        if (balanceCommissionDate != null) {
            String[] ymDate = balanceCommissionDate.split("\\-");
            return DateUtils.getLastDayOfMonthString(Integer.valueOf(ymDate[0]), Integer.valueOf(ymDate[1]));

        }
        return null;
    }

    public void setBalanceCommissionEndDate(String balanceCommissionEndDate) {
        this.balanceCommissionEndDate = balanceCommissionEndDate;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDesignerAssistantCommissionDate() {
        return designerAssistantCommissionDate;
    }

    public void setDesignerAssistantCommissionDate(String designerAssistantCommissionDate) {
        this.designerAssistantCommissionDate = designerAssistantCommissionDate;
    }

    public String getDesignerAssistantCommissionStartDate() {
        if (designerAssistantCommissionDate != null) {
            String[] ymDate = designerAssistantCommissionDate.split("\\-");
            return DateUtils.getFirstDayOfMontString((Integer.valueOf(ymDate[0])), Integer.valueOf(ymDate[1]));

        }
        return null;
    }

    public void setDesignerAssistantCommissionStartDate(String designerAssistantCommissionStartDate) {
        this.designerAssistantCommissionStartDate = designerAssistantCommissionStartDate;
    }

    public String getDesignerAssistantCommissionEndDate() {
        if (designerAssistantCommissionDate != null) {
            String[] ymDate = designerAssistantCommissionDate.split("\\-");
            return DateUtils.getLastDayOfMonthString(Integer.valueOf(ymDate[0]), Integer.valueOf(ymDate[1]));

        }
        return null;
    }

    public void setDesignerAssistantCommissionEndDate(String designerAssistantCommissionEndDate) {
        this.designerAssistantCommissionEndDate = designerAssistantCommissionEndDate;
    }
}
