package com.dms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.ProjectCommisionDao;
import com.dms.domain.MiniRequest;
import com.dms.dto.ProjectCommisionDto;
import com.dms.service.ProjectCommisionService;

@Service
public class ProjectCommisionServiceImpl implements ProjectCommisionService {

	@Autowired
	private ProjectCommisionDao projectCommisionDao;

	@Override
	public List<ProjectCommisionDto> getProjectCommisions(MiniRequest request) {
		return projectCommisionDao.getProjectCommisions(request);
	}

	@Override
	public int getProjectCommisionCount(MiniRequest request) {
		return 10;
		// return projectCommisionDao.getProjectCommisionCount(request);
	}

	@Override
	public void saveProjectCommision(ProjectCommisionDto projectCommision) throws IllegalAccessException {
		throw new IllegalAccessException("unsupport operation");
		// projectCommisionDao.saveProjectCommision(projectCommision);
	}

	@Override
	public void updateProjectCommision(ProjectCommisionDto projectCommision) {
		projectCommisionDao.updateProjectCommision(projectCommision);
	}
}
