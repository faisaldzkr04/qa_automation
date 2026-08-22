# team1-juaracoding-finalproject
Final Project untuk Pelatihan Juara Coding - SQA Automation Testing Framework

Framework otomatisasi pengujian lengkap untuk aplikasi **HADIR** (Web Admin & Mobile Mirroring) berbasis **Java + Maven + Cucumber BDD + TestNG + Selenium + REST Assured**.

---

## 📁 Struktur Folder Project

```text
free_TA/
├── pom.xml                                  # Konfigurasi Maven & Dependensi
├── testng.xml                               # Suite runner TestNG
├── README.md                                # Petunjuk penggunaan framework
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/hadir/
│   │           ├── api/                     # REST Assured API Services & Endpoints
│   │           │   ├── ApiEndpoints.java
│   │           │   └── AuthApiService.java
│   │           ├── pages/                   # Page Object Model (POM)
│   │           │   ├── BasePage.java
│   │           │   ├── AdminLoginPage.java
│   │           │   ├── UserLoginPage.java
│   │           │   └── UserDashboardPage.java
│   │           └── utils/                   # Config & WebDriver Factory
│   │               ├── ConfigReader.java
│   │               └── DriverFactory.java
│   └── test/
│       ├── java/
│       │   └── com/hadir/
│       │       ├── runners/                 # TestNG Cucumber Runner
│       │       │   ├── TestRunner.java      # UI Test Runner
│       │       │   └── ApiTestRunner.java   # API Test Runner
│       │       └── stepdefinitions/         # Glue code Cucumber
│       │           ├── Hooks.java           # Setup & Teardown WebDriver per scenario
│       │           ├── AdminLoginSteps.java
│       │           ├── UserLoginSteps.java
│       │           └── ApiAuthSteps.java
│       └── resources/
│           ├── config.properties            # URL & Credentials konfigurasi
│           └── features/                    # Skenario BDD Gherkin (.feature)
│               ├── 01_admin_login.feature
│               ├── 02_user_login.feature
│               ├── 03_user_absen.feature
│               └── 04_api_auth.feature
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
  Ubah di `src/test/resources/config.properties`:
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
