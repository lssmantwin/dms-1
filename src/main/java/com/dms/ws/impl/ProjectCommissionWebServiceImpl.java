package com.dms.ws.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dto.EnumDto;
import com.dms.dto.ProjectCommissionDto;
import com.dms.enums.CommissionStateEnum;
import com.dms.enums.ContractStateEnum;
import com.dms.enums.EnumType;
import com.dms.request.ProjectCommissionFilterRequest;
import com.dms.response.DataGridResponse;
import com.dms.service.ProjectCommissionService;
import com.dms.ws.ProjectCommissionWebService;
import com.google.common.collect.Lists;

@Service("projectCommissionWebService")
public class ProjectCommissionWebServiceImpl implements ProjectCommissionWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectCommissionWebServiceImpl.class);

	@Autowired
	private ProjectCommissionService projectCommissionService;

	@Override
	public List<EnumDto> getStates(String type) {
		if (EnumType.CONTRACT.toString().equals(type)) {
			return Lists.newArrayList(ContractStateEnum.values()).stream().map(m -> new EnumDto(m.getDbConstant(), m.getText())).collect(Collectors.toList());
		}
		if (EnumType.COMMISSION.toString().equals(type)) {
			return Lists.newArrayList(CommissionStateEnum.values()).stream().map(m -> new EnumDto(m.getDbConstant(), m.getText())).collect(Collectors.toList());
		}
		return Lists.newArrayList();
	}

	@Override
	public void saveProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos) {

		LOGGER.info("save project commissions, {}", projectCommissionDtos);

		projectCommissionService.updateProjectCommissions(projectCommissionDtos);
	}

	@Override
	public DataGridResponse<List<ProjectCommissionDto>> getProjectCommissions(String designer, String contractState, String commissionState, int pageIndex,
			int pageSize, String sortField, String sortOrder) {

		LOGGER.info("get project commissions, designer {}, contractState {}, commissionState {}, pageIndex {}, pageSize {}, sortField {}, sortOrder {}",
				designer, contractState, commissionState, pageIndex, pageSize, sortField, sortOrder);

		ProjectCommissionFilterRequest request = generateProjectCommissionFilterRequest(designer, contractState, commissionState, pageIndex, pageSize,
				sortField, sortOrder);

		int count = projectCommissionService.getProjectCommissionCount(request);
		List<ProjectCommissionDto> projectCommissions = projectCommissionService.getProjectCommissions(request);

		DataGridResponse<List<ProjectCommissionDto>> response = new DataGridResponse<>();
		response.setTotal(count);
		response.setData(projectCommissions);

		return response;
	}

	private ProjectCommissionFilterRequest generateProjectCommissionFilterRequest(String designer, String contractState, String commissionState, int pageIndex,
			int pageSize, String sortField, String sortOrder) {
		ProjectCommissionFilterRequest request = new ProjectCommissionFilterRequest();
		request.setStart(pageIndex * pageSize + 1);
		request.setEnd((pageIndex + 1) * pageSize);
		request.setDesigner(designer);
		request.setContractState(contractState);
		request.setCommissionState(commissionState);
		request.setSortField(sortField);
		request.setSortOrder(sortOrder);
		return request;
	}

}