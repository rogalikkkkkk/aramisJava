package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OptimizationDirection {
    MAX,
    MIN;

    @JsonCreator
    public static OptimizationDirection fromString(String value) {
        try {
            return OptimizationDirection.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid scale value: " + value);
        }
    }

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }
}
