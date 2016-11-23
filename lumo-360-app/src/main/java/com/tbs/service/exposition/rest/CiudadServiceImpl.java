package com.tbs.service.exposition.rest;

import javax.ws.rs.core.Response;

import com.tbs.service.ContextProvider;
import com.tbs.service.persistence.entities.Ciudad;
import com.tbs.service.persistence.entities.Pais;
import com.tbs.service.persistence.spring.PersistenceService;

public class CiudadServiceImpl implements CiudadService{

	@Override
	public Response findAll() {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findAll(Ciudad.class))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

}
