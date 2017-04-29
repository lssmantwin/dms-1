package com.dms.ws.impl;

import com.dms.dto.ProjectCommissionDto;
import com.dms.request.DataGridRequest;
import com.dms.response.DataGridResponse;
import com.dms.service.ProjectCommissionService;
import com.dms.ws.ProjectCommissionWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectCommissionWebService")
public class ProjectCommissionWebServiceImpl implements ProjectCommissionWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectCommissionWebServiceImpl.class);

	@Autowired
	private ProjectCommissionService projectCommissionService;

	@Override
	public void saveProjectCommissions(List<ProjectCommissionDto> ProjectCommissionDtos) {

		LOGGER.info("save ProjectCommission, {}", ProjectCommissionDtos);

		for (ProjectCommissionDto ProjectCommissionDto : ProjectCommissionDtos) {
			projectCommissionService.updateProjectCommission(ProjectCommissionDto);
		}
	}

	@Override
	public DataGridResponse<List<ProjectCommissionDto>> getProjectCommissions(String key, int pageIndex, int pageSize, String sortField, String sortOrder) {

		LOGGER.info("get projectCommissions, key {}, pageIndex {}, pageSize {}, sortField {}, sortOrder {}", key, pageIndex, pageSize, sortField, sortOrder);

		DataGridRequest request = generateDataGridRequest(key, pageIndex, pageSize, sortField, sortOrder);

		int count = projectCommissionService.getProjectCommissionCount(request);
		List<ProjectCommissionDto> projectCommissions = projectCommissionService.getProjectCommissions(request);

		DataGridResponse<List<ProjectCommissionDto>> response = new DataGridResponse<>();
		response.setTotal(count);
		response.setData(projectCommissions);

		return response;
	}

	private DataGridRequest generateDataGridRequest(String key, int pageIndex, int pageSize, String sortField, String sortOrder) {
		DataGridRequest request = new DataGridRequest();
		request.setStart(pageIndex * pageSize + 1);
		request.setEnd((pageIndex + 1) * pageSize);
		request.setKey(key);
		request.setSortField(sortField);
		request.setSortOrder(sortOrder);
		return request;
	}

}
