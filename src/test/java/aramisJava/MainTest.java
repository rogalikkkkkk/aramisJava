package aramisJava;

import org.example.Main;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MainTest {
    @Test
    void threeCriteriaDense() {
        boolean userPrinting = false;
        // PARAMETERS
        boolean generateData = true;
        int alternativesCount = 5;
        int criteriaCount = 3;
        int expertsCount = 5;

        // Number of values must be same as number of all ratings
        Map<String, Integer> ratingDistribution = new HashMap<>() {{
            put("ОВ", 5);
            put("В", 5);
            put("С", 5);
            put("Н", 5);
            put("ОН", 5);
        }};

        Main.calculateRatings(
                userPrinting,
                generateData,
                alternativesCount,
                criteriaCount,
                expertsCount,
                ratingDistribution
        );
    }

    @Test
    void sixCriteriaDense() {
        boolean userPrinting = false;
        // PARAMETERS
        boolean generateData = true;
        int alternativesCount = 5;
        int criteriaCount = 6;
        int expertsCount = 5;

        // Number of values must be same as number of all ratings
        Map<String, Integer> ratingDistribution = new HashMap<>() {{
            put("ОВ", 5);
            put("В", 5);
            put("С", 5);
            put("Н", 5);
            put("ОН", 5);
        }};

        Main.calculateRatings(
                userPrinting,
                generateData,
                alternativesCount,
                criteriaCount,
                expertsCount,
                ratingDistribution
        );
    }

    @Test
    void nineCriteriaDense() {
        boolean userPrinting = false;
        // PARAMETERS
        boolean generateData = true;
        int alternativesCount = 5;
        int criteriaCount = 9;
        int expertsCount = 5;

        // Number of values must be same as number of all ratings
        Map<String, Integer> ratingDistribution = new HashMap<>() {{
            put("ОВ", 5);
            put("В", 5);
            put("С", 5);
            put("Н", 5);
            put("ОН", 5);
        }};

        Main.calculateRatings(
                userPrinting,
                generateData,
                alternativesCount,
                criteriaCount,
                expertsCount,
                ratingDistribution
        );
    }

    @Test
    void threeCriteriaHalfDense() {
        boolean userPrinting = false;
        // PARAMETERS
        boolean generateData = true;
        int alternativesCount = 5;
        int criteriaCount = 3;
        int expertsCount = 5;

        // Number of values must be same as number of all ratings
        Map<String, Integer> ratingDistribution = new HashMap<>() {{
            put("ОВ", 10);
            put("В", 0);
            put("С", 5);
            put("Н", 0);
            put("ОН", 10);
        }};

        Main.calculateRatings(
                userPrinting,
                generateData,
                alternativesCount,
                criteriaCount,
                expertsCount,
                ratingDistribution
        );
    }

    @Test
    void sixCriteriaHalfDense() {
        boolean userPrinting = false;
        // PARAMETERS
        boolean generateData = true;
        int alternativesCount = 5;
        int criteriaCount = 6;
        int expertsCount = 5;

        // Number of values must be same as number of all ratings
        Map<String, Integer> ratingDistribution = new HashMap<>() {{
            put("ОВ", 10);
            put("В", 0);
            put("С", 5);
            put("Н", 0);
            put("ОН", 10);
        }};

        Main.calculateRatings(
                userPrinting,
                generateData,
                alternativesCount,
                criteriaCount,
                expertsCount,
                ratingDistribution
        );
    }

    @Test
    void nineCriteriaHalfDense() {
        boolean userPrinting = false;
        // PARAMETERS
        boolean generateData = true;
        int alternativesCount = 5;
        int criteriaCount = 9;
        int expertsCount = 5;

        // Number of values must be same as number of all ratings
        Map<String, Integer> ratingDistribution = new HashMap<>() {{
            put("ОВ", 10);
            put("В", 0);
            put("С", 5);
            put("Н", 0);
            put("ОН", 10);
        }};

        Main.calculateRatings(
                userPrinting,
                generateData,
                alternativesCount,
                criteriaCount,
                expertsCount,
                ratingDistribution
        );
    }
    @Test
    void threeCriteriaNoDense() {
        boolean userPrinting = false;
        // PARAMETERS
        boolean generateData = true;
        int alternativesCount = 5;
        int criteriaCount = 3;
        int expertsCount = 5;

        // Number of values must be same as number of all ratings
        Map<String, Integer> ratingDistribution = new HashMap<>() {{
            put("ОВ", 0);
            put("В", 0);
            put("С", 25);
            put("Н", 0);
            put("ОН", 0);
        }};

        Main.calculateRatings(
                userPrinting,
                generateData,
                alternativesCount,
                criteriaCount,
                expertsCount,
                ratingDistribution
        );
    }

    @Test
    void sixCriteriaNoDense() {
        boolean userPrinting = false;
        // PARAMETERS
        boolean generateData = true;
        int alternativesCount = 5;
        int criteriaCount = 6;
        int expertsCount = 5;

        // Number of values must be same as number of all ratings
        Map<String, Integer> ratingDistribution = new HashMap<>() {{
            put("ОВ", 0);
            put("В", 0);
            put("С", 25);
            put("Н", 0);
            put("ОН", 0);
        }};

        Main.calculateRatings(
                userPrinting,
                generateData,
                alternativesCount,
                criteriaCount,
                expertsCount,
                ratingDistribution
        );
    }

    @Test
    void nineCriteriaNoDense() {
        boolean userPrinting = false;
        // PARAMETERS
        boolean generateData = true;
        int alternativesCount = 5;
        int criteriaCount = 9;
        int expertsCount = 5;

        // Number of values must be same as number of all ratings
        Map<String, Integer> ratingDistribution = new HashMap<>() {{
            put("ОВ", 0);
            put("В", 0);
            put("С", 25);
            put("Н", 0);
            put("ОН", 0);
        }};

        Main.calculateRatings(
                userPrinting,
                generateData,
                alternativesCount,
                criteriaCount,
                expertsCount,
                ratingDistribution
        );
    }

}
