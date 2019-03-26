@filterby
Feature: Filter
  As an end user
  I want to apply filter on the results
  So that i can view results of my filter choice


  Background: search for a product
    Given I am on homepage
    When I search for a product "nike"

  @regression
  Scenario Outline: Filter By Rating
    And I apply filter "<filterValue>" on search result
    Then I should see all products "review" are filtered "<expectedValue>"

    Examples:
      | filterValue | expectedValue |
      | 4or more    | 4.0           |
      | 3or more    | 3.0           |
      | 2or more    | 2.0           |


  @regression
  Scenario: Filter by Price range
    And I apply filter "£10 - £15" on search result
    Then I should see all products "range" are filtered "10-15"