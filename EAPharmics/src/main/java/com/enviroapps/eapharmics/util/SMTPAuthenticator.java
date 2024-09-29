package com.enviroapps.eapharmics.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator { /* @Override */
	protected PasswordAuthentication getPasswordAuthentication() {
		return super.getPasswordAuthentication();
	}

	public SMTPAuthenticator(String username, String password) {
		super();
	}
}