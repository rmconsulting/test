package com.tbs.service.exposition.rest;

import java.util.Date;

import javax.ws.rs.core.Response;

import com.tbs.service.ContextProvider;
import com.tbs.service.persistence.PersistibleImpl;
import com.tbs.service.persistence.entities.Luminaria;
import com.tbs.service.persistence.entities.Role;
import com.tbs.service.persistence.spring.PersistenceService;

public class RoleServiceImpl implements RoleService {

	@Override
	public Response findAll() {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findAll(Role.class))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findPagination(Long from, Long limit) {
		try {
			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findPagination(Role.class, from, limit)).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findByID(Long id) {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findByID(Role.class, id))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response save(Role role) {
		try {
			Role nuevoRol = (Role) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(role);
			return javax.ws.rs.core.Response.ok(nuevoRol).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response update(Role role) {
		try {
			Role nuevoRol = (Role) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(role);
			return javax.ws.rs.core.Response.ok(nuevoRol).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response remove(Role role) {
		try {
			role.setFechaBorrado(new Date());
			Role nuevoRol = (Role) ContextProvider.getInstance().lookup(PersistenceService.class)
					.saveOrUpdate(role);
			return javax.ws.rs.core.Response.ok(nuevoRol).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

}
