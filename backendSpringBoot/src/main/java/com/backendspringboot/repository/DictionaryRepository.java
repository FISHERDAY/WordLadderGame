package com.backendspringboot.repository;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Repository
public class DictionaryRepository {
    private static final HashSet<String> dictionary;
    private static final ArrayList<String> fiveLetterDictionary;

    static {
        List<Object> dictionaries = readDictionaryFromFile();
        dictionary = (HashSet<String>) dictionaries.get(0);
        fiveLetterDictionary = (ArrayList<String>) dictionaries.get(1);
    }

    private static List<Object> readDictionaryFromFile() {
        InputStream is = DictionaryRepository.class.getClassLoader().getResourceAsStream("dictionary.txt");
        HashSet<String> dictionary = new HashSet<>();
        ArrayList<String> fiveLetterDictionary = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
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
