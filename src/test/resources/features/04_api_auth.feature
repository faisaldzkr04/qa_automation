@api @auth
Feature: REST API Authentication Testing
  As an API Consumer / QA Tester
  I want to test backend authentication endpoints using REST Assured
  So that API security and contract requirements are verified

  @positive
  Scenario: [Positive] Successful Admin Login via API returns 200 OK and JWT Token
    Given The API request payload with email "admin@hadir.com" and password "MagangSQA_JC@123"
    When QA sends POST request to endpoint "/api/v1/auth/login" or authentication route
    Then The response status code should be 200 or 201
    And The response body should contain a valid "token" or "accessToken"

  @negative
  Scenario: [Negative] Admin Login via API with invalid password returns 401 Unauthorized
    Given The API request payload with email "admin@hadir.com" and password "WrongPassword123"
    When QA sends POST request to endpoint "/api/v1/auth/login" or authentication route
    Then The response status code should be 400 or 401
    And The response body should contain error message
