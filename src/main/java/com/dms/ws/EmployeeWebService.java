package com.dms.ws;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.dms.domain.Attendance;
import com.dms.domain.MiniResponse;
import com.dms.domain.Month;
import com.dms.domain.Position;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;
import com.dms.request.FinanceRequest;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1")
public interface EmployeeWebService {

	@GET
	@Path("/attendance")
	List<Attendance> getAttendance();

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
	MiniResponse<List<EmployeeDto>> getEmployees(@QueryParam("key") String key, @QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize,
			@QueryParam("sortField") String sortField, @QueryParam("sortOrder") String sortOrder);

	@GET
	@Path("/finances")
	MiniResponse<List<FinanceDto>> getFinances(@QueryParam("key") String key, @QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize,
			@QueryParam("sortField") String sortField, @QueryParam("sortOrder") String sortOrder);

	@POST
	@Path("/finances")
	void saveFinances(FinanceRequest request);
}