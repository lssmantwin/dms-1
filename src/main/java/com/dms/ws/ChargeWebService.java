package com.dms.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	List<ChargeDetailDto> getChargeDetails();

}
