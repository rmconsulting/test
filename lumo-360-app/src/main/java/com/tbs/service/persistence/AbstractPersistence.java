package com.tbs.service.persistence;

import java.util.Date;

/**
 * Comportamiento comun a todos los objetos a persistir
 * 
 * @author sgcoco
 */
public abstract class AbstractPersistence {

	public void setData(Persistible data){
		if (data.getID() == null) {
			((PersistibleImpl) data).setFechaCreacion(new Date());
		}
		((PersistibleImpl) data).setFechaModificacion(new Date());
	}
}
