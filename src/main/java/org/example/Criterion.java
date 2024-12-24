package org.example;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Criterion {
    private final String criterionName;
    private final Scale criterionScale;
    private final OptimizationDirection optimizationDirection;

    @JsonCreator
    public Criterion(
            @JsonProperty("criterionName") String criterionName,
            @JsonProperty("criterionScale") Scale criterionScale,
            @JsonProperty("optimizationDirection") OptimizationDirection optimizationDirection) {
        this.criterionName = criterionName;
        this.criterionScale = criterionScale;
        this.optimizationDirection = optimizationDirection;
    }

    public String getCriterionName() {
        return criterionName;
    }

    public Scale getCriterionScale() {
        return criterionScale;
    }

    public OptimizationDirection getOptimizationDirection() {
        return optimizationDirection;
    }
}

