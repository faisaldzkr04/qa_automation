@admin @ui @laporan
Feature: Admin Laporan and Approvals (Manual Book Section 4 - 14)
  As a Hadir Admin
  I want to view attendance reports, filter by dates, and approve or reject employee requests
  So that employee attendance and leave requests are managed

  Background:
    Given User is already logged in as Admin with email "admin@hadir.com" and password "MagangSQA_JC@123"

  @positive @laporan_kehadiran
  Scenario: [Positive] Filter and view Kehadiran Report with valid date range
    Given Admin is on Laporan "Kehadiran" page
    When Admin filters report by employee name "Hadir", start date "2024-01-01", and end date "2024-12-31"
    Then Report data table should be displayed

  @negative @laporan_kehadiran
  Scenario: [Negative] Filter Kehadiran Report with non-existent employee name
    Given Admin is on Laporan "Kehadiran" page
    When Admin filters report by employee name "UnknownEmployeeXYZ999", start date "2024-01-01", and end date "2024-12-31"
    Then Report table should display no data found

  @positive @laporan_approval
  Scenario: [Positive] Open Lembur Approval page to view pending requests
    Given Admin is on Laporan "Lembur" page
    Then Report data table should be displayed

  @negative @laporan_approval
  Scenario: [Negative] Reset report filter returns data table to default state
    Given Admin is on Laporan "Lembur" page
    When Admin filters report by employee name "DummySearchFilter", start date "2024-01-01", and end date "2024-12-31"
    And Admin clicks reset button
    Then Report data table should be displayed
