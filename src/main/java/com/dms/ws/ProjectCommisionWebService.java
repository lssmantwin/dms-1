package com.dms.ws;

import com.dms.domain.Attendance;
import com.dms.domain.MiniResponse;
import com.dms.domain.Month;
import com.dms.domain.Position;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;
import com.dms.dto.ProjectCommisionDto;
import com.dms.request.FinanceRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1")
public interface ProjectCommisionWebService {
	@POST
	@Path("/projectCommisions")
	void saveProjectCommisions(List<ProjectCommisionDto> projectCommisionDtos);

	@GET
	@Path("/projectCommisions")
	MiniResponse<List<ProjectCommisionDto>> getProjectCommisions(@QueryParam("key") String key, @QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize,
												 @QueryParam("sortField") String sortField, @QueryParam("sortOrder") String sortOrder);


}