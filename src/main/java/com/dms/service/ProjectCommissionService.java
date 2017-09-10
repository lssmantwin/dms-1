package com.dms.service;

import com.dms.dto.DesignAssistantDto;
import com.dms.dto.ProjectCommissionDto;

import java.util.Date;
import java.util.List;

import com.dms.request.ProjectCommissionFilterRequest;

public interface ProjectCommissionService {

	List<ProjectCommissionDto> getProjectCommissions(ProjectCommissionFilterRequest request);

	List<ProjectCommissionDto>  getCurrentMonthProjectCommissions(String currentMonth);

	int getProjectCommissionCount(ProjectCommissionFilterRequest request);


	ProjectCommissionDto getProject(String acNumber);

	List<ProjectCommissionDto> getProjects(Date startDate, Date endDate);

	void calculateFirstCommission(List<ProjectCommissionDto> projectCommissionDtos);

	void calculateBalanceCommission(List<ProjectCommissionDto> projectCommissionDtos);

	void revertFirstCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	void revertBalanceCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	void saveProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos) throws IllegalAccessException;

	int saveProjectCommission(ProjectCommissionDto projectCommissionDto) throws IllegalAccessException;

	void updateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	void updateProjectCommission(ProjectCommissionDto projectCommission);

	void updateDesignAssistants(List<DesignAssistantDto> designAssistantDtos);

	void sychronzieProejcts();



}

