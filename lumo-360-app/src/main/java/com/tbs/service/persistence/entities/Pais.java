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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.tbs.service.persistence.PersistibleImpl;

@Entity
@Table(name = "pais")
public class Pais extends PersistibleImpl {
	@Id
	@GeneratedValue
	@Column(name = "pais_id")
	protected Long id;

	private String nombre;

	private String codigoPais;
	private String codigoTel;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "pais_id", referencedColumnName = "pais_id")
//	@Fetch(FetchMode.SUBSELECT)
//	@Where(clause = "fechaBorrado is null")
//	private List<Estado> estados = new ArrayList<Estado>();

	public Pais() {
		// TODO Auto-generated constructor stub
	}

	public Pais(String nombre) {
		setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoTel() {
		return codigoTel;
	}

	public void setCodigoTel(String codigoTel) {
		this.codigoTel = codigoTel;
	}

//	public List<Estado> getEstados() {
//		return estados;
//	}
//
//	public void setEstados(List<Estado> estados) {
//		this.estados = estados;
//	}

	@Override
	public Long getID() {
		return this.id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

}
