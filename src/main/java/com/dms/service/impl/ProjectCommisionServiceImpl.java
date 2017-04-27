package com.dms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.ProjectCommisionDao;
import com.dms.request.DataGridRequest;
import com.dms.dto.ProjectCommisionDto;
import com.dms.service.ProjectCommisionService;

@Service
public class ProjectCommisionServiceImpl implements ProjectCommisionService {

	@Autowired
	private ProjectCommisionDao projectCommisionDao;

	@Override
	public List<ProjectCommisionDto> getProjectCommisions(DataGridRequest request) {
		return projectCommisionDao.getProjectCommisions(request);
	}

	@Override
	public int getProjectCommisionCount(DataGridRequest request) {
		return 10;
		// return projectCommisionDao.getProjectCommisionCount(request);
	}

	@Override
	public ProjectCommisionDto getProjectCommision(String acNumber) {
		return projectCommisionDao.getProjectCommision(acNumber);
	}

	@Override
	public int getProjects(Date startDate, Date endDate) {
		return 0;
	}

	@Override
	public void saveProjectCommision(List<ProjectCommisionDto> projectCommisionDtos) throws IllegalAccessException {
		
	}

	@Override
	public void saveProjectCommision(ProjectCommisionDto projectCommision) throws IllegalAccessException {
		throw new IllegalAccessException("unsupport operation");
		//projectCommisionDao.saveProjectCommision(projectCommision);
	}

	@Override
	public void updateProjectCommision(ProjectCommisionDto projectCommision) {
		projectCommisionDao.updateProjectCommision(projectCommision);
	}
}
