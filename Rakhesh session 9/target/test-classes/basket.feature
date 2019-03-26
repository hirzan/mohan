@basket
Feature: Basket
  As an end user
  I want to add a product to basket
  So that i buy products

  @smoke @rk
  Scenario: add product to basket
    Given I am on homepage
    When I search for a product "puma"
    And I select any product
    And I add the product to the basket
    Then the product should be in the basket

