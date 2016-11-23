package com.tbs.service.persistence.entities;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tbs.service.ContextProvider;
import com.tbs.service.persistence.Persistible;
import com.tbs.service.persistence.spring.PersistenceService;

public class DataSet {

	
	private static void doPersistence(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		// PersistenceService service = ctx.getBean(PersistenceService.class);
		PersistenceService service = ContextProvider.getInstance().lookup(PersistenceService.class);

		createProfiles(service);
		
		createRoles(service);

		Pais pais = new Pais("Argentina");
		pais.setCodigoPais("AR");
		pais = (Pais) service.saveOrUpdate(pais);

		Pais all = new Pais("all");
		all.setCodigoPais(" ");
		all = (Pais) service.saveOrUpdate(all);
		Provincia estadoall = new Provincia("all");
		estadoall.setPais(all);
		estadoall = (Provincia) service.saveOrUpdate(estadoall);
		Ciudad ciudadall = new Ciudad("all");
		ciudadall.setProvincia(estadoall);
		service.saveOrUpdate(ciudadall);

		Pais peru = new Pais("Peru");
		peru.setCodigoPais("PR");
		peru = (Pais) service.saveOrUpdate(peru);

		Provincia estado = new Provincia("Buenos Aires");
		estado.setPais(pais);
		estado = (Provincia) service.saveOrUpdate(estado);

		service.saveOrUpdate(new Provincia("Mendoza", pais));
		service.saveOrUpdate(new Provincia("San Luis", pais));
		service.saveOrUpdate(new Provincia("Corrientes", pais));
		service.saveOrUpdate(new Provincia("Tucuman", pais));
		service.saveOrUpdate(new Provincia("Entre Rios", pais));

		Ciudad ciudad = new Ciudad("Capital Federal");
		ciudad.setProvincia(estado);
		ciudad = (Ciudad) service.saveOrUpdate(ciudad);

		ciudad = new Ciudad("Mendoza");
		ciudad.setProvincia(estado);
		ciudad = (Ciudad) service.saveOrUpdate(ciudad);
		ciudad = new Ciudad("Miramar");
		ciudad.setProvincia(estado);
		ciudad = (Ciudad) service.saveOrUpdate(ciudad);

		Distrito distrito = new Distrito("Almagro");
		distrito.setCiudad(ciudad);
		distrito = (Distrito) service.saveOrUpdate(distrito);

		Distrito distrito1 = new Distrito("Centro");
		distrito1.setCiudad(findCiudad(service, "Capital Federal"));
		Concentrador concentrador1 = new Concentrador("Concentrador1");
		concentrador1 = (Concentrador) service.saveOrUpdate(concentrador1);
		Concentrador concentrador2 = new Concentrador("Concentrador2");
		concentrador2 = (Concentrador) service.saveOrUpdate(concentrador2);
		Concentrador concentrador3 = new Concentrador("Concentrador3");
		concentrador3 = (Concentrador) service.saveOrUpdate(concentrador3);

		for (int i = 4; i < 18; i++) {
			service.saveOrUpdate(new Concentrador("concentrador" + i));
		}

		distrito1.addConcentrador(concentrador1);
		distrito1 = (Distrito) service.saveOrUpdate(distrito1);

		PuntoLuz puntoLuz1 = new PuntoLuz("puntoluz-1", "lat11", "long11");
		puntoLuz1.setConcentrador(concentrador1);
		puntoLuz1 = (PuntoLuz) service.saveOrUpdate(puntoLuz1);

		Controlador controlador1 = new Controlador("controlador1");
		controlador1.setPuntoLuz(puntoLuz1);
		controlador1 = (Controlador) service.saveOrUpdate(controlador1);
		for (int i = 1; i < 7; i++) {
			Luminaria luminaria = new Luminaria("luminaria-" + i, "cont-1");
			luminaria.setControlador(controlador1);
			service.saveOrUpdate(luminaria);
		}

		PuntoLuz puntoLuz2 = new PuntoLuz("puntoluz-2", "lat11", "long11");
		puntoLuz2.setConcentrador(concentrador1);
		puntoLuz2 = (PuntoLuz) service.saveOrUpdate(puntoLuz2);

		Controlador controlador2 = new Controlador("controlador2");
		controlador2.setPuntoLuz(puntoLuz2);
		controlador2 = (Controlador) service.saveOrUpdate(controlador2);

		for (int i = 8; i < 13; i++) {
			Luminaria luminaria = new Luminaria("luminaria-" + i, "cont-2");
			luminaria.setControlador(controlador2);
			service.saveOrUpdate(luminaria);
		}

		Controlador controlador3 = new Controlador("controlador3");
		controlador3.setPuntoLuz(puntoLuz2);
		controlador3 = (Controlador) service.saveOrUpdate(controlador3);
		for (int i = 14; i < 18; i++) {
			Luminaria luminaria = new Luminaria("luminaria-" + i, "cont-3");
			luminaria.setControlador(controlador3);
			service.saveOrUpdate(luminaria);
		}


		createUsers(service);
		
		ctx.close();
	}
	
