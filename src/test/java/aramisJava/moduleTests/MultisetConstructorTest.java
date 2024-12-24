package aramisJava.moduleTests;

import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultisetConstructorTest {


    private void testMultisetGeneration(
            List<Criterion> criteria,
            List<RatingWithProblem> ratingWithProblems,
            List<Map<String, Integer>> expectedRatingCounts) {

        Multiset multiset = new Multiset("Test Alternative", ratingWithProblems);

        assertRatingCountsEqual(expectedRatingCounts, multiset.ratingCounts);
    }

    private void assertRatingCountsEqual(List<Map<String, Integer>> expected, List<Map<String, Integer>> actual) {
        assertEquals(expected.size(), actual.size(), "Размер списков ratingCounts не совпадает!");

        for (int i = 0; i < expected.size(); i++) {
            List<String> mapKeys = new ArrayList<>(actual.get(i).keySet());
            Map<String, Integer> expectedMap = expected.get(i);
            Map<String, Integer> actualMap = actual.get(i);

            for (String key : mapKeys) {
                assertEquals(expectedMap.get(key), actualMap.get(key),
                        "Оценка " + i + " альтернативы не совпадает! Ожидаемая для " + key + ": "
                                + expectedMap.get(key) + ", фактический: " + actualMap.get(key)
                );
            }
        }
    }


    @Test
    void testMultisetWithParameters() {
        List<Criterion> criteria = List.of(
                new Criterion("Criterion 1", Scale.LONG, OptimizationDirection.MAX),
                new Criterion("Criterion 2", Scale.BASE, OptimizationDirection.MIN)
        );

        Problem problem = new Problem("Test Problem", criteria);

        Rating rating1 = new Rating("Test Problem", "Alternative 1", List.of("ОВ", "С"));
        Rating rating2 = new Rating("Test Problem", "Alternative 1", List.of("В", "Н"));
        Rating rating3 = new Rating("Test Problem", "Alternative 1", List.of("ОВ", "С"));

        RatingWithProblem rwp1 = new RatingWithProblem(rating1, problem);
        RatingWithProblem rwp2 = new RatingWithProblem(rating2, problem);
        RatingWithProblem rwp3 = new RatingWithProblem(rating3, problem);

        List<RatingWithProblem> ratingWithProblems = List.of(rwp1, rwp2, rwp3);

        List<Map<String, Integer>> expectedRatingCounts = List.of(
                Map.of("ОВ", 2, "В", 1, "С", 0, "Н", 0, "ОН", 0),
                Map.of("ОВ", 0, "В", 0, "С", 2, "Н", 1, "ОН", 0)
        );

        testMultisetGeneration(criteria, ratingWithProblems, expectedRatingCounts);
    }

    @Test
    void testMultisetWithBestRatings() {
        List<Criterion> criteria = List.of(
                new Criterion("Criterion 1", Scale.LONG, OptimizationDirection.MAX),
                new Criterion("Criterion 2", Scale.BASE, OptimizationDirection.MIN)
        );

        int ratingsCount = 3;
        Multiset multiset = new Multiset("Best Alternative", criteria, ratingsCount, true);

        List<Map<String, Integer>> expectedRatingCounts = List.of(
                Map.of("ОВ", ratingsCount, "В", 0, "С", 0, "Н", 0, "ОН", 0),
                Map.of("ОВ", ratingsCount, "В", 0, "С", 0, "Н", 0, "ОН", 0)
        );

        assertEquals(expectedRatingCounts, multiset.ratingCounts, "Rating counts для лучшего мультисета не совпадают.");
    }

    @Test
    void testMultisetWithWorstRatings() {
        List<Criterion> criteria = List.of(
                new Criterion("Criterion 1", Scale.LONG, OptimizationDirection.MAX),
                new Criterion("Criterion 2", Scale.BASE, OptimizationDirection.MIN)
        );

        int ratingsCount = 3;
        Multiset multiset = new Multiset("Worst Alternative", criteria, ratingsCount, false);

        List<Map<String, Integer>> expectedRatingCounts = List.of(
                Map.of("ОВ", 0, "В", 0, "С", 0, "Н", 0, "ОН", ratingsCount),
                Map.of("ОВ", 0, "В", 0, "С", 0, "Н", 0, "ОН", ratingsCount)
        );

        assertEquals(expectedRatingCounts, multiset.ratingCounts, "Rating counts для худшего мультисета не совпадают.");
    }
}

