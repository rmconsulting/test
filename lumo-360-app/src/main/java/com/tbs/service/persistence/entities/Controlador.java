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
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.ws.rs.QueryParam;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;

import com.tbs.service.persistence.PersistibleImpl;

/**
 * 
 * @author sgcoco
 */
@Entity
@Table(name = "controlador")
public class Controlador extends PersistibleImpl {

	private static final long serialVersionUID = 8476803052975051581L;

	@Id
	@GeneratedValue
	@Column(name = "controlador_id")
	protected Long id;

	private String nombre;

	 @ManyToOne
	 @JoinColumn(columnDefinition="integer", name="puntoluz_id")
	private PuntoLuz puntoLuz;

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinColumn(name = "controlador_id", referencedColumnName =
	// "controlador_id")
	// @Fetch(FetchMode.SUBSELECT)
	// @Where(clause = "fechaBorrado is null")
	// private List<Luminaria> luminarias = new ArrayList<Luminaria>();

	public Controlador() {

	}

	public Controlador(String nombre) {
		setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// public List<Luminaria> getLuminarias() {
	// return luminarias;
	// }
	//
	// public void setLuminarias(List<Luminaria> luminarias) {
	// this.luminarias = luminarias;
	// }

	// public Concentrador addLuminaria(Luminaria luminaria) {
	// this.luminarias.add(luminaria);
	// return this;
	// }

	
	@Override
	public Long getID() {
		return this.id;
	}

	public PuntoLuz getPuntoLuz() {
		return puntoLuz;
	}

	public void setPuntoLuz(PuntoLuz puntoLuz) {
		this.puntoLuz = puntoLuz;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "[" + id + " - " + nombre;// + "{#luminarias:" +
											// luminarias.size() + "}]";
	}

}
