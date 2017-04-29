package com.dms.service.impl;

import java.util.Date;
import java.util.List;

import com.dms.dto.ProjectCommissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.request.DataGridRequest;
import com.dms.service.ProjectCommissionService;

@Service
public class ProjectCommissionServiceImpl implements ProjectCommissionService {

	@Autowired
	private ProjectCommissionDao projectCommissionDao;

	public ProjectCommissionServiceImpl(ProjectCommissionDao projectCommissionDao) {
		this.projectCommissionDao = projectCommissionDao;
	}

	@Override
	public List<ProjectCommissionDto> getProjectCommissions(DataGridRequest request) {
		return projectCommissionDao.getProjectCommissions(request);
	}

	@Override
	public int getProjectCommissionCount(DataGridRequest request) {
		return 10;
		// return projectCommissionDao.getProjectCommissionCount(request);
	}

	@Override
	public ProjectCommissionDto getProjectCommission(String acNumber) {
		return projectCommissionDao.getProjectCommission(acNumber);
	}

	@Override
	public int getProjects(Date startDate, Date endDate) {
		return 0;
	}

	@Override
	public void saveProjectCommission(List<ProjectCommissionDto> projectCommissionDtos) throws IllegalAccessException {
		
	}

	@Override
	public int saveProjectCommission(ProjectCommissionDto projectCommission) throws IllegalAccessException {
		throw new IllegalAccessException("unsupport operation");
		//projectCommissionDao.saveProjectCommission(projectCommission);
	}

	@Override
	public void updateProjectCommission(ProjectCommissionDto projectCommission) {
		projectCommissionDao.updateProjectCommission(projectCommission);
	}
}
