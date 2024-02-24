package com.backendspringboot.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;

@Repository
public class DictionaryRepository {
    private static final String dictionaryPath;//System.getProperty("user.dir") + "\\dictionary.txt";

    static {
        try {
            dictionaryPath = ResourceUtils.getFile("classpath:dictionary.txt").getAbsolutePath();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final HashSet<String> dictionary;
    private static final ArrayList<String> fiveLetterDictionary;

    static {
        List<Object> dictionaries = readDictionaryFromFile(dictionaryPath);
        dictionary = (HashSet<String>) dictionaries.get(0);
        fiveLetterDictionary = (ArrayList<String>) dictionaries.get(1);
    }

    private static List<Object> readDictionaryFromFile(String filePath) {
        HashSet<String> dictionary = new HashSet<>();
        ArrayList<String> fiveLetterDictionary = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim();
                if (word.length() == 5) fiveLetterDictionary.add(word);
                dictionary.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(List.of(dictionary, fiveLetterDictionary));
    }

    public static boolean checkWordIfExists(String word) {
        return dictionary.contains(word.toUpperCase());
    }
    public static String getFirstWord() {
        return fiveLetterDictionary.get(new Random().nextInt(fiveLetterDictionary.size()));
    }

}
