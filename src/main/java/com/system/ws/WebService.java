package com.system.ws;

import com.system.domain.MiniResponse;
import com.system.domain.User;
import com.system.domain.User2;
import com.system.dto.PositionDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1")
public interface WebService {

	@POST
	@Path("/login")
	int login(User2 user);

	@POST
	@Path("/")
	int save(List<User> users);

	@DELETE
	@Path("/")
	void delete(List<User> users);

	@GET
	@Path("/")
	MiniResponse getUsers(@QueryParam("key") String key, @QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize);

}