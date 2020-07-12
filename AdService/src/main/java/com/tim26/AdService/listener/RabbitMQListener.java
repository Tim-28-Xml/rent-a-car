package com.tim26.AdService.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim26.AdService.dto.CreateAdDto;
import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.service.interfaces.AdService;
import com.tim26.AdService.service.interfaces.PricelistService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.SQLException;

@Service
public class RabbitMQListener implements MessageListener {

    private AdService adService;
    private PricelistService pricelistService;

    public RabbitMQListener(AdService adService, PricelistService pricelistService){
        this.adService = adService;
        this.pricelistService = pricelistService;
    };

    @Override
    public void onMessage(Message message) {
        ObjectMapper m = new ObjectMapper();
        try {
            CreateAdDto adDto = m.readValue(message.getBody(), CreateAdDto.class);
            adService.save(adDto);
        } catch (IOException | SQLException e) {
            try {
                CreatePricelistDto pricelistDto = m.readValue(message.getBody(), CreatePricelistDto.class);
                pricelistService.save(pricelistDto);
            } catch (IOException | SQLException ioException) {
                ioException.printStackTrace();
            }

        }
    }

}
