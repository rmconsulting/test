package com.tbs.service.exposition.rest;

import javax.ws.rs.core.Response;

import com.tbs.service.ContextProvider;
import com.tbs.service.persistence.entities.Ciudad;
import com.tbs.service.persistence.entities.Provincia;
import com.tbs.service.persistence.entities.Pais;
import com.tbs.service.persistence.spring.PersistenceService;

public class ProvinciaServiceImpl implements ProvinciaService{

	@Override
	public Response findAll() {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findAll(Provincia.class))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findCiudades(String id) {
		try {
			Provincia provincia=new Provincia();
			provincia.setID(new Long(id));
			
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findByEqualAttribute(Ciudad.class, "provincia", provincia))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}
}
