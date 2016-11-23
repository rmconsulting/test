package com.tbs.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.tbs.service.persistence.entities.Concentrador;

public class ResponseJsonDeserializer extends JsonDeserializer<Response> {

	@Override
	public Response deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		    ObjectCodec oc = jp.getCodec();
	        JsonNode node = oc.readTree(jp);
	        ObjectMapper mapper = new ObjectMapper();
	        
	        
	        Response r = new Response();
//	        Collection<COrder> readValues = new ObjectMapper().readValue(jsonAsString, new TypeReference<Collection<COrder>>() { })
//	        r.status(Status.valueOf(node.get("status").asText()));
	        mapper.readValue(node.get("data"), new TypeReference<Response<List<Concentrador>>>() { });
	       
	        r.status(node.get("status").asInt());
	        return r;
	}

}
