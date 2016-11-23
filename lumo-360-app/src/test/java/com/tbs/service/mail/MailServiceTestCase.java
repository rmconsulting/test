package com.tbs.service.mail;

import java.security.Security;
import java.util.Properties;

import org.junit.Test;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MailServiceTestCase {

	@Test
	public void send(){
		final String username = "santiagococo@gmail.com";
		final String password = "p3r0f0nt39876";

		  
		  Properties props = new Properties();
		  System.setProperty("mail.socket.debug", "true");
		  props.put("mail.smtp.socks.host", "localhost");
          props.put("mail.smtp.socks.port", "5865");
           props.put("mail.smtp.ssl.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.debug","true");
	        props.put("mail.smtp.port", "587");
	       
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("santiagococo@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("sgastonc@yahoo.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
