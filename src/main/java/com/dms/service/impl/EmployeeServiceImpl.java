package com.dms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.EmployeeDao;
import com.dms.domain.*;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;
import com.dms.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Attendance> getAttendance() {
		return employeeDao.getAttendance();
	}

	@Override
	public List<Month> getMonths() {
		return employeeDao.getMonths();
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
	public List<EmployeeDto> getEmployees(MiniRequest request) {
		return employeeDao.getEmployees(request);
	}

	@Override
	public List<FinanceDto> getFinances(MiniRequest request) {
		return employeeDao.getFinances(request);
	}

	@Override
	public void saveEmployee(EmployeeDto employee) {
		employeeDao.saveEmployee(employee);
	}

	@Override
	public void updateEmployee(EmployeeDto employee) {
		employeeDao.updateEmployee(employee);
	}

	@Override
	public void saveFinance(FinanceDto financeDto) {
		employeeDao.saveFinance(financeDto);
	}

	@Override
	public void updateFinance(FinanceDto financeDto) {
		employeeDao.updateFinance(financeDto);
	}
}
