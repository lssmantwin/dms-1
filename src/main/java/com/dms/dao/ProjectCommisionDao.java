package com.dms.dao;

import java.util.List;

import com.dms.request.DataGridRequest;
import com.dms.dto.ProjectCommisionDto;

public interface ProjectCommisionDao {

	List<ProjectCommisionDto> getProjectCommisions(DataGridRequest request);

	int getProjectCommisionCount(DataGridRequest request);

	void saveProjectCommision(ProjectCommisionDto projectCommision);

	void updateProjectCommision(ProjectCommisionDto projectCommision);

}
