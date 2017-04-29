package com.dms.ws;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.dms.dto.ProjectCommissionDto;
import com.dms.response.DataGridResponse;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1")
public interface ProjectCommissionWebService {

	@POST
	@Path("/projectCommissions")
	void saveProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	@GET
	@Path("/projectCommissions")
	DataGridResponse<List<ProjectCommissionDto>> getProjectCommissions(@QueryParam("key") String key, @QueryParam("pageIndex") int pageIndex,
			@QueryParam("pageSize") int pageSize, @QueryParam("sortField") String sortField, @QueryParam("sortOrder") String sortOrder);

}