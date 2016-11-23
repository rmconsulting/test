package com.tbs.service.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;

import com.tbs.service.persistence.PersistibleImpl;

/**
 * Representa un diseniador.
 * 
 * @author sgcoco
 */
@Entity
@Table(name = "distrito")
public class Distrito extends PersistibleImpl {

	private static final long serialVersionUID = -7350612311663720277L;

	@Id
	@GeneratedValue
	@Column(name = "distrito_id")
	protected Long id;

	private String nombre;
	private String topx;
	private String topy;
	private String bottomx;
	private String bottomy;

	 @ManyToOne
	 @JoinColumn(columnDefinition="integer", name="ciudad_id")
	private Ciudad ciudad;
	 
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "distrito_id", referencedColumnName = "distrito_id")
	@Fetch(FetchMode.SUBSELECT)
	@Where(clause = "fechaBorrado is null")
	private List<Concentrador> concentradores = new ArrayList<Concentrador>();

	
	public Distrito() {
	}
	
	public Distrito(String nombre) {
		setNombre(nombre);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTopx() {
		return topx;
	}

	public void setTopx(String topx) {
		this.topx = topx;
	}

	public String getTopy() {
		return topy;
	}

	public void setTopy(String topy) {
		this.topy = topy;
	}

	public String getBottomx() {
		return bottomx;
	}

	public void setBottomx(String bottomx) {
		this.bottomx = bottomx;
	}

	public String getBottomy() {
		return bottomy;
	}

	public void setBottomy(String bottomy) {
		this.bottomy = bottomy;
	}
	
	public Distrito addConcentrador(Concentrador concentrador) {
		this.concentradores.add(concentrador);
		return this;
	}
//
	public List<Concentrador> getConcentradores() {
		return concentradores;
	}

	public void setConcentradores(List<Concentrador> concentradores) {
		this.concentradores = concentradores;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "[" + id + " - " + nombre + "}]";
	}

	public void setCuadrante(String topx, String topy, String bottomx,
			String bottomy) {
		setTopx(topx);
		setTopy(topy);
		setBottomx(bottomx);
		setBottomy(bottomy);
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
