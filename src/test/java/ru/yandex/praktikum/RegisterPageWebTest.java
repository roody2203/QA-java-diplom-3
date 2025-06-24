package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.praktikum.dto.LoginUserRequest;
import ru.yandex.praktikum.steps.DeleteUser;
import ru.yandex.praktikum.steps.LoginUser;

import static org.hamcrest.CoreMatchers.is;

public class RegisterPageWebTest extends BaseWebTest {
    String name = generateRandomName();
    String email = generateRandomEmail();

    @Step("Generate random invalid password")
    public String generateRandomInvalidPassword() {
        return RandomStringUtils.randomAlphabetic(4);
    }


    @Test
    @DisplayName("Check successful registration on register page")
    public void checkSuccessfulRegistration() throws InterruptedException {
        String password = generateRandomValidPassword();

        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver, driverWaiter);
        // Открываем домашнюю страницу
        homePage.openHomePage();
        // Нажимаем на кнопку Войти в аккаунт
        homePage.clickLoginInToAccountButton();

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver, driverWaiter);
        // Кликаем на кнопку Зарегистрироваться
        loginPage.clickRegisterButtonInLoginPage();

        // Создаем объект класса RegisterPage
        RegisterPage registerPage = new RegisterPage(driver, driverWaiter);
        // Заполняем все поля валидными данными
        registerPage.fillPageRegister(name, email, password);
        // Нажимаем на кнопку Зарегистрироваться
        registerPage.clickRegisterButton();
        // Ожидаем перехода на страницу
        driverWaiter.until(ExpectedConditions.urlContains(EnvConfig.REGISTER_URL));
        // Проверяем переход на страницу Входа после нажатия на кнопку Зарегистрироваться
        MatcherAssert.assertThat("Некорректный url", driver.getCurrentUrl(), is(EnvConfig.REGISTER_URL));

        deleteUserByAPI(email, password);
    }

    @Test
    @DisplayName("Check error with invalid password on register page")
    public void checkErrorWithInvalidPassword() {
        String password = generateRandomInvalidPassword();
        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver, driverWaiter);
        // Открываем домашнюю страницу
        homePage.openHomePage();
        // Нажимаем на кнопку Войти в аккаунт
        homePage.clickLoginInToAccountButton();

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver, driverWaiter);
        // Кликаем на кнопку Зарегистрироваться
        loginPage.clickRegisterButtonInLoginPage();

        // Создаем объект класса RegisterPage
        RegisterPage registerPage = new RegisterPage(driver, driverWaiter);
        // Заполняем все поля валидными данными
        registerPage.fillPageRegister(name, email, password);
        // Нажимаем на кнопку Зарегистрироваться
        registerPage.clickRegisterButton();
        // Проверяем ошибку поля Пароль
        MatcherAssert.assertThat("Некорректный текст ошибки в поле 'Пароль'", registerPage.getFieldPasswordTextError(), is("Некорректный пароль"));
    }
}
