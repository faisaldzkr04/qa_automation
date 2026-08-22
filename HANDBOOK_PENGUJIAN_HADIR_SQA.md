# 📘 BUKU PANDUAN PENGUJIAN & LAPORAN HASIL PENGUJIAN (HANDBOOK & TEST REPORT)
## APLIKASI HADIR (WEB ADMIN & HADIR MOBILE) — SQA BATCH 29

---

## 📌 ATURAN WAJIB PENGUJIAN (STANDAR SQA BATCH 29)
> [!IMPORTANT]
> **Aturan Pengujian**: Setiap fitur/fungsionalitas di setiap menu **WAJIB memiliki minimal 1 Positive Scenario dan 1 Negative Scenario** (skenario tambahan bersifat opsional).

---

## 🧪 MATRIKS SKENARIO 1 POSITIVE & 1 NEGATIVE PER FUNGSIONAL

| ID Test Case | Menu / Fungsional | Tipe | Skenario Pengujian | Input / Test Data | Expected Result | Status |
| :--- | :--- | :---: | :--- | :--- | :--- | :---: |
| **TC-ADM-AUTH-01** | Admin Login | **Positive** | Login admin dengan kredensial valid | `admin@hadir.com` / `MagangSQA_JC@123` | Berhasil redirect ke Dashboard Admin | ✅ **PASSED** |
| **TC-ADM-AUTH-02** | Admin Login | **Negative** | Login admin dengan email salah | `wrongadmin@hadir.com` / `MagangSQA_JC@123` | Muncul validasi `"Email atau password salah"` | ✅ **PASSED** |
| **TC-USR-AUTH-01** | User Mobile Login | **Positive** | Login karyawan dengan kredensial valid | `hadirsqa1@gmail.com` / `SQA@Hadir12345` | Masuk ke Dashboard Hadir Mobile | ✅ **PASSED** |
| **TC-USR-AUTH-02** | User Mobile Login | **Negative** | Login karyawan dengan email belum terdaftar | `unregistered@hadir.com` / `SQA@Hadir12345` | Muncul validasi `"Akun tidak ditemukan"` | ✅ **PASSED** |
| **TC-REG-01** | Registrasi Akun | **Positive** | Pendaftaran mandiri dengan data lengkap | NIK: `D6240005`, Email valid, Foto selfie | Pendaftaran berhasil & muncul notifikasi | ✅ **PASSED** |
| **TC-REG-02** | Registrasi Akun | **Negative** | Pendaftaran dengan format email tanpa `@` | Email: `invalidemailformat.com` | Muncul validasi format email tidak valid | ✅ **PASSED** |
| **TC-ABS-IN-01** | Absen Masuk | **Positive** | Absen masuk WFO dengan catatan | Tipe: `Work From Office`, Note: `Tepat waktu` | Muncul notifikasi berhasil absen masuk | ✅ **PASSED** |
| **TC-ABS-IN-02** | Absen Masuk | **Negative** | Absen masuk tanpa memilih tipe absen | Tipe Absen: *(Kosong)* | Muncul validasi tipe absen wajib diisi | ✅ **PASSED** |
| **TC-ABS-OUT-01** | Absen Keluar | **Positive** | Absen pulang dengan catatan | Note: `Selesai jam kerja` | Muncul notifikasi berhasil absen pulang | ✅ **PASSED** |
| **TC-ABS-OUT-02** | Absen Keluar | **Negative** | Batal absen pulang dan kembali ke menu | Klik tombol Back / Cancel | Kembali ke dashboard tanpa submit absen | ✅ **PASSED** |
| **TC-DASH-01** | Dashboard Mobile | **Positive** | Verifikasi tampilan card statistik | Cards: WFO, Cuti, Sakit, WFH | Statistik status kehadiran ditampilkan | ✅ **PASSED** |
| **TC-DASH-02** | Dashboard Mobile | **Negative** | Klik pada card read-only (bukan tombol) | Klik Card: `WFO` | Tidak berpindah halaman (tetap di dashboard) | ✅ **PASSED** |
| **TC-USR-MGT-01** | User Management | **Positive** | Cari karyawan terdaftar by Nama/NIK | Keyword: `Hadir` | Data karyawan muncul di tabel | ✅ **PASSED** |
| **TC-USR-MGT-02** | User Management | **Negative** | Cari karyawan dengan NIK tidak terdaftar | Keyword: `NONEXISTENT_NIK_99999` | Menampilkan pesan data kosong (*0 of 0*) | ✅ **PASSED** |
| **TC-USR-ADD-01** | Pendaftaran User | **Positive** | Admin tambah karyawan form lengkap | NIK: `D6240099`, Email: `qa.emp99@hadir.com` | Karyawan baru berhasil terdaftar | ✅ **PASSED** |
| **TC-USR-ADD-02** | Pendaftaran User | **Negative** | Admin submit karyawan dengan NIK kosong | NIK: `""` (Kosong) | Form dicegah tersimpan & muncul error | ✅ **PASSED** |
| **TC-LAP-01** | Laporan Kehadiran | **Positive** | Filter laporan kehadiran rentang tanggal | Nama: `Hadir`, Tanggal: `2024-01-01` s/d `2024-12-31` | Tabel memuat rekap data absensi | ✅ **PASSED** |
| **TC-LAP-02** | Laporan Kehadiran | **Negative** | Filter laporan dengan nama tidak ada | Nama: `UnknownEmployeeXYZ999` | Tabel menampilkan status kosong | ✅ **PASSED** |
| **TC-MST-01** | Master Divisi | **Positive** | Tambah master divisi baru | Nama Divisi: `Quality Assurance Unit` | Data divisi tersimpan di list | ✅ **PASSED** |
| **TC-MST-02** | Master Divisi | **Negative** | Tambah master divisi dengan nama kosong | Nama Divisi: `""` (Kosong) | Validasi mencegah data kosong tersimpan | ✅ **PASSED** |
| **TC-API-01** | REST API Auth | **Positive** | Login API dengan body kredensial valid | POST `/api/v1/auth/login` valid body | Status `200 OK` & Response Token JWT | ✅ **PASSED** |
| **TC-API-02** | REST API Auth | **Negative** | Login API dengan password salah | POST `/api/v1/auth/login` invalid pass | Status `401 Unauthorized` | ✅ **PASSED** |

---

## 💻 CARA MENJALANKAN PENGUJIAN OTOMATIS (AUTOMATION RUNNERS)

```bash
# Menjalankan SEMUA skenario (Positive & Negative)
mvn clean test

# Menjalankan HANYA skenario POSITIVE
mvn test -Dcucumber.filter.tags="@positive"

# Menjalankan HANYA skenario NEGATIVE
mvn test -Dcucumber.filter.tags="@negative"

# Menjalankan pengujian Web Admin saja
mvn test -Dcucumber.filter.tags="@admin"

# Menjalankan pengujian Hadir Mobile saja
mvn test -Dcucumber.filter.tags="@user"
```

Laporan hasil uji HTML interaktif tersimpan di **`target/cucumber-reports/cucumber-pretty.html`**.
