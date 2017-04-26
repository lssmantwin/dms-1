package com.dms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.ProjectCommissionDao;
import com.dms.request.DataGridRequest;
import com.dms.dto.ProjectCommissionDto;
import com.dms.service.ProjectCommissionService;

@Service
public class ProjectCommissionServiceImpl implements ProjectCommissionService {

	@Autowired
	private ProjectCommissionDao projectCommissionDao;

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
	public void saveProjectCommission(ProjectCommissionDto projectCommission) throws IllegalAccessException {
		throw new IllegalAccessException("unsupport operation");
		// projectCommissionDao.saveProjectCommission(projectCommission);
	}

	@Override
	public void updateProjectCommission(ProjectCommissionDto projectCommission) {
		projectCommissionDao.updateProjectCommission(projectCommission);
	}
}
