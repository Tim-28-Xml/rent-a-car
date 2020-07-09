package com.tim26.AdService.controller;

import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.dto.ReportDTO;
import com.tim26.AdService.service.interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    @Autowired
    private ReportService reportService;

    public static class ReportIdDTO {
        public String reportId;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ReportDTO>> getAll(Principal p) {
        List<ReportDTO> reports = reportService.getAllByUser(p.getName());
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @PostMapping(value = "/pay")
    public ResponseEntity<List<ReportDTO>> payReport(@RequestBody ReportIdDTO reportId, Principal p) {
        Long repId = Long.parseLong(reportId.reportId);
        List<ReportDTO> reports = reportService.payAdditionalKm(repId, p);

        return new ResponseEntity<>(reports, HttpStatus.OK);


    }
}
