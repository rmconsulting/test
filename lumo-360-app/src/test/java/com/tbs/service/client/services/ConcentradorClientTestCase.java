package com.tbs.service.client.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import com.tbs.service.exposition.rest.ConcentradorService;
import com.tbs.service.persistence.entities.Concentrador;

public class ConcentradorClientTestCase {

	@Test
	public void findAll() {
		ArrayList list = new ArrayList();
		list.add(new JacksonJsonProvider());
		
		ConcentradorService store = JAXRSClientFactory
				.create("http://localhost:8080/encuestas/services",
						ConcentradorService.class, list);

		javax.ws.rs.core.Response response = store.findAll();
		ObjectMapper mapper = new ObjectMapper();

		try {
			List<Concentrador> lista = mapper.readValue(
					response.readEntity(String.class),
					new TypeReference<List<Concentrador>>() {
					});

			for (Concentrador sector : lista) {
				System.out.println(sector);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void findByID() {
		ArrayList list = new ArrayList();
		list.add(new JacksonJsonProvider());
		
		ConcentradorService store = JAXRSClientFactory
				.create("http://localhost:8080/encuestas/services",
						ConcentradorService.class, list);

		javax.ws.rs.core.Response response = store.findByID(new Long("2"));
		ObjectMapper mapper = new ObjectMapper();

		try {
			Concentrador sector = mapper.readValue(response.readEntity(String.class),
					Concentrador.class);

			System.out.println(sector);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void save() {
		ArrayList list = new ArrayList();
		list.add(new JacksonJsonProvider());
		
		ConcentradorService store = JAXRSClientFactory
				.create("http://localhost:8080/encuestas/services",
						ConcentradorService.class, list);

		Concentrador nuevo = new Concentrador();
		nuevo.setNombre("Concentrador1");
		
		javax.ws.rs.core.Response response = store.save(nuevo);
		ObjectMapper mapper = new ObjectMapper();

		try {
			Concentrador sector = mapper.readValue(response.readEntity(String.class),
					Concentrador.class);

			System.out.println(sector);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void update(){
		ArrayList list = new ArrayList();
		list.add(new JacksonJsonProvider());
		
		ConcentradorService store = JAXRSClientFactory
				.create("http://localhost:8080/encuestas/services",
						ConcentradorService.class, list);

		javax.ws.rs.core.Response response = store.findByID(new Long("7"));
		ObjectMapper mapper = new ObjectMapper();

		try {
			Concentrador sector = mapper.readValue(response.readEntity(String.class),
					Concentrador.class);

			sector.setNombre("NUEVO");
			
			response=store.update(sector);
			
			Concentrador news=mapper.readValue(response.readEntity(String.class),
					Concentrador.class);
			
			System.out.println(news);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
