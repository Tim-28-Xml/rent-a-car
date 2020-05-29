package com.tim26.demo.service;

import com.tim26.demo.model.Codebook;
import com.tim26.demo.repository.CodebookRepository;
import com.tim26.demo.service.interfaces.CodebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodebookServiceImpl implements CodebookService {

    @Autowired
    CodebookRepository codebookRepository;

    @Override
    public Codebook getFirstCodebook() {
        return codebookRepository.getFirstCodebook();
    }

    @Override
    public Codebook save(Codebook codebook) {
        return codebookRepository.save(codebook);
    }
}
