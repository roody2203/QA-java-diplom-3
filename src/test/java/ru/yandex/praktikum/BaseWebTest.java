package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.dto.CreateUserRequest;
import ru.yandex.praktikum.steps.CreateUser;

import java.time.Duration;

public class BaseWebTest {
    protected WebDriver driver;
    protected WebDriverWait driverWaiter;

    @Before
    public void init() throws Exception {
        String browser = System.getProperty("browser", "yandex");

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

        driverWaiter = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Step("Generate random email")
    public String generateRandomEmail() {
        return RandomStringUtils.randomAlphabetic(5) + "@yandex.ru";
    }

    @Step("Generate random valid password")
    public String generateRandomValidPassword() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    @Step("Generate random name")
    public String generateRandomName() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    @Step("Create user by API")
    public void createUserByAPI(String email, String password, String name) {
        // Создаем пользователя
        CreateUser createUser = new CreateUser();
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail(email);
        request.setPassword(password);
        request.setName(name);
        ValidatableResponse response = createUser.createUser(request);
    }
}
