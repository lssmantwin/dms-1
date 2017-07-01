package com.dms.ws;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.dms.response.DmsResponse;

@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/charge/v1")
public interface ChargeWebService {

	@GET
	@Path("/charges")
	DmsResponse getChargeDetails(@QueryParam("employeeName") String employeeName, @QueryParam("chargeTime") String chargeTime,
			@QueryParam("chargeBalance") String chargeBalance);

}
