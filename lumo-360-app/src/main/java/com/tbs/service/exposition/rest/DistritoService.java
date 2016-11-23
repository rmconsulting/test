package com.tbs.service.exposition.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tbs.service.persistence.entities.Distrito;

@Path("/")
public interface DistritoService {
	@GET
	@Path("distrito")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findAll();

	@GET
	@Path("distrito/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findByID(@PathParam("id") String id);

	@POST
	@Path("distrito/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response save(Distrito distrito);

	@PUT
	@Path("distrito/upd")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response update(Distrito distrito);

	@PUT
	@Path("distrito/del")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response remove(Distrito distrito);

	@POST
	@Path("distrito/on/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response on(@PathParam("id")Long id);
	@POST
	@Path("distrito/off/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response off(@PathParam("id")Long id);
	
	@POST
	@Path("distrito/dimmer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response dimmer(@PathParam("id")Long id);
	
	// @DELETE
	// @Path("distrito/del")
	// @Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	// public javax.ws.rs.core.Response remove(Designer sector);
}
