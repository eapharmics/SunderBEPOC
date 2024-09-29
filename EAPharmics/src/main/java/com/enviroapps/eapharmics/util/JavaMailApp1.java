package com.enviroapps.eapharmics.util;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class JavaMailApp1
{
    public static void main( String[] args )
    {	
    	String host = "enviroap.ipower.com";
    	int port = 587;
    	final String username = "sundar@enviroapps.com";
    	final String password = "sundar1";
 
    	Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true"); 
    	
    	Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		session.setDebug(true);
 
    	try {
 
		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress("sundar@enviroapps.com"));
		    message.setRecipients(Message.RecipientType.TO, 
	                        InternetAddress.parse("makesh@enviroapps.com"));
		    message.setSubject("Testing Subject");
		    message.setText("Hope this works!");
	 
		    Transport transport = session.getTransport("smtp");
		    transport.connect(host, port, username, password);
	 
		    System.out.println("Sending Message");
		    Transport.send(message);
	 
		    System.out.println("Done");
 
    	} catch (MessagingException e) {
    	    throw new RuntimeException(e);
    	}
    }
}