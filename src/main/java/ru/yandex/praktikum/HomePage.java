package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    // Локатор кнопки Войти в аккаунт
    private final By logInToAccountButton = By.className("button_button_type_primary__1O7Bx");
    // Локатор кнопки Личный кабинет
    private final By personalAccountButton = By.cssSelector("#root > div > header > nav > a > p");
    // Локатор кнопки Булки
    private final By bunButton = By.xpath(".//span[text()='Булки']");
    // Локатор кнопки Соусы
    private final By saucesButton = By.xpath(".//span[text()='Соусы']");
    // Локатор кнопки Начинки
    private final By toppingsButton = By.xpath(".//span[text()='Начинки']");
    // Локатор поля Булки
    private final By bunField = By.xpath(".//div[span[text()='Булки']]");
    // Локатор поля Соусы
    private final By saucesField = By.xpath(".//div[span[text()='Соусы']]");
    // Локатор поля Начинки
    private final By toppingsField = By.xpath(".//div[span[text()='Начинки']]");

    public HomePage(WebDriver driver, WebDriverWait driverWaiter) {
        super(driver, driverWaiter);
    }

    @Step("Open Home Page")
    public void openHomePage() {
        driver.get(EnvConfig.BASE_URL);
    }

    @Step("Click login to account button")
    public void clickLoginInToAccountButton() {
        clickButtonAndWait(logInToAccountButton);
    }

    @Step("Click login to personal account button")
    public void clickPersonalAccountButton() {
        clickButtonAndWait(personalAccountButton);
    }

    @Step("Click bun button")
    public void clickBunButton() {
        clickButtonAndWait(bunButton);
    }

    @Step("Click sauces button")
    public void clickSaucesButton() {
        clickButtonAndWait(saucesButton);
    }

    @Step("Click toppings button")
    public void clickToppingsButton() {
        clickButtonAndWait(toppingsButton);
    }

    @Step("Is current bun field")
    public boolean isCurrentBunField() {
        return isCurrentField(bunField);
    }

    @Step("Is current sauces field")
    public boolean isCurrentSaucesField() {
        return isCurrentField(saucesField);
    }

    @Step("Is current toppings field")
    public boolean isCurrentToppingsField() {
        return isCurrentField(toppingsField);
    }
}
