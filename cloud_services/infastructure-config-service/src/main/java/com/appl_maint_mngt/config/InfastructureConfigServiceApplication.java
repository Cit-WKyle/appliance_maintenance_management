package com.appl_maint_mngt.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class InfastructureConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfastructureConfigServiceApplication.class, args);
	}
}
