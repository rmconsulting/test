package com.tbs.service.persistence.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tbs.service.persistence.PersistibleImpl;
import com.tbs.utils.Utilities;

/**
 * Representa un diseniador.
 * 
 * @author sgcoco
 */
@Entity
@Table(name = "token")
public class Token extends PersistibleImpl {

	@Id
	@GeneratedValue
	@Column(name="token_id")
	protected Long id;
	
	private String userID;
	
	private String uuid;
	
	private Date expiracion;
	
	public Token() {
		this.uuid = UUID.randomUUID().toString();
		
		this.expiracion=Utilities.addMinutesToDate(30, new Date());
	}

	public String getUuid() {
		return uuid;
	}

	public String getUserID() {
		return userID;
	}

	public void setUser(String user) {
		this.userID = user;
	}

	public Date getExpiracion() {
		return expiracion;
	}

	@Override
	public void setID(Long id) {
		this.id=id;
	}
	
	@Override
	public Long getID() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return "[" + getUuid() + "-" + userID +"]";
	}

}
