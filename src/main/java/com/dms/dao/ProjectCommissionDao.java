package com.dms.dao;

import java.util.Date;
import java.util.List;

import com.dms.dto.DesignAssistantDto;
import com.dms.dto.ProjectCommissionDto;
import com.dms.request.DataGridRequest;
import com.dms.request.ProjectCommissionFilterRequest;

public interface ProjectCommissionDao {

	List<ProjectCommissionDto> getProjectCommissions(DataGridRequest request);

	int getProjectCommissionCount(DataGridRequest request);

	ProjectCommissionDto getProjectCommission(String acNumber);

	List<ProjectCommissionDto> getProjects(Date startDate, Date endDate);

	ProjectCommissionDto getProject(String acNumber);

	void saveProjectCommission(List<ProjectCommissionDto> projectCommissions);

	int saveProjectCommission(ProjectCommissionDto projectCommission);

	List<ProjectCommissionDto> getProjectCommissions(ProjectCommissionFilterRequest request);

	int getProjectCommissionCount(ProjectCommissionFilterRequest request);

	void updateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	void updateProjectCommission(ProjectCommissionDto projectCommission);

	void updateProject(ProjectCommissionDto projectCommission);

	void updateDesignAssistant(DesignAssistantDto designAssistantDto);
}
