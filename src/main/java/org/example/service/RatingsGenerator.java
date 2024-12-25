package org.example.service;

import org.example.*;

import java.util.*;

public class RatingsGenerator {
    static public List<RatingWithProblem> generateData(
            int altCount,
            int criteriaCount,
            int expertsCount,
            Map<String, Integer> ratingDistribution
    ) {
        List<RatingWithProblem> result = new ArrayList<>();
        List<Criterion> criteria = new ArrayList<>();

        // Check if distribution is correct
        int allRatings = ratingDistribution.values().stream().mapToInt(Integer::intValue).sum();
        if (allRatings != altCount * expertsCount) {
            throw new IllegalArgumentException("Sum of ratings distribution values don't match with number of ratings (altCount * expertsCount)");
        }

        // Generating list of ratings using distribution parameter
        List<String> ratingsDistributed = new ArrayList<>();
        for (String rating : ratingDistribution.keySet()) {
            for (int i = 0; i < ratingDistribution.get(rating); i++) {
                ratingsDistributed.add(rating);
            }
        }

        // Creating set of criteria for problem in base scale with maximization
        for (int i = 0; i < criteriaCount; i++) {
            String criterionName = "Criterion " + (i + 1);
            Criterion crit = new Criterion(criterionName, Scale.BASE, OptimizationDirection.MAX);

            criteria.add(crit);
        }

        // Creating problem with set of criteria
        Problem prblm = new Problem("Default problem", criteria);

        // Calculating amount of all ratings
        for (int i = 0; i < altCount; i++) {
            for (int j = 0; j < expertsCount; j++) {
                RatingWithProblem rwp = new RatingWithProblem();
                rwp.setProblem(prblm);

                String altName = "Alternative " + (i + 1);
                Rating rtg = new Rating(prblm.getProblemName(), altName, new ArrayList<>());
                rwp.setRating(rtg);

                result.add(rwp);
            }
        }

        // Distributing ratings to alternatives
        for (int i = 0; i < criteriaCount; i++) {
            List<String> possibleRatings = new ArrayList<>(ratingsDistributed);

            for (int j = 0; j < altCount; j++) {
                for (int k = 0; k < expertsCount; k++) {
                    Random rand = new Random();
                    RatingWithProblem currentRWP = result.get(j * expertsCount + k);
                    List<String> currentAltExpRating = currentRWP.rating().getAlternativeRatings();
                    List<String> newAltExpRating = new ArrayList<>(currentAltExpRating);

                    int selectedIndex = rand.nextInt(possibleRatings.size());
                    newAltExpRating.add(possibleRatings.get(selectedIndex));
                    possibleRatings.remove(selectedIndex);

                    currentRWP.rating().setAlternativeRatings(newAltExpRating);
                }
            }
        }

        return result;
    }

}
