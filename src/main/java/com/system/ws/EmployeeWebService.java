package com.system.ws;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.system.domain.*;
import com.system.dto.EmployeeDto;
import com.system.dto.PositionDto;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1")
public interface EmployeeWebService {

	@GET
	@Path("/months")
	List<Month> getMonths();

	@GET
	@Path("/positions")
	List<Position> getPositions();

	@POST
	@Path("/")
	void save(List<EmployeeDto> employeeDtos);

	@GET
	@Path("/")
	MiniResponse getEmployees(@QueryParam("key") String key, @QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize,
			@QueryParam("sortField") String sortField, @QueryParam("sortOrder") String sortOrder);

}