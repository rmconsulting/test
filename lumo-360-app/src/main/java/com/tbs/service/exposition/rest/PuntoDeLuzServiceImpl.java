/**
 * 
 */
package com.tbs.service.exposition.rest;

import java.util.Date;

import javax.ws.rs.core.Response;

import com.tbs.service.ContextProvider;
import com.tbs.service.DataService;
import com.tbs.service.persistence.entities.Concentrador;
import com.tbs.service.persistence.entities.PuntoLuz;
import com.tbs.service.persistence.entities.Token;
import com.tbs.service.persistence.spring.PersistenceService;

/**
 * @author sgcoco
 * 
 */

public class PuntoDeLuzServiceImpl implements PuntoDeLuzService {

	public javax.ws.rs.core.Response findAll() {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findAll(PuntoLuz.class)).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response findAvailables() {
		try {
			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findByQuery(PuntoLuz.class, "concentrador is null")).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	public javax.ws.rs.core.Response findByID(Long id) {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findByID(PuntoLuz.class, id))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response save(PuntoLuz puntoDeLuz) {
		try {
			PuntoLuz nuevaPuntoLuz = (PuntoLuz) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(puntoDeLuz);
			return javax.ws.rs.core.Response.ok(nuevaPuntoLuz).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response update(PuntoLuz puntoDeLuz) {
		try {
			PuntoLuz nuevaPuntoLuz = (PuntoLuz) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(puntoDeLuz);
			return javax.ws.rs.core.Response.ok(nuevaPuntoLuz).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response remove(PuntoLuz puntoDeLuz) {
		try {
			puntoDeLuz.setFechaBorrado(new Date());
			// puntoDeLuz.setControlador(null);
			PuntoLuz nuevaPuntoLuz = (PuntoLuz) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(puntoDeLuz);
			return javax.ws.rs.core.Response.ok(nuevaPuntoLuz).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findPagination(Long from, Long limit) {
		try {
			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findPagination(PuntoLuz.class, from, limit)).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findPaginationByConcentrador(Long concentradorID, Long from, Long limit) {
		try {
			Concentrador concentrador = new Concentrador();
			concentrador.setID(new Long(concentradorID));

			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findPaginationBy(PuntoLuz.class, from, limit, "concentrador", concentrador)).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response add2Concentrador(PuntoLuz puntoDeLuz, Long idConcentrador) {
		try {
			//PuntoLuz puntoDeLuz = ContextProvider.getInstance().lookup(PersistenceService.class).findByID(PuntoLuz.class, idPunto);
			Concentrador concentrador = new Concentrador();
			concentrador.setID(new Long(idConcentrador));
			puntoDeLuz.setConcentrador(concentrador);
			PuntoLuz nuevaPuntoLuz = (PuntoLuz) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(puntoDeLuz);
			return javax.ws.rs.core.Response.ok(nuevaPuntoLuz).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response on(Long id, Token token) {
		System.out.println("Encendiendo puntoDeLuz " + id + " token: " + token.getID() + token.getUuid() + " user:"
				+ token.getUserID());
		return javax.ws.rs.core.Response.ok(new DataService("On " + id)).build();
	}

	@Override
	public Response off(Long id, Token token) {
		System.out.println("Apagando puntoDeLuz " + id + " token: " + token.getID() + token.getUuid() + " user:"
				+ token.getUserID());
		return javax.ws.rs.core.Response.ok(new DataService("Off " + id)).build();
	}

	@Override
	public Response dimmer(Long id, Token token) {
		System.out.println("Regulo intensidad puntoDeLuz " + id + " token: " + token.getID() + token.getUuid()
				+ " user:" + token.getUserID());
		return javax.ws.rs.core.Response.ok(new DataService("Dimmer  " + id)).build();
	}

}
