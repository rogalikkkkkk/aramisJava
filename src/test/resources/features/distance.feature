Feature: Is distance calculates right?
  Check if distance between given multisets is right

  Scenario: Right distance calculated
    Given App is in initial state
    When Alternative has ratings 3, 0, 0, 0, 0
    And Alternative has ratings 0, 0, 0, 0, 3
    Then Answer should be 6.0

  Scenario: Incorrect distance calculated
    Given App is in initial state
    When Alternative has ratings 3, 0, 0, 0, 0
    And Alternative has ratings 0, 0, 0, 0, 3
    Then Answer should be 6.5