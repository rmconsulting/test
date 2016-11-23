package com.tbs.service.mail;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class MailGunTestCase {

	@Test
	public void send(){
		 Client client = Client.create();
		    client.addFilter(new HTTPBasicAuthFilter("api",
		                "key-204a52470413c22be101afd92141029f"));
		    WebResource webResource =
		        client.resource("https://api.mailgun.net/v3/sandboxf9a2382bad5a46d68e8f90bf6ca5813c.mailgun.org/messages");
		    MultivaluedMapImpl formData = new MultivaluedMapImpl();
		    formData.add("from", "Mailgun Sandbox <postmaster@sandboxf9a2382bad5a46d68e8f90bf6ca5813c.mailgun.org>");
		    formData.add("to", "Gaston <santiagococo@gmail.com>");
		    formData.add("subject", "Hello Gaston");
		    formData.add("text", "Congratulations Gaston, you just sent an email with Mailgun!  You are truly awesome!  You can see a record of this email in your logs: https://mailgun.com/cp/log .  You can send up to 300 emails/day from this sandbox server.  Next, you should add your own domain so you can send 10,000 emails/month for free.");
		    webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
		                                                post(ClientResponse.class, formData);
	}
}
