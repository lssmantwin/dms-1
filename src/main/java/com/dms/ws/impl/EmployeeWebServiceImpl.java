package com.dms.ws.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.domain.Attendance;
import com.dms.domain.Month;
import com.dms.domain.Position;
import com.dms.dto.EmployeeDto;
import com.dms.dto.EnumDto;
import com.dms.enums.CompanyEnum;
import com.dms.request.BaseFilterRequest;
import com.dms.response.DataGridResponse;
import com.dms.service.EmployeeService;
import com.dms.ws.EmployeeWebService;
import com.google.common.collect.Lists;

@Service("employeeWebService")
public class EmployeeWebServiceImpl implements EmployeeWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeWebServiceImpl.class);

	@Autowired
	private EmployeeService employeeService;

	@Override
	public List<Attendance> getAttendance() {
		return employeeService.getAttendance();
	}

	@Override
	public List<EnumDto> getCompanies() {
		return Lists.newArrayList(CompanyEnum.values()).stream().map(e -> new EnumDto(e.getDbConstant(), e.getText())).collect(Collectors.toList());
	}

	@Override
	public List<Month> getMonths() {
		return employeeService.getMonths();
	}

	@Override
	public List<Position> getPositions() {

		LOGGER.info("get positions");

		return employeeService.getPositions();

	}

	@Override
	public void saveEmployees(List<EmployeeDto> employeeDtos) {

		LOGGER.info("save employees, {}", employeeDtos);

		List<EmployeeDto> addEmployees = Lists.newArrayList();
		List<EmployeeDto> updateEmployees = Lists.newArrayList();
		for (EmployeeDto employeeDto : employeeDtos) {
			if (StringUtils.isBlank(employeeDto.getId())) {
				addEmployees.add(employeeDto);
				continue;
			}
			updateEmployees.add(employeeDto);
		}

		if (CollectionUtils.isNotEmpty(addEmployees)) {
			employeeService.saveEmployees(addEmployees);
		}
		if (CollectionUtils.isNotEmpty(updateEmployees)) {
			employeeService.updateEmployees(updateEmployees);
		}
	}

	@Override
	public DataGridResponse<List<EmployeeDto>> getEmployees(String employeeName, int pageIndex, int pageSize, String sortField, String sortOrder) {

		LOGGER.info("get employees, employeeName {}, pageIndex {}, pageSize {}, sortField {}, sortOrder {}", employeeName, pageIndex, pageSize, sortField,
				sortOrder);

		BaseFilterRequest request = generateFilterRequest(employeeName, pageIndex, pageSize, sortField, sortOrder);

		int count = employeeService.getEmployeeCount(request);
		List<EmployeeDto> employees = employeeService.getEmployees(request);

		DataGridResponse<List<EmployeeDto>> response = new DataGridResponse<>();
		response.setTotal(count);
		response.setData(employees);
		response.setRows(employees);

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
