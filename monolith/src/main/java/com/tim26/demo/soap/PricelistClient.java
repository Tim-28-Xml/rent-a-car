package com.tim26.demo.soap;

import com.tim26.demo.dto.CreatePricelistDto;
import com.tim26.demo.service.interfaces.PricelistService;
import com.xml.RentCar.wsdl.PricelistRequest;
import com.xml.RentCar.wsdl.PricelistResponse;
import com.xml.RentCar.wsdl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class PricelistClient extends WebServiceGatewaySupport {

    @Autowired
    private PricelistService pricelistService;

    public PricelistResponse postPricelist(CreatePricelistDto pricelist, String username) {

        PricelistRequest request = new PricelistRequest();
        request.setCdwPrice(pricelist.getCdwPrice());
        request.setDailyPrice(pricelist.getDailyPrice());
        request.setPricePerExtraKm(pricelist.getPricePerExtraKm());
        request.setUsername(username);
        request.setName(pricelist.getName());

        PricelistResponse response = (PricelistResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8084/ws/adservice-schema", request,
                        new SoapActionCallback("http://localhost:8084/ws/adservice-schema/pricelistRequest"));

        return response;
    }
}
