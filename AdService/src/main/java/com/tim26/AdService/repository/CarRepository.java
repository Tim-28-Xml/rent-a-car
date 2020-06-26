package com.tim26.AdService.repository;

import com.tim26.AdService.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findById(Long id);
    List<Car> findAll();

}