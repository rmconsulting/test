package com.tbs.service.exposition.rest;

import java.util.List;

import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tbs.service.Response;
import com.tbs.service.persistence.entities.Distrito;
import com.tbs.service.persistence.entities.Concentrador;

@Path("/")
public interface ConcentradorService {

	@GET
	@Path("concentrador")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findAll();
	
	@GET
	@Path("concentrador/avl")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findAvailables();
	
	@GET
	@Path("concentrador/{from}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findPagination(@PathParam("from") Long from, @PathParam("limit") Long limit);
	
	@GET
	@Path("concentrador/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findByID(@PathParam("id") Long id);

	@POST
	@Path("concentrador/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response save(Concentrador concentrador);

	@PUT
	@Path("concentrador/upd")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response update(Concentrador concentrador);

	@PUT
	@Path("concentrador/del")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response remove(Concentrador concentrador);
	
	@POST
	@Path("concentrador/on/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response on(@PathParam("id")Long id);
	@POST
	@Path("concentrador/off/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response off(@PathParam("id")Long id);
	
	@POST
	@Path("concentrador/dimmer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response dimmer(@PathParam("id")Long id);
	
}