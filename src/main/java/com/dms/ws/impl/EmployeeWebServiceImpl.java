package com.dms.ws.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.aspect.CheckAuthority;
import com.dms.domain.Attendance;
import com.dms.domain.Month;
import com.dms.dto.EmployeeDto;
import com.dms.dto.EnumDto;
import com.dms.enums.CompanyEnum;
import com.dms.enums.ResponseEnum;
import com.dms.request.BaseFilterRequest;
import com.dms.response.DmsResponse;
import com.dms.service.EmployeeService;
import com.dms.ws.EmployeeWebService;
import com.google.common.collect.Lists;

@Service("employeeWebService")
public class EmployeeWebServiceImpl implements EmployeeWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeWebServiceImpl.class);

	@Autowired
	private EmployeeService employeeService;

	@Override
	@CheckAuthority
	public DmsResponse getAttendance() {
		DmsResponse<List<Attendance>> response = new DmsResponse<>();
		response.setCode(ResponseEnum.SUCCESS);
		response.setData(employeeService.getAttendance());
		return response;
	}

	@Override
	@CheckAuthority
	public DmsResponse getCompanies() {
		List<EnumDto> companies = Lists.newArrayList(CompanyEnum.values()).stream().map(e -> new EnumDto(e.getDbConstant(), e.getText()))
				.collect(Collectors.toList());
		DmsResponse<List<EnumDto>> response = new DmsResponse<>();
		response.setCode(ResponseEnum.SUCCESS);
		response.setData(companies);
		return response;
	}

	@Override
	@CheckAuthority
	public DmsResponse getMonths() {
		List<Month> months = employeeService.getMonths();
		DmsResponse<List<Month>> response = new DmsResponse<>();
		response.setCode(ResponseEnum.SUCCESS);
		response.setData(months);
		return response;
	}

	@Override
	@CheckAuthority
	public DmsResponse saveEmployees(List<EmployeeDto> employeeDtos) {
		LOGGER.info("save employees, {}", employeeDtos);
		List<EmployeeDto> employees = Lists.newArrayList();
		for (EmployeeDto employeeDto : employeeDtos) {
			if (StringUtils.isBlank(employeeDto.getName())) {
				continue;
			}
			employees.add(employeeDto);
		}
		employeeService.saveEmployees(employees);
		DmsResponse response = new DmsResponse();
		response.setCode(ResponseEnum.SUCCESS);
		return response;
	}

	@Override
	@CheckAuthority
	public DmsResponse<List<EmployeeDto>> getEmployees(String employeeName, int pageIndex, int pageSize, String sortField, String sortOrder) {

		LOGGER.info("get employees, employeeName {}, pageIndex {}, pageSize {}, sortField {}, sortOrder {}", employeeName, pageIndex, pageSize, sortField,
				sortOrder);

		BaseFilterRequest request = generateFilterRequest(employeeName, pageIndex, pageSize, sortField, sortOrder);

		int count = employeeService.getEmployeeCount(request);
		List<EmployeeDto> employees = employeeService.getEmployees(request);

		DmsResponse<List<EmployeeDto>> response = new DmsResponse<>();
		response.setCode(ResponseEnum.SUCCESS);
		response.setTotal(count);
		response.setData(employees);

		return response;
	}

	private BaseFilterRequest generateFilterRequest(String employeeName, int pageIndex, int pageSize, String sortField, String sortOrder) {
		BaseFilterRequest request = new BaseFilterRequest();
		request.setStart(pageIndex * pageSize + 1);
		request.setEnd((pageIndex + 1) * pageSize);
		request.setEmployeeName(employeeName);
		request.setSortField(sortField);
		request.setSortOrder(sortOrder);
		return request;
	}

}
