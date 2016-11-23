package com.tbs.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import com.tbs.service.exposition.rest.CiudadServiceImpl;
import com.tbs.service.exposition.rest.ConcentradorServiceImpl;
import com.tbs.service.exposition.rest.ControladorServiceImpl;
import com.tbs.service.exposition.rest.DistritoServiceImpl;
import com.tbs.service.exposition.rest.ProvinciaServiceImpl;
import com.tbs.service.exposition.rest.LuminariaServiceImpl;
import com.tbs.service.exposition.rest.PaisServiceImpl;
import com.tbs.service.exposition.rest.ProfileServiceImpl;
import com.tbs.service.exposition.rest.PuntoDeLuzServiceImpl;
import com.tbs.service.exposition.rest.RoleServiceImpl;
import com.tbs.service.exposition.rest.SecurityServiceImpl;
import com.tbs.service.exposition.rest.UserServiceImpl;
import com.tbs.service.exposition.rest.VersionServiceImpl;
import com.tbs.service.persistence.spring.PersistenceService;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class AdminApplication extends Application {
	HashSet<Object> singletons = new HashSet<Object>();

	public AdminApplication() {
		singletons.add(new PaisServiceImpl());
		singletons.add(new ProvinciaServiceImpl());
		singletons.add(new CiudadServiceImpl());
		singletons.add(new DistritoServiceImpl());
		singletons.add(new ConcentradorServiceImpl());
		singletons.add(new ProfileServiceImpl());
		singletons.add(new PuntoDeLuzServiceImpl());
		singletons.add(new ControladorServiceImpl());
		singletons.add(new LuminariaServiceImpl());
		singletons.add(new SecurityServiceImpl());
		singletons.add(new RoleServiceImpl());
		singletons.add(new UserServiceImpl());
		singletons.add(new VersionServiceImpl());
	}

	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<Class<?>>();
		return set;
	}

	@Override
	public Set<Object> getSingletons() {

		ContextProvider.getInstance().lookup(PersistenceService.class);

		ObjectMapper mapper = new ObjectMapper();
		AnnotationIntrospector primary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary, secondary);
		mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
		mapper.getSerializationConfig().setAnnotationIntrospector(pair);

		// Set up the provider
		JacksonJsonProvider jaxbProvider = new JacksonJsonProvider();
		jaxbProvider.setMapper(mapper);

		singletons.add(jaxbProvider);

		return singletons;
	}
}
