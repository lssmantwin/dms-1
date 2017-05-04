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
            int commissionState = CommissionStateEnum.COMMISION_STATE_START.getDbConstant();
            if (CommissionStateEnum.COMMISION_STATE_START.getDbConstant() == commission.getCommissionState()) {
                BigDecimal firstCommission = BigDecimal.ZERO;
                if (commission.getContractTotal() != null
                        && commission.getContractTotal().compareTo(DmsConstants.MIN_CONTRACT_COMMISSION) <= 0) {
                    firstCommission = DmsConstants.MIN_COMMISSION;
                    commissionState = CommissionStateEnum.COMMISION_STATE_FINISH.getDbConstant();
                } else {
                    if (commission.getDesignCommissionRate() != null && commission.getFirstCommissionRate() != null) {
                        commissionState = CommissionStateEnum.COMMISION_STATE_FIRST.getDbConstant();
                        firstCommission = commission.getCommissionBase().multiply(commission.getDesignCommissionRate()).
                                multiply(commission.getFirstCommissionRate());
                    }

                }
                if (BigDecimal.ZERO != firstCommission) {
                    commission.setFirstCommission(firstCommission);
                    commission.setFirstCommissionDate(LocalDateTime.now());
                    commission.setCommissionState(commissionState);
                    commission.setUpdatedTime(LocalDateTime.now());
                }
                projectCommissionDao.updateProjectCommission(commission);
            } else if (CommissionStateEnum.COMMISION_STATE_FIRST.getDbConstant() == commission.getCommissionState()
                    && commission.getProjectChangeTotal() != null) {
                BigDecimal balanceCommission = BigDecimal.ZERO;
                commissionState = CommissionStateEnum.COMMISION_STATE_FINISH.getDbConstant();
                balanceCommission = commission.getCommissionBase().add(commission.getProjectChangeTotal());
                balanceCommission = balanceCommission .multiply(commission.getDesignCommissionRate()).subtract(commission.getFirstCommission());
                commission.setBalanceCommission(balanceCommission);
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
