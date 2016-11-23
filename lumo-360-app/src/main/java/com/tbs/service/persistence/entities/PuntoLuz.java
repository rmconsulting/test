package com.tbs.service.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.tbs.service.persistence.PersistibleImpl;

@SqlResultSetMapping(name = "updateResult", columns = { @ColumnResult(name = "count") })
@NamedNativeQueries({
		@NamedNativeQuery(name = "updatePuntoLuz", query = "UPDATE puntoluz SET concentrador_id = NULL WHERE concentrador_id = ?", resultSetMapping = "updateResult") })
@Entity
@Table(name = "puntoluz")
public class PuntoLuz extends PersistibleImpl {

	@Id
	@GeneratedValue
	@Column(name = "puntoluz_id")
	protected Long id;

	 @ManyToOne
	 @JoinColumn(columnDefinition="integer", name="concentrador_id")
	private Concentrador concentrador;

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinColumn(name = "puntoluz_id", referencedColumnName = "puntoluz_id")
	// @Fetch(FetchMode.SUBSELECT)
	// @Where(clause = "fechaBorrado is null")
	// private List<Controlador> controladores = new ArrayList<Controlador>();

	private String nombre;

	private String latitud;
	private String longitud;

	public PuntoLuz() {

	}

	public PuntoLuz(String nombre, String latitud, String longitud) {
		setNombre(nombre);
		setLatitud(latitud);
		setLongitud(longitud);
	}

	// public List<Controlador> getControladores() {
	// return controladores;
	// }
	//
	// public void setControladores(List<Controlador> controladores) {
	// this.controladores = controladores;
	// }

	
	public String getNombre() {
		return nombre;
	}

	public Concentrador getConcentrador() {
		return concentrador;
	}

	public void setConcentrador(Concentrador concentrador) {
		this.concentrador = concentrador;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
