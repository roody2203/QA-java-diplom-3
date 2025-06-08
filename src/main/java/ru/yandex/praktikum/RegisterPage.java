package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait driverWaiter;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.driverWaiter = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    // Локатор поля Имя страницы Регистрации
    private final By fieldName = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    // Локатор поля Email страницы Регистрации
    private final By fieldEmail = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    // Локатор поля Пароль страницы Регистрации
    private final By fieldPassword = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input");
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
        driver.findElement(registerButton).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Check url after click register button")
    public void checkUrlAfterClickRegisterButton() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        MatcherAssert.assertThat(driver.getCurrentUrl(), is(EnvConfig.LOGIN_URL));
    }

    @Step("Check field password error text")
    public void checkFieldPasswordTextError() {
        String textError = driver.findElement(fieldPasswordErrorText).getText();
        MatcherAssert.assertThat("Некорректный пароль", is(textError));
    }

    @Step("Click log in button")
    public void clickLogInButton() {
        driver.findElement(logInButton).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
}
