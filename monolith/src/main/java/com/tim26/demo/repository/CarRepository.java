package com.tim26.demo.repository;

import com.tim26.demo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {


    public List<Car> findAllByOrderByKmDesc();




}
