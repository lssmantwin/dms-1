package com.dms.ws;

import com.dms.dto.EnumDto;
import com.dms.dto.ProjectCommissionDto;
import com.dms.response.DataGridResponse;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/commission/v1")
public interface ProjectCommissionWebService {

	@GET
	@Path("/states")
	List<EnumDto> getStates(@QueryParam("type") String type);

	@POST
	@Path("/projectCommissions")
	void saveProjectCommissions(List<ProjectCommissionDto> ProjectCommissionDtos);

	@POST
	@Path("/calculateCommission")
	void calculateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	@GET
	@Path("/projectCommissions")
	DataGridResponse<List<ProjectCommissionDto>> getProjectCommissions(@QueryParam("designer") String designer,
			@QueryParam("designerAssistant") String designerAssistant,
			@QueryParam("contractState") String contractState, @QueryParam("commissionState") String commissionState,
			@QueryParam("contractId") String contractId, @QueryParam("payContractRatio") String payContractRatio,
			 @QueryParam("payProjectRatio") String payProjectRatio, @QueryParam("actualStartTime") String actualStartTime,
			@QueryParam("actualEndTime") String actualEndTime,
			@QueryParam("contractDate") String contractDate, @QueryParam("firstCommissionDate") String firstCommissionDate,
			@QueryParam("balanceTime") String balanceTime, @QueryParam("balanceCommissionDate") String balanceCommissionDate,
			@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize, @QueryParam("sortField") String sortField,
			@QueryParam("sortOrder") String sortOrder);

}