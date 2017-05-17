package com.dms.service;

import java.math.BigDecimal;
import java.util.List;

import com.dms.domain.Attendance;
import com.dms.domain.Month;
import com.dms.domain.Position;
import com.dms.dto.EmployeeDto;
import com.dms.request.DataGridRequest;

public interface EmployeeService {

	List<Attendance> getAttendance();

	List<Month> getMonths();

	List<Position> getPositions();

	int getEmployeeCount(DataGridRequest request);

	List<EmployeeDto> getEmployees(DataGridRequest request);

	void saveEmployees(List<EmployeeDto> employeeDtos);

	void updateEmployees(List<EmployeeDto> employeeDtos);

	EmployeeDto getEmployee(Long employeeId);

	void updateCharge(Long employeeId, BigDecimal charge);

}
