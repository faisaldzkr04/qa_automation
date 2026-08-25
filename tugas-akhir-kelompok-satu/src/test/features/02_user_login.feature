@user @ui @login
Feature: User Mobile Mirroring Authentication
  As a User/Employee of HADIR application
  I want to login with my user credentials
  So that I can access the Hadir Mobile dashboard

  Background:
    Given User is on Hadir User login page

  @positive
  Scenario: [Positive] Successful user login with valid credentials
    When User enters user email "hadirsqa1@gmail.com" and password "SQA@Hadir12345"
    And User clicks user login submit button
    Then User should be redirected to the User Home Attendance page

  @negative
  Scenario: [Negative] Failed user login with wrong email
    When User enters user email "unregistered@hadir.com" and password "SQA@Hadir12345"
    And User clicks user login submit button
    Then A validation message or warning "Akun tidak ditemukan" should appear
