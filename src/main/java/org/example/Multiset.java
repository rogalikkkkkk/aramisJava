package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Multiset {
    public final String alternativeName;
    public final List<Map<String, Integer>> ratingCounts;
    public final List<Criterion> criteria;
    double distanceToBest;
    double distanceToWorst;
    Map<String, Integer> defaultMap = new HashMap<>() {{
        put("ОВ", 0);
        put("В", 0);
        put("С", 0);
        put("Н", 0);
        put("ОН", 0);
    }};

    public Multiset(String alternativeName, List<RatingWithProblem> ratingWithProblems) {
        this.alternativeName = alternativeName;
        List<Map<String, Integer>> res = new ArrayList<Map<String, Integer>>();
        this.criteria = ratingWithProblems.getFirst().problem().getCriteria();

        for (int i = 0; i < criteria.size(); i++) {
            Map<String, Integer> map = new HashMap<String, Integer>(defaultMap);
            for (RatingWithProblem rwp : ratingWithProblems) {
                final String ratingOfAlternativeByICriterion = rwp.rating().getAlternativeRatings().get(i);
                map.put(ratingOfAlternativeByICriterion, map.get(ratingOfAlternativeByICriterion) + 1);
            }
            res.add(map);
        }
        this.ratingCounts = res;
    }

    public Multiset(String alternativeName, List<Criterion> criteria, int ratingsCount, boolean isBest) {
        this.alternativeName = alternativeName;
        this.criteria = criteria;
        List<Map<String, Integer>> res = new ArrayList<Map<String, Integer>>();

        for (int i = 0; i < criteria.size(); i++) {
            Map<String, Integer> map = new HashMap<String, Integer>(defaultMap);
            if (isBest) {
                map.put("ОВ", ratingsCount);
            } else {
                map.put("ОН", ratingsCount);
            }
            res.add(map);
        }
        this.ratingCounts = res;
    }

    public void setDistanceToBest(double distanceToBest) {
        this.distanceToBest = distanceToBest;
    }

    public void setDistanceToWorst(double distanceToWorst) {
        this.distanceToWorst = distanceToWorst;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Multiset{\n")
                .append("  alternativeName='").append(alternativeName).append("',\n")
                .append("  ratingCounts=[\n");

        for (int i = 0; i < ratingCounts.size(); i++) {
            sb.append("    ").append(criteria.get(i).getCriterionName()).append(": ").append(ratingCounts.get(i)).append("\n");
        }

        sb.append("  distanceToBest=").append(distanceToBest).append(",\n");
        sb.append("  distanceToWorst=").append(distanceToWorst).append(",\n");

        sb.append("  ]\n}");
        return sb.toString();
    }

}
