package com.tim26.AdService.config;

import com.tim26.AdService.listener.RabbitMQListener;
import com.tim26.AdService.service.AdServiceImpl;
import com.tim26.AdService.service.interfaces.AdService;
import com.tim26.AdService.service.interfaces.PricelistService;
import com.tim26.AdService.service.interfaces.ReportService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private AdService adService;

    @Autowired
    private PricelistService pricelistService;

    @Autowired
    private ReportService reportService;

    @Value("${queue-name}")
    String queueName;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }


    @Bean
    @DependsOn({"adService", "pricelistService", "reportService"})
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory ) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueues(queue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQListener(adService, pricelistService, reportService));
        return simpleMessageListenerContainer;
    }

}
