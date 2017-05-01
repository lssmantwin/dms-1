package com.dms.service.impl;

import java.util.List;

import com.dms.request.ProjectCommissionFilterRequest;
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
	public List<ProjectCommissionDto> getProjectCommissions(ProjectCommissionFilterRequest request) {
		return projectCommissionDao.getProjectCommissions(request);
	}

	@Override
	public int getProjectCommissionCount(ProjectCommissionFilterRequest request) {
		return projectCommissionDao.getProjectCommissionCount(request);
	}

	@Override
	public void updateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos) {
		projectCommissionDao.updateProjectCommissions(projectCommissionDtos);
	}
}
