# ==============================================================================
# SCRIPT OTOMASI TESTING ADMIN LAPORAN PER MENU (HADIR SQA AUTOMATION)
# ==============================================================================
# Script ini digunakan untuk menjalankan test modul Admin Laporan satu per satu
# berdasarkan menu pilihan atau menjalankan seluruh menu satu per satu secara berurutan.
# ==============================================================================

param (
    [string]$Menu = "",
    [switch]$All,
    [switch]$DryRun
)

# Pastikan working directory berada di folder tempat pom.xml berada
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition
if (Test-Path "$scriptDir\pom.xml") {
    Set-Location $scriptDir
} elseif (Test-Path "$scriptDir\tugas-akhir-kelompok-satu\pom.xml") {
    Set-Location "$scriptDir\tugas-akhir-kelompok-satu"
}

$featurePath = "src/test/features/09_admin_laporan_approval.feature"

# Definisi Daftar 18 Submenu Admin Laporan
$menuList = @(
    @{ Id = 1;  Nama = "Semua";              Filter = "-Dcucumber.filter.name=.*on Semua Report";            Tipe = "name" },
    @{ Id = 2;  Nama = "Kehadiran";          Filter = "-Dcucumber.filter.name=.*on Kehadiran Report";        Tipe = "name" },
    @{ Id = 3;  Nama = "Izin";               Filter = "-Dcucumber.filter.name=.*on Izin Report";             Tipe = "name" },
    @{ Id = 4;  Nama = "Izin Terlambat";     Filter = "-Dcucumber.filter.name=.*on Izin Terlambat Report";   Tipe = "name" },
    @{ Id = 5;  Nama = "Izin Pulang Cepat";  Filter = "-Dcucumber.filter.name=.*on Izin Pulang Cepat Report";Tipe = "name" },
    @{ Id = 6;  Nama = "Cuti";               Filter = "-Dcucumber.filter.name=.*on Cuti Report";             Tipe = "name" },
    @{ Id = 7;  Nama = "Sakit";              Filter = "-Dcucumber.filter.name=.*on Sakit Report";            Tipe = "name" },
    @{ Id = 8;  Nama = "Lembur";             Filter = "-Dcucumber.filter.name=.*on Lembur Report";           Tipe = "name" },
    @{ Id = 9;  Nama = "Validasi Lembur";    Filter = "-Dcucumber.filter.name=.*on Validasi Lembur Report";  Tipe = "name" },
    @{ Id = 10; Nama = "Shifting";           Filter = "-Dcucumber.filter.name=.*on Shifting Report";         Tipe = "name" },
    @{ Id = 11; Nama = "Meeting";            Filter = "-Dcucumber.filter.name=.*on Meeting Report";          Tipe = "name" },
    @{ Id = 12; Nama = "Perjalanan Dinas";   Filter = "-Dcucumber.filter.name=.*on Perjalanan Dinas Report"; Tipe = "name" },
    @{ Id = 13; Nama = "Koreksi";            Filter = "-Dcucumber.filter.name=.*on Koreksi Report";          Tipe = "name" },
    @{ Id = 14; Nama = "Izin Off";           Filter = "-Dcucumber.filter.name=.*on Izin Off Report";         Tipe = "name" },
    @{ Id = 15; Nama = "Download Absen";     Filter = "-Dcucumber.filter.tags=@download_absen";              Tipe = "tag"  },
    @{ Id = 16; Nama = "Download Absen v2";  Filter = "-Dcucumber.filter.tags=@download_absen_v2";           Tipe = "tag"  },
    @{ Id = 17; Nama = "Sisa Cuti";          Filter = "-Dcucumber.filter.tags=@sisa_cuti";                   Tipe = "tag"  },
    @{ Id = 18; Nama = "Daily Report";       Filter = "-Dcucumber.filter.tags=@daily_report";                Tipe = "tag"  }
)

function Run-SingleTest {
    param (
        [hashtable]$Item,
        [boolean]$IsDryRun = $false
    )

    Write-Host ""
    Write-Host "=================================================================" -ForegroundColor Cyan
    Write-Host " [RUNNING TEST] Submenu Laporan : $($Item.Nama)" -ForegroundColor Yellow
    Write-Host "=================================================================" -ForegroundColor Cyan

    $argsList = @(
        "test",
        "-Dcucumber.features=$featurePath",
        $Item.Filter
    )

    if ($IsDryRun) {
        $argsList += "-Dcucumber.execution.dry-run=true"
        Write-Host "Mode: Dry-Run (Validasi skenario tanpa membuka browser)" -ForegroundColor DarkGray
    }

    $startTime = Get-Date
    & mvn @argsList
    $exitCode = $LASTEXITCODE
    $duration = (Get-Date) - $startTime

    Write-Host ""
    if ($exitCode -eq 0) {
        Write-Host "==> HASIL [$($Item.Nama)]: PASSED / SUCCESS (Waktu: $($duration.ToString('mm\:ss')))" -ForegroundColor Green
    } else {
        Write-Host "==> HASIL [$($Item.Nama)]: FAILED / ERROR (Exit Code: $exitCode)" -ForegroundColor Red
    }
    Write-Host "=================================================================" -ForegroundColor Cyan
    return $exitCode
}

