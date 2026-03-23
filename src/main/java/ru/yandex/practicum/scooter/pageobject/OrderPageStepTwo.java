package ru.yandex.practicum.scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPageStepTwo {

    private final WebDriver driver;

    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.className("Dropdown-control");
    private final By colorBlackCheckbox = By.id("black");
    private final By colorGreyCheckbox = By.id("grey");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton =
            By.xpath("//button[text()='Заказать' and contains(@class,'Button_Middle')]");
    private final By yesButtonInModal = By.xpath("//button[text()='Да']");
    private final By orderModal = By.xpath("//div[contains(@class,'Order_Modal')]");

    public OrderPageStepTwo(WebDriver driver) {
        this.driver = driver;
    }

    public void setDate(String date) {
        driver.findElement(dateInput).sendKeys(date);
        driver.findElement(dateInput).sendKeys(Keys.ENTER);
    }

    public void setRentalPeriod(String periodText) {
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(By.xpath(
                "//div[@class='Dropdown-option' and text()='" + periodText + "']")
        ).click();
    }

    public void setColor(String color) {
        if ("black".equalsIgnoreCase(color)) {
            driver.findElement(colorBlackCheckbox).click();
        } else if ("grey".equalsIgnoreCase(color)) {
            driver.findElement(colorGreyCheckbox).click();
        }
    }

    public void setComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void submitOrder() {
        driver.findElement(orderButton).click();
        driver.findElement(yesButtonInModal).click();
    }

    // Проверка, что модальное окно заказа появилось
    public boolean isOrderCreated() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(orderModal));
        return driver.findElement(orderModal).isDisplayed();
    }

    // Методы для проверки выбранного цвета

    public boolean isBlackSelected() {
        return driver.findElement(colorBlackCheckbox).isSelected();
    }

    public boolean isGreySelected() {
        return driver.findElement(colorGreyCheckbox).isSelected();
    }
}

