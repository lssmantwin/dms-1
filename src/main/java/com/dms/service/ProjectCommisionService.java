package com.dms.service;

import com.dms.request.DataGridRequest;
import com.dms.dto.ProjectCommisionDto;

import java.util.List;

public interface ProjectCommisionService {

	List<ProjectCommisionDto> getProjectCommisions(DataGridRequest request);

	int getProjectCommisionCount(DataGridRequest request);

	void saveProjectCommision(ProjectCommisionDto projectCommisionDto) throws IllegalAccessException;

	void updateProjectCommision(ProjectCommisionDto projectCommisionDto);
}
