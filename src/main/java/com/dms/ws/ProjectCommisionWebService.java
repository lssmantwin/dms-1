package com.dms.ws;

import com.dms.response.DataGridResponse;
import com.dms.dto.ProjectCommisionDto;

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
	DataGridResponse<List<ProjectCommisionDto>> getProjectCommisions(@QueryParam("key") String key, @QueryParam("pageIndex") int pageIndex,
																	 @QueryParam("pageSize") int pageSize, @QueryParam("sortField") String sortField, @QueryParam("sortOrder") String sortOrder);

}