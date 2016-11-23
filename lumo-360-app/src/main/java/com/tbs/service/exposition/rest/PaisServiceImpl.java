package com.tbs.service.exposition.rest;

import javax.ws.rs.core.Response;

import com.tbs.service.ContextProvider;
import com.tbs.service.persistence.entities.Provincia;
import com.tbs.service.persistence.entities.Pais;
import com.tbs.service.persistence.spring.PersistenceService;

public class PaisServiceImpl implements PaisService{

	@Override
	public Response findAll() {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findAll(Pais.class))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findProvincias(String id) {
		try {
			Pais pais=new Pais();
			pais.setID(new Long(id));
			
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findByEqualAttribute(Provincia.class, "pais", pais))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

}
