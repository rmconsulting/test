package com.tbs.service.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tbs.service.persistence.PersistibleImpl;

@Entity
@Table(name = "profile")
public class Profile extends PersistibleImpl {

	@Id
	@GeneratedValue
	@Column(name = "profile_id")
	protected Long id;

	private String nombre;

	private int diasExpiracionCuenta = -1;

	private int diasExpiracionPassword = -1;

	private int cantidadUltimosPassword = 12;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDiasExpiracionCuenta() {
		return diasExpiracionCuenta;
	}

	public void setDiasExpiracionCuenta(int diasExpiracionCuenta) {
		this.diasExpiracionCuenta = diasExpiracionCuenta;
	}

	public int getDiasExpiracionPassword() {
		return diasExpiracionPassword;
	}

	public void setDiasExpiracionPassword(int diasExpiracionPassword) {
		this.diasExpiracionPassword = diasExpiracionPassword;
	}

	public int getCantidadUltimosPassword() {
		return cantidadUltimosPassword;
	}

	public void setCantidadUltimosPassword(int cantidadUltimosPassword) {
		this.cantidadUltimosPassword = cantidadUltimosPassword;
	}

	@Override
	public String toString() {
		return "[" + id + " - " + nombre + "}]";
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
