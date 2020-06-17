package com.tim26.AdService.repository;

import com.tim26.AdService.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad save(Ad ad);
    List<Ad> findAll();
    Ad findById(long id);

    @Query("select ad from Ad ad where ad.id in :ids")
    List<Ad> findByIds(List<Long> ids);
}