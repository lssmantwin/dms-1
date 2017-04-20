package com.dms.service;

import com.dms.domain.Attendance;
import com.dms.domain.MiniRequest;
import com.dms.domain.Month;
import com.dms.domain.Position;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;
import com.dms.dto.ProjectCommisionDto;

import java.util.List;

public interface ProjectCommisionService {

	List<ProjectCommisionDto> getProjectCommisions(MiniRequest request);

	int getProjectCommisionCount(MiniRequest request);

	void saveProjectCommision(ProjectCommisionDto projectCommisionDto) throws IllegalAccessException;

	void updateProjectCommision(ProjectCommisionDto projectCommisionDto);
}
