package com.dms.ws;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.dms.domain.*;
import com.dms.dto.EmployeeDto;

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