package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.CoreMatchers.is;

public class TransfersFromPersonalAccountTest extends BaseWebTest {

    @Test
    @DisplayName("Check transfer to constructor from personal account")
    public void checkTransferToConstructorTest() {
        String email = generateRandomEmail();
        String password = generateRandomValidPassword();
        String name = generateRandomName();

        // Создаем пользователя со сгенерированными данными через API
        createUserByAPI(email, password, name);

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
        // Нажимаем кнопку Конструктор
        profilePage.clickConstructorButton();
        // Ожидаем перехода на страницу
        driverWaiter.until(ExpectedConditions.urlContains(EnvConfig.BASE_URL));
        // Проверяем открытие страницы конструктора
        MatcherAssert.assertThat(driver.getCurrentUrl(), is(EnvConfig.BASE_URL));
    }
}
