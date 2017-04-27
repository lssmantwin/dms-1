package com.dms.service;

import com.dms.request.DataGridRequest;
import com.dms.dto.ProjectCommisionDto;

import java.util.Date;
import java.util.List;

public interface ProjectCommisionService {

	List<ProjectCommisionDto> getProjectCommisions(DataGridRequest request);

	int getProjectCommisionCount(DataGridRequest request);

	ProjectCommisionDto getProjectCommision(String acNumber);

	int getProjects(Date startDate, Date endDate);

	void saveProjectCommision(List<ProjectCommisionDto> projectCommisionDtos) throws IllegalAccessException;

	int saveProjectCommision(ProjectCommisionDto projectCommisionDto) throws IllegalAccessException;

	void updateProjectCommision(ProjectCommisionDto projectCommisionDto);
}
