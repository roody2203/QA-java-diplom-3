package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class ConstructorWebTest extends  BaseWebTest {


    @Test
    @DisplayName("Check click to bun test")
    public void checkClickToBunTest() {

        // Открыли стартовую страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver, driverWaiter);
        // Кликаем на кнопку Соусы
        homePage.clickSaucesButton();
        // Кликаем на кнопку Булки
        homePage.clickBunButton();
        // Проверяем видимость поля Булки
        MatcherAssert.assertThat("Bun field is not visible", homePage.isCurrentBunField(), is(true));
    }

    @Test
    @DisplayName("Check click to sauces test")
    public void checkClickToSaucesTest() {

        // Открыли стартовую страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver, driverWaiter);
        // Кликаем на кнопку Соусы
        homePage.clickSaucesButton();

        // Проверяем видимость поля Соусы
        MatcherAssert.assertThat("Sauces field is not visible", homePage.isCurrentSaucesField(), is(true));
    }

    @Test
    @DisplayName("Check click to toppings test")
    public void checkClickToToppingsTest() {

        // Открыли стартовую страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver, driverWaiter);
        // Кликаем на кнопку Начинки
        homePage.clickToppingsButton();

        // Проверяем видимость поля Начинки
        MatcherAssert.assertThat("Toppings field is not visible", homePage.isCurrentToppingsField(), is(true));
    }
}