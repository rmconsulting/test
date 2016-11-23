package com.tbs.service.exposition.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tbs.service.persistence.entities.Luminaria;
import com.tbs.service.persistence.entities.Token;

@Path("/")
public interface LuminariaService {

	@GET
	@Path("luminaria")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findAll();
	
	@GET
	@Path("luminaria/avl")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findAvailables();
	
	@GET
	@Path("luminaria/{from}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findPagination(@PathParam("from") Long from, @PathParam("limit") Long limit);
	
	@GET
	@Path("luminaria/{id}/{from}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findPaginationByControlador(@PathParam("id") Long controladorId,@PathParam("from") Long from, @PathParam("limit") Long limit);

	@GET
	@Path("luminaria/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findByID(@PathParam("id") Long id);

	@POST
	@Path("luminaria/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response save(Luminaria luminaria);

	@PUT
	@Path("luminaria/upd")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response update(Luminaria luminaria);

	@PUT
	@Path("luminaria/del")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response remove(Luminaria luminaria);
	@POST
	@Path("luminaria/set/{idcontrolador}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response add2Controlador(Luminaria luminaria, @PathParam("idcontrolador") Long idControlador);
	
	@POST
	@Path("luminaria/on/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response on(@PathParam("id")Long id, Token token);
	@POST
	@Path("luminaria/off/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response off(@PathParam("id")Long id, Token token);
	
	@POST
	@Path("luminaria/dimmer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response dimmer(@PathParam("id")Long id, Token token);
	
}