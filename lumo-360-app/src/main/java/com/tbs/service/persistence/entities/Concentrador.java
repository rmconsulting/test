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
 * Representa al sector de preferencia al que puede pertenecer por ejemplo un
 * diseniador
 * 
 * @see Distrito
 * @author sgcoco
 */
@Entity
@Table(name="concentrador")
public class Concentrador extends PersistibleImpl {

	private static final long serialVersionUID = 8476803052975051581L;

	@Id
	@GeneratedValue
	@Column(name = "concentrador_id")
	protected Long id;

//	 @ManyToOne
//	 @JoinColumn(columnDefinition="integer", name="distrito_id")
//	private Distrito distrito;
//	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "concentrador_id", referencedColumnName = "concentrador_id")
//	@Fetch(FetchMode.SUBSELECT)
//	@Where(clause = "fechaBorrado is null")
//	private List<PuntoLuz> puntosDeLuz = new ArrayList<PuntoLuz>();
	
	private String nombre;

	
	public Concentrador() {
		// TODO Auto-generated constructor stub
	}

	public Concentrador(String nombre) {
		setNombre(nombre);
	}
	
	
//	public List<PuntoLuz> getPuntosDeLuz() {
//		return puntosDeLuz;
//	}
//
//
//	public void setPuntosDeLuz(List<PuntoLuz> puntosDeLuz) {
//		this.puntosDeLuz = puntosDeLuz;
//	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	public List<Luminaria> getLuminarias() {
//		return luminarias;
//	}
//
//	public void setLuminarias(List<Luminaria> luminarias) {
//		this.luminarias = luminarias;
//	}

//	public Concentrador addLuminaria(Luminaria luminaria) {
//		this.luminarias.add(luminaria);
//		return this;
//	}

	

	
	@Override
	public Long getID() {
		return this.id;
	}

//	public Distrito getDistrito() {
//		return distrito;
//	}
//
//
//	public void setDistrito(Distrito distrito) {
//		this.distrito = distrito;
//	}


	@Override
	public void setID(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "[" + id + " - " + nombre;// + "{#luminarias:" + luminarias.size() + "}]";
	}

}
