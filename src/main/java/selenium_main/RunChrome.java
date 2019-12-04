package selenium_main;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RunChrome {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver chrome = new ChromeDriver();
        try {
            chrome.navigate().to("https://www.google.com");
            final By q = By.name("q");

            new WebDriverWait(chrome, 5).until(
                    d -> d.findElement(q).isDisplayed()
            );
            chrome.findElement(q).sendKeys("Selenium" + Keys.ENTER.toString());

        } finally {
            chrome.quit();
        }
    }
}