	private static void createProfiles(PersistenceService service){
		Profile profile = new Profile();
		profile.setNombre("Humano");
		profile.setDiasExpiracionCuenta(90);
		profile.setDiasExpiracionPassword(30);
		
		service.saveOrUpdate(profile);
		
		profile = new Profile();
		profile.setNombre("Sistema");
		service.saveOrUpdate(profile);
		
	}
	
	private static void createUsers(PersistenceService service) {
		User user = new User();
		user.setApellido("Admin");
		user.setEmail("santiagococo@gmail.com");
		user.setName("Admins");
		user.setUsername("admin");
		user.setCryptPassword("admin");
		user.setProfile(service.findByEqualAttribute(Profile.class, "nombre", "Sistema").get(0));
		user.setSuperUser(Boolean.TRUE);
		service.saveOrUpdate(user);

		user = new User();
		user.setApellido("Distritos");
		user.setEmail("santiagococo@gmail.com");
		user.setName("Administrador");
		user.setUsername("distrito");
		user.setCryptPassword("distrito");
		user.setProfile(service.findByEqualAttribute(Profile.class, "nombre", "Humano").get(0));
		RoleByCity role = new RoleByCity();
		role.setCiudad(findCiudad(service, "Mendoza"));
		role.setRole(findRole(service, "distrito_admin"));
		role = (RoleByCity) service.saveOrUpdate(role);
		user.addRoleByCity(role);
		service.saveOrUpdate(user);

		user = new User();
		user.setApellido("Concentradores");
		user.setEmail("santiagococo@gmail.com");
		user.setName("Administrador");
		user.setUsername("concentrador");
		user.setCryptPassword("concentrador");
		user.setProfile(service.findByEqualAttribute(Profile.class, "nombre", "Humano").get(0));
		role = new RoleByCity();
		role.setCiudad(findCiudad(service, "Mendoza"));
		role.setRole(findRole(service, "concentrador_admin"));
		role = (RoleByCity) service.saveOrUpdate(role);
		user.addRoleByCity(role);
		service.saveOrUpdate(user);

		user = new User();
		user.setApellido("Luminarias");
		user.setEmail("santiagococo@gmail.com");
		user.setName("Administrador");
		user.setUsername("luminaria");
		user.setCryptPassword("luminaria");
		user.setProfile(service.findByEqualAttribute(Profile.class, "nombre", "Humano").get(0));
		role = new RoleByCity();
		role.setCiudad(findCiudad(service, "Mendoza"));
		role.setRole(findRole(service, "luminaria_admin"));
		role = (RoleByCity) service.saveOrUpdate(role);
		user.addRoleByCity(role);
		service.saveOrUpdate(user);

		user = new User();
		user.setApellido("reporter");
		user.setEmail("santiagococo@gmail.com");
		user.setName("reporter");
		user.setUsername("reporter");
		user.setCryptPassword("1234");
		user.setProfile(service.findByEqualAttribute(Profile.class, "nombre", "Humano").get(0));
		role = new RoleByCity();
		role.setCiudad(findCiudad(service, "all"));
		role.setRole(findRole(service, "reporte_user"));
		role = (RoleByCity) service.saveOrUpdate(role);
		user.addRoleByCity(role);
		service.saveOrUpdate(user);
	}

