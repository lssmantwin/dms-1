package com.dms.ws;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.dms.response.DmsResponse;
import org.springframework.stereotype.Controller;

import com.dms.dto.UserDto;

@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/user/v1")
public interface UserWebService {

	@GET
	@Path("/check")
	boolean check(@QueryParam("username") String username);

	@POST
	@Path("/login")
	DmsResponse login(UserDto userDto);

	@POST
	@Path("/register")
	DmsResponse register(UserDto userDto);

	@GET
	@Path("/user")
	DmsResponse getCurrentUser(@CookieParam("Token") String token);

	@GET
	@Path("/logout")
	DmsResponse logout(@CookieParam("Token") String token);

}
