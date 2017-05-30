package com.dms.ws;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileUploadException;
import org.jboss.resteasy.spi.HttpRequest;
import org.springframework.stereotype.Controller;

import com.dms.dto.EnumDto;
import com.dms.dto.ProjectCommissionDto;
import com.dms.response.DataGridResponse;

@Controller
@Path("/commission/v1")
public interface ProjectCommissionWebService {

	@GET
	@Path("/export")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response export(@QueryParam("designer") String designer, @QueryParam("contractState") String contractState,
			@QueryParam("commissionState") String commissionState, @QueryParam("actualStartTime") String actualStartTime,
			@QueryParam("actualEndTime") String actualEndTime, @QueryParam("contractDate") String contractDate,
			@QueryParam("firstCommissionDate") String firstCommissionDate, @QueryParam("balanceTime") String balanceTime,
			@QueryParam("balanceCommissionDate") String balanceCommissionDate);

	@GET
	@Path("/states")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	List<EnumDto> getStates(@QueryParam("type") String type);

	@POST
	@Path("/projectCommissions")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	void saveProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	@POST
	@Path("/calculateCommission")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	void calculateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos);

	@GET
	@Path("/projectCommissions")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	DataGridResponse<List<ProjectCommissionDto>> getProjectCommissions(@QueryParam("designer") String designer,
			@QueryParam("contractState") String contractState, @QueryParam("commissionState") String commissionState,
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