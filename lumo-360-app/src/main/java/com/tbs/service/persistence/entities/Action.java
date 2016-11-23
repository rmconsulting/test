package com.tbs.service.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.tbs.service.persistence.PersistibleImpl;

//@Entity
//@Table(name = "action")
public class Action extends PersistibleImpl {

	private static final long serialVersionUID = 7363359878150504216L;
//
//	@Id
//	@GeneratedValue
//	@Column(name = "action_id")
	protected Long id;

//	@Column(nullable = false)
	private String identifier;

	private String description;


//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "acciones")
	private List<Role> roles = new ArrayList<Role>();

	public Action() {

	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
