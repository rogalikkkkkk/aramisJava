package org.example.service;

import org.example.Problem;
import org.example.Rating;
import org.example.RatingWithProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JoinService {
    public List<RatingWithProblem> mapRatingsToCriteria(
            String problemName,
            List<Problem> problems,
            List<Rating> ratings
    ) {
        List<RatingWithProblem> result = new ArrayList<>();

        Optional<Problem> problemFromArray = problems.stream().filter(p -> p.getProblemName().equals(problemName)).findFirst();

        if (problemFromArray.isPresent()) {
            Problem selectedProblem = problemFromArray.get();
            List<Rating> ratingsFilteredByProblem = ratings.stream().filter(rating -> rating.getProblemName().equals(problemName)).toList();

            for (Rating rating : ratingsFilteredByProblem) {
                RatingWithProblem ratingWithProblem = new RatingWithProblem(
                        rating,
                        selectedProblem
                );

                result.add(ratingWithProblem);
            }
        } else {
            System.err.println("Проблема с именем '" + problemName + "' не найдена.");
        }

        return result;
    }
}
