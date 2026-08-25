# team1-juaracoding-finalproject
Final Project untuk Pelatihan Juara Coding - SQA Automation Testing Framework

Framework otomatisasi pengujian lengkap untuk aplikasi **HADIR** (Web Admin & Mobile Mirroring) berbasis **Java + Maven + Cucumber BDD + TestNG + Selenium + REST Assured**.

---

## 📁 Struktur Folder Project

```text
tugas-akhir-kelompok-satu/
├── pom.xml                                  # Konfigurasi Maven & Dependensi
├── testng.xml                               # Suite runner TestNG
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/finalproject1/
│   │           ├── api/                     # REST Assured API Services & Endpoints
│   │           │   ├── ApiEndpoints.java
│   │           │   └── AuthApiService.java
│   │           ├── pages/                   # Page Object Model (POM)
│   │           │   ├── BasePage.java
│   │           │   ├── AdminDashboardPage.java
│   │           │   ├── AdminLaporanPage.java
│   │           │   ├── AdminLoginPage.java
│   │           │   ├── AdminManagementUserPage.java
│   │           │   ├── AdminMasterDataPage.java
│   │           │   ├── AttendancePage.java
│   │           │   ├── DashboardMenuPage.java
│   │           │   ├── RegisterPage.java
│   │           │   ├── UserDashboardPage.java
│   │           │   └── UserLoginPage.java
│   │           └── utils/                   # Config & WebDriver Factory
│   └── test/
│       ├── config.properties               # URL & Credentials konfigurasi
│       ├── features/                        # Skenario BDD Gherkin (.feature)
│       │   ├── 01_admin_login.feature
│       │   ├── 02_user_login.feature
│       │   ├── 03_user_absen.feature
│       │   ├── 04_api_auth.feature
│       │   ├── 05_user_registration.feature
│       │   ├── 06_absen_masuk_keluar.feature
│       │   ├── 07_user_dashboard_menus.feature
│       │   ├── 08_admin_management_user.feature
│       │   ├── 09_admin_laporan_approval.feature
│       │   └── 10_admin_master_data.feature
│       └── java/
│           └── com/finalproject1/
│               ├── runners/                 # TestNG Cucumber Runner
│               │   ├── TestRunner.java      # UI Test Runner
│               │   └── ApiTestRunner.java   # API Test Runner
│               └── stepdefinitions/         # Glue code Cucumber
```

---

## ⚙️ Prasyarat (Prerequisites)
1. **Java JDK 17 / 21**
2. **Apache Maven 3.8+**
3. **Google Chrome / Microsoft Edge** Browser

---

## 🚀 Cara Menjalankan Test (Running Tests)

### 1. Menjalankan Semua Test via Maven
```bash
mvn clean test
```

### 2. Menjalankan Berdasarkan Tag Tertentu
* **Hanya UI Tests**:
  ```bash
  mvn test -Dcucumber.filter.tags="@ui"
  ```
* **Hanya API Tests**:
  ```bash
  mvn test -Dcucumber.filter.tags="@api"
  ```
* **Hanya Smoke Test**:
  ```bash
  mvn test -Dcucumber.filter.tags="@smoke"
  ```
* **Mode Headless Browser (tanpa membuka window browser)**:
  Ubah di `tugas-akhir-kelompok-satu/src/test/config.properties`:
  ```properties
  headless=true
  ```

### 3. Menjalankan via IDE (IntelliJ IDEA / Eclipse / VS Code)
* Klik kanan pada file `testng.xml` -> Pilih **Run '.../testng.xml'**
* Atau klik kanan pada `TestRunner.java` / `ApiTestRunner.java` -> **Run**.

---

## 📊 Hasil Laporan Pengujian (Reports)
Setelah pengujian selesai, laporan HTML otomatis dibuat di:
* **UI Report**: `target/cucumber-reports/cucumber-pretty.html`
* **API Report**: `target/cucumber-reports/api-cucumber-pretty.html`
* Buka file `.html` tersebut langsung di browser Chrome/Edge untuk melihat grafik status passed/failed, logs, dan screenshot kegagalan.
