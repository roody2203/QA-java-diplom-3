package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseWebTest {
    protected WebDriver driver;

    @Before
    public void init() throws Exception {
        String browser = "yandex";

        if(browser.equalsIgnoreCase("chrome")) {
            /** Создаем драйвер для браузера Chrome */
            WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("yandex"))
        {
            /** Создаем драйвер для Яндекс Браузера */
            String appDataPath = System.getenv("LOCALAPPDATA");

            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.setBinary(appDataPath + "\\Yandex\\YandexBrowser\\Application\\browser.exe");

            driver = new ChromeDriver(options);
        }
        else {
            throw new Exception("Incorrect browser");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.IMPLICITY_WAIT));
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
