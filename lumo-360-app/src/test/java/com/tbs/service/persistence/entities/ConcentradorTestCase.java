package com.tbs.service.persistence.entities;

import java.util.List;

import org.junit.Test;

import com.tbs.service.ContextProvider;
import com.tbs.service.persistence.spring.PersistenceService;

public class ConcentradorTestCase {

	@Test
	public void findAll() {
		List<Concentrador> lista = ContextProvider.getInstance().lookup(PersistenceService.class)
				.findAll(Concentrador.class);

		for (Concentrador concentrador : lista) {
			System.out.println(concentrador.getNombre());// + " size:" +
															// concentrador.getLuminarias().size());
		}

	}
	@Test
	public void findAvailables() {
		List<Concentrador> lista = ContextProvider.getInstance().lookup(PersistenceService.class)
				.findByQuery(Concentrador.class, "distrito_id is null");
		
		for (Concentrador concentrador : lista) {
			System.out.println(concentrador.getNombre());// + " size:" +
															// concentrador.getLuminarias().size());
		}
	}

}