	private static Role findRole(PersistenceService service, String name) {
		return service.findByEqualAttribute(Role.class, "name", name).get(0);
	}

	private static Ciudad findCiudad(PersistenceService service, String name) {
		return service.findByEqualAttribute(Ciudad.class, "nombre", name).get(0);
	}

	private static void createRoles(PersistenceService service) {
		Role rol = new Role();

//		rol = new Role();
//		rol.setName("ciudad_admin");
//		service.saveOrUpdate(rol);

		rol = new Role();
		rol.setName("opera_luminaria");
		service.saveOrUpdate(rol);
		
		rol = new Role();
		rol.setName("distrito_admin");
		service.saveOrUpdate(rol);

		rol = new Role();
		rol.setName("concentrador_admin");
		service.saveOrUpdate(rol);

		rol = new Role();
		rol.setName("puntoluz_admin");
		service.saveOrUpdate(rol);

		rol = new Role();
		rol.setName("controlador_admin");
		service.saveOrUpdate(rol);

		rol = new Role();
		rol.setName("luminaria_admin");
		service.saveOrUpdate(rol);

//		rol = new Role();
//		rol.setName("user_admin");
//		service.saveOrUpdate(rol);

		rol = new Role();
		rol.setName("reporte_user");
		service.saveOrUpdate(rol);
	}

	// private static void actions(PersistenceService service) {
	// Action action=new Action();
	// action.setIdentifier("luminaria_admin");
	// action.setDescription("Admin de luminarias");
	// service.saveOrUpdate(action);
	//

	//
	//
	// action=new Action();
	// action.setIdentifier("reporte_user");
	// action.setDescription("Usuario de reportes");
	// service.saveOrUpdate(action);
	//
	// action=new Action();
	// action.setIdentifier("admin_user");
	// action.setDescription("Admin de usuarios");
	// service.saveOrUpdate(action);
	//
	// }

	// private static Persistible createDistrito(String nombre,
	// List<Concentrador> concentradores) {
	// Distrito distrito = new Distrito();
	//
	// distrito.setNombre(nombre);
	//// distrito.setConcentradores(concentradores);
	// distrito.setCuadrante("-34.6157436", "-58.5734068", "-34.6157436",
	// "-58.5734068");
	//
	// return distrito;
	// }

	public static final Concentrador createConcentrador(String nombre, List<PuntoLuz> puntos) {
		Concentrador concentrador = new Concentrador();
		concentrador.setNombre(nombre);
		// Riobamba y peron -34.606976,-58.3933722
		// Mitre y rodriguez penia -34.6078557,-58.3908121
		// concentrador.setCuadrante("-34.606976", "-58.3933722", "-34.6078557",
		// "-58.3908121");

		// concentrador.setPuntosDeLuz(puntos);

		return concentrador;
	}
	
	private static void doTest() throws JsonProcessingException{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		// PersistenceService service = ctx.getBean(PersistenceService.class);
		PersistenceService service = ContextProvider.getInstance().lookup(PersistenceService.class);
		User usuario = service.findByID(User.class, new Long(2));
		
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		mapper.setVisibilityChecker(
				mapper.getSerializationConfig().getDefaultVisibilityChecker().withFieldVisibility(Visibility.ANY));
		
		System.out.println(mapper.writeValueAsString(usuario));
		
		ctx.close();
		System.exit(0);
	}
	
	
	public static void main(String[] args) {
		try{
		doPersistence();
//		doTest();
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	
}
