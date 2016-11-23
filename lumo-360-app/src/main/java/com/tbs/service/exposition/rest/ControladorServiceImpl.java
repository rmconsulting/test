/**
 * 
 */
package com.tbs.service.exposition.rest;

import java.util.Date;

import javax.ws.rs.core.Response;

import com.tbs.service.ContextProvider;
import com.tbs.service.DataService;
import com.tbs.service.persistence.entities.Concentrador;
import com.tbs.service.persistence.entities.Controlador;
import com.tbs.service.persistence.entities.PuntoLuz;
import com.tbs.service.persistence.entities.Token;
import com.tbs.service.persistence.spring.PersistenceService;

/**
 * @author sgcoco
 * 
 */

public class ControladorServiceImpl implements ControladorService {

	public javax.ws.rs.core.Response findAll() {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findAll(Controlador.class))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response findAvailables() {
		try {
			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findByQuery(Controlador.class, "puntoluz_id is null")).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	public javax.ws.rs.core.Response findByID(Long id) {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findByID(Controlador.class, id))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response save(Controlador controlador) {
		try {
			Controlador nuevaControlador = (Controlador) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(controlador);
			return javax.ws.rs.core.Response.ok(nuevaControlador).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response update(Controlador controlador) {
		try {
			Controlador nuevaControlador = (Controlador) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(controlador);
			return javax.ws.rs.core.Response.ok(nuevaControlador).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response remove(Controlador controlador) {
		try {
			controlador.setFechaBorrado(new Date());
//			controlador.setControlador(null);
			Controlador nuevaControlador = (Controlador) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(controlador);
			return javax.ws.rs.core.Response.ok(nuevaControlador).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findPagination(Long from, Long limit) {
		try {
			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findPagination(Controlador.class, from, limit)).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findPaginationByPuntoLuz(Long concentradorID, Long from, Long limit) {
		try {
			PuntoLuz puntoLuz = new PuntoLuz();
			puntoLuz.setID(new Long(concentradorID));

			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findPaginationBy(Controlador.class, from, limit, "puntoLuz", puntoLuz)).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response add2PuntoLuz(Controlador controlador, Long idPuntoLuz) {
		try {
			//PuntoLuz puntoDeLuz = ContextProvider.getInstance().lookup(PersistenceService.class).findByID(PuntoLuz.class, idPunto);
			PuntoLuz puntoDeLuz = new PuntoLuz();
			puntoDeLuz.setID(new Long(idPuntoLuz));
			controlador.setPuntoLuz(puntoDeLuz);
			Controlador nuevoControlador = (Controlador) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(controlador);
			return javax.ws.rs.core.Response.ok(nuevoControlador).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}
	
	@Override
	public Response on(Long id, Token token) {
		System.out.println("Encendiendo controlador " + id + " token: " + token.getID() + token.getUuid() + " user:" + token.getUserID());
		return javax.ws.rs.core.Response.ok(new DataService("On " + id)).build();
	}

	@Override
	public Response off(Long id, Token token) {
		System.out.println("Apagando controlador " + id + " token: " + token.getID() + token.getUuid() + " user:" + token.getUserID());
		return javax.ws.rs.core.Response.ok(new DataService("Off " + id)).build();
	}

	@Override
	public Response dimmer(Long id, Token token) {
		System.out.println("Regulo intensidad controlador " + id + " token: " + token.getID() + token.getUuid() + " user:" + token.getUserID());
		return javax.ws.rs.core.Response.ok(new DataService("Dimmer  " + id)).build();
	}

	

}
