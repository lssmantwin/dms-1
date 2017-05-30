package com.dms.ws;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.dms.dto.UserDto;
import org.springframework.stereotype.Controller;

@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/user/v1")
public interface UserWebService {

	@POST
	@Path("/check")
	boolean check(UserDto userDto);

}
