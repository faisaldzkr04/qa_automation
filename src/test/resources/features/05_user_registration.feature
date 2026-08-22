@user @ui @registration
Feature: User Account Registration (FSD A. Tampilan Registrasi Akun)
  As a new employee
  I want to register an account in Hadir Mobile
  So that I can use the attendance application

  Background:
    Given User is on Hadir Registration page

  @positive
  Scenario: [Positive] Successful user registration with valid data and mandatory fields
    When User fills registration form with NIK "D6240005", Name "Employee Testing", Email "test.emp@hadir.com", Password "Pass@12345"
    And User submits the registration form
    Then A success notification or redirection should occur

  @negative
  Scenario: [Negative] Failed user registration with invalid email format (no @ symbol)
    When User fills registration form with NIK "D6240005", Name "Employee Testing", Email "invalidemailformat.com", Password "Pass@12345"
    And User submits the registration form
    Then A validation error for invalid email format should be displayed
