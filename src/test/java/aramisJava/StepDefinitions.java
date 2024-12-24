package aramisJava;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Scale;
import org.example.service.DistanceCalculationService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScaleAnswerConverter {
    static String convertAnswer(boolean result) {
        return result ? "Your rating is good" : "Your rating is not good";
    }

}

class RatingsAnswerConverter {
    static String convertAnswer(boolean result) {
        return result ? "Ratings with the same length" : "Ratings with different lengths";
    }
}

public class StepDefinitions {
    private boolean isInScale;
    private String actualAnswer;
    private Integer firstExpertRatingsCounts;
    private boolean firstExpertRatingsMatch;
    private Map<String, Integer> firstAlternativeRatings;
    private double calculatedDistance;

    @Given("App is in initial state")
    public void appIsInInitialState() {
    }

    @When("Expert rate {string} on {string} scale")
    public void expertRateRatingOnScaleScale(String rating, String scale) {
        Scale targetScale = Scale.valueOf(scale);

        String[] baseScaleRatings = {"ОВ", "В", "С", "Н", "ОН"};
        String[] longScaleRatings = {"ЭВ", "ОВ", "В", "С", "Н", "ОН", "ЭН"};
        String[] shortScaleRatings = {"В", "С", "Н"};

        switch (targetScale) {
            case LONG -> {
                isInScale = Arrays.asList(longScaleRatings).contains(rating);
            }
            case BASE -> {
                isInScale = Arrays.asList(baseScaleRatings).contains(rating);
            }
            case SHORT -> {
                isInScale = Arrays.asList(shortScaleRatings).contains(rating);
            }
        }

        actualAnswer = ScaleAnswerConverter.convertAnswer(isInScale);
    }

    @Then("Answer should be {string}")
    public void answerShouldBe(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

    @When("Expert {int} rate {string} with {string}")
    public void expertRateWith(int expertNumber, String altName, String ratings) {
        final String[] ratingsList = ratings.split(", ");
        if (firstExpertRatingsCounts == null) {
            firstExpertRatingsCounts = ratingsList.length;
        } else {
            firstExpertRatingsMatch = firstExpertRatingsCounts == ratingsList.length;
        }
    }

    @Then("Ratings answer should be {string}")
    public void ratingsAnswerShouldBe(String expectedAnswer) {
        assertEquals(expectedAnswer, RatingsAnswerConverter.convertAnswer(firstExpertRatingsMatch));
    }

    @When("Alternative has ratings {int}, {int}, {int}, {int}, {int}")
    public void alternativeHasRatings(int ov, int v, int s, int n, int on) {
        Map<String, Integer> newMap = new HashMap<String, Integer>() {
            {
                put("ОВ", ov);
                put("В", v);
                put("С", s);
                put("Н", n);
                put("ОН", on);
            }
        };
        if (firstAlternativeRatings == null) {
            firstAlternativeRatings = newMap;
        } else {
            calculatedDistance = DistanceCalculationService
                    .distanceBetweenRatings(firstAlternativeRatings, newMap);
        }
    }

    @Then("Answer should be {double}")
    public void answerShouldBe(double arg1) {
        assertEquals(calculatedDistance,arg1);
    }

}

