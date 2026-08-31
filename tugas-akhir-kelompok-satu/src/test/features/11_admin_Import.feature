@admin @ui @import
Feature: Admin Import Management
  As a Hadir Admin
  I want to download templates and import data for Absen, Status Aktif, Struktur User, and Cuti
  So that bulk employee data can be imported and updated in the system

  Background:
    Given User is already logged in as Admin with email "admin@hadir.com" and password "MagangSQA_JC@123"

  @positive @import_absen
  Scenario: [Positive] Download template Import Absen
    Given Admin is on Import "Import Absen" page
    When Admin clicks Download Template button
    Then Template Excel file should be downloaded successfully

  @positive @import_absen
  Scenario: [Positive] Import Absen with valid Excel file
    Given Admin is on Import "Import Absen" page
    When Admin uploads valid Excel file "DATA_ABSEN_HADIR.xlsx"
    And Admin clicks Import button
    Then Success pop-up notification "berhasil import excel" should be displayed

  @negative @import_absen
  Scenario: [Negative] Import Absen with invalid Non-Excel file
    Given Admin is on Import "Import Absen" page
    When Admin uploads non-Excel file "document.pdf"
    And Admin clicks Import button
    Then Validation error message "File harus berupa file Excel (.xls atau .xlsx)" should be displayed

  @positive @import_absen
  Scenario: [Positive] Verify Page Title and Card Title on Import Absen page
    Given Admin is on Import "Import Absen" page
    Then Page title should display "Import Absen"
    And Card title should display "Import Excel Absen Hadir"

  @positive @import_status_aktif
  Scenario: [Positive] Download template Import Status Aktif
    Given Admin is on Import "Import Status Aktif" page
    When Admin clicks Download Template button
    Then Template Excel file should be downloaded successfully

  @positive @import_status_aktif
  Scenario: [Positive] Import Status Aktif with valid Excel file
    Given Admin is on Import "Import Status Aktif" page
    When Admin uploads valid Excel file "DATA_STATUS_USER.xlsx"
    And Admin clicks Import button
    Then Success pop-up notification "berhasil import excel" should be displayed

  @negative @import_status_aktif
  Scenario: [Negative] Import Status Aktif with invalid Non-Excel file
    Given Admin is on Import "Import Status Aktif" page
    When Admin uploads non-Excel file "document.pdf"
    And Admin clicks Import button
    Then Validation error message "File harus berupa file Excel (.xls atau .xlsx)" should be displayed

  @positive @import_status_aktif
  Scenario: [Positive] Verify Page Title and Card Title on Import Status Aktif page
    Given Admin is on Import "Import Status Aktif" page
    Then Page title should display "Import Status Aktif"
    And Card title should display "Import Excel Status Aktif"

  @positive @import_struktur_user
  Scenario: [Positive] Download template Import Struktur User
    Given Admin is on Import "Import Struktur User" page
    When Admin clicks Download Template button
    Then Template Excel file should be downloaded successfully

  @positive @import_struktur_user
  Scenario: [Positive] Import Struktur User with valid Excel file
    Given Admin is on Import "Import Struktur User" page
    When Admin uploads valid Excel file "DATA_STRUKTUR_USER.xlsx"
    And Admin clicks Import button
    Then Success pop-up notification "berhasil import excel" should be displayed

  @negative @import_struktur_user
  Scenario: [Negative] Import Struktur User with invalid Non-Excel file
    Given Admin is on Import "Import Struktur User" page
    When Admin uploads non-Excel file "document.pdf"
    And Admin clicks Import button
    Then Validation error message "File harus berupa file Excel (.xls atau .xlsx)" should be displayed

  @positive @import_struktur_user
  Scenario: [Positive] Verify Page Title and Card Title on Import Struktur User page
    Given Admin is on Import "Import Struktur User" page
    Then Page title should display "Import Struktur User"
    And Card title should display "Import Excel Struktur User"

  @positive @import_cuti
  Scenario: [Positive] Download template Import Cuti
    Given Admin is on Import "Import Cuti" page
    When Admin clicks Download Template button
    Then Template Excel file should be downloaded successfully

  @positive @import_cuti
  Scenario: [Positive] Import Cuti with valid Excel file
    Given Admin is on Import "Import Cuti" page
    When Admin uploads valid Excel file "DATA_CUTI.xlsx"
    And Admin clicks Import button
    Then Success pop-up notification "berhasil import excel" should be displayed

  @negative @import_cuti
  Scenario: [Negative] Import Cuti with invalid Non-Excel file
    Given Admin is on Import "Import Cuti" page
    When Admin uploads non-Excel file "document.pdf"
    And Admin clicks Import button
    Then Validation error message "File harus berupa file Excel (.xls atau .xlsx)" should be displayed

  @positive @import_cuti
  Scenario: [Positive] Verify Page Title and Card Title on Import Cuti page
    Given Admin is on Import "Import Cuti" page
    Then Page title should display "Import Cuti"
    And Card title should display "Import Excel Absen Hadir"
