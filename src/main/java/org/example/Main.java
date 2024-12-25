package org.example;

import org.example.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        // PARAMETERS
        boolean generateData = true;
        int alternativesCount = 5;
        int criteriaCount = 5;
        int expertsCount = 5;

        // Number of values must be same as number of all ratings
        Map<String, Integer> ratingDistribution = new HashMap<>() {{
            put("ОВ", 5);
            put("В", 5);
            put("С", 5);
            put("Н", 5);
            put("ОН", 5);
        }};

        List<RatingWithProblem> joinedRatings;

        if (!generateData) {
            ProblemService problemService = new ProblemService();
            AlternativeService alternativeService = new AlternativeService();
            RatingService ratingService = new RatingService();
            List<Problem> problems = problemService.loadProblems("/problems.json");
            List<Alternative> alternatives = alternativeService.loadAlternatives("/alternatives.json");
            List<Rating> ratings = ratingService.loadRatings("/ratings.json");

            JoinService joinService = new JoinService();
            joinedRatings = joinService.mapRatingsToCriteria("Problem 1", problems, ratings);

            Map<String, List<RatingWithProblem>> groupedByAlternativeName = joinedRatings.stream()
                    .collect(Collectors.groupingBy(rwp -> rwp.rating().getAlternativeName()));

            System.out.println("Alternatives and their ratings from jsons");

            groupedByAlternativeName.forEach((alternativeName, group) -> {
                System.out.println("Alternative Name: " + alternativeName);
                group.forEach(rwp -> System.out.println("  Rating: " + rwp.rating().getAlternativeRatings()));
            });
        } else {
            joinedRatings = RatingsGenerator.generateData(
                    alternativesCount,
                    criteriaCount,
                    expertsCount,
                    ratingDistribution
            );
        }


        System.out.println("-------------------------\n");
        System.out.println(generateData ? "Generated ratings" : "Normalized ratings");

        UpdateRatingsService updateRatingsService = new UpdateRatingsService();
        List<RatingWithProblem> newGrouped = updateRatingsService.mapOldRatingsToNew(joinedRatings);

        Map<String, List<RatingWithProblem>> alternativesWithItsRatings = newGrouped.stream()
                .collect(Collectors.groupingBy(rwp -> rwp.rating().getAlternativeName()));

        alternativesWithItsRatings.forEach((alternativeName, group) -> {
            System.out.println("Alternative Name: " + alternativeName);
            group.forEach(rwp -> System.out.println("  Rating: " + rwp.rating().getAlternativeRatings()));
        });

        System.out.println("-------------------------\n");
        System.out.println("Multisets for every alternative");

        List<Multiset> alternativesMultiset = new ArrayList<>();

        alternativesWithItsRatings.forEach((alternativeName, group) -> {
            Multiset newMultiset = new Multiset(alternativeName, group);
            alternativesMultiset.add(newMultiset);
            System.out.println(newMultiset);
        });

        System.out.println("------------------------\n");
        System.out.println("Extreme multisets");

        final String nameOfFirst = alternativesMultiset.getFirst().alternativeName;
        final int numOfRatingToOneAlternative = alternativesWithItsRatings.get(nameOfFirst).size();
        final List<Criterion> criteria = alternativesWithItsRatings.get(nameOfFirst).getFirst().problem().getCriteria();

        final Multiset bestMultiset = new Multiset("A-plus", criteria, numOfRatingToOneAlternative, true);
        final Multiset worstMultiset = new Multiset("A-minus", criteria, numOfRatingToOneAlternative, false);

        System.out.println(bestMultiset);
        System.out.println(worstMultiset);

        DistanceCalculationService distanceCalculationService = new DistanceCalculationService();

        for (Multiset multiset : alternativesMultiset) {
            multiset.setDistanceToBest(distanceCalculationService.calculateDistance(multiset, bestMultiset));
            multiset.setDistanceToWorst(distanceCalculationService.calculateDistance(multiset, worstMultiset));
        }

        System.out.println("------------------------\n");
        System.out.println("Multisets for every alternative with distances");

        alternativesMultiset.forEach(System.out::println);

        alternativesMultiset.sort((Multiset o1, Multiset o2) -> {
            double score1 = o1.distanceToBest / (o1.distanceToBest + o1.distanceToWorst);
            double score2 = o2.distanceToBest / (o2.distanceToBest + o2.distanceToWorst);
            return Double.compare(score1, score2);
        });

        System.out.println("------------------------\n");
        System.out.println("Sorted alternatives multisets with relative distance");

        for (Multiset multiset : alternativesMultiset) {
            System.out.println(multiset);
            System.out.println("Relative distance: " + multiset.distanceToBest / (multiset.distanceToBest + multiset.distanceToWorst));
        }

    }
}
