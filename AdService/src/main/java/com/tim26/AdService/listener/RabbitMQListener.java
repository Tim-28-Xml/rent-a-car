package com.tim26.AdService.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim26.AdService.dto.CreateAdDto;
import com.tim26.AdService.service.interfaces.AdService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitMQListener implements MessageListener {

    @Autowired
    AdService adService;

    @Override
    public void onMessage(Message message) {
        ObjectMapper m = new ObjectMapper();
        try {
            CreateAdDto adDto = m.readValue(message.getBody(), CreateAdDto.class);
            System.out.println(adDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
