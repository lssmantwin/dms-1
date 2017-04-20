package com.dms.dao;

import java.util.List;

import com.dms.domain.MiniRequest;
import com.dms.dto.ProjectCommisionDto;

public interface ProjectCommisionDao {

	List<ProjectCommisionDto> getProjectCommisions(MiniRequest request);

	int getProjectCommisionCount(MiniRequest request);

	void saveProjectCommision(ProjectCommisionDto projectCommision);

	void updateProjectCommision(ProjectCommisionDto projectCommision);

}
