package com.dms.service.impl;

import com.dms.dao.ProjectCommissionDao;
import com.dms.dto.ProjectCommissionDto;
import com.dms.enums.CommissionStateEnum;
import com.dms.request.ProjectCommissionFilterRequest;
import com.dms.service.ProjectCommissionService;
import com.dms.utils.DmsConstants;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ProjectCommissionServiceImpl implements ProjectCommissionService {

    @Autowired
    private ProjectCommissionDao projectCommissionDao;

    public ProjectCommissionServiceImpl(ProjectCommissionDao projectCommissionDao) {
        this.projectCommissionDao = projectCommissionDao;
    }

    @Override
    public ProjectCommissionDto getProjectCommission(String acNumber) {
        return projectCommissionDao.getProjectCommission(acNumber);
    }

    @Override
    public int getProjects(Date startDate, Date endDate) {
        return 0;
    }

    @Override
    public void calculateCommission(List<ProjectCommissionDto> projectCommissionDtos) {
        for (ProjectCommissionDto commission : projectCommissionDtos) {

            if (commission.getPurchasingCost() == null) {
                commission.setPurchasingCost(BigDecimal.ZERO);
            }
            commission.setCommissionBase(commission.getContractTotal().subtract(commission.getPurchasingCost()));
            // 设计师助理提成
            if (commission.getDesignerAssistant() != null && !commission.getDesignerAssistant().equals("")
                    && commission.getDesignerAssistantCommissionRate() != null && commission.getDesignerAssistantCommission() == null) {
                BigDecimal designCommissionRate = commission.getDesignCommissionRate().subtract(DmsConstants.DESIGN_COMMISSION_SUB_RATE);
                commission.setDesignCommissionRate(designCommissionRate.setScale(2, BigDecimal.ROUND_HALF_UP));
                commission.setDesignerAssistantCommission(commission.getCommissionBase().multiply(commission.getDesignerAssistantCommissionRate()).setScale(2, BigDecimal.ROUND_HALF_UP));
                commission.setDesignerAssistantCommissionDate(LocalDateTime.now());
            }
			CommissionStateEnum commissionState = CommissionStateEnum.COMMISSION_STATE_START;
            if (CommissionStateEnum.COMMISSION_STATE_START  == commission.getCommissionState()) {
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

                projectCommissionDao.updateProjectCommission(commission);
            } else if (CommissionStateEnum.COMMISSION_STATE_FIRST == commission.getCommissionState()
                    && commission.getProjectChangeTotal() != null) {
                BigDecimal balanceCommission = BigDecimal.ZERO;
                commissionState = CommissionStateEnum.COMMISSION_STATE_FINISH;
                balanceCommission = commission.getCommissionBase().add(commission.getProjectChangeTotal());
                balanceCommission = balanceCommission .multiply(commission.getDesignCommissionRate()).subtract(commission.getFirstCommission());
                commission.setBalanceCommission(balanceCommission.setScale(2, BigDecimal.ROUND_HALF_UP));
                commission.setBalanceCommissionDate(LocalDateTime.now());
                commission.setCommissionState(commissionState);
                commission.setUpdatedTime(LocalDateTime.now());
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
}
