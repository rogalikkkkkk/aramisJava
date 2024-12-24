package org.example.service;

import org.example.*;

import java.util.*;

public class RatingsGenerator {
    static public Map<String, List<RatingWithProblem>> generateData(int altCount, int criteriaCount, int expertsCount) {
        List<Criterion> criteria = new ArrayList<>();
        final String[] baseArray = {"ОВ", "В", "С", "Н", "ОН"};
        Map<String, List<RatingWithProblem>> result = new HashMap<>();

        for (int i = 0; i < criteriaCount; i++) {
            String criterionName = "Criterion " + (i + 1);
            Criterion crit = new Criterion(criterionName, Scale.BASE, OptimizationDirection.MAX);

            criteria.add(crit);
        }

        Problem prblm = new Problem("Default problem", criteria);

        for (int i = 0; i < altCount; i++) {
            String altName = "Alternative " + (i + 1);
            List<RatingWithProblem> allRatingsToAlt = new ArrayList<>();

            for (int j = 0; j < expertsCount; j++) {
                List<String> ratings = new ArrayList<>();

                for (int k = 0; k < criteriaCount; k++) {
                    Random rand = new Random();
                    ratings.add(baseArray[rand.nextInt(baseArray.length)]);
                }

                Rating currentRating = new Rating(prblm.getProblemName(), altName, ratings);
                RatingWithProblem altRatingByExpert = new RatingWithProblem(currentRating, prblm);
                allRatingsToAlt.add(altRatingByExpert);
            }

            result.put(altName, allRatingsToAlt);
        }

        return result;
    }

}
