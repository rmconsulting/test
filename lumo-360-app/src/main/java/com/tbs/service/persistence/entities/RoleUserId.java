package com.tbs.service.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleUserId implements Serializable {
	@Column(name = "role_ciudad_id")
	protected Long roleCiudad;

	@Column(name = "user_id")
	protected Long userId;

	public Long getRoleCiudad() {
		return roleCiudad;
	}

	public void setRoleCiudad(Long roleCiudad) {
		this.roleCiudad = roleCiudad;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
