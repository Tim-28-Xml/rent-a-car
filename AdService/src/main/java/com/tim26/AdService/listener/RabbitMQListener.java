package com.tim26.AdService.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim26.AdService.dto.CreateAdDto;
import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.dto.CreateReportDto;
import com.tim26.AdService.service.interfaces.AdService;
import com.tim26.AdService.service.interfaces.PricelistService;
import com.tim26.AdService.service.interfaces.ReportService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.SQLException;

@Service
public class RabbitMQListener implements MessageListener {

    private AdService adService;
    private PricelistService pricelistService;
    private ReportService reportService;

    public RabbitMQListener(AdService adService, PricelistService pricelistService, ReportService reportService){
        this.adService = adService;
        this.pricelistService = pricelistService;
        this.reportService = reportService;
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
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
                try {
                    CreateReportDto createReportDto = m.readValue(message.getBody(), CreateReportDto.class);
                    reportService.save(createReportDto);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }
    }

}
