package com.backendspringboot.service;

import com.backendspringboot.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

    public boolean checkIfExist(String word) {
        return DictionaryRepository.checkWordIfExists(word);
    }

    public String getFirstWord() {
        return DictionaryRepository.getFirstWord();
    }
}
