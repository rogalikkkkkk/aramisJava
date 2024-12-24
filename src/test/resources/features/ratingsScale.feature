Feature: Is rating belongs to scale?
  Check if given value is in the given scale

  Scenario: Right rating is given
    Given App is in initial state
    When Expert rate "ЭВ" on "LONG" scale
    Then Answer should be "Your rating is good"

  Scenario: Not right rating is given
    Given App is in initial state
    When Expert rate "ЭВ" on "SHORT" scale
    Then Answer should be "Your rating is not good"