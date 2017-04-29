package com.dms.ws;

import com.dms.response.DataGridResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1")
public interface ProjectCommisionWebService {

	@POST
	@Path("/ProjectCommisions")
	void saveProjectCommisions(List<ProjectCommisionDto> ProjectCommisionDtos);

	@GET
	@Path("/ProjectCommisions")
	DataGridResponse<List<ProjectCommisionDto>> getProjectCommisions(@QueryParam("key") String key, @QueryParam("pageIndex") int pageIndex,
                                                                     @QueryParam("pageSize") int pageSize, @QueryParam("sortField") String sortField, @QueryParam("sortOrder") String sortOrder);

}