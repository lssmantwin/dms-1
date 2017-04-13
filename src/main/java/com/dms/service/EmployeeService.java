package com.dms.service;

import com.dms.domain.*;

import java.util.List;

public interface EmployeeService {

	List<Month> getMonths();

	List<Position> getPositions();

	int getEmployeeCount(MiniRequest request);

	List<Employee> getEmployees(MiniRequest request);

	void save(Employee employee);

	void update(Employee employee);

}
