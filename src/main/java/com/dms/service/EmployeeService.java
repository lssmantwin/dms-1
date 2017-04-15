package com.dms.service;

import java.util.List;

import com.dms.domain.*;
import com.dms.dto.FinanceDto;

public interface EmployeeService {

	List<Attendance> getAttendance();

	List<Month> getMonths();

	List<Position> getPositions();

	int getEmployeeCount(MiniRequest request);

	List<Employee> getEmployees(MiniRequest request);

	List<FinanceDto> getFinances(MiniRequest request);

	void saveEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void saveFinance(FinanceDto financeDto);

	void updateFinance(FinanceDto financeDto);
}
