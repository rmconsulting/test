package com.tbs.service.exposition.rest;

import javax.ws.rs.core.Response;

import com.tbs.service.ContextProvider;
import com.tbs.service.persistence.entities.Profile;
import com.tbs.service.persistence.spring.PersistenceService;

public class ProfileServiceImpl implements ProfileService {

	@Override
	public Response findAll() {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findAll(Profile.class))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}
}