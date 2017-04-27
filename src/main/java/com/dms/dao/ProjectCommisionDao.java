package com.dms.dao;

import java.util.Date;
import java.util.List;

import com.dms.request.DataGridRequest;
import com.dms.dto.ProjectCommisionDto;

public interface ProjectCommisionDao {

	List<ProjectCommisionDto> getProjectCommisions(DataGridRequest request);

	int getProjectCommisionCount(DataGridRequest request);

	ProjectCommisionDto getProjectCommision(String acNumber);

	int getProjects(Date startDate, Date endDate);

	void saveProjectCommision(List<ProjectCommisionDto> projectCommisions);

	int saveProjectCommision(ProjectCommisionDto projectCommision);

	void updateProjectCommision(ProjectCommisionDto projectCommision);

}
