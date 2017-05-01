package com.dms.service.impl;

import com.dms.dao.ProjectCommissionDao;
import com.dms.dto.ProjectCommissionDto;
import com.dms.request.DataGridRequest;
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
    public List<ProjectCommissionDto> getProjectCommissions(DataGridRequest request) {
        return projectCommissionDao.getProjectCommissions(request);
    }

    @Override
    public int getProjectCommissionCount(DataGridRequest request) {
        return 10;
        // return projectCommissionDao.getProjectCommissionCount(request);
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

            commission.setCommissionBase(commission.getContractTotal().subtract(commission.getPurchasingCost()));
            String commissionState = null;
            if (DmsConstants.COMMISION_STATE_START.equals(commission.getCommissionState())) {
                BigDecimal firstCommission = BigDecimal.ZERO;
                if (commission.getContractTotal() != null
                        && commission.getContractTotal().compareTo(DmsConstants.MIN_CONTRACT_COMMISSION) <= 0) {
                    firstCommission = DmsConstants.MIN_COMMISSION;
                    commissionState = DmsConstants.COMMISION_STATE_FINISH;
                } else {
                    commissionState = DmsConstants.COMMISION_STATE_FIRST;
                    firstCommission = commission.getCommissionBase().multiply(commission.getDesignCommissionRate()).multiply(commission.getFirstCommissionRate());
                }
                commission.setFirstCommission(firstCommission);
                commission.setFirstCommissionDate(LocalDateTime.now());
            } else if (DmsConstants.COMMISION_STATE_FIRST.equals(commission.getCommissionState())) {
                BigDecimal balanceCommission = BigDecimal.ZERO;
                commissionState = DmsConstants.COMMISION_STATE_FINISH;
                balanceCommission = commission.getCommissionBase().multiply(commission.getDesignCommissionRate()).subtract(commission.getFirstCommission());
                commission.setFirstCommission(balanceCommission);
                commission.setBalanceCommissionDate(LocalDateTime.now());

            }
            commission.setCommissionState(commissionState);
            commission.setUpdatedTime(LocalDateTime.now());
        }
        projectCommissionDao.saveProjectCommission(projectCommissionDtos);

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
        projectCommissionDao.updateProjectCommission(projectCommission);
    }
}
