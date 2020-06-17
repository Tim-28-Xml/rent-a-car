package com.tim26.Eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Properties;


@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {



	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
		Properties props = System.getProperties();
		props.setProperty("javax.net.debug","ssl,handshake");
	}

}
