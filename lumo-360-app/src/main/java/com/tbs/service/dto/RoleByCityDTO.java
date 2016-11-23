package com.tbs.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoleByCityDTO implements Serializable {
	private List<String> roles = new ArrayList<String>();

	public Long id;
	public String nombre;

	public RoleByCityDTO() {

	}

	public RoleByCityDTO(Long idCiudad, String nombre) {
		this.id = idCiudad;
		this.nombre = nombre;
	}

	public void addRole(String rol) {
		this.roles.add(rol);
	}

	

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
