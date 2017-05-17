package com.dms.ws;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.dms.domain.Attendance;
import com.dms.domain.Month;
import com.dms.domain.Position;
import com.dms.dto.EmployeeDto;
import com.dms.dto.EnumDto;
import com.dms.response.DataGridResponse;
import org.springframework.stereotype.Controller;

@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/employee/v1")
public interface EmployeeWebService {

	@GET
	@Path("/attendance")
	List<Attendance> getAttendance();

	@GET
	@Path("/companies")
	List<EnumDto> getCompanies();

	@GET
	@Path("/months")
	List<Month> getMonths();

	@GET
	@Path("/positions")
	List<Position> getPositions();

	@POST
	@Path("/employees")
	void saveEmployees(List<EmployeeDto> employeeDtos);

	@GET
	@Path("/employees")
	DataGridResponse<List<EmployeeDto>> getEmployees(@QueryParam("key") String key, @QueryParam("pageIndex") int pageIndex,
			@QueryParam("pageSize") int pageSize, @QueryParam("sortField") String sortField, @QueryParam("sortOrder") String sortOrder);

}