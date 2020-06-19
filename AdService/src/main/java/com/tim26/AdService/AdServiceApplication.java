package com.tim26.AdService;

import com.tim26.AdService.service.AdServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.File;

@SpringBootApplication
@EnableDiscoveryClient
public class AdServiceApplication {

	public static void main(String[] args) {
		//new File(AdServiceImpl.uploadDirectory).mkdir();
		SpringApplication.run(AdServiceApplication.class, args);
	}

}
