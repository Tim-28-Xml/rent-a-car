package com.tim26.demo.service.interfaces;

import com.tim26.demo.dto.AdDTO;
import com.tim26.demo.dto.CreateReportDto;
import com.tim26.demo.dto.RentedCarDto;
import com.tim26.demo.model.Ad;
import com.tim26.demo.model.Report;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ReportService {
    boolean save(CreateReportDto dto, Principal p);
    Optional<Report> findReportById(Long id);
    List<RentedCarDto> findAllRentedCars(Principal p);
}
