Feature: Filter calendar

  Scenario: Filter calendar by currency and importance for the current month
    Given calendar is opened
    When select period "CURRENT MONTH"
    And filter by importance "MEDIUM"
    And filter by currency "CHF"
    And open first event with currency "CHF"
    Then importance "MEDIUM" is displayed
    And country "Switzerland" is displayed
    And log history table
