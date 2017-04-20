package com.dms.service.impl;

import com.dms.dao.ProjectCommisionDao;
import com.dms.domain.MiniRequest;

import com.dms.dao.ProjectCommisionDao;
import com.dms.dto.ProjectCommisionDto;
import com.dms.service.ProjectCommisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectCommisionServiceImpl implements ProjectCommisionService {

	@Autowired
	private ProjectCommisionDao projectCommisionDao;


	@Override
	public List<ProjectCommisionDto> getProjectCommisions(MiniRequest request) {
		return projectCommisionDao.getProjectCommisions();
	}

	@Override
	public int getProjectCommisionCount(MiniRequest request) {
		return 0;
	}

	@Override
	public void saveProjectCommision(ProjectCommisionDto projectCommision) {

	}

	@Override
	public void updateProjectCommision(ProjectCommisionDto projectCommision) {

	}
}
