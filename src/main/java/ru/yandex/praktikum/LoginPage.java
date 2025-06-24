package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    // Локатор поля Email на странице входа
    private final By fieldEmail = By.name("name");

    // Локатор поля Пароль на странице входа
    private final By fieldPassword = By.name("Пароль");

    // Локатор кнопки Войти на странице входа
    private final By loginButton = By.className("button_button__33qZ0");

    // Локатор кнопки Зарегистрироваться на странице входа
    private final By registerButton = By.xpath(".//a[text()='Зарегистрироваться']");

    // Локатор кнопки Восстановить пароль на странице входа
    private final By recoverPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver, WebDriverWait driverWaiter) {
        super(driver, driverWaiter);
    }

    @Step("Fill page login")
    public void fillPageRegister(String email, String password) {
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Click login button")
    public void clickLogInButton() {
        clickButtonAndWait(loginButton);
    }

    @Step("Click register button in login page")
    public void clickRegisterButtonInLoginPage() {
        clickButtonAndWait(registerButton);
    }

    @Step("Click recover password button in login page")
    public void clickRecoverPasswordButtonInLoginPage() {
        clickButtonAndWait(recoverPasswordButton);
    }
}
