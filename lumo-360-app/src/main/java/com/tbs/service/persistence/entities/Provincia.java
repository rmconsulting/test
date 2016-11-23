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
@Table(name = "provincia")
public class Provincia extends PersistibleImpl {

	@Id
	@GeneratedValue
	@Column(name = "provincia_id")
	protected Long id;

	private String nombre;

	private String codigoProvincia;

	 @ManyToOne
	 @JoinColumn(columnDefinition="integer", name="pais_id")
	private Pais pais;
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "estado_id", referencedColumnName = "estado_id")
//	@Fetch(FetchMode.SUBSELECT)
//	@Where(clause = "fechaBorrado is null")
//	private List<Ciudad> ciudades = new ArrayList<Ciudad>();

	public Provincia() {
		// TODO Auto-generated constructor stub
	}

	public Provincia(String nombre) {
		setNombre(nombre);
	}
	public Provincia(String nombre, Pais pais) {
		setNombre(nombre);
		setPais(pais);
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

//	public List<Ciudad> getCiudades() {
//		return ciudades;
//	}
//
//	public void setCiudades(List<Ciudad> ciudades) {
//		this.ciudades = ciudades;
//	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
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