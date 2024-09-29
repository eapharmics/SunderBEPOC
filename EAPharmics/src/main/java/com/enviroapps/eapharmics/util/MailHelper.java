/**
 * 
 */
package com.enviroapps.eapharmics.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.enviroapps.eapharmics.bom.admin.ApplParameter;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.persistence.AdminFactory;
import com.enviroapps.eapharmics.ui.Constants;

/**
 * @author EAPharmics
 * 
 */
public class MailHelper {

	public static void sendMail(List toEmailAddresses, String subject,
			String mailText) {
		try {
			String mailHost = "";
			String id = "";
			String pwd = "";
			int port = 0;
			ApplParameter parameter = AdminFactory.getInstance()
					.getApplParameterByName(Constants.EMAIL_HOST);
			if (parameter.getParameterValue() != null) {
				mailHost = parameter.getParameterValue();
			}
			parameter = AdminFactory.getInstance().getApplParameterByName(
					Constants.EMAIL_ADMIN_USER_ID);
			if (parameter.getParameterValue() != null) {
				id = parameter.getParameterValue();
			}
			parameter = AdminFactory.getInstance().getApplParameterByName(
					Constants.EMAIL_ADMIN_PASSWORD);
			if (parameter.getParameterValue() != null) {
				pwd = parameter.getParameterValue();
			}
			parameter = AdminFactory.getInstance().getApplParameterByName(
					Constants.EMAIL_PORT);
			if (parameter.getParameterValue() != null) {
				try {
					port = Integer.parseInt(parameter.getParameterValue());
				} catch (NumberFormatException e) {
					UtilityServiceFactory.getLogger().debug("MailHelper",
							"sendMail",
							"Error in configured port. Using default 587.");
					UtilityServiceFactory.getLogger().debug("MailHelper",
							"sendMail", e);
				}
			}

			final String fromAddressId = id;
			final String password = pwd;
			Properties props = new Properties();
			props.put("mail.smtp.host", mailHost);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");

			// SMTPAuthenticator authenticator = new
			// SMTPAuthenticator(fromAddressId, password);

			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromAddressId, password);
				}
			};

			Session session = Session.getInstance(props, auth);
			//session.setDebug(true);
			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("sundar@enviroapps.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress
						.parse("makesh@enviroapps.com"));
				Address fromAddress = new InternetAddress(fromAddressId,
						"EnviroApps");

				List<Address> list = new ArrayList<Address>();
				for (Iterator iterator = toEmailAddresses.iterator(); iterator
						.hasNext();) {
					String object = (String) iterator.next();
					Address toAddress = new InternetAddress(object);
					list.add(toAddress);
				}
				Address[] toAddresses = new Address[list.size()];
				toAddresses = (Address[]) list
						.toArray(new Address[list.size()]);

				message.setSubject(subject);
				message.setText(mailText);

				Transport transport = session.getTransport("smtp");
				transport.connect(mailHost, port, fromAddressId, password);

				//System.out.println("Sending Message");
				Transport.send(message);

				//System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			System.err.println("Unable to send mail to "
					+ toEmailAddresses.size() + " participants. Message: "
					+ mailText);
			UtilityServiceFactory.getLogger()
					.error("MailHelper", "sendMail", e);
		}

	}

	public static void sendMail(String emailAddress, String subject,
			String message) {
		List<String> list = new ArrayList<String>();
		list.add(emailAddress);
		sendMail(list, subject, message);
	}

	public static void main(String[] args) {
		MailHelper.sendMail("sundar@enviroapps.com", "This is a test mail",
				"Hope you received it");
	}
}
