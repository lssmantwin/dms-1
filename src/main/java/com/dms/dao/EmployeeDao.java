package com.dms.dao;

import com.dms.domain.Attendance;
import com.dms.domain.Month;
import com.dms.domain.Position;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;
import com.dms.request.DataGridRequest;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeDao {

	List<Attendance> getAttendance();

	List<Month> getMonths();

	List<Position> getPositions();

	int getEmployeeCount(DataGridRequest request);

	List<EmployeeDto> getEmployees(DataGridRequest request);

	List<FinanceDto> getFinances(DataGridRequest request);

	void saveEmployees(List<EmployeeDto> employeeDtos);

	void updateEmployees(List<EmployeeDto> employeeDtos);

	void saveFinance(FinanceDto financeDto);

	void updateFinance(FinanceDto financeDto);

	EmployeeDto getEmployee(Long employeeId);

	void updateCharge(@Param("id") Long employeeId, @Param("charge") BigDecimal charge);
}
