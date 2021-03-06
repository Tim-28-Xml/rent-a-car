package com.tim26.AdService.repository;

import com.tim26.AdService.model.Codebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CodebookRepository extends JpaRepository<Codebook, Long> {

    @Query("SELECT c FROM Codebook c WHERE c.id = -1")
    Codebook getFirstCodebook();

    Codebook save(Codebook codebook);

}
