package com.finalproject1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardMenuPage extends BasePage {

    // Status Cards
    private final By cardWfo = By.xpath("//*[contains(text(),'WFO')]");
    private final By cardCuti = By.xpath("//*[contains(text(),'Cuti')]");
    private final By cardSakit = By.xpath("//*[contains(text(),'Sakit')]");
    private final By cardWfh = By.xpath("//*[contains(text(),'WFH')]");
    private final By cardLembur = By.xpath("//*[contains(text(),'Lembur')]");
    private final By cardTerlambat = By.xpath("//*[contains(text(),'Terlambat')]");
    private final By cardPulangCepat = By.xpath("//*[contains(text(),'Pulang Cepat')]");

    // Action Submenus
    private final By menuAbsensi = By.xpath("//*[contains(text(),'Absensi')]");
    private final By menuKoreksiAbsen = By.xpath("//*[contains(text(),'Koreksi Absen')]");
    private final By menuIzin = By.xpath("//*[contains(text(),'Izin')]");
    private final By menuLembur = By.xpath("//*[contains(text(),'Lembur')]");
    private final By menuCuti = By.xpath("//*[contains(text(),'Cuti')]");
    private final By menuSakit = By.xpath("//*[contains(text(),'Sakit')]");

    public DashboardMenuPage(WebDriver driver) {
        super(driver);
    }

    public boolean areDashboardCardsDisplayed() {
        return isElementDisplayed(cardWfo) && isElementDisplayed(cardCuti) && isElementDisplayed(cardSakit);
    }

    public boolean isWfhCardDisplayed() {
        return isElementDisplayed(cardWfh);
    }

    public boolean isLemburCardDisplayed() {
        return isElementDisplayed(cardLembur);
    }

    public boolean isTerlambatCardDisplayed() {
        return isElementDisplayed(cardTerlambat);
    }

    public boolean isPulangCepatCardDisplayed() {
        return isElementDisplayed(cardPulangCepat);
    }

    public void clickMenu(String menuName) {
        switch (menuName.toLowerCase()) {
            case "absensi" -> click(menuAbsensi);
            case "koreksi absen" -> click(menuKoreksiAbsen);
            case "izin" -> click(menuIzin);
            case "lembur" -> click(menuLembur);
            case "cuti" -> click(menuCuti);
            case "sakit" -> click(menuSakit);
            default -> {
                By dynamicMenu = By.xpath("//*[contains(text(),'" + menuName + "')]");
                click(dynamicMenu);
            }
        }
    }
}
