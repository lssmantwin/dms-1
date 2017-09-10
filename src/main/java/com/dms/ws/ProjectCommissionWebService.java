package com.dms.ws;

import java.util.List;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;

import com.dms.dto.EnumDto;
import com.dms.dto.ProjectCommissionDto;
import com.dms.response.DmsResponse;

@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/commission/v1")
public interface ProjectCommissionWebService {

	@GET
	@Path("/export")
	Response export(@QueryParam("designer") String designer, @QueryParam("designerAssistant") String designerAssistant,
			@QueryParam("contractState") String contractState, @QueryParam("commissionState") String commissionState, @QueryParam("acNumber") String acNumber,
			@QueryParam("branch") String branch, @QueryParam("contractId") String contractId, @QueryParam("payContractRatio") String payContractRatio,
			@QueryParam("payProjectRatio") String payProjectRatio, @QueryParam("actualStartTime") String actualStartTime,
			@QueryParam("actualEndTime") String actualEndTime, @QueryParam("contractDate") String contractDate,
			@QueryParam("firstCommissionDate") String firstCommissionDate, @QueryParam("balanceTime") String balanceTime,
			@QueryParam("balanceCommissionDate") String balanceCommissionDate,
			@QueryParam("designerAssistantCommissionDate") String designerAssistantCommissionDate);

	@GET
	@Path("/states")
	DmsResponse getStates(@QueryParam("type") String type);

	@POST
	@Path("/projectCommissions")
	DmsResponse saveProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	@POST
	@Path("/calculateFirstCommission")
	DmsResponse calculateFirstCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	@POST
	@Path("/calculateBalanceCommission")
	DmsResponse calculateBalanceCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	@POST
	@Path("/importDesignAssistant")
	DmsResponse updateDesignAssistant(@QueryParam("designAssistant") String designAssistant);

	@POST
	@Path("/revertFirstCommission")
	DmsResponse revertFirstCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	@POST
	@Path("/revertBalanceCommission")
	DmsResponse revertBalanceCommissions(List<ProjectCommissionDto> projectCommissionDtos);


	@GET
	@Path("/projectCommissions")
	DmsResponse getProjectCommissions(@QueryParam("designer") String designer, @QueryParam("designerAssistant") String designerAssistant,
			@QueryParam("contractState") String contractState, @QueryParam("commissionState") String commissionState, @QueryParam("acNumber") String acNumber,
			@QueryParam("branch") String branch, @QueryParam("contractId") String contractId, @QueryParam("payContractRatio") String payContractRatio,
			@QueryParam("payProjectRatio") String payProjectRatio, @QueryParam("actualStartTime") String actualStartTime,
			@QueryParam("actualEndTime") String actualEndTime, @QueryParam("contractDate") String contractDate,
			@QueryParam("firstCommissionDate") String firstCommissionDate, @QueryParam("balanceTime") String balanceTime,
			@QueryParam("balanceCommissionDate") String balanceCommissionDate,
			@QueryParam("designerAssistantCommissionDate") String designerAssistantCommissionDate, @QueryParam("pageIndex") int pageIndex,
			@QueryParam("pageSize") int pageSize, @QueryParam("sortField") String sortField, @QueryParam("sortOrder") String sortOrder);

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	Response upload(@Context HttpServletRequest request) throws FileUploadException, IOException;
}