package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.dto.CreateUserRequest;
import ru.yandex.praktikum.dto.LoginUserRequest;
import ru.yandex.praktikum.steps.CreateUser;
import ru.yandex.praktikum.steps.DeleteUser;
import ru.yandex.praktikum.steps.LoginUser;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class LogInWebTest extends BaseWebTest {
    private String email;//создаем поле логин
    private String password;//создаем поле пароль

    private LoginUser loginUser;
    private DeleteUser deleteUser;

    @Override
    @Before
    public void init() throws Exception {
        super.init();

        loginUser = new LoginUser();
        deleteUser = new DeleteUser();
    }

    @Step("Generate random email")
    public String generateRandomEmail() {
        return RandomStringUtils.randomAlphabetic(5) + "@yandex.ru";
    }

    @Step("Generate random valid password")
    public String generateRandomValidPassword() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    @Step("Generate random name")
    public String generateRandomName() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    @Step("Create user by API")
    public void createUserByAPI(String email, String password, String name) {
        // Создаем пользователя
        CreateUser createUser = new CreateUser();
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail(email);
        request.setPassword(password);
        request.setName(name);
        ValidatableResponse response = createUser.createUser(request);
    }

    @Step("Check correct url after log in by log in button")
    public void checkCorrectProfileUrlByLogInButton(WebDriver driver) {
        MatcherAssert.assertThat(driver.getCurrentUrl(), is(containsString(EnvConfig.PROFILE_URL)));
    }

    /**@Step("Check correct url after log in by profile button")
    public void checkCorrectProfileUrlByProfileButton(WebDriver driver) {
        MatcherAssert.assertThat(driver.getCurrentUrl(), containsString(EnvConfig.BASE_URL));
    } */

    public void checkLogInHomePage(WebDriver driver, boolean useLogInButton) {
        email = generateRandomEmail();
        password = generateRandomValidPassword();
        String name = generateRandomName();

        // Создаем пользователя со сгенерированными данными через API
        createUserByAPI(email, password, name);

        // Открываем стартовую страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект стартовой страницы
        HomePage homePage = new HomePage(driver);

        // Кликаем по кнопке Войти или Личный кабинет
        if(useLogInButton) {
            homePage.clickLoginInToAccountButton();
        } else {
            homePage.clickPersonalAccountButton();
        }

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver);
        // Заполняем все поля
        loginPage.fillPageRegister(email, password);
        // Нажимаем кнопку Войти
        loginPage.clickLogInButton();

        // Нажимаем кнопку Личный кабинет на стартовой странице
        homePage.clickPersonalAccountButton();
        // Проверяем открытие страницы Профиля
        checkCorrectProfileUrlByLogInButton(driver);
    }

    // Тест входа по кнопке Войти в аккаунт на стартовой странице
    @Test
    @DisplayName("Check log in by log in button")
    public void checkLogInByLogInButton() throws InterruptedException {
        checkLogInHomePage(driver, true);
    }

    @Test
    @DisplayName("Check log in by personal account button")
    public void checkLogInByPersonalAccountButton() {
        checkLogInHomePage(driver, false);
    }
    @Test
    @DisplayName("Check log in from register page")
    public void checkLogInFromRegisterPageTest() {
        email = generateRandomEmail();
        password = generateRandomValidPassword();
        String name = generateRandomName();

        // Открываем стартовую страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем пользователя со сгенерированными данными через API
        createUserByAPI(email, password, name);

        // Создаем объект стартовой страницы
        HomePage homePage = new HomePage(driver);
        // Кликаем по кнопке Войти на стартовой странице
        homePage.clickLoginInToAccountButton();

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver);
        // Нажимаем на кнопку Зарегистрироваться
        loginPage.clickRegisterButtonInLoginPage();

        // Создаем объект класса RegisterPage
        RegisterPage registerPage = new RegisterPage(driver);
        // Нажимаем кнопку Войти на странице Регистрации
        registerPage.clickLogInButton();

        // Заполняем все поля
        loginPage.fillPageRegister(email, password);
        // Нажимаем кнопку Войти
        loginPage.clickLogInButton();

        // Нажимаем кнопку Личный кабинет на стартовой странице
        homePage.clickPersonalAccountButton();
        // Проверяем открытие страницы Профиля
        checkCorrectProfileUrlByLogInButton(driver);
    }

    @Test
    @DisplayName("Check log in from recover password page")
    public void checkLogInFromRecoverPasswordPageTest() {
        email = generateRandomEmail();
        password = generateRandomValidPassword();
        String name = generateRandomName();

        driver.get(EnvConfig.BASE_URL);

        // Создаем пользователя со сгенерированными данными через API
        createUserByAPI(email, password, name);

        // Создаем объект стартовой страницы
        HomePage homePage = new HomePage(driver);
        // Кликаем по кнопке Войти на стартовой странице
        homePage.clickLoginInToAccountButton();

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver);
        // Нажимаем на кнопку Восстановить пароль
        loginPage.clickRecoverPasswordButtonInLoginPage();

        // Создаем объект класса ForgotPassword
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        // Нажимаем кнопку Войти на странице Регистрации
        forgotPasswordPage.clickLogInButton();

        // Заполняем все поля
        loginPage.fillPageRegister(email, password);
        // Нажимаем кнопку Войти
        loginPage.clickLogInButton();

        // Нажимаем кнопку Личный кабинет на стартовой странице
        homePage.clickPersonalAccountButton();
        // Проверяем открытие страницы Профиля
        checkCorrectProfileUrlByLogInButton(driver);
    }

    @Override
    @After
    public void teardown()
    {
        // Удаляем созданного пользователя
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail(email);
        request.setPassword(password);
        ValidatableResponse response = loginUser.loginUser(request);
        String accessToken = response.extract().path("accessToken");
        deleteUser.deleteUser(accessToken);

        super.teardown();
    }
}
