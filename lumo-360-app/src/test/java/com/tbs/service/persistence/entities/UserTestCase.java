package com.tbs.service.persistence.entities;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.tbs.service.ContextProvider;
import com.tbs.service.persistence.spring.PersistenceService;

public class UserTestCase {
	
	@Test
	public void findAll() {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idCiudad", new Long(3));
		
		java.util.List<User> users = ContextProvider.getInstance().lookup(PersistenceService.class).findByNamedQuery(User.class, "findAllByCity", parameters);
		
		Assert.assertTrue(users.size()>1);
	
		
	}
	
	@Test
	public void findAllB() {
		String query = "select u from "+User.class.getName()+" u, " +RoleByCity.class.getName()+ " rc," +
				RoleUser.class.getName() +" ru" +
				" where u.fechaBorrado is null and rc.ciudad.id=3 and rc.id=ru.roleCiudad and ru.userId=u.id";

//		java.util.List<User> users = ContextProvider.getInstance().lookup(PersistenceService.class).findByNativeQuery(User.class, query);
//		System.out.println(users.size());
	}
}
