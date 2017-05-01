package com.dms.service;

import com.dms.dto.ProjectCommissionDto;
import com.dms.request.DataGridRequest;

import java.util.Date;
import java.util.List;


public interface ProjectCommissionService {
	List<ProjectCommissionDto> getProjectCommissions(DataGridRequest request);

	int getProjectCommissionCount(DataGridRequest request);

	ProjectCommissionDto getProjectCommission(String acNumber);

	int getProjects(Date startDate, Date endDate);

	void calculateCommission(List<ProjectCommissionDto> projectCommissionDtos);

	void saveProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos) throws IllegalAccessException;

	int saveProjectCommission(ProjectCommissionDto projectCommissionDto) throws IllegalAccessException;

	void updateProjectCommission(ProjectCommissionDto projectCommissionDto);
}

