package com.dms.service;

import java.math.BigDecimal;
import java.util.List;

import com.dms.domain.Attendance;
import com.dms.domain.Month;
import com.dms.dto.EmployeeDto;
import com.dms.request.BaseFilterRequest;

public interface EmployeeService {

	List<Attendance> getAttendance();

	List<Month> getMonths();

	int getEmployeeCount(BaseFilterRequest request);

	List<EmployeeDto> getEmployees(BaseFilterRequest request);

	void saveEmployees(List<EmployeeDto> employeeDtos);

	void updateEmployees(List<EmployeeDto> employeeDtos);

	EmployeeDto getEmployee(Long employeeId);

	void updateCharge(Long employeeId, BigDecimal charge);

}
