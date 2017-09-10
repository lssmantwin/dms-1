package com.dms.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.EmployeeDao;
import com.dms.domain.Attendance;
import com.dms.domain.Month;
import com.dms.dto.EmployeeDto;
import com.dms.request.BaseFilterRequest;
import com.dms.service.EmployeeService;
import org.springframework.transaction.annotation.Transactional;

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
	public int getEmployeeCount(BaseFilterRequest request) {
		return employeeDao.getEmployeeCount(request);
	}

	@Override
	public List<EmployeeDto> getEmployees(BaseFilterRequest request) {
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

	@Override
	@Transactional
	public void synchronizeCommissionRate(List<EmployeeDto> employeeDtos) {
		employeeDao.synchronizeDesignerRate(employeeDtos);
	}
}
