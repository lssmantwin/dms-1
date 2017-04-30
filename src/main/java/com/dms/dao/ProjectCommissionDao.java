package com.dms.dao;

import java.util.List;

import com.dms.request.DataGridRequest;
import com.dms.dto.ProjectCommissionDto;

public interface ProjectCommissionDao {

	List<ProjectCommissionDto> getProjectCommissions(DataGridRequest request);

	int getProjectCommissionCount(DataGridRequest request);

	void updateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

}
