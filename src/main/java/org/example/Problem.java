package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Problem {
    private String problemName;
    private List<Criterion> criteria;

    @JsonCreator
    public Problem(
            @JsonProperty("problemName") String problemName,
            @JsonProperty("criteria") List<Criterion> criteria) {
        this.problemName = problemName;
        this.criteria = criteria;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public List<Criterion> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criterion> criteria) {
        this.criteria = criteria;
    }
}
