package com.dms.dao;

import com.dms.domain.*;

import java.util.List;

public interface EmployeeDao {

	List<Month> getMonths();

	List<Position> getPositions();

	int getEmployeeCount(MiniRequest request);

	List<Employee> getEmployees(MiniRequest request);

	void save(Employee employee);

	void update(Employee employee);
}
