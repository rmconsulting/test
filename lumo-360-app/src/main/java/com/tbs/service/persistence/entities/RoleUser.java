package com.tbs.service.persistence.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.tbs.service.persistence.PersistibleImpl;

@Entity
@Table(name = "role_user")
@JsonIgnoreProperties(ignoreUnknown = true)
@IdClass(value=RoleUserId.class)
public class RoleUser extends PersistibleImpl {
	
	@Id
	protected Long roleCiudad;

	@Id
	protected Long userId;

	@Override
	public Long getID() {
		return Long.valueOf(0);
	}

	@Override
	public void setID(Long id) {
		
	}
}
