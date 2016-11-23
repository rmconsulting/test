package com.tbs.service.exposition.rest;

import javax.ws.rs.core.Response;

public class VersionServiceImpl implements VersionService {

	@Override
	public Response current() {
		try {
			return javax.ws.rs.core.Response.ok("V 1.2").build();
		} catch (Exception e) {
			return javax.ws.rs.core.Response.serverError().build();
		}
	}
}
