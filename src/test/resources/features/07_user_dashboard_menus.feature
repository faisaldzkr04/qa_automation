@user @ui @dashboard
Feature: Dashboard Navigation and Summary Cards (FSD 2. Dashboard)
  As an employee
  I want to view attendance summary cards and navigate to submenus
  So that I can monitor my attendance status and request approvals

  Background:
    Given User is already logged in as dummy user "hadirsqa1@gmail.com" with password "SQA@Hadir12345"

  @positive
  Scenario: [Positive] Verify Dashboard summary cards display (WFO, Cuti, Sakit, WFH)
    Then Dashboard should display summary cards for WFO, Cuti, Sakit, and WFH

  @negative
  Scenario: [Negative] Attempt to click on non-clickable read-only summary card
    When User attempts to click on read-only summary card "WFO"
    Then The card should not perform navigation and remain on the dashboard
