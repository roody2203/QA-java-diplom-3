package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class ConstructorWebTest extends  BaseWebTest {


    @Test
    @DisplayName("Check transition to bun test")
    public void checkTransitionToBunTest() {

        // Открыли стартовую страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver);
        // Кликаем на кнопку Соусы
        homePage.clickSaucesButton();
        // Кликаем на кнопку Булки
        homePage.clickBunButton();

        // Проверяем видимость поля Булки
        homePage.checkVisibleBunField();
    }

    @Test
    @DisplayName("Check transition to sauces test")
    public void checkTransitionToSaucesTest() {

        // Открыли стартовую страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver);
        // Кликаем на кнопку Соусы
        homePage.clickSaucesButton();

        // Проверяем видимость поля Соусы
        homePage.checkVisibleSaucesField();
    }

    @Test
    @DisplayName("Check transition to toppings test")
    public void checkTransitionToToppingsTest() {

        // Открыли стартовую страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver);
        // Кликаем на кнопку Начинки
        homePage.clickToppingsButton();

        // Проверяем видимость поля Начинки
        homePage.checkVisibleToppingsField();
    }
}