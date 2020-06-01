package com.tim26.demo.repository;

import com.tim26.demo.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad save(Ad ad);
    List<Ad> findAll();
    Ad findById(long id);

}
