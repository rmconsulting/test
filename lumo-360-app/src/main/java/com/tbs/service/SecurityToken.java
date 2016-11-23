package com.tbs.service;

import java.io.Serializable;

import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.tbs.utils.Utilities;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityToken implements Serializable {

	private static final long serialVersionUID = 6549695858513851592L;

	private String user;

	private String pass;

	@Transient
	private String newPassword;

	@Transient
	@JsonIgnore
	private String passwordVerify;

	public SecurityToken() {

	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return this.pass;
	}

	public String getCryptedPass() {
		return Utilities.getMD5(pass);
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
