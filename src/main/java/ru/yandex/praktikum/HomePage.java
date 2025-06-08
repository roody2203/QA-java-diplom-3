package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait driverWaiter;

    // Локатор кнопки Войти в аккаунт
    private final By logInToAccountButton = By.className("button_button_type_primary__1O7Bx");
    // Локатор кнопки Личный кабинет
    private final By personalAccountButton = By.cssSelector("#root > div > header > nav > a > p");
    // Локатор кнопки Булки
    private final By bunButton = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]/span");
    // Локатор кнопки Соусы
    private final By saucesButton = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]/span");
    // Локатор кнопки Начинки
    private final By toppingsButton = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]/span");
    // Локатор поля Булки
    private final By bunField = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]/span");
    // Локатор поля Соусы
    private final By saucesField = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/ul[2]");
    // Локатор поля Начинки
    private final By toppingsField = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/ul[3]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.driverWaiter = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Step("Open Home Page")
    public void openHomePage() {
        driver.get(EnvConfig.BASE_URL);
    }

    @Step("Click login to account button")
    public void clickLoginInToAccountButton() {
        driver.findElement(logInToAccountButton).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Click login to personal account button")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Click bun button")
    public void clickBunButton() {
        driver.findElement(bunButton).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Click sauces button")
    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Click toppings button")
    public void clickToppingsButton() {
        driver.findElement(toppingsButton).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Check visible field")
    public void checkVisibleField(By field) {
        driver.findElement(field).isDisplayed();
    }

    @Step("Check visible bun field")
    public void checkVisibleBunField() {
        checkVisibleField(bunField);
    }

    @Step("Check visible sauces field")
    public void checkVisibleSaucesField() {
        checkVisibleField(saucesField);
    }

    @Step("Check visible toppings field")
    public void checkVisibleToppingsField() {
        checkVisibleField(toppingsField);
    }
}
