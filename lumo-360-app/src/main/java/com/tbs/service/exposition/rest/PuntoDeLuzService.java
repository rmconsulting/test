package com.tbs.service.exposition.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tbs.service.persistence.entities.PuntoLuz;
import com.tbs.service.persistence.entities.Token;

@Path("/")
public interface PuntoDeLuzService {

	@GET
	@Path("puntodeluz")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findAll();
	
	@GET
	@Path("puntodeluz/avl")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findAvailables();
	
	@GET
	@Path("puntodeluz/{from}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findPagination(@PathParam("from") Long from, @PathParam("limit") Long limit);
	
	@POST
	@Path("puntodeluz/set/{idconcentrador}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response add2Concentrador(PuntoLuz puntodeluz, @PathParam("idconcentrador") Long idConcentrador);
	
	@GET
	@Path("puntodeluz/{id}/{from}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findPaginationByConcentrador(@PathParam("id") Long concentradorID,@PathParam("from") Long from, @PathParam("limit") Long limit);

	@GET
	@Path("puntodeluz/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findByID(@PathParam("id") Long id);

	@POST
	@Path("puntodeluz/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response save(PuntoLuz puntodeluz);

	@PUT
	@Path("puntodeluz/upd")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response update(PuntoLuz puntodeluz);

	@PUT
	@Path("puntodeluz/del")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response remove(PuntoLuz puntodeluz);
	
	@POST
	@Path("puntodeluz/on/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response on(@PathParam("id")Long id, Token token);
	@POST
	@Path("puntodeluz/off/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response off(@PathParam("id")Long id, Token token);
	
	@POST
	@Path("puntodeluz/dimmer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response dimmer(@PathParam("id")Long id, Token token);
	
}