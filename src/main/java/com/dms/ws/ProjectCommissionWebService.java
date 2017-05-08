package com.dms.ws;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

import com.dms.dto.EnumDto;
import com.dms.dto.ProjectCommissionDto;
import com.dms.response.DataGridResponse;

@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/commission/v1")
public interface ProjectCommissionWebService {

	@GET
	@Path("/export")
	Response export(@QueryParam("designer") String designer, @QueryParam("contractState") String contractState,
			@QueryParam("commissionState") String commissionState, @QueryParam("actualStartTime") String actualStartTime,
			@QueryParam("actualEndTime") String actualEndTime, @QueryParam("contractDate") String contractDate,
			@QueryParam("firstCommissionDate") String firstCommissionDate, @QueryParam("balanceTime") String balanceTime,
			@QueryParam("balanceCommissionDate") String balanceCommissionDate);

	@GET
	@Path("/states")
	List<EnumDto> getStates(@QueryParam("type") String type);

	@POST
	@Path("/projectCommissions")
	void saveProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	@POST
	@Path("/calculateCommission")
	void calculateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	@GET
	@Path("/projectCommissions")
	DataGridResponse<List<ProjectCommissionDto>> getProjectCommissions(@QueryParam("designer") String designer,
			@QueryParam("contractState") String contractState, @QueryParam("commissionState") String commissionState,
			@QueryParam("actualStartTime") String actualStartTime, @QueryParam("actualEndTime") String actualEndTime,
			@QueryParam("contractDate") String contractDate, @QueryParam("firstCommissionDate") String firstCommissionDate,
			@QueryParam("balanceTime") String balanceTime, @QueryParam("balanceCommissionDate") String balanceCommissionDate,
			@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize, @QueryParam("sortField") String sortField,
			@QueryParam("sortOrder") String sortOrder);

}