package com.enviroapps.eapharmics.ui;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.enviroapps.eapharmics.persistence.DictionaryFactory;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.UnavailableException;

@Component
public class EAInitializer {
    @PostConstruct
    public void init() throws Exception {
        // Your initialization logic here
    	Date sd = new Date();
		try {
			//This is a dummy invocation and don't care about the results
			DictionaryFactory.getInstance().getMaxDetailDisplayOrder("ABBREVIATION");
		} catch (Exception e) {
			//log("Connection Initialization Error", e);
			e.printStackTrace();
			throw new UnavailableException(
					"Connection Initialization Error: Cannot initialize connection.");
		}
		Date ed = new Date();
		System.out.println("Initialization logic executed! " + (ed.getTime() - sd.getTime()));
    }
}
