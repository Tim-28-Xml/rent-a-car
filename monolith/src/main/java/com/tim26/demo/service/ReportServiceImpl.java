package com.tim26.demo.service;

import com.tim26.demo.dto.CreateReportDto;
import com.tim26.demo.dto.RentedCarDto;
import com.tim26.demo.model.*;
import com.tim26.demo.repository.AdRepository;
import com.tim26.demo.repository.RentRequestRepository;
import com.tim26.demo.repository.ReportRepository;
import com.tim26.demo.repository.UserRepository;
import com.tim26.demo.service.interfaces.ReportService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentRequestRepository rentRequestRepository;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${exchange}")
    String exchange;

    @Value("${ad-service-key}")
    private String adKey;

    @Override
    public boolean save(CreateReportDto dto, Principal p) {

        User user = userRepository.findByUsername(dto.getUsername());
        Optional<RentRequest> rentRequest = rentRequestRepository.findById(dto.getRequestId());
        if(user == null || rentRequest.get() == null)
            return false;

        rentRequest.get().setHasReport(true);

        if(dto != null) {
            Report r = new Report();
            r.setText(dto.getText());
            r.setUser(user);
            r.setPaid(false);

           if(adRepository.findById(dto.getAdId()).isPresent()) {
               Ad ad = adRepository.findById(dto.getAdId()).get();
                   r.setAd(ad);
                   r.setStartKM(ad.getCar().getKm());
                   r.setEndKM(dto.getKm());
                   ad.getReports().add(r);
                   ad.getCar().setKm(dto.getKm());
                   reportRepository.save(r);
                   adRepository.save(ad);
                   rentRequestRepository.save(rentRequest.get());
                   rabbitTemplate.convertAndSend(exchange, adKey, dto);
                   return true;
            }
        }

        return false;
    }

    @Override
    public Optional<Report> findReportById(Long id) {
        return reportRepository.findById(id);
    }

    @Override
    public List<RentedCarDto> findAllRentedCars(Principal p) {
        User user = userRepository.findByUsername(p.getName());
        List<RentedCarDto> ads = new ArrayList<>();
            for(RentRequest rentRequest : user.getOwnedRentRequests()) {
                if(rentRequest.getRequestStatus().equals(RequestStatus.PAID)) {
                    for(AdDateRange dr : rentRequest.getAdsWithDates()) {


                            LocalDate today = LocalDate.now();

                            if(dr.getEnd_Date().isBefore(today) && !rentRequest.isHasReport()) {
                                Ad ad = adRepository.findById(dr.getAd_id());
                                RentedCarDto dto = new RentedCarDto();
                                dto.setBrand(ad.getCar().getBrand());
                                dto.setCarClass(ad.getCar().getCarClass());
                                dto.setModel(ad.getCar().getModel());
                                dto.setFuel(ad.getCar().getFuel());
                                dto.setTransmission(ad.getCar().getTransmission());
                                dto.setKm(ad.getCar().getKm());
                                dto.setKmLimit(ad.getCar().getKmLimit());
                                dto.setChildSeats(ad.getCar().getChildSeats());
                                dto.setCdw(ad.getCar().isCdw());
                                dto.setId(ad.getId());
                                dto.setUsername(rentRequest.getCreator().getUsername());
                                dto.setRequestId(rentRequest.getId());
                                ads.add(dto);
                            }
                        }

                }
            }
        return ads;
    }
}
