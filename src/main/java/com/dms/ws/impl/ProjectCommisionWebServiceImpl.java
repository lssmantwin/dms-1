package com.dms.ws.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.request.DataGridRequest;
import com.dms.response.DataGridResponse;
import com.dms.dto.ProjectCommisionDto;
import com.dms.service.ProjectCommisionService;
import com.dms.ws.ProjectCommisionWebService;

@Service("projectCommisionWebService")
public class ProjectCommisionWebServiceImpl implements ProjectCommisionWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectCommisionWebServiceImpl.class);

	@Autowired
	private ProjectCommisionService projectCommisionService;

	@Override
	public void saveProjectCommisions(List<ProjectCommisionDto> projectCommisionDtos) {

		LOGGER.info("save projectCommision, {}", projectCommisionDtos);

		for (ProjectCommisionDto projectCommisionDto : projectCommisionDtos) {
			projectCommisionService.updateProjectCommision(projectCommisionDto);
		}
	}

	@Override
	public DataGridResponse<List<ProjectCommisionDto>> getProjectCommisions(String key, int pageIndex, int pageSize, String sortField, String sortOrder) {

		LOGGER.info("get projectCommisions, key {}, pageIndex {}, pageSize {}, sortField {}, sortOrder {}", key, pageIndex, pageSize, sortField, sortOrder);

		DataGridRequest request = generateDataGridRequest(key, pageIndex, pageSize, sortField, sortOrder);

		int count = projectCommisionService.getProjectCommisionCount(request);
		List<ProjectCommisionDto> projectCommisions = projectCommisionService.getProjectCommisions(request);

		DataGridResponse<List<ProjectCommisionDto>> response = new DataGridResponse<>();
		response.setTotal(count);
		response.setData(projectCommisions);

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
