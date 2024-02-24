package com.backendspringboot.controller;

import com.backendspringboot.service.DictionaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/checkWord/{word}")
    @ResponseBody
    public boolean checkWord(@PathVariable("word") String word) {
        return dictionaryService.checkIfExist(word);
    }

    @GetMapping("/getFirstWord")
    @ResponseBody
    public String getFirstWord() {
        return dictionaryService.getFirstWord();
    }
}
