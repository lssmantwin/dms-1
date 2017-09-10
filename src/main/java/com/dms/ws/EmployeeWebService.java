package com.dms.ws;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.dms.domain.Attendance;
import com.dms.domain.Month;
import com.dms.dto.EmployeeDto;
import com.dms.dto.EnumDto;
import com.dms.response.DmsResponse;

@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/employee/v1")
public interface EmployeeWebService {

	@GET
	@Path("/attendance")
	DmsResponse getAttendance();

	@GET
	@Path("/companies")
	DmsResponse getCompanies();

	@GET
	@Path("/months")
	DmsResponse getMonths();

	@POST
	@Path("/employees")
	DmsResponse saveEmployees(List<EmployeeDto> employeeDtos);

	@POST
	@Path("/synchronizeCommissionRate")
	DmsResponse synchronizeCommissionRate(List<EmployeeDto> employeeDtos);

	@GET
	@Path("/employees")
	DmsResponse getEmployees(@QueryParam("employeeName") String employeeName, @QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize,
			@QueryParam("sortField") String sortField, @QueryParam("sortOrder") String sortOrder);

}