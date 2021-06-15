package com.cars.trip.onlinetrips.ui;


import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import com.cars.trip.onlinetrips.factory.Creator;
import com.cars.trip.onlinetrips.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@Import(Creator.class)
public class CarFormularTest {
    private static final int RESPONSE_TIMEOUT = 30;
    private static final String CORRECT_USER = "username";
    private static final String CORRECT_PASSWORD = "password";
    private static final String CORRECT_EMAIL = "email@email.com";

    @Value("${server.port}")
    private String port = "";
    private WebDriver driver;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private Creator creator;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
        String chromedriverPath = AuthenticationTest.class.getResource("/chromedriver.exe").getFile();
        System.setProperty("webdriver.chrome.driver", chromedriverPath );
    }

    @BeforeEach
    public void beforeEach() {
        String circleCIChromedriverPath = "/usr/local/bin/chromedriver";
        if (new File(circleCIChromedriverPath).exists()) {
            System.setProperty("webdriver.chrome.driver", circleCIChromedriverPath);
        }

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);

        driver = new ChromeDriver(chromeOptions);

        creator.saveEntities(new User("firstname",
                "lastname",
                CORRECT_USER,
                CORRECT_EMAIL,
                passwordEncoder.encode(CORRECT_PASSWORD)));
    }


    @AfterEach
    public void afterEach() {
        if (driver != null) driver.quit();
        userRepository.delete(userRepository.findByUsername(CORRECT_USER).get());
    }

    private void signIn(final String username, final String password, final int timeout) {
        driver.get(String.format("http://localhost:%s/#/signin", port));
        driver.findElement(By.cssSelector("input[name=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        WebDriverWait wt = new WebDriverWait(driver,timeout);
        wt.until(ExpectedConditions.urlContains("#/profile"));
    }

    @Test
    public void putToForm(){
        final String EXPECTED = "TEST";
        signIn(CORRECT_USER, CORRECT_PASSWORD, RESPONSE_TIMEOUT);

        putToForm(EXPECTED);
        assertTrue(carRepository.existsByCarBrand(EXPECTED));
    }

    private void putToForm(final String expected) {
        driver.get(String.format("http://localhost:%s/#/interestpoint/add", port));
        driver.findElement(By.name("carBrand")).sendKeys(expected);
        driver.findElement(By.name("carModel")).sendKeys(expected);
        driver.findElement(By.name("countryOfOrigin")).sendKeys(expected);
        driver.findElement(By.name("enginePowerKW")).sendKeys(expected);
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        WebDriverWait wt = new WebDriverWait(driver, RESPONSE_TIMEOUT);
        wt.until(ExpectedConditions.urlContains("#/profile"));
    }
}
