package com.system.dao;

import com.system.domain.*;

import java.util.List;

public interface EmployeeDao {

	List<Month> getMonths();

	List<Menu> getMenus();

	List<Position> getPositions();

	int getEmployeeCount(MiniRequest request);

	List<Employee> getEmployees(MiniRequest request);

	void save(Employee employee);

	void update(Employee employee);
}
