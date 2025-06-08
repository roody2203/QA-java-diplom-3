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

import static org.hamcrest.CoreMatchers.is;

public class LogOutFromAccountTest extends  BaseWebTest {
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

    @Step("Check correct url after log out from account")
    public void checkCorrectUrl(WebDriver driver) {
        MatcherAssert.assertThat(driver.getCurrentUrl(), is(EnvConfig.LOGIN_URL));
    }

    @Test
    @DisplayName("Check log out from account")
    public void checkLogOutOfAccountTest() throws InterruptedException {
        String email = generateRandomEmail();
        String password = generateRandomValidPassword();
        String name = generateRandomName();

        // Создаем пользователя со сгенерированными данными через API
        createUserByAPI(email, password, name);

        // Открываем стартовую страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект стартовой страницы
        HomePage homePage = new HomePage(driver);
        // Кликаем по кнопке Войти
        homePage.clickLoginInToAccountButton();

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver);
        // Заполняем все поля
        loginPage.fillPageRegister(email, password);
        // Нажимаем кнопку Войти
        loginPage.clickLogInButton();

        // Нажимаем кнопку Личный кабинет на стартовой странице
        homePage.clickPersonalAccountButton();

        // Создаем объект класса ProfilePage
        ProfilePage profilePage = new ProfilePage(driver);
        // Нажимаем на кнопку Выйти
        profilePage.clickLogOutButton();
        Thread.sleep(3000);
        // Проверяем, что открылась страница Входа с введением данных
        checkCorrectUrl(driver);
    }
}
