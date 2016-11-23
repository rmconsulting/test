package com.tbs.service;

import java.lang.reflect.Method;
import java.security.Principal;
import java.util.List;

import org.apache.cxf.common.util.ClassHelper;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.JAXRSInvoker;
import org.apache.cxf.jaxrs.client.spec.ClientRequestFilterInterceptor;
import org.apache.cxf.jaxrs.impl.SecurityContextImpl;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.attachment.AttachmentDeserializer;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

public class SecurityFilter extends ClientRequestFilterInterceptor {
	public SecurityFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleMessage(Message message) throws Fault {
//		message.get("path_to_match_slash")
	
		System.out.println("Query....: " + message.get(org.apache.cxf.message.Message.QUERY_STRING));
		System.out.println("Headers..: " + message.get(org.apache.cxf.message.Message.PROTOCOL_HEADERS));
		System.out.println("Request..: " + message.get("org.apache.cxf.request.uri"));
		System.out.println("Servicios: " + message.get(org.apache.cxf.message.Message.BASE_PATH));
		
		dumpMethod((Method) message.get("org.apache.cxf.resource.method"), message.getContent(java.util.List.class));
		
		super.handleMessage(message);
	}
	
	private void dumpMethod(Method method, List params){
		System.out.print("Metodo...: " + method.getName()+"( ");
		for (Class<?> parametro : method.getParameterTypes()) {
			System.out.print(parametro.getName() + " ");
		} 
		System.out.println(")");
		
	}
	// @Override
	// protected Object invoke(Exchange exchange, Object resourceObject, Method
	// method, List<Object> requestParams) {
	// OperationResourceInfo ori = exchange.get(OperationResourceInfo.class);
	// Method m = ori.getMethodToInvoke();
	// Class<?> realClass = ClassHelper.getRealClass(resourceObject);
	//
	// Principal p = new
	// SecurityContextImpl(exchange.getInMessage()).getUserPrincipal();
	// if (realClass == SecureBookStore.class &&
	// "getThatBook".equals(method.getName())
	// && "baddy".equals(p.getName())) {
	// return new
	// MessageContentsList(javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.FORBIDDEN).build());
	// }

	// return super.invoke(exchange, resourceObject, method, requestParams);
	// }

}
