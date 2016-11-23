package com.tbs.service;

import java.io.Serializable;

import com.tbs.service.dto.UserDTO;
import com.tbs.service.persistence.entities.Token;
import com.tbs.service.persistence.entities.User;

public class UserToken implements Serializable {

	private static final long serialVersionUID = -431416317388740342L;

	private UserDTO user;

	private Token token;

	public UserToken(UserDTO user, Token token) {
		this.user = user;
		this.token = token;
	}

	public UserDTO getUser() {
		return user;
	}

	public Token getToken() {
		return token;
	}

}
