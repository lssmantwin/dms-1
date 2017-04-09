package com.system.service.impl;

import com.system.dao.EmployeeDao;
import com.system.domain.*;
import com.system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Month> getMonths() {
		return employeeDao.getMonths();
	}

	@Override
	public List<Menu> getMenus() {
		return employeeDao.getMenus();
	}

	@Override
	public List<Position> getPositions() {
		return employeeDao.getPositions();
	}

	@Override
	public int getEmployeeCount(MiniRequest request) {
		return employeeDao.getEmployeeCount(request);
	}

	@Override
	public List<Employee> getEmployees(MiniRequest request) {
		return employeeDao.getEmployees(request);
	}

	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}
}
