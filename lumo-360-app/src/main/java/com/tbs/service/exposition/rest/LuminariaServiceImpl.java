/**
 * 
 */
package com.tbs.service.exposition.rest;

import java.util.Date;

import javax.ws.rs.core.Response;

import com.tbs.service.ContextProvider;
import com.tbs.service.DataService;
import com.tbs.service.persistence.entities.Controlador;
import com.tbs.service.persistence.entities.Luminaria;
import com.tbs.service.persistence.entities.Token;
import com.tbs.service.persistence.spring.PersistenceService;

/**
 * @author sgcoco
 * 
 */

public class LuminariaServiceImpl implements LuminariaService {

	public javax.ws.rs.core.Response findAll() {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findAll(Luminaria.class))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response findAvailables() {
		try {
			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findByQuery(Luminaria.class, "controlador is null")).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	public javax.ws.rs.core.Response findByID(Long id) {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findByID(Luminaria.class, id))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response save(Luminaria luminaria) {
		try {
			Luminaria nuevaLuminaria = (Luminaria) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(luminaria);
			return javax.ws.rs.core.Response.ok(nuevaLuminaria).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response update(Luminaria luminaria) {
		try {
			Luminaria nuevaLuminaria = (Luminaria) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(luminaria);
			return javax.ws.rs.core.Response.ok(nuevaLuminaria).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response remove(Luminaria luminaria) {
		try {
			luminaria.setFechaBorrado(new Date());
//			luminaria.setControlador(null);
			Luminaria nuevaLuminaria = (Luminaria) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(luminaria);
			return javax.ws.rs.core.Response.ok(nuevaLuminaria).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findPagination(Long from, Long limit) {
		try {
			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findPagination(Luminaria.class, from, limit)).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findPaginationByControlador(Long controladorId, Long from, Long limit) {
		try {
			Controlador controlador = new Controlador();
			controlador.setID(new Long(controladorId));

			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findPaginationBy(Luminaria.class, from, limit, "controlador", controlador)).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response add2Controlador(Luminaria luminaria, Long idControlador) {
		try {
			//PuntoLuz puntoDeLuz = ContextProvider.getInstance().lookup(PersistenceService.class).findByID(PuntoLuz.class, idPunto);
			Controlador controlador = new Controlador();
			controlador.setID(new Long(idControlador));
			luminaria.setControlador(controlador);
			Luminaria nuevaLuminaria = (Luminaria) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(luminaria);
			return javax.ws.rs.core.Response.ok(nuevaLuminaria).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}
	
	@Override
	public Response on(Long id, Token token) {
		System.out.println("Encendiendo luminaria " + id + " token: " + token.getID() + token.getUuid() + " user:" + token.getUserID());
		return javax.ws.rs.core.Response.ok(new DataService("On " + id)).build();
	}

	@Override
	public Response off(Long id, Token token) {
		System.out.println("Apagando luminaria " + id + " token: " + token.getID() + token.getUuid() + " user:" + token.getUserID());
		return javax.ws.rs.core.Response.ok(new DataService("Off " + id)).build();
	}

	@Override
	public Response dimmer(Long id, Token token) {
		System.out.println("Regulo intensidad luminaria " + id + " token: " + token.getID() + token.getUuid() + " user:" + token.getUserID());
		return javax.ws.rs.core.Response.ok(new DataService("Dimmer  " + id)).build();
	}

}
