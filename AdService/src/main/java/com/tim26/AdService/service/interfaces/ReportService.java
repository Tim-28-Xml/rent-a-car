package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.dto.CreateReportDto;
import com.tim26.AdService.dto.ReportDTO;
import com.tim26.AdService.model.Report;

import java.security.Principal;
import java.util.List;

public interface ReportService {
    List<Report> findAll();
    List<ReportDTO> getAllByUser(String username);
    List<ReportDTO> payAdditionalKm(Long id, Principal p);
    Report save(Report report);
    void save(CreateReportDto dto);
}
