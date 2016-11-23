package com.tbs.service.exposition.rest;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.tbs.service.ContextProvider;
import com.tbs.service.SecurityToken;
import com.tbs.service.UserToken;
import com.tbs.service.dto.UserDTO;
import com.tbs.service.persistence.entities.Token;
import com.tbs.service.persistence.entities.User;
import com.tbs.service.persistence.spring.PersistenceService;
import com.tbs.utils.Utilities;

public class SecurityServiceImpl implements SecurityService {

	@Override
	public Response token(SecurityToken securityToken) {
		Token t = new Token();
		t.setUser(securityToken.getUser());
		try {
			User user = ContextProvider.getInstance().lookup(PersistenceService.class).login(User.class, securityToken);

			Token token = (Token) ContextProvider.getInstance().lookup(PersistenceService.class).saveOrUpdate(t);

			return Response
					.ok(new UserToken(new UserDTO(user.getID(), user.getName(), user.getUsername(), user.isSuperUser()),
							token))
					.build();
		} catch (NoResultException e) {
			return Response.status(Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response changePassword(SecurityToken token) {
		User user = null;
		try {
			user = ContextProvider.getInstance().lookup(PersistenceService.class).login(User.class, token);
					
			if(user==null){
				return Response.status(Status.NO_CONTENT).build();
			}
		} catch (NoResultException e) {
			return Response.status(Status.NO_CONTENT).build();
		}catch(Exception e1){
			return javax.ws.rs.core.Response.serverError().build();
		}
		user.setCryptPassword(token.getNewPassword());
		
		return new UserServiceImpl().update(user);
	}

}
