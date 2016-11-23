package com.tbs.service.persistence.entities;

import java.util.List;

import org.junit.Test;

import com.tbs.service.ContextProvider;
import com.tbs.service.persistence.spring.PersistenceService;

public class LuminariaTestCase {

	@Test
	public void nativeNull(){
		ContextProvider.getInstance().lookup(PersistenceService.class).nativeNull("updateLuminaria", new Long(2));
	}
	
	@Test
	public void findPaginationBy(){
		Concentrador concentrador = new Concentrador();
		concentrador.setID(new Long(2));
		
		List<Luminaria> lista = ContextProvider.getInstance().lookup(PersistenceService.class)
				.findPaginationBy(Luminaria.class, new Long(1), new Long(10), "concentrador", concentrador);

		for (Luminaria luminaria : lista) {
			System.out.println(luminaria.getNombre());
		}
	}
}
