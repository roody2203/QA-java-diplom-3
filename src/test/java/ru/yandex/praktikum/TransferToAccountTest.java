package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.praktikum.dto.LoginUserRequest;
import ru.yandex.praktikum.steps.DeleteUser;
import ru.yandex.praktikum.steps.LoginUser;

import static org.hamcrest.CoreMatchers.is;

public class TransferToAccountTest extends BaseWebTest {
    String email = generateRandomEmail();
    String password = generateRandomValidPassword();
    String name = generateRandomName();

    @Override
    @Before
    public void init() throws Exception {
        super.init();

        // Создаем пользователя со сгенерированными данными через API
        createUserByAPI(email, password, name);
    }

    @Override
    @After
    public void teardown()
    {
        // Удаляем созданного пользователя
        deleteUserByAPI(email, password);

        super.teardown();
    }

    @Test
    @DisplayName("Check transfer to personal account without login")
    public void checkTransferToAccountWithoutLoginTest() {
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver, driverWaiter);
        // Кликаем на кнопку Личный кабинет
        homePage.clickPersonalAccountButton();
        // Ожидаем перехода на страницу
        driverWaiter.until(ExpectedConditions.urlContains(EnvConfig.LOGIN_URL));
        // Проверяем что открывается страница Входа
        MatcherAssert.assertThat(driver.getCurrentUrl(), is(EnvConfig.LOGIN_URL));
    }

    @Test
    @DisplayName("Check transfer to personal account with login")
    public void checkTransferToAccountWithLoginTest() {


        // Открываем главную страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект класса HomePage
        HomePage homePage = new HomePage(driver, driverWaiter);
        // Кликаем на кнопку Личный кабинет
        homePage.clickPersonalAccountButton();

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver, driverWaiter);
        // Заполняем все поля
        loginPage.fillPageRegister(email, password);
        // Нажимаем кнопку Войти
        loginPage.clickLogInButton();

        // Кликаем на кнопку Личный кабинет после входа в аккаунт
        homePage.clickPersonalAccountButton();
        // Ожидаем перехода на страницу
        driverWaiter.until(ExpectedConditions.urlContains(EnvConfig.PROFILE_URL));
        // Проверяем что открывается страница Профиля
        MatcherAssert.assertThat(driver.getCurrentUrl(), is(EnvConfig.PROFILE_URL));
    }
}
