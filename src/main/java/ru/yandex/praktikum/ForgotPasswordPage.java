package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private final WebDriverWait driverWaiter;

    // Локатор кнопки Войти в аккаунт
    private final By logInButton = By.className("Auth_link__1fOlj");


    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.driverWaiter = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Step("Click logInButton")
    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }
}
