package com.tbs.service.client.services;

import java.util.ArrayList;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.tbs.service.SecurityToken;
import com.tbs.service.exposition.rest.SecurityService;
import com.tbs.service.persistence.entities.Token;

import junit.framework.Assert;

public class SecurityServiceTestCase {
	

	@Test
	public void passUserIncorrect() {
		ArrayList list = new ArrayList();
		list.add(new JacksonJsonProvider());

		SecurityService store = JAXRSClientFactory
				.create("http://localhost:8080/lumo-360/services",
						SecurityService.class, list);

		SecurityToken token = new SecurityToken();
		token.setUser("gaston");
		token.setPass("gaston");
		javax.ws.rs.core.Response response = store.token(token);
		
		Assert.assertEquals(204, response.getStatus());
	}
	
	@Test
	public void changePassword(){
		ArrayList list = new ArrayList();
		list.add(new JacksonJsonProvider());

		SecurityService store = JAXRSClientFactory
				.create("http://localhost:8080/lumo-360/services",
						SecurityService.class, list);
		
		SecurityToken securityToken = new SecurityToken();
		securityToken.setUser("admin");
		securityToken.setPass("test");
		securityToken.setNewPassword("admin");
		javax.ws.rs.core.Response response = store.changePassword(securityToken);
		
		Assert.assertEquals(200, response.getStatus());
	}
}
