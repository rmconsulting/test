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

@Entity
@Table(name = "role")
public class Role extends PersistibleImpl {

	private static final long serialVersionUID = 2892158133957855784L;

	@Id
	@GeneratedValue
	@Column(name = "role_id")
	protected Long id;

	private String name;

	private String descripcion;

	// private Ciudad ciudad;

	// @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	// private List<User> users = new ArrayList<User>();

	// @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JoinTable(name = "action_role", joinColumns = {
	// @JoinColumn(name = "role_id", nullable = false) }, inverseJoinColumns = {
	// @JoinColumn(name = "action_id", nullable = false) })
	// private List<Action> acciones = new ArrayList<Action>();

	public Role() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public List<Action> getAcciones() {
	// return acciones;
	// }
	//
	// public void setAcciones(List<Action> acciones) {
	// this.acciones = acciones;
	// }

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public Long getID() {
		return this.id;
	}

	// public List<User> getUsers() {
	// return users;
	// }
	//
	// public void setUsers(List<User> users) {
	// this.users = users;
	// }

	@Override
	public void setID(Long id) {
		this.id = id;
	}

	// public void addAction(Action action) {
	// this.acciones.add(action);
	// }

}
