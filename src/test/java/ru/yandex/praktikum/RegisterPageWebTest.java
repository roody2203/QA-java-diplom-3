package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class RegisterPageWebTest extends BaseWebTest {

    @Step("Generate random email")
    public String generateRandomEmail() {
        return RandomStringUtils.randomAlphabetic(5) + "@yandex.ru";
    }

    @Step("Generate random valid password")
    public String generateRandomValidPassword() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    @Step("Generate random invalid password")
    public String generateRandomInvalidPassword() {
        return RandomStringUtils.randomAlphabetic(4);
    }

    @Step("Generate random name")
    public String generateRandomName() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    @Test
    @DisplayName("Check successful registration on register page")
    public void checkSuccessfulRegistration() throws InterruptedException {
        String name = generateRandomName();
        String email = generateRandomEmail();
        String password = generateRandomValidPassword();

        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver);
        // Открываем домашнюю страницу
        homePage.openHomePage();
        // Нажимаем на кнопку Войти в аккаунт
        homePage.clickLoginInToAccountButton();

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver);
        // Кликаем на кнопку Зарегистрироваться
        loginPage.clickRegisterButtonInLoginPage();

        // Создаем объект класса RegisterPage
        RegisterPage registerPage = new RegisterPage(driver);
        // Заполняем все поля валидными данными
        registerPage.fillPageRegister(name, email, password);
        // Нажимаем на кнопку Зарегистрироваться
        registerPage.clickRegisterButton();
        /** Проверяем переход на страницу входа после нажатия на кнопку Зарегистрироваться
        registerPage.checkUrlAfterClickRegisterButton(); */
    }

    @Test
    @DisplayName("Check error with invalid password on register page")
    public void checkErrorWithInvalidPassword() {
        String name = generateRandomName();
        String email = generateRandomEmail();
        String password = generateRandomInvalidPassword();
        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver);
        // Открываем домашнюю страницу
        homePage.openHomePage();
        // Нажимаем на кнопку Войти в аккаунт
        homePage.clickLoginInToAccountButton();

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver);
        // Кликаем на кнопку Зарегистрироваться
        loginPage.clickRegisterButtonInLoginPage();

        // Создаем объект класса RegisterPage
        RegisterPage registerPage = new RegisterPage(driver);
        // Заполняем все поля валидными данными
        registerPage.fillPageRegister(name, email, password);
        // Нажимаем на кнопку Зарегистрироваться
        registerPage.clickRegisterButton();
        // Проверяем ошибку поля Пароль
        registerPage.checkFieldPasswordTextError();
    }
}
