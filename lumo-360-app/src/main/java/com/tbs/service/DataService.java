package com.tbs.service;

import java.io.Serializable;

public class DataService implements Serializable{

	private static final long serialVersionUID = -2001064519142823625L;

	private String status;
	
	public DataService(String status){
		this.status=status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
