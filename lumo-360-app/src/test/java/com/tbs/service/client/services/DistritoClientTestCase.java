package com.tbs.service.client.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import com.tbs.service.exposition.rest.DistritoService;
import com.tbs.service.persistence.entities.Distrito;
import com.tbs.service.persistence.entities.Ciudad;
import com.tbs.service.persistence.entities.Concentrador;

public class DistritoClientTestCase {

	@Test
	public void findAll() {
		ArrayList list = new ArrayList();
		list.add(new JacksonJsonProvider());
		
		DistritoService store = JAXRSClientFactory
				.create("http://localhost:8080/encuestas/services",
						DistritoService.class, list);

		javax.ws.rs.core.Response response = store.findAll();
		ObjectMapper mapper = new ObjectMapper();

		try {
			List<Distrito> lista = mapper.readValue(
					response.readEntity(String.class),
					new TypeReference<List<Distrito>>() {
					});

			for (Distrito d : lista) {
				System.out.println(d);
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
		
		DistritoService store = JAXRSClientFactory
				.create("http://localhost:8080/encuestas/services",
						DistritoService.class, list);

		javax.ws.rs.core.Response response = store.findByID("2");
		ObjectMapper mapper = new ObjectMapper();

		try {
			Distrito sector = mapper.readValue(response.readEntity(String.class),
					Distrito.class);

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
		
		DistritoService store = JAXRSClientFactory
				.create("http://localhost:8080/encuestas/services",
						DistritoService.class, list);

		Distrito nuevo = createDesigner("4444");
		nuevo.setCiudad(getCiudad(1));
//		
		javax.ws.rs.core.Response response = store.save(nuevo);
		ObjectMapper mapper = new ObjectMapper();

		try {
			Distrito sector = mapper.readValue(response.readEntity(String.class),
					Distrito.class);

			System.out.println(sector);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Ciudad getCiudad(int id) {
		Ciudad sector = new Ciudad();
		sector.setID(new Long(id));
		return sector;
	}

	private Distrito createDesigner(String text) {
		Distrito nuevo = new Distrito();
		
		return nuevo;
	}
	
	@Test
	public void update(){
		ArrayList list = new ArrayList();
		list.add(new JacksonJsonProvider());
		
		DistritoService store = JAXRSClientFactory
				.create("http://localhost:8080/encuestas/services",
						DistritoService.class, list);

		javax.ws.rs.core.Response response = store.findByID("9");
		ObjectMapper mapper = new ObjectMapper();

		try {
			Distrito distrito = mapper.readValue(response.readEntity(String.class),
					Distrito.class);

			distrito.setNombre("NUEVO");
			
			response=store.update(distrito);
			
			Distrito news=mapper.readValue(response.readEntity(String.class),
					Distrito.class);
			
			System.out.println(news);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
