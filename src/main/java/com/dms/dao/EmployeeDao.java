package com.dms.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dms.domain.Attendance;
import com.dms.domain.Month;
import com.dms.domain.Position;
import com.dms.dto.EmployeeDto;
import com.dms.request.BaseFilterRequest;

public interface EmployeeDao {

	List<Attendance> getAttendance();

	List<Month> getMonths();

	List<Position> getPositions();

	int getEmployeeCount(BaseFilterRequest request);

	List<EmployeeDto> getEmployees(BaseFilterRequest request);

	void saveEmployees(List<EmployeeDto> employeeDtos);

	void updateEmployees(List<EmployeeDto> employeeDtos);

	EmployeeDto getEmployee(Long employeeId);

	void updateCharge(@Param("id") Long employeeId, @Param("charge") BigDecimal charge);
}
