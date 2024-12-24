package org.example.service;

import org.example.Multiset;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DistanceCalculationService {
    public double calculateDistance(Multiset alternativeMultiset, Multiset idealMultiset) {
        double totalDistance = 0;
        for (int i = 0; i < alternativeMultiset.criteria.size(); i++) {
            final Map<String, Integer> alternativeRatings = alternativeMultiset.ratingCounts.get(i);
            final Map<String, Integer> idealRatings = idealMultiset.ratingCounts.get(i);

            totalDistance += distanceBetweenRatings(alternativeRatings, idealRatings);
        }

        return totalDistance;
    }

    public static double distanceBetweenRatings(Map<String, Integer> alternativeRatings, Map<String, Integer> idealRatings) {
        final List<String> ratingsKeys = new ArrayList<>(alternativeRatings.keySet());
        double totalDistance = 0;
        for (String ratingKey : ratingsKeys) {
            totalDistance += Math.abs(alternativeRatings.get(ratingKey) - idealRatings.get(ratingKey));
        }
        return totalDistance;
    }
}
