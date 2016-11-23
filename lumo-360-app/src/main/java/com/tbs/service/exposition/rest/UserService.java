package com.tbs.service.exposition.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tbs.service.persistence.entities.Role;
import com.tbs.service.persistence.entities.User;

@Path("/")
public interface UserService {
	
	@GET
	@Path("usuario")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findAll();
	
	@GET
	@Path("usuario/ciudad/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findAllByCity(@PathParam("id") String idCiudad);
	
	@GET
	@Path("usuario/{from}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findPagination(@PathParam("from") Long from, @PathParam("limit") Long limit);
	
	@GET
	@Path("usuario/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findByID(@PathParam("id") Long id);

	@GET
	@Path("usercity/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response findCiudadesByID(@PathParam("id") Long id);

	
	@POST
	@Path("usuario/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response save(User user);

	@PUT
	@Path("usuario/upd")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response update(User user);

	@PUT
	@Path("usuario/del")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response remove(User user);
	
}
