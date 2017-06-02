package com.dms.ws;

import java.util.Base64;
import java.util.List;

import javax.jws.WebParam;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileUploadException;
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
	Response export(@QueryParam("designer") String designer, @QueryParam("designerAssistant") String designerAssistant,
			@QueryParam("contractState") String contractState, @QueryParam("commissionState") String commissionState, @QueryParam("acNumber") String acNumber,
			@QueryParam("contractId") String contractId, @QueryParam("payContractRatio") String payContractRatio,
			@QueryParam("payProjectRatio") String payProjectRatio, @QueryParam("actualStartTime") String actualStartTime,
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
	@Path("/calculateFirstCommission")
	void calculateFirstCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	@POST
	@Path("/calculateBalanceCommission")
	void calculateBalanceCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	@POST
	@Path("/importDesignAssistant")
	void updateDesignAssistant(@QueryParam("designAssistant") String designAssistant);

	@GET
	@Path("/projectCommissions")
	DataGridResponse<List<ProjectCommissionDto>> getProjectCommissions(@QueryParam("designer") String designer,
			@QueryParam("designerAssistant") String designerAssistant, @QueryParam("contractState") String contractState,
			@QueryParam("commissionState") String commissionState, @QueryParam("acNumber") String acNumber, @QueryParam("contractId") String contractId,
			@QueryParam("payContractRatio") String payContractRatio, @QueryParam("payProjectRatio") String payProjectRatio,
			@QueryParam("actualStartTime") String actualStartTime, @QueryParam("actualEndTime") String actualEndTime,
			@QueryParam("contractDate") String contractDate, @QueryParam("firstCommissionDate") String firstCommissionDate,
			@QueryParam("balanceTime") String balanceTime, @QueryParam("balanceCommissionDate") String balanceCommissionDate,
			@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize, @QueryParam("sortField") String sortField,
			@QueryParam("sortOrder") String sortOrder);

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	void upload(@Context HttpServletRequest request) throws FileUploadException, IOException;
}