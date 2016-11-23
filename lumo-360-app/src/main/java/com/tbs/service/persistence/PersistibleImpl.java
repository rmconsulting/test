package com.tbs.service.persistence;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Clase base para persistir, se marca la fecha de la creacion en la base del
 * objeto. Y la fecha de la ultima modificacion;
 * 
 * 
 * @author sgcoco
 */
@MappedSuperclass
public abstract class PersistibleImpl implements Persistible {

	private static final long serialVersionUID = -1755086873409044652L;

	protected Date fechaCreacion;

	protected Date fechaModificacion;

	protected Date fechaBorrado;

	protected String iconName;

	public abstract Long getID();

	public abstract void setID(Long id);

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean stored() {
		return getID() != null;
	}

	public Date getFechaBorrado() {
		return fechaBorrado;
	}

	public void setFechaBorrado(Date fechaBorrado) {
		this.fechaBorrado = fechaBorrado;
	}

	/**
	 * Si no tiene seteado una imagen de icono se agrega una imagen
	 * transparente. Esto es para que todas las entidades puedan tener un icono
	 * sin necesidad de que sea algo por entidad.
	 */
	public String getIconName() {
		if (iconName != null && iconName.trim().length() > 0) {
			return iconName;
		}
		return "noicon";
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

}
