@search
Feature: Search Functionality

  Scenario Outline: Search and Validate Item Price
    Given user is on landing page
    When user search for "<item>"
    And user clicks on 1st item in the listed results
    And user assets new book price
    And user clicks on "<button1>" button
    Then verify cart subtotal amount
    And user clicks on "<button2>" button
#  LAST STEP REQUIRES USER LOGIN
   # Then verify order total amount

    Examples:
      | item                     | button1      | button2             |
      | qa testing for beginners | Add to Cart  | Proceed to Checkout |