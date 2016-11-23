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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.tbs.service.persistence.PersistibleImpl;

@Entity
@Table(name = "ciudad")
public class Ciudad extends PersistibleImpl {
	@Id
	@GeneratedValue
	@Column(name = "ciudad_id")
	protected Long id;

	private String nombre;

	private String latitud;

	private String longitud;
	@ManyToOne
	@JoinColumn(columnDefinition = "integer", name = "provincia_id")
	private Provincia provincia;
	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinColumn(name = "ciudad_id", referencedColumnName = "ciudad_id")
	// @Fetch(FetchMode.SUBSELECT)
	// @Where(clause = "fechaBorrado is null")
	// private List<Distrito> distritos = new ArrayList<Distrito>();

	public Ciudad() {
		// TODO Auto-generated constructor stub
	}

	public Ciudad(String nombre) {
		setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// public List<Distrito> getDistritos() {
	// return distritos;
	// }
	//
	// public void setDistritos(List<Distrito> distritos) {
	// this.distritos = distritos;
	// }

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
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
