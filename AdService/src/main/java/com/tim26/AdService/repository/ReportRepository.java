package com.tim26.AdService.repository;

import com.tim26.AdService.model.Report;
import com.tim26.AdService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, String> {
    Report save(Report report);
    List<Report> findAll();
    List<Report> findAllByUser(User user);
    Report findById(Long id);
}
