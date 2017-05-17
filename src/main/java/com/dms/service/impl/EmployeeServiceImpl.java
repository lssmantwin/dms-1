package com.dms.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.EmployeeDao;
import com.dms.domain.Attendance;
import com.dms.domain.Month;
import com.dms.domain.Position;
import com.dms.dto.EmployeeDto;
import com.dms.request.DataGridRequest;
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
	public int getEmployeeCount(DataGridRequest request) {
		return employeeDao.getEmployeeCount(request);
	}

	@Override
	public List<EmployeeDto> getEmployees(DataGridRequest request) {
		return employeeDao.getEmployees(request);
	}

	@Override
	public void saveEmployees(List<EmployeeDto> employeeDtos) {
		employeeDao.saveEmployees(employeeDtos);
	}

	@Override
	public void updateEmployees(List<EmployeeDto> employeeDtos) {
		employeeDao.updateEmployees(employeeDtos);
	}

	@Override
	public EmployeeDto getEmployee(Long employeeId) {
		return employeeDao.getEmployee(employeeId);
	}

	@Override
	public void updateCharge(Long employeeId, BigDecimal charge) {
		employeeDao.updateCharge(employeeId, charge);
	}
}
