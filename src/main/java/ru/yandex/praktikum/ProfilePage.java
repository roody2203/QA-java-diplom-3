package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait driverWaiter;

    // Локатор кнопки Конструктор страницы Регистрации
    private final By constructorButton = By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a/p");
    // Локатор кнопки Выход
    private final By logOutButton = By.className("Account_button__14Yp3");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.driverWaiter = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Step("Click constructor button")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Click log out button")
    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
}
