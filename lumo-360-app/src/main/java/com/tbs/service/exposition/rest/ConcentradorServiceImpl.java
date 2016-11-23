/**
 * 
 */
package com.tbs.service.exposition.rest;

import java.util.Date;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;

import com.tbs.service.ContextProvider;
import com.tbs.service.persistence.entities.Distrito;
import com.tbs.service.persistence.entities.Luminaria;
import com.tbs.service.persistence.entities.PuntoLuz;
import com.tbs.service.persistence.entities.Concentrador;
import com.tbs.service.persistence.spring.PersistenceService;

/**
 * @author sgcoco
 * 
 */

public class ConcentradorServiceImpl implements ConcentradorService {

	private Logger logger = Logger.getLogger("ConcentradorService");

	public javax.ws.rs.core.Response findAll() {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findAll(Concentrador.class))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response findAvailables() {
		try {
			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findByQuery(Concentrador.class, "distrito_id is null")).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}
	
	public javax.ws.rs.core.Response findByID(Long id) {
		try {
			Concentrador concentrador = ContextProvider.getInstance().lookup(PersistenceService.class).findByID(Concentrador.class, id);
			return javax.ws.rs.core.Response
					.ok(concentrador)
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findPagination(Long from, Long limit) {
		try {
			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findPagination(Concentrador.class, from, limit)).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response save(Concentrador concentrador) {
		try {
			Concentrador nuevoConcentrador = (Concentrador) ContextProvider.getInstance()
					.lookup(PersistenceService.class).saveOrUpdate(concentrador);
			return javax.ws.rs.core.Response.ok(nuevoConcentrador).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response update(Concentrador concentrador) {
		try {
			Concentrador nuevoConcentrador = (Concentrador) ContextProvider.getInstance()
					.lookup(PersistenceService.class).saveOrUpdate(concentrador);
			return javax.ws.rs.core.Response.ok(nuevoConcentrador).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response remove(Concentrador concentrador) {
		try {
			concentrador.setFechaBorrado(new Date());
			Concentrador nuevoConcentrador = (Concentrador) ContextProvider.getInstance()
					.lookup(PersistenceService.class).saveOrUpdate(concentrador);

			int rows = ContextProvider.getInstance().lookup(PersistenceService.class).nativeNull("updatePuntoLuz",concentrador.getID());
			logger.info("Actualizando " + rows + " luminarias de " + concentrador.getID() + "a Null");

			return javax.ws.rs.core.Response.ok(nuevoConcentrador).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}
	
	@Override
	public Response on(Long id) {
		return javax.ws.rs.core.Response.ok("On " + id).build();
	}
	@Override
	public Response off(Long id) {
		return javax.ws.rs.core.Response.ok("Off " + id).build();
	}
	@Override
	public Response dimmer(Long id) {
		return javax.ws.rs.core.Response.ok("Dimmer  " + id).build();
	}
}
