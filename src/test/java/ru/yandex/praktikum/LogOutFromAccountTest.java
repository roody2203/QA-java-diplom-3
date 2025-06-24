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

public class LogOutFromAccountTest extends  BaseWebTest {
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
    @DisplayName("Check log out from account")
    public void checkLogOutOfAccountTest() throws InterruptedException {
        // Открываем стартовую страницу
        driver.get(EnvConfig.BASE_URL);

        // Создаем объект стартовой страницы
        HomePage homePage = new HomePage(driver, driverWaiter);
        // Кликаем по кнопке Войти
        homePage.clickLoginInToAccountButton();

        // Создаем объект класса LoginPage
        LoginPage loginPage = new LoginPage(driver, driverWaiter);
        // Заполняем все поля
        loginPage.fillPageRegister(email, password);
        // Нажимаем кнопку Войти
        loginPage.clickLogInButton();

        // Нажимаем кнопку Личный кабинет на стартовой странице
        homePage.clickPersonalAccountButton();

        // Создаем объект класса ProfilePage
        ProfilePage profilePage = new ProfilePage(driver, driverWaiter);
        // Нажимаем на кнопку Выйти
        profilePage.clickLogOutButton();
        // Ожидаем перехода на страницу
        driverWaiter.until(ExpectedConditions.urlContains(EnvConfig.PROFILE_URL));
        // Проверяем, что открылась страница Входа с введением данных
        MatcherAssert.assertThat(driver.getCurrentUrl(), is(EnvConfig.PROFILE_URL));
    }
}
