@user @ui @attendance
Feature: Attendance Flow (FSD B. Absen Masuk & C. Absen Keluar)
  As an employee
  I want to submit Absen Masuk and Absen Keluar
  So that my daily attendance record is completed

  Background:
    Given User is already logged in as dummy user "hadirsqa1@gmail.com" with password "SQA@Hadir12345"

  @positive @absen_masuk
  Scenario: [Positive] Successful Absen Masuk with Work From Office and valid note
    Given User is on Absen Masuk screen
    When User selects attendance type "Work From Office"
    And User enters optional note "Masuk kantor tepat waktu"
    And User clicks Absen Masuk button
    Then System should display successful attendance notification

  @negative @absen_masuk
  Scenario: [Negative] Failed Absen Masuk without selecting attendance type (Mandatory field empty)
    Given User is on Absen Masuk screen
    When User does not select attendance type
    And User clicks Absen Masuk button
    Then System should display validation error for missing attendance type

  @positive @absen_keluar
  Scenario: [Positive] Successful Absen Keluar with exit notes
    Given User is on Absen Keluar screen
    When User enters optional exit note "Selesai jam kerja"
    And User clicks Absen Pulang button
    Then System should display successful exit attendance notification

  @negative @absen_keluar
  Scenario: [Negative] Cancel Absen Keluar by navigating back to dashboard
    Given User is on Absen Keluar screen
    When User cancels exit attendance
    Then User should be returned to the main dashboard
