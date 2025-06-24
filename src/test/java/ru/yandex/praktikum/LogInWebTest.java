package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.praktikum.dto.LoginUserRequest;
import ru.yandex.praktikum.steps.DeleteUser;
import ru.yandex.praktikum.steps.LoginUser;

import static org.hamcrest.CoreMatchers.is;

public class LogInWebTest extends BaseWebTest {
    private String email;//создаем поле логин
    private String password;//создаем поле пароль

    @Override
    @Before
    public void init() throws Exception {
        super.init();

        email = generateRandomEmail();
        password = generateRandomValidPassword();
        String name = generateRandomName();

        // Создаем пользователя со сгенерированными данными через API
        createUserByAPI(email, password, name);
    }

    public void checkLogInHomePage(WebDriver driver, boolean useLogInButton, String url) {
        // Открываем стартовую страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект стартовой страницы
        HomePage homePage = new HomePage(driver, driverWaiter);

        // Кликаем по кнопке Войти или Личный кабинет
        if(useLogInButton) {
            homePage.clickLoginInToAccountButton();
        } else {
            homePage.clickPersonalAccountButton();
        }

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver, driverWaiter);
        // Заполняем все поля
        loginPage.fillPageRegister(email, password);
        // Нажимаем кнопку Войти
        loginPage.clickLogInButton();

        // Нажимаем кнопку Личный кабинет на стартовой странице
        homePage.clickPersonalAccountButton();
        // Ожидаем перехода на страницу
        driverWaiter.until(ExpectedConditions.urlContains(url));
        // Проверяем открытие страницы Профиля
        MatcherAssert.assertThat(driver.getCurrentUrl(), is(url));
    }

    // Тест входа по кнопке Войти в аккаунт на стартовой странице
    @Test
    @DisplayName("Check log in by log in button")
    public void checkLogInByLogInButton() throws InterruptedException {
        checkLogInHomePage(driver, true, EnvConfig.PROFILE_URL);
    }

    @Test
    @DisplayName("Check log in by personal account button")
    public void checkLogInByPersonalAccountButton() {
        checkLogInHomePage(driver, false, EnvConfig.PROFILE_URL);
    }

    @Test
    @DisplayName("Check log in from register page")
    public void checkLogInFromRegisterPageTest() {
        // Открываем стартовую страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект стартовой страницы
        HomePage homePage = new HomePage(driver, driverWaiter);
        // Кликаем по кнопке Войти на стартовой странице
        homePage.clickLoginInToAccountButton();

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver, driverWaiter);
        // Нажимаем на кнопку Зарегистрироваться
        loginPage.clickRegisterButtonInLoginPage();

        // Создаем объект класса RegisterPage
        RegisterPage registerPage = new RegisterPage(driver, driverWaiter);
        // Нажимаем кнопку Войти на странице Регистрации
        registerPage.clickLogInButton();

        // Заполняем все поля
        loginPage.fillPageRegister(email, password);
        // Нажимаем кнопку Войти
        loginPage.clickLogInButton();

        // Нажимаем кнопку Личный кабинет на стартовой странице
        homePage.clickPersonalAccountButton();
        // Ожидаем перехода на страницу
        driverWaiter.until(ExpectedConditions.urlContains(EnvConfig.PROFILE_URL));
        // Проверяем открытие страницы Профиля
        MatcherAssert.assertThat("Некорректный url", driver.getCurrentUrl(), is(EnvConfig.PROFILE_URL));
    }

    @Test
    @DisplayName("Check log in from recover password page")
    public void checkLogInFromRecoverPasswordPageTest() {
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект стартовой страницы
        HomePage homePage = new HomePage(driver, driverWaiter);
        // Кликаем по кнопке Войти на стартовой странице
        homePage.clickLoginInToAccountButton();

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver, driverWaiter);
        // Нажимаем на кнопку Восстановить пароль
        loginPage.clickRecoverPasswordButtonInLoginPage();

        // Создаем объект класса ForgotPassword
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver, driverWaiter);
        // Нажимаем кнопку Войти на странице Регистрации
        forgotPasswordPage.clickLogInButton();

        // Заполняем все поля
        loginPage.fillPageRegister(email, password);
        // Нажимаем кнопку Войти
        loginPage.clickLogInButton();

        // Нажимаем кнопку Личный кабинет на стартовой странице
        homePage.clickPersonalAccountButton();
        // Ожидаем перехода на страницу
        driverWaiter.until(ExpectedConditions.urlContains(EnvConfig.PROFILE_URL));
        // Проверяем открытие страницы Профиля
        MatcherAssert.assertThat("Некорректный url", driver.getCurrentUrl(), is(EnvConfig.PROFILE_URL));
    }

    @Override
    @After
    public void teardown()
    {
        // Удаляем созданного пользователя
        deleteUserByAPI(email, password);

        super.teardown();
    }
}
