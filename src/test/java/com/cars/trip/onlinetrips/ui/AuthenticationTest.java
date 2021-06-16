package com.cars.trip.onlinetrips.ui;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import com.cars.trip.onlinetrips.entity.Car;
import com.cars.trip.onlinetrips.factory.Creator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
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
import java.util.Random;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@Import(Creator.class)
public class AuthenticationTest {
    private static final int RESPONSE_TIMEOUT = 30;
    private static final String CORRECT_USER = "username";
    private static final String CORRECT_PASSWORD = "password";
    private static final String CORRECT_EMAIL = "email@email.com";

    @Value("${server.port}")
    private String port = "";
    private WebDriver driver;
    private LocalStorage localStorage;

    @Autowired
    private Creator creator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
//        String chromedriverPath = AuthenticationTest.class.getResource("/chromedriver.exe").getFile();
//        System.setProperty("webdriver.chrome.driver", chromedriverPath );
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

        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        localStorage = webStorage.getLocalStorage();

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

    @Test
    public void loginUser() {
        signIn(CORRECT_USER, CORRECT_PASSWORD, false);
        assertTrue(localStorage.keySet().contains("user"));
    }

    @Test
    public void loginUserBadCredentials() {
        signIn(String.valueOf(new Random().nextInt()), String.valueOf(new Random().nextInt()), true);
        assertFalse(localStorage.keySet().contains("user"));
    }

    private void signIn(String username, String password, boolean expectFail) {
        driver.get(String.format("http://localhost:%s/#/signin", port));
        driver.findElement(By.cssSelector("input[name=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        WebDriverWait wait = new WebDriverWait(driver, AuthenticationTest.RESPONSE_TIMEOUT);
        if (!expectFail){
            wait.until(ExpectedConditions.urlContains("#/profile"));
        }else{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div [role='alert']")));
        }
    }

}
