package com.dms.dao;

import java.util.List;

import com.dms.request.DataGridRequest;
import com.dms.dto.ProjectCommissionDto;
import com.dms.request.ProjectCommissionFilterRequest;

public interface ProjectCommissionDao {

	List<ProjectCommissionDto> getProjectCommissions(ProjectCommissionFilterRequest request);

	int getProjectCommissionCount(ProjectCommissionFilterRequest request);

	void updateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

}
