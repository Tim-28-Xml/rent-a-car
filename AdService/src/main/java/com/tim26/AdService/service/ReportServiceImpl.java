package com.tim26.AdService.service;

import com.netflix.discovery.converters.Auto;
import com.tim26.AdService.dto.ReportDTO;
import com.tim26.AdService.model.Report;
import com.tim26.AdService.model.User;
import com.tim26.AdService.repository.ReportRepository;
import com.tim26.AdService.service.interfaces.ReportService;
import com.tim26.AdService.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public List<ReportDTO> getAllByUser(String username) {
        User user = userService.findByUsername(username);
        List<Report> reports = reportRepository.findAllByUser(user);

        List<ReportDTO> reportDTOS = new ArrayList<>();
        for(Report report : reports){
            ReportDTO reportDTO = new ReportDTO(report);

            double diff = report.getEndKM() - report.getStartKM();
            double overLimit = diff - report.getAd().getCar().getKmLimit();

            if(overLimit > 0){
                reportDTO.setOverLimit(overLimit);
                double price = overLimit * report.getAd().getPriceList().getPricePerExtraKm();
                reportDTO.setPrice(price);
            } else {
                reportDTO.setPaid(true);
            }

            reportDTOS.add(reportDTO);
        }
        return reportDTOS;
    }


    @Override
    public List<ReportDTO> payAdditionalKm(Long id, Principal p) {
        User user = userService.findByUsername(p.getName());
        Report report = reportRepository.findById(id);

        if(!report.isPaid()){
            report.setPaid(true);
            save(report);
        }

        return getAllByUser(p.getName());
    }


}
