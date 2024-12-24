package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Scale {
    LONG,
    BASE,
    SHORT;

    @JsonCreator
    public static Scale fromString(String value) {
        try {
            return Scale.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid scale value: " + value);
        }
    }
     public static Scale fromString2(String value) {
        return Scale.valueOf(value.toUpperCase());
     }

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }
}

