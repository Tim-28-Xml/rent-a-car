package com.tim26.AdService.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim26.AdService.dto.CreateAdDto;
import com.tim26.AdService.service.AdServiceImpl;
import com.tim26.AdService.service.interfaces.AdService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class RabbitMQListener implements MessageListener {

    private AdService adService;

    public RabbitMQListener(AdService adService){
        this.adService = adService;
    };

    @Override
    public void onMessage(Message message) {
        ObjectMapper m = new ObjectMapper();
        try {
            CreateAdDto adDto = m.readValue(message.getBody(), CreateAdDto.class);
            adService.save(adDto);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}
