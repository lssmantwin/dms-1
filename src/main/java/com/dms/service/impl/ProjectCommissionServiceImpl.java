package com.dms.service.impl;

import com.dms.dao.EmployeeDao;
import com.dms.dao.FinanceDao;
import com.dms.dao.ProjectCommissionDao;
import com.dms.dto.DesignAssistantDto;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;
import com.dms.dto.ProjectCommissionDto;
import com.dms.enums.CommissionStateEnum;
import com.dms.request.ProjectCommissionFilterRequest;
import com.dms.service.ProjectCommissionService;
import com.dms.utils.DateUtils;
import com.dms.utils.DmsConstants;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ProjectCommissionServiceImpl implements ProjectCommissionService {

    @Autowired
    private ProjectCommissionDao projectCommissionDao;

    @Autowired
    private FinanceDao financeDao;

    @Autowired
    private EmployeeDao employeeDao;

    public ProjectCommissionServiceImpl(ProjectCommissionDao projectCommissionDao) {
        this.projectCommissionDao = projectCommissionDao;
    }

    @Override
    public ProjectCommissionDto getProject(String acNumber) {
        return projectCommissionDao.getProject(acNumber);
    }

    @Override
    public List<ProjectCommissionDto> getProjects(Date startDate, Date endDate) {
        return projectCommissionDao.getProjects(startDate, endDate);
    }

    @Override
    public void calculateFirstCommission(List<ProjectCommissionDto> projectCommissionDtos) {
        for (ProjectCommissionDto commission : projectCommissionDtos) {

            if (commission.getPurchasingCost() == null) {
                commission.setPurchasingCost(BigDecimal.ZERO);
            }
            commission.setCommissionBase(commission.getContractTotal().subtract(commission.getPurchasingCost()));
            // 设计师助理提成
            if (commission.getDesignerAssistant() != null && !commission.getDesignerAssistant().equals("")
                    && commission.getDesignerAssistantCommissionRate() != null && commission.getDesignerAssistantCommission() == null) {
                BigDecimal designCommissionRate = commission.getDesignCommissionRate().subtract(DmsConstants.DESIGN_COMMISSION_SUB_RATE);
                commission.setDesignCommissionRate(designCommissionRate.setScale(4, BigDecimal.ROUND_HALF_UP));
                commission.setDesignerAssistantCommission(commission.getCommissionBase().multiply(commission.getDesignerAssistantCommissionRate()).setScale(2, BigDecimal.ROUND_HALF_UP));
                commission.setDesignerAssistantCommissionDate(LocalDateTime.now());
            }
            CommissionStateEnum commissionState = CommissionStateEnum.COMMISSION_STATE_START;
            if (CommissionStateEnum.COMMISSION_STATE_START == commission.getCommissionState()) {
                BigDecimal firstCommission = BigDecimal.ZERO;
                //小于1万 提成250
                if (commission.getContractTotal() != null
                        && commission.getContractTotal().compareTo(DmsConstants.MIN_CONTRACT_COMMISSION) <= 0) {
                    firstCommission = DmsConstants.MIN_COMMISSION;
                    commissionState = CommissionStateEnum.COMMISSION_STATE_FINISH;
                } else {
                    if (commission.getDesignCommissionRate() != null && commission.getFirstCommissionRate() != null) {
                        commissionState = CommissionStateEnum.COMMISSION_STATE_FIRST;
                        firstCommission = commission.getCommissionBase().multiply(commission.getDesignCommissionRate()).
                                multiply(commission.getFirstCommissionRate());
                    }

                }
                if (BigDecimal.ZERO != firstCommission) {
                    commission.setFirstCommission(firstCommission.setScale(2, BigDecimal.ROUND_HALF_UP));
                    commission.setFirstCommissionDate(LocalDateTime.now());
                    commission.setCommissionState(commissionState);
                    commission.setUpdatedTime(LocalDateTime.now());
                }
                updateCommission(commission.getEmployeeId(), commission.getFirstCommission(), commission.getFirstCommissionDate());
                //设计助理提成
                if (!StringUtils.isEmpty(commission.getDesignerAssistant())) {
                    EmployeeDto employee = employeeDao.getEmployee(commission.getDesignerAssistant().trim());
                    if (!StringUtils.isEmpty(employee.getId())) {
                        updateCommission(Long.valueOf(employee.getId()), commission.getDesignerAssistantCommission(), commission.getBalanceCommissionDate());
                    }
                }
                projectCommissionDao.updateProjectCommission(commission);
            }
        }

    }

    private void updateCommission(Long employeeId, BigDecimal commission, LocalDateTime commissionDate) {
        FinanceDto financeDto = new FinanceDto();
        financeDto.setEmployeeId(Long.valueOf(employeeId));
        financeDto.setBonusCard(commission);
        String monthOfYear = String.valueOf(commissionDate.getMonthOfYear());
        if (!StringUtils.isEmpty(monthOfYear) && monthOfYear.length() == 1) {
            monthOfYear = "0".concat(monthOfYear);
        }
        String month = String.valueOf(commissionDate.getYear()).concat(monthOfYear);
        financeDto.setMonth(month);
        financeDao.updateFinanceCommission(financeDto);
    }

    @Override
    public void calculateBalanceCommission(List<ProjectCommissionDto> projectCommissionDtos) {
        for (ProjectCommissionDto commission : projectCommissionDtos) {
            CommissionStateEnum commissionState;
            if (CommissionStateEnum.COMMISSION_STATE_FIRST == commission.getCommissionState()) {
                BigDecimal balanceCommission = BigDecimal.ZERO;
                commissionState = CommissionStateEnum.COMMISSION_STATE_FINISH;
                if (commission.getProjectChangeTotal() == null ) {
                    commission.setProjectChangeTotal(BigDecimal.ZERO);
                }
                balanceCommission = commission.getCommissionBase().add(commission.getProjectChangeTotal());
                balanceCommission = balanceCommission.multiply(commission.getDesignCommissionRate()).subtract(commission.getFirstCommission());
                commission.setBalanceCommission(balanceCommission.setScale(2, BigDecimal.ROUND_HALF_UP));
                commission.setBalanceCommissionDate(LocalDateTime.now());
                commission.setCommissionState(commissionState);
                commission.setUpdatedTime(LocalDateTime.now());
                //设计师提成
                updateCommission(commission.getEmployeeId(), commission.getBalanceCommission(), commission.getBalanceCommissionDate());
                //设计助理提成
                if (!StringUtils.isEmpty(commission.getDesignerAssistant())
                        && (commission.getDesignerAssistantCommission() != null && BigDecimal.ZERO != commission.getDesignerAssistantCommission())) {
                    EmployeeDto employee = employeeDao.getEmployee(commission.getDesignerAssistant().trim());
                    if (!StringUtils.isEmpty(employee.getId())) {
                        updateCommission(Long.valueOf(employee.getId()), commission.getDesignerAssistantCommission(), commission.getBalanceCommissionDate());
                    }
                }
                projectCommissionDao.updateProjectCommission(commission);
            }
        }

    }

    @Override
    public void saveProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos) throws IllegalAccessException {
        projectCommissionDao.saveProjectCommission(projectCommissionDtos);
    }

    @Override
    public int saveProjectCommission(ProjectCommissionDto projectCommission) throws IllegalAccessException {
        throw new IllegalAccessException("unsupport operation");
        //projectCommissionDao.saveProjectCommission(projectCommission);
    }

    @Override
    public void updateProjectCommission(ProjectCommissionDto projectCommission) {
        if (projectCommission.getCommissionBase() == null && projectCommission.getPurchasingCost() != null) {
            projectCommission.getContractTotal().subtract(projectCommission.getPurchasingCost());
        }
        projectCommissionDao.updateProjectCommission(projectCommission);
    }

    @Override
    public void sychronzieProejcts() {
        List<ProjectCommissionDto> projects = getProjects(DateUtils.getCurrentDayStart(), DateUtils.getCurrentDayEnd());
        for (ProjectCommissionDto project : projects) {
            ProjectCommissionDto projectCommissionDto = projectCommissionDao.getProject(project.getAcNumber());
            if (projectCommissionDto == null) {
                projectCommissionDto.setCreatedTime(LocalDateTime.now());
                if (project.getDesigner() != null) {
                    //取得设计师的提成系数
                    EmployeeDto employee = employeeDao.getEmployee(project.getDesigner());
                    //翻新
                    if (project.getContractId().startsWith(DmsConstants.FX_PROJECT)) {
                        project.setDesignCommissionRate(employee.getRenovateCommossionRatio());
                        project.setFirstCommissionRate(employee.getCommencementRatio());
                        //佳园屋
                    } else if (project.getContractId().startsWith(DmsConstants.JYW_PROJECT)) {
                        project.setDesignCommissionRate(employee.getJywCommissionRatio());
                        project.setFirstCommissionRate(employee.getCommencementRatio());
                    }
                    project.setDesignCommissionRate(employee.getJywCommissionRatio());
                    project.setEmployeeId(Long.valueOf(employee.getId()));
                    //郊区设计师助理
                    if (!StringUtils.isEmpty(project.getBranch())
                            && (project.getBranch().indexOf(DmsConstants.SUBURBS_QINGPU) > 0
                            || (project.getBranch().indexOf(DmsConstants.SUBURBS_SONGJIANG) > 0))) {
                        projectCommissionDto.setDesignerAssistantCommissionRate(DmsConstants.DESIGN_ASSISTANT_SUBURBS_COMMISSION_RATE);
                    } else {
                        projectCommissionDto.setDesignerAssistantCommissionRate(DmsConstants.DESIGN_ASSISTANT_COMMISSION_RATE);
                    }
                }
                projectCommissionDao.saveProjectCommission(projectCommissionDto);
            } else {
                projectCommissionDto.setUpdatedTime(LocalDateTime.now());
                projectCommissionDao.updateProjectCommission(projectCommissionDto);
            }
        }
    }

    @Override
    public List<ProjectCommissionDto> getProjectCommissions(ProjectCommissionFilterRequest request) {
        return projectCommissionDao.getProjectCommissions(request);
    }

    @Override
    public int getProjectCommissionCount(ProjectCommissionFilterRequest request) {
        return projectCommissionDao.getProjectCommissionCount(request);
    }

    @Override
    public void updateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos) {
        for (ProjectCommissionDto projectCommissionDto : projectCommissionDtos) {
            projectCommissionDao.updateProjectCommission(projectCommissionDto);
        }
    }

    @Override
    public void updateDesignAssistants(List<DesignAssistantDto> designAssistantDtos) {
        for (DesignAssistantDto designAssistantDto : designAssistantDtos) {
            EmployeeDto employee = employeeDao.getEmployee(designAssistantDto.getDesignAssistant());
            if (!StringUtils.isEmpty(employee.getId())) {
                designAssistantDto.setDesignAssistantId(Long.valueOf(employee.getId()));
            }
            projectCommissionDao.updateDesignAssistant(designAssistantDto);
        }
    }
}
