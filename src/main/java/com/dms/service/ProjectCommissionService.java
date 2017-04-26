package com.dms.service;

import com.dms.request.DataGridRequest;
import com.dms.dto.ProjectCommissionDto;

import java.util.List;

public interface ProjectCommissionService {

	List<ProjectCommissionDto> getProjectCommissions(DataGridRequest request);

	int getProjectCommissionCount(DataGridRequest request);

	void saveProjectCommission(ProjectCommissionDto projectCommissionDto) throws IllegalAccessException;

	void updateProjectCommission(ProjectCommissionDto projectCommissionDto);
}
