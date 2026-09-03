Feature: MainFeatureTest.feature

  @Test
  Scenario: Validate the application is installed successfully
    Given The user installed the application

  @Test
  Scenario: Validate the user is able to launch the application
    Given The user launched the application

  @Test
  Scenario: Validate the user is able to login successfully
    Given The user launched the application
    And The user is on the login screen
    And The user enters valid credentials
    When The user clicks on the login button
    Then The user should be logged in successfully