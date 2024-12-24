Feature: Is experts give same count of ratings?
  Check if experts give same amount of ratings for one alternative

  Scenario: Right counts is given
    Given App is in initial state
    When Expert 1 rate "Alternative 1" with "ОВ, Н"
    And Expert 2 rate "Alternative 1" with "ОН, В"
    Then Ratings answer should be "Ratings with the same length"

  Scenario: Not right counts is given
    Given App is in initial state
    When Expert 1 rate "Alternative 1" with "ОВ, Н"
    And Expert 2 rate "Alternative 1" with "ОВ, Н, ОН"
    Then Ratings answer should be "Ratings with different lengths"