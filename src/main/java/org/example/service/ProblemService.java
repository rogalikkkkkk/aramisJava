package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Problem;
import org.example.ProblemList;

import java.io.InputStream;
import java.util.List;

public class ProblemService {

    public List<Problem> loadProblems(String jsonFileName) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(jsonFileName);
            if (inputStream == null) {
                throw new IllegalArgumentException("Файл не найден: " + jsonFileName);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            ProblemList problemList = objectMapper.readValue(inputStream, ProblemList.class);
            return problemList.getProblems();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке проблем из файла: " + jsonFileName, e);
        }
    }
}

