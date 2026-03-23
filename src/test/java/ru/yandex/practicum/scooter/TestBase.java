package ru.yandex.practicum.scooter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// Можно при желании добавить Firefox:
// import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;

    @Before
    public void setUp() {
        // Все тесты тестируются в Chrome.
        // При желании можно вынести выбор браузера в переменную окружения.
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Неявное ожидание на весь тест (5 секунд)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        // Требование задания: браузер должен закрываться после каждого теста
        if (driver != null) {
            driver.quit();
        }
    }
}
