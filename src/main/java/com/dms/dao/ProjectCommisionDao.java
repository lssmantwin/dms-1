package com.dms.dao;

import com.dms.domain.MiniRequest;
import com.dms.domain.ProjectCommision;
import com.dms.dto.EmployeeDto;
import com.dms.dto.ProjectCommisionDto;

import java.util.List;

public interface ProjectCommisionDao {

	List<ProjectCommisionDto三> getProjectCommisions();

	int getProjectCommisionCount(MiniRequest request);

	void saveProjectCommision(ProjectCommisionDto projectCommision);

	void updateProjectCommision(ProjectCommisionDto projectCommision);

}
