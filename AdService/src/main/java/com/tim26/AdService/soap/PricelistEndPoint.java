/*package com.tim26.AdService.soap;


import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.model.PriceList;
import com.tim26.AdService.service.interfaces.PricelistService;
import com.tim26.AdService.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import localhost._8084.adservice_schema.PricelistRequest;
import localhost._8084.adservice_schema.PricelistResponse;

import java.security.Principal;
import java.text.ParseException;

@Endpoint
public class PricelistEndPoint {

    private static final String NAMESPACE_URI = "http://localhost:8084/adservice-schema";

    @Autowired
    private PricelistService pricelistService;

    @Autowired
    private UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "pricelistRequest")
    @ResponsePayload
    public PricelistResponse createPricelist(@RequestPayload PricelistRequest request) throws ParseException {

        System.out.println("SOAP - Create pricelist.");
        PricelistResponse response = new PricelistResponse();
        CreatePricelistDto dto = new CreatePricelistDto();
        dto.setCdwPrice(request.getCdwPrice());
        dto.setDailyPrice(request.getDailyPrice());
        dto.setPricePerExtraKm(request.getPricePerExtraKm());
        dto.setName(request.getName());
        PriceList priceList =pricelistService.save(request.getUsername(),dto);
        userService.findByUsername(request.getUsername()).getPriceLists().add(priceList);

        if(priceList != null) {
            response.setName(request.getName());
        }
        return response;
    }
}*/
