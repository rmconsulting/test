package com.tbs.service.exposition.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tbs.service.SecurityToken;

@Path("/")
public interface SecurityService {
	 	@POST
	    @Path("login/new")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public javax.ws.rs.core.Response token(SecurityToken user);
	 	
	 	
	 	@POST
	    @Path("change/pwd")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public javax.ws.rs.core.Response changePassword(SecurityToken user);
	 	
}
