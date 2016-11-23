package com.tbs.service.persistence.entities;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tbs.service.persistence.PersistibleImpl;

@Entity
@Table(name = "role_ciudad")
public class RoleByCity extends PersistibleImpl {

	@Id
	@GeneratedValue
	@Column(name = "role_ciudad_id")
	protected Long id;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "ciudad_id")
	private Ciudad ciudad;

	
	public RoleByCity(){
		
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public Long getID() {
		return this.id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

}
