package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.dto.CreateUserRequest;
import ru.yandex.praktikum.steps.CreateUser;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class TransferToAccountTest extends BaseWebTest {
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

    @Step("Check correct url after click on personal account button without log in")
    public void checkCorrectUrlAfterClickProfileButtonWithoutLogIn(WebDriver driver) {
        MatcherAssert.assertThat(driver.getCurrentUrl(), is(EnvConfig.LOGIN_URL));
    }

    @Step("Check correct url after log in by log in button")
    public void checkCorrectProfileUrl(WebDriver driver) {
        MatcherAssert.assertThat(driver.getCurrentUrl(), is(containsString(EnvConfig.PROFILE_URL)));
    }

    @Test
    @DisplayName("Check transfer to personal account without login")
    public void checkTransferToAccountWithoutLoginTest() {
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver);
        // Кликаем на кнопку Личный кабинет
        homePage.clickPersonalAccountButton();

        // Проверяем что открывается страница Входа
        checkCorrectUrlAfterClickProfileButtonWithoutLogIn(driver);
    }

    @Test
    @DisplayName("Check transfer to personal account with login")
    public void checkTransferToAccountWithLoginTest() {
        String email = generateRandomEmail();
        String password = generateRandomValidPassword();
        String name = generateRandomName();

        // Создаем пользователя со сгенерированными данными через API
        createUserByAPI(email, password, name);

        // Открываем главную страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver);
        // Кликаем на кнопку Личный кабинет
        homePage.clickPersonalAccountButton();

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver);
        // Заполняем все поля
        loginPage.fillPageRegister(email, password);
        // Нажимаем кнопку Войти
        loginPage.clickLogInButton();

        // Кликаем на кнопку Личный кабинет после входа в аккаунт
        homePage.clickPersonalAccountButton();
        // Проверяем что открывается страница Входа
        checkCorrectProfileUrl(driver);
    }
}
