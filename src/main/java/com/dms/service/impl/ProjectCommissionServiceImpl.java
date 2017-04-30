package com.dms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.ProjectCommissionDao;
import com.dms.dto.ProjectCommissionDto;
import com.dms.request.DataGridRequest;
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
		return projectCommissionDao.getProjectCommissionCount(request);
	}

	@Override
	public void updateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos) {
		projectCommissionDao.updateProjectCommissions(projectCommissionDtos);
	}
}
