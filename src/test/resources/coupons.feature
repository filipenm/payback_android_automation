Feature: Coupons feature
  As a user
  I want to choose a coupon by a partner
  So that I will have some advantage

  Scenario: Test coupons functionality
    Given I am at the Login page
    When I log in with account number '{cardNumber}' and password '{password}'
    And I click the login button
    And I proceed to coupons page
    And I filter by partner and activate a coupon
    Then I see an activated coupon