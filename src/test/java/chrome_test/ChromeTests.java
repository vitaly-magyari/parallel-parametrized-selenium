package chrome_test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Execution(ExecutionMode.CONCURRENT)
class ChromeTests {

    private WebDriver chrome;

    @BeforeAll
     static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        chrome = new ChromeDriver();
    }

    @Test
    void helloTest() {
        chrome.navigate().to("https://www.google.com");
        final By q = By.name("q");

        new WebDriverWait(chrome, 5).until(
                d -> d.findElement(q).isDisplayed()
        );
        chrome.findElement(q).sendKeys("Selenium" + Keys.ENTER.toString());

        Assertions.assertTrue(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://google.com","https://yandex.ru"})
    void enumerateSites(String urlString) throws InterruptedException {
        chrome.navigate().to(urlString);
        Thread.sleep(2000);
    }

    @AfterEach
    void teardown() {
        chrome.quit();
    }
}

