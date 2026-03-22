package ru.yandex.practicum.scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPageStepOne {

    private final WebDriver driver;

    // Поля первой части формы заказа
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    private final By metroFirstOption = By.className("select-search__row");
    private final By phoneInput = By.xpath(
            "//input[@placeholder='* Телефон: на него позвонит курьер']"
    );

    private final By nextButton = By.xpath("//button[text()='Далее']");

    public OrderPageStepOne(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнение данных клиента
    public void fillClientInfo(String name, String surname, String address,
                               String metro, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);

        driver.findElement(metroInput).click();
        driver.findElement(metroInput).sendKeys(metro);
        // Для простоты выбираем первую станцию в выпадающем списке
        driver.findElement(metroFirstOption).click();

        driver.findElement(phoneInput).sendKeys(phone);
    }

    // Методы для проверки, что данные действительно попали в поля

    public String getNameValue() {
        return driver.findElement(nameInput).getAttribute("value");
    }

    public String getSurnameValue() {
        return driver.findElement(surnameInput).getAttribute("value");
    }

    public String getAddressValue() {
        return driver.findElement(addressInput).getAttribute("value");
    }

    public String getMetroValue() {
        // Вёрстка может отличаться, минимально проверяем value инпута
        return driver.findElement(metroInput).getAttribute("value");
    }

    public String getPhoneValue() {
        return driver.findElement(phoneInput).getAttribute("value");
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }
}
