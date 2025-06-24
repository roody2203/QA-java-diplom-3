package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage {

    // Локатор кнопки Конструктор страницы Регистрации
    private final By constructorButton = By.className("AppHeader_header__linkText__3q_va");
    // Локатор кнопки Выход
    private final By logOutButton = By.className("Account_button__14Yp3");

    public ProfilePage(WebDriver driver, WebDriverWait driverWaiter) {
        super(driver, driverWaiter);
    }

    @Step("Click constructor button")
    public void clickConstructorButton() {
        clickButtonAndWait(constructorButton);
    }

    @Step("Click log out button")
    public void clickLogOutButton() {
        clickButtonAndWait(logOutButton);
    }
}
