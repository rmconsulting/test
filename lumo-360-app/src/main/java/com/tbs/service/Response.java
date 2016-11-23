package com.tbs.service;

import java.io.Serializable;

import javax.ws.rs.core.Response.Status;

/**
 * Wrapper para la respuesta
 * 
 * @author sgcoco
 *
 * @param <T>
 */
@org.codehaus.jackson.map.annotate.JsonSerialize(using = ResponseJsonSerializer.class)
@org.codehaus.jackson.map.annotate.JsonDeserialize(using = ResponseJsonDeserializer.class)
public class Response<T> implements Serializable{
	
	private static final long serialVersionUID = 7990032708136172122L;

	private T entity;
	
	private int status;
	
	public Response(){
		
	}
	
	public T getEntity(){
		return this.entity;
	}
	
	public Response data(T data){
		this.entity = data;
		return this;
	}
	
	public Response ok(T data){
		this.status = 200;
		this.entity = data;
		return this;
	}
	
	public Response fail(){
		this.status = 503;
		return this;
	}
	
	public Response status(int status){
		this.status=status;
		return this;
	}
	
	public int getStatus(){
		return this.status;
	}
	
	public boolean hasError(){
		return this.status!=200;
	}
}
