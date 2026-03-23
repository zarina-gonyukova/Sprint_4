package ru.yandex.practicum.scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;

    // Кнопка "Заказать" в шапке
    private final By orderButtonHeader =
            By.xpath("(//button[contains(@class,'Button_Button') and text()='Заказать'])[1]");

    // Кнопка "Заказать" внизу страницы
    private final By orderButtonBottom =
            By.xpath("//div[contains(@class,'Home_FinishButton')]//button[text()='Заказать']");

    // Вопрос в аккордеоне "Вопросы о важном" по индексу (0–7)
    private By faqQuestion(int index) {
        // Вёрстка использует id вида accordion__heading-0, accordion__heading-1 и т.д.
        return By.id("accordion__heading-" + index);
    }

    // Ответ в аккордеоне по индексу (0–7)
    private By faqAnswer(int index) {
        // Вёрстка использует id вида accordion__panel-0, accordion__panel-1 и т.д.
        return By.id("accordion__panel-" + index);
    }

    // Логотип Самоката (для доп.задания)
    private final By scooterLogo = By.className("Header_LogoScooter__3lsAR");

    // Логотип Яндекса (для доп.задания)
    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    // Кнопка принятия cookies
    private final By cookieAcceptButton = By.id("rcc-confirm-button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Принимаем cookies, если баннер есть
    public void acceptCookies() {
        if (!driver.findElements(cookieAcceptButton).isEmpty()) {
            driver.findElement(cookieAcceptButton).click();
        }
    }

    public void clickOrderButtonHeader() {
        acceptCookies();
        driver.findElement(orderButtonHeader).click();
    }

    public void clickOrderButtonBottom() {
        acceptCookies();
        driver.findElement(orderButtonBottom).click();
    }

    // Клик по вопросу в аккордеоне по индексу
    public void clickFaqQuestion(int index) {
        acceptCookies();
        driver.findElement(faqQuestion(index)).click();
    }

    // Получение текста ответа по индексу
    public String getFaqAnswerText(int index) {
        return driver.findElement(faqAnswer(index)).getText();
    }

    public void clickScooterLogo() {
        acceptCookies();
        driver.findElement(scooterLogo).click();
    }

    public void clickYandexLogo() {
        acceptCookies();
        driver.findElement(yandexLogo).click();
    }
}

