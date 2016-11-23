package com.tbs.service.exposition.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;

import org.hibernate.exception.ConstraintViolationException;

import com.tbs.service.ContextProvider;
import com.tbs.service.dto.RoleByCityDTO;
import com.tbs.service.persistence.entities.Role;
import com.tbs.service.persistence.entities.RoleByCity;
import com.tbs.service.persistence.entities.User;
import com.tbs.service.persistence.spring.PersistenceService;

public class UserServiceImpl implements UserService {

	@Override
	public Response findAll() {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findAll(User.class)).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findAllByCity(String idCiudad) {
		try {
			if("all".equals(idCiudad.toLowerCase())){
				return findAll();
			}else{
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("idCiudad", new Long(idCiudad));
			
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findByNamedQuery(User.class, "findAllByCity", parameters)).build();
		}
		}catch(

	Exception e)
	{
		return javax.ws.rs.core.Response.serverError().build();
	}
	}

	@Override
	public javax.ws.rs.core.Response findCiudadesByID(Long id) {
		try {
			User user = ContextProvider.getInstance().lookup(PersistenceService.class).findByID(User.class, id);
			HashMap<Long, RoleByCityDTO> roles = new HashMap<Long, RoleByCityDTO>();

			for (RoleByCity role : user.getRoles()) {
				String textoCiudad = "";
				if ((role.getCiudad().getProvincia().getPais().getCodigoPais() != null)
						&& (role.getCiudad().getProvincia().getPais().getCodigoPais().trim().length() > 0)) {
					textoCiudad = ", " + role.getCiudad().getProvincia().getPais().getCodigoPais();
				} else {
					textoCiudad = "";
				}
				RoleByCityDTO rol = new RoleByCityDTO(role.getCiudad().getID(),
						role.getCiudad().getNombre() + textoCiudad);

				if (roles.containsKey(role.getCiudad().getID())) {
					RoleByCityDTO exists = roles.get(role.getCiudad().getID());
					exists.addRole(role.getRole().getName());
					roles.put(role.getCiudad().getID(), exists);
				} else {
					rol.addRole(role.getRole().getName());
					roles.put(role.getCiudad().getID(), rol);
				}
			}
			return Response.ok(roles.values()).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findPagination(Long from, Long limit) {
		try {
			return javax.ws.rs.core.Response.ok(ContextProvider.getInstance().lookup(PersistenceService.class)
					.findPagination(User.class, from, limit)).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response findByID(Long id) {
		try {
			return javax.ws.rs.core.Response
					.ok(ContextProvider.getInstance().lookup(PersistenceService.class).findByID(User.class, id))
					.build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response save(User user) {
		try {
			for (RoleByCity rol : user.getRoles()) {
				rol.setFechaCreacion(new Date());
			}
			user.setCryptPassword(user.getPassword());
			User nuevoUser = (User) ContextProvider.getInstance().lookup(PersistenceService.class).saveOrUpdate(user);

			return javax.ws.rs.core.Response.ok(nuevoUser).build();
		} catch (PersistenceException e) {
			if (ConstraintViolationException.class.isInstance(e.getCause())) {
				return javax.ws.rs.core.Response.status(Response.Status.CONFLICT).build();
			} else {
				return javax.ws.rs.core.Response.serverError().build();
			}
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response update(User user) {
		try {
			User nuevoUser = (User) ContextProvider.getInstance().lookup(PersistenceService.class).saveOrUpdate(user);
			return javax.ws.rs.core.Response.ok(nuevoUser).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

	@Override
	public Response remove(User user) {
		try {
			user.setFechaBorrado(new Date());
			User nuevoUser = (User) ContextProvider.getInstance().lookup(PersistenceService.class).saveOrUpdate(user);
			return javax.ws.rs.core.Response.ok(nuevoUser).build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}

}
