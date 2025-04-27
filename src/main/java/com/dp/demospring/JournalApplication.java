package com.dp.demospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class JournalApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
		ConfigurableEnvironment environment = context.getEnvironment();
		System.out.println(environment.getActiveProfiles()[0]);
	}

}
