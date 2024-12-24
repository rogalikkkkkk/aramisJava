package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Rating {

    private final String problemName;
    private final String alternativeName;
    private final List<String> alternativeRatings;

    @JsonCreator
    public Rating(
            @JsonProperty("problemName") String problemName,
            @JsonProperty("alternativeName") String alternativeName,
            @JsonProperty("alternativeRatings") List<String> alternativeRatings) {
        this.problemName = problemName;
        this.alternativeName = alternativeName;
        this.alternativeRatings = alternativeRatings;
    }

    public String getProblemName() {
        return problemName;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public List<String> getAlternativeRatings() {
        return alternativeRatings;
    }


}
