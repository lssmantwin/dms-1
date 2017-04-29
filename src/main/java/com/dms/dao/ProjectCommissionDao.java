package com.dms.dao;

import com.dms.dto.FinanceDto;
import com.dms.dto.ProjectCommissionDto;
import com.dms.request.DataGridRequest;

import java.util.Date;
import java.util.List;

public interface ProjectCommissionDao {

    List<ProjectCommissionDto> getProjectCommissions(DataGridRequest request);

    int getProjectCommissionCount(DataGridRequest request);

    ProjectCommissionDto getProjectCommission(String acNumber);

    int getProjects(Date startDate, Date endDate);

    void saveProjectCommission(List<ProjectCommissionDto> projectCommissions);

    int saveProjectCommission(ProjectCommissionDto projectCommission);

    void updateProjectCommission(ProjectCommissionDto projectCommission);
}
