package com.dms.service;

import java.util.List;

import com.dms.dto.ProjectCommissionDto;
import com.dms.request.ProjectCommissionFilterRequest;

public interface ProjectCommissionService {

	List<ProjectCommissionDto> getProjectCommissions(ProjectCommissionFilterRequest request);

	int getProjectCommissionCount(ProjectCommissionFilterRequest request);

	void updateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

}
