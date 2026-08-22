@admin @ui @master_data
Feature: Admin Master Data Management (Manual Book Section 22.D - 22.J)
  As a Hadir Admin
  I want to configure master data for Divisi, Unit, Posisi, and Absen Point
  So that the organizational structure and location rules are updated

  Background:
    Given User is already logged in as Admin with email "admin@hadir.com" and password "MagangSQA_JC@123"

  @positive @master_search
  Scenario: [Positive] Search existing Divisi master data
    Given Admin is on Master Data "Divisi" page
    When Admin filters master data by name "IT"
    Then Master data table should be displayed

  @negative @master_search
  Scenario: [Negative] Search non-existent Divisi master data
    Given Admin is on Master Data "Divisi" page
    When Admin filters master data by name "UNKNOWN_DIVISI_99999"
    Then Master data table should display no records or empty state

  @positive @master_add
  Scenario: [Positive] Add new Divisi master data
    Given Admin is on Master Data "Divisi" page
    When Admin clicks Tambahkan button
    And Admin inputs master name "Quality Assurance Unit" and submits
    Then Master data table should be displayed

  @negative @master_add
  Scenario: [Negative] Add Divisi master data with blank name
    Given Admin is on Master Data "Divisi" page
    When Admin clicks Tambahkan button
    And Admin inputs master name "" and submits
    Then Form validation error should prevent saving empty master data
