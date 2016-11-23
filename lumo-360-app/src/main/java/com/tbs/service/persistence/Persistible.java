package com.tbs.service.persistence;

import java.io.Serializable;
import java.util.Date;


public interface Persistible extends Serializable{
	public Long getID();
	public Date getFechaCreacion();
	public Date getFechaModificacion();
	
	public String getIconName();
	public boolean stored();
	
}
