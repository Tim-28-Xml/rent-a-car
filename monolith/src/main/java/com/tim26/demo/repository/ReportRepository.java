package com.tim26.demo.repository;

import com.tim26.demo.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report save(Report r);
    Optional<Report> findById(Long id);
}
