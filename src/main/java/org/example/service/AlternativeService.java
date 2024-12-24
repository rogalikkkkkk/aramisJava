package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Alternative;
import org.example.AlternativeList;

import java.io.InputStream;
import java.util.List;

public class AlternativeService {
    public List<Alternative> loadAlternatives(String jsonFileName) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(jsonFileName);
            if (inputStream == null) {
                throw new IllegalArgumentException("Файл не найден: " + jsonFileName);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            AlternativeList problemList = objectMapper.readValue(inputStream, AlternativeList.class);
            return problemList.getAlternatives();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке проблем из файла: " + jsonFileName, e);
        }
    }
}
