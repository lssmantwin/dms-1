package com.dms.ws;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

import com.dms.request.FinanceRequest;
import com.dms.response.DmsResponse;

@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/finance/v1")
public interface FinanceWebService {

	@GET
	@Path("/finances")
	DmsResponse getFinances(@QueryParam("employeeName") String employeeName, @QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize,
			@QueryParam("sortField") String sortField, @QueryParam("sortOrder") String sortOrder, @QueryParam("month") String month);

	@POST
	@Path("/finances")
	DmsResponse saveFinances(FinanceRequest request);

	@GET
	@Path("/export")
	Response export(@QueryParam("employeeName") String employeeName, @QueryParam("month") String month);

	@POST
	@Path("/synchronizeDesignerCommissions")
	DmsResponse synchronizeDesignerCommissions(FinanceRequest financeRequest);

	@POST
	@Path("/synchronizeDesignerAssistantCommissions")
	DmsResponse synchronizeDesignerAssistantCommissions(FinanceRequest financeRequest);
}
