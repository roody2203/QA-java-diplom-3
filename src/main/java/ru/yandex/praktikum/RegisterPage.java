package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends  BasePage {
    public RegisterPage(WebDriver driver, WebDriverWait driverWaiter) {
        super(driver, driverWaiter);
    }

    // Локатор поля Имя страницы Регистрации
    private final By fieldName = By.xpath(".//fieldset[1]/div/div/input");
    // Локатор поля Email страницы Регистрации
    private final By fieldEmail = By.xpath(".//fieldset[2]/div/div/input");
    // Локатор поля Пароль страницы Регистрации
    private final By fieldPassword = By.xpath(".//fieldset[3]/div/div/input");
    // Локатор кнопки Зарегистрироваться страницы Регистрации
    private final By registerButton = By.className("button_button__33qZ0");
    // Локатор кнопки Войти страницы Регистрации
    private final By logInButton = By.className("Auth_link__1fOlj");
    // Локатор текста ошибки поля Пароль
    private final By fieldPasswordErrorText = By.className("input__error");

    @Step("Fill page registration")
    public void fillPageRegister(String name, String email, String password) {
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Click register button")
    public void clickRegisterButton() {
        clickButtonAndWait(registerButton);
    }

    @Step("Get field password error text")
    public String getFieldPasswordTextError() {
        return driver.findElement(fieldPasswordErrorText).getText();
    }

    @Step("Click log in button")
    public void clickLogInButton() {
        clickButtonAndWait(logInButton);
    }
}
