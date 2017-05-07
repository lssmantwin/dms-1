package com.dms.service;

import com.dms.dto.ProjectCommissionDto;
import com.dms.request.DataGridRequest;

import java.util.Date;
import java.util.List;

import java.util.List;

import com.dms.dto.ProjectCommissionDto;
import com.dms.request.ProjectCommissionFilterRequest;

public interface ProjectCommissionService {

	List<ProjectCommissionDto> getProjectCommissions(ProjectCommissionFilterRequest request);

	int getProjectCommissionCount(ProjectCommissionFilterRequest request);

	ProjectCommissionDto getProjectCommission(String acNumber);

	int getProjects(Date startDate, Date endDate);

	void calculateCommission(List<ProjectCommissionDto> projectCommissionDtos);

	void saveProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos) throws IllegalAccessException;

	int saveProjectCommission(ProjectCommissionDto projectCommissionDto) throws IllegalAccessException;

	void updateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	void updateProjectCommission(ProjectCommissionDto projectCommissionDto);


}

