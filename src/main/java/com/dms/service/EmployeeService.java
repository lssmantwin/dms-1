package com.dms.service;

import java.util.List;

import com.dms.domain.*;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;

public interface EmployeeService {

	List<Attendance> getAttendance();

	List<Month> getMonths();

	List<Position> getPositions();

	int getEmployeeCount(MiniRequest request);

	List<EmployeeDto> getEmployees(MiniRequest request);

	List<FinanceDto> getFinances(MiniRequest request);

	void saveEmployee(EmployeeDto employee);

	void updateEmployee(EmployeeDto employee);

	void saveFinance(FinanceDto financeDto);

	void updateFinance(FinanceDto financeDto);
}
