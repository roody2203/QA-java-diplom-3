package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait driverWaiter;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driverWaiter = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    // Локатор поля Email на странице входа
    private final By fieldEmail = By.name("name");

    // Локатор поля Пароль на странице входа
    private final By fieldPassword = By.name("Пароль");

    // Локатор кнопки Войти на странице входа
    private final By loginButton = By.className("button_button__33qZ0");

    // Локатор кнопки Зарегистрироваться на странице входа
    private final By registerButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a");

    // Локатор кнопки Восстановить пароль на странице входа
    private final By recoverPasswordButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a");

    @Step("Fill page login")
    public void fillPageRegister(String email, String password) {
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Click login button")
    public void clickLogInButton() {
        driver.findElement(loginButton).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Click register button in login page")
    public void clickRegisterButtonInLoginPage() {
        driver.findElement(registerButton).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Click recover password button in login page")
    public void clickRecoverPasswordButtonInLoginPage() {
        driver.findElement(recoverPasswordButton).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
}
