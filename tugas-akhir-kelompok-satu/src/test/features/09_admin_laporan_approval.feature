@admin @ui @laporan
Feature: Admin Laporan and Approvals
  As a Hadir Admin
  I want to view attendance reports, filter by dates, and download reports
  So that employee attendance and leave requests are managed

  Background:
    Given User is already logged in as Admin with email "admin@hadir.com" and password "MagangSQA_JC@123"

  # ==========================================
  # GROUP 1: Laporan Menus (Semua, Kehadiran, Izin, etc.)
  # ==========================================

  @positive @laporan_general
  Scenario Outline: [Positive] Validate UI elements and positive search on <menu_name> Report
    Given Admin is on Laporan "<menu_name>" page
    Then Admin should see search input, search button, reset button, filter by unit departemen, start date, and end date
    When Admin selects start date and end date and clicks Save
    Then The report table should display "<expected_column>" column
    And Export button visibility should be "<has_export>"

    Examples:
      | menu_name          | expected_column      | has_export |
      | Semua              | semua laporan        | false      |
      | Kehadiran          | kehadiran            | true       |
      | Izin               | izin                 | false      |
      | Izin Terlambat     | izin terlambat       | false      |
      | Izin Pulang Cepat  | izin pulang cepat    | false      |
      | Cuti               | cuti                 | false      |
      | Sakit              | sakit                | false      |
      | Lembur             | lembur               | true       |
      | Validasi Lembur    | validasi lembur      | false      |
      | Shifting           | shifting             | false      |
      | Meeting            | meeting              | false      |
      | Perjalanan Dinas   | perjalanan           | false      |
      | Koreksi            | koreksi              | false      |
      | Izin Off           | izin off             | false      |

  @negative @laporan_general
  Scenario Outline: [Negative] Validate search with invalid name on <menu_name> Report
    Given Admin is on Laporan "<menu_name>" page
    When Admin inputs search by name "InvalidUserXYZ999"
    And Admin clicks Search button
    Then The report table should display no data found

    Examples:
      | menu_name          |
      | Semua              |
      | Kehadiran          |
      | Izin               |
      | Izin Terlambat     |
      | Izin Pulang Cepat  |
      | Cuti               |
      | Sakit              |
      | Lembur             |
      | Validasi Lembur    |
      | Shifting           |
      | Meeting            |
      | Perjalanan Dinas   |
      | Koreksi            |
      | Izin Off           |

  # ==========================================
  # GROUP 2: Download Absen
  # ==========================================

  @positive @download_absen
  Scenario: [Positive] Validate UI elements and download on Download Absen page
    Given Admin is on Laporan "Download Absen" page
    Then Admin should see start date, end date, input NIK, input Nama user, input Upliner, input Divisi, input Unit, input Tipe Report, Download button, and Reset button
    When Admin fills all inputs on Download Absen form
    And Admin clicks Download button
    Then The absen report should be downloaded successfully

  @negative @download_absen
  Scenario: [Negative] Reset functionality on Download Absen page
    Given Admin is on Laporan "Download Absen" page
    When Admin fills all inputs on Download Absen form
    And Admin clicks Reset button
    Then All inputs on Download Absen form should be cleared

  # ==========================================
  # GROUP 3: Download Absen v2
  # ==========================================

  @positive @download_absen_v2
  Scenario: [Positive] Validate UI elements and download on Download Absen v2 page
    Given Admin is on Laporan "Download Absen v2" page
    Then Admin should see start date, end date, input NIK, input Nama user, input Upliner, input Unit, Download button, and Reset button
    When Admin fills all inputs on Download Absen V2 form
    And Admin clicks Download button
    Then The absen v2 report should be downloaded successfully

  @negative @download_absen_v2
  Scenario: [Negative] Reset functionality on Download Absen v2 page
    Given Admin is on Laporan "Download Absen v2" page
    When Admin fills all inputs on Download Absen V2 form
    And Admin clicks Reset button
    Then All inputs on Download Absen V2 form should be cleared

  # ==========================================
  # GROUP 4: Sisa Cuti
  # ==========================================

  @positive @sisa_cuti
  Scenario: [Positive] Validate UI elements and download on Sisa Cuti page
    Given Admin is on Laporan "Sisa Cuti" page
    Then Admin should see input NIK, input Nama user, input Upliner, Download button, and Reset button
    When Admin fills all inputs on Sisa Cuti form
    And Admin clicks Download button
    Then The sisa cuti report should be downloaded successfully

  @negative @sisa_cuti
  Scenario: [Negative] Reset functionality on Sisa Cuti page
    Given Admin is on Laporan "Sisa Cuti" page
    When Admin fills all inputs on Sisa Cuti form
    And Admin clicks Reset button
    Then All inputs on Sisa Cuti form should be cleared

  # ==========================================
  # GROUP 5: Daily Report
  # ==========================================

  @positive @daily_report
  Scenario: [Positive] Validate UI elements and download on Daily Report page
    Given Admin is on Laporan "Daily Report" page
    Then Admin should see tanggal penarikan data, Download button, and Reset button
    When Admin selects tanggal penarikan data
    And Admin clicks Download button
    Then The daily report should be downloaded successfully

  @negative @daily_report
  Scenario: [Negative] Reset functionality on Daily Report page
    Given Admin is on Laporan "Daily Report" page
    When Admin selects tanggal penarikan data
    And Admin clicks Reset button
    Then The tanggal penarikan data should be cleared
