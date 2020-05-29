package com.tim26.demo.service.interfaces;

import com.tim26.demo.model.Codebook;

public interface CodebookService {
    Codebook getFirstCodebook();
    Codebook save(Codebook codebook);
}
