package com.tim26.demo.config;

import com.tim26.demo.soap.PricelistClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.xml.RentCar.wsdl");
        return marshaller;
    }



    @Bean
    public PricelistClient pricelistClient(Jaxb2Marshaller marshaller) {
        PricelistClient client = new PricelistClient();
        client.setDefaultUri("http://localhost:8084/adservice-schema/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
