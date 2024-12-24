package org.example.service;

import org.example.*;

import java.util.*;

public class UpdateRatingsService {
    public List<RatingWithProblem> mapOldRatingsToNew(List<RatingWithProblem> ratingWithProblem) {
        List<RatingWithProblem> newRatings = new ArrayList<RatingWithProblem>();
        final String[] baseArray = {"ОВ", "В", "С", "Н", "ОН"};
        final Map<String, String> baseMap = new HashMap<String, String>() {
            {
                put("ОВ", "ОВ");
                put("В", "В");
                put("С", "С");
                put("Н", "Н");
                put("ОН", "ОН");
            }
        };
        final Map<String, String> longToBaseMap = new HashMap<>() {
            {
                put("ЭВ", "ОВ");
                put("ОВ", "В");
                put("В", "В");
                put("С", "С");
                put("Н", "Н");
                put("ОН", "Н");
                put("ЭН", "ОН");
            }
        };
        final Map<String, String> shortToBaseMap = new HashMap<String, String>() {
            {
                put("В", "ОВ");
                put("С", "С");
                put("Н", "ОН");
            }
        };
        for (RatingWithProblem rating : ratingWithProblem) {
            final List<String> oldRatings = rating.rating().getAlternativeRatings();
            final List<Criterion> criteria = rating.problem().getCriteria();

            List<String> newRaStrings = new ArrayList<>();

            for (int i = 0; i < oldRatings.size(); i++) {
                final String oldRating = oldRatings.get(i);
                final Criterion criterion = criteria.get(i);

                if (criterion.getCriterionScale() == Scale.LONG) {
                    if (criterion.getOptimizationDirection() == OptimizationDirection.MAX) {
                        newRaStrings.add(longToBaseMap.get(oldRating));
                    } else {
                        final String convertedBaseRating = longToBaseMap.get(oldRating);
                        final int initIndex = Arrays.asList(baseArray).indexOf(convertedBaseRating);
                        final int correctedIndex = baseArray.length - 1 - initIndex;
                        newRaStrings.add(Arrays.asList(baseArray).get(correctedIndex));
                    }
                } else if (criterion.getCriterionScale() == Scale.SHORT) {
                    if (criterion.getOptimizationDirection() == OptimizationDirection.MAX) {
                        newRaStrings.add(shortToBaseMap.get(oldRating));
                    } else {
                        final String convertedBaseRating = shortToBaseMap.get(oldRating);
                        final int initIndex = Arrays.asList(baseArray).indexOf(convertedBaseRating);
                        final int correctedIndex = baseArray.length - 1 - initIndex;
                        newRaStrings.add(Arrays.asList(baseArray).get(correctedIndex));
                    }
                } else {
                    if (criterion.getOptimizationDirection() == OptimizationDirection.MAX) {
                        newRaStrings.add(baseMap.get(oldRating));
                    } else {
                        final String convertedBaseRating = baseMap.get(oldRating);
                        final int initIndex = Arrays.asList(baseArray).indexOf(convertedBaseRating);
                        final int correctedIndex = baseArray.length - 1 - initIndex;
                        newRaStrings.add(Arrays.asList(baseArray).get(correctedIndex));
                    }
                }
            }
            final Rating newRating = new Rating(rating.rating().getProblemName(), rating.rating().getAlternativeName(), newRaStrings);
            final RatingWithProblem newRWP = new RatingWithProblem(newRating, rating.problem());

            newRatings.add(newRWP);
        }
        return newRatings;
    }
}
