@admin @ui @management
Feature: Admin User Management (Manual Book Section 22)
  As a Hadir Admin
  I want to manage employee user accounts, register new users, and filter user lists
  So that employee access is properly configured

  Background:
    Given User is already logged in as Admin with email "admin@hadir.com" and password "MagangSQA_JC@123"

  @positive @user_management
  Scenario: [Positive] Search and filter user by existing name
    Given Admin is on User Management page
    When Admin searches user with keyword "Hadir"
    Then User table should display matching user records

  @negative @user_management
  Scenario: [Negative] Search user by non-existent NIK
    Given Admin is on User Management page
    When Admin searches user with keyword "NONEXISTENT_NIK_99999"
    Then User table should display no records or empty state

  @positive @pendaftaran_user
  Scenario: [Positive] Admin registers new employee with complete valid form
    Given Admin is on Pendaftaran User page
    When Admin fills account info NIK "D6240099", Name "QA Automation Employee", Email "qa.emp99@hadir.com", Password "Pass@12345"
    And Admin submits employee registration
    Then Registration should be successfully processed

  @negative @pendaftaran_user
  Scenario: [Negative] Admin submits employee registration with blank mandatory NIK
    Given Admin is on Pendaftaran User page
    When Admin fills account info NIK "", Name "QA Incomplete Employee", Email "qa.incomplete@hadir.com", Password "Pass@12345"
    And Admin submits employee registration
    Then Form validation error should prevent submission
