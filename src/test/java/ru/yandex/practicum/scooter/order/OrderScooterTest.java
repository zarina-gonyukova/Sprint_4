package ru.yandex.practicum.scooter.order;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.scooter.TestBase;
import ru.yandex.practicum.scooter.pageobject.HomePage;
import ru.yandex.practicum.scooter.pageobject.OrderPageStepOne;
import ru.yandex.practicum.scooter.pageobject.OrderPageStepTwo;

@RunWith(Parameterized.class)
public class OrderScooterTest extends TestBase {

    // Параметры заказа
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;
    private final String orderButtonPosition; // "header" или "bottom"

    public OrderScooterTest(String name, String surname, String address,
                            String metro, String phone, String date,
                            String period, String color, String comment,
                            String orderButtonPosition) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
        this.orderButtonPosition = orderButtonPosition;
    }

    @Parameterized.Parameters(name = "{index}: {0} {1}, кнопка={9}, цвет={7}")
    public static Object[][] getData() {
        // Два набора данных + две точки входа (кнопка сверху и снизу)
        return new Object[][]{
                {"Иван", "Иванов", "ул. Тестовая, 1", "Черкизовская",
                        "+79991234567", "20.03.2026", "сутки",
                        "black", "Позвонить за 30 минут", "header"},
                {"Пётр", "Петров", "пр. Автотестов, 2", "Сокол",
                        "+79997654321", "21.03.2026", "двое суток",
                        "grey", "Не звонить", "bottom"},
        };
    }

    @Test
    public void orderScooterPositiveFlow() {
        HomePage homePage = new HomePage(driver);

        // Параметризованная точка входа: верхняя или нижняя кнопка "Заказать"
        if ("header".equals(orderButtonPosition)) {
            homePage.clickOrderButtonHeader();
        } else {
            homePage.clickOrderButtonBottom();
        }

        // Шаг 1: Заполнение данных клиента
        OrderPageStepOne stepOne = new OrderPageStepOne(driver);
        stepOne.fillClientInfo(name, surname, address, metro, phone);

        // Автоматизированная проверка, что данные действительно попали в поля
        Assert.assertEquals("Имя в поле не совпадает с ожидаемым",
                name, stepOne.getNameValue());
        Assert.assertEquals("Фамилия в поле не совпадает с ожидаемой",
                surname, stepOne.getSurnameValue());
        Assert.assertEquals("Адрес в поле не совпадает с ожидаемым",
                address, stepOne.getAddressValue());
        Assert.assertEquals("Станция метро в поле не совпадает с ожидаемой",
                metro, stepOne.getMetroValue());
        Assert.assertEquals("Телефон в поле не совпадает с ожидаемым",
                phone, stepOne.getPhoneValue());

        stepOne.clickNext();

        // Шаг 2: Параметризованные данные аренды и цвет
        OrderPageStepTwo stepTwo = new OrderPageStepTwo(driver);
        stepTwo.setDate(date);
        stepTwo.setRentalPeriod(period);
        stepTwo.setColor(color);
        stepTwo.setComment(comment);

        // Проверяем, что выбран нужный цвет самоката
        if ("black".equalsIgnoreCase(color)) {
            Assert.assertTrue("Чёрный цвет должен быть выбран", stepTwo.isBlackSelected());
            Assert.assertFalse("Серый цвет не должен быть выбран", stepTwo.isGreySelected());
        } else if ("grey".equalsIgnoreCase(color)) {
            Assert.assertTrue("Серый цвет должен быть выбран", stepTwo.isGreySelected());
            Assert.assertFalse("Чёрный цвет не должен быть выбран", stepTwo.isBlackSelected());
        }

        // Отправляем заказ и проверяем модальное окно
        stepTwo.submitOrder();
        Assert.assertTrue("Модальное окно заказа не появилось", stepTwo.isOrderCreated());
    }
}

