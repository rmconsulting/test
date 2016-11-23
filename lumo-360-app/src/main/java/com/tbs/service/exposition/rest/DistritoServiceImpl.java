package com.tbs.service.exposition.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.tbs.service.ContextProvider;
import com.tbs.service.persistence.entities.Distrito;
import com.tbs.service.persistence.entities.Concentrador;
import com.tbs.service.persistence.spring.PersistenceService;

public class DistritoServiceImpl implements DistritoService {
	public javax.ws.rs.core.Response findAll() {
		try {
			return javax.ws.rs.core.Response.ok(
					ContextProvider.getInstance()
							.lookup(PersistenceService.class)
							.findAll(Distrito.class)).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	public javax.ws.rs.core.Response findByID(String id) {
		try {
			if ("new".equals(id)) {
				return javax.ws.rs.core.Response.ok(new Distrito()).build();
			} else {
				return javax.ws.rs.core.Response.ok(
						ContextProvider.getInstance()
								.lookup(PersistenceService.class)
								.findByID(Distrito.class, new Long(id)))
						.build();
			}
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public javax.ws.rs.core.Response save(Distrito distrito) {
		try {
//			distrito = updateConcentradores(distrito);

			Distrito nuevoDistrito = (Distrito) ContextProvider.getInstance()
					.lookup(PersistenceService.class).saveOrUpdate(distrito);
			return javax.ws.rs.core.Response.ok(nuevoDistrito).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

//	private Distrito updateConcentradores(Distrito distrito) {
//		
//		List<Concentrador> concentradores = new ArrayList<Concentrador>();
//		for (Concentrador e : distrito.getConcentradores()) {
//			concentradores.add(ContextProvider.getInstance()
//					.lookup(PersistenceService.class)
//					.findByID(Concentrador.class, e.getID()));
//		}
//
//		distrito.setConcentradores(concentradores);
//		return distrito;
//	}

	@Override
	public javax.ws.rs.core.Response update(Distrito distrito) {
		try {
//			distrito = updateConcentradores(distrito);

			Distrito nuevoDistrito = (Distrito) ContextProvider.getInstance()
					.lookup(PersistenceService.class).saveOrUpdate(distrito);
			return javax.ws.rs.core.Response.ok(nuevoDistrito).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	/**
	 * No borra sino que actualiza el estado de la fecha de borrado.
	 */
	@Override
	public Response remove(Distrito distrito) {
		try {
//			distrito = updateConcentradores(distrito);
			distrito.setFechaBorrado(new Date());
			Distrito nuevoSector = (Distrito) ContextProvider.getInstance()
					.lookup(PersistenceService.class).saveOrUpdate(distrito);
			return javax.ws.rs.core.Response.ok(nuevoSector).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
		// try {
		// sector = updateSectores(sector);
		//
		// Designer nuevoSector = (Designer) ContextProvider.getInstance()
		// .lookup(PersistenceService.class).delete(sector);
		// return javax.ws.rs.core.Response.ok(nuevoSector).build();
		// } catch (Exception e) {
		// return javax.ws.rs.core.Response.serverError().build();
		// }
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
