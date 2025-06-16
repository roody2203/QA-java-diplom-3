package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;

public class BasePage
{
    protected final WebDriver driver;
    protected final WebDriverWait driverWaiter;

    public BasePage(WebDriver driver, WebDriverWait driverWaiter) {
        this.driver = driver;
        this.driverWaiter = driverWaiter;
    }

    @Step("Click button and wait")
    public void clickButtonAndWait(By button)
    {
        driverWaiter.until(ExpectedConditions.elementToBeClickable(button));
        driver.findElement(button).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Is visible field")
    public boolean isVisibleField(By field) {
        return driver.findElement(field).isDisplayed();
    }

    @Step("Is current field")
    public boolean isCurrentField(By field) {
        WebElement fieldVision = driverWaiter.until(ExpectedConditions.visibilityOfElementLocated(field));
        driverWaiter.until(ExpectedConditions.attributeContains(field, "class", "tab_tab_type_current" ));
        return fieldVision.getAttribute("class").contains("tab_tab_type_current");
    }
}
