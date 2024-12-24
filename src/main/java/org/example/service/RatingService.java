package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Rating;
import org.example.RatingList;

import java.io.InputStream;
import java.util.List;

public class RatingService {
    public List<Rating> loadRatings(String jsonFileName) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(jsonFileName);
            if (inputStream == null) {
                throw new IllegalArgumentException("Файл не найден: " + jsonFileName);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            RatingList problemList = objectMapper.readValue(inputStream, RatingList.class);
            return problemList.getRatings();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке проблем из файла: " + jsonFileName, e);
        }
    }
}
