package com.tbs.service.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
	private Long id;

	private String name;

	private String username;

	private Boolean root;

	public UserDTO(Long id, String name, String username, Boolean root) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.root = root;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return this.username;
	}

	public String getName() {
		return name;
	}

	public Boolean isRoot() {
		return this.root;
	}
}
