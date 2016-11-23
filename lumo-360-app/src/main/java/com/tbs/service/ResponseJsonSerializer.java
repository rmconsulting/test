package com.tbs.service;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;


public class ResponseJsonSerializer extends JsonSerializer<Response> {

    /**
     * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object,
     *      org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
     */
    @Override
    public void serialize(Response value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonProcessingException {
        
        jgen.writeStartObject();
        jgen.writeStringField("data", new ObjectMapper().writeValueAsString(value.getEntity()));
        jgen.writeStringField("status", new ObjectMapper().writeValueAsString(value.getStatus()));
        jgen.writeEndObject();

    }


}
