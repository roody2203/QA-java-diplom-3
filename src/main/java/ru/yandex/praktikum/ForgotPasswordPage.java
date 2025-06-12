package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage extends BasePage {

    // Локатор кнопки Войти в аккаунт
    private final By logInButton = By.className("Auth_link__1fOlj");


    public ForgotPasswordPage(WebDriver driver, WebDriverWait driverWaiter) {
        super(driver, driverWaiter);
    }

    @Step("Click logInButton")
    public void clickLogInButton() {
        clickButtonAndWait(logInButton);
    }
}
