package com.dms.service;

import java.util.List;

import com.dms.domain.*;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;
import com.dms.request.DataGridRequest;

public interface EmployeeService {

	List<Attendance> getAttendance();

	List<Month> getMonths();

	List<Position> getPositions();

	int getEmployeeCount(DataGridRequest request);

	List<EmployeeDto> getEmployees(DataGridRequest request);

	List<FinanceDto> getFinances(DataGridRequest request);

	void saveEmployees(List<EmployeeDto> employeeDtos);

	void updateEmployees(List<EmployeeDto> employeeDtos);

	void saveFinance(FinanceDto financeDto);

	void updateFinance(FinanceDto financeDto);
}
