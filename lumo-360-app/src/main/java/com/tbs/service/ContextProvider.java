package com.tbs.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tbs.service.persistence.spring.PersistenceService;

public class ContextProvider {

	private static ContextProvider instance;
	private ClassPathXmlApplicationContext ctx;
	
	public synchronized static final ContextProvider getInstance(){
			if(instance==null){
				instance = new ContextProvider();
			}
			return instance;
	}
	
	private ContextProvider(){
		 ctx = new ClassPathXmlApplicationContext(
				"classpath:/applicationContext.xml");
	}
	
	public <T> T lookup(Class<T> clase){
		return ctx.getBean(clase);
	}
}
