@user @ui @absen
Feature: User Attendance Camera and Modal
  As an Employee of HADIR
  I want to interact with attendance popups
  So that I can verify attendance camera requirements

  Background:
    Given User is already logged in as dummy user "hadirsqa1@gmail.com" with password "SQA@Hadir12345"

  @positive
  Scenario: [Positive] Click Absen Masuk displays camera preview modal
    When User clicks on "Absen Masuk" button
    Then The attendance confirmation or selfie capture popup should appear
    And Camera preview or capture action should be active

  @negative
  Scenario: [Negative] User cancels or closes attendance camera modal without taking photo
    When User clicks on "Absen Masuk" button
    And User closes the attendance popup without submitting
    Then User should remain on the User Home / Attendance page