# -------------------------------------------------------------
# Mode 1: Jika dipanggil dengan flag -All
# -------------------------------------------------------------
if ($All) {
    Write-Host "Menjalankan seluruh 18 submenu Admin Laporan satu per satu..." -ForegroundColor Green
    $passed = 0
    $failed = 0

    foreach ($item in $menuList) {
        $res = Run-SingleTest -Item $item -IsDryRun $DryRun
        if ($res -eq 0) { $passed++ } else { $failed++ }
        Start-Sleep -Seconds 2
    }

    Write-Host ""
    Write-Host "==================== REKAP EKSEKUSI ====================" -ForegroundColor Magenta
    Write-Host "Total Menu Diuji : $($menuList.Count)"
    Write-Host "Berhasil         : $passed" -ForegroundColor Green
    Write-Host "Gagal            : $failed" -ForegroundColor Red
    Write-Host "=========================================================" -ForegroundColor Magenta
    exit 0
}

# -------------------------------------------------------------
# Mode 2: Jika dipanggil dengan parameter -Menu <Nama atau Nomor>
# -------------------------------------------------------------
if ($Menu -ne "") {
    $found = $null
    if ($Menu -match '^\d+$') {
        $targetId = [int]$Menu
        $found = $menuList | Where-Object { $_.Id -eq $targetId }
    } else {
        $found = $menuList | Where-Object { $_.Nama -like "*$Menu*" }
    }

    if ($found) {
        Run-SingleTest -Item $found -IsDryRun $DryRun
        exit 0
    } else {
        Write-Host "Menu '$Menu' tidak ditemukan dalam daftar!" -ForegroundColor Red
    }
}

# -------------------------------------------------------------
# Mode 3: Menu Interaktif
# -------------------------------------------------------------
do {
    Clear-Host
    Write-Host "=================================================================" -ForegroundColor Cyan
    Write-Host "         PILIHAN TESTING ADMIN LAPORAN PER MENU                  " -ForegroundColor Yellow
    Write-Host "                 HADIR SQA AUTOMATION SUITE                      " -ForegroundColor Yellow
    Write-Host "=================================================================" -ForegroundColor Cyan
    Write-Host "Pilih nomor menu yang ingin Anda jalankan secara terpisah:" -ForegroundColor White
    Write-Host ""

    for ($i = 0; $i -lt $menuList.Count; $i += 2) {
        $item1 = $menuList[$i]
        $str1 = " [{0,2}] {1,-18}" -f $item1.Id, $item1.Nama
        if ($i + 1 -lt $menuList.Count) {
            $item2 = $menuList[$i + 1]
            $str2 = " [{0,2}] {1,-18}" -f $item2.Id, $item2.Nama
            Write-Host "$str1 $str2" -ForegroundColor Gray
        } else {
            Write-Host "$str1" -ForegroundColor Gray
        }
    }

    Write-Host ""
    Write-Host " [A]  Jalankan SEMUA Menu Satu per Satu Berurutan" -ForegroundColor Green
    Write-Host " [Q]  Keluar" -ForegroundColor Red
    Write-Host "=================================================================" -ForegroundColor Cyan
    $pilihan = Read-Host "Masukkan Pilihan Anda (1-18 / A / Q)"

    if ($pilihan -eq 'Q' -or $pilihan -eq 'q') {
        Write-Host "Keluar dari script." -ForegroundColor Yellow
        break
    }

    if ($pilihan -eq 'A' -or $pilihan -eq 'a') {
        foreach ($item in $menuList) {
            Run-SingleTest -Item $item -IsDryRun $DryRun
            Start-Sleep -Seconds 2
        }
        Read-Host "Seluruh menu selesai dijalankan. Tekan ENTER untuk kembali ke menu..."
        continue
    }

    if ($pilihan -match '^\d+$') {
        $num = [int]$pilihan
        $target = $menuList | Where-Object { $_.Id -eq $num }
        if ($target) {
            Run-SingleTest -Item $target -IsDryRun $DryRun
            Write-Host ""
            Read-Host "Test selesai. Tekan ENTER untuk kembali ke menu..."
        } else {
            Write-Host "Nomor tidak valid. Silakan pilih 1-18." -ForegroundColor Red
            Start-Sleep -Seconds 1
        }
    } else {
        Write-Host "Input tidak valid!" -ForegroundColor Red
        Start-Sleep -Seconds 1
    }

} while ($true)
