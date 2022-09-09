Feature: login feature


  Scenario: Test login functionality
    Given I am at the Login page
    When I fill the account email text box with value '{email}'
    And I fill the password text box with value '{password}'
    And I click the login button