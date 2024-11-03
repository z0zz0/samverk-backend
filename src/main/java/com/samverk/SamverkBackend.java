package com.samverk;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SamverkBackend {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SamverkBackend.class, args);
		String[] activeProfiles = context.getEnvironment().getActiveProfiles();
		System.out.println("Active profiles: " + Arrays.toString(activeProfiles));
	}

}