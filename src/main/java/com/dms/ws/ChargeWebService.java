package com.dms.ws;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.dms.dto.ChargeDetailDto;

@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/charge/v1")
public interface ChargeWebService {

	@GET
	@Path("/charges")
	List<ChargeDetailDto> getChargeDetails(@QueryParam("employeeName") String employeeName);

}
