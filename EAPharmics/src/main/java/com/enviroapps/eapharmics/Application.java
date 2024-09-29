package com.enviroapps.eapharmics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.setProperty("application.configuration.location", "C:\\Programs\\EAPharmics\\EAPharmics\\config");
		System.setProperty("application.configuration.location.name", "");
		SpringApplication.run(Application.class, args);
	}

}
