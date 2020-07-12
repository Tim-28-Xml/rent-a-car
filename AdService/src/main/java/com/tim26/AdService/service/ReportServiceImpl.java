package com.tim26.AdService.service;

import com.netflix.discovery.converters.Auto;
import com.tim26.AdService.dto.CreateReportDto;
import com.tim26.AdService.dto.ReportDTO;
import com.tim26.AdService.model.Ad;
import com.tim26.AdService.model.Report;
import com.tim26.AdService.model.User;
import com.tim26.AdService.repository.ReportRepository;
import com.tim26.AdService.service.interfaces.AdService;
import com.tim26.AdService.service.interfaces.ReportService;
import com.tim26.AdService.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service(value = "reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AdService adService;

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    public void save(CreateReportDto dto){
        User user = userService.findByUsername(dto.getUsername());
        Ad ad = adService.findAdById(dto.getAdId());

        Report report = new Report(dto.getText(), ad.getCar().getKm(), ad.getCar().getKm()+dto.getKm(), ad, user);

        double diff = report.getEndKM() - report.getStartKM();
        double overLimit = diff - report.getAd().getCar().getKmLimit();

        if(overLimit < 0)
            report.setPaid(true);
        else
            user.incUnpaid();

        ad.getReports().add(report);
        ad.getCar().setKm(report.getEndKM());
        adService.save(ad);
        checkReports(user.getUsername(), user.getUnpaid());
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

        user.decUnpaid();
        userService.save(user);
        checkReports(user.getUsername(), user.getUnpaid());
        return getAllByUser(p.getName());
    }

    private void checkReports(String username, int num){
        try {
            URL url = new URL("http://localhost:8086/api/users/check?username=" + username + "&num=" + num);
            URLConnection connection = url.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
