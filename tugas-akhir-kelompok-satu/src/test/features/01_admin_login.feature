@admin @ui @login
Feature: Admin Authentication
  As an Admin of HADIR application
  I want to authenticate with my credentials
  So that I can access the Admin portal

  Background:
    Given User is on Hadir Admin login page

  @positive
  Scenario: [Positive] Successful admin login with valid credentials
    When User enters admin email "admin@hadir.com" and password "MagangSQA_JC@123"
    And User clicks the login button
    Then User should be redirected to the Admin Dashboard
    And Admin dashboard header should be displayed

  @negative
  Scenario: [Negative] Failed admin login with wrong email
    When User enters admin email "wrongadmin@hadir.com" and password "MagangSQA_JC@123"
    And User clicks the login button
    Then An error alert or message "Email atau password salah" should be displayed
