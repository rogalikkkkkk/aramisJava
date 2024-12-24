package org.example;

import java.util.Objects;

public final class RatingWithProblem {
    private final Rating rating;
    private final Problem problem;

    public RatingWithProblem(Rating rating, Problem problem) {
        this.rating = rating;
        this.problem = problem;
    }

    public Rating rating() {
        return rating;
    }

    public Problem problem() {
        return problem;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RatingWithProblem) obj;
        return Objects.equals(this.rating, that.rating) &&
                Objects.equals(this.problem, that.problem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating, problem);
    }

    @Override
    public String toString() {
        return "RatingWithProblem[" +
                "rating=" + rating + ", " +
                "problem=" + problem + ']';
    }


}

